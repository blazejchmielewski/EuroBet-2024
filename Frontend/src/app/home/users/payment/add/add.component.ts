import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserService } from 'src/app/core/services/user.service';
import { EditUserDialogComponent } from '../../edit-user-dialog/edit-user-dialog.component';
import { User } from 'src/app/core/models/user.model';
import { FormService } from 'src/app/core/services/form.service';
import { AddPaymentForm, EditUserForm } from 'src/app/core/models/forms.model';
import { FormControl, FormGroup } from '@angular/forms';
import { PaymentService } from 'src/app/core/services/payment.service';
import { AddPayment } from 'src/app/core/models/auth.model';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  paymentForm: FormGroup<AddPaymentForm> = this.formService.initAddPaymentForm();

  payment: number | null = null
  currentUser: User | null = null;
  saldo: number = 0;
  constructor(
    private formService: FormService, 
    private userService: UserService,
    private paymentService: PaymentService,
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<EditUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public user: User
  ) {}

  ngOnInit(): void {
    this.getAccountStatus()

  }
  
  getErrorMessage(control: FormControl): string {
    return this.formService.getErorrMessage(control);
  }

  get controls(): AddPaymentForm {
    return this.paymentForm.controls;
  }

  getAccountStatus(){
    const currentUser = this.userService.getCurrentUser();
    this.currentUser = currentUser
    if(this.currentUser !== null){
      this.paymentService.getAccountStatus(this.currentUser.id).subscribe({
        next: (resp) => {
          this.saldo = resp.status;
          console.log(resp)
        }
      })
    }
  }

  onPayment(){
    const currentUser = this.userService.getCurrentUser();
    if(this.paymentForm.valid && currentUser !== null){
      const paymentData: AddPayment = {
        userId: currentUser.id,
        amount: this.paymentForm.value.amount ?? 0
      };      
      this.paymentService.addPayment(paymentData).subscribe({
        next: (resp) => {
          console.log(resp)
          this.dialogRef.close(true);
          window.location.reload();
        },
        error: (error) => {
          console.error(error);
          // Tutaj możesz obsłużyć błędy w razie potrzeby
        }
      });
    }  
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
}
