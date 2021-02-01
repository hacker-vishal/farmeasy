import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdoptionComponent } from './adoption/adoption.component';
import { CasestudyComponent } from './casestudy/casestudy.component';
import { FirstComponent } from './first/first.component';
import { JobdescComponent } from './jobdesc/jobdesc.component';

const routes: Routes = [
  { path: 'casestudy', component:CasestudyComponent },
  { path: 'first', component:FirstComponent },
  { path: 'adoption', component:AdoptionComponent },
  { path: 'jobdesc', component:JobdescComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
