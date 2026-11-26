package y.mubarki.applicationyareen.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import y.mubarki.applicationyareen.Model.AppDatabase;
import y.mubarki.applicationyareen.Model.MyUserTable.MyUser;
import y.mubarki.applicationyareen.Model.MyUserTable.MyUserQuery;

public class UserRepository {
    private final FirebaseAuth mAuth;
    /**
     * كائن Firebase Firestore المسؤول عن قراءة وكتابة بيانات المستخدمين في قاعدة البيانات السحابية.   */
    private final FirebaseFirestore db;

    public UserRepository(Application application) {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }


    public LiveData<MyUser> registerUser(MyUser user) {// insert(MyUser user) او
        // المرحلة 1: إنشاء كائن LiveData قابل للتعديل لإرسال النتيجة لاحقاً
        MutableLiveData<MyUser> userLiveData = new MutableLiveData<>();
        // المرحلة 3: إنشاء حساب في Firebase Authentication بالبريد وكلمة المرور
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassw())
                .addOnCompleteListener(task -> { //هل تم التسجيل بنجاح
                    if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                        // المرحلة 4: استخراج UID الخاص بالمستخدم الجديد من Firebase Auth
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String uid = firebaseUser.getUid();
                        user.setKeyid(uid); // حفظ الـ UID داخل كائن المستخدم
                        // المرحلة 5: حفظ بيانات المستخدم كاملة في Firestore ضمن مجموعة "users"
                        // المستند يُنشأ بنفس الـ UID لضمان الربط بين Auth و Firestore
                        db.collection("users").document(uid).set(user)
                                .addOnSuccessListener(aVoid -> userLiveData.setValue(user)) // نجاح الحفظ
                                .addOnFailureListener(e -> userLiveData.setValue(null));     // فشل الحفظ
                    } else {
                        userLiveData.setValue(null);// فشل إنشاء الحساب في Authentication (بريد مكرر، كلمة مرور ضعيفة...)
                    }
                });
        // المرحلة 6: إرجاع LiveData فوراً - النتيجة ستصل لاحقاً عند اكتمال العمليات السحابية
        return userLiveData;
    }
    public LiveData<MyUser> login(String email, String password) {
        // المرحلة 1: إنشاء كائن LiveData قابل للتعديل لإرسال النتيجة لاحقاً
        MutableLiveData<MyUser> userLiveData = new MutableLiveData<>();
        // المرحلة 3: محاولة تسجيل الدخول في Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            //هل تم الدخول بنجاح؟
            if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                // المرحلة 4: استخراج UID الخاص بالمستخدم الذي سجل دخوله بنجاح
                String uid = mAuth.getCurrentUser().getUid();
                // المرحلة 5: جلب بيانات المستخدم الكاملة من Firestore
                // المستند يُقرأ من مجموعة "users" باستخدام الـ UID كمعرّف
                db.collection("users").document(uid).get()
                        .addOnCompleteListener(firestoreTask -> {
                            if (firestoreTask.isSuccessful() && firestoreTask.getResult() != null &&
                                    firestoreTask.getResult().exists()) {
                                // المرحلة 6أ: المستند موجود - تحويله إلى كائن MyUser وإرساله
                                MyUser fetchedUser = firestoreTask.getResult().toObject(MyUser.class);
                                userLiveData.setValue(fetchedUser);//هذه النتيجة ستصل لجواب ال observe
                            } else {
                                // المرحلة 6ب: فشل الجلب أو المستند غير موجود - إرسال null
                                userLiveData.setValue(null);//هذه النتيجة ستصل لجواب ال observe


                            }
                        });


            }