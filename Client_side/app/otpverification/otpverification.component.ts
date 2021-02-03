import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';
import { PassresetService } from '../Services/passreset.service';
import { Response } from '../Models/response';
import { ToastrService } from 'ngx-toastr';
import { SessionStorageService } from 'angular-web-storage';

@Component({
  selector: 'app-otpverification',
  templateUrl: './otpverification.component.html',
  styleUrls: ['./otpverification.component.css']
})
export class OtpverificationComponent implements OnInit {

  username:string;
  otp=null;

  //inject services required into constructor
  constructor(private r: Router, private pr:PassresetService, private ls:LoginService,
    private t:ToastrService, private session:SessionStorageService) { }

    //get otp for username who forgot his password
  ngOnInit() {
    this.username = this.session.get('id');

    //console.log(this.username);

    if(this.username!==null && this.username!==undefined)
    this.findOtp();

    //console.log(this.otp);
  }

  //finds otp from db
  findOtp()
  {
    this.pr.getOtp(this.username).subscribe(
      (otp)=>{//console.log(otp);
       alert(otp);
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
      });
  }

  //resets password with new password
  pswrdreset()
  {
    //this.r.navigate(['/setnewpassword']);
    this.pr.checkOtp(this.otp).subscribe(
      (rsp:Response)=>{
        if(rsp.status===1 )
        {
          //console.log(rsp.message);
          this.t.success(rsp.message);
          this.r.navigate(['/setnewpassword']);
          // this.r.navigate(['/setnewpassword'],{ state: { id: this.username } } );
        }
        else
        this.t.error(rsp.message);
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmease.txt"));
      });
  }

}
