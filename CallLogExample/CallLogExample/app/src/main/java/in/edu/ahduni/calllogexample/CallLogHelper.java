package in.edu.ahduni.calllogexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import java.util.ArrayList;

/**
 * Created by macbookair on 29/01/18.
 */

public class CallLogHelper {

    ContentResolver cr;

    CallLogHelper(ContentResolver cr) {
        this.cr = cr;
    }

    ArrayList<Call> getCallDetails(int type) {

        ArrayList<Call> callList = new ArrayList<>();

        Uri uri = Uri.parse(CallLog.Calls.CONTENT_URI + "");
        Cursor cursor = cr.query(uri, null, null, null,
                CallLog.Calls.DATE + " DESC");

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int t = cursor.getInt(
                        cursor.getColumnIndex(
                                CallLog.Calls.TYPE));

                if (t == type || type == 0) {

                    Call call = new Call();
                    call.setNumber(cursor.getString
                            (cursor.getColumnIndex(CallLog.Calls.NUMBER)));
                    call.setDateTime(cursor.getString(cursor.getColumnIndex
                            (CallLog.Calls.DATE)));
                    call.setDuration(cursor.getString
                            (cursor.getColumnIndex(CallLog.Calls.DURATION)) + " SEC");

                    callList.add(call);
                }
            }
        }

        return callList;
    }
}
