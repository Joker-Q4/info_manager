package com.manager.service.impl;

import com.manager.entity.Manager;
import com.manager.mapper.ManagerMapper;
import com.manager.service.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    @Resource
    ManagerMapper managerMapper;

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public Manager getManagerById(int account) {
        return managerMapper.queryManagerById(account);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean modifyManager(Manager manager) {
        return managerMapper.updateManager(manager) > 0;
    }
}
