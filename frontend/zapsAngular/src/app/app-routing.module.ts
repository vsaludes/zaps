import { Error404PageComponent } from './shared/pages/error404-page/error404-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriaComponent } from './views/pages/categoria/categoria.component';
import { AsfaltoComponent } from './views/pages/asfalto/asfalto.component';
import { TrailComponent } from './views/pages/trail/trail.component';



const routes: Routes = [
  {
    path: '',
    redirectTo:'/home',
    pathMatch:'full'
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then (m => m.AuthModule),
  },
  {
    path: 'home',
    loadChildren: () => import('./views/views.module').then (m => m.ViewsModule),
  },
  {
    path: 'asfalto',
    component: AsfaltoComponent,
  },
  {
    path: 'trail',
    component: TrailComponent,
  },
  {
    path: '404',
    component: Error404PageComponent,
  },
  {
    path: '**',
    redirectTo: '404'
  },
  {
    path: 'categoria/:id',
    component: CategoriaComponent,
  },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
