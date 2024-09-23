import { FormArray, FormControl, FormGroup } from '@angular/forms';

export interface LoginForm{
    email: FormControl<string>;
    password: FormControl<string>;
}

export interface RegisterForm{
    firstname: FormControl<string>;
    lastname: FormControl<string>;
    email: FormControl<string>
    department: FormControl<string>
}

export interface EditUserForm{
    firstname: FormControl<string>;
    lastname: FormControl<string>;
    email: FormControl<string>
    department: FormControl<string>
}

export interface AddPaymentForm{
    amount: FormControl<number>
}

export interface ChangeMatchResult{
    firstTeamGoals: FormControl<number>;
    secondTeamGoals: FormControl<number>;
}

export interface AddBetForm{
    matchId: FormControl<number>;
    firstTeamGoals: FormControl<number>;
    secondTeamGoals: FormControl<number>;
}

export interface AddGoalsKingForm{
    name: FormControl<string>;
    goals: FormControl<number>;
}

