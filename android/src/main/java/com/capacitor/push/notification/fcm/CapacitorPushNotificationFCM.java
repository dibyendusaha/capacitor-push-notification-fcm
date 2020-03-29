package com.capacitor.push.notification.fcm;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

@NativePlugin()
public class CapacitorPushNotificationFCM extends Plugin {

    @PluginMethod()
    public void subscribeToTopic(final PluginCall call) {
        final String topic = call.getString("topic");

        FirebaseMessaging.getInstance().subscribeToTopic(topic).addOnSuccessListener(aVoid -> {
            JSObject ret = new JSObject();
            ret.put("message", "Subscribed to topic " + topic);
            call.success();
        });
    }

    @PluginMethod()
    public void unsubscribeFromTopic(final PluginCall call) {
        final String topic = call.getString("topic");

        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic).addOnSuccessListener(avoid -> {
            JSObject ret = new JSObject();
            ret.put("message", "Unsubscribed from topic " + topic);
            call.success(ret);
        }).addOnFailureListener(e -> call.error("Can't unsubscribe from topic " + topic, e));
    }

    @PluginMethod()
    public void getToken(final PluginCall call) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(getActivity(), instanceIdResult -> {
           JSObject data = new JSObject();
           data.put("token", instanceIdResult.getToken());
           call.success(data);
        }).addOnFailureListener(e -> call.error("Failed to get instance FirebaseID", e));
    }
}
