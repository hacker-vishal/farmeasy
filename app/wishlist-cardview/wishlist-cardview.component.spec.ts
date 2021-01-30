import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WishlistCardviewComponent } from './wishlist-cardview.component';

describe('WishlistCardviewComponent', () => {
  let component: WishlistCardviewComponent;
  let fixture: ComponentFixture<WishlistCardviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WishlistCardviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WishlistCardviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
