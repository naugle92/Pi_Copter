package vilallaugle.com.pi_copter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Naugle on 3/30/16.
 * Purpose: create one analog controls for the four degrees
 * of freedom on the quadcopter
 */
class AnalogView extends View {

    //locations of the small circle within the analog control
    private float analogx;
    private float analogy;
    //locations of the controls, the large circles
    private float analogBasex = 120;
    private float analogBasey = 120;

    private static int leftAnalogBasex = 120;
    private static int leftAnalogBasey = 120;
    private static int rightAnalogBasex = 120;
    private static int rightAnalogBasey = 770;

    boolean down = false;

    double leftTheta;

    private static float analogBaseRadius = 100;
    private static float analogBaseRadius2 = 85;
    private static float analogStickRadius = 50;

    private Paint basepaint;
    private Paint basepaint2;

    private Paint stickpaint;

    public AnalogView(Context context, int xx, int yy) {
        super(context);

        analogBasex = xx;
        analogBasey = yy;

        analogx  = analogBasex;
        analogy  = analogBasey;

        basepaint = new Paint();
        basepaint.setColor(Color.GRAY);
        basepaint2 = new Paint();
        basepaint2.setColor(Color.BLACK);
        stickpaint = new Paint();
        stickpaint.setColor(Color.RED);
    }

    public void setPositions(int lx, int ly) {
        setAnalogx(lx);
        setAnalogy(ly);

        invalidate();
    }

    public void setAnalogx(float analogx) {
        this.analogx = analogx;
    }
    public void setAnalogy(float analogy) {
        this.analogy = analogy;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw the analog base
        canvas.drawCircle(analogBasex, analogBasey, analogBaseRadius, basepaint);
        canvas.drawCircle(analogBasex, analogBasey, analogBaseRadius2, basepaint2);

        //draw the movable circle that acts as a control
        canvas.drawCircle(analogx, analogy, analogStickRadius, stickpaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xx = event.getX();
        float yy = event.getY();
        //boolean down = false;
        double tempx = (xx - leftAnalogBasex) * (xx - leftAnalogBasex);
        double tempy = (yy - leftAnalogBasey) * (yy - leftAnalogBasey);
        //if you are touching within the circle, change the location
        if (down == true || (Math.sqrt(tempx + tempy) < 100 && event.getAction() == MotionEvent.ACTION_MOVE)) {
            //if we press and go out of bounds, then we should keep the control in bounds,
            //but still be able to follow the direction of the touch
            xx = event.getX();
            yy = event.getY();
            down = true;
            tempx = (xx - leftAnalogBasex) * (xx - leftAnalogBasex);
            tempy = (yy - leftAnalogBasey) * (yy - leftAnalogBasey);

            if (Math.sqrt(tempx + tempy) > 100) {

                if (xx >= leftAnalogBasex) {
                    leftTheta = (Math.atan((yy - leftAnalogBasey) / (xx - leftAnalogBasex)));
                } else {
                    leftTheta = (Math.atan((yy - leftAnalogBasey) / (xx - leftAnalogBasex))) + Math.PI;
                }

                tempy = 100 * Math.sin(leftTheta) + leftAnalogBasey;
                tempx = 100 * Math.cos(leftTheta) + leftAnalogBasex;

                setPositions((int) tempx, (int) tempy);
            } else {
                setPositions((int) xx, (int) yy);
            }
        }
        if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
            setPositions(leftAnalogBasex, leftAnalogBasey);
            down = false;
        }

        invalidate();
        return true;
        //return super.onTouchEvent(event);
    }
}