import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-casestudy',
  styleUrls: ['./casestudy.component.css'],
  templateUrl: './casestudy.component.html',
})
export class CasestudyComponent 
{
  constructor(private r:Router)
   { }

   x:any;
   y:any;
   msg:any;
   hint:any;

  ngOnInit() {
  }

  login()
  {
    if(this.x!=null && this.y!=null)
    {
      if(this.y=="root")
      this.r.navigate(['/first']);
      else
      {
        this.hint="Password hint:root";
        this.msg="Password is case sensitive!!!";
      }
    }
    else
    this.msg="Username and password are mandatory!!!";
  }

  reset()
  {
    this.x=null;
    this.y=null;
  }
}