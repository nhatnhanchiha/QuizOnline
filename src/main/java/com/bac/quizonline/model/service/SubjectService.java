package com.bac.quizonline.model.service;

import com.bac.quizonline.model.entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubject(Subject record, int offset, int limit);
    List<Subject> getAllCreatedSubjectByIdUser(String idUser);
    int addQuestion(Subject subject);
    Subject getValidSubject(int idSubject);
}

