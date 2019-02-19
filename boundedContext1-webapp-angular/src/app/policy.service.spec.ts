import { TestBed } from '@angular/core/testing';

import { PolicyService } from './policy.service';

describe('PolicyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PolicyService = TestBed.get(PolicyService);
    expect(service).toBeTruthy();
  });
});
