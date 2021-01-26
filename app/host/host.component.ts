import { Component, OnInit } from '@angular/core';
import { Hostuser } from '../Models/hostuser';
import { LoginService } from '../Services/login.service';
import { HostService } from '../Services/host.service';
import { Router } from '@angular/router';
import { Response } from '../Models/response';


@Component({
  selector: 'app-host',
  templateUrl: './host.component.html',
  styleUrls: ['./host.component.css']
})
export class HostComponent implements OnInit {

  h:Hostuser;
  selectedFile:File;
  msg:string;

  constructor(private loginService:LoginService, private hs:HostService, private r: Router) 
  { 
    this.h = {
      email : "",
      equipmenttype : "",
      img : "",
      location : "",
      manufacturer : "",
      rent : null,
      servicetype : ""
    };
  }

  ngOnInit(): void {
    this.h.email = this.loginService.getUserName();
    console.log(this.h.email);
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
    this.msg=this.selectedFile.name;
  }

  registerhost(){

    console.log(67);
    this.hs.gethost(this.h).subscribe(

      (rsp:Response)=>{
        console.log(456);

        if(rsp.status===1)
        {

          this.msg=rsp.message;
          this.r.navigate(['/home']);

        }

      },
        (err)=>{console.log(JSON.stringify(err));
        //this.msg="you got some error";

      });
    
  }
}
