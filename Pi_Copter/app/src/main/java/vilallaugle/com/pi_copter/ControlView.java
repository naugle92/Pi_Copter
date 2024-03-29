package vilallaugle.com.pi_copter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Naugle on 3/30/16.
 * Purpose: create two analog controls for the four degrees
 * of freedom on the quadcopter
 */
class ControlView extends View {

    //locations of the small circle within the analog control
    private float leftAnalogx;
    private float leftAnalogy;
    private float rightAnalogx;
    private float rightAnalogy;
    //locations of the controls, the large circles
    private static float leftAnalogBasex = 120;
    private static float leftAnalogBasey = 120;
    private static float rightAnalogBasex = 120;
    private static float rightAnalogBasey = 770;

    private static float analogBaseRadius = 100;
    private static float analogStickRadius = 15;

    private Paint basepaint;
    private Paint stickpaint;

    public ControlView(Context context, int leftx, int lefty, int rightx, int righty) {
        super(context);

        leftAnalogx  = leftAnalogBasex  + leftx;
        leftAnalogy  = leftAnalogBasey  + lefty;
        rightAnalogx = rightAnalogBasex + rightx;
        rightAnalogy = rightAnalogBasey + righty;

        basepaint = new Paint();
        basepaint.setColor(Color.GRAY);
        stickpaint = new Paint();
        stickpaint.setColor(Color.RED);
    }

    public void setPositions(int lx, int ly, int rx, int ry) {
        setLeftAnalogx(lx);
        setLeftAnalogy(ly);
        setRightAnalogx(rx);
        setRightAnalogy(ry);

        invalidate();
    }

    public void setLeftAnalogx(float leftAnalogx) {
        this.leftAnalogx = leftAnalogx;
    }
    public void setLeftAnalogy(float leftAnalogy) {
        this.leftAnalogy = leftAnalogy;
    }
    public void setRightAnalogx(float rightAnalogx) {
        this.rightAnalogx = rightAnalogx;
    }
    public void setRightAnalogy(float rightAnalogy) {
        this.rightAnalogy = rightAnalogy;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw the analog bases
        canvas.drawCircle(leftAnalogBasex, leftAnalogBasey, analogBaseRadius, basepaint);
        canvas.drawCircle(rightAnalogBasex, rightAnalogBasey, analogBaseRadius, basepaint);
        canvas.drawCircle(leftAnalogx, leftAnalogy, analogStickRadius, stickpaint);
        canvas.drawCircle(rightAnalogx, rightAnalogy, analogStickRadius, stickpaint);
    }
}
