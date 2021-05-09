package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class TakenSubject implements Serializable {
    private Integer id;

    private Integer idSubject;

    private String idUser;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Subject subject;

    private User user;

    private List<TakenSubjectDetail> takenSubjectDetails;

    private static final long serialVersionUID = 1L;


    public static final class TakenSubjectBuilder {
        private Integer id;
        private Integer idSubject;
        private String idUser;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Subject subject;
        private User user;
        private List<TakenSubjectDetail> takenSubjectDetails;

        private TakenSubjectBuilder() {
        }

        public static TakenSubjectBuilder aTakenSubject() {
            return new TakenSubjectBuilder();
        }

        public TakenSubjectBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public TakenSubjectBuilder withIdSubject(Integer idSubject) {
            this.idSubject = idSubject;
            return this;
        }

        public TakenSubjectBuilder withIdUser(String idUser) {
            this.idUser = idUser;
            return this;
        }

        public TakenSubjectBuilder withStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public TakenSubjectBuilder withEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public TakenSubjectBuilder withSubject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public TakenSubjectBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public TakenSubjectBuilder withTakenSubjectDetails(List<TakenSubjectDetail> takenSubjectDetails) {
            this.takenSubjectDetails = takenSubjectDetails;
            return this;
        }

        public TakenSubject build() {
            TakenSubject takenSubject = new TakenSubject();
            takenSubject.setId(id);
            takenSubject.setIdSubject(idSubject);
            takenSubject.setIdUser(idUser);
            takenSubject.setStartTime(startTime);
            takenSubject.setEndTime(endTime);
            takenSubject.setSubject(subject);
            takenSubject.setUser(user);
            takenSubject.setTakenSubjectDetails(takenSubjectDetails);
            return takenSubject;
        }
    }
}