package y.mubarki.applicationyareen.ViewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import y.mubarki.applicationyareen.Model.AppDatabase;
import y.mubarki.applicationyareen.Model.MyUserTable.MyUser;
import y.mubarki.applicationyareen.Model.MyUserTable.MyUserQuery;
import y.mubarki.applicationyareen.R;

public class SignInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnSignIn, btnGoogleSignIn, btnAppleSignIn;
    private CheckBox cbRememberMe;
    private TextView tvForgotPassword;
    private MyUserQuery userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        userQuery = db.getMyUserQuery();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);
        btnAppleSignIn = findViewById(R.id.btnAppleSignIn);

        btnSignIn.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "رجاءً أدخل البريد الإلكتروني وكلمة المرور", Toast.LENGTH_SHORT).show();
                return;
            }

            MyUser user = userQuery.checkEmailPassw(email, password);
            if (user != null) {
                if (cbRememberMe.isChecked()) {
                    Toast.makeText(this, "تم حفظ خيار تذكرني", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this, "تم تسجيل الدخول بنجاح!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "البريد الإلكتروني أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
            }
        });

        tvForgotPassword.setOnClickListener(v -> {
            Toast.makeText(this, "الانتقال لإعادة ضبط كلمة المرور...", Toast.LENGTH_SHORT).show();
        });

        btnGoogleSignIn.setOnClickListener(v -> {
            Toast.makeText(this, "جاري تسجيل الدخول بواسطة Google...", Toast.LENGTH_SHORT).show();
        });

        btnAppleSignIn.setOnClickListener(v -> {
            Toast.makeText(this, "جاري تسجيل الدخول بواسطة Apple...", Toast.LENGTH_SHORT).show();
        });
    }
}

