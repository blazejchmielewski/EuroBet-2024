import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UsersComponent } from '../users.component';

export interface DialogData {
  name: string;
}

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<ConfirmDialogComponent>,
    private _snackBar: MatSnackBar,
    private userComponent: UsersComponent,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  onNoClick(): void {
    
    this.dialogRef.close();
  }

  openSnackBar(message: string, action: string) {
    if(this.userComponent.userToDelete != null){
      this.userComponent.deactivateUser(this.userComponent.userToDelete);
      this._snackBar.open(message, action);
    }
  }
}
