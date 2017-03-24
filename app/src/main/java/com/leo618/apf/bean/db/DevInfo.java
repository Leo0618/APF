package com.leo618.apf.bean.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * function:
 *
 * <p></p>
 * Created by lzj on 2017/3/24.
 */
@Entity(nameInDb = "devInfo")
public class DevInfo {
    @Id(autoincrement = true)
    private long id;

    private String packageName;

    @Generated(hash = 59495498)
    public DevInfo(long id, String packageName) {
        this.id = id;
        this.packageName = packageName;
    }

    @Generated(hash = 116176695)
    public DevInfo() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
