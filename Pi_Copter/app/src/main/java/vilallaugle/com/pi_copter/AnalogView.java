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
    private float rightanalogx;
    private float rightanalogy;
    //locations of the controls, the large circles
    private float analogBasex = 120;
    private float analogBasey = 120;

    private static int leftAnalogBasex = 120;
    private static int leftAnalogBasey = 120;
    private static int rightAnalogBasex = 120;
    private static int rightAnalogBasey = 770;

    boolean down = false;
    boolean rightdown = false;

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

        rightanalogx = rightAnalogBasex;
        rightanalogy = rightAnalogBasey;

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
    public void setrightPositions(int rx, int ry) {
        setrightAnalogx(rx);
        setrightAnalogy(ry);

        invalidate();
    }

    public void setAnalogx(float analogx) { this.analogx = analogx; }
    public void setAnalogy(float analogy) { this.analogy = analogy; }
    public void setrightAnalogx(float rightanalogx) { this.rightanalogx = rightanalogx; }
    public void setrightAnalogy(float rightanalogy) { this.rightanalogy = rightanalogy; }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw the analog base
        canvas.drawCircle(analogBasex, analogBasey, analogBaseRadius, basepaint);
        canvas.drawCircle(analogBasex, analogBasey, analogBaseRadius2, basepaint2);

        //draw the movable circle that acts as a control
        canvas.drawCircle(analogx, analogy, analogStickRadius, stickpaint);

        //draw the analog base
        canvas.drawCircle(rightAnalogBasex, rightAnalogBasey, analogBaseRadius, basepaint);
        canvas.drawCircle(rightAnalogBasex, rightAnalogBasey, analogBaseRadius2, basepaint2);

        //draw the movable circle that acts as a control
        canvas.drawCircle(rightanalogx, rightanalogy, analogStickRadius, stickpaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xx = event.getX();
        float yy = event.getY();
        //boolean down = false;
        double tempx = (xx - leftAnalogBasex) * (xx - leftAnalogBasex);
        double tempy = (yy - leftAnalogBasey) * (yy - leftAnalogBasey);
        double righttempx = (xx - rightAnalogBasex) * (xx - rightAnalogBasex);
        double righttempy = (yy - rightAnalogBasey) * (yy - rightAnalogBasey);
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
        System.out.println(Math.sqrt(righttempx + righttempy));
        if (rightdown == true || (Math.sqrt(righttempx + righttempy) < 100 && event.getAction() == MotionEvent.ACTION_MOVE)) {
            //if we press and go out of bounds, then we should keep the control in bounds,
            //but still be able to follow the direction of the touch
            xx = event.getX();
            yy = event.getY();
            System.out.println("got inside");
            rightdown = true;
            tempx = (xx - rightAnalogBasex) * (xx - rightAnalogBasex);
            tempy = (yy - rightAnalogBasey) * (yy - rightAnalogBasey);

            if (Math.sqrt(righttempx + righttempy) > 100) {

                if (xx >= rightAnalogBasex) {
                    leftTheta = (Math.atan((yy - rightAnalogBasey) / (xx - rightAnalogBasex)));
                } else {
                    leftTheta = (Math.atan((yy - rightAnalogBasey) / (xx - rightAnalogBasex))) + Math.PI;
                }

                tempy = 100 * Math.sin(leftTheta) + rightAnalogBasey;
                tempx = 100 * Math.cos(leftTheta) + rightAnalogBasex;

                setrightPositions((int) tempx, (int) tempy);
            } else {
                setrightPositions((int) xx, (int) yy);
            }
        }
        if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
            setPositions(leftAnalogBasex, leftAnalogBasey);
            setrightPositions(rightAnalogBasex, rightAnalogBasey);
            down = false;
            rightdown = false;
        }

        invalidate();
        return true;
        //return super.onTouchEvent(event);
    }
}