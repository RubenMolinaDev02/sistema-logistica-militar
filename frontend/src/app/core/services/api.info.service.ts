import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpHeaders,
  HttpParams
} from '@angular/common/http';

import {
  catchError,
  from,
  Observable,
  switchMap,
  throwError
} from 'rxjs';

import { Router } from '@angular/router';

import { environment } from '../../../environments/environment';

import { WeaponResponse } from '../../shared/models/weapon/weaponResponse';

import { WeaponModel } from '../../shared/models/weapon/weaponModel';

import {
  ItemResponse,
  ItemType,
  SkuResponse,
  StockResponse,
  SerializedUnitResponse,
  ShipmentResponse,
  BatchResponse,
  LocationResponse
} from '../../shared/models/models';

import { AuthService } from './auth.service';
import { BaseResponse } from '../../shared/models/base/baseResponse';
import { BaseModel } from '../../shared/models/base/baseModel';
import { ItemDetailModel } from '../../shared/models/detail/detailModel';

@Injectable({
  providedIn: 'root'
})
export class InformationService {

  private base = environment.apis.information;

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}

  // ─────────────────────────────────────────
  // GENERIC REQUEST WITH AUTO REFRESH
  // ─────────────────────────────────────────

  private requestWithAuth<T>(
    request: () => Observable<T>
  ): Observable<T> {

    return request().pipe(

      catchError(error => {

        console.log('ERROR', error);
        
        // token expirado
        if (error.status === 401) {

          return from(this.authService.refreshToken()).pipe(

            switchMap((newToken: string) => {

              localStorage.setItem('access_token', newToken);

              return request();
            }),

            catchError(refreshError => {

              // refresh expiró → login
              localStorage.clear();

              this.router.navigate(['/login']);

              return throwError(() => refreshError);
            })
          );
        }

        return throwError(() => error);
      })
    );
  }

  // ─────────────────────────────────────────
  // HEADERS
  // ─────────────────────────────────────────

  private authHeaders(): HttpHeaders {

    const token = localStorage.getItem('access_token');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  // ─────────────────────────────────────────
  // ITEMS
  // ─────────────────────────────────────────

  getItem(
    id: string,
    type: ItemType
  ): Observable<ItemResponse> {

    return this.requestWithAuth(() =>

      this.http.get<ItemResponse>(
        `${this.base}/items/${id}`,
        {
          params: new HttpParams().set('type', type),
          headers: this.authHeaders()
        }
      )
    );
  }

  getWeapons(size: number, page: number, request: any): Observable<BaseResponse<BaseModel>> {
  return this.requestWithAuth(() =>
    this.http.post<BaseResponse<BaseModel>>(
      `${this.base}/armory/weapons/search`,
      request, { 
        headers: this.authHeaders(),
        params: new HttpParams()
          .set('size', size)
          .set('page', page)
       }
    )
  );
}

getWeaponById(id: string): Observable<ItemDetailModel> {
  return this.requestWithAuth(() =>
    this.http.get<ItemDetailModel>(
      `${this.base}/armory/weapons/id/` + id,
      { 
        headers: this.authHeaders()
       }
    )
  );
}

  getAmmo(): Observable<any[]> {

    return this.requestWithAuth(() =>

      this.http.get<any[]>(
        `${this.base}/armory/ammo`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getMagazines(): Observable<any[]> {

    return this.requestWithAuth(() =>

      this.http.get<any[]>(
        `${this.base}/armory/magazines`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getHelmets(): Observable<any[]> {

    return this.requestWithAuth(() =>

      this.http.get<any[]>(
        `${this.base}/armory/helmets`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getArmorVests(): Observable<any[]> {

    return this.requestWithAuth(() =>

      this.http.get<any[]>(
        `${this.base}/armory/armor-vests`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getNvgs(): Observable<any[]> {

    return this.requestWithAuth(() =>

      this.http.get<any[]>(
        `${this.base}/armory/nvg`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }


  getMiscItems(size: number, page: number, request: any): Observable<BaseResponse<BaseModel>> {
  return this.requestWithAuth(() =>
    this.http.post<BaseResponse<BaseModel>>(
      `${this.base}/armory/misc/search`,
      request, { 
        headers: this.authHeaders(),
        params: new HttpParams()
          .set('size', size)
          .set('page', page)
       }
    )
  );
}

getMiscItemsById(id: string): Observable<ItemDetailModel> {
  return this.requestWithAuth(() =>
    this.http.get<ItemDetailModel>(
      `${this.base}/armory/misc/id/` + id,
      { 
        headers: this.authHeaders()
       }
    )
  );
}
}