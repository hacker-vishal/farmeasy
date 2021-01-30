import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from '../Models/booking';
import { Response } from '../Models/response';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private h:HttpClient) { }

  checkavailability(b:Booking): Observable<Response>{
    let url="http://localhost:8080/booking/checkavail";
    return this.h.post<Response>(url, b);
  }
}
