package com.bac.quizonline.model.component;

import com.bac.quizonline.model.entity.TakenSubject;
import com.bac.quizonline.model.entity.TakenSubjectDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class HistoryPage implements Serializable {
    private static final long serialVersionUID = 6885641027270856346L;
    private TakenSubject takenSubject;
    private double point;

    public HistoryPage(TakenSubject takenSubject) {
        this.takenSubject = takenSubject;
        this.point = takePoint(takenSubject);
    }

    private static double takePoint(TakenSubject takenSubject) {
        double totalPoint = 0.0;
        for (TakenSubjectDetail takenSubjectDetail : takenSubject.getTakenSubjectDetails()) {
            totalPoint += takenSubjectDetail.getPoint();
        }

        return totalPoint/takenSubject.getTakenSubjectDetails().size() * 100;
    }
}