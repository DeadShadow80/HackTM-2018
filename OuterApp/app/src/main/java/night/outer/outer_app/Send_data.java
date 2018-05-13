package night.outer.outer_app;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Send_data extends AsyncTask<String, String, String> {

    public Send_data(){
        //set context variables if required
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    public String doInBackground(String... params) {

        String urlString = "http://localhost:8000/"; // URL to call

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