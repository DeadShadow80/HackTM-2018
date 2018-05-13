/*package night.outer.outer_app;

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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import night.outer.outer_app.FirstCustomize;

public class FirstCustomize extends Activity implements
        TextToSpeech.OnInitListener {
    /**
     * Called when the activity is first created.
     */

    private TextToSpeech tts;
    private Button btnSpeak,btn1,btn2;
    private ToggleButton tog;
    private EditText txtText;
    public Object[] mStringArray;
    String text;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_customize);

        tts = new TextToSpeech(this, this);
        btnSpeak = (Button) findViewById(R.id.button2);
        btn1=(Button) findViewById(R.id.button7);
        btn2=(Button) findViewById(R.id.button8);
        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                /*Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // Specify free form input
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                ArrayList results;
                results = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //mStringArray = results.toArray();
                //speakOut();*/
                doInBackground("deeznutz");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent=new Intent (getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
                    }
                }, 1);
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
        text="Hi there, I'm Outer, and welcome to my app. Let me help you with the setup. Press let's begin to continue";
        speakOut();

    }

    private void speakOut() {
        //String text =(String)mStringArray[0];
        //txtText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    public String doInBackground(String... params) {

        String urlString = "localhost:"; // URL to call

        String data = params[0]; //data to post

        OutputStream out = null;
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(out, "UTF-8"));

            writer.write(data);

            writer.flush();

            writer.close();

            out.close();

            urlConnection.connect();


        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
        return urlString;
    }

}