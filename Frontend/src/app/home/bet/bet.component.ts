import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { AddBetData, AddGoalsKing } from 'src/app/core/models/auth.model';
import { AddBetForm, AddGoalsKingForm } from 'src/app/core/models/forms.model';
import { Match } from 'src/app/core/models/match.model';
import { BetService } from 'src/app/core/services/bet.service';
import { FormService } from 'src/app/core/services/form.service';
import { MatchesService } from 'src/app/core/services/matches.service';

@Component({
  selector: 'app-bet',
  templateUrl: './bet.component.html',
  styleUrls: ['./bet.component.css']
})
export class BetComponent {
  matches: Match[] | null = null;
  goalsKing: AddGoalsKing | null = null;
  private subscription: Subscription = new Subscription();
  email: string | null = null;

  player: string | null = null;
  playerGoals: number | null = null;

  constructor(private matchService: MatchesService, 
    private formService: FormService, 
    private _snackBar: MatSnackBar,
    private betService: BetService) {}

  addBetForm: FormGroup<AddBetForm> = this.formService.initBet();
  addGoalsKing: FormGroup<AddGoalsKingForm> = this.formService.initGoalsKing();

  ngOnInit(): void {
    this.getAllMatchesToAddBet();
    this.getUserKingGoal();
    if (localStorage.getItem('showSnackbar')) {
      this.openSnackBar('Bramki zostały zapisane', 'Zamknij');
      localStorage.removeItem('showSnackbar');
    }
  }

  getUserKingGoal(){
    this.email = localStorage.getItem('userEmail');
    if(this.email !== null){
      this.betService.getUserKingGoal(this.email).subscribe({
        next: (resp)=>{
          this.player = resp.name;
          this.playerGoals = resp.goals;
        }
      })
    }

  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getAllMatchesToAddBet(): void {
    this.email = localStorage.getItem('userEmail');
    if(this.email !== null){
      this.subscription.add(
        this.betService.getAllMatchesToBet(this.email).subscribe({
          next: (resp) => {
            this.betService.updatePoints();
            this.matches = resp;
            console.log(resp);
          },
          error: (err) => console.log(err)
        })
      );
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 4000,
    });
  }

  saveMatchGoals(id: Number): void {
    this.email = localStorage.getItem('userEmail');
    if (this.addBetForm.valid && this.email !== null) {
      const bet: AddBetData = {
        matchId: id || 0,
        firstTeamScore: this.addBetForm.value.firstTeamGoals || 0,
        secondTeamScore: this.addBetForm.value.secondTeamGoals || 0
      }
        this.subscription.add(
          this.betService.addBet(this.email, bet).subscribe({
            next: () => {
              this.betService.updatePoints();
              localStorage.setItem('showSnackbar', 'true');
              window.location.reload();
            },
            error: (err) => {
              console.log(err);
              console.log(bet);
            }
          })
        );
      }
    }

    onSaveGoalsKing(){
      this.email = localStorage.getItem('userEmail');
      if(this.addGoalsKing.valid && this.email !== null){
        const kingGoals: AddGoalsKing = {
          name: this.addGoalsKing.value.name || '',
          goals: this.addGoalsKing.value.goals || 0
        }
        this.subscription.add(
          this.betService.addGoalsKing(this.email, kingGoals).subscribe({
          next:() =>{
            localStorage.setItem('showSnackbar', 'true');
            window.location.reload();
          },
          error: (err) => {
            console.log(err);
        }
      }
      ))
    }
    }


    getFlagUrl(countryName: string): string {
      switch (countryName) {
        case 'Niemcy': return 'https://flagcdn.com/w320/de.png';
        case 'Hiszpania': return 'https://flagcdn.com/w320/es.png';
        case 'Szkocja': return 'https://flagcdn.com/w320/gb-sct.png';
        case 'Francja': return 'https://flagcdn.com/w320/fr.png';
        case 'Holandia': return 'https://flagcdn.com/w320/nl.png';
        case 'Anglia': return 'https://flagcdn.com/w320/gb-eng.png';
        case 'Włochy': return 'https://flagcdn.com/w320/it.png';
        case 'Turcja': return 'https://flagcdn.com/w320/tr.png';
        case 'Chorwacja': return 'https://flagcdn.com/w320/hr.png';
        case 'Albania': return 'https://flagcdn.com/w320/al.png';
        case 'Czechy': return 'https://flagcdn.com/w320/cz.png';
        case 'Belgia': return 'https://flagcdn.com/w320/be.png';
        case 'Austria': return 'https://flagcdn.com/w320/at.png';
        case 'Węgry': return 'https://flagcdn.com/w320/hu.png';
        case 'Serbia': return 'https://flagcdn.com/w320/rs.png';
        case 'Dania': return 'https://flagcdn.com/w320/dk.png';
        case 'Słowenia': return 'https://flagcdn.com/w320/si.png';
        case 'Rumunia': return 'https://flagcdn.com/w320/ro.png';
        case 'Szwajcaria': return 'https://flagcdn.com/w320/ch.png';
        case 'Portugalia': return 'https://flagcdn.com/w320/pt.png';
        case 'Słowacja': return 'https://flagcdn.com/w320/sk.png';
        case 'Polska': return 'https://flagcdn.com/w320/pl.png';
        case 'Ukraina': return 'https://flagcdn.com/w320/ua.png';
        case 'Gruzja': return 'https://flagcdn.com/w320/ge.png';
        default: return '';
      }
    }
}