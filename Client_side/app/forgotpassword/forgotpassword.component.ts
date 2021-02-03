import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PassresetService } from '../Services/passreset.service';
import { Response } from '../Models/response';
import { ToastrService } from 'ngx-toastr';
import { SessionStorageService } from 'angular-web-storage';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  username:string;
  msg: string;

  //inject services required into constructor
  constructor(private r: Router, private pr:PassresetService, 
    private t:ToastrService, private session:SessionStorageService) 
  { }

  ngOnInit() {
  }

  //generates an otp and stores in db for the user who forgot the password
  otp()
  {
    this.pr.isEmailExists(this.username).subscribe(
      (rsp:Response)=>{
        if(rsp.status===1)
        {
          this.t.show(rsp.message);
          //console.log(rsp.status);
          this.r.navigate(['/otpverification']);
          this.session.set('id',this.username);
          // this.r.navigate(['/otpverification'],{ state: { id: this.username } } );
        }
        else{
          this.t.error("User with "+this.username+" username does not exist");
        }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
      });
  }

}
