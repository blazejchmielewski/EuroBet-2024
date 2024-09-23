import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit{
  
  email: string | null = null;

  ngOnInit(): void {
    this.email = localStorage.getItem('userEmail');
  }
  
}
