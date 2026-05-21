import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPagination } from './item-pagination';

describe('ItemPagination', () => {
  let component: ItemPagination;
  let fixture: ComponentFixture<ItemPagination>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemPagination],
    }).compileComponents();

    fixture = TestBed.createComponent(ItemPagination);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
