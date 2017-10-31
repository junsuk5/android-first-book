package com.example.databaseexam;

import android.provider.BaseColumns;

/**
 * 메모 계약 클래스
 */
public final class MemoContract {

    // 인스턴스화 금지
    private MemoContract() {
    }

    // 테이블 정보를 내부 클래스로 정의
    public static class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENTS = "contents";
    }
}
