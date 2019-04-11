//
// Name                 Patrick O'Donnell
// Student ID           S1714595
// Programme of Study   BSc Computing
//

package mdp.podonn203;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class display_single extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlepost);

        TextView text = (TextView) findViewById(R.id.eqDepth);
        Bundle view = getIntent().getExtras();
        text.setText(view.getString("earthquake"));
    }
}
