package y.mubarki.applicationyareen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import y.mubarki.applicationyareen.Model.MyUserTable.MyUser;
import y.mubarki.applicationyareen.repositories.TaskRepository;
import y.mubarki.applicationyareen.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private  LiveData<List<MyUser>> allUsers ;
    public UserViewModel(@NonNull Application application){

        super(application);
        repository = new UserRepository(application);
    }

    public void insert(MyUser user) {
        repository.insert(user);
    }
    public MyUser getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    public LiveData<List<MyUser>> getAllUsers() {
        return allUsers;
    }
    public LiveData<List<MyUser>>getUsersByUserId(long userId){
        return repository.getUsersByUserId(userId);
    }
    public LiveData<MyUser> getUserById(long userId) {
        return repository.getUserById(userId);
    }

}
