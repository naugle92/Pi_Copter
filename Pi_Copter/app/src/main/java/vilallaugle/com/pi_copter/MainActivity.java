package vilallaugle.com.pi_copter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import java.lang.Math;
import android.support.v4.view.GestureDetectorCompat;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static int leftAnalogBasex = 120;
    private static int leftAnalogBasey = 120;
    private int touchx;
    private int touchy;

    //angle of the left press from the left analog base
    double leftTheta;

    //is the screen pressed?
    boolean down = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //final ControlView control = new ControlView(this, 0, 0, 0, 0);
        final AnalogView leftAnalog = new AnalogView(this, leftAnalogBasex, leftAnalogBasey);
        leftAnalog.setBackgroundColor(Color.BLACK);
        setContentView(leftAnalog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*

            }
        });*/
        leftAnalog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //get the locations of the touch event and pass it into the view.
                float xx = event.getX();
                float yy = event.getY();
                //boolean down = false;
                double tempx = (xx-leftAnalogBasex)*(xx-leftAnalogBasex);
                double tempy = (yy-leftAnalogBasey)*(yy-leftAnalogBasey);
                //if you are touching within the circle, change the location
                if (down == true || (Math.sqrt(tempx + tempy) < 100 && event.getAction() == MotionEvent.ACTION_MOVE)) {
                    //if we press and go out of bounds, then we should keep the control in bounds,
                    //but still be able to follow the direction of the touch
                    xx = event.getX();
                    down = true;
                    yy = event.getY();
                    tempx = (xx-leftAnalogBasex)*(xx-leftAnalogBasex);
                    tempy = (yy-leftAnalogBasey)*(yy-leftAnalogBasey);

                    if (Math.sqrt(tempx + tempy) > 100) {

                        if (xx >= leftAnalogBasex) {
                            leftTheta = (Math.atan((yy - leftAnalogBasey) / (xx - leftAnalogBasex)));
                        } else {
                            leftTheta = (Math.atan((yy - leftAnalogBasey) / (xx - leftAnalogBasex))) + Math.PI;
                        }
                        System.out.println("got here too");

                        tempy = 100*Math.sin(leftTheta) + leftAnalogBasey;
                        tempx = 100*Math.cos(leftTheta) + leftAnalogBasex;

                        leftAnalog.setPositions((int) tempx, (int) tempy);
                    } else {
                        leftAnalog.setPositions((int) xx, (int) yy);
                    }
                }
                if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    leftAnalog.setPositions(leftAnalogBasex, leftAnalogBasey);
                    down = false;
                }
                return true;
            }
        });
        leftAnalog.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }




    /////////////////////// BEGIN GESTURES ////////////////////////////////
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    /////////////////////// END GESTURES ////////////////////////////

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchx = (int)event.getX();
        touchy = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        //control.setPositions(x, y, 0, 0);
        //return super.onTouchEvent(event);
        return false;
    }
*/

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
