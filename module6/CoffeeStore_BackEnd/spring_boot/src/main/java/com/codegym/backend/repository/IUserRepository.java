package com.codegym.backend.repository;

import com.codegym.backend.dto.IUserDto;
import com.codegym.backend.dto.IUserInforDTO;
import com.codegym.backend.dto.UserFindIdDTO;
import com.codegym.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {


    /**
     * ThangLV
     * get Information of User
     */
    @Query(value = "select user.id as id, user.name,account.user_name as userName, user.birthday as dateOfBirth, user.address, user.phone_number as phone,\n" +
            "              user.gender, user.salary, position.name as position, user.img_url as img from user\n" +
            "              join position on position.id = user.position_id\n" +
            "              join account on account.id = user.account_id where user.enable_flag =1 and account.user_name = ?", nativeQuery = true)
    IUserInforDTO findUserByUsername(String username);

    @Query(value = "SELECT * FROM user where account_id = ?1 and enable_flag = ?2", nativeQuery = true)
    User findByAccountId(int accountId, Boolean enableFlag);

    @Query(value = "SELECT name FROM user where account_id = ?1 and enable_flag = ?2", nativeQuery = true)
    String findNameByAccountId(int accountId, Boolean enableFlag);


    String JOIN_ACCOUNT = "join account a on u.account_id = a.id\n";
    String SELECT_USER_ITEM = "select u.id, a.user_name account, u.name userName, u.address, u.phone_number PhoneNumber, u.gender, u.birthday, u.enable_flag enableFlag, u.salary, p.name position  from user u\n";
    String JOIN_POSITION = "join position p on p.id = u.position_id\n";
    String ORDER_BY = "order by u.id";

    String SELECT_ALL_USER_SQL = SELECT_USER_ITEM + JOIN_ACCOUNT + JOIN_POSITION + ORDER_BY;

    @Query(value = SELECT_ALL_USER_SQL, countQuery = SELECT_ALL_USER_SQL, nativeQuery = true)
    Page<IUserDto> findAllList(Pageable pageable);

    @Query(value = SELECT_ALL_USER_SQL, countQuery = SELECT_ALL_USER_SQL, nativeQuery = true)
    List<IUserDto> findAllUser();

    String FIND_NAME_AND_BIRTHDAY_SQL = SELECT_USER_ITEM + JOIN_ACCOUNT + JOIN_POSITION +
            "where u.birthday = ? and u.name like ? \n" +
            ORDER_BY;
    @Query(value = FIND_NAME_AND_BIRTHDAY_SQL, countQuery = FIND_NAME_AND_BIRTHDAY_SQL, nativeQuery = true)
    Page<IUserDto> findUserByNameOrDate(Pageable pageable, String date, String name);

    String FIND_NAME_SQL = SELECT_USER_ITEM +
            "u.salary, p.name position  from user u \n" + JOIN_ACCOUNT +
            "join position p on p.id = u.position_id\n" +
            "where u.name like ? \n" +
            ORDER_BY;
    @Query(value = FIND_NAME_SQL, countQuery = FIND_NAME_SQL, nativeQuery = true)
    Page<IUserDto> findUserByName(Pageable pageable, String date);

    @Modifying
    @Query(value = "update user set enable_flag = 0 where id = ?", nativeQuery = true)
    void deleteById(int id);

    @Modifying
    @Query(value = "update user as u set u.name = ?1, u.address = ?2, u.phone_number = ?3, u.birthday = ?4, u.gender = ?5," +
            " u.salary = ?6, u.img_url = ?7, u.position_id = ?8 where u.id = ?9", nativeQuery = true)
    void editUser(String name,String address,String phoneNumber,String birthday,int gender,Double salary,String imgUrl,int position,int id);


    @Modifying
    @Query(value = "INSERT INTO user (name, address, phone_number, birthday, gender, salary, img_url, position_id,account_id, enable_flag)" +
            " values (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
    void createNewUser(String name,String address,String phoneNumber,String birthday,Integer gender,Double salary,String imgUrl,Integer position,Integer account,Boolean enableFlag);


    @Query(value = "select count(phone_number) from user where phone_number = ?", nativeQuery = true)
    Integer findByPhone(String phoneNumber);

    @Query(value = "select count(user_name) from account where user_name = :userName ", nativeQuery = true)
    Integer findByUserName(@Param("userName") String userName);

    @Query(value = "select count(email) from account where email = :email ", nativeQuery = true)
    Integer findByEmail(@Param("email") String email);


    @Query(value = "select user.id , a.user_name as username, user.name ,user.img_url as imgUrl, a.email, user.gender, user.birthday,user.address , user.phone_number as phoneNumber\n" +
            "            , user.position_id as position, user.salary from user \n" +
            "            inner join position as p on p.id = user.position_id \n" +
            "            inner join account as a on a.id = user.account_id \n" +
            "            where user.id = ?", nativeQuery = true)
    UserFindIdDTO getById(int id);

}
