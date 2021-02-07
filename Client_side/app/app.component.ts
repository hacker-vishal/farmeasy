import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './Services/login.service';
import { GlobalConstants } from './common/global-constants';
import { ResponsiveService } from './Services/responsive.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = GlobalConstants.siteTitle;

  //inject services required into constructor
    constructor(private r:Router, private loginService: LoginService, private resp:ResponsiveService,
      private t:ToastrService) 
    {
      console.log(GlobalConstants.apiURL);
     }

    equipmenttype:string;
    location:string;
    msg:any;
    isLoggedIn: boolean;
    username: string;
    loginflag:boolean=false;
    url:any;
  
    //get data of user on load
    ngOnInit() {
      this.loginService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
      this.loginService.username.subscribe((data: string) => this.username = data);
      this.isLoggedIn = this.loginService.isLoggedIn();
      //  console.log(this.isLoggedIn);
      // console.log(this.username);
      this.username = this.loginService.getUserName();
      if(this.isLoggedIn)
        this.loginflag=true;

      if(this.loginflag)
      {
        if(this.username===undefined || this.username===null)
        {
          this.url='login';
          this.t.warning("Session has expired! Please log in again!!!");
          this.logout();
        }
      }
      //console.log(this.title);
      //below part is a part of poc to check screen size
      this.resp.getMobileStatus().subscribe( isMobile =>{
        if(isMobile){
          //console.log('Mobile device detected')
        }
        else{
          //console.log('Desktop detected')
        }
      });
      //this.onResize();    
    }
  
    //logic to resize the page
    onResize(){
      this.resp.checkWidth();
    }
    
    // navigate to login page
    login()
    {
      this.r.navigate['/login'];
    }

    // navigate to profile
    goToUserProfile() {
      this.r.navigateByUrl('/profile/' + this.username);
    }
  
    //log out of the session
    logout() {
      this.loginService.logout();
      this.isLoggedIn = false;
      this.loginflag = false;
      this.r.navigateByUrl(this.url);
      this.t.info("Logged out! Thank you!");
    }
}



