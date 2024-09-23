import { NgModule } from '@angular/core';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { SharedModule } from '../shared/shared.module';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent
  ], exports: [
    RegisterComponent,
    LoginComponent
  ], imports: [
    SharedModule,
    CommonModule,
    RouterModule,

  ]
})
export class AuthModule { }
