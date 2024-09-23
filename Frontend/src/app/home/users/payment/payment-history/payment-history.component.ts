import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { PaymentHistory } from 'src/app/core/models/user.model';
import { PaymentService } from 'src/app/core/services/payment.service';

@Component({
  selector: 'app-payment-history',
  templateUrl: './payment-history.component.html',
  styleUrls: ['./payment-history.component.css']
})
export class PaymentHistoryComponent implements OnInit, OnDestroy{

  paymentHistory: PaymentHistory[] = [];
  private subscription: Subscription = new Subscription();

  constructor(private paymentService: PaymentService,
    private router: Router
  ){}
  
  ngOnInit(): void {
    this.getHistory()
  }

  getHistory(){
    const sub = this.paymentService.getPaymentHistory().subscribe({
      next: (resp) => {
        this.paymentHistory = resp;
      },
      error: (err) => {
        console.error('Error fetching payment history', err);
      }
    })
    this.subscription.add(sub);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  back(){
    this.router.navigate(['/users'])
  }

}
