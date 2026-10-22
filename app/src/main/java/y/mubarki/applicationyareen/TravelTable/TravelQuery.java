package y.mubarki.applicationyareen.TravelTable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import y.mubarki.applicationyareen.Model.MyUserTable.MyUser;
import y.mubarki.applicationyareen.Model.mytasksTable.MyTask;

@Dao
public interface TravelQuery {
    @Query("SELECT * FROM MyTask ORDER BY importance DESC")
    LiveData<List<MyTask>> getAllTravels();
    @Query("SELECT * FROM MyTravel WHERE userId=:userid_p ORDER BY time DESC")
    LiveData<List<MyTask>> getAllTravelOrederBy(long userid_p);

    @Query("SELECT * FROM  WHERE userId=:userid_p AND isCompleted=:isCompleted_p " +
            "ORDER BY importance DESC")
    List<MyTask> getAllTravelOrederBy(long userid_p, boolean isCompleted_p);

    @Insert
    void insertTravel(MyTravel... t);

    @Update
    void updateTravel(MyTravel... travel);

    @Delete
// حذف
    void delete(MyTravel travel);
}
