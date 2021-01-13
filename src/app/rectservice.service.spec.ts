import { TestBed } from '@angular/core/testing';

import { RectserviceService } from './rectservice.service';

describe('RectserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RectserviceService = TestBed.get(RectserviceService);
    expect(service).toBeTruthy();
  });
});
