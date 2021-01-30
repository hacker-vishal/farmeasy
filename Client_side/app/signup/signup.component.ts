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
    // this.signupForm = new FormGroup({
    //   username: new FormControl('', Validators.required),
    //   email: new FormControl('', [Validators.required, Validators.email]),
    //   password: new FormControl('', Validators.required),
    // });
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
    // this.user.email = this.signupForm.get('email').value;
    // this.user.password = this.signupForm.get('password').value;
    // this.user.fname = this.signupForm.get('fname').value;
    // this.user.lname = this.signupForm.get('lname').value;
    // this.user.otp = this.signupForm.get('otp').value;
    // this.user.mobileno = this.signupForm.get('mobileno').value;

    this.loginService.signup(this.user)
    .subscribe(data => {
      this.router.navigate(['/login'],
        { queryParams: { registered: 'true' } });
        this.t.success("User registered successfully!");
    }, error => {
      //console.log(error);
      this.t.error('Registration Failed! Please try again');
    });
  }

}
