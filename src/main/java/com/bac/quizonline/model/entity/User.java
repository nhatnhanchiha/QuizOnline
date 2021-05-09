package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Getter
@Setter
@ToString
public class User implements Serializable {
    private String email;

    private String name;

    private String password;

    private Short roleId;

    private Boolean status;

    private List<Subject> subjects;

    private List<TakenSubject> takenSubjects;

    private static final long serialVersionUID = 1L;


    public static final class UserBuilder {
        private String email;
        private String name;
        private String password;
        private Short roleId;
        private Boolean status;
        private List<Subject> subjects;
        private List<TakenSubject> takenSubjects;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withPassword(String password) {
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedHash = digest.digest(
                        password.getBytes(StandardCharsets.UTF_8));
                this.password = bytesToHex(encodedHash);
            } catch (NoSuchAlgorithmException ignored) {
                //Cannot happened
            }
            return this;
        }

        public UserBuilder withRoleId(Short roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserBuilder withStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public UserBuilder withSubjects(List<Subject> subjects) {
            this.subjects = subjects;
            return this;
        }

        public UserBuilder withTakenSubjects(List<TakenSubject> takenSubjects) {
            this.takenSubjects = takenSubjects;
            return this;
        }

        public User build() {
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setRoleId(roleId);
            user.setStatus(status);
            user.setSubjects(subjects);
            user.setTakenSubjects(takenSubjects);
            return user;
        }

        private static String bytesToHex(byte[] hash) {
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
    }
}