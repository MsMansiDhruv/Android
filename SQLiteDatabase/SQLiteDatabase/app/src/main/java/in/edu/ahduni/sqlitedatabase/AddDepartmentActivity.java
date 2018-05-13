package in.edu.ahduni.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDepartmentActivity extends AppCompatActivity {

    EditText edtDepartmentCode, edtDepartmentName;
    Button btnSave;
    DatabaseHelper helper;
    Department selectedDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        helper = new DatabaseHelper(getApplicationContext());

        edtDepartmentCode = (EditText) findViewById(R.id.edtDepartmentCode);
        edtDepartmentName = (EditText) findViewById(R.id.edtDepartmentName);
        btnSave = (Button) findViewById(R.id.btnSave);

        final boolean hasExtras = getIntent().hasExtra("selectedDepartment");

        //Update
        if (hasExtras) {
            selectedDepartment = (Department)
                    getIntent().getSerializableExtra("selectedDepartment");

            btnSave.setText("Update");

            edtDepartmentCode.setText(selectedDepartment.getCode());
            edtDepartmentName.setText(selectedDepartment.getName());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Department d = new Department();
                d.setCode(edtDepartmentCode.getText().toString());
                d.setName(edtDepartmentName.getText().toString());

                if (hasExtras) {
                    //Update
                    d.setId(selectedDepartment.getId());
                    helper.updateDepartment(AddDepartmentActivity.this, d);
                } else {
                    //Add
                    helper.addDepartment(AddDepartmentActivity.this, d);
                }
            }
        });
    }
}
