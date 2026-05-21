import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemTabs } from './item-tabs';

describe('ItemTabs', () => {
  let component: ItemTabs;
  let fixture: ComponentFixture<ItemTabs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemTabs],
    }).compileComponents();

    fixture = TestBed.createComponent(ItemTabs);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
