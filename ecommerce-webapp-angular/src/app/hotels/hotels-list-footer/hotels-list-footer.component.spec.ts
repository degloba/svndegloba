import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelsListFooterComponent } from './hotels-list-footer.component';

describe('HotelsListFooterComponent', () => {
  let component: HotelsListFooterComponent;
  let fixture: ComponentFixture<HotelsListFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelsListFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelsListFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
