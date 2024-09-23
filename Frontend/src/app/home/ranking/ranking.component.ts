import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RankingData } from 'src/app/core/models/user.model';
import { BetService } from 'src/app/core/services/bet.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit, OnDestroy{

  users: RankingData[] | null = null;
  private subscription: Subscription = new Subscription();

  constructor(private betService: BetService){}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  
  ngOnInit(): void {
    this.getRanking()
  }

  getRanking(){
    
    this.subscription.add(
      this.betService.getRanking().subscribe({
        next: (resp) =>{
          this.betService.updatePoints();
          this.users = resp
          console.log(resp)
        },
        error: (err) => console.log(err)
      })
    );
  }
}
