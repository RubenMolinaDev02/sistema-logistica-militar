import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { DynamicFormComponent } from '../../shared/components/dynamic-form-component/dynamic-form.component';
import { CREATE_WEAPON_MODEL } from '../../shared/models/create-items-dto/create-weapon.dto'; 
import { ApiInfoService } from '../../core/services/api.info.service';

@Component({
  selector: 'app-item-create',
  standalone: true,
  imports: [CommonModule, DynamicFormComponent],
  templateUrl: './item-create.component.html'
})
export class ItemCreateComponent {

  model: any;

  category = "";

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private info: ApiInfoService
    ) { }

    @Output() submit = new EventEmitter<Record<string, any>>();

    submitForm() {
      const values: Record<string, any> = {};

      for (const section of this.model.sections) {
        for (const field of section.fields) {
          values[field.key] = field.value;
        }
      }

      this.submit.emit(values);
    }

ngOnInit(): void {

    const map: any = {
        weapons: () => CREATE_WEAPON_MODEL,
        /*ammo: (data: any) => this.info.createAmmo(data),
        magazines: (data: any) => this.info.createMagazine(data),
        misc: (data: any) => this.info.createMisc(data)*/
    };

    this.category =
      this.route.snapshot.paramMap.get('category') ?? '';

    this.model = map[this.category]();
  }

  handleCreate(payload: any) {
  console.log('CREATE PAYLOAD:', payload);

  const map: Record<string, any> = {
    weapons: (data: any) => this.info.createItem(data, "weapons"),
    /*ammo: (data: any) => this.info.createAmmo(data),
    magazines: (data: any) => this.info.createMagazine(data),
    misc: (data: any) => this.info.createMisc(data)*/
  };

  const req = map[this.category];

  if (!req) return;

  req(payload).subscribe({
    next: () => {
        this.router.navigate(['/items']);
    }
  });
}

  cancel() {
    this.router.navigate(['/items']);
  }
}