package com.bac.quizonline.model.component;

import com.bac.quizonline.model.entity.TakenSubject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZoneOffset;

@Getter
@Setter
@ToString
public class QuizPage {
    private TakenSubject takenSubject;
    private int currentPage;
    private boolean hasNextPage;
    public static int MAX_QUESTION_OF_A_PAGE = 1;

    public long getEndTimeInMilliseconds() {
        return takenSubject.getEndTime().toInstant(ZoneOffset.of("+7")).toEpochMilli();
    }


    public QuizPage(TakenSubject takenSubject, int currentPage) {
        this.takenSubject = takenSubject;
        this.currentPage = currentPage;
        if (takenSubject.getTakenSubjectDetails().size() > MAX_QUESTION_OF_A_PAGE) {
            hasNextPage = true;
            takenSubject.getTakenSubjectDetails().remove(MAX_QUESTION_OF_A_PAGE);
        }
    }

    public TakenSubject getTakenSubject() {
        return takenSubject;
    }

    public void setTakenSubject(TakenSubject takenSubject) {
        this.takenSubject = takenSubject;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
