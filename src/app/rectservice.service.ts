import { Injectable } from '@angular/core';
import { Rectangle } from './rectangle';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { AR } from './ar';

@Injectable({
  providedIn: 'root'
})
export class RectserviceService {

  constructor(private x:HttpClient) { }


    callws(e:Rectangle):Observable<AR>
    {
      let url: "http://localhost:7073/arpr";
      return this.x.post<AR>(url,e);
    }


}
