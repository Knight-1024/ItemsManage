package com.lero.model;

/**
 * @Description : 开发者实体
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class Developer {

    private Integer developerId;
    private String userName;
    private String password;
    private Integer itemManaerId;
    private String name;
    private String sex;
    private String tel;

    ItemManager itemManager;
    private String itemManagerName;

    public Developer() {
    }

    public Developer(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public Developer(Integer developerId, String userName, Integer itemManaerId, String name, String sex, String tel) {
        this.developerId = developerId;
        this.userName = userName;
        this.itemManaerId = itemManaerId;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
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

    public Integer getItemManaerId() {
        return itemManaerId;
    }

    public void setItemManaerId(Integer itemManaerId) {
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

    public String getItemManagerName() {
        return itemManagerName;
    }

    public void setItemManagerName(String itemManagerName) {
        this.itemManagerName = itemManagerName;
    }
}
