package com.edix.restful.zaps.restcontrollerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;


import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.CarritoProducto;
import com.edix.restful.zaps.modelo.entities.Pedido;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.restcontroller.PedidoController;
import com.edix.restful.zaps.service.CarritoService;
import com.edix.restful.zaps.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.http.HttpStatusCodes;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;




@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestControllerImpl implements PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private CarritoService carritoService;
    

    @Override
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable int idPedido) {
        Pedido pedido = pedidoService.buscarPedidoPorId(idPedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pedido>> buscarPedidoPorUsuario(@PathVariable int idUsuario) {
        List<Pedido> pedidos = pedidoService.buscarPedidoPorUsuario(idUsuario);
        return ResponseEntity.ok(pedidos);
    }

    
    
    
    @Override
    @PostMapping("/procesar/{idCarrito}")
    public ResponseEntity<Object> procesarPedido(@PathVariable int idCarrito) {
        Carrito carrito = carritoService.buscarCarritoPorId(idCarrito);
        if (carrito == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El carrito no existe");
        }
        
        BigDecimal precioTotal = calcularPrecioTotalCarrito(carrito);
               
        boolean emailEnviado = enviarEmailConDetallesPedido(carrito);
        if (!emailEnviado) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo electrónico");
        }
        
        boolean pedidoProcesado = pedidoService.procesarPedido(idCarrito, precioTotal);
        if (!pedidoProcesado) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el pedido");
        }
        
        String jsonPedido = generarJsonDelPedido(carrito, precioTotal);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonPedido);
        
    }
    
    private boolean enviarEmailConDetallesPedido(Carrito carrito) {
        String destinatario = carrito.getUsuario().getEmail();
        //System.out.println("Correo electrónico del destinatario: " + destinatario);
        
        String asunto = "Confirmación de pedido";
        String cuerpo = "Gracias por tu pedido. Detalles:\n";
        for (Producto producto : carrito.getProductos()) {
            cuerpo += "Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Color: " + producto.getColor() + ", Talla: " + producto.getTalla() + "\n";
        }

        Email from = new Email("zaps.running@gmail.com");
        Email to = new Email(destinatario);
        Content content = new Content("text/plain", cuerpo);
        Mail mail = new Mail(from, asunto, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            
            if (response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK || 
                response.getStatusCode() == HttpStatusCodes.STATUS_CODE_ACCEPTED) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private String generarJsonDelPedido(Carrito carrito, BigDecimal precioTotal) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonPedido = objectMapper.createObjectNode();
        jsonPedido.put("idCarrito", carrito.getIdCarrito());
        jsonPedido.put("usuario", carrito.getUsuario().getNombre());
        jsonPedido.put("precioTotal", precioTotal);

        ArrayNode productosJson = objectMapper.createArrayNode();
        for (Producto producto : carrito.getProductos()) {
            ObjectNode productoJson = objectMapper.createObjectNode();
            productoJson.put("id", producto.getIdProducto());
            productoJson.put("nombre", producto.getNombre());
            productoJson.put("precio", producto.getPrecio());
            productoJson.put("color", producto.getColor());
            
            productosJson.add(productoJson);
        }
        jsonPedido.set("productos", productosJson);

        return jsonPedido.toString();
    }

    
    
    
    
    
    private BigDecimal calcularPrecioTotalCarrito(Carrito carrito) {
        BigDecimal precioTotal = BigDecimal.ZERO;
        for (CarritoProducto carritoProducto : carrito.getCarritoProducto()) {
            precioTotal = precioTotal.add(carritoProducto.getSubtotal());
        }
        return precioTotal;
    }
       
    @Override
    @PostMapping("/cancelar/{idPedido}")
    public ResponseEntity<String> cancelarPedido(@PathVariable int idPedido) {
        boolean result = pedidoService.cancelarPedido(idPedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("El pedido ha sido cancelado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido ya no se puede cancelar");
        }
    }

    @Override
    @PostMapping("/entregar/{idPedido}")
    public ResponseEntity<String> marcarPedidoEntregado(@PathVariable int idPedido) {
        boolean result = pedidoService.marcarPedidoEntregado(idPedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("pedido entregado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede marcar como entregado");
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodosPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.buscarTodos();
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build(); 
            } else {
                return ResponseEntity.ok(pedidos); 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
    @Override
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> eliminarPedidoPorId(@PathVariable int idPedido) {
        boolean result = pedidoService.eliminarPedidoPorId(idPedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}