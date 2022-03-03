package erha.fun.demo.bean;

import erha.fun.demo.utils.Tools;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 2/28/22 9:23 PM
 */
@Getter
@EqualsAndHashCode
@ToString
public class Evaluate {
    private Long id;
    private String content;
    private Integer score;
    private String eid;
    private String sid;
    private String tid;
    private String cid;

    public Evaluate() {
    }

    public Evaluate(String content, Integer score) {
        this.content = content;
        this.score = score;
        setEid();
    }

    public Evaluate(String content, Integer score, String sid, String tid, String cid) {
        this.content = content;
        this.score = score;
        setEid();
        this.sid = sid;
        this.tid = tid;
        this.cid = cid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEid() {
        this.eid = Tools.randomString(30);
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
