package com.example.paintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {

    private static class Stroke {
        Path path;
        Paint paint;
        Stroke(Path p, Paint paint) {
            this.path = p;
            this.paint = paint;
        }
    }

    private final ArrayList<Stroke> strokes = new ArrayList<>();
    private Path currentPath;
    private Paint currentPaint;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        currentPaint = createPaint(0xFF000000); // чёрный по умолчанию
    }

    private Paint createPaint(int color) {
        Paint p = new Paint();
        p.setColor(color);
        p.setAntiAlias(true);
        p.setStrokeWidth(8f);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        return p;
    }

    public void setPaintColor(int color) {
        currentPaint = createPaint(color);
    }

    public void clear() {
        strokes.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Stroke s : strokes) {
            canvas.drawPath(s.path, s.paint);
        }
        if (currentPath != null) {
            canvas.drawPath(currentPath, currentPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath = new Path();
                currentPath.moveTo(x, y);
                strokes.add(new Stroke(currentPath, new Paint(currentPaint)));
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (currentPath != null) {
                    currentPath.lineTo(x, y);
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
                currentPath = null;
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }
}
