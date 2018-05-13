package in.edu.ahduni.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {

    Spinner spinDepartment;
    ListView lstStudents;
    DatabaseHelper helper;

    ArrayList<Department> departmentList;
    ArrayList<String> departmentNames, studentDetails;

    ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        spinDepartment = (Spinner) findViewById(R.id.spinDepartment);
        lstStudents = (ListView) findViewById(R.id.lstStudents);

        helper = new DatabaseHelper(getApplicationContext());
        departmentList = helper.getAllDepartments();

        //Department Names
        departmentNames = new ArrayList<String>();
        departmentNames.add("All Departments");
        for (int i=0; i<departmentList.size(); i++) {
            Department d = departmentList.get(i);
            departmentNames.add(d.getName());
        }

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                departmentNames);
        spinDepartment.setAdapter(spinAdapter);

        spinDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position != 0) {
                    Department selectedDepartment = departmentList.get(position - 1);
                    getDetailsOfStudents(selectedDepartment.getId());
                } else
                    getDetailsOfStudents(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getDetailsOfStudents(0);
            }
        });
    }

    void getDetailsOfStudents(int id) {
        //Get Student Details From Student List
        studentList = helper.getAllStudents(id);

        studentDetails = new ArrayList<>();
        for (int i=0; i< studentList.size(); i++) {
            Student s = studentList.get(i);
            studentDetails.add(s.getRollNo() + ". " + s.getName());
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>
                (getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, studentDetails);
        lstStudents.setAdapter(listAdapter);
    }
}
