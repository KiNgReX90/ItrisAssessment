import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'button[navButton]',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="select-none"
    [ngClass]="active ? 'text-base border-b border-green-400 flex items-center gap-2 pb-2 font-semibold text-green-400': 
    'text-base border-b border-transparent text-gray-500 flex items-center pb-2 gap-2 hover:text-green-400 hover:border-green-400 hover:border-b-green-400 hover:font-semibold transition-all duration-300'">
      <ng-content select="icon" />
      <ng-content />
    </div>
  `,
})
export class NavButton {
  @Input() active: boolean = false;
}
