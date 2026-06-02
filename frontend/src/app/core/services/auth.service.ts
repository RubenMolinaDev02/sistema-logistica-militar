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

getUserRoles(): string[] {

  const token =
    localStorage.getItem('access_token');

  if (!token) return [];

  try {

    const payload =
      JSON.parse(atob(token.split('.')[1]));

    const realmRoles =
      payload?.realm_access?.roles || [];

    const clientRoles =
      payload?.resource_access?.[
        environment.keycloak.clientId
      ]?.roles || [];

    return [
      ...realmRoles,
      ...clientRoles
    ];

  } catch {

    return [];

  }

}

hasRole(role: string): boolean {
  return this.getUserRoles().includes(role);
}

async logout(): Promise<void> {

  const refreshToken =
    localStorage.getItem('refresh_token');

  try {

    if (refreshToken) {

      const body = new URLSearchParams();

      body.set(
        'client_id',
        environment.keycloak.clientId
      );

      body.set(
        'refresh_token',
        refreshToken
      );

      await fetch(
        `${environment.keycloak.url}/realms/${environment.keycloak.realm}/protocol/openid-connect/logout`,
        {
          method: 'POST',
          headers: {
            'Content-Type':
              'application/x-www-form-urlencoded'
          },
          body
        }
      );
    }

  } catch (err) {

    console.error('Logout error', err);

  } finally {

    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');

    sessionStorage.clear();
    localStorage.clear();
  }
}
}