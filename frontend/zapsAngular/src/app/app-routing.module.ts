import { Error404PageComponent } from './shared/pages/error404-page/error404-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

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
    path: '404',
    component: Error404PageComponent,
  },
  {
    path: '**',
    redirectTo: '404'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
