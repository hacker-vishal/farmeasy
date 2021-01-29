import { Component, OnInit } from '@angular/core';
import { EditService } from '../Services/edit.service';
import { User } from '../Models/user';
import { Response } from '../Models/response';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';
import { AppComponent } from '../app.component';
import { ToastrService } from 'ngx-toastr';

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
  // isUpdated:boolean;
  // isFailed:boolean;
  // hideSuccessMessage:boolean=false;

  constructor(private e:EditService, private r: Router, private ls:LoginService, private apc:AppComponent,
    private t:ToastrService) 
  { 
    this.user = {email: '', password: '', fname:'', lname:'', otp:null, mobileno:null};
    
  }

  ngOnInit(): void {
    

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
    // this.isUpdated=false;
    // this.isFailed=false;

    this.e.getDetails(this.username).subscribe(
      (u:User)=>{
        if(u.email!==null && u.email===this.username)
        {
          //console.log(JSON.stringify(u));
          this.user = u;
        }
      },
      (err)=>{console.log(JSON.stringify(err));
        //this.msg="you got some error";
        this.t.error("You got some error!!!");
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
        // this.isUpdated=true;
        // this.isFailed=false;
        //this.msg="updated successfully";
        //this.hideSuccessMessage = false;
        this.t.success("Profile updated successfully!");
      }
    },
    (err)=>{console.log(JSON.stringify(err));
      this.t.error("You got some error!!!");
      //this.msg="you got some error";
      // this.isFailed=true;
      // this.isUpdated=false;
      //this.msg="some error occured";
      //this.hideSuccessMessage = false;
    });
}

logout() {
  this.apc.logout();
}

  // FadeOutSuccessMsg() {
  //   setTimeout( () => {
  //       this.hideSuccessMessage = true;
  //    }, 2000);
  //   }

}
