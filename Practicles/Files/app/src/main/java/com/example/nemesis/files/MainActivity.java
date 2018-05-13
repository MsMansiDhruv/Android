package com.example.nemesis.files;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextNotes;
    Button buttonSave,buttonDelete;

    File file;

    String line="",notes="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNotes = (EditText) findViewById(R.id.editTextNotes);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        file = new File(getFilesDir(),"Notes.txt");

        if(file.exists()){
            readFile();
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSave:
                writeFile();
                Toast.makeText(this, "Notes saved", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonDelete:
                if(file.exists()){
                    file.delete();
                    Toast.makeText(this, "Notes deleted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

   public void readFile(){
       try {
           FileInputStream fis = new FileInputStream(file);
           BufferedReader br = new BufferedReader(new InputStreamReader(fis));
           while((line = br.readLine()) != null){
                notes += line +"\n";
           }
           editTextNotes.setText(notes);
           br.close();
           fis.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public void writeFile(){
       try {
           FileOutputStream fos = new FileOutputStream(file);
           OutputStreamWriter write = new OutputStreamWriter(fos);
           write.append(editTextNotes.getText().toString());
           write.close();
           fos.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
}
