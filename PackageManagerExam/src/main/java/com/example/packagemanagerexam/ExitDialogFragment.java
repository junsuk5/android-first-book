package com.example.packagemanagerexam;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by junsuk on 2017. 6. 29..
 */

public class ExitDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // AlertDialog 빌더 클래스를 이용해서 다이얼로그를 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("종료 확인");
        builder.setMessage("정말로 종료하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 종료 처리
            }
        });
        builder.setNegativeButton("취소", null);

        // 생성된 다이얼로그를 반환함
        return builder.create();
    }

}
