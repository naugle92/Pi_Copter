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
    //locations of the controls, the large circles
    private static float leftAnalogBasex = 220;
    private static float leftAnalogBasey = 220;
    private static float rightAnalogBasex = 220;
    private static float rightAnalogBasey = 1500;
    private static float baseAnalogRadius = 180;
    private Paint basepaint;

    public ControlView(Context context, int leftx, int lefty) {
        super(context);

        leftAnalogx = leftx;
        leftAnalogy = lefty;

        basepaint = new Paint();
        basepaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw the analog bases
        canvas.drawCircle(leftAnalogBasex, leftAnalogBasey, baseAnalogRadius, basepaint);
        canvas.drawCircle(rightAnalogBasex, rightAnalogBasey, baseAnalogRadius, basepaint);
    }
}
