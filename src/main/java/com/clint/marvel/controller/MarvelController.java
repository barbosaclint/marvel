package com.clint.marvel.controller;

import com.clint.marvel.model.MarvelCharacterData;
import com.clint.marvel.service.MarvelCharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/clint-marvel-api" )
@RequiredArgsConstructor
public class MarvelController {

    private final MarvelCharacterService marvelCharacterService;

    @GetMapping(value = "/getcharacter/{characterId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarvelCharacterData> getCharacter(@PathVariable int characterId) {
        MarvelCharacterData marvelCharacterData = marvelCharacterService.getCharacter(characterId);

        return ResponseEntity.ok(marvelCharacterData);
    }

    @GetMapping(value = "/getallcharacters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<int[]> getAllCharacters() {

        return ResponseEntity.ok(marvelCharacterService.getCharacterAll());
    }
}
