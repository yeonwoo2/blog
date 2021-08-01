package com.example.blog.repository;

import com.example.blog.dto.ReplySaveRequestDto;
import com.example.blog.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())"
            ,nativeQuery = true)
    int mSave(Integer userId, Integer boardId, String content); // 업데이트된 행의 개수를 리턴해줌.

    @Modifying
    @Query(value = "DELETE FROM reply WHERE boardId = ?1"
            ,nativeQuery = true)
    int mDelete(Integer boardId); // 업데이트된 행의 개수를 리턴해줌.

}
