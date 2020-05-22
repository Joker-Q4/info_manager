package com.manager.mapper;

import com.manager.entity.Entrepreneur;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EntrepreneurMapper {

    List<Entrepreneur> queryEntrepreneur();
    List<Entrepreneur> queryOwnEntrepreneur(@Param("teacherAccount") int teacherAccount);
    List<Entrepreneur> queryEntrepreneurByTeacher(@Param("teacherAccount") int teacherAccount, @Param("currentState") int currentState);
    Entrepreneur queryEntrepreneurByAccount(@Param("account") int account);
    int insertEntrepreneur(Entrepreneur entrepreneur);
    int unBindTeacher(Entrepreneur entrepreneur);
    int updateEntrepreneur(Entrepreneur entrepreneur);
    int deleteEntrepreneur(int account);

}
