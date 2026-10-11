package y.mubarki.applicationyareen.data.mytasksTable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyTaskQuery
{
    /**
     * إعادة جميع معطيات جدول المهمات
     * @return قائمة من المهمات *
     */
    @Query("SELECT * FROM MyTask ORDER BY importance DESC")
    LiveData< List<MyTask>> getAllTasks();

    /**
     * ارجاع المهمات حسب المستعمل وإذا انتهت أم لا ومرتبة تنازليًا حسب الأهمية *
     * @param userid_p رقم المستعمل *
     * @return
     */
    @Query("SELECT * FROM MyTask WHERE userId=:userid_p ORDER BY time DESC")
    LiveData<List<MyTask>> getAllTaskOrederBy(long userid_p);

    /**
     * ارجاع المهمات حسب المستعمل وإذا انتهت أم لا ومرتبة تنازليًا حسب الأهمية *
     * @param userid_p رقم المستعمل *
     * @param isCompleted_p هل تمت أم لا *
     * @return قائمة مهمات *
     */
    @Query("SELECT * FROM MyTask WHERE userId=:userid_p AND isCompleted=:isCompleted_p " +
            "ORDER BY importance DESC")
 List<MyTask> getAllTaskOrederBy(long userid_p, boolean isCompleted_p);

    @Insert
    void insertTask(MyTask... t); // ثلاثة نقاط تعني مجموعة

    /**
     * تعديل المهمات *
     * @param tasks * مجموعة مهمات للتعديل (التعديل حسب المفتاح الرئيسي)
     */
    @Update
    void updateTask(MyTask... tasks);

    /**
     * حذف مهمة او مهمات *
     * @param tasks * حذف المهمات (حسب المفتاح الرئيسي)
     */
    @Delete
    void deleteTask(MyTask...tasks);

    @Query("DELETE FROM MyTask WHERE keyId=:kid")
    void deleteTask(long kid);

    LiveData<MyTask> getTaskById(long taskId);

    LiveData<List<MyTask>> getTasksByTitle(String title);

    LiveData<List<MyTask>> getTasksByDescription(String description);

    LiveData<List<MyTask>> getTasksByPriority(int priority);

    LiveData<List<MyTask>> getTasksByUserIdAndTitle(long userId, String title);

    void insert(MyTask[] tasks);


    void update(MyTask[] tasks);

    void updateTask(long taskId, String title, String description, int priority);

    void delete(MyTask[] tasks);

    void deleteTaskById(long taskId);
}
