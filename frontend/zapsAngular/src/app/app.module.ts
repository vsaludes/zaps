import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './views/pages/header/header.component';
import { FooterComponent } from './views/pages/footer/footer.component';
import { ViewsModule } from './views/views.module';
import { AuthModule } from './auth/auth.module';
import { LoginComponent } from './auth/pages/login/login.component';
import { JwtInterceptorService } from './auth/Services/jwt-interceptor.service';
import { ErrorInterceptorService } from './auth/Services/error-interceptor.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ViewsModule,
    HttpClientModule,
    AuthModule
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS,useClass:JwtInterceptorService,multi:true},
    {provide:HTTP_INTERCEPTORS,useClass:ErrorInterceptorService,multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
