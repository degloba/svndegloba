import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnviamentsComponent } from './enviaments.component';

describe('EnviamentsComponent', () => {
  let component: EnviamentsComponent;
  let fixture: ComponentFixture<EnviamentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnviamentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnviamentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
