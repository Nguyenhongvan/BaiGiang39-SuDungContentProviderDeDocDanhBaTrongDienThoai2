package com.vansuzy.baigiang39_sudungcontentproviderdedocdanhbatrongdienthoai;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vansuzy.model.Contact;

import java.util.ArrayList;

public class DanhBaActivity extends AppCompatActivity {
    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> adapterDanhBa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);

        addControls();

        showAllContactFromDevice();
    }

    private void showAllContactFromDevice() {
        Uri uriContact = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);
        dsDanhBa.clear();
        while (cursor.moveToNext()) {
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;

            int viTriTenCotName = cursor.getColumnIndex(tenCotName);
            int viTriTenCotPhone = cursor.getColumnIndex(tenCotPhone);

            String name = cursor.getString(viTriTenCotName);
            String phone = cursor.getString(viTriTenCotPhone);

            Contact contact = new Contact(name, phone);
            dsDanhBa.add(contact);
        }
        cursor.close();
        adapterDanhBa.notifyDataSetChanged();
    }

    private void addControls() {
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        adapterDanhBa = new ArrayAdapter<Contact>(
                DanhBaActivity.this,
                android.R.layout.simple_list_item_1,
                dsDanhBa
        );
        lvDanhBa.setAdapter(adapterDanhBa);
    }
}
