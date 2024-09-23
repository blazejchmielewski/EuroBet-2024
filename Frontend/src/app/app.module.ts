import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HomeModule } from './home/home.module';
import { AuthModule } from './auth/auth.module';
import { AuthInterceptService } from './core/services/auth-intercept.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HomeModule,
    AuthModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
