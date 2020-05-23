import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelsListHeaderComponent } from './hotels-list-header.component';

describe('HotelsListHeaderComponent', () => {
  let component: HotelsListHeaderComponent;
  let fixture: ComponentFixture<HotelsListHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelsListHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelsListHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
