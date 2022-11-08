package com.mustache.bbs2.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//JPA에서 객체로 다루겠다는 선언
@NoArgsConstructor
@Getter
public class Article {
    @Id
    //@Entity가 붙어있다면 꼭 붙여줘야 한다. pk를 의미.
    @GeneratedValue
    //자동 완성
    //ID를 직접 생성하지 않고 자동으로 생성하도록 한 경우 붙인다.
    private Long id;

    private String title;
    private String contents;

    public Article(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
