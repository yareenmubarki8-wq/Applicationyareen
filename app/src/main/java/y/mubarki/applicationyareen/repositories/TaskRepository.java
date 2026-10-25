package y.mubarki.applicationyareen.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import y.mubarki.applicationyareen.Model.AppDatabase;
import y.mubarki.applicationyareen.Model.mytasksTable.MyTask;
import y.mubarki.applicationyareen.Model.mytasksTable.MyTaskQuery;

public class TaskRepository {

        private final MyTaskQuery taskQuery;//واجهة الاستعلامات
        private final LiveData<List<MyTask>> allTasks;//مبنى معطيات يحوي جميع المهلام المستخرجة

        public TaskRepository(Application application) {
            AppDatabase db = AppDatabase.getDB(application);
            taskQuery = db.getMyTaskQuery();
            allTasks = taskQuery.getAllTasks();
        }
        public LiveData<List<MyTask>> getAllTasks() {
            return allTasks;
        }
    public LiveData<List<MyTask>> getTasksByUserId(long userId) {
        return taskQuery.getAllTaskOrederBy(userId);
    }
    public LiveData<MyTask> getTaskById(long taskId) {
        return taskQuery.getTaskById(taskId);
    }
    public LiveData<List<MyTask>> getTasksByTitle(String title) {
        return taskQuery.getTasksByTitle(title);
    }
    public LiveData<List<MyTask>> getTasksByDescription(String description) {
        return taskQuery.getTasksByDescription(description);
    }
    public LiveData<List<MyTask>> getTasksByPriority(int priority) {
        return taskQuery.getTasksByPriority(priority);
    }
    public LiveData<List<MyTask>> getTasksByUserIdAndTitle(long userId) {
        return taskQuery.getTasksByUserIdAndTitle(userId);
    }
    public void insert(MyTask... tasks) {
        taskQuery.insert(tasks);
    }
    public void update(MyTask... tasks) {
        taskQuery.update(tasks);
    }
    public void updateTask(long taskId, String title, String description, int priority) {
        taskQuery.updateTask(taskId, title, description, priority);
    }
    public void delete(MyTask... tasks) {
        taskQuery.delete(tasks);
    }


    public void deleteTaskById(long taskId) {
        taskQuery.deleteTaskById(taskId);
    }
}
