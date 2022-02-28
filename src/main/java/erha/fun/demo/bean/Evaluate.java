package erha.fun.demo.bean;

import erha.fun.demo.utils.Tools;
import lombok.*;

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
    private String uid;
    private String tid;

    public Evaluate() {
    }

    public Evaluate(String content, Integer score, String uid, String tid) {
        this.content = content;
        this.score = score;
        setEid();
        this.uid = uid;
        this.tid = tid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void setEid() {
        this.eid = Tools.randomString(30);
    }
}
