package in.edu.ahduni.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class ViewDepartmentActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ListView lstDepartments;
    DatabaseHelper helper;
    ArrayList<Department> dList;
    CustomDepartments customDepartments;
    Department selectedDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_department);

        lstDepartments = (ListView) findViewById(R.id.lstDepartments);
        helper = new DatabaseHelper(getApplicationContext());

        lstDepartments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedDepartment = dList.get(position);

                //Popup Menu
                PopupMenu pm = new PopupMenu(ViewDepartmentActivity.this, view);
                pm.inflate(R.menu.custom_menu);
                pm.setOnMenuItemClickListener(ViewDepartmentActivity.this);
                pm.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        dList = helper.getAllDepartments();
        customDepartments = new CustomDepartments
                (ViewDepartmentActivity.this, dList);
        lstDepartments.setAdapter(customDepartments);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnUpdate:

                Intent i = new Intent(ViewDepartmentActivity.this,
                        AddDepartmentActivity.class);
                i.putExtra("selectedDepartment", selectedDepartment);
                startActivity(i);
                break;

            case R.id.btnDelete:
                helper.deleteDepartment(ViewDepartmentActivity.this,
                        selectedDepartment);
                dList.remove(selectedDepartment);
                customDepartments.notifyDataSetChanged();

                break;
        }

        return false;
    }
}
