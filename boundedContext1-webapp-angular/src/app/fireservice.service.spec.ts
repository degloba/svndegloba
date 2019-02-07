import { TestBed } from '@angular/core/testing';

import { FireserviceService } from './fireservice.service';

describe('FireserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FireserviceService = TestBed.get(FireserviceService);
    expect(service).toBeTruthy();
  });
});
