import { TestBed } from '@angular/core/testing';

import { PassresetService } from './passreset.service';

describe('PassresetService', () => {
  let service: PassresetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PassresetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
