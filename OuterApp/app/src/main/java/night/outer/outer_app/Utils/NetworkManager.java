package night.outer.outer_app.Utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkManager {
    private static final String TAG = "NetworkManager";
    private static NetworkManager instance = null;

    //for Volley API
    public RequestQueue requestQueue;

    private NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuf if you need
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance() {
        if (null == instance) {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void Send_GET_Request(String url, final SomeCustomListener<String> listener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG + ": ", "POST Response : " + response.toString());
                        if (null != response.toString())
                            listener.getResult(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse) {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.getResult("Error");
                        }
                    }
                }
        );
        requestQueue.add(request);
    }


    public void Send_POST_Request(Object param1, String url, final SomeCustomListener<String> listener) {
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("param1", param1);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG + ": ", "POST Response : " + response.toString());
                        if (null != response.toString())
                            listener.getResult(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse) {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.getResult("Error");
                        }
                    }
                });
        requestQueue.add(request);
    }

    public interface SomeCustomListener<T> {
        public void getResult(T object);
    }
}

/**
 * Requests can be handeled the simple way, though it requires using them from the contexts  where they are called,
 * not from a separate class (java problem solving 101 : Avoid the problem by doing it different)-Leon
 * <p>
 * public String text = "";
 * <p>
 * String Send_GET_Request(String url) {
 * String Send_GET_Request(String url) {
 * RequestQueue RequestQueue = Volley.newRequestQueue(this);
 * StringRequest GetRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
 *
 * @Override public void onResponse(String response) {
 * text = response;
 * }
 * }, new Response.ErrorListener() {
 * @Override public void onErrorResponse(VolleyError error) {
 * text = "Error";
 * }
 * });
 * RequestQueue.add(GetRequest);
 * return text;
 * }
 * }
 * <p>
 * String Send_POST_Request(String url, String payload) {
 * final String payload1 = payload;
 * RequestQueue RequestQueue = Volley.newRequestQueue(this);
 * StringRequest PostRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
 * @Override public void onResponse(String response) {
 * text = response;
 * }
 * },
 * new Response.ErrorListener() {
 * @Override public void onErrorResponse(VolleyError error) {
 * // error
 * text = "Error";
 * }
 * }
 * ) {
 * @Override protected Map<String, String> getParams() {
 * Map<String, String> params = new HashMap<String, String>();
 * params.put(payload1, "");
 * //params.put("domain", "http://itsalif.info");
 * //Mai adaugi la nevoie
 * return params;
 * }
 * };
 * RequestQueue.add(PostRequest);
 * return text;
 * }
 */