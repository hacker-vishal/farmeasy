import { Component, OnInit } from '@angular/core';
import { Hostuser } from '../Models/hostuser';
import { LoginService } from '../Services/login.service';
import { HostService } from '../Services/host.service';
import { Response } from '../Models/response';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-host',
  templateUrl: './host.component.html',
  styleUrls: ['./host.component.css']
})
export class HostComponent implements OnInit {

  h:Hostuser;
  selectedFile:File;
  msg:string;
  

  constructor(private loginService:LoginService, private hs:HostService, private t:ToastrService) 
  { 
    this.h = {
      hostemail : "",
      equipmenttype : "",
      img : null,
      location : "",
      manufacturer : "",
      rent : null,
      servicetype : ""
    };
  }

  ngOnInit(): void {
    this.h.hostemail = this.loginService.getUserName();
    //console.log(this.h.hostemail);
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
    this.msg=this.selectedFile.name;
  }

  registerhost(){

    this.hs.gethostdetails(this.h).subscribe(
     
      (rsp:Response)=>{
        //console.log(this.h);
        if(rsp.status===1)
        {
          this.t.show(rsp.message);
          //console.log(rsp.message);
        }
        else
        {
          this.t.warning(rsp.message);
          //console.log(rsp.message);
        }
      },
    (err)=>{//console.log(JSON.stringify(err));
      this.t.error("You got some error!!!")
    });
}
}