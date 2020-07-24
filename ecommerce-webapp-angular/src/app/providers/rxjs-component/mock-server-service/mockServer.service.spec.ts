import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MockServerServiceComponent } from './mock-server-service.component';

describe('MockServerServiceComponent', () => {
  let component: MockServerServiceComponent;
  let fixture: ComponentFixture<MockServerServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MockServerServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MockServerServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
