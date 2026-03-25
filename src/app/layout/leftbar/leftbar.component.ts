import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-leftbar',
  standalone: true,
  templateUrl: './leftbar.component.html',
  styleUrl: './leftbar.component.scss'
})
export class LeftbarComponent {
  @Input() title = '';
  @Input() topics: string[] = [];
}