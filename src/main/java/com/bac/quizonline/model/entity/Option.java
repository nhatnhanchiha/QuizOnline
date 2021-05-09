package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class Option implements Serializable {
    private Integer id;

    private String content;

    private Boolean isRightAnswer;

    private Integer idQuestion;

    private Question question;

    private List<TakenSubjectDetail> takenSubjectDetails;

    private static final long serialVersionUID = 1L;


    public static final class OptionBuilder {
        private Integer id;
        private String content;
        private Boolean isRightAnswer;
        private Integer idQuestion;
        private Question question;
        private List<TakenSubjectDetail> takenSubjectDetails;

        private OptionBuilder() {
        }

        public static OptionBuilder anOption() {
            return new OptionBuilder();
        }

        public OptionBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OptionBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public OptionBuilder withIsRightAnswer(Boolean isRightAnswer) {
            this.isRightAnswer = isRightAnswer;
            return this;
        }

        public OptionBuilder withIdQuestion(Integer idQuestion) {
            this.idQuestion = idQuestion;
            return this;
        }

        public OptionBuilder withQuestion(Question question) {
            this.question = question;
            return this;
        }

        public OptionBuilder withTakenSubjectDetails(List<TakenSubjectDetail> takenSubjectDetails) {
            this.takenSubjectDetails = takenSubjectDetails;
            return this;
        }

        public Option build() {
            Option option = new Option();
            option.setId(id);
            option.setContent(content);
            option.setIsRightAnswer(isRightAnswer);
            option.setIdQuestion(idQuestion);
            option.setQuestion(question);
            option.setTakenSubjectDetails(takenSubjectDetails);
            return option;
        }
    }
}