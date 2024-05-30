import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ViewsRoutingModule } from './views-routing.module';
import { HeaderComponent } from './pages/header/header.component';
import { FooterComponent } from './pages/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { CarouselComponent } from './pages/carousel/carousel.component';
import { ProductoComponent } from './pages/producto/producto.component';
import { CategoriaComponent } from './pages/categoria/categoria.component';
import { AsfaltoComponent } from './pages/asfalto/asfalto.component';
import { TrailComponent } from './pages/trail/trail.component';




@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    CarouselComponent,
    ProductoComponent,
    CategoriaComponent,
    AsfaltoComponent,
    TrailComponent,
  ],
  imports: [
    CommonModule,
    ViewsRoutingModule
  ],
  exports: [
    HeaderComponent,
    FooterComponent
  ]
})
export class ViewsModule { }
