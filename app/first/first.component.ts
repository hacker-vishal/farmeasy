import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from './search.service';
import { Receive } from './receive';
import { Send } from './send';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent {

  constructor(private r:Router,private srch:SearchService) 
  {
    this.s=new Send();
    this.s.id;
  }

  s:Send;
  rows:any=[];
  headers:any=[];

  logout()
  {
    this.r.navigate(['/casestudy']);
  }

  search()
  {
    this.srch.dosearch(this.s).subscribe((rec:Receive)=>
    {
      this.rows=rec.result;
      this.headers=["empid","fname","lname","dob","doj","grade"];
      //console.log(JSON.stringify(this.rows));
    },
    (error)=>
    {
      this.rows ="ajax failed some issue in contacting";
    })
  }
}
