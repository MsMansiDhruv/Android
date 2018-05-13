package in.edu.ahduni.sqlitedatabase;

/**
 * Created by macbookair on 18/01/18.
 */

public class Student {

    int rollNo;
    String name;
    int deptId;

    public Student() {
        rollNo = deptId = 0;
        name = "";
    }

    public Student(int rollNo, String name, int deptId) {
        this.rollNo = rollNo;
        this.name = name;
        this.deptId = deptId;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
