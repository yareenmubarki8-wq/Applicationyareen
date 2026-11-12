package y.mubarki.applicationyareen.ViewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import y.mubarki.applicationyareen.R;

public class YourTaskActivity extends AppCompatActivity {

    private TextView tvWelcomeUser;
    private Button btnAddNewTask2;
    private Button btnSignOut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yourtask);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
        btnAddNewTask2 = findViewById(R.id.btnAddNewTask2);

        // Retrieve user name passed from SignUpActivity or SignInActivity
        String userName = getIntent().getStringExtra("userName");
        if (userName != null && !userName.isEmpty()) {
            tvWelcomeUser.setText("Welcome, " + userName + "!");
        }

        // Setup Toolbar Menu Item Listener
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        if (topAppBar != null)
        if (btnAddNewTask2 != null) {
            btnAddNewTask2.setOnClickListener(v -> {
                Intent intent = new Intent(YourTaskActivity.this, AddTaskActivity.class);
                startActivity(intent);
            });
        }
        if (btnSignOut2 != null) {
            btnSignOut2.setOnClickListener(v -> {
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(YourTaskActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            });
        }}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itmSettings) {
            Toast.makeText(this, "الإعدادات (Settings)", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.itmsSignOut) {
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(YourTaskActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


