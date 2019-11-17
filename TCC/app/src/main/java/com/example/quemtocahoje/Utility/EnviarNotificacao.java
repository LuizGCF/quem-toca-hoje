package com.example.quemtocahoje.Utility;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.quemtocahoje.Notificação.MySingleton;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EnviarNotificacao {
    final static String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final static String serverKey = "";
    final static String contentType = "application/json";

    public static JSONObject CriarJsonNotificacao(String titulo, String mensagem, String topico)
    {
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", titulo);
            notifcationBody.put("message", mensagem);

            notification.put("to", topico);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e("JSONEXCEPTION", "onCreate: " + e.getMessage() );
        }
        return notification;
    }
    public static void EnviarNotificacao(JSONObject notification, Context ctx) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("JSONEXCEPTION", "onResponse: " + response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx, "Request error", Toast.LENGTH_LONG).show();
                        Log.i("JSONEXCEPTION", "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(ctx).addToRequestQueue(jsonObjectRequest);
    }
}
