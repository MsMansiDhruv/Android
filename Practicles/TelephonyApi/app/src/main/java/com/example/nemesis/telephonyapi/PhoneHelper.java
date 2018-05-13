package com.example.nemesis.telephonyapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.Toast;


/**
 * Created by Nemesis on 2/12/2018.
 */

public class PhoneHelper {

    Context context;

    PhoneHelper(Context context){
        this.context= context;
    }

    void CallPhone(String number){
        Toast.makeText(context, "Callphone", Toast.LENGTH_SHORT).show();
        try{

        Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:" +number));
        context.startActivity(i);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    String PhoneDetails(){
        String phoneDetails="";

        try {
            TelephonyManager manager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            phoneDetails = manager.getLine1Number()+"";
            phoneDetails += manager.getSimState()+"";
            switch (manager.getPhoneType()) {
                case TelephonyManager.PHONE_TYPE_CDMA:
                    phoneDetails += "CDMA";
                    break;
                case TelephonyManager.PHONE_TYPE_GSM:
                    phoneDetails += "GSM";
                    break;
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }
        Toast.makeText(context, phoneDetails, Toast.LENGTH_SHORT).show();
        return phoneDetails;
    }
}
