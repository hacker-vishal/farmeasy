import { HttpClient } from '@angular/common/http';
// import { Response } from '../Models/response';
import { Userdto } from '../Models/userdto';
import { Injectable, Output, EventEmitter } from '@angular/core';
import { User } from '../Models/user';
import { Observable, throwError } from 'rxjs';
import { SessionStorageService } from 'angular-web-storage';
 import { LoginResponse } from '../Models/login-response';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();

  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName()
  }

 constructor(private httpClient: HttpClient,
   private session:SessionStorageService) {
  }

  //test logic
  getUserEmailPswd(u:Userdto):Observable<Response>
  {
    let url = "http://localhost:8080/login";
    return this.httpClient.post<Response>(url,u);
  }

  signup(user: User): Observable<any> {
    return this.httpClient.post('http://localhost:8080/auth/signup', user, { responseType: 'text' });
  }

  login(userdto: Userdto): Observable<boolean> {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/auth/login',
      userdto).pipe(map(data => {
        this.session.set('authenticationToken', data.authenticationToken);
        this.session.set('username', data.username);
        this.session.set('refreshToken', data.refreshToken);
        this.session.set('expiresAt', data.expiresAt);

        this.loggedIn.emit(true);
        this.username.emit(data.username);
         return true;
      }));
  }

  getJwtToken() {
    return this.session.get('authenticationToken');
  }

  refreshToken() {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/auth/refresh/token',
      this.refreshTokenPayload)
      .pipe(tap(response => {
        this.session.clear();
        //this.session.clear('authenticationToken');
       // this.session.clear('expiresAt');

        this.session.set('authenticationToken',
          response.authenticationToken);
        this.session.set('expiresAt', response.expiresAt);
      }));
  }

  logout() {
    this.httpClient.post('http://localhost:8080/auth/logout', this.refreshTokenPayload,
      { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
      }, error => {
        throwError(error);
      })
      this.session.clear();
    // this.localStorage.clear('authenticationToken');
    // this.localStorage.clear('username');
    // this.localStorage.clear('refreshToken');
    // this.localStorage.clear('expiresAt');
  }

  getUserName() {
    return this.session.get('username');
  }
  getRefreshToken() {
    return this.session.get('refreshToken');
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
