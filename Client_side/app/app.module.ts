import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import  { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { NgxWebstorageModule } from 'ngx-webstorage';
import { ToastrModule } from 'ngx-toastr';
import { TokenInterceptor } from './Models/token-interceptor';
//import { FontAwesomeModule } from '@fortawesome/fontawesome-free';
import { RouterModule, Routes } from '@angular/router';
import { MatCardModule}  from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core'; 
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule, NgxMatTimepickerModule} from '@angular-material-components/datetime-picker';

const routes: Routes = [
]

@NgModule({
  declarations: [
      routingComponents
  ],
  
  imports: [
    BrowserModule,AppRoutingModule,NgbModule,FormsModule,HttpClientModule, 
    BrowserAnimationsModule,MatToolbarModule,MatInputModule,MatButtonModule,MatIconModule,ReactiveFormsModule,
    RouterModule.forRoot(routes),NgxWebstorageModule.forRoot(),ToastrModule.forRoot(), MatCardModule,
    MatDatepickerModule, MatFormFieldModule, MatNativeDateModule,  NgxMatDatetimePickerModule,
    NgxMatNativeDateModule, NgxMatTimepickerModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
