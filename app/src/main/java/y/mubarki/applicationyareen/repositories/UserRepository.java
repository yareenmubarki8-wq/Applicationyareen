package y.mubarki.applicationyareen.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import y.mubarki.applicationyareen.Model.AppDatabase;
import y.mubarki.applicationyareen.Model.MyUserTable.MyUser;
import y.mubarki.applicationyareen.Model.MyUserTable.MyUserQuery;

public class UserRepository {

    private MyUserQuery userQuery;
    private LiveData<List<MyUser>> allUsers;

    public UserRepository(Application application) {
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

    public LiveData<MyUser> getUserById(long userId) {
        return userQuery.getUserById(userId);
    }

    public void insert(MyUser myUser) {
        userQuery.insertAll(myUser);
    }

    public void update(MyUser... values) {
        userQuery.update(values);
    }
    public void delete(MyUser user){
        userQuery.delete(user);
    }
    public void delete(int id) {
        userQuery.delete(id);
    }
}