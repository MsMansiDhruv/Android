package in.edu.ahduni.sqlitedatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by macbookair on 11/01/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME
            = "Student_Management";
    private static final int DATABASE_VERSION = 1;

    private static final String DEPARTMENT_TABLE = "Department";
    private static final String ID_COLUMN = "id";
    private static final String CODE_COLUMN = "code";
    private static final String NAME_COLUMN = "name";

    private static final String CREATE_DEPARTMENT_TABLE
            = "CREATE TABLE " + DEPARTMENT_TABLE + "(" +
                ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CODE_COLUMN + " TEXT, " +
                NAME_COLUMN + " TEXT" + ")";

    //Student Table
    private static final String ROLLNO_COLUMN = "rollno";
    private static final String DEPARTMENT_ID_COLUMN = "deptId";
    private static final String STUDENT_TABLE = "Student";

    private static final String CREATE_STUDENT_TABLE =
            "CREATE TABLE " + STUDENT_TABLE + "(" +
                    ROLLNO_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_COLUMN + " TEXT, " +
                    DEPARTMENT_ID_COLUMN + " INT, " +
                    "FOREIGN KEY (" + DEPARTMENT_ID_COLUMN + ") REFERENCES " +
                    DEPARTMENT_TABLE + " (" + ID_COLUMN + ") ON DELETE CASCADE)";


    public DatabaseHelper(Context context) {

        //Creation of Database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DEPARTMENT_TABLE);
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    void addDepartment(Activity a, Department d) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CODE_COLUMN, d.getCode());
        values.put(NAME_COLUMN, d.getName());

        try {
            long id = db.insertOrThrow(DEPARTMENT_TABLE, null, values);

            if (id > 0)
                Toast.makeText(a, "Department is Saved !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {0
            e.printStackTrace();
        }
    }

    ArrayList<Department> getAllDepartments() {

        ArrayList<Department> dList = new ArrayList<Department>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + DEPARTMENT_TABLE,
                null);
        if (c.getCount() > 0) {

            c.moveToFirst();
            do {

                Department d = new Department();
                d.setId(c.getInt(0));
                d.setCode(c.getString(1));
                d.setName(c.getString(2));

                dList.add(d);

            } while (c.moveToNext());
        }

        return dList;
    }

    void deleteDepartment(Activity a, Department d) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=1;");

        long id = db.delete(DEPARTMENT_TABLE,
                ID_COLUMN + "= ?",
                new String[]{Integer.toString(d.getId())});
        if (id > 0)
            Toast.makeText(a, "Department Deleted !", Toast.LENGTH_SHORT).show();
    }
    
    void updateDepartment(Activity a, Department d) {
        SQLiteDatabase db = getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(CODE_COLUMN, d.getCode());
        values.put(NAME_COLUMN, d.getName());
        
        long id = db.update(DEPARTMENT_TABLE, values, 
                ID_COLUMN +" = ?", 
                new String[]{Integer.toString(d.getId())});
        if (id > 0)
            Toast.makeText(a, "Department Updated !", Toast.LENGTH_SHORT).show();
    }

    void addStudent(Activity a, Student s) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=1;");

        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, s.getName());
        values.put(DEPARTMENT_ID_COLUMN, s.getDeptId());

        try {
            long id = db.insertOrThrow(STUDENT_TABLE, null, values);

            if (id > 0)
                Toast.makeText(a, "Student is Saved !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<Student> getAllStudents(int id) {
        ArrayList<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + STUDENT_TABLE;
        if (id != 0) {
            query = "SELECT * FROM " + STUDENT_TABLE
                    + " WHERE " + DEPARTMENT_ID_COLUMN + " = " + id;
        }

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            c.moveToFirst();

            do {
                Student s = new Student();
                s.setRollNo(c.getInt(c.getColumnIndex(ROLLNO_COLUMN)));
                s.setName(c.getString(c.getColumnIndex(NAME_COLUMN)));
                s.setDeptId(c.getInt(c.getColumnIndex(DEPARTMENT_ID_COLUMN)));
                studentList.add(s);
            } while (c.moveToNext());
        }
        return studentList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
