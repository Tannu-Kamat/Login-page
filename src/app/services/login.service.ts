import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credentials } from '../model/credentials';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor( private http:HttpClient) { }

  login(credentials : Credentials) {
    this.http.post( 'https://b5e7-2401-4900-5b91-cf9d-d7a1-141a-e8c8-95c8.ngrok-free.app/login' ,credentials,{})
    .subscribe({
      next:(Response)=>{
        console.log(Response);
      },
      error:(errors)=>{
        console.log(errors);
      }
    })
    
    console.log(credentials);}
}
