import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemGrid } from './item-grid';

describe('ItemGrid', () => {
  let component: ItemGrid;
  let fixture: ComponentFixture<ItemGrid>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemGrid],
    }).compileComponents();

    fixture = TestBed.createComponent(ItemGrid);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
