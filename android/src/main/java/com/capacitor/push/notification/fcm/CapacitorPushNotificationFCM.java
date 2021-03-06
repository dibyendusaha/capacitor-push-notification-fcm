package com.capacitor.push.notification.fcm;

import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

@NativePlugin()
public class CapacitorPushNotificationFCM extends Plugin {

    @PluginMethod()
    public void subscribeToTopic(final PluginCall call) {
        final String topic = call.getString("topic");

        FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        JSObject data = new JSObject();
                        data.put("message", "Subscribed to topic " + topic);
                        call.success();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        call.error("Can't subscribe to topic " + topic, e);
                    }
                });
    }

    @PluginMethod()
    public void unsubscribeFromTopic(final PluginCall call) {
        final String topic = call.getString("topic");

        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        JSObject data = new JSObject();
                        data.put("message", "Unsubscribed from topic " + topic);
                        call.success(data);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        call.error("Can't unsubscribe from topic " + topic, e);
                    }
                });
    }

    @PluginMethod()
    public void getToken(final PluginCall call) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        JSObject data = new JSObject();
                        data.put("token", instanceIdResult.getToken());
                        call.success(data);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        call.error(e.getLocalizedMessage());
                    }
                });
    }
}
