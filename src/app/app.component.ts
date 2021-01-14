import { asNativeElements, Component } from '@angular/core';
//import { AR } from './ar';
//import { Rectangle } from './rectangle';
//import { RectserviceService } from './rectservice.service';
import { RectComponent } from './assign/rect/rect.component';
import { Rectangle } from './assign/rectangle';
import { PA } from './assign/pa';
import { RectservService } from './assign/rectserv.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Hey, TitleName is in app.component.ts';
  fv:any=0; //if i didn't give 'any' then it is assuming only int.
  obj:Rectangle;
  
  //obj1:AR;
  obj1:PA;




  constructor(private y:RectservService)
  {
    this.obj=new Rectangle(0,0);
    this.obj1= new PA(-1,-1);
    //this.obj1= new AR(-1,-1);
  }

  aor()
  {
    //this.fv="hey ";
    //var x = this.y.f1(this.obj);
    this.y.getanswer(this.obj).subscribe(

      (ans:PA)=>{
      if(ans.area==-1) 
          this.fv="failed";
      else
          this.fv="success!! area: "+ans.area+" perimeter: "+ans.perimeter;
    }, 
      (err)=>{this.fv=JSON.stringify(err)
        //this.fv="failed"
      }

    );

  }


}
