import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Models/user';
import { Response } from '../Models/response';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EditService {

  constructor(private h:HttpClient) { }

  getDetails(username: string): Observable<User> {
    return this.h.get<User>("http://localhost:8080/update/details?username="+username);
  }

  profileEdit(user: User): Observable<Response> {
    return this.h.post<Response>("http://localhost:8080/update/",user);
  }
}
