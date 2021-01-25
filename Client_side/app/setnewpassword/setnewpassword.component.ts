import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Response } from '../Models/response';
import { Userdto } from '../Models/userdto';
import { PassresetService } from '../Services/passreset.service';
import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms'; 
import { ConfirmedValidator } from './confirmed.validator';

@Component({
  selector: 'app-setnewpassword',
  templateUrl: './setnewpassword.component.html',
  styleUrls: ['./setnewpassword.component.css']
})
export class SetnewpasswordComponent implements OnInit {

  username:string;
  passwd:string;
  userdto:Userdto;
  form: FormGroup = new FormGroup({});

  ngOnInit() {
    this.username = history.state.id;
    this.userdto.username=this.username;
  }

  constructor(private pr:PassresetService, private r:Router, private fb: FormBuilder) { 
    this.userdto = new Userdto ("","");
    this.form = fb.group({
      password: ['', [Validators.required]],
      confirm_password: ['', [Validators.required]]
    }, { 
      validator: ConfirmedValidator('password', 'confirm_password')
    })
  }

  get f(){
    return this.form.controls;
  }

  confirm()
  {//console.log(123);
    this.pr.setNewPassword(this.userdto).subscribe(
      (rsp:Response)=>{
        console.log(JSON.stringify(rsp));
        if(rsp.status===1)
        {
          console.log(rsp.message);
          this.r.navigate(['/login']);
        }
      },
      (err)=>{console.log(JSON.stringify(err));
      });
  }

}
