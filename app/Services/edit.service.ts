import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Models/user';
import { Response } from '../Models/response';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EditService {
  getDetails(username: string): Observable<User> {
    let url="http://localhost:8080/update/details?username="+username;
    return this.h.get<User>(url);
  }

  constructor(private h:HttpClient) { }

  profileEdit(user: User): Observable<Response> {
    
    let url="http://localhost:8080/update/";
    return this.h.post<Response>(url,user);
  }

  
}
