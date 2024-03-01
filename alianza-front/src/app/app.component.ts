import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MainHeaderComponent } from './components/main-header/main-header.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [MainHeaderComponent, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'alianza-pt';
}
