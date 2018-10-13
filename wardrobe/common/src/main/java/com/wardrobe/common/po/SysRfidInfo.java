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
    private Integer workAntenna;
    private int did;
    private String name;

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

    @Basic
    @Column(name = "workAntenna")
    public Integer getWorkAntenna() {
        return workAntenna;
    }

    public void setWorkAntenna(Integer workAntenna) {
        this.workAntenna = workAntenna;
    }

    @Basic
    @Column(name = "did")
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
