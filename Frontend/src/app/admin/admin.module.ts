import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin/admin.component';
import { RouterModule } from '@angular/router';
import { MatchResultComponent } from './match-result/match-result.component';
import { SharedModule } from '../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AdminComponent,
    MatchResultComponent,
    
  ],
  imports: [
    CommonModule,
    RouterModule,
    BrowserModule,
    SharedModule,
    FormsModule,
  ], exports: [
    AdminComponent,
    MatchResultComponent,
  ]
})
export class AdminModule { }
