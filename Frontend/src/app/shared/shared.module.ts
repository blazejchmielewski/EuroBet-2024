import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [],
  imports: [
    CommonModule, ReactiveFormsModule, MaterialModule, HttpClientModule
  ], exports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class SharedModule { }
