package com.sparta.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.Timestamped;

import com.sparta.dto.MemberRequestDto;
import com.sparta.dto.MemberResponseDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member extends Timestamped {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    private Long id;
    @Column(nullable = false, unique = true)
    private String nickname;
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    @Builder
    public Member(String nickname, String password, Authority authority) {
        this.nickname = nickname;
        this.password = password;
        this.authority = authority;

    }

}