package com.manager.service;

import com.manager.entity.Entrepreneur;

import java.util.List;

public interface EntrepreneurService {

    List<Entrepreneur> getEntrepreneurList();
    List<Entrepreneur> getEntrepreneurList(int teacherAccount);
    List<Entrepreneur> getEntrepreneurList(int teacherAccount, int currentState);
    Entrepreneur getEntrepreneurById(int account);
    boolean addEntrepreneur(Entrepreneur entrepreneur);
    boolean modifyEntrepreneur(Entrepreneur entrepreneur);
    boolean deleteTeacher(Entrepreneur entrepreneur);
    boolean deleteEntrepreneur(int id);
}
