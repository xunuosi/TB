package io.github.xunuosi.tb.model.bean;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xns on 2017/6/2.
 * 球员的实体类
 */
@Entity(nameInDb = "tb_player")
public class Player implements Parcelable {
    @Id(autoincrement = true)
    private Long id;
    private String avator_url;
    private String sex;
    private String name;
    private int num;
    private int teamId;
    private String teamName;
    private String cardNum;
    private String position;

    public Player() {
    }


    @Generated(hash = 1606293319)
    public Player(Long id, String avator_url, String sex, String name, int num,
            int teamId, String teamName, String cardNum, String position) {
        this.id = id;
        this.avator_url = avator_url;
        this.sex = sex;
        this.name = name;
        this.num = num;
        this.teamId = teamId;
        this.teamName = teamName;
        this.cardNum = cardNum;
        this.position = position;
    }


    protected Player(Parcel in) {
        id = in.readLong();
        avator_url = in.readString();
        sex = in.readString();
        name = in.readString();
        num = in.readInt();
        teamId = in.readInt();
        teamName = in.readString();
        cardNum = in.readString();
        position = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getAvator_url() {
        return this.avator_url;
    }


    public void setAvator_url(String avator_url) {
        this.avator_url = avator_url;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", avator_url='" + avator_url + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(avator_url);
        dest.writeString(sex);
        dest.writeString(name);
        dest.writeInt(num);
        dest.writeInt(teamId);
        dest.writeString(teamName);
        dest.writeString(cardNum);
        dest.writeString(position);
    }
}
