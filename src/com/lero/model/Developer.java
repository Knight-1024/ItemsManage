package com.lero.model;

public class Developer {

    private int developerId;
    private String userName;
    private String password;
    private int itemManaerId;
    private String name;
    private String sex;
    private String tel;

    private ItemManager itemManager;

    public Developer(){}

    public Developer(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public Developer(int developerId, String userName, String password, int itemManaerId, String name, String sex, String tel) {
        this.developerId = developerId;
        this.userName = userName;
        this.password = password;
        this.itemManaerId = itemManaerId;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getItemManaerId() {
        return itemManaerId;
    }

    public void setItemManaerId(int itemManaerId) {
        this.itemManaerId = itemManaerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
