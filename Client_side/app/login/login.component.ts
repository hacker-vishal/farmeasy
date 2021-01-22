import { Component, OnInit } from '@angular/core';
import { Userdto } from '../Models/userdto';
import { LoginService } from '../Services/login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { throwError } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  msg:string;

    loginForm: FormGroup;
    userdto: Userdto;
   registerSuccessMessage: string;
   isError: boolean;
 
    constructor(private loginService: LoginService, private activatedRoute: ActivatedRoute,
     private router: Router, private toastr: ToastrService) {
      this.userdto = {
        username: '',
        password: ''
      };
   }
 
   ngOnInit(): void {
     this.loginForm = new FormGroup({
       username: new FormControl('', Validators.required),
       password: new FormControl('', Validators.required)
     });
 
      this.activatedRoute.queryParams
        .subscribe(params => {
          if (params.registered !== undefined && params.registered === 'true') {
            this.toastr.success('Signup Successful');
            this.registerSuccessMessage = 'Please Check your inbox for activation email '
              + 'activate your account before you Login!';
          }
        });
   }
 
    login() {
      // this.userdto.username = this.loginForm.get('username').value;
      // this.userdto.password = this.loginForm.get('password').value;
 
      this.loginService.login(this.userdto).subscribe(data => {
      console.log('login successful');
            this.isError = false;
        this.router.navigateByUrl('/');
        this.toastr.success('Login Successful');
      }, error => {
        this.isError = true;
        throwError(error);
      });
    }

  fgtpswd()
  { this.router.navigate['/forgotpassword'];}

  logindummy()
  {
    this.loginService.getUserEmailPswd(this.userdto).subscribe(
      (creds)=>{
        console.log(JSON.stringify(creds));
        this.msg = "No such user found";
        if(creds.status==1)
        {
            this.msg = "Logged in successfully";
            console.log("logged in");
        }
      },
      (err)=>{this.msg= JSON.stringify(err);
      });
  }
}
