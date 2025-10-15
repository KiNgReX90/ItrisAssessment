import { Component, HostBinding } from '@angular/core';

// Usage of class binding to ensure that the class is being applied when the cancelButton attribute is used.
@Component({
  selector: 'button[cancelButton]',
  standalone: true,
  template: `<ng-content />`
})
export class CancelButton {
  @HostBinding('class')
  get classes(): string {
    return 'py-2 px-4 text-white bg-gray-600 rounded-md select-none hover:bg-red-900 hover:text-white transition-all duration-300 flex justify-center';
  }
}
