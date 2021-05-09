package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class Question implements Serializable {
    private Integer id;

    private String text;

    private String content;

    private Integer idSubject;

    private LocalDate createdDate;

    private Boolean status;

    private Subject subject;

    private List<Option> options;

    private static final long serialVersionUID = 1L;

    public int getNumOfRightOption() {
        int d = 0;
        for (Option option : options) {
            if (option.getIsRightAnswer()) {
                d += 1;
            }
        }
        return d;
    }

    public static final class QuestionBuilder {
        private Integer id;
        private String text;
        private String content;
        private Integer idSubject;
        private LocalDate createdDate;
        private Boolean status;
        private Subject subject;
        private List<Option> options;

        private QuestionBuilder() {
        }

        public static QuestionBuilder aQuestion() {
            return new QuestionBuilder();
        }

        public QuestionBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public QuestionBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public QuestionBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public QuestionBuilder withIdSubject(Integer idSubject) {
            this.idSubject = idSubject;
            return this;
        }

        public QuestionBuilder withCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public QuestionBuilder withStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public QuestionBuilder withSubject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public QuestionBuilder withOptions(List<Option> options) {
            this.options = options;
            return this;
        }

        public Question build() {
            Question question = new Question();
            question.setId(id);
            question.setText(text);
            question.setContent(content);
            question.setIdSubject(idSubject);
            question.setCreatedDate(createdDate);
            question.setStatus(status);
            question.setSubject(subject);
            question.setOptions(options);
            return question;
        }
    }
}