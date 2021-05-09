package com.bac.quizonline.model.component;

import com.bac.quizonline.model.entity.TakenSubject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class HistoryListPage implements Serializable {
    private static final long serialVersionUID = 3512073621375975551L;
    public static final int MAX_SIZE_OF_TAKEN_SUBJECT_LIST = 10;

    private List<TakenSubject> takenSubjects;
    private int currentPage;
    private boolean hasNextPage;

    public HistoryListPage(List<TakenSubject> takenSubjects, int currentPage) {
        this.takenSubjects = takenSubjects;
        this.currentPage = currentPage;
        if (takenSubjects.size() > MAX_SIZE_OF_TAKEN_SUBJECT_LIST) {
            takenSubjects.remove(MAX_SIZE_OF_TAKEN_SUBJECT_LIST);
            hasNextPage = true;
        } else {
            hasNextPage = false;
        }
    }
}
