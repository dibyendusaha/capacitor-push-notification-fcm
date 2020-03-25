
  Pod::Spec.new do |s|
    s.name = 'CapacitorPushNotificationFcm'
    s.version = '0.0.1'
    s.summary = 'Custom push notification plugin for Capacitor'
    s.license = 'MIT'
    s.homepage = 'https://github.com/dibyendusaha/capacitor-push-notification-fcm.git'
    s.author = 'Dibyendu Saha'
    s.source = { :git => 'https://github.com/dibyendusaha/capacitor-push-notification-fcm.git', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end