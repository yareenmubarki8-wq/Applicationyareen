package y.mubarki.applicationyareen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import y.mubarki.applicationyareen.Model.mytasksTable.MyTask;
import y.mubarki.applicationyareen.repositories.TaskRepository;

public class TaskViewModel extends AndroidViewModel
{
    private final TaskRepository repository;
    private final  LiveData<List<MyTask>> allTasks ;

    public TaskViewModel(@NonNull Application application){

        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }
public LiveData<List<MyTask>> getAllTasks(){
        return allTasks;
}
    public LiveData<List<MyTask>>getTasksByUserId(long userId){
        return repository.getTasksByUserId(userId);
}

public LiveData<MyTask> getTaskById(long taskId) {
        return repository.getTaskById(taskId);
}
    public LiveData<List<MyTask>> getTasksByTitle(String title) {
        return repository.getTasksByTitle(title);
    }
    public LiveData<List<MyTask>> getTasksByDescription(String description) {
        return repository.getTasksByDescription(description);
    }
    public LiveData<List<MyTask>> getTasksByPriority(int priority) {
        return repository.getTasksByPriority(priority);
    }
    public LiveData<List<MyTask>> getTasksByUserIdAndTitle(long userId, String title) {
        return repository.getTasksByUserIdAndTitle(userId, title);
    }
    public void insert(MyTask... tasks) {
        repository.insert(tasks);
    }
    public void update(MyTask... tasks) {
        repository.update(tasks);
    }
    public void updateTask(long taskId, String title, String description, int priority) {
        repository.updateTask(taskId, title, description, priority);
    }
    public void delete(MyTask... tasks) {
        repository.delete(tasks);
    }
    public void deleteTaskById(long taskId) {
        repository.deleteTaskById(taskId);
    }

}
