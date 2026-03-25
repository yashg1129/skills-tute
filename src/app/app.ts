import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs/operators';
import { HeaderComponent } from './layout/header/header.component';
import { RightbarComponent } from './layout/rightbar/rightbar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { LeftbarComponent } from './layout/leftbar/leftbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    LeftbarComponent,
    RightbarComponent,
    FooterComponent,
    RouterOutlet
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  sidebarTitle = '';
  sidebarTopics: string[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.updateSidebarFromRoute();
      });

    this.updateSidebarFromRoute();
  }

  private updateSidebarFromRoute(): void {
    let route = this.activatedRoute;

    while (route.firstChild) {
      route = route.firstChild;
    }

    const data = route.snapshot.data;
    this.sidebarTitle = data['sidebarTitle'] || '';
    this.sidebarTopics = data['sidebarTopics'] || [];
  }
}