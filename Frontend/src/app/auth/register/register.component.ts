import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthResponse, RegisterData } from 'src/app/core/models/auth.model';
import { RegisterForm } from 'src/app/core/models/forms.model';
import { AuthService } from 'src/app/core/services/auth.service';
import { FormService } from 'src/app/core/services/form.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  departments: string[] = [];
  registerForm: FormGroup<RegisterForm> = this.formService.initRegisterForm();
  notMatchingPasswordsErr: null | string = null;

  constructor(private formService: FormService, private authService: AuthService, private router: Router){}


  ngOnInit(): void {
    this.getDepartments();
  }

  getErrorMessage(control: FormControl): string{
    return this.formService.getErorrMessage(control);
  }

  get controls(): RegisterForm {
    return this.registerForm.controls;
  }

  getDepartments() {
    this.authService.departments().subscribe((response: string[]) => {
      console.log(response);
      this.departments = response; // przypisanie otrzymanych danych do zmiennej departments
    }, (error) => {
      console.log(error);
    });
  }

  onRegister(): void{
    
    console.log(!this.registerForm.invalid)

    if(!this.registerForm.invalid){
      const formData: RegisterData = {
        firstname: this.registerForm.value.firstname ?? '',
        lastname: this.registerForm.value.lastname ?? '',
        email: this.registerForm.value.email ?? '',
        department: this.registerForm.value.department ?? ''
      }

      console.log(formData)
      this.authService.register(formData).subscribe({
        next: (response: AuthResponse) => {
          localStorage.setItem('accessToken', response.accessToken);
          this.router.navigate(['admin'])
        }, error: (err => console.log(err))
      })
    }
  }
}
