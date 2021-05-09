package com.bac.quizonline.model.component;

import com.bac.quizonline.model.entity.Subject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class QuizzesPage implements Serializable {
    private static final long serialVersionUID = -6949376454782458819L;
    public static final int MAX_SIZE_OF_SUBJECT_LIST = 10;
    private List<Subject> subjects;
    private int currentPage;
    private boolean hasNextPage;

    public QuizzesPage(List<Subject> subjects, int currentPage) {
        this.subjects = subjects;
        this.currentPage = currentPage;

        if (subjects.size() > MAX_SIZE_OF_SUBJECT_LIST) {
            hasNextPage = true;
            subjects.remove(MAX_SIZE_OF_SUBJECT_LIST);
        } else {
            hasNextPage = false;
        }
    }
}
