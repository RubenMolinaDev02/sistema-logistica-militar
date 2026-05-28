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

import { AuthService } from './auth.service';
import { BaseResponse } from '../../shared/models/base/baseResponse';
import { BaseModel } from '../../shared/models/base/baseModel';
import { ItemDetailModel } from '../../shared/models/detail/detailModel';

@Injectable({
  providedIn: 'root'
})

export class ApiInfoService{
     
    private base = environment.apis.information;
    
      constructor(
        private http: HttpClient,
        private authService: AuthService,
        private router: Router
      ) {}

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

  private authHeaders(): HttpHeaders {

    const token = localStorage.getItem('access_token');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }


    getItems(size: number, page: number, request: any, endpoint: string): Observable<BaseResponse<BaseModel>> {
      return this.requestWithAuth(() =>
        this.http.post<BaseResponse<BaseModel>>(
          `${this.base}${endpoint}`,
          request, { 
            headers: this.authHeaders(),
            params: new HttpParams()
              .set('size', size)
              .set('page', page)
           }
        )
      );
    }
    
    getItemsById(id: string, endpoint: string): Observable<ItemDetailModel> {
      return this.requestWithAuth(() =>
        this.http.get<ItemDetailModel>(
          `${this.base}${endpoint}` + id,
          { 
            headers: this.authHeaders()
          }
        )
      );
    }

    createItem(request: any, category: string): Observable<ItemDetailModel> {
      console.log(request);
      return this.requestWithAuth(() =>
        this.http.post<ItemDetailModel>(
          `${this.base}/armory/${category}`,
          request, { 
            headers: this.authHeaders()
           }
        )
      );
    }

    editItem(request: any, endpoint: string): Observable<ItemDetailModel> {
      console.log(request);
      return this.requestWithAuth(() =>
        this.http.patch<ItemDetailModel>(
          `${this.base}${endpoint}`,
          request, { 
            headers: this.authHeaders()
           }
        )
      );
    }

    get(endpoint: string | undefined): Observable<BaseModel[]>{
        return this.requestWithAuth(() =>
        this.http.get<BaseModel[]>(
          `${this.base}${endpoint}`,
          { 
            headers: this.authHeaders()
           }
        )
      );
    }

    uploadImage(formData: FormData) {
      return this.requestWithAuth(() =>
        this.http.post<ItemDetailModel>(
          `${this.base}/images/upload`,
          formData, { 
            headers: this.authHeaders()
           }
        )
      );
    }

  getWithParams(endpoint: string, params: Record<string, any>) {
  let httpParams = new HttpParams();

  Object.entries(params).forEach(([key, value]) => {
    if (value !== null && value !== undefined && value !== '') {
      httpParams = httpParams.set(key, value);
    }
  });

  return this.requestWithAuth(() =>
    this.http.get<BaseModel[]>(
      `${this.base}${endpoint}`,
      {
        headers: this.authHeaders(),
        params: httpParams
      }
    )
  );
}
}