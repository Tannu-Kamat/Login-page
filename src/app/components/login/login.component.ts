import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { __values } from 'tslib';
import { Credentials } from '../../model/credentials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  credentials: Credentials = new Credentials();
  constructor(private loginservice : LoginService) {}

  

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required,]),
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(10),]),
  });

  loginmethod() { 
    this.credentials = new Credentials();
    this.credentials.username = this.loginForm.get('username')?.value;
    this.credentials.password = this.loginForm.get('password')?.value;
    this.loginservice.login(this.credentials)
  }


  ngOnInit(): void { }


  get USR(): FormControl {
    return this.loginForm.get('username') as FormControl;
  }
  
  get PWD(): FormControl {
    return this.loginForm.get('password') as FormControl;
  }
  

  

  
}
