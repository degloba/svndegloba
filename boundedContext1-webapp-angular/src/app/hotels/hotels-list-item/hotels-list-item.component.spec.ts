import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelsListItemComponent } from './hotels-list-item.component';

describe('HotelsListItemComponent', () => {
  let component: HotelsListItemComponent;
  let fixture: ComponentFixture<HotelsListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelsListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelsListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
