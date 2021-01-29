import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hostuser } from '../Models/hostuser';
import { Response } from '../Models/response';

@Injectable({
  providedIn: 'root'
})
export class HostService {

  constructor(private h:HttpClient) { }

  gethostdetails(hu: Hostuser): Observable<Response> {
    console.log(hu);
    let url ="http://localhost:8080/services/insert";
    return this.h.post<Response>(url,hu);
    
  }

}
