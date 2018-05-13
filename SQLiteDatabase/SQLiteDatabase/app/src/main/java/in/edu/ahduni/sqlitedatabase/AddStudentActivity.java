package in.edu.ahduni.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    EditText edtName;
    Spinner spinDepartments;
    Button btnSave;
    DatabaseHelper helper;
    ArrayList<Department> departmentList;
    ArrayList<String> departmentNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtName = (EditText) findViewById(R.id.edtName);
        spinDepartments = (Spinner) findViewById(R.id.spinDepartments);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student s = new Student();
                s.setName(edtName.getText().toString());

                Department selectedDepartment = departmentList
                        .get(spinDepartments.getSelectedItemPosition());
                s.setDeptId(selectedDepartment.getId());

                helper.addStudent(AddStudentActivity.this, s);
            }
        });

        helper = new DatabaseHelper(getApplicationContext());
        departmentList = helper.getAllDepartments();

        //Department Names
        departmentNames = new ArrayList<String>();
        for (int i=0; i<departmentList.size(); i++) {
            Department d = departmentList.get(i);
            departmentNames.add(d.getName());
            //departmentNames.add(departmentList.get(i).getName());
        }

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                departmentNames);
        spinDepartments.setAdapter(spinAdapter);
    }
}
