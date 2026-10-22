package y.mubarki.applicationyareen.TravelTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyTravel {

    @PrimaryKey(autoGenerate = true)
    /** رقم المهمة **/
    public long keyId;
    public int importance;

    /** عنوان قصير **/
    public String shortTitle;
    public String text;

    /** زمن بناء المهمة **/
    public long time;
    public long subId;

    public boolean isCompleted;

    /** رقم المستعمل الذي اضاف المهمة **/
    public long userId;
}
