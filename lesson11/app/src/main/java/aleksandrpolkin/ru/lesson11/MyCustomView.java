package aleksandrpolkin.ru.lesson11;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyCustomView extends View{

    Paint paint;
    Paint textDatePaint;
    Paint textPaint;
    private List<Integer> data;
    private int colorLine;
    private int colorDate;
    int countLine, wh, x, xDate, y, yKon, yMax, yFinish, yDate, xText, rxy;
    private List<String> date;
    String text;
    Rect rectDate;
    Rect rectData;
    int duration = 500;

    private void init() {
        yFinish = getHeight() / 10 * 2;
        textDatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textDatePaint.setColor(Color.LTGRAY);
        int spSize = 12;
        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spSize, getResources().getDisplayMetrics());
        textDatePaint.setTextSize(scaledSizeInPixels);
        date=new ArrayList<>();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        spSize = 14;
        scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spSize, getResources().getDisplayMetrics());
        textPaint.setTextSize(scaledSizeInPixels);
        rectDate = new Rect();
        rectData = new Rect();
    }


    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.MyCustomView,0,0);
        colorLine = a.getColor(R.styleable.MyCustomView_linesColor, Color.YELLOW);
        colorDate = a.getColor(R.styleable.MyCustomView_textColor, Color.LTGRAY);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getLenghtLine();
        rxy = 5;
        canvas.drawColor(Color.DKGRAY);
        wh = getWidth()/countLine;
        paint.setColor(colorLine);
        textPaint.setColor(colorLine);
        paint.setStrokeWidth(10);
        textDatePaint.setColor(colorDate);
        text = date.get(0);
        textDatePaint.getTextBounds(text, 0, text.length(), rectDate);
        text = data.get(0).toString();
        textDatePaint.getTextBounds(text, 0, text.length(), rectData);
        for(int i = 0; i < countLine; i++){
            if(i==0){
                x = wh/2;
                xDate = wh/2- rectDate.width()/2;
                xText = wh/2 - rectData.width()/3*2;
            }else if(i== colorLine-1){
                x = wh*i;
                xDate = wh*i- rectDate.width()/2;
                xText = wh*i- rectData.width()/3*2;
            }else {
                x = wh * i + wh / 2;
                xDate = wh * i + wh / 2 - rectDate.width()/2;
                xText = wh * i + wh / 2 - rectData.width()/3*2;
            }
            yDate = getHeight() / 10 * 9;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(x-5,  y - yFinish/yMax*data.get(i), x+5, y,rxy,rxy, paint);
            }else{
                canvas.drawLine(x,  y, x, y - yFinish/yMax*data.get(i), paint);
            }
            canvas.drawText(date.get(i), xDate, yDate, textDatePaint);
            canvas.drawText(data.get(i).toString(), xText, y - yFinish/yMax*data.get(i)-getHeight()/10, textPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int minw = getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 0);
        Log.d("LES", "w= " + w + ", h= " );
        setMeasuredDimension(w, (int) (w/1.93));
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
        if(data.size() >= 9){
            countLine = 9;
        }else {
            countLine = data.size();
        }
        for(int i = 0; i < countLine; i++){
           yMax = Math.max(yMax, data.get(i));
        }
        date = getDateWeek(countLine, date);
        invalidate();
    }

    @SuppressLint("SimpleDateFormat")
    List<String> getDateWeek(int count, List<String> date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -count+1);
        Date tomorrow = calendar.getTime();
        DateFormat df = new SimpleDateFormat("dd.MM");
        String text = df.format(tomorrow);
        date.add(text);
        for(int i = 0; i < count; i++){
            calendar.add(Calendar.DATE, 1);
            tomorrow = calendar.getTime();
            text = df.format(tomorrow);
            date.add(text);
        }
        return date;
    }


    @Override
    public ViewPropertyAnimator animate() {
        getLenghtLine();
        ValueAnimator animation = ValueAnimator.ofInt(0, y - yKon);
        animation.setDuration(duration);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yFinish = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animation.start();
        return super.animate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            animate();
        }
        return super.onTouchEvent(event);
    }

    void getLenghtLine(){
        y = getHeight()/10*8;
        yKon = getHeight() / 10 * 2;
    }
}
