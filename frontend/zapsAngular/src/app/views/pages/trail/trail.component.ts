import { Component, Input, OnInit, inject } from '@angular/core';
import { Categoria } from '../../../core/interfaces/categoria';
import { Producto } from '../../../core/interfaces/producto';
import { CategoriasService } from '../../../core/services/categorias.service';
import { ProductosService } from '../../../core/services/productos.service';

@Component({
  selector: 'app-trail',
  templateUrl: './trail.component.html',
  styleUrls: ['./trail.component.css']
})
export class TrailComponent implements OnInit {

  categoriasService = inject(CategoriasService)
  categorias:Categoria[] = [];
  productosService = inject(ProductosService)
  productos: Producto[] = [];

   @Input({required:true}) producto!:Producto;

   items: any[] = [];

   constructor(private productoService: ProductosService) { }

   ngOnInit(): void {
     this.productoService.getDataByCategory(2).subscribe(data => {
       this.items = data;
     });
   }


}
