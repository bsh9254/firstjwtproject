package com.sparta.repository;


import com.sparta.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();/*다 찾아줘(findAll) 정렬해줘(By OrderBy)수정된 날짜를 기준으로 (ModifiedAt) 내림차순으로=최신순 (Desc)  // Memo클래스는
    Timestamped클래스를 상속하고 있고 Timestamped는 modifiedAt을 필드에 멤버 변수로 갖고 있기 때문에 쓸 수 있다.*/

}