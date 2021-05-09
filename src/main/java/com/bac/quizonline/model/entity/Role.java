package com.bac.quizonline.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Role implements Serializable {
    private Short id;

    private String name;

    private static final long serialVersionUID = 1L;
}