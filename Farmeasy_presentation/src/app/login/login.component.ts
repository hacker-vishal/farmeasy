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
    isLoggedIn=false;
    url:any;
    //hideSuccessMessage:boolean=false;
 
    //inject services required into constructor and initialize userdto
    constructor(private loginService: LoginService, private activatedRoute: ActivatedRoute,
     private router: Router, private toastr: ToastrService) {
      this.userdto = {
        username: '',
        password: ''
      };
   }
 
    //apply validators on login form
   ngOnInit(): void {

    this.url = history.state.url;//console.log(this.url);

     this.loginForm = new FormGroup({
       username: new FormControl('', Validators.required),
       password: new FormControl('', Validators.required)
     });
   }
 
   //logs in the user if credentials are correct
    login() {
      // this.userdto.username = this.loginForm.get('username').value;
      // this.userdto.password = this.loginForm.get('password').value;
 
      this.loginService.login(this.userdto).subscribe(data => {
      //console.log('login successful');
            this.isError = false;
        this.router.navigateByUrl('/');
        this.toastr.success('Login Successful!');
        //this.msg ="Login Successful!";
        this.isLoggedIn=true; //console.log(this.isLoggedIn);
        if(this.url!==null && this.url!==undefined)
        this.router.navigate([this.url]);
      }, error => {
        this.isError = true;
        this.toastr.error("Incorrect username or password!!!");
        throwError(error);
      });
    }

    //if forgot password, navigate to forgot password page
  fgtpswd()
  { this.router.navigate['/forgotpassword'];}

  logindummy()
  {
    this.loginService.getUserEmailPswd(this.userdto).subscribe(
      (creds)=>{
        //console.log(JSON.stringify(creds));
        this.toastr.info("No such user found");
        if(creds.status==1)
        {
            this.msg = "Logged in successfully";
            //console.log("logged in");
        }
      },
      (err)=>{this.msg= JSON.stringify(err);
      });
  }

  // FadeOutSuccessMsg() {
  //   setTimeout( () => {
  //       this.hideSuccessMessage = true;
  //    }, 4000);
  //   }
}
