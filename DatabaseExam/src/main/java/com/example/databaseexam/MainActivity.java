package com.example.databaseexam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int REQUEST_CODE_INSERT = 1000;
    private MemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        MemoActivity.class));

            }
        });

        ListView listView = (ListView) findViewById(R.id.memo_list);

        MemoDbHelper dbHelper = MemoDbHelper.getInstance(this);
        Cursor cursor = dbHelper.getReadableDatabase()
                .query(MemoContract.MemoEntry.TABLE_NAME,
                        null, null, null, null, null, null);

        mAdapter = new MemoAdapter(this, cursor);
        listView.setAdapter(mAdapter);

        // 리스트 클릭시 메모 내용 표시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                // 클릭한 시점의 아이템을 얻음
                Cursor cursor = (Cursor) mAdapter.getItem(position);
                // 커서에서 제목과 내용을 얻음
                String title = cursor.getString(cursor.getColumnIndexOrThrow
                        (MemoContract.MemoEntry.COLUMN_NAME_TITLE));
                String contents = cursor.getString(cursor.getColumnIndexOrThrow
                        (MemoContract.MemoEntry.COLUMN_NAME_CONTENTS));
                // 인텐트에 id와 함께 저장
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("contents", contents);
                // MemoActivity 를 시작
                startActivity(intent);
            }
        });

        // 아이템 롱 클릭 이벤트 정의
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
                                           long id) {
                final long deleteId = id;
                // 삭제할 것인지 다이얼로그 표시
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("메모 삭제");
                builder.setMessage("메모를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db =
                                MemoDbHelper.getInstance(MainActivity.this).getWritableDatabase();
//                        int deletedCount = db.delete(MemoContract.MemoEntry.TABLE_NAME,
//                                MemoContract.MemoEntry._ID + " = " + deleteId, null);
                        int deletedCount = getContentResolver().delete(MemoProvider.CONTENT_URI,
                                MemoContract.MemoEntry._ID + " = " + deleteId, null);

                        if (deletedCount == 0) {
                            Toast.makeText(MainActivity.this, "삭제에 문제가 발생하였습니다",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "메모가 삭제되었습니다",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
                return true;
            }
        });

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, MemoProvider.CONTENT_URI, null, null, null,
                MemoContract.MemoEntry._ID + " DESC");

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    private static class MemoAdapter extends CursorAdapter {
        public MemoAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView titleText = (TextView) view.findViewById(android.R.id.text1);
            titleText.setText(cursor.getString(cursor.getColumnIndexOrThrow
                    (MemoContract.MemoEntry.COLUMN_NAME_TITLE)));
        }
    }

}
