import { Component, Input, inject } from '@angular/core';
import { Producto } from '../../../core/interfaces/producto';


@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrl: './producto.component.css'
})
export class ProductoComponent {

  @Input({required:true}) producto!:Producto;


}
