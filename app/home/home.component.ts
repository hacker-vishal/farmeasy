import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hostuser } from '../Models/hostuser';
import { HomeService } from '../Services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  h:Hostuser;
  showlist:any=[];
  msg:any=[];
  

  constructor(private hs:HomeService,private r:Router) 
  { 
    this.h = new Hostuser("","");
    
  }
  ngOnInit(): void {
  }

  getnow()
  {
    this.hs.getServiceOnEqAndLoc(this.h).subscribe(
      (list)=>{
        
        //console.log(this.h.equipmenttype);
        this.msg="No service found matching to your request!";
        //console.log(list.length,this.h.equipmenttype,this.h.location,JSON.stringify(this.h));
        if(list.length>0){
          //console.log(list.length,this.h.equipmenttype,this.h.location);
        list.forEach(element => {
          if(element.equipmenttype!=null){
        //this.msg="Here is the list of your required services!"
        //this.msg=JSON.stringify(list);
        this.msg="";
        this.showlist.push(element);

        //this.msg=this.showlist;
      }});
      //localStorage.setItem('data', JSON.stringify(this.showlist));
      this.r.navigate(['/showlist'],{ state: { list: this.showlist } });
        //sendlist = JSON.stringify(this.showlist);
        
      }
      },
      (err)=>{console.log(JSON.stringify(err));
        this.msg="you got some error"

      });
  }

 

}
