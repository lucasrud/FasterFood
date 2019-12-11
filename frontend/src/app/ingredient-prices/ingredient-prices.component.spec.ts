import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientPricesComponent } from './ingredient-prices.component';

describe('IngredientPricesComponent', () => {
  let component: IngredientPricesComponent;
  let fixture: ComponentFixture<IngredientPricesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IngredientPricesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IngredientPricesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
