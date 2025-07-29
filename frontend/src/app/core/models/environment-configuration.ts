export type EnvironmentType = 'LOCAL' | 'DEV' | 'ACC' | 'PROD';

export interface EnvironmentConfiguration {
  environment: EnvironmentType;
  backendUri: string;
  backendBasePath: string;
}
