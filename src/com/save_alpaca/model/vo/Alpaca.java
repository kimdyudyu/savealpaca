package com.save_alpaca.model.vo;


public class Alpaca {
    private static String userName;
    private static String alpacaName;
    private static int lifetime = 50;
    private static int health = 50;
    private static int hunger = 50;
    private static int hygiene=0;

    public Alpaca() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        System.out.println(userName);
        this.userName = userName;
    }

    public String getAlpacaName() {
        return this.alpacaName;
    }

    public void setAlpacaName(String alpacaName) {
        System.out.println(alpacaName);
        this.alpacaName = alpacaName;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHygiene() {
        return hygiene;
    }

    public void setHygiene(int hygiene) {
    	
        this.hygiene = hygiene;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    // *** health, hygiene, hunger 컨트롤러들은
    // result 로 들어온 값이 양수이든 음수이든 받아서 저장할 수 있으므로
    // 모든 결과값은 연관된 각각의 control 메소드로 보낸다 !

    public void healthControl(int result) {
        this.health += result;
    }

    public void hygieneControl(int result) {
        this.hygiene += result;
    }

    public void hungerControl(int result) {
        this.hunger += result;
    }
}
