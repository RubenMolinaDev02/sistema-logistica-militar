import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiInfoService } from '../../core/services/api.info.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { mapToUserDetail } from './user-detail.dto';
import { UserService } from '../../core/services/user.service';

@Component({
  selector: 'app-my-user-detail',
  standalone: true,
  imports: [CommonModule, UiButtonComponent, ItemHeader],
  templateUrl: './my-user.page.html'
})
export class MyUserComponent {

    constructor(
        private userService: UserService,
        private route: ActivatedRoute,
        private cdr: ChangeDetectorRef,
        private router: Router
    ) {}

    loading = false;

  item: any = null;

  category = '';
  id = '';

  openEdit(category: string) {
  this.router.navigate([`/items/edit/${category}/${this.id}`])
  }

  ngOnInit(): void {

    this.category =
      this.route.snapshot.paramMap.get('category') ?? '';

    this.id =
      this.route.snapshot.paramMap.get('id') ?? '';

    this.loadData();
  }

  isObject(value: any): boolean {
    return value && typeof value === 'object' && !Array.isArray(value);
  }

  isPrimitive(value: any): boolean {
    return (
      typeof value === 'string' ||
      typeof value === 'number' ||
      typeof value === 'boolean'
    );
  }

  objectEntries(obj: any) {
    return Object.entries(obj || {});
  }

  formatLabel(key: string): string {
    return key
      .replace(/([A-Z])/g, ' $1')
      .replace(/_/g, ' ')
      .replace(/^./, s => s.toUpperCase());
  }

  back(category: string){
  this.router.navigate([`/items/${category}`])
}

  loadData(): void {

  this.loading = true;

  this.userService.getMyUser().subscribe({
    next: (res: any) => {
      this.item = mapToUserDetail(res);
      console.log(this.item);
      this.loading = false;
      this.cdr.detectChanges();
    },
    error: () => {
      this.loading = false;
      this.cdr.detectChanges();
    }
  });
}



}




