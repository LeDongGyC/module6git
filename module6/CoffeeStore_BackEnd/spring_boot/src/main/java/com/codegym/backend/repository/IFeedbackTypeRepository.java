package com.codegym.backend.repository;

import com.codegym.backend.model.FeedbackType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFeedbackTypeRepository extends JpaRepository<FeedbackType, Integer> {
    @Query(value = "select * from feedback_type", nativeQuery = true)
    List<FeedbackType> selectAllList();

    @Query(value = "select * from feedback_type where id = :id", nativeQuery = true)
    FeedbackType findObject(@Param("id") int id);
}
