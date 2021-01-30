import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingCardviewComponent } from './booking-cardview.component';

describe('BookingCardviewComponent', () => {
  let component: BookingCardviewComponent;
  let fixture: ComponentFixture<BookingCardviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookingCardviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingCardviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
