import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Response } from '../Models/response';
import { Userdto } from '../Models/userdto';

@Injectable({
  providedIn: 'root'
})
export class PassresetService {

  constructor(private h:HttpClient) { }

  setNewPassword(u:Userdto): Observable<Response> {
    console.log(JSON.stringify(u));
    let url = "http://localhost:8080/password/setnewpass";
    return this.h.post<Response>(url,u);
  }

  checkOtp(otp: any): Observable<Response> {
    let url = "http://localhost:8080/password/checkotp?otp="+otp;
    return this.h.get<Response>(url);
  }

  getOtp(username:string): Observable<any> {
    let url = "http://localhost:8080/password/getotp?id="+username;
    return this.h.get<any>(url);
  }

  isEmailExists(username: string): Observable<Response> {
    let url = "http://localhost:8080/password/pswdreset?id="+username;
    return this.h.get<Response>(url);
  }
}
