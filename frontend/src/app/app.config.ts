import {
  ApplicationConfig,
  provideAppInitializer,
  provideBrowserGlobalErrorListeners,
  provideZoneChangeDetection,
  signal
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {EnvironmentConfiguration} from './core/models/environment-configuration';
import {Configuration} from './core/rest-client';
import {ConfigurationService, INJECT_CONFIG} from './core/services/configuration.service';
import {provideHttpClient} from '@angular/common/http';

const configPath = '/config/configuration.json';
const configuration = signal<EnvironmentConfiguration | null>(null);

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    provideAppInitializer(() => fetch(configPath)
      .then(res => res.json())
      .then((config: EnvironmentConfiguration) => {
        console.log('Config fetched: ', config);
        configuration.set(config);
      })),
    {
      provide: INJECT_CONFIG,
      useFactory: () => {
        console.log("Configuration", configuration())
        return configuration() as EnvironmentConfiguration;
      }
    },
    {
      provide: Configuration,
      useFactory: (configService: ConfigurationService) => {
        console.log('Backend URL:', configService.getBackendUrl());
        return new Configuration({
          basePath: configService.getBackendUrl()
        });
      },
      deps: [ConfigurationService],
      multi: false
    }
  ]
};
