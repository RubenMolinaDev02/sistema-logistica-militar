import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemHeader } from './item-header';

describe('ItemHeader', () => {
  let component: ItemHeader;
  let fixture: ComponentFixture<ItemHeader>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemHeader],
    }).compileComponents();

    fixture = TestBed.createComponent(ItemHeader);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
