package com.andy.type;

/**
 * Created by andy on 16-3-22.
 */
public class Account {

    /**
     * choose : 1
     * accountDao : null
     * user_id : 1
     * account_desc : test0
     * date : 2016-03-17
     * id : 2
     * type : 0
     * number : 1234
     */

    private String choose;
    private Object accountDao;
    private String user_id;
    private String account_desc;
    private String date;
    private String id;
    private String type;
    private String number;

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public Object getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(Object accountDao) {
        this.accountDao = accountDao;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccount_desc() {
        return account_desc;
    }

    public void setAccount_desc(String account_desc) {
        this.account_desc = account_desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
