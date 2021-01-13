import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PA } from './pa';
import { Rectangle } from './rectangle';

@Injectable({
  providedIn: 'root'
})
export class RectservService {

  constructor(private x:HttpClient) { }

  getanswer(e:Rectangle):Observable<PA>
  {

    let url="http://localhost:7074/arpr";
    return this.x.post<PA>(url,e);

  }

  poc():Observable<string>
  {
    let url="http://localhost:7074/";
    return this.x.get(url,{responseType: 'text'});
  }
}
