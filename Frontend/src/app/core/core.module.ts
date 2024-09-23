import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from '../shared/shared.module';
import { AuthService } from './services/auth.service';
import { MatchesComponent } from './matches/matches.component';

@NgModule({
  declarations: [
    
  
    MatchesComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    SharedModule
  ],
  providers: [AuthService] 
})
export class CoreModule { }
