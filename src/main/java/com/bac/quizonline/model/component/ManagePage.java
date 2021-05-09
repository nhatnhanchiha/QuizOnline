package com.bac.quizonline.model.component;

import com.bac.quizonline.model.entity.Subject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ManagePage implements Serializable {
    private static final long serialVersionUID = 2263667197239801489L;
    public final static int SIZE = 20;
    private Subject subject;
    private int currentPage;
    private boolean hasNextPage;

    public ManagePage(Subject subject, int currentPage) {
        this.subject = subject;
        this.currentPage = currentPage;
        final int size = subject.getQuestions().size();
        if (size > SIZE) {
            subject.getQuestions().remove(SIZE);
            hasNextPage = true;
        } else {
            hasNextPage = false;
        }
    }
}
