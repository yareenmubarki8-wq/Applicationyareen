package y.mubarki.applicationyareen.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import y.mubarki.applicationyareen.Model.MyUserTable.MyUser;
import y.mubarki.applicationyareen.Model.MyUserTable.MyUserQuery;
import y.mubarki.applicationyareen.Model.mysubjectstable.MySubject;
import y.mubarki.applicationyareen.Model.mysubjectstable.MySubjectQuery;
import y.mubarki.applicationyareen.Model.mytasksTable.MyTask;
import y.mubarki.applicationyareen.Model.mytasksTable.MyTaskQuery;

@Database(entities = {MyUser.class, MySubject.class, MyTask.class}, version = 1)
/**
 * الفئة المسؤولة عن بناء قاعدة البيانات بكل جداولها
 * وتوفر لنا كائن للتعامل مع قاعدة البيانات
 */
public abstract class AppDatabase extends RoomDatabase {
    /**
     * كائن للتعامل مع قاعدة البيانات
     */
    private static AppDatabase db;

    /**
     * يعيد كائن لعمليات جدول المستخدمين
     * @return
     */
    public abstract MyUserQuery getMyUserQuery();

    /**
     * يعيد كائن لعمليات جدول الموضوع
     * @return
     */
    public abstract MySubjectQuery getMySubjectQuery();

    /**
     * يعيد كائن لعمليات جدول المهمات
     * @return
     */
    public abstract MyTaskQuery getMyTaskQuery();


    /**
     * بناء قاعدة البيانات واعادة كائن يؤشر عليها
     * @param context
     * @return
     */
    public static AppDatabase getDB(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                            AppDatabase.class,
                            "yareenDataBase") // اسم قاعدة البيانات
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }


}

