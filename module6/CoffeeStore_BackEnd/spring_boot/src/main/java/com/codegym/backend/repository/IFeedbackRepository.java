/**
 * IFeedbackRepository interface is used to interact with the database to get data
 *
 * @author TuLG
 * @version 1.0
 * @since 2023-06-13
 */

package com.codegym.backend.repository;

import com.codegym.backend.dto.FeedbackDetailDto;
import com.codegym.backend.dto.IFeedbackDto;
import com.codegym.backend.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

    String SELECT_ITEM = "select f.id, f.fb_id , f.name, f.email, ft.type, f.date, f.rate from feedback f\n";
    String ORDER_BY = "order by f.id asc";
    String LEFT_JOIN = "left join feedback_type ft on f.type_id = ft.id\n";

    @Modifying
    @Query(value = "insert into feedback(fb_id,name,email,date,content,type_id,rate) values (?1,?2,?3,?4,?5,?6,?7);", nativeQuery = true)
    void createFeedback(String fbId, String name,
                        String email, String date, String content,
                        int typeId, Integer rate);

    String SELECT_ALL_FEEDBACK_SQL = SELECT_ITEM + LEFT_JOIN + ORDER_BY;

    @Query(value = SELECT_ALL_FEEDBACK_SQL, countQuery = SELECT_ALL_FEEDBACK_SQL, nativeQuery = true)
    Page<IFeedbackDto> findAllList(Pageable pageable);

    String SELECT_FEEDBACK_DETAIL_SQL = "select f.rate, ft.type ,f.name, f.content from feedback f\n" + LEFT_JOIN + "where f.id = ?";

    @Query(value = SELECT_FEEDBACK_DETAIL_SQL, countQuery = SELECT_FEEDBACK_DETAIL_SQL, nativeQuery = true)
    FeedbackDetailDto findFeedbackById(int id);

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    @Query(value = "select MAX(id) from feedback", nativeQuery = true)
    Integer getLastInsert();

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    @Query(value = "select * from feedback where id = :id", nativeQuery = true)
    Feedback findObject(@Param("id") int id);

    @Query(value = "select count(email) from feedback where email = :email", nativeQuery = true)
    Integer selectCountEmail(@Param("email") String email);

    /**
     * @author DongPL
     * @version 2.0
     * @since 26/06/2023
     */
    String SELECT_FEEDBACK_IMG_SQL = "select fi.imgUrl from feedback f\n" +
            "left join feedback_img fi on f.id= fi.feedback_id\n" +
            "where f.id = ? \n" +
            "order by fi.id asc";

    @Query(value = SELECT_FEEDBACK_IMG_SQL, countQuery = SELECT_FEEDBACK_IMG_SQL, nativeQuery = true)
    List<String> findImgUrlById(int id);

    String SELECT_ALL_FEEDBACK_BY_RATE_SQL = SELECT_ITEM + LEFT_JOIN +
            "where f.date between ? and ?\n" +
            ORDER_BY;

    @Query(value = SELECT_ALL_FEEDBACK_BY_RATE_SQL, countQuery = SELECT_ALL_FEEDBACK_BY_RATE_SQL, nativeQuery = true)
    Page<IFeedbackDto> findListFeedbackByDate(Pageable pageable, String dateF, String dateT);

    String SELECT_ALL_FEEDBACK_BY_RATE_AND_DATE_SQL = SELECT_ITEM + LEFT_JOIN +
            "where f.rate = ? and f.date between ? and ?\n" +
            ORDER_BY;

    @Query(value = SELECT_ALL_FEEDBACK_BY_RATE_AND_DATE_SQL, countQuery = SELECT_ALL_FEEDBACK_BY_RATE_AND_DATE_SQL, nativeQuery = true)
    Page<IFeedbackDto> findListFeedbackByRateAndDate(Pageable pageable, String rate, String dateF, String dateT);
}
