import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Goals, Match } from '../models/match.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { ChangeMatchResult } from '../models/forms.model';

@Injectable({
  providedIn: 'root'
})
export class MatchesService {

  apiUrl = environment.apiUrl
  constructor(private http: HttpClient) {}

  getAllMatches(email: string): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.apiUrl}/match/matches/${email}`);
  }
  
  getAllMatchesToAddResult(): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.apiUrl}/match/matches-to-add`);
  }

  saveMatchGoals(id: Number,goals: Goals): Observable<string>{
    return this.http.put<string>(`${this.apiUrl}/match/update/match/${id}`, goals);
  }

  
}
