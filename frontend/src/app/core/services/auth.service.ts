import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {

  async login(username: string, password: string): Promise<any> {

    const body = new URLSearchParams();

    body.set('client_id', environment.keycloak.clientId);
    body.set('grant_type', 'password');
    body.set('username', username);
    body.set('password', password);

    const response = await fetch(
      `${environment.keycloak.url}/realms/${environment.keycloak.realm}/protocol/openid-connect/token`,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        body
      }
    );

    if (!response.ok) {
      throw new Error('Login failed');
    }

    const data = await response.json();

    localStorage.setItem('access_token', data.access_token);
    localStorage.setItem('refresh_token', data.refresh_token);

    return data;
  }
  
async refreshToken(): Promise<string> {

  const refreshToken = localStorage.getItem('refresh_token');

  const body = new URLSearchParams();

  body.set('client_id', 'weapon-service');
  body.set('grant_type', 'refresh_token');
  body.set('refresh_token', refreshToken!);

  const response = await fetch(
    `${environment.keycloak.url}/realms/${environment.keycloak.realm}/protocol/openid-connect/token`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body
    }
  );

  if (!response.ok) {
    throw new Error('Refresh failed');
  }

  const data = await response.json();

  localStorage.setItem('access_token', data.access_token);

  if (data.refresh_token) {
    localStorage.setItem('refresh_token', data.refresh_token);
  }

  return data.access_token;
}
}