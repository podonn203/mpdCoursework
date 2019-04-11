//
// Name                 Patrick O'Donnell
// Student ID           S1714595
// Programme of Study   BSc Computing
//

package mdp.podonn203;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;
import android.content.Context;

import java.util.LinkedList;

//import static java.security.AccessController.getContext;

public class EarthquakeAdapter extends BaseAdapter {

    private Context earthquakeContext;
    private LinkedList<Earthquake> AllEarthquakes;

    //Picks colour depending on the magnitude level
    private int getMagnitudeColor(double Magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(Magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            case 10:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitudeneg;
                break;
        }
        return ContextCompat.getColor(earthquakeContext, magnitudeColorResourceId);
    }


    //Creates Constructor
    public EarthquakeAdapter(Context eqContext,LinkedList<Earthquake> eAllEQ){

        this.earthquakeContext = eqContext;
        this.AllEarthquakes = eAllEQ;

    }

    @Override
    public int getCount(){
        return AllEarthquakes.size();

    }

    //Get Item
    @Override
    public Object getItem(int position){return AllEarthquakes.get(position);}

    //Gets item ID
    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = LayoutInflater.from(earthquakeContext).inflate(R.layout.earthquake_item_list, parent, false);
        }

        TextView eqMagnitude = (TextView) convertView.findViewById(R.id.eqMagnitude);
        TextView eqLocation = (TextView) convertView.findViewById(R.id.eqLocation);
        TextView eqDate = (TextView) convertView.findViewById(R.id.eqDate);

        GradientDrawable magnitudeCircle = (GradientDrawable) eqMagnitude.getBackground();
       int magnitudeColor = getMagnitudeColor(AllEarthquakes.get(position).getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        //Text View
        eqDate.setText(String.format("Date / Time: %s", AllEarthquakes.get(position).getPubDate()));
        eqLocation.setText(String.format("LOCATION: %s", AllEarthquakes.get(position).getLocation()));
        eqMagnitude.setText(String.format("MAGNITUDE: %s", AllEarthquakes.get(position).getMagnitude()));

        return convertView;
    }
}

