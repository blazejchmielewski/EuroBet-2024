import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input'
import { MatRadioModule } from '@angular/material/radio';
import { MatTableModule} from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CustomPaginatorIntl } from 'src/app/core/config/CustomPaginatorIntl';

@NgModule({
  declarations: [],
  providers: [
    { provide: MatPaginatorIntl, useClass: CustomPaginatorIntl }
  ],
  exports: [
    CommonModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatRadioModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatDialogModule,
    MatSnackBarModule
  ]
})
export class MaterialModule { }
