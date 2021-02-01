import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { User } from '../Models/user';
import { LoginService } from '../Services/login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PassresetService } from '../Services/passreset.service';
import { Response } from '../Models/response';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: User;
  signupForm: FormGroup;
  userAlreadyExists: boolean;

  constructor(private loginService: LoginService, private router: Router,
    private t: ToastrService, private pr:PassresetService, private r:Router) {
    this.user = {
      email: '', password: '', fname:'', lname:'', otp:null, mobileno:null
    };
  }

  ngOnInit() {
    this.userAlreadyExists=false;
  }

  checkmail()
  { //console.log("checkmail works!");
    this.pr.isEmailExists(this.user.email).subscribe(
      (rsp:Response)=>{
        if(rsp.status===1)
          this.userAlreadyExists = true;
        else
          this.userAlreadyExists = false;
      },
      (err)=>{//console.log(JSON.stringify(err));
        //console.log("you got some error");
        this.t.error("You got some error!!!");
      });
  }

  signup() {
    this.loginService.signup(this.user)
    .subscribe(data => {
      this.router.navigate(['/login'],
        { queryParams: { registered: 'true' } });
        this.t.success("User registered successfully!");
    }, error => {
      //console.log(error);
      this.t.error("Registration Failed! Please try again");
    });
  }
}
