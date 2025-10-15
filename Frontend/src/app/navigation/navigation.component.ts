import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { routes } from '../app.routes';
import { NavButton } from '../html-elements/nav.button';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [CommonModule, RouterLink, NavButton, FontAwesomeModule],
  template: `
    <nav class="bg-black bg-opacity-80 py-12 px-12 min-h-full">
      <div class="flex-row justify-between items-center">
        @for (route of navRoutes; track route.path) {
          <div class="py-5">
            <button navButton [routerLink]="'/' + route.path" [active]="router.url === '/' + route.path">{{ route.title || route.path }}
              @if (route.data && route.data['icon']) {
                <fa-icon [icon]="route.data['icon']" ngProjectAs="icon"></fa-icon>
              }
            </button>
          </div>
        }
      </div>
    </nav>
  `,
  styles: `
    :host {
      display: block;
    }
  `
})
export class NavigationComponent {
  constructor(public router: Router) {}
  navRoutes = routes.filter(route => route.path && route.path !== 'users/:id/edit' && route.path !== '**');
}
