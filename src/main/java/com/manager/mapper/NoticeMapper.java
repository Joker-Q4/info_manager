package com.manager.mapper;

import com.manager.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoticeMapper {

    @Select("select * from notice where id = 0")
    Notice queryNotice();

    @Update("update notice set content = #{content}, is_show = #{isShow} where id = 0")
    int updateNotice(Notice notice);
}
