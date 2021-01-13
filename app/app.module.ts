import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import  { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatInputModule, MatButtonModule, MatIconModule } from '@angular/material';





@NgModule({
  declarations: [
    routingComponents,
    
  
    
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,NgbModule,FormsModule,HttpClientModule, 
    BrowserAnimationsModule,MatToolbarModule,MatInputModule,MatButtonModule,MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
