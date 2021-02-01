import { Component } from '@angular/core';
import { Acc } from './acc';
import { AppServiceService } from './app-service.service';
import { Ajaxoutput } from './ajaxoutput';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent 
{
  title = 'sbs';
  testvariable:number=0;
  obj1:Acc;
  acar:Acc[]=[];
  xyz:number[]=[];


  constructor(private so:AppServiceService)
  {
    this.obj1 =new Acc();
    this.obj1.acno=44;
    //this.obj1.acno=44;

    /*
    this.acar.push(new Acc(21,1234,10));
    this.acar.push(new Acc(32,6789,20));
    this.xyz.push(1);
    this.xyz.push(2);
    */
  }
  
  step3()
  {
    //this.title ="step3";
  
    this.so.whyyouWantobyheartSyntax(this.obj1).subscribe((data:Ajaxoutput)=>
    {
      this.title="ajax working"; 
      this.acar=data.result;  
    },
    (err)=>
    {
      this.title="ajax failed";
    });
    /*
    this.so.whyyouWantobyheartSyntax(this.obj1).subscribe
    (function(data:Ajaxoutput){
       this.title="ajax working"; 
       this.acar=data.result;  

      },
    function(err){

     this.title="ajax failed";
     

    });*/
  }



  step2()
  {
    //cannot copy paste this from google i have to think.
    //this.acar = this.so.nearlyserver(this.obj1).result;

    //welcome to style of coding..
    let q:Ajaxoutput =this.so.nearlyserver(this.obj1);
    this.acar=q.result;
   // this.title =JSON.stringify(q);






    //this.title=this.so.testser(3);
    //this.testvariable=4;


  }
}
