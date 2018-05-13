package in.edu.ahduni.files;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    EditText edtNotes;
    Button btnSave, btnDelete, btnExternal;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNotes = (EditText) findViewById(R.id.edtNotes);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        //Internal Storage
        file = new File(getFilesDir(), "Notes.txt");

        if (file.exists())
            readFile();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Write To File
                writeToFile();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.exists()) {
                    file.delete();
                    Toast.makeText(MainActivity.this,
                            "Notes Deleted Successfully !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExternal = (Button) findViewById(R.id.btnExternalFile);
        btnExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ExternalFileActivity.class);
                startActivity(i);
            }
        });
    }

    void writeToFile() {
        try {
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
