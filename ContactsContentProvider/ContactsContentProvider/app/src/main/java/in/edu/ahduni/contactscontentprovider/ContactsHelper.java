package in.edu.ahduni.contactscontentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by macbookair on 31/01/18.
 */

public class ContactsHelper {

    ContentResolver cr;

    ContactsHelper(ContentResolver cr) {
        this.cr = cr;
    }

    ArrayList<Contact> getAllContacts() {

        ArrayList<Contact> cList = new ArrayList<>();

        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null,
                null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                Contact contact = new Contact();

                contact.setId(cursor.getString(
                        cursor.getColumnIndex(
                                ContactsContract.Contacts._ID)));
                contact.setName(cursor.getString(
                        cursor.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME)));

                Cursor phoneCursor = cr.query(ContactsContract
                                .CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + "=?",
                        new String[] {contact.getId()},
                        null);

                Log.e("Phones", phoneCursor.getCount() + "");
                if (phoneCursor.getCount() > 0) {
                    String phoneNumbers = "";
                    while(phoneCursor.moveToNext()) {
                        Log.e("Number", phoneCursor.getString(
                                phoneCursor.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        phoneNumbers = phoneNumbers + phoneCursor.getString(
                                phoneCursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER)) + " ";
                    }
                    contact.setPhoneNumber(phoneNumbers);
                }
                cList.add(contact);
            }
        }

        return cList;
    }
}
