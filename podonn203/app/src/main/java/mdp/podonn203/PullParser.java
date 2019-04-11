//
// Name                 Patrick O'Donnell
// Student ID           S1714595
// Programme of Study   BSc Computing
//

package mdp.podonn203;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

public class PullParser {

    LinkedList<Earthquake> allEarthquakes;
    private Earthquake earthquake;
    private String text;

    public PullParser (){
        allEarthquakes = new LinkedList<>();

    }

    //Gets the Earthquake Data
    public LinkedList<Earthquake> parse(String pDataToParse){
        //declare variables

        Boolean quakeStart = false;
        XmlPullParserFactory factory;
        XmlPullParser pparser;

        //Try and catch

        try {

            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            pparser= factory.newPullParser();
            pparser.setInput(new StringReader(pDataToParse));

            int eventType = pparser.getEventType();

            //Case statements

            while (eventType != XmlPullParser.END_DOCUMENT){
                String tagname = pparser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase( "item")){
                            earthquake= new Earthquake();
                        }
                        break;
                    case XmlPullParser.TEXT:

                        text = pparser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("image")){
                            quakeStart = true;
                        }
                        else if (tagname.equalsIgnoreCase("item")){
                            allEarthquakes.add(earthquake);
                        }
                        else if (tagname.equalsIgnoreCase("title") &&quakeStart == true) {
                            earthquake.setTitle(text);
                        }
                        else if (tagname.equalsIgnoreCase("description") &&quakeStart == true) {

                             String temp = text;
                             String[] array = temp.split(";");

                             earthquake.setLocation(array[1].substring(11));

                             earthquake.setLatLong(array[2].substring(11));

                             earthquake.setDepth(array[3].substring(7));

                            double d = Double.parseDouble((array[4].substring(11)));
                             earthquake.setMagnitude(d);

                            earthquake.setDescription(text);
                        }else if (tagname.equalsIgnoreCase("pubdate")){
                            earthquake.setPubDate(text);
                        }else if (tagname.equalsIgnoreCase("category")){
                            earthquake.setCategory(text);
                        }
                        else if (tagname.equalsIgnoreCase("lat")){
                            earthquake.setLatLong(text);
                        }
                        else if (tagname.equalsIgnoreCase("long")){
                            earthquake.setLatLong(earthquake.getLatLong() + "," + text);
                        }

                    default: break;
                }

                eventType = pparser.next();
            }
        }catch (XmlPullParserException ae1){
            //log statement for errors
            Log.e("XmlPullParserExeption", "item element");

        }catch (IOException ae1){
            Log.e("IOException","item element");
        }
        //returns allEarthquakes
        return allEarthquakes;
    }
}

