import { Component, Input } from '@angular/core';
import { Categoria } from '../../../core/interfaces/categoria';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrl: './categoria.component.css'
})
export class CategoriaComponent {

  @Input({required:true}) categoria!:Categoria;
}
