import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Response } from '../Models/response';
import { Userdto } from '../Models/userdto';
import { PassresetService } from '../Services/passreset.service';

@Component({
  selector: 'app-setnewpassword',
  templateUrl: './setnewpassword.component.html',
  styleUrls: ['./setnewpassword.component.css']
})
export class SetnewpasswordComponent implements OnInit {

  username:string;
  passwd:string;
  userdto:Userdto;

  ngOnInit() {
    this.username = history.state.id;
  }

  constructor(private pr:PassresetService, private r:Router) { 
    this.userdto = new Userdto ("","");
  }

  confirm()
  {console.log(123);
    this.pr.setNewPassword(this.userdto).subscribe(
      (rsp:Response)=>{
        console.log(JSON.stringify(rsp));
        if(rsp.status===1 )
        {
          console.log(rsp.message);
          this.r.navigate(['/login']);
        }
      },
      (err)=>{console.log(JSON.stringify(err));
      });
  }

}
