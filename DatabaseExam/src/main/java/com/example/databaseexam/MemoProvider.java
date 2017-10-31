package com.example.databaseexam;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MemoProvider extends ContentProvider {

    // 프로바이더 이름
    private static final String AUTHORITY = "com.example.databaseexam.provider";

    // content://com.example.databaseexam.provider/memo
    // 프로바이더의 memo 테이블
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"
            + MemoContract.MemoEntry.TABLE_NAME);

    // 1개의 아이템 요청 MIME 타입
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.example.databaseexam.provider." + MemoContract.MemoEntry.TABLE_NAME;
    // 여러 개의 아이템 요청 MIME 타입
    public static final String CONTENT_ALL_TYPE =
            "vnd.android.cursor.dir/vnd.com.example.databaseexam.provider." +
                    MemoContract.MemoEntry.TABLE_NAME;

    // UriMatcher 객체 생성
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int ALL = 1;
    public static final int ITEM = 2;

    static {
        // content://com.example.databaseexam.provider/memo
        sUriMatcher.addURI(AUTHORITY, MemoContract.MemoEntry.TABLE_NAME, ALL);
        // content://com.example.databaseexam.provider/memo/1 (#은 모든 숫자와 대응)
        sUriMatcher.addURI(AUTHORITY, MemoContract.MemoEntry.TABLE_NAME + "/#", ITEM);
    }

    private MemoDbHelper mMemoDbHelper;


    public MemoProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // URI 타입에 따라 조건 여부 결정
        switch (sUriMatcher.match(uri)) {
            case ALL:
                break;

            case ITEM:
                // uri의 # 뒤의 숫자 (_id)만 뽑아서 조건문을 완성
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;

            case UriMatcher.NO_MATCH:
                return 0;
        }

        SQLiteDatabase db = mMemoDbHelper.getWritableDatabase();
        int deleteCount = db.delete(MemoContract.MemoEntry.TABLE_NAME,
                selection,
                selectionArgs);

        if (deleteCount > 0) {
            // 상태가 변경됨을 ContentResolver에 통지
            getContext().getContentResolver().notifyChange(uri, null);
        }


        return deleteCount;
    }

    @Override
    public String getType(Uri uri) {
        // 이 프로바이더가 처리 할 수 있는 패턴인지 검사
        switch (sUriMatcher.match(uri)) {
            case ALL:
                return CONTENT_ALL_TYPE;
            case ITEM:
                return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (sUriMatcher.match(uri)) {
            case ALL:
                long id = mMemoDbHelper.getWritableDatabase()
                        .insert(MemoContract.MemoEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    // content://com.example.databaseexam.provider/#[id]
                    Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
                    // 상태가 변경됨을 ContentResolver에 통지
                    getContext().getContentResolver().notifyChange(returnUri, null);
                    return returnUri;
                }
                break;
        }
        return null;

    }

    @Override
    public boolean onCreate() {
        // 메모 DB 헬퍼의 인스턴스 초기화
        mMemoDbHelper = MemoDbHelper.getInstance(getContext());
        return true;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (sUriMatcher.match(uri)) {
            case ALL:
                break;

            case ITEM:
                // uri의 #뒤의 숫자 (_id)만 뽑아서 조건문을 완성
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase database = mMemoDbHelper.getReadableDatabase();
        Cursor cursor = database.query(MemoContract.MemoEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        // 커서를 감시대상으로 설정
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case ALL:
                break;

            case ITEM:
                // uri의 #뒤의 숫자 (_id)만 뽑아서 조건문을 완성
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;

            case UriMatcher.NO_MATCH:
                return 0;
        }

        SQLiteDatabase db = mMemoDbHelper.getWritableDatabase();

        int update = db.update(MemoContract.MemoEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (update > 0) {
            // 상태가 변경됨을 ContentResolver 에 통지
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return update;

    }
}
