import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  username:string;
  isLoggedIn:boolean;
  
  //inject services required into constructor
  constructor(private ls:LoginService, private apc:AppComponent) { }

  //load the log in data on load
  ngOnInit(): void {

    this.ls.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
      this.ls.username.subscribe((data: string) => this.username = data);
      this.isLoggedIn = this.ls.isLoggedIn();
  }

  //do log out of your account
  logout() {
    this.apc.logout();
  }
}
