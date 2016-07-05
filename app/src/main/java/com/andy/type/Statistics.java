/*统计类：用于统计day、week、month的金钱使用情况
context 方便对数据库的调用
function：Statistics(Context context)
					  getIncome(String day)
					  getPay(String day)
					  week_statistics(String start, String end)
					  week_statistics_pay(String start, String end)
					  week_statistics_income(String start, String end)
					  month_statistics(String month)
					  month_statistics_pay(String month)
					  month_statistics_income(String month)*/
package com.andy.type;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andy.SQLiteHelper.CutepigDatabaseHelper;

public class Statistics {

	private CutepigDatabaseHelper dbHelper;
	private Context context;

	public Statistics(Context context) {
		this.context = context;
	}

	public double getIncome(String day, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.rawQuery(
				"select * from Count where Date like ? and Choose like ?",
				new String[] { day, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 0) {
					ans += number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}

	public double getPay(String day, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.rawQuery(
				"select * from Count where Date like ? and Choose like ?",
				new String[] { day, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 1) {
					ans += number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}

	public double week_statistics(String start, String end, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db
				.rawQuery(
						"select * from Count where Date between ? and ?  and Choose like ?",
						new String[] { start, end, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 0) {
					ans += number;
				} else {
					ans -= number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}

	public double week_statistics_pay(String start, String end, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db
				.rawQuery(
						"select * from Count where Date between ? and ?  and Choose like ?",
						new String[] { start, end, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 1) {
					ans += number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}

	public double week_statistics_income(String start, String end, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db
				.rawQuery(
						"select * from Count where Date between ? and ?  and Choose like ?",
						new String[] { start, end, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 0) {
					ans += number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}

	public double month_statistics(String month, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		month = month.concat("%");
		Cursor cursor = db.rawQuery(
				"select * from Count where Date like ?  and Choose like ?",
				new String[] { month, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 0) {
					ans += number;
				} else {
					ans -= number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}

	public double month_statistics_pay(String month, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		month = month.concat("%");
		Cursor cursor = db.rawQuery(
				"select * from Count where Date like ?  and Choose like ?",
				new String[] { month, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 1) {
					ans += number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}

	public double month_statistics_income(String month, String choose) {
		double ans = 0.0;
		dbHelper = new CutepigDatabaseHelper(context, "CutePig_MoneyCount.db",
				null, 1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		month = month.concat("%");
		Cursor cursor = db.rawQuery(
				"select * from Count where Date like ?  and Choose like ?",
				new String[] { month, choose });
		if (cursor.moveToFirst()) {
			do {
				double number = cursor.getDouble(cursor
						.getColumnIndex("Number"));
				// int choose = cursor.getInt(cursor.getColumnIndex("Choose"));
				int type = cursor.getInt(cursor.getColumnIndex("Type"));

				if (type == 0) {
					ans += number;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ans;
	}
}
