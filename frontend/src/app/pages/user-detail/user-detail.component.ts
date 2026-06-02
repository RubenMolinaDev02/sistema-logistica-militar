import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiInfoService } from '../../core/services/api.info.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { mapToUserDetail } from '../my-user-page/user-detail.dto';

@Component({
  selector: 'app-user-detail',
  standalone: true,
  imports: [CommonModule, UiButtonComponent, ItemHeader],
  templateUrl: './user-detail.component.html'
})
export class UserDetailComponent {

    constructor(
        private apiInfoService: ApiInfoService,
        private route: ActivatedRoute,
        private cdr: ChangeDetectorRef,
        private router: Router
    ) {}

    loading = false;

  item: any = null;

  id = '';

  canDelete: boolean = false;

  openEdit() {
  this.router.navigate([`/users/edit/${this.id}`])
  }


  ngOnInit(): void {


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

  back(){
  this.router.navigate([`/users`])
}

  loadData(): void {

  this.loading = true;

  this.apiInfoService.getItemsById(this.id, `/admin/users/detail/`).subscribe({
    next: (res: any) => {
      this.canDelete = res.canDelete;
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




