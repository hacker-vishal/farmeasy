import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hostuser } from '../Models/hostuser';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private h:HttpClient) {  }

  getServiceOnEqAndLoc(e:Hostuser):Observable<any>
  {
    let url="http://localhost:8080/services/searchserv";  
    return this.h.post<any>(url,e);
  }

}
