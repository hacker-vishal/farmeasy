import { Component, OnInit } from '@angular/core';
import { EditService } from '../Services/edit.service';
import { User } from '../Models/user';
import { Response } from '../Models/response';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';
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

  //inject services required into constructor and initialize the user
  constructor(private e:EditService, private r: Router, private ls:LoginService, private t:ToastrService) 
  { 
    this.user = {email: '', password: '', fname:'', lname:'', otp:null, mobileno:null}; 
  }

  //get username on loading of page
  ngOnInit(): void {
    this.username = this.ls.getUserName();
    //console.log(this.username);

    if(this.username!==null && this.username!==undefined)
    this.getuserdetails();
  }

  //fetch user details from db and show on profile
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
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("Some error occured!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
      });
  }
  
  //update into the profile
updateProfile()
{
  this.e.profileEdit(this.user).subscribe(
    (rsp:Response)=>{
      if(rsp.status===1)
      {
        //this.msg=rsp.message;
        //console.log(rsp.status);
        this.t.success("updated successfully");
        window.location.reload();
      }
    },
    (err)=>{//console.log(JSON.stringify(err));
      //this.msg="you got some error";
      this.t.error("Some error occured! Could not perform this action!");
      this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
    });
}

}
