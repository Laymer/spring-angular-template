import {inject, Injectable, InjectionToken, signal} from '@angular/core';
import {EnvironmentConfiguration} from '../models/environment-configuration';

export const INJECT_CONFIG = new InjectionToken<EnvironmentConfiguration>('injectConfig');

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  private readonly injectedConfig = inject<EnvironmentConfiguration>(INJECT_CONFIG);
  public config = signal(this.injectedConfig);

  public getBackendUrl(): string {
    return this.config().backendUri + this.config().backendBasePath;
  }
}
