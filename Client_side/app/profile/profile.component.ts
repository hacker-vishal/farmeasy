import { Component, OnInit } from '@angular/core';
import { EditService } from '../Services/edit.service';
import { User } from '../Models/user';
import { Response } from '../Models/response';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  msg:String;
  user:User;
  username:string;

  constructor(private e:EditService, private r: Router, private ls:LoginService) 
  { 
    this.user = {email: '', password: '', fname:'', lname:'', otp:null, mobileno:null};
  }

  ngOnInit(): void {
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
        //console.log(rsp.status);
      }
    },
    (err)=>{console.log(JSON.stringify(err));
      this.msg="you got some error";

    });
}

}
