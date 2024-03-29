package vilallaugle.com.pi_copter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private static int leftAnalogBasex = 120;
    private static int leftAnalogBasey = 120;
    private static int rightAnalogBasex = 120;
    private static int rightAnalogBasey = 770;

    //angle of the left press from the left analog base
    double leftTheta;

    //is the screen pressed?
    boolean down = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //final ControlView control = new ControlView(this, 0, 0, 0, 0); 
        //final AnalogView analog = new AnalogView(this, leftAnalogBasex, leftAnalogBasey);
        //final AnalogView rightAnalog = new AnalogView(this, rightAnalogBasex, rightAnalogBasey);
        //analog.setBackgroundColor(Color.BLACK);
        //setContentView(analog);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
