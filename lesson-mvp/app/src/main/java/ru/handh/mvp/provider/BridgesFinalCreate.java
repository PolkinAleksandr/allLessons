package ru.handh.mvp.provider;

import android.annotation.SuppressLint;
import android.text.Html;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.handh.mvp.R;
import ru.handh.mvp.data.model.Bridge;
import ru.handh.mvp.data.model.DivorceInfo;
import ru.handh.mvp.data.model.FinalBridge;

public class BridgesFinalCreate {
        private Bridge bridge;
        private final int OPEN_BRIGHT = 100;
        private final int CLOSE_BRIGHT = 101;
        private final int WAIT_BRIDGE = 102;
        private int bridgePic;

        public FinalBridge getFinalBridgeData(Bridge bridge){
            this.bridge = bridge;
            checkBridge(bridge.getDivorces());
            FinalBridge finalBridge = new FinalBridge();
            finalBridge.setPic(getBridgePic(bridgePic));
            finalBridge.setName(bridge.getName());
            finalBridge.setDescription(String.valueOf(Html.fromHtml(bridge.getDescription())));
            finalBridge.setTime(getOpenBridgeTime(bridge.getDivorces()));
            finalBridge.setPhotoClose(bridge.getPhotoClose());
            finalBridge.setPhotoOpen(bridge.getPhotoOpen());
            finalBridge.setId(bridge.getId());
            return finalBridge;
        }

        public List<FinalBridge> getFinalBridgesData(List<Bridge> bridges){
            List<FinalBridge> finalBridges = new ArrayList<>();
            FinalBridge finalBridge;
            Log.d("MyTag", String.valueOf(bridges.size()));
            for(int i = 0; i < bridges.size(); i++){
                    finalBridge = new FinalBridge();
                    checkBridge(bridges.get(i).getDivorces());
                    finalBridge.setPic(getBridgePic(bridgePic));
                    finalBridge.setName(bridges.get(i).getName());
                    finalBridge.setDescription(String.valueOf(Html.fromHtml(bridges.get(i).getDescription())));
                    finalBridge.setTime(getOpenBridgeTime(bridges.get(i).getDivorces()));
                    finalBridge.setPhotoClose(bridges.get(i).getPhotoClose());
                    finalBridge.setPhotoOpen(bridges.get(i).getPhotoOpen());
                    finalBridge.setId(bridges.get(i).getId());
                    finalBridges.add(finalBridge);
            }
            return finalBridges;
        }

        public  void checkBridge(List<DivorceInfo> divorces){
            bridgePic = CLOSE_BRIGHT;
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < divorces.size(); i++) {
                cal.setTime(setTimeDateFormat(divorces.get(i).getStart())); // sets calendar time/date
                cal.add(Calendar.HOUR_OF_DAY, -1); // adds one hour
                if (setTimeDateFormat(getTimeLong()).compareTo(setTimeDateFormat(divorces.get(i).getStart())) >= 0) {
                    if (setTimeDateFormat(getTimeLong()).compareTo(setTimeDateFormat(divorces.get(i).getEnd())) <= 0) {
                        bridgePic = OPEN_BRIGHT;
                    }
                } else {
                    if (setTimeDateFormat(getTimeLong()).compareTo(cal.getTime()) >= 0 && bridgePic != OPEN_BRIGHT) {
                        bridgePic = WAIT_BRIDGE;
                    }
                }
            }
        }

    public int getBridgePic(int bridgePic) {
        if(bridgePic == OPEN_BRIGHT){
            return R.drawable.ic_brige_late;
        }else if(bridgePic == WAIT_BRIDGE){
            return R.drawable.ic_brige_soon;
        }else {
            return R.drawable.ic_brige_normal;
        }
    }

    public String getOpenBridgeTime(List<DivorceInfo> divorces){
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
