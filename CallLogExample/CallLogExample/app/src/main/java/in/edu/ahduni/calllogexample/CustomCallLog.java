package in.edu.ahduni.calllogexample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by macbookair on 30/01/18.
 */

public class CustomCallLog extends ArrayAdapter<Call> {

    Activity a;
    ArrayList<Call> callList;

    CustomCallLog(Activity a, ArrayList<Call> callList) {
        super(a, R.layout.custom_call_list, callList);
        this.a = a;
        this.callList = callList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = a.getLayoutInflater()
                .inflate(R.layout.custom_call_list,
                        null, true);

        TextView txtPhone = (TextView) listItem.findViewById(R.id.txtPhone);
        TextView txtDateTimeDuration = (TextView) listItem.findViewById(R.id.txtDateTimeDuration);

        Call call = callList.get(position);
        txtPhone.setText(call.getNumber());
        txtDateTimeDuration.setText(call.getDateTime()
                + " Duration : " + call.getDuration());

        return listItem;



    }
}
