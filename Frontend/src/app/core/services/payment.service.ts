import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { AddPayment } from '../models/auth.model';
import { PaymentHistory, userAcountStatus } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  apiUrl = `${environment.apiUrl}/payment`

  constructor(private http: HttpClient) {}

  addPayment(data: AddPayment): Observable<string>{
    return this.http.post<string>(`${this.apiUrl}/add`, data)
  }

  getAccountStatus(id: number): Observable<userAcountStatus>{
    return this.http.get<userAcountStatus>(`${this.apiUrl}/status/${id}`)
  }

  getPaymentHistory():Observable<PaymentHistory[]>{
    return this.http.get<PaymentHistory[]>(`${this.apiUrl}/history`)
  }
}
