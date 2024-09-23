import { Injectable } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { AddBetForm, AddGoalsKingForm, AddPaymentForm, ChangeMatchResult, EditUserForm, LoginForm, RegisterForm } from '../models/forms.model';
import { Match } from '../models/match.model';

@Injectable({
  providedIn: 'root',
})
export class FormService{
    
    initLoginForm(): FormGroup<LoginForm>{
        return new FormGroup({
            email: new FormControl('', { validators: [
                Validators.required, 
                Validators.email], nonNullable: true
                }
            ),
            password: new FormControl('', { validators: [
                Validators.required, 
                Validators.minLength(5), 
                Validators.maxLength(50)], nonNullable: true
                }
            )
        })
    }

    initRegisterForm(): FormGroup<RegisterForm> {
        return new FormGroup({
            firstname: new FormControl('', { validators: [
                    Validators.required, 
                    Validators.minLength(3), 
                    Validators.maxLength(20)], nonNullable: true
                }
            ),
            lastname: new FormControl('', { validators: [
                Validators.required, 
                Validators.minLength(3), 
                Validators.maxLength(20)], nonNullable: true
                }
            ),
            email: new FormControl('', { validators: [
                Validators.required, 
                Validators.email], nonNullable: true
                }
            ),
            department: new FormControl('', { validators: [
                Validators.required], nonNullable: true
                }
            ),
        });
    }

    initEditUserForm(): FormGroup<EditUserForm>{
        return new FormGroup({
            firstname: new FormControl('', {validators: [
                Validators.required, 
                Validators.minLength(3), 
                Validators.maxLength(20)], nonNullable: true
            }),
            lastname: new FormControl('', {validators: [
                Validators.required, 
                Validators.minLength(3), 
                Validators.maxLength(20)], nonNullable: true
            }),
            email: new FormControl('', {validators: [
                Validators.required, 
                Validators.email], nonNullable: true
            }),
            department: new FormControl('', {validators: [
                Validators.required], nonNullable: true
            }),
        })
    }

    initAddPaymentForm(): FormGroup<AddPaymentForm>{
        return new FormGroup({
            amount: new FormControl(0, {validators: [
                Validators.required], nonNullable: true
            }),
        })
    }

    initChangeMatchResult(): FormGroup<ChangeMatchResult> {
        return new FormGroup<ChangeMatchResult>({
            firstTeamGoals: new FormControl(0, {
                validators: [Validators.required],
                nonNullable: true
            }),
            secondTeamGoals: new FormControl(0, {
                validators: [Validators.required],
                nonNullable: true
            })
        });
    }

    initBet(): FormGroup<AddBetForm> {
        return new FormGroup<AddBetForm>({
            matchId: new FormControl(0, {
                validators: [Validators.required],
                nonNullable: true
            }),
            firstTeamGoals: new FormControl(0, {
                validators: [Validators.required],
                nonNullable: true
            }),
            secondTeamGoals: new FormControl(0, {
                validators: [Validators.required],
                nonNullable: true
            })
        });
    }

    initGoalsKing():FormGroup<AddGoalsKingForm> {
        return new FormGroup({
            name: new FormControl('', {
                validators: [Validators.required],
                nonNullable: true
            }),
            goals: new FormControl(0, {
                validators: [Validators.required], 
                nonNullable: true
            })
        })
    }

    getErorrMessage(control: FormControl): string{
        if (control.hasError('required')) {
            return 'To pole jest wymagane';
        }

        if (control.hasError('email')) {
            return 'Nieprawidłowy adres e-mail';
        }

        if (control.hasError('minlength')) {
            return `Minimalna długość to ${control.errors?.['minlength'].requiredLength}`;
        }

        if (control.hasError('maxlength')) {
            return `Maksymalna długość to ${control.errors?.['maxlength'].requiredLength}`;
        }

        return '';
    }

    
}