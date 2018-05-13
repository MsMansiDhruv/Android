package in.edu.ahduni.contactscontentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstContacts;
    static final int READ_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstContacts = (ListView) findViewById(R.id.lstContacts);

        boolean canRead = askPermission(READ_ID,
                Manifest.permission.READ_CONTACTS);
        if(canRead)
            viewAllContacts();
    }

    void viewAllContacts() {
        ContactsHelper helper = new ContactsHelper(getContentResolver());
        ArrayList<Contact> cList = helper.getAllContacts();
        CustomContactList customContactList = new CustomContactList
                (MainActivity.this, cList);
        lstContacts.setAdapter(customContactList);
    }

    boolean askPermission(int requestId, String name) {
        if (Build.VERSION.SDK_INT >= 23) {

            int permission = ActivityCompat
                    .checkSelfPermission(this,name);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{name}, requestId);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            if (requestCode == READ_ID) {
                if (grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    viewAllContacts();
                }
            }
        }
    }
}
