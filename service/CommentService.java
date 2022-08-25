package com.sparta.service;



import com.sparta.dto.CommentRequestDto;
import com.sparta.model.Comment;
import com.sparta.model.Memo;
import com.sparta.repository.CommentRepository;
import com.sparta.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor //final로 선언한 변수가 있으면 꼭 생성해달라는 것
@Service
public class CommentService {


    private final CommentRepository commentRepository; // [2번]update메소드 작성 전에 id에 맞는 값을 찾으려면 find를 써야하는데 find를 쓰기위해서는 Repository가 있어야한다.
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @Secured("ROLE_USER")
    @Transactional
    public Comment addComment(Long id, CommentRequestDto commentRequestDto) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        String memberName = memoService.getNickname();
        Comment comment = new Comment(memo, memberName, commentRequestDto);
        commentRepository.save(comment);
        memo.addComment(comment);

        return comment;

    }

    @Transactional
    public Comment updateComment(Long id, Long commentId, CommentRequestDto commentRequestDto) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if(!memoService.getNickname().equals(memo.getMemberName())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        comment.setComment(commentRequestDto);
        return comment;
    }

    @Transactional
    public Boolean deleteComment(Long id, Long commentId) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        if(!memoService.getNickname().equals(memo.getMemberName())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        memo.deleteComment(comment);
        return true;

    }
}