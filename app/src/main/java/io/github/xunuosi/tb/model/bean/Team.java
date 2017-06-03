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
    @Generated(hash = 1218157628)
    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
