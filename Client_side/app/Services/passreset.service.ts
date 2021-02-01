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
    // console.log(JSON.stringify(u));
    return this.h.post<Response>("http://localhost:8080/password/setnewpass",u);
  }

  checkOtp(otp: any): Observable<Response> {
    return this.h.get<Response>("http://localhost:8080/password/checkotp?otp="+otp);
  }

  getOtp(username:string): Observable<any> {
    return this.h.get<any>("http://localhost:8080/password/getotp?id="+username);
  }

  isEmailExists(username: string): Observable<Response> {
    return this.h.get<Response>("http://localhost:8080/password/pswdreset?id="+username);
  }
}
