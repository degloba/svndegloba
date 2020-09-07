import { TestBed } from '@angular/core/testing';

import { EnviamentsService } from './enviaments.service';

describe('EnviamentsService', () => {
  let service: EnviamentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnviamentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
