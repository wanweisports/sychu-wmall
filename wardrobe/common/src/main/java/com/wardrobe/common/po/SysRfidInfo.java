package com.wardrobe.common.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/10/12.
 */
@Entity
@Table(name = "sys_rfid_info", schema = "")
public class SysRfidInfo {
    private int rfid;
    private String ip;
    private Integer port;
    private String type;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rfid")
    public int getRfid() {
        return rfid;
    }

    public void setRfid(int rfid) {
        this.rfid = rfid;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRfidInfo that = (SysRfidInfo) o;

        if (rfid != that.rfid) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rfid;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
