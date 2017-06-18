package io.github.xunuosi.tb.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xns on 2017/6/2.
 * 球队的实体类
 */
@Entity(nameInDb = "tb_team")
public class Team implements Parcelable {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String avator_team;
    private long teamId;

    @Generated(hash = 1760585996)
    public Team(Long id, String name, String avator_team, long teamId) {
        this.id = id;
        this.name = name;
        this.avator_team = avator_team;
        this.teamId = teamId;
    }

    @Generated(hash = 882286361)
    public Team() {
    }

    protected Team(Parcel in) {
        id = in.readLong();
        name = in.readString();
        avator_team = in.readString();
        teamId = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(avator_team);
        dest.writeLong(teamId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvator_team() {
        return this.avator_team;
    }

    public void setAvator_team(String avator_team) {
        this.avator_team = avator_team;
    }

    public long getTeamId() {
        return this.teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avator_team='" + avator_team + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
