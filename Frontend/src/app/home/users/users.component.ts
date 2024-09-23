import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { User } from 'src/app/core/models/user.model';
import { UserService } from 'src/app/core/services/user.service';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { EditUserDialogComponent } from './edit-user-dialog/edit-user-dialog.component';
import { AddComponent } from './payment/add/add.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements AfterViewInit, OnInit {
  currentUser!: User; 
  displayedColumns: string[] = ['lp', 'firstname', 'lastname', 'email', 'department', 'buttons'];
  users: User[] = []; // Tablica przechowująca kopię danych
  dataSource!: MatTableDataSource<User>;
  userToDelete: User | null = null;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private userService: UserService, public dialog: MatDialog, private changeDetectorRefs: ChangeDetectorRef, private router: Router) {}
  
  ngOnInit(): void {
    this.refresh();
  }

  ngAfterViewInit(): void {
    this.refreshData();
  }

  refreshData(): void {
    this.userService.getAllUsers().subscribe({
      next: (users)=> {
        this.users = users.slice(); // Kopia danych z backendu
        this.dataSource = new MatTableDataSource<User>(this.users);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.changeDetectorRefs.detectChanges(); // Odświeżanie widoku
      }, error: (err) => {
        console.log(err);
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  confirmDeactivateUser(user: User): void {
    this.userToDelete = user;
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: { user: user, name: `${user.firstname} ${user.lastname}` }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Możesz dodać dodatkową logikę tutaj po zamknięciu dialogu
      }
    });
  }

  confirmPayment(user: User): void {
    this.userService.setCurrentUser(user);
    const dialogRef = this.dialog.open(AddComponent, {
      width: '700px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'success') {
        this.refresh();
      }
    });
  }
  
  deactivateUser(user: User): void {
    this.userService.expireUser(user.id).subscribe({
      next: (response) => {
        console.log(response);
        
        this.users = this.users.filter(u => u.id !== user.id);
        this.dataSource.data = this.users;
      },
      error: (err) => {
        console.error('Wystąpił błąd podczas wygaszania użytkownika:', err);
      }
    });
  }

  openEditUserDialog(user: User) {
    this.userService.setCurrentUser(user);
    const dialogRef = this.dialog.open(EditUserDialogComponent, {
      width: '700px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'success') {
        this.refresh();
      }
    });
  } 

  refresh() {
    this.userService.getAllUsers().subscribe((res) => {
      this.changeDetectorRefs.detectChanges();
    });
  }

  paymentHistory(){
    this.router.navigate(['payment-history'])
  }
}
