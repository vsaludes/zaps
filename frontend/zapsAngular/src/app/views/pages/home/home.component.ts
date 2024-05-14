import { Component, OnInit, inject } from '@angular/core';
import { CategoriasService } from '../../../core/services/categorias.service';
import { Categoria } from '../../../core/interfaces/categoria';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent implements OnInit{

  categoriasService = inject(CategoriasService)
  categorias:Categoria[] = [];

  ngOnInit(): void {
    this.categoriasService.getAll().then(res => this.categorias = res)

  }

  images = [
    { src: 'assets/images/back1.jpg' },
    { src: 'assets/images/back2.jpg' },
    { src: 'assets/images/back3.jpg' },
  ]
}
