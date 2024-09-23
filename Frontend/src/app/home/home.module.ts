import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';
import { UsersComponent } from './users/users.component';
import { MatTableModule } from '@angular/material/table';
import { SharedModule } from '../shared/shared.module';
import { ConfirmDialogComponent } from './users/confirm-dialog/confirm-dialog.component';
import { EditUserDialogComponent } from './users/edit-user-dialog/edit-user-dialog.component';
import { MatchesComponent } from './matches/matches.component';
import { RouterModule } from '@angular/router';
import { MatchResultComponent } from '../admin/match-result/match-result.component';
import { AdminModule } from '../admin/admin.module';
import { BetComponent } from './bet/bet.component';
import { RankingComponent } from './ranking/ranking.component';
import { PaymentComponent } from './users/payment/payment.component';
import { AddComponent } from './users/payment/add/add.component';
import { PaymentHistoryComponent } from './users/payment/payment-history/payment-history.component';



@NgModule({
  declarations: [
    HomeComponent,
    NavComponent,
    FooterComponent,
    UsersComponent,
    ConfirmDialogComponent,
    EditUserDialogComponent,
    MatchesComponent,
    BetComponent,
    RankingComponent,
    PaymentComponent,
    AddComponent,
    PaymentHistoryComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    AdminModule
  ], exports: [
    HomeComponent,
    NavComponent,
    FooterComponent,
    BetComponent,
    RankingComponent,
    PaymentComponent,
    PaymentHistoryComponent
  ]
})
export class HomeModule { }
