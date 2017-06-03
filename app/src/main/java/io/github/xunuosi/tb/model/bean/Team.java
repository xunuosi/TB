package io.github.xunuosi.tb.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xns on 2017/6/2.
 * 球队的实体类
 */
@Entity(nameInDb = "tb_team")
public class Team {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String avator_team;

    @Generated(hash = 863188931)
    public Team(Long id, String name, String avator_team) {
        this.id = id;
        this.name = name;
        this.avator_team = avator_team;
    }
    @Generated(hash = 882286361)
    public Team() {
    }
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
}
