import {Component, inject, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HelloService} from './core/rest-client';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('angular-template');
  private readonly helloService = inject(HelloService);
  protected message = signal<string>('Hello World!');
  protected inputMessage: string = '';

  protected getHelloWorldMessage(): void {
    this.helloService.helloWorld(this.inputMessage).subscribe(msg => this.message.set(msg.message));
  }
}
