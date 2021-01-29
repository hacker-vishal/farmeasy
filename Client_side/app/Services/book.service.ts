import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private h:HttpClient) { }

  checkavailability(b: any): Observable<Response>{
    let url="http://localhost:8080/booking/checkavail";
    return this.h.post<Response>(url, b);
  }
}
