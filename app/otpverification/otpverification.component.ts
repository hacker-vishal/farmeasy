import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-otpverification',
  templateUrl: './otpverification.component.html',
  styleUrls: ['./otpverification.component.css']
})
export class OtpverificationComponent implements OnInit {

  constructor(private r: Router) { }

  pswrdreset()
  {
    this.r.navigate['/setnewpassword'];
  }

  ngOnInit() {
  }

}
