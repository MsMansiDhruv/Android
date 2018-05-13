package in.edu.ahduni.contactscontentprovider;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macbookair on 30/01/18.
 */

public class CustomContactList extends ArrayAdapter<Contact> {

    Activity a;
    ArrayList<Contact> cList;

    CustomContactList(Activity a, ArrayList<Contact> cList) {
        super(a, R.layout.custom_contact_list, cList);
        this.a = a;
        this.cList = cList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = a.getLayoutInflater()
                .inflate(R.layout.custom_contact_list,
                        null, true);

        TextView txtName = (TextView) listItem.findViewById(R.id.txtName);
        TextView txtPhone = (TextView) listItem.findViewById(R.id.txtPhone);

        Contact contact = cList.get(position);
        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhoneNumber());

        return listItem;
    }
}
