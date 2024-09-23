import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { UsersComponent } from './home/users/users.component';
import { MatchesComponent } from './home/matches/matches.component';
import { FooterComponent } from './home/footer/footer.component';
import { AdminComponent } from './admin/admin/admin.component';
import { MatchResultComponent } from './admin/match-result/match-result.component';
import { BetComponent } from './home/bet/bet.component';
import { RankingComponent } from './home/ranking/ranking.component';
import { PaymentComponent } from './home/users/payment/payment.component';
import { PaymentHistoryComponent } from './home/users/payment/payment-history/payment-history.component';

const routes: Routes = [
  {path:'', component: LoginComponent},
  {path:'home', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path:'register', component: RegisterComponent},
  {path:'users', component: UsersComponent},
  {path:'matches/:email', component: MatchesComponent},
  {path:'footer',component: FooterComponent},
  {path:'bet',component: BetComponent},
  {path:'admin',component: AdminComponent},
  {path:'match-result',component: MatchResultComponent},
  {path:'ranking',component: RankingComponent},
  {path:'payment',component: PaymentComponent},
  {path:'payment-history',component: PaymentHistoryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
