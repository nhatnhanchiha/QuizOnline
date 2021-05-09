package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class Subject implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private Double minutes;

    private Integer numberOfQuestionInQuiz;

    private Boolean status;

    private String idUser;

    private User user;

    private List<Question> questions;

    private List<TakenSubject> takenSubjects;

    private int totalOfValidQuestion;

    private static final long serialVersionUID = 1L;

    public static final class SubjectBuilder {
        private Integer id;
        private String name;
        private String description;
        private Double minutes;
        private Integer numberOfQuestionInQuiz;
        private Boolean status;
        private String idUser;
        private User user;
        private List<Question> questions;
        private List<TakenSubject> takenSubjects;

        private SubjectBuilder() {
        }

        public static SubjectBuilder aSubject() {
            return new SubjectBuilder();
        }

        public SubjectBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public SubjectBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SubjectBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SubjectBuilder withMinutes(Double minutes) {
            this.minutes = minutes;
            return this;
        }

        public SubjectBuilder withNumberOfQuestionInQuiz(Integer numberOfQuestionInQuiz) {
            this.numberOfQuestionInQuiz = numberOfQuestionInQuiz;
            return this;
        }

        public SubjectBuilder withStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public SubjectBuilder withIdUser(String idUser) {
            this.idUser = idUser;
            return this;
        }

        public SubjectBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public SubjectBuilder withQuestions(List<Question> questions) {
            this.questions = questions;
            return this;
        }

        public SubjectBuilder withTakenSubjects(List<TakenSubject> takenSubjects) {
            this.takenSubjects = takenSubjects;
            return this;
        }

        public Subject build() {
            Subject subject = new Subject();
            subject.setId(id);
            subject.setName(name);
            subject.setDescription(description);
            subject.setMinutes(minutes);
            subject.setNumberOfQuestionInQuiz(numberOfQuestionInQuiz);
            subject.setStatus(status);
            subject.setIdUser(idUser);
            subject.setUser(user);
            subject.setQuestions(questions);
            subject.setTakenSubjects(takenSubjects);
            return subject;
        }
    }
}