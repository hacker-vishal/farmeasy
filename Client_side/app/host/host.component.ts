import { Component, OnInit } from '@angular/core';
import { Hostuser } from '../Models/hostuser';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-host',
  templateUrl: './host.component.html',
  styleUrls: ['./host.component.css']
})
export class HostComponent implements OnInit {

  h:Hostuser;
  selectedFile:File;
  msg:string;

  constructor(private loginService:LoginService) 
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
    //this.h.email = this.loginService.getUserName();
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
    this.msg=this.selectedFile.name;
  }

  registerhost(){
    
  }
}
