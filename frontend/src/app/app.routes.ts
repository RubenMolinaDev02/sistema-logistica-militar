import { Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';
import { ItemDetailComponent } from './pages/item-detail/item-detail.component';
import { ItemCreateComponent } from './pages/create-item/item-create.component';
import { ItemEditComponent } from './pages/edit-item/item-edit.component';
import { MyUserComponent } from './pages/my-user-page/my-user.page';
import { EditMyProfileComponent } from './pages/edit-my-profile/edit-my-profile.component';
import { UsersComponent } from './pages/users-page/users-page';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { EditUserComponent } from './pages/edit-user/edit-user.component';
import { UserCreateComponent } from './pages/create-user/create-user.page';
import { UserCreatedComponent } from './pages/show-created-user/user-created.component';
import { LocationsComponent } from './pages/locations-page/location.page';
import { LocationDetailComponent } from './pages/locations-detail/locations-detail.page';
import { LocationCreateComponent } from './pages/create-location/create-location.page';
import { EditLocationComponent } from './pages/edit-location/location-edit.page';

export const routes: Routes = [

  /*
  |--------------------------------------------------------------------------
  | LOGIN
  |--------------------------------------------------------------------------
  */

  {
    path: 'login',
    loadComponent: () =>
      import('./pages/login/login.component')
        .then(m => m.LoginComponent)
  },

  /*
  |--------------------------------------------------------------------------
  | APP LAYOUT
  |--------------------------------------------------------------------------
  */

  {
    path: '',
    loadComponent: () =>
      import('./pages/shell/shell.component')
        .then(m => m.ShellComponent),

    canActivate: [AuthGuard],

    children: [

      /*
      |--------------------------------------------------------------------------
      | DASHBOARD
      |--------------------------------------------------------------------------
      */

      {
        path: 'dashboard',
        loadComponent: () =>
          import('./pages/dashboard/dashboard.page')
            .then(m => m.DashboardComponent)
      },

      /*
      |--------------------------------------------------------------------------
      | ITEMS
      |--------------------------------------------------------------------------
      */

      {
        path: 'items/:category',
        loadComponent: () =>
          import('./modules/items/items.component')
            .then(m => m.ItemsComponent)
      },
      {
        path: 'items/:category/:id',
        component: ItemDetailComponent
      },
      {
        path: 'items/edit/:category/:id',
        component: ItemEditComponent
      },
      {
        path: 'create/:category',
        component: ItemCreateComponent
      },


      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'users/create',
        component: UserCreateComponent
      },
      {
        path: 'users/detail/:id',
        component: UserDetailComponent
      },
      {
        path: 'users/edit/:id',
        component: EditUserComponent
      },
      {
        path: 'myuser',
        component: MyUserComponent
      },

      {
        path: 'myuser/edit',
        component: EditMyProfileComponent
      },
      {
        path: 'users/create/results',
        component: UserCreatedComponent
      },

      /*
      |--------------------------------------------------------------------------
      | INVENTORY
      |--------------------------------------------------------------------------
      */

      {
        path: 'inventory',
        children: [

          {
            path: '',
            loadComponent: () =>
              import('./modules/inventory/inventory.component')
                .then(m => m.InventoryComponent)
          },

          {
            path: 'skus',
            loadComponent: () =>
              import('./modules/inventory/skus/skus.component')
                .then(m => m.SkusComponent)
          },

          {
            path: 'stock',
            loadComponent: () =>
              import('./modules/inventory/stock/stock.component')
                .then(m => m.StockComponent)
          },

          {
            path: 'units',
            loadComponent: () =>
              import('./modules/inventory/units/units.component')
                .then(m => m.UnitsComponent)
          }
        ]
      },

      /*
      |--------------------------------------------------------------------------
      | SHIPMENTS
      |--------------------------------------------------------------------------
      */

      {
        path: 'shipments',
        loadComponent: () =>
          import('./modules/shipments/shipments.component')
            .then(m => m.ShipmentsComponent)
      },

      /*
      |--------------------------------------------------------------------------
      | LOCATIONS
      |--------------------------------------------------------------------------
      */

      {
        path: 'locations',
        component: LocationsComponent
      },
      {
        path: 'locations/detail/:id',
        component: LocationDetailComponent
      },
      {
        path: 'locations/create',
        component: LocationCreateComponent
      },
      {
        path: 'locations/edit/:id',
        component: EditLocationComponent
      },

      /*
      |--------------------------------------------------------------------------
      | DEFAULT
      |--------------------------------------------------------------------------
      */

      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },

  /*
  |--------------------------------------------------------------------------
  | FALLBACK
  |--------------------------------------------------------------------------
  */

  {
    path: '**',
    redirectTo: 'dashboard'
  }
];