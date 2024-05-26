import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './views/pages/header/header.component';
import { FooterComponent } from './views/pages/footer/footer.component';
import { ViewsModule } from './views/views.module';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AuthModule } from './auth/auth.module';
import { LoginComponent } from './auth/pages/login/login.component';


@NgModule({ declarations: [
        AppComponent,
    ],
    bootstrap: [AppComponent], imports: [BrowserModule,
        AppRoutingModule,
        ViewsModule,
        AuthModule], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class AppModule { }
