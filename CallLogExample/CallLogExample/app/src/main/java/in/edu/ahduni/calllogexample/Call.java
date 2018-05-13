package in.edu.ahduni.calllogexample;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by macbookair on 29/01/18.
 */

public class Call {

    String number;
    String dateTime;
    String duration;

    public Call() {
        number = dateTime = duration = "";
    }

    public Call(String number, String dateTime, String duration) {
        this.number = number;
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;

        SimpleDateFormat sdf =
                new SimpleDateFormat
                        ("dd-MM-yyyy HH:mm a");
        this.dateTime = sdf.format(new Date(Long.parseLong(dateTime)));
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Call{" +
                "number='" + number + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
