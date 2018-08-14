package aleksandrpolkin.ru.lesson7;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import aleksandrpolkin.ru.lesson7.data.Divorces;
import aleksandrpolkin.ru.lesson7.data.ObjectsData;

public class CheckTime {

    private List<Divorces> divorces;
    private final int OPEN_BRIGHT = 100;
    private final int CLOSE_BRIGHT = 101;
    private final int WAIT_BRIDGE = 102;
    private int bridge;

    int checkTimeDrawable(List<Divorces> divorces){
        this.divorces = divorces;
        checkBridge();
        if(bridge == OPEN_BRIGHT){
            return R.drawable.ic_brige_late;
        }else if(bridge == WAIT_BRIDGE){
            return R.drawable.ic_brige_soon;
        }else {
            return R.drawable.ic_brige_normal;
        }
    }

    int checkTimeBitmap(List<Divorces> divorces){
        this.divorces = divorces;
        checkBridge();
        if(bridge == OPEN_BRIGHT){
            return R.mipmap.ic_brige_late;
        }else if(bridge == WAIT_BRIDGE){
            return R.mipmap.ic_brige_soon;
        }else {
            return R.mipmap.ic_brige_normal;
        }
    }

     public  void checkBridge(){
         bridge = CLOSE_BRIGHT;
         Calendar cal = Calendar.getInstance();
         for (int i = 0; i < divorces.size(); i++)
         {
             cal.setTime(setTimeDateFormat(divorces.get(i).getStart())); // sets calendar time/date
             cal.add(Calendar.HOUR_OF_DAY, -1); // adds one hour
             if(setTimeDateFormat(getTimeLong()).compareTo(setTimeDateFormat(divorces.get(i).getStart())) >= 0){
                 if(setTimeDateFormat(getTimeLong()).compareTo(setTimeDateFormat(divorces.get(i).getEnd())) <= 0){
                     bridge = OPEN_BRIGHT;
                 }
             }else{
                 if(setTimeDateFormat(getTimeLong()).compareTo(cal.getTime()) >=0 && bridge != OPEN_BRIGHT){
                     bridge = WAIT_BRIDGE;
                 }
             }
         }
     }

    public String getOpenBridgeTime(List<Divorces> divorces){
        String time = "";
        for (int i = 0; i < divorces.size(); i++) {
            time = time + setStringDateTime(setTimeDateFormat(divorces.get(i).getStart())) + " - "
                    + setStringDateTime(setTimeDateFormat(divorces.get(i).getEnd())) + "  ";
        }
        return time;
    }

    Date setTimeDateFormat(String text) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("HH:mm:ss");
        Date docDate = null;
        try {
            docDate = format.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return docDate;
    }

    String setStringDateTime(Date date){
        return new SimpleDateFormat( "HH:mm", Locale.getDefault()).format(date);
    }

    public static String getTimeLong() {
        return new SimpleDateFormat( "HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }
}
