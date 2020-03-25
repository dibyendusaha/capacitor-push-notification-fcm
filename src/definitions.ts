declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorPushNotificationFCM: CapacitorPushNotificationFCMPlugin;
  }
}

export interface CapacitorPushNotificationFCMPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
}
