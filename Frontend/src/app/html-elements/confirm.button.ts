import { Component, Input, HostBinding } from '@angular/core';

// Usage of hostbing to ensure that the disabled attribute is applied correctly.
// Usage of class binding to ensure that the class is being applied when the confirmButton attribute is used.
@Component({
  selector: 'button[confirmButton]',
  standalone: true,
  template: `<ng-content />`
})
export class ConfirmButton {
  @Input() 
  @HostBinding('disabled') 
  disabled: boolean = false;

  @HostBinding('class')
  get classes(): string {
    let className = 'py-2 px-4 border border-transparent text-white bg-gray-700 rounded-md select-none hover:bg-green-400 hover:text-black transition-all duration-300 flex justify-center disabled:bg-gray-600 disabled:cursor-not-allowed disabled:text-gray-400';
    if (!this.disabled) {
      className = className.replace(' border-transparent', ' border-green-400');
    }
    return className;
  }
}
