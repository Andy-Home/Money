//1、创建继承SQLiteOpenHelper的CutepigDatabaseHelper类，方便调用SQLite
//2、创建Count表，用于存储收入项和收入项
package com.andy.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CutepigDatabaseHelper extends SQLiteOpenHelper {
	// set a const value for table count
	public static final String CREATE_COUNT = "create table Count ("
			+ "Id integer primary key autoincrement, " + "Date text, "
			+ "Number real, " + "Explain text, " + "Choose integer, "
			+ "Type integer)";

	public CutepigDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_COUNT);
		// db.execSQL(CREATE_PAY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists Count");
		db.execSQL("drop table if exists Income");
		onCreate(db);
	}
}
