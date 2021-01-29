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
  
    ngOnInit() {
      this.loginService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
      this.loginService.username.subscribe((data: string) => this.username = data);
      this.isLoggedIn = this.loginService.isLoggedIn();
      //console.log(this.isLoggedIn);
      this.username = this.loginService.getUserName();
      //console.log(this.title);
      this.resp.getMobileStatus().subscribe( isMobile =>{
        if(isMobile){
          console.log('Mobile device detected')
        }
        else{
          console.log('Desktop detected')
        }
      });
      this.onResize();    
    }
  
    onResize(){
      this.resp.checkWidth();
    }
    

    login()
    {
      this.r.navigate['/login'];
    }

    goToUserProfile() {
      this.r.navigateByUrl('/profile/' + this.username);
    }
  
    logout() {
      this.loginService.logout();
      this.isLoggedIn = false;
      this.r.navigateByUrl('');
      this.t.info("Logged out! Thank you!")
    }
}



