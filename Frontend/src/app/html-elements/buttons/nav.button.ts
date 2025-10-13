import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'button[navButton]',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="select-none"
    [ngClass]="active ? 'p-2 border border-green-400 rounded-md flex items-center gap-2 bg-green-400 text-black': 
    'p-2 border border-green-400 text-green-400 rounded-md flex items-center gap-2 hover:bg-green-400 hover:text-black transition-all duration-500'">
      <ng-content select="icon" />
      <ng-content />
    </div>
  `,
})
export class NavButton {
  @Input() active: boolean = false;
}
