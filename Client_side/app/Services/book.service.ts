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
    return this.h.post<Response>("http://localhost:8080/booking/checkavail", b);
  }

  getbookings(username: string): Observable<any> {
    return this.h.get<any>("http://localhost:8080/booking/get?username="+username);
  }

  bookit(b: Booking): Observable<Response> {
    return this.h.post<Response>("http://localhost:8080/booking/bookit", b);
  }

  cancelmybooking(bid: any): Observable<Response> {
    return this.h.get<Response>("http://localhost:8080/booking/cancelbooking?bid="+bid);
  }
}
