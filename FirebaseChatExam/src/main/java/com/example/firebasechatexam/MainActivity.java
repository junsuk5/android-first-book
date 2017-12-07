package com.example.firebasechatexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_INVITE = 1000;

    public static final String MESSAGES_CHILD = "messages";
    private DatabaseReference mFirebaseDatabaseReference;
    private EditText mMessageEditText;

    private FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder> mFirebaseAdapter;
    private RecyclerView mMessageRecyclerView;

    // Firebase 인스턴스 변수
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    // Google
    private GoogleApiClient mGoogleApiClient;

    // 사용자 이름과 사진
    private String mUsername;
    private String mPhotoUrl;

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        ImageView messageImageView;
        TextView messengerTextView;
        CircleImageView messengerImageView;

        public MessageViewHolder(View v) {
            super(v);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            messageImageView = itemView.findViewById(R.id.messageImageView);
            messengerTextView = itemView.findViewById(R.id.messengerTextView);
            messengerImageView = itemView.findViewById(R.id.messengerImageView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // 인증이 안 되었다면 인증 화면으로 이동
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

        mMessageRecyclerView = findViewById(R.id.message_recycler_view);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .addApi(AppInvite.API)
                .build();


        // Firebase 리얼타임 데이터 베이스 초기화
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        mMessageEditText = findViewById(R.id.message_edit);
        // 보내기 버튼
        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatMessage chatMessage = new ChatMessage(mMessageEditText.getText().toString(),
                        mUsername,
                        mPhotoUrl,
                        null);
                mFirebaseDatabaseReference.child(MESSAGES_CHILD)
                        .push()
                        .setValue(chatMessage);
                mMessageEditText.setText("");
            }
        });


        mFirebaseAdapter = new FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>(
                ChatMessage.class,
                R.layout.item_message,
                MessageViewHolder.class,
                mFirebaseDatabaseReference.child(MESSAGES_CHILD)) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, ChatMessage model, int position) {
                viewHolder.messageTextView.setText(model.getText());
                viewHolder.messengerTextView.setText(model.getName());
                if (model.getPhotoUrl() == null) {
                    viewHolder.messengerImageView.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,
                            R.drawable.ic_account_circle_black_24dp));
                } else {
                    Glide.with(MainActivity.this)
                            .load(model.getPhotoUrl())
                            .into(viewHolder.messengerImageView);
                }
            }
        };

        // 리사이클러뷰에 레이아웃 매니저와 어댑터 설정
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecyclerView.setAdapter(mFirebaseAdapter);

        // Firebase Remote Config 초기화
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        // Firebase Remote Config 설정
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettings =
                new FirebaseRemoteConfigSettings.Builder()
                        .setDeveloperModeEnabled(true)
                        .build();

        // 인터넷 연결이 안 되었을 때 기본 값 정의
        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put("message_length", 10L);

        // 설정과 기본 값 설정
        mFirebaseRemoteConfig.setConfigSettings(firebaseRemoteConfigSettings);
        mFirebaseRemoteConfig.setDefaults(defaultConfigMap);

        // 원격 구성 가져오기
        fetchConfig();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mUsername = "";
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                return true;
            case R.id.invitation_menu:
                sendInvitation();
                return true;
            case R.id.crash_menu:
                throw new RuntimeException("치명적인 버그!!");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 원격 구성 가져오기
    public void fetchConfig() {
        long cacheExpiration = 3600; // 1시간
        // 개발자 모드라면 0초로 하기
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings()
                .isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 원격 구성 가져오기 성공
                        mFirebaseRemoteConfig.activateFetched();
                        applyRetrievedLengthLimit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 원격 구성 가져오기 실패
                        Log.w(TAG, "Error fetching config: " +
                                e.getMessage());
                        applyRetrievedLengthLimit();
                    }
                });
    }


    /**
     * 서버에서 가져 오거나 캐시된 값을 가져 옴
     */
    private void applyRetrievedLengthLimit() {
        Long messageLength =
                mFirebaseRemoteConfig.getLong("message_length");
        mMessageEditText.setFilters(new InputFilter[]{new
                InputFilter.LengthFilter(messageLength.intValue())});
        Log.d(TAG, "메시지 길이 : " + messageLength);
    }

    private void sendInvitation() {
        Intent intent = new AppInviteInvitation.IntentBuilder("초대 제목")
                .setMessage("채팅앱에 초대합니다")
                .setCallToActionText("채팅에 참여하기")
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // 얼마나 많은 초대를 보냈는지
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                Log.d(TAG, "Invitations sent: " + ids.length);
            } else {
                // 초대가 취소되거나 실패함
                Log.d(TAG, "Failed to send invitation.");
            }
        }
    }

}
