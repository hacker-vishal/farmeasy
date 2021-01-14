import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hostuser } from './Models/hostuser';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private x:HttpClient) { }

  
  getListEquipTypeLoc()
  {

  }

}
