package in.edu.ahduni.files;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ExternalFileActivity extends AppCompatActivity {

    EditText edtNotes;
    Button btnSave, btnDelete;

    File folder, file;
    String path;

    private static final int WRITE_ID  = 100, READ_ID= 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file);

        edtNotes = (EditText) findViewById(R.id.edtNotes);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSave.setEnabled(true);

        //if (!isExternalStorageAvailable())
            //btnSave.setEnabled(false);

        folder = new File(
                Environment.getExternalStorageDirectory()
                + File.separator + "Notes");

        path = folder.getAbsolutePath()
                        + File.separator + "Note.txt";
        file = new File(path);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean canWrite = askPermission(WRITE_ID,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (canWrite)
                    writeToFile();
            }
        });

        //Read
        boolean canRead = askPermission(READ_ID,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (canRead)
            readFile();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.exists()) {
                    file.delete();
                    folder.delete();
                    Toast.makeText(ExternalFileActivity.this,
                            "Notes Deleted Successfully !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state == Environment.MEDIA_MOUNTED
                && state != Environment.MEDIA_MOUNTED_READ_ONLY) {
            return true;
        }
        return false;
    }

    boolean askPermission(int requestId, String name) {

        if (Build.VERSION.SDK_INT >= 23) {
            int permission = ActivityCompat.
                    checkSelfPermission(this, name);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{name}, requestId);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult
            (int requestCode,
             @NonNull String[] permissions,
             @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            switch (requestCode) {
                case WRITE_ID:

                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //Write To File
                    }
                    break;

                case READ_ID:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //Read File
                    }
                    break;
            }
        }
    }

    void writeToFile() {
        try {
                if (!folder.exists())
                    folder.mkdir();

            FileOutputStream fout = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fout);
            writer.append(edtNotes.getText().toString());

            writer.close();
            fout.close();

            Toast.makeText(this, "Notes Saved !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void readFile() {
        try {
            FileInputStream fin = new FileInputStream(file);
            BufferedReader buf = new BufferedReader
                    (new InputStreamReader(fin));

            String line = "", note = "";
            while((line = buf.readLine()) != null) {
                note += line + "\n";
            }
            edtNotes.setText(note);

            fin.close();
            buf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
