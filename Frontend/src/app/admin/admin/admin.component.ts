import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  constructor(private router: Router){}

  navigateToAddMatchResult(){
    this.router.navigateByUrl('/match-result');
  }

  navigateToAddParticipent(){
    this.router.navigateByUrl('/register');
  }

  navigateToEditUsers(){
    this.router.navigateByUrl('/users');
  }

  navigateToPayment(){
    this.router.navigateByUrl('/payment');
  }
}
