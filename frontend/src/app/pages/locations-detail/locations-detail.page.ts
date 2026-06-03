import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiInfoService } from '../../core/services/api.info.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { mapToUserDetail } from '../my-user-page/user-detail.dto';
import { mapToLocationDetail } from './locations-detail.dto';
import { AuthService } from '../../core/services/auth.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-location-detail',
  standalone: true,
  imports: [CommonModule, UiButtonComponent, ItemHeader],
  templateUrl: './locations-detail.page.html'
})
export class LocationDetailComponent {

    constructor(
        private apiInfoService: ApiInfoService,
        private route: ActivatedRoute,
        private cdr: ChangeDetectorRef,
        private router: Router,
        public authService: AuthService
    ) {}

    environment = environment;

    loading = false;

  item: any = null;

  id = '';

  canDelete: boolean = false;

  openEdit() {
  this.router.navigate([`/locations/edit/${this.id}`])
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
  this.router.navigate([`/locations`])
}

  loadData(): void {

  this.loading = true;

  this.apiInfoService.getItemsById(this.id, `/organization/locations/`).subscribe({
    next: (res: any) => {
      this.canDelete = res.canDelete;
      this.item = mapToLocationDetail(res);
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




