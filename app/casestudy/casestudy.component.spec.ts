import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CasestudyComponent } from './casestudy.component';

describe('CasestudyComponent', () => {
  let component: CasestudyComponent;
  let fixture: ComponentFixture<CasestudyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CasestudyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CasestudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
