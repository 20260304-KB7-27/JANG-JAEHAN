package org.scoula.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class BoardVO {

    private int no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
