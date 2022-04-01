package com.kdomo.Myhome.repository;

import com.kdomo.Myhome.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    public List<Board> findByTitleOrContent(String title,String content);
    public Page<Board> findAllByOrderByIdDesc(Pageable pageable);
    public Page<Board> findByTitleContainingOrContentContainingOrderByIdDesc(String title,String content,Pageable pageable);

}
