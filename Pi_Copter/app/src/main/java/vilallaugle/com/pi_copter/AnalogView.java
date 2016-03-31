package vilallaugle.com.pi_copter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private static float analogBaseRadius = 100;
    private static float analogStickRadius = 15;

    private Paint basepaint;
    private Paint stickpaint;

    public AnalogView(Context context, int xx, int yy) {
        super(context);

        analogBasex = xx;
        analogBasey = yy;

        analogx  = analogBasex;
        analogy  = analogBasey;

        basepaint = new Paint();
        basepaint.setColor(Color.GRAY);
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
        //draw the movable circle that acts as a control
        canvas.drawCircle(analogx, analogy, analogStickRadius, stickpaint);
    }
}