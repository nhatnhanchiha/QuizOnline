package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class AnswerDetail implements Serializable {
    private Integer idTakenSubject;

    private Integer idQuestion;

    private Integer idOption;

    private TakenSubject takenSubject;

    private Question question;

    private Option option;

    private static final long serialVersionUID = 1L;



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerDetail)) {
            return false;
        }
        AnswerDetail that = (AnswerDetail) o;
        return getIdOption().equals(that.getIdOption());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdOption());
    }

    public static final class AnswerDetailBuilder {
        private Integer idTakenSubject;
        private Integer idQuestion;
        private Integer idOption;
        private TakenSubject takenSubject;
        private Question question;
        private Option option;

        private AnswerDetailBuilder() {
        }

        public static AnswerDetailBuilder anAnswerDetail() {
            return new AnswerDetailBuilder();
        }

        public AnswerDetailBuilder withIdTakenSubject(Integer idTakenSubject) {
            this.idTakenSubject = idTakenSubject;
            return this;
        }

        public AnswerDetailBuilder withIdQuestion(Integer idQuestion) {
            this.idQuestion = idQuestion;
            return this;
        }

        public AnswerDetailBuilder withIdOption(Integer idOption) {
            this.idOption = idOption;
            return this;
        }

        public AnswerDetailBuilder withTakenSubject(TakenSubject takenSubject) {
            this.takenSubject = takenSubject;
            return this;
        }

        public AnswerDetailBuilder withQuestion(Question question) {
            this.question = question;
            return this;
        }

        public AnswerDetailBuilder withOption(Option option) {
            this.option = option;
            return this;
        }

        public AnswerDetail build() {
            AnswerDetail answerDetail = new AnswerDetail();
            answerDetail.setIdTakenSubject(idTakenSubject);
            answerDetail.setIdQuestion(idQuestion);
            answerDetail.setIdOption(idOption);
            answerDetail.setTakenSubject(takenSubject);
            answerDetail.setQuestion(question);
            answerDetail.setOption(option);
            return answerDetail;
        }
    }
}