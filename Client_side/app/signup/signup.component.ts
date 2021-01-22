import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../Models/user';
import { LoginService } from '../Services/login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { analyzeAndValidateNgModules } from '@angular/compiler';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: User;
  signupForm: FormGroup;

  constructor(private loginService: LoginService, private router: Router,
    private toastr: ToastrService) {
    this.user = {
      email: '', password: '', fname:'', lname:'', otp:null, mobileno:null
    };
  }

  ngOnInit() {
    // this.signupForm = new FormGroup({
    //   username: new FormControl('', Validators.required),
    //   email: new FormControl('', [Validators.required, Validators.email]),
    //   password: new FormControl('', Validators.required),
    // });
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
    }, error => {
      console.log(error);
      this.toastr.error('Registration Failed! Please try again');
    });
  }

}
