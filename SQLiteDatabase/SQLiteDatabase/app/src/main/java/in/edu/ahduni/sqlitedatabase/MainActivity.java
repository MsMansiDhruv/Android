package in.edu.ahduni.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btnAddDepartment, btnViewDepartment;
    Button btnAddStudent, btnViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddDepartment = (Button) findViewById(R.id.btnAddDepartment);
        btnViewDepartment = (Button) findViewById(R.id.btnViewDepartment);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
        btnViewStudent = (Button) findViewById(R.id.btnViewStudent);

        btnAddDepartment.setOnClickListener(this);
        btnViewDepartment.setOnClickListener(this);
        btnAddStudent.setOnClickListener(this);
        btnViewStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(MainActivity.this,
                AddDepartmentActivity.class);

        if (v.getId() == R.id.btnViewDepartment)
            i = new Intent(MainActivity.this,
                    ViewDepartmentActivity.class);
        else if(v.getId() == R.id.btnAddStudent)
            i = new Intent(MainActivity.this,
                    AddStudentActivity.class);
        else if (v.getId() == R.id.btnViewStudent)
            i = new Intent(MainActivity.this,
                    ViewStudentActivity.class);

        startActivity(i);
    }
}
