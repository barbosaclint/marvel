package com.clint.marvel.model;

import lombok.Data;

import java.util.List;

@Data
public class Character {

    private int id;
    private String name;
    private String description;

    private List<Character> results;
}
