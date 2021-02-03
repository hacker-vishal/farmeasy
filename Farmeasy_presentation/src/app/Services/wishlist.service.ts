import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Response } from '../Models/response';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  constructor(private h: HttpClient) { }

  removeitem(w: any) {
    return this.h.post<Response>("http://localhost:8080/wishlist/remove", w);
  }

  getwishlist(username: string): Observable<any> {
    return this.h.get<any>("http://localhost:8080/wishlist/?email="+username);
  }

  addwishlist(wishlist:any): Observable<Response> {
    return this.h.post<Response>("http://localhost:8080/wishlist/add", wishlist);
  }


}
