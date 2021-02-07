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

  //inject services required into constructor and initialize user
  constructor(private loginService: LoginService, private router: Router,
    private t: ToastrService, private pr:PassresetService, private r:Router) {
    this.user = {
      email: '', password: '', fname:'', lname:'', otp:null, mobileno:null
    };
  }

  //on loading of page, checks whether the user already exists or not
  ngOnInit() {
    this.userAlreadyExists=false;
  }

  //check the username to find if user with same name exists or not
  checkmail()
  { //console.log("checkmail works!");
    this.pr.isEmailExists(this.user.email).subscribe(
      (rsp:Response)=>{
        if(rsp.status===1)
        {
          this.userAlreadyExists = true;
          this.t.warning("Seems you already have an account! Try logging in!!!");
        }
        else
          this.userAlreadyExists = false;
      },
      (err)=>{//console.log(JSON.stringify(err));
        //console.log("you got some error");
        this.t.error("Some error occured! Seems you already have an account! Try logging in!!!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
      });
  }

  //completes the registration of user
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
