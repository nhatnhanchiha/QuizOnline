package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
public class TakenSubjectDetail implements Serializable {
    private Integer idTakenSubject;

    private Integer idQuestion;

    private TakenSubject takenSubject;

    private Question question;

    private Set<AnswerDetail> answerDetails;

    public double getPoint() {
        int match = 0;
        int notMatch;
        int total = 0;
        for (Option option : question.getOptions()) {
            if (option.getIsRightAnswer()) {
                total ++;
                if (answerDetails.contains(AnswerDetail.AnswerDetailBuilder.anAnswerDetail().withIdOption(option.getId()).build())) {
                    match ++;
                }
            }
        }

        if (total == 0 && answerDetails.isEmpty()) {
            return 1;
        }

        if (total == 0) {
            return 0;
        }

        notMatch = answerDetails.size() - match;

        return (match - notMatch) <= 0 ? 0.0 : (double) (match - notMatch)/total;
    }

    public boolean isChoiceOption(int idOption) {
        return answerDetails.contains(AnswerDetail.AnswerDetailBuilder.anAnswerDetail().withIdOption(idOption).build());
    }

    private static final long serialVersionUID = 1L;


    public static final class TakenSubjectDetailBuilder {
        private Integer idTakenSubject;
        private Integer idQuestion;
        private TakenSubject takenSubject;
        private Question question;
        private Set<AnswerDetail> answerDetails;

        private TakenSubjectDetailBuilder() {
        }

        public static TakenSubjectDetailBuilder aTakenSubjectDetail() {
            return new TakenSubjectDetailBuilder();
        }

        public TakenSubjectDetailBuilder withIdTakenSubject(Integer idTakenSubject) {
            this.idTakenSubject = idTakenSubject;
            return this;
        }

        public TakenSubjectDetailBuilder withIdQuestion(Integer idQuestion) {
            this.idQuestion = idQuestion;
            return this;
        }

        public TakenSubjectDetailBuilder withTakenSubject(TakenSubject takenSubject) {
            this.takenSubject = takenSubject;
            return this;
        }

        public TakenSubjectDetailBuilder withQuestion(Question question) {
            this.question = question;
            return this;
        }

        public TakenSubjectDetailBuilder withAnswerDetails(Set<AnswerDetail> answerDetails) {
            this.answerDetails = answerDetails;
            return this;
        }

        public TakenSubjectDetail build() {
            TakenSubjectDetail takenSubjectDetail = new TakenSubjectDetail();
            takenSubjectDetail.setIdTakenSubject(idTakenSubject);
            takenSubjectDetail.setIdQuestion(idQuestion);
            takenSubjectDetail.setTakenSubject(takenSubject);
            takenSubjectDetail.setQuestion(question);
            takenSubjectDetail.setAnswerDetails(answerDetails);
            return takenSubjectDetail;
        }
    }
}