import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/Forms';
import { AppRoutingModule } from './app-routing.module';
import  { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SessionComponent } from './session/session.component';
import { CasestudyComponent } from './casestudy/casestudy.component';
import { FirstComponent } from './first/first.component';
import { AdoptionComponent } from './adoption/adoption.component';
import { JobdescComponent } from './jobdesc/jobdesc.component';


@NgModule({
  declarations: [
    AppComponent,
    SessionComponent,
    CasestudyComponent,
    FirstComponent,
    AdoptionComponent,
    JobdescComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
