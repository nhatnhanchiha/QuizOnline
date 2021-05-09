package com.bac.quizonline.model.service;

import com.bac.quizonline.model.entity.TakenSubject;

import java.util.List;

public interface TakenSubjectService {
    int insert(TakenSubject takenSubject);

    TakenSubject getTakenSubjectInQuiz(TakenSubject takenSubject);

    TakenSubject getTakenSubjectWithDetail(TakenSubject takenSubject);

    TakenSubject getTakenSubjectWithDetailInProgressQuiz(TakenSubject takenSubject, int offset, int limit);

    TakenSubject checkValidTakenSubjectId(int id, String idUser);

    List<TakenSubject> getTakenSubjectHistoryList(String email, String nameOrIdSubject, Integer orIdSubject, int offset, int limit);

    int endTakenSubject(int id);
}
