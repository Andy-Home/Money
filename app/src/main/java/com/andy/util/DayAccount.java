package com.andy.util;

import com.andy.type.Account;

import java.util.List;

/**
 * Created by andy on 16-3-25.
 */
public class DayAccount {
    private List<Account> accounts;

    public DayAccount(List<Account> accounts) {
        this.accounts = accounts;
    }

    public double getIncome(String date, int position) {
        double ans = 0;
        for (int i = position; (i < accounts.size())&&(accounts.get(i).getDate().equals(date)); i++) {
            if (accounts.get(i).getType().equals("0")) {
                ans += Double.parseDouble(accounts.get(i).getNumber());
            }

        }
        return ans;
    }

    public double getPay(String date, int position) {
        double ans = 0;
        for (int i = position; (i < accounts.size())&&accounts.get(i).getDate().equals(date); i++) {
            if (accounts.get(i).getType().equals("1")) {
                ans += Double.parseDouble(accounts.get(i).getNumber());
            }
        }
        return ans;
    }
}
