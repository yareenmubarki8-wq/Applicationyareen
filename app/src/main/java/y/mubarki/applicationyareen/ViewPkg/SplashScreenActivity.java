package y.mubarki.applicationyareen.ViewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import y.mubarki.applicationyareen.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        //الـ Handler في أندرويد هو أداة تُستخدم لإرسال وإدارة الرسائل أو المهام (Tasks) بين الخيوط (Threads) المختلفة داخل التطبيق.تأخير تنفيذ أمر معين:
        // تشغيل كود بعد مرور وقت محدد (مثل الانتقال لشاشة أخرى بعد 3 ثوانٍ).
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // إغلاق الشاشة الحالية إذا كنت لا تريد العودة إليها عند ضغط زر الرجوع
            }
        },3000); // 3000 مللي ثانية = 3 ثوانٍ
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}