import { Component, Input, OnInit, inject } from '@angular/core';
import { Producto } from '../../../core/interfaces/producto';
import { ProductosService } from '../../../core/services/productos.service';
import { CategoriasService } from '../../../core/services/categorias.service';
import { Categoria } from '../../../core/interfaces/categoria';

@Component({
  selector: 'app-asfalto',
  templateUrl: './asfalto.component.html',
  styleUrl: './asfalto.component.css'
})
export class AsfaltoComponent {

  categoriasService = inject(CategoriasService)
  categorias:Categoria[] = [];
  productosService = inject(ProductosService)
  productos: Producto[] = [];

   @Input({required:true}) producto!:Producto;

   items: any[] = [];

   constructor(private productoService: ProductosService) { }

   ngOnInit(): void {
     this.productoService.getDataByCategory(1).subscribe(data => {
       this.items = data;
     });
   }

}
