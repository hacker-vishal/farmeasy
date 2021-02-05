import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Response } from '../Models/response';
import { Userdto } from '../Models/userdto';
import { PassresetService } from '../Services/passreset.service';
import { FormBuilder, FormGroup, Validators} from '@angular/forms'; 
import { ConfirmedValidator } from './confirmed.validator';
import { ToastrService } from 'ngx-toastr';
import { SessionStorageService } from 'angular-web-storage';

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

  //retrieve username on load of page
  ngOnInit() {
    this.username = this.session.get('id');
    //this.username = history.state.id;
    this.userdto.username=this.username;
  }

  //inject services required into constructor and apply validators on reset password form
  constructor(private pr:PassresetService, private r:Router, private fb: FormBuilder,
    private t:ToastrService, private session:SessionStorageService) { 
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

  //confirm and reset the password
  confirm()
  {
    //console.log(JSON.stringify(this.form.value.password));
    this.userdto.password=this.form.value.password;
    //console.log(JSON.stringify(this.userdto));
    this.pr.setNewPassword(this.userdto).subscribe(
      (rsp:Response)=>{
        //console.log(JSON.stringify(rsp));
        if(rsp.status===1)
        {
          //console.log(rsp.message);
          this.t.success(rsp.message);
          this.r.navigate(['/login']);
        }
        else
        this.t.error(rsp.message);
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
      });
  }

}
