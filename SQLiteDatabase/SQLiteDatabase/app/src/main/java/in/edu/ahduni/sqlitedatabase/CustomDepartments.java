package in.edu.ahduni.sqlitedatabase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macbookair on 12/01/18.
 */

public class CustomDepartments extends ArrayAdapter<Department> {

    Activity a;
    ArrayList<Department> dList;

    public CustomDepartments(Activity a, ArrayList<Department> dList) {
        super(a, R.layout.custom_view_departments, dList);
        this.a = a;
        this.dList = dList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = a.getLayoutInflater().inflate(R.layout.custom_view_departments,
                null, true);

        TextView txtID = (TextView) listItem.findViewById(R.id.txtID);
        TextView txtCode = (TextView) listItem.findViewById(R.id.txtCode);
        TextView txtName = (TextView) listItem.findViewById(R.id.txtName);

        Department d = dList.get(position);
        txtID.setText("" + d.getId());
        txtCode.setText(d.getCode());
        txtName.setText(d.getName());

        return listItem;
    }

}
