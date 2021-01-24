import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';
import { PassresetService } from '../Services/passreset.service';
import { Response } from '../Models/response';

@Component({
  selector: 'app-otpverification',
  templateUrl: './otpverification.component.html',
  styleUrls: ['./otpverification.component.css']
})
export class OtpverificationComponent implements OnInit {

  username:string;
  otp=null;

  constructor(private r: Router, private pr:PassresetService, private ls:LoginService) { }

  ngOnInit() {
    this.username = history.state.id;

    console.log(this.username);

    if(this.username!==null && this.username!==undefined)
    this.findOtp();

    //console.log(this.otp);
  }

  findOtp()
  {
    this.pr.getOtp(this.username).subscribe(
      (otp)=>{console.log(otp);
       alert(otp);
      },
      (err)=>{console.log(JSON.stringify(err));
      });
  }

  pswrdreset()
  {
    //this.r.navigate(['/setnewpassword']);
    this.pr.checkOtp(this.otp).subscribe(
      (rsp:Response)=>{
        if(rsp.status===1 )
        {
          console.log(rsp.message);
          this.r.navigate(['/setnewpassword'],{ state: { id: this.username } } );
        }
      },
      (err)=>{console.log(JSON.stringify(err));
      });
  }

}
