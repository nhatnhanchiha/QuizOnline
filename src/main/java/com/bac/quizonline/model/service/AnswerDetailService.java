package com.bac.quizonline.model.service;

import com.bac.quizonline.model.entity.AnswerDetail;

import java.util.List;

public interface AnswerDetailService {
    int insertList(List<AnswerDetail> answerDetails);
}
