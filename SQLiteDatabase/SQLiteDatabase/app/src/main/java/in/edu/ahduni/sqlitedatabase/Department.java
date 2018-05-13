package in.edu.ahduni.sqlitedatabase;

import java.io.Serializable;

/**
 * Created by macbookair on 11/01/18.
 */

public class Department implements Serializable {

    int id;
    String code, name;

    public Department() {
        id = 0;
        code = name = "";
    }

    public Department(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
