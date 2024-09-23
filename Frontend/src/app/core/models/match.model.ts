export interface Match {
    id: number;
    firstTeamId: number;
    firstTeamName: string;
    secondTeamId: number;
    secondTeamName: string;
    playDateTime: Date;
    result: string;
    type: string;
}

export interface Goals{
    firstTeamScore: Number;
    secondTeamScore: Number;
}

export interface Bet {
    matchId: Number;
    firstTeamScore: Number;
    secondTeamScore: Number;
}