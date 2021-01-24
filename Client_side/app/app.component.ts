import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Hostuser } from './Models/hostuser';
import { LoginService } from './Services/login.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

    constructor(private r:Router, private loginService: LoginService) 
    { }

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
    }
}



