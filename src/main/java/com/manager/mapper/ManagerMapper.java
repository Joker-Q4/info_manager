package com.manager.mapper;

import com.manager.entity.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {

    Manager queryManagerById(int account);
    int updateManager(Manager manager);
}
