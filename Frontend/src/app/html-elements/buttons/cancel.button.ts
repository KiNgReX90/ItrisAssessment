import { Component } from '@angular/core';

@Component({
  selector: 'button[cancelButton]',
  standalone: true,
  template: `
  <button class="py-2 px-4 text-white bg-gray-600 rounded-md select-none hover:bg-red-900 hover:text-white transition-all duration-300 flex justify-center">
    <ng-content />
  </button>
  `
})
export class CancelButton {}
