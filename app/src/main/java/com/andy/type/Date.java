//日期类：根据当天日期,计算本周的开始日期与结束日期
package com.andy.type;

import java.util.Calendar;

public class Date {
	private Calendar calendar = Calendar.getInstance();
	private Calendar cal = Calendar.getInstance();
	private int year = calendar.get(Calendar.YEAR);
	private int month = calendar.get(Calendar.MONTH) + 1;
	private int day = calendar.get(Calendar.DAY_OF_MONTH);
	private int week = calendar.get(Calendar.DAY_OF_WEEK);
	private int max_month = calendar.getActualMaximum(Calendar.DATE);
	private int period = 1;

	public String getMonth(int add) {
		String string;
		if (month < 10) {
			string = new String(year + "-0" + (month+add));
		} else {
			string = new String(year + "-" + (month+add));
		}
		return string;
	}

	public String getEnd() {
		String string;
		int num = day + 7 - week + period;
		if (num > max_month) {
			string = new String(getMonth(1) + "-0" + (num - max_month));
		} else {
			if (num < 10) {
				string = new String(getMonth(0) + "-0" + num);
			} else {
				string = new String(getMonth(0) + "-" + num);
			}
		}
		return string;
	}

	public String getStart() {
		String string = "";
		cal.clear();
		cal.set(Calendar.YEAR, cal.get(Calendar.MONTH) - 1, 1);
		int max_nextmonth = cal.getActualMaximum(Calendar.DATE);
		int num = day - week + period;
		if (num < 1) {
			if ((month - 1 < 10) && (month - 1 > 0)) {
				string = new String(year + "-0" + (month - 1) + "-"
						+ (max_nextmonth + num));
			} else if (month - 1 < 1) {
				string = new String(year - 1 + "-" + (month - 1) + "-"
						+ (max_nextmonth + num));
			}
		} else {
			if (num < 10) {
				string = new String(getMonth(0) + "-0" + num);
			} else {
				string = new String(getMonth(0) + "-" + num);
			}
		}
		return string;
	}
}
