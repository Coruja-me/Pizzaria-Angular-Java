import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { HomeModule } from './modules/home/home.module';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeModule, RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'front-angular-pizzaria';
}
