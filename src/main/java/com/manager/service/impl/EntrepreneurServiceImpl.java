package com.manager.service.impl;

import com.manager.entity.Entrepreneur;
import com.manager.mapper.EntrepreneurMapper;
import com.manager.service.EntrepreneurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("entrepreneurService")
public class EntrepreneurServiceImpl implements EntrepreneurService {

    @Resource
    EntrepreneurMapper entrepreneurMapper;

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Entrepreneur> getEntrepreneurList() {
        return entrepreneurMapper.queryEntrepreneur();
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Entrepreneur> getEntrepreneurList(int teacherAccount) {
        return entrepreneurMapper.queryOwnEntrepreneur(teacherAccount);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Entrepreneur> getEntrepreneurList(int teacherAccount, int currentState) {
        return entrepreneurMapper.queryEntrepreneurByTeacher(teacherAccount, currentState);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public Entrepreneur getEntrepreneurById(int account) {
        return entrepreneurMapper.queryEntrepreneurByAccount(account);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean addEntrepreneur(Entrepreneur entrepreneur) {
        return entrepreneurMapper.insertEntrepreneur(entrepreneur) > 0;
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean modifyEntrepreneur(Entrepreneur entrepreneur) {
        return entrepreneurMapper.updateEntrepreneur(entrepreneur) > 0;
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean deleteTeacher(Entrepreneur entrepreneur) {
        return entrepreneurMapper.unBindTeacher(entrepreneur) > 0;
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean deleteEntrepreneur(int account) {
        return entrepreneurMapper.deleteEntrepreneur(account) > 0;
    }
}
