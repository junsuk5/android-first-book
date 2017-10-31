package com.example.realmdbexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realmdbexam.models.User;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<Realm> {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mNewPassword;
    private TextView mResultText;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.email_edit);
        mPassword = (EditText) findViewById(R.id.password_edit);
        mNewPassword = (EditText) findViewById(R.id.new_password_edit);
        mResultText = (TextView) findViewById(R.id.result_text);

        mRealm = Realm.getDefaultInstance();
        mRealm.addChangeListener(this);

        showResult();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.removeAllChangeListeners();
        mRealm.close();
    }

    public void SignIn(View view) {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        User user = mRealm.where(User.class)
                .equalTo("email", email)
                .equalTo("password", password)
                .findFirst();

        if (user != null) {
            Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
        }
    }

    // 회원가입
    public void SignUp(View view) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                if (realm.where(User.class).equalTo("email", mEmail.getText().toString()).count() > 0) {
                    realm.cancelTransaction();
                }

                User user = realm.createObject(User.class);
                user.setEmail(mEmail.getText().toString());
                user.setPassword(mPassword.getText().toString());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "성공", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void deleteAccount(View view) {
        final RealmResults<User> results = mRealm.where(User.class).equalTo("email", mEmail.getText().toString()).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    public void updatePassword(View view) {
        final User user = mRealm.where(User.class).equalTo("email", mEmail.getText().toString()).findFirst();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                user.setPassword(mNewPassword.getText().toString());
            }
        });
    }

    private void showResult() {
        RealmResults<User> userList = mRealm.where(User.class).findAll();
        mResultText.setText(userList.toString());
    }

    @Override
    public void onChange(Realm element) {
        showResult();
    }
}
