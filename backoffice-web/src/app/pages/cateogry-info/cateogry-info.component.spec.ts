import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CateogryInfoComponent } from './cateogry-info.component';

describe('CateogryInfoComponent', () => {
  let component: CateogryInfoComponent;
  let fixture: ComponentFixture<CateogryInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CateogryInfoComponent]
    });
    fixture = TestBed.createComponent(CateogryInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
