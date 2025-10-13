import { Component, Input, HostBinding } from '@angular/core';

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
    return 'py-2 px-4 text-white bg-gray-700 rounded-md select-none hover:bg-green-400 hover:text-black transition-all duration-300 flex justify-center disabled:bg-gray-600 disabled:cursor-not-allowed disabled:text-gray-400';
  }
}
