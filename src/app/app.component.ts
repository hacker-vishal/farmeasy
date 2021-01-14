import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Hostuser } from './Models/hostuser';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

    constructor(private r:Router) 
    { }

    equipmenttype:string;
    location:string;
    msg:any;
  
    ngOnInit() {
    }

    login()
    {
      
      this.r.navigate['/login'];
    }
  
    listnow()
    {
      if(this.equipmenttype!= null && this.location!=null )
      {
        if(this.equipmenttype==Hostuser.toString() && this.location==Hostuser.toString())
        {
          
        }
        else
        {
          this.msg = "No Such Result Found";
        }
      }
      else
      {
        this.msg = "To see equipments at a particular location you need to type location and equipment type";
      }
    }
  

}



