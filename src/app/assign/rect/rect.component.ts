import { Component, OnInit } from '@angular/core';
import { PA } from '../pa';
import { Rectangle } from '../rectangle'
import { RectservService } from '../rectserv.service';

@Component({
  selector: 'app-rect',
  templateUrl: './rect.component.html',
  styleUrls: ['./rect.component.css']
})
export class RectComponent {

  stats:any="get your answer here";
  p:PA;
  r:Rectangle;

  constructor(private y:RectservService) 
  { 
    this.r=new Rectangle(0,0);
    this.p=new PA(-1,-1);
  }

  poc()
  {
    this.stats="works";
    /*this.y.poc().subscribe(
      (got)=>{
        if(got!=null)
        this.stats=got;
      },
      (err)=>{//this.stats=JSON.stringify(err)
        this.stats="some error found";
    }
    );*/
  }

  areaperi()
  {
    //this.stats="area and perimeter will be shown here";

    this.y.getanswer(this.r).subscribe(
    (got:PA)=>{ 
      
      if(got.area==-1)
          this.stats="failed";
      else
          this.stats="suceeded! Area: "+got.area+" Perimeter: "+got.perimeter;   
    
    },
      
      (err)=>{this.stats=JSON.stringify(err)
          //this.stats="some error found";
      }
    );
  }
}
