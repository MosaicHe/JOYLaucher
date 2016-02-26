package laucher.joy.com.JOYLauncher.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/12/15.
 */
public class NewImageView extends ImageView {


    private int borderwidth;
    private int co;

    public NewImageView(Context context) {
        super(context);
    }

    public NewImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect localRect = canvas.getClipBounds();
        localRect.bottom -= 1;
        localRect.right -= 1;
        Paint localPaint = new Paint();
        localPaint.setColor(this.co);
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setStrokeWidth(this.borderwidth);
        canvas.drawRect(localRect, localPaint);
    }

    public void setBorderWidth(int borderWidth) {
        this.borderwidth = borderWidth;
    }

    public void setColour(int colour) {
        this.co = colour;
    }
}
