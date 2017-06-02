package io.github.xunuosi.tb.model.bean;

/**
 * Created by xns on 2017/6/2.
 * 球员的实体类
 */

public class Player {
    private int id;
    private String sex;
    private String name;
    private int num;
    private int teamId;
    private String teamName;
    private String cardNum;
    private String position;

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
