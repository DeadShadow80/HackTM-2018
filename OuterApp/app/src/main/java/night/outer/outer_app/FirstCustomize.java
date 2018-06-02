/*
SPEECH RECOGNIZE

package night.outer.outer_app;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

public class FirstCustomize extends AppCompatActivity {

    Button btnaudio;
    TextToSpeech.OnInitListener helper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_customize);


        btnaudio = (Button) findViewById(R.id.button2);
        btnaudio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // Specify free form input
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                ArrayList results;
                results = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                (String)mStringArray[0]


            }
        });

    }


}
*/
package night.outer.outer_app;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import night.outer.outer_app.Network;

import java.util.Locale;

public class FirstCustomize extends Activity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button btnSpeak, btn1, btn2;
    private ToggleButton tog;
    private EditText txtText;
    public Object[] mStringArray;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_customize);

        Send_GET_Request("https://www.google.com");
        /*
        Network a = null;
        a.Send_POST_Request("https://www.google.ro","deez_nutz");
        final TextView mTextView = (TextView) findViewById(R.id.text);
        mTextView.setText(a.Send_GET_Request("https://www.google.ro"));
        */


        tts = new TextToSpeech(this, this);
        btnSpeak = (Button) findViewById(R.id.button2);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**Text to speech*/
                /*Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // Specify free form input
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                ArrayList results;
                results = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //mStringArray = results.toArray();
                //speakOut();*/

                btn1 = (Button) findViewById(R.id.button7);
                btn2 = (Button) findViewById(R.id.button8);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                /**Switch to next activity*/
                /*
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent=new Intent (getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
                    }
                }, 1);*/
            }
        });
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        btnSpeak.setEnabled(true);
        tts.setLanguage(Locale.US);
        text = "Hi there, I'm Outer, and welcome to my app. Let me help you with the setup. Press let's begin to continue";
        speakOut();

    }

    private void speakOut() {
        //String text =(String)mStringArray[0];
        //txtText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


}