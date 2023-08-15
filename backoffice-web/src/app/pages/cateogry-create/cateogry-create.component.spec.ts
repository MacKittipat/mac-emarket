import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CateogryCreateComponent } from './cateogry-create.component';

describe('CateogryCreateComponent', () => {
  let component: CateogryCreateComponent;
  let fixture: ComponentFixture<CateogryCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CateogryCreateComponent]
    });
    fixture = TestBed.createComponent(CateogryCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
