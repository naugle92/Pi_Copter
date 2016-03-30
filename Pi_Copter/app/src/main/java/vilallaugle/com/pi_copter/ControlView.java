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
    private static float leftAnalogBasex = 220;
    private static float leftAnalogBasey = 220;
    private static float rightAnalogBasex = 220;
    private static float rightAnalogBasey = 1500;

    private static float analogBaseRadius = 180;
    private static float analogStickRadius = 30;

    private Paint basepaint;
    private Paint stickpaint;

    public ControlView(Context context, int leftx, int lefty, int rightx, int righty) {
        super(context);

        leftAnalogx  = leftAnalogBasex  + leftx;
        leftAnalogy  = leftAnalogBasey  + lefty;
        rightAnalogx = rightAnalogBasex + rightx;
        rightAnalogy = rightAnalogBasey + righty;

        basepaint = new Paint();
        basepaint.setColor(Color.GREEN);
        stickpaint = new Paint();
        stickpaint.setColor(Color.RED);
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
