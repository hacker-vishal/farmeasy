import { Injectable } from '@angular/core';
import { Acc } from './acc';
import { Ajaxoutput } from './ajaxoutput';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppServiceService 
{
  constructor(private y:HttpClient) { }

  whyyouWantobyheartSyntax(x:Acc):Observable<Ajaxoutput>
  {
    let url:string ="http://localhost:90/beforepostman";
    return this.y.post<Ajaxoutput>(url,x);
  }

  nearlyserver(x:Acc):Ajaxoutput
  {
    //one day from server we will get Acc[] array only
    let y:Acc[]=[];
    y.push(new Acc(3,4,55));
    y.push(new Acc(6,7,85));
    let z:Ajaxoutput = new Ajaxoutput();
    z.result=y;
    return z;  //dummy array created in serivce and returning..
  }

  testser(x:number):string
  {
    return "poc is important here also " + x;

  }
}
