package com.clint.marvel.model;

import lombok.Data;

@Data
public class MarvelCharacterData {

    private int code;
    private String status;
    private MarvelCharacterResults data;

}
