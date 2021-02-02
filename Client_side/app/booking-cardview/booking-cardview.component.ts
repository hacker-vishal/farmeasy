import { Component, OnInit } from '@angular/core';
import { BookService } from '../Services/book.service';
import { LoginService } from '../Services/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-booking-cardview',
  templateUrl: './booking-cardview.component.html',
  styleUrls: ['./booking-cardview.component.css']
})
export class BookingCardviewComponent implements OnInit {

  mybookings:any;
  username:string;
  isListEmpty:boolean=false;

  constructor(private bs: BookService, private loginService: LoginService, private t: ToastrService) { }

  ngOnInit(): void {
     
    this.username = this.loginService.getUserName();
    //console.log(this.username);
    this.showmybookings();
  }

  showmybookings()
  {
    this.bs.getbookings(this.username).subscribe(
      (data)=>{//console.log(data);
        if(data)
        {
          for(let d of data)
          {
            d.dateofbooking=new Date(d.dateofbooking).toLocaleDateString("en-us");
            d.datefinish=new Date(d.datefinish).toLocaleDateString("en-us");
            //console.log(d.datefinish);
          }
          //console.log(JSON.stringify(data));
          if(data!==null && data!==undefined)
          this.isListEmpty=false;
          this.mybookings= data;
        }
        else{
          this.t.info("You do not have any bookings yet!");
          this.isListEmpty=true;
        }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
      });

  }

}
