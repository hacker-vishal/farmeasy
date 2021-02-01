import { Component, OnInit } from '@angular/core';
import { WishlistService } from '../Services/wishlist.service';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from '../Services/login.service';
import { Response } from '../Models/response';
import { Router } from '@angular/router';
import { SessionStorageService } from 'angular-web-storage';

@Component({
  selector: 'app-wishlist-cardview',
  templateUrl: './wishlist-cardview.component.html',
  styleUrls: ['./wishlist-cardview.component.css']
})
export class WishlistCardviewComponent implements OnInit {

  mywishlist:any;
  username:string;
  isListEmpty:boolean=false;

  constructor(private ws: WishlistService, private t: ToastrService, private ls:LoginService, private r:Router,
    private session:SessionStorageService) { }

  ngOnInit(): void {

    this.username = this.ls.getUserName();
    this.showmywishlist();
  }

  showmywishlist()
  {
    this.ws.getwishlist(this.username).subscribe(
      (list)=>{

          if(list!==undefined && list!==null)
          {
            this.mywishlist=list;
            //console.log(JSON.stringify(list));
            this.isListEmpty=false;
          }
          else
          {
            this.t.info("You do not have anything in your wishlist!");
            this.isListEmpty=true;
          }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
      });
  }

  bookthisitem(w:any)
  {
    this.session.remove('book');
    this.session.set('book',w);
    this.r.navigate(['/book']);
  }

  removefromwishlist(w:any)
  {
    this.ws.removeitem(w).subscribe(
      (rsp:Response)=>{

          if(rsp.status==1)
          {
            this.t.success(rsp.message);
            //console.log(rsp.message);
            window.location.reload();
          }
          else
          {
            this.t.info(rsp.message);
          }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
      });
  }
}
