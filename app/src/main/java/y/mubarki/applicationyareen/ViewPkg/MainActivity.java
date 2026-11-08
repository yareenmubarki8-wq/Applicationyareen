package y.mubarki.applicationyareen.ViewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import y.mubarki.applicationyareen.Model.AppDatabase;
import y.mubarki.applicationyareen.Model.mysubjectstable.MySubject;
import y.mubarki.applicationyareen.Model.mysubjectstable.MySubjectQuery;
import y.mubarki.applicationyareen.R;

public class MainActivity extends AppCompatActivity {
    private Button btnAddNewTask;
    private Button btnRegisterUser;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 1. بناء قاعدة بيانات وارجاع مؤشر عليها
        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        // 2. مؤشر لكائن عمليات لجدول
        MySubjectQuery subjectQuery = db.getMySubjectQuery();
        // 3. بناء كائن من نوع الجدول وتحديد قيم الصفات
        MySubject s1 = new MySubject();
        s1.setTitle("Math");
        MySubject s2 = new MySubject();
        s2.title = "Computers";
        // 4. اضافة كائن للجدول
        subjectQuery.insert(s1);
        subjectQuery.insert(s2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnAddNewTask = findViewById(R.id.btnAddNewTask);
        btnRegisterUser = findViewById(R.id.btnRegisterUser);
        btnSignIn = findViewById(R.id.btnSignIn);

        if (btnRegisterUser != null) {
            btnRegisterUser.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            });
        }

        if (btnSignIn != null) {
            btnSignIn.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(i);
            });
        }

        if (btnAddNewTask != null) {
            btnAddNewTask.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(i);
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itmSettings) {
            Toast.makeText(this, "الإعدادات (Settings)", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.itmsHistory) {
            Toast.makeText(this, "سجل الرحلات (History)", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.itmsSignOut) {
            Toast.makeText(this, "تسجيل الخروج (Sign Out)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
