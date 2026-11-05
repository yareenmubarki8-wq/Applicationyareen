package y.mubarki.applicationyareen.ViewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import y.mubarki.applicationyareen.Model.MyUserTable.MyUser;
import y.mubarki.applicationyareen.R;
import y.mubarki.applicationyareen.repositories.UserRepository;
import y.mubarki.applicationyareen.viewmodel.UserViewModel;

public class SignUpActivity extends AppCompatActivity {

    private TextView userRegisterTitle;
    private EditText etFullName, etEmail, etPassword, etPhoneNumber;
    private Button btnRegister;
    private UserRepository userRepository;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userRepository = new UserRepository(getApplication());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userRegisterTitle = findViewById(R.id.tvSignUp);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "رجاءً إكمال جميع الحقول المطلوبة", Toast.LENGTH_SHORT).show();
                return;
            }

            MyUser user = new MyUser();
            user.setFullName(fullName);
            user.email = email;
            user.passw = password;
            user.phone = phone;

            userRepository.insert(user);
            Toast.makeText(this, "تم إنشاء الحساب بنجاح!", Toast.LENGTH_SHORT).show();
            finish();
        });

        signUpUser();
    }
    private void signUpUser() {
        // 1. جلب المدخلات من الحقول
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (email.isEmpty()){
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        if (password.isEmpty()){
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        MyUser user = userViewModel.login(email, password);
        if (user != null) {
            Toast.makeText(this, "Welcome, " + user.getFullName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }

    }

}
