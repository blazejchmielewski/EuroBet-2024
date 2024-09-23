import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user.model';
import { AuthResponse, EditData } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = environment.apiUrl;
  private currentUser: User | null = null;
  private userUpdated = new Subject<User>();

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/user/all`);
  }

  expireUser(id: number): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/user/expire/${id}`, {});
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/user/${id}`);
  }

  edit(id: number, body: EditData): Observable<AuthResponse> {
    return this.http.put<AuthResponse>(`${this.apiUrl}/user/update/${id}`, body);
  }

  setCurrentUser(user: User): void {
    this.currentUser = user;
  }

  getCurrentUser(): User | null {
    return this.currentUser;
  }
}
