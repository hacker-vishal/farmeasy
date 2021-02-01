import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-adoption',
  templateUrl: './adoption.component.html',
  styleUrls: ['./adoption.component.css']
})
export class AdoptionComponent implements OnInit {

  petId = 1234;
  constructor() { }

  ngOnInit(): void {
  }
  onSubmit(myForm:NgForm){
    console.log("Adopted");
  }

}
