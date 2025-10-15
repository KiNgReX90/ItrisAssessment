import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NavigationComponent } from './navigation/navigation.component';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(
    library: FaIconLibrary, 
    public router: Router,
    private userService: UserService
  ) {
    library.addIconPacks(fas);
  }

  // Prefetching users to increase initial load time over user-overview page.
  ngOnInit(): void {
    this.userService.getUsersSlice(0, 25).subscribe();
  }

  get currentTitle(): string {
    return this.router.url.split('/').pop() || '';
  }
}
