import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseClaimsStartComponent } from './expense-claims-start.component';

describe('ExpenseClaimsStartComponent', () => {
  let component: ExpenseClaimsStartComponent;
  let fixture: ComponentFixture<ExpenseClaimsStartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExpenseClaimsStartComponent]
    });
    fixture = TestBed.createComponent(ExpenseClaimsStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
