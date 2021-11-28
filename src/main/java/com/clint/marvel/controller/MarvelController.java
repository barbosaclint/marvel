package com.clint.marvel.controller;

import com.clint.marvel.model.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/" )
public class MarvelController {

    private final Marve

    public ResponseEntity<Character> getCharacter(@PathVariable int characterId) {

        return ResponseEntity.ok()
    }
}
