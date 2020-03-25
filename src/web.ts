import { WebPlugin } from '@capacitor/core';
import { CapacitorPushNotificationFCMPlugin } from './definitions';

export class CapacitorPushNotificationFCMWeb extends WebPlugin implements CapacitorPushNotificationFCMPlugin {
  constructor() {
    super({
      name: 'CapacitorPushNotificationFCM',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }
}

const CapacitorPushNotificationFCM = new CapacitorPushNotificationFCMWeb();

export { CapacitorPushNotificationFCM };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorPushNotificationFCM);
