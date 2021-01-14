import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hostuser } from '../Models/hostuser';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{

  h:Hostuser;

  constructor(private hs:HomeService) 
  { 
    
    
  }

  listnow()
  {

    this.hs.getListEquipTypeLoc

  }

}
