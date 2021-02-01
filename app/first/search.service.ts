import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Receive } from './receive';
import { Send } from './send';

@Injectable({
  providedIn: 'root'
})
export class SearchService 
{
  constructor(private htp:HttpClient) { }

  dosearch(s:Send):Observable<Receive>
  {
    let x:any ="http://localhost:90/search";
    return this.htp.post<Receive>(x,s);
  }
}
