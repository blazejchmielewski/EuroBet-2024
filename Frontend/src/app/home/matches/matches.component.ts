import { Component, OnInit, OnDestroy } from '@angular/core';
import { Match } from 'src/app/core/models/match.model';
import { MatchesService } from 'src/app/core/services/matches.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit, OnDestroy {
  matches: Match[] | null = null;
  private subscription: Subscription = new Subscription();
  email: string | null = null;
  constructor(private matchService: MatchesService) {}

  ngOnInit(): void {
    this.getAllMatches();
  }

  getAllMatches(): void {
    this.email = localStorage.getItem("userEmail")
    console.log(this.email)
    if(this.email !== null){
      this.subscription.add(
        this.matchService.getAllMatches(this.email).subscribe({
          next: (resp) => {
            console.log(resp)
            this.matches = resp;
          },
          error: (err) => console.log(err)
        })
    );
    }
}
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
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
