package y.mubarki.applicationyareen.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import y.mubarki.applicationyareen.data.AppDatabase;
import y.mubarki.applicationyareen.data.MyUserTable.MyUser;
import y.mubarki.applicationyareen.data.MyUserTable.MyUserQuery;

public class UserRepository {

    private MyUserQuery userQuery;
    private LiveData<List<MyUser>> allUsers;

    public UserRepository (Application application) {
        AppDatabase db = AppDatabase.getDB(application);
        userQuery = db.getMyUserQuery();
        allUsers = userQuery.getAll();
}
    public LiveData<List<MyUser>> getallUsers() {
        return allUsers;
    }
    public LiveData<List<MyUser>> getUsersByUserId(long userId) {
        return userQuery.getTasksByUserId(userId);
    }
    public LiveData<MyUser> getUserById(long taskId) {
        return U
    }


}
