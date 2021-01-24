import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';
import { PassresetService } from '../Services/passreset.service';
import { Response } from '../Models/response';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  username:string;
  msg: string;

  constructor(private r: Router, private pr:PassresetService, 
    private ls:LoginService) 
  { }

  ngOnInit() {
  }

  otp()
  {
    this.pr.isEmailExists(this.username).subscribe(
      (rsp:Response)=>{
        if(rsp.status===1)
        {
          this.msg=rsp.message;
          //console.log(rsp.status);
          this.r.navigate(['/otpverification'],{ state: { id: this.username } } );
        }
      },
      (err)=>{console.log(JSON.stringify(err));
        this.msg="you got some error";

      });
  }

}
