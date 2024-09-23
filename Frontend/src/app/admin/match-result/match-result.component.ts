import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { ChangeMatchResult } from 'src/app/core/models/forms.model';
import { Goals, Match } from 'src/app/core/models/match.model';
import { BetService } from 'src/app/core/services/bet.service';
import { FormService } from 'src/app/core/services/form.service';
import { MatchesService } from 'src/app/core/services/matches.service';

@Component({
  selector: 'app-match-result',
  templateUrl: './match-result.component.html',
  styleUrls: ['./match-result.component.css']
})
export class MatchResultComponent implements OnInit, OnDestroy {
  matches: Match[] | null = null;
  private subscription: Subscription = new Subscription();
  

  constructor(private matchService: MatchesService, 
    private formService: FormService, 
    private _snackBar: MatSnackBar,
    private betService: BetService) {}

  changeMatchResultForm: FormGroup<ChangeMatchResult> = this.formService.initChangeMatchResult();

  ngOnInit(): void {
    this.getAllMatchesToAddResult();
    this.betService.updatePoints();

    if (localStorage.getItem('showSnackbar')) {
      this.openSnackBar('Bramki zostały zapisane', 'Zamknij');
      localStorage.removeItem('showSnackbar'); 
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getAllMatchesToAddResult(): void {
    this.subscription.add(
      this.matchService.getAllMatchesToAddResult().subscribe({
        next: (resp) => {
          this.matches = resp;
          console.log(resp);
        },
        error: (err) => console.log(err)
      })
    );
  }

  saveMatchGoals(id: Number): void {
    if (this.changeMatchResultForm.valid) {
      const goals: Goals = {
        firstTeamScore: this.changeMatchResultForm.value.firstTeamGoals || 0,
        secondTeamScore: this.changeMatchResultForm.value.secondTeamGoals || 0
      };
      
      this.subscription.add(
        this.matchService.saveMatchGoals(id, goals).subscribe({
          next: () => {
            localStorage.setItem('showSnackbar', 'true');
            window.location.reload();
          },
          error: (err) => {
            console.log(err);
            console.log(goals);
          }
        })
      );
      
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 4000,
    });
  }
}