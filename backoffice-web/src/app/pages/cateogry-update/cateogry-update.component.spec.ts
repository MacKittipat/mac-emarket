import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CateogryUpdateComponent } from './cateogry-update.component';

describe('CateogryUpdateComponent', () => {
  let component: CateogryUpdateComponent;
  let fixture: ComponentFixture<CateogryUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CateogryUpdateComponent]
    });
    fixture = TestBed.createComponent(CateogryUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
