package com.example.ancient_gs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "poetry.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "poetry";
    private static final String COLUMN_ROWID = "rowid";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_CONTENTS = "contents";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表的 SQL 语句
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ROWID + " INTEGER PRIMARY KEY, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_CONTENTS + " TEXT);";

        // 执行创建表的 SQL 语句
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 在此处执行数据库升级操作
        // 例如： db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        // onCreate(db);
    }

    // 插入数据的方法
    public void insertData(String title, String author, String contents) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_AUTHOR, author);
        values.put(COLUMN_CONTENTS, contents);

        // 插入数据
        db.insert(TABLE_NAME, null, values);

        // 关闭数据库连接
        db.close();
    }


    //查询诗句

    public String[] queryData() {
        SQLiteDatabase db = this.getReadableDatabase();

        // 定义要查询的列
        String[] projection = {
                COLUMN_ROWID,
                COLUMN_TITLE,
                COLUMN_AUTHOR,
                COLUMN_CONTENTS
        };

        // 定义查询条件，这里选择 rowid 为 1 的数据
        String selection = COLUMN_ROWID + "=?";
        // 创建 Random 对象
        Random random = new Random();

        // 生成一个0到500之间的随机数
        int randomNumber = random.nextInt(501);  // 501表示生成的随机数范围是[0, 500]
        // 将随机整数转换为字符串
        String randomString = String.valueOf(randomNumber);

        String[] selectionArgs = {randomString};

        // 执行查询
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String[] result = null;

        // 处理查询结果
        // 检查列名是否存在
        if (cursor.moveToFirst()) {

            int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
            int authorIndex = cursor.getColumnIndex(COLUMN_AUTHOR);
            int contentsIndex = cursor.getColumnIndex(COLUMN_CONTENTS);

            if (titleIndex != -1 && authorIndex != -1 && contentsIndex != -1) {
                result = new String[]{
                        cursor.getString(titleIndex),
                        cursor.getString(authorIndex),
                        cursor.getString(contentsIndex)
                };
            }
        }

        // 关闭 Cursor 和数据库连接
        cursor.close();
        db.close();

        return result;
    }
    public List<String[]> queryMultiplePoems(int numberOfPoems) {
        SQLiteDatabase db = this.getReadableDatabase();

        // 定义要查询的列
        String[] projection = {
                COLUMN_ROWID,
                COLUMN_TITLE,
                COLUMN_AUTHOR,
                COLUMN_CONTENTS
        };

        List<String[]> resultsList = new ArrayList<>();

        for (int i = 0; i < numberOfPoems; i++) {
            // 创建 Random 对象
            Random random = new Random();

            // 生成一个0到500之间的随机数
            int randomNumber = random.nextInt(501);  // 501表示生成的随机数范围是[0, 500]
            // 将随机整数转换为字符串
            String randomString = String.valueOf(randomNumber);

            String[] selectionArgs = {randomString};

            // 执行查询
            Cursor cursor = db.query(
                    TABLE_NAME,
                    projection,
                    COLUMN_ROWID + "=?",
                    selectionArgs,
                    null,
                    null,
                    null
            );

            // 处理查询结果
            if (cursor.moveToFirst()) {
                int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                int authorIndex = cursor.getColumnIndex(COLUMN_AUTHOR);
                int contentsIndex = cursor.getColumnIndex(COLUMN_CONTENTS);

                if (titleIndex != -1 && authorIndex != -1 && contentsIndex != -1) {
                    String[] result = new String[]{
                            cursor.getString(titleIndex),
                            cursor.getString(authorIndex),
                            cursor.getString(contentsIndex)
                    };
                    resultsList.add(result);
                }
            }

            // 关闭 Cursor
            cursor.close();
        }

        // 关闭数据库连接
        db.close();

        return resultsList;
    }




}
