package y.mubarki.applicationyareen.data.mytasksTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyTask {

        @PrimaryKey(autoGenerate = true)
        /** رقم المهمة **/
        public long keyId;

        /** درجة الاهمية 1-5 **/
        public int importance;

        /** عنوان قصير **/
        public String shortTitle;

        /** نص المهمة **/
        public String text;

        /** زمن بناء المهمة **/
        public long time;

        /** هل تمت المهمة **/
        public boolean isCompleted;

        /** رقم موضوع المهمة **/
        public long subId;

        /** رقم المستعمل الذي اضاف المهمة **/
        public long userId;
    }

