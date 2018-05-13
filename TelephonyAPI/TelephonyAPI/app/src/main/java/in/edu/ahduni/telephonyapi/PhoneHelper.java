package in.edu.ahduni.telephonyapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by macbookair on 01/02/18.
 */

public class PhoneHelper {

    Context context;
    PhoneHelper(Context context) {
        this.context = context;
    }

    void callPhoneNumber(String phoneNumber) {
        try {
            Intent i = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:"+"1111"));
            context.startActivity(i);
        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    String getPhoneDetails() {

        String phoneDetails = "";

        try {
            TelephonyManager manager = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            phoneDetails = "Device ID: " + manager.getDeviceId();
            phoneDetails += "\nSim Number: " + manager.getSimSerialNumber();
            phoneDetails += "\nNetwork Country ISO: "
                    + manager.getNetworkCountryIso();
            phoneDetails += "\nSim Country ISO: " + manager.getSimCountryIso();
            phoneDetails += "\nVoice Mail Number: " + manager.getVoiceMailNumber();
            phoneDetails += "\nLine Number: " + manager.getLine1Number();

            phoneDetails += "\nRoaming: ";
            if (manager.isNetworkRoaming())
                phoneDetails += "Yes";
            else
                phoneDetails += "No";

            phoneDetails += "\nPhone Type: ";
            switch (manager.getPhoneType()) {
                case TelephonyManager.PHONE_TYPE_CDMA:
                    phoneDetails += "CDMA";
                    break;
                case TelephonyManager.PHONE_TYPE_GSM:
                    phoneDetails += "GSM";
                    break;
                case TelephonyManager.PHONE_TYPE_SIP:
                    phoneDetails += "SIP";
                    break;
                case TelephonyManager.PHONE_TYPE_NONE:
                    phoneDetails += "NONE";
                    break;
            }

            phoneDetails += "\nSIM STATE: ";
            switch (manager.getSimState()) {
                case TelephonyManager.SIM_STATE_ABSENT:
                    phoneDetails += "Absent";
                    break;
                case TelephonyManager.SIM_STATE_NOT_READY:
                    phoneDetails += "Not Ready";
                    break;
                case TelephonyManager.SIM_STATE_READY:
                    phoneDetails += "Ready";
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    phoneDetails += "Unknown";
                    break;
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                    phoneDetails += "Network Locked";
                    break;
            }

        } catch(SecurityException e) {
            e.printStackTrace();
        }

        return phoneDetails;
    }

    void sendEmail(Email email) {

        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setData(Uri.parse("mailto:"));
            i.setType("text/plain");

            i.putExtra(Intent.EXTRA_EMAIL, new String []{email.getTo()});
            i.putExtra(Intent.EXTRA_CC, new String[] {email.getCc()});
            i.putExtra(Intent.EXTRA_BCC, new String[] {email.getBcc()});
            i.putExtra(Intent.EXTRA_SUBJECT, email.getSubject());
            i.putExtra(Intent.EXTRA_TEXT, email.getMessage());

            context.startActivity(Intent.createChooser(i, "Choose Email Application"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void sendSMS(SMS sms) {

        SmsManager manager = SmsManager.getDefault();
        String SENT_INTENT = "SMS_SENT";

        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(context, 1,
                        new Intent(SENT_INTENT), 0);

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (getResultCode() != Activity.RESULT_OK) {
                    Toast.makeText(context, "SMS Not Sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "SMS Sent Successfully !", Toast.LENGTH_SHORT).show();
                }
            }
        };

        context.registerReceiver(broadcastReceiver,
                new IntentFilter(SENT_INTENT));

        manager.sendTextMessage(sms.getPhoneNumber(), null,
                sms.getMessage(), pendingIntent, null);
    }
}
