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


    public MyUser login(String email, String password) {
        return repository.login(email, password);
    }
public MyUser registerUser(MyUser user){

}

}
