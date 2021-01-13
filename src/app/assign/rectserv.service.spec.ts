import { TestBed } from '@angular/core/testing';

import { RectservService } from './rectserv.service';

describe('RectservService', () => {
  let service: RectservService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RectservService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
