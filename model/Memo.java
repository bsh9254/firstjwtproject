package com.sparta.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.Timestamped;

import com.sparta.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String memberName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  //부모가 삭제될 때 자식들도 다 삭제되는 어노테이션
    @JsonManagedReference //DB연관관계 무한회귀 방지
    private List<Comment> commentList;


    public Memo(MemoRequestDto requestDto, String memberName) {
        this.memberName = memberName;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }


    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
    public void deleteComment(Comment comment){
        commentList.remove(comment);
//    commentId로 받고 commentList.removeIf(comment -> comment.getId().equals(commentId)); 해도 제거는 된다.
    }
}
