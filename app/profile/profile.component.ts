import { Component, OnInit } from '@angular/core';
import { EditService } from '../Services/edit.service';
import { User } from '../Models/user';
import { Response } from '../Models/response';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';
import { GlobalConstants } from '../common/global-constants';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  msg:String;
  user:User;
  username:string;
  isLoggedIn:boolean;
  isUpdated:boolean;

  constructor(private e:EditService, private r: Router, private ls:LoginService, private apc:AppComponent) 
  { 
    this.user = {email: '', password: '', fname:'', lname:'', otp:null, mobileno:null};
    
  }

  ngOnInit(): void {
    //this.isUpdated=null;

    this.ls.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
      this.ls.username.subscribe((data: string) => this.username = data);
      this.isLoggedIn = this.ls.isLoggedIn();


    this.username = this.ls.getUserName();
    //console.log(this.username);

    if(this.username!==null && this.username!==undefined)
    this.getuserdetails();
  }

  getuserdetails()
  {
    this.e.getDetails(this.username).subscribe(
      (u:User)=>{
        if(u.email!==null && u.email===this.username)
        {
          //console.log(JSON.stringify(u));
          this.user = u;
        }
      },
      (err)=>{console.log(JSON.stringify(err));
        this.msg="you got some error";
      });
  }
  
updateProfile()
{
  this.e.profileEdit(this.user).subscribe(
    (rsp:Response)=>{
      if(rsp.status===1)
      {
        //this.msg=rsp.message;
        console.log(rsp.status);
        this.isUpdated=true;

        this.msg="updated successfully";
        
      }
    },
    (err)=>{console.log(JSON.stringify(err));
      //this.msg="you got some error";
      this.isUpdated=false;
      this.msg="some error occured";
      console.log(this.msg+" "+this.isUpdated);
    });
}

logout() {
  this.apc.logout();
  // this.ls.logout();
  // this.isLoggedIn = false;
  // this.r.navigateByUrl('');
}

}
