package com.sparta.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.sparta.Timestamped;
import com.sparta.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    private Long id;


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String memberName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "MEMO_ID", nullable = false)
    private Memo memo;

//    @Builder
//    public Comment(String author, String content, Authority authority) { //
//        this.author = author;
//        this.content = content;
//        this.authority = authority;
//    }

    public Comment(Memo memo, String memberName, CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
        this.memo = memo;
        this.memberName = memberName;
    }

    public void setComment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
