import { Component, OnInit } from '@angular/core';
import {
  RouterOutlet,
  RouterLink,
  RouterLinkActive
} from '@angular/router';

import { CommonModule } from '@angular/common';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-shell',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './shell.component.html'
})
export class ShellComponent implements OnInit {

  username = '';
  roles = '';

  constructor(private keycloak: KeycloakService) {}

  async ngOnInit(): Promise<void> {

    const logged = await this.keycloak.isLoggedIn();

    if (!logged) {
      return;
    }

    const profile = await this.keycloak.loadUserProfile();

    this.username = profile.username ?? '';

    const roles = this.keycloak.getUserRoles();

    this.roles = roles.slice(0, 2).join(', ');
  }

  logout(): void {
    this.keycloak.logout(window.location.origin);
    localStorage.setItem('access_token', "");
    localStorage.setItem('refresh_token', "");
  }
}