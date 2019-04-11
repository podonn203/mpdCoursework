//
// Name                 Patrick O'Donnell
// Student ID           S1714595
// Programme of Study   BSc Computing
//

package mdp.podonn203;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.Toolbar;
import android.content.Context;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import mdp.podonn203.R;

public class MainActivity extends AppCompatActivity {
//set variables and call url link

    private TextView rawDataDisplay;
    private Button startBurtton;
    private String result;
    private String url1="";
    private String urlSource="http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    private View mainView;

    InputStream urlSourceInputStream = new ByteArrayInputStream(urlSource.getBytes(StandardCharsets.UTF_8));
    private ListView eqListView;
    LinkedList<Earthquake> allEQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the raw links to the graphical components
        //rawDataDisplay = (TextView)findViewById(R.id.rawDataDisplay);
        //startButton = (Button)findViewById(R.id.startButton);
        //startButton.setOnClickListener(this);
        startProgress();
        mainView = (View)findViewById(R.id.mainView);
        mainView.setBackgroundColor(getResources().getColor(R.color.White,null));

    }

    public void onClick(View aview) {

        startProgress();
    }

    public void startProgress()
    {
        new Thread(new Task(urlSource)).start();
    }

    private class Task implements Runnable{

        private String url;

        public Task(String aurl)
        {
            url = aurl;
        }

        @Override
        public void run()
        {
            URL aurl;
            URLConnection yc;
            BufferedReader in;
            String inputLine="";

            try
            {
                //Continuous log statements to find where project breaks
                Log.e("MyTag", "in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                Log.e("MyTag", "in try 2");

                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                Log.e("MyTag","in try 3");

                while ((inputLine = in.readLine()) !=null)
                {
                    result = result + inputLine;
                    Log.e("My Tag", inputLine);
                    url1 = url1 + inputLine;
                }
                in.close();
            }
            catch (IOException ae)
            {
                Log.e("My Tag", "ioexception");
            }

            //Parse XML data

            PullParser parser = new PullParser();
            allEQ = parser.parse(url1);

            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Log.d("UIthread","I am the UI thread");

                    EarthquakeAdapter adapter= new EarthquakeAdapter(getApplicationContext(), allEQ);

                    final ListView eqListView =(ListView) findViewById(R.id.earthquakeList);

                    eqListView.setAdapter(adapter);

                    eqListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent page = new Intent(MainActivity.this,display_single.class);
                            page.putExtra("earthquake",eqListView.getItemAtPosition(i).toString());
                            startActivity(page);
                        }
                    });

                    }
                    });
        }
    }
}
