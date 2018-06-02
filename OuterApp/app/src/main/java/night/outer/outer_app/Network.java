package night.outer.outer_app;

import android.app.Activity;
import android.provider.CalendarContract;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Network extends Activity {
    public String text = "";

    String Send_GET_Request(String url) {
        RequestQueue RequestQueue = Volley.newRequestQueue(this);
        StringRequest GetRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                text = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text = "Error";
            }
        });
        RequestQueue.add(GetRequest);
        return text;
    }

    String Send_POST_Request(String url, String payload) {
        final String payload1 = payload;
        RequestQueue RequestQueue = Volley.newRequestQueue(this);
        StringRequest PostRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                text = response;
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        text = "Error";
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(payload1, "");
                //params.put("domain", "http://itsalif.info");
                //Mai adaugi la nevoie
                return params;
            }
        };
        RequestQueue.add(PostRequest);
        return text;
    }

}
