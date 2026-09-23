package y.mubarki.applicationyareen.data.mysubjectstable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MySubject {
    @PrimaryKey(autoGenerate = true)
    public long key_id;

    public String title;

    // Constrcutor فارغ
    public MySubject() {
    }

    // Constructor مع التلقيب
    public MySubject(String title) {
        this.title = title;
    }

    // Getters & Setters
    public long getKey_id() {
        return key_id;
    }

    public void setKey_id(long key_id) {
        this.key_id = key_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

