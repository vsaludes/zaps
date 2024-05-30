import { Component, Input, OnInit } from '@angular/core';
import { Categoria } from '../../../core/interfaces/categoria';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrl: './categoria.component.css',

})
export class CategoriaComponent{


  @Input({required:true}) categoria!:Categoria;
}
