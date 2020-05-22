package com.manager.service;

import com.manager.entity.Manager;

public interface ManagerService {

    Manager getManagerById(int account);
    boolean modifyManager(Manager manager);
}
