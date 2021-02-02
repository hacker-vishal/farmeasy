import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hostuser } from '../Models/hostuser';
import { HomeService } from '../Services/home.service';
import { LoginService } from '../Services/login.service';
import { SessionStorageService } from 'angular-web-storage';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  h:Hostuser;
  showlist:any=[];
  msg:any=[];
  isLoggedIn:boolean=false;

  //inject services required into constructor
  constructor(private hs:HomeService, private r:Router, private loginService:LoginService,
    private session:SessionStorageService, private t:ToastrService) 
  { 
    this.h = new Hostuser("","");
    
  }

  //checks if user is logged in or not
  ngOnInit(): void {
    this.isLoggedIn = this.loginService.isLoggedIn();
  }

  //searches for the services according to the equipmenttype and location passed by the user
  getnow()
  {
    this.hs.getServiceOnEqAndLoc(this.h).subscribe(
      (list)=>{
        
        //console.log(this.h.equipmenttype);
        //console.log(list.length,this.h.equipmenttype,this.h.location,JSON.stringify(this.h));
        if(list.length>0){
          this.t.show("Here is the list of your required services!");
          //console.log(list.length,this.h.equipmenttype,this.h.location);
        list.forEach(element => {
          if(element.equipmenttype!=null){
        //this.msg=JSON.stringify(list);
        this.showlist.push(element);

        //this.msg=this.showlist;
      }});
      //localStorage.setItem('data', JSON.stringify(this.showlist));
      this.session.set('list',this.showlist);
      this.r.navigate(['/showlist']);
      //this.r.navigate(['/showlist'],{ state: { list: this.showlist } });
        //sendlist = JSON.stringify(this.showlist); 
      }
      else{
        this.t.info("No service found matching to your request!");
      }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
      });
  }

  //navigate to host component
  navigatetohost()
  {
    if(this.isLoggedIn)
    {
      this.r.navigate(['/host']);
    }
  }

}
