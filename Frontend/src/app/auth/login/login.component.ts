import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthResponse } from 'src/app/core/models/auth.model';
import { LoginForm } from 'src/app/core/models/forms.model';
import { AuthService } from 'src/app/core/services/auth.service';
import { BetService } from 'src/app/core/services/bet.service';
import { FormService } from 'src/app/core/services/form.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup<LoginForm> = this.formService.initLoginForm();
  loginError: string | null = null
  email: string | null = null

  constructor(private formService: FormService, private authService: AuthService, private router: Router, private betService: BetService){}

  getErrorMessage(control: FormControl): string {
    return this.formService.getErorrMessage(control);
  }

  get controls(): LoginForm {
    return this.loginForm.controls;
  }

  onLogin():void {
    if(this.loginForm.valid){
      const formData = {
        email: this.loginForm.value.email ?? '',
        password: this.loginForm.value.password ?? ''
      }
      console.log(formData)
      this.authService.login(formData).subscribe({
        next: (response: AuthResponse) => {
          console.log(response);
          localStorage.setItem('userEmail', formData.email);
          localStorage.setItem('token', response.accessToken);
          this.router.navigate(['/home']);
        }, error: (error) =>{
          if(error.status === 403){
            this.loginError = "Nieprawidłowe dane logowania"
          } else this.loginError = "Nie znaleziono takiego użytkownika"
        }
      })
    }

  }
}
