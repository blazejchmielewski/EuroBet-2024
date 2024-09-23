import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { AuthResponse, LoginData, RegisterData } from '../models/auth.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = `${environment.apiUrl}/auth`

  constructor(private http: HttpClient) {}

  register(body: RegisterData): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, body)
  }

  login(body: LoginData): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, body)
  }

  departments(): Observable<string[]>{
    return this.http.get<string[]>(`${this.apiUrl}/departments`)
  }

  
  
}
