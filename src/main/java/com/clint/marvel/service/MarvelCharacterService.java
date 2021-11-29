package com.clint.marvel.service;

import com.clint.marvel.config.UrlProperties;
import com.clint.marvel.model.MarvelCharacterData;
import com.clint.marvel.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarvelCharacterService {

    @Autowired
    private RequestUtil requestUtil;

    @Autowired
    private UrlProperties urlProperties;

    public MarvelCharacterData getCharacter(int characterId){

        String getCharacterUrl = urlProperties.getBase() + "characters/" + characterId + "?";
        MarvelCharacterData marvelCharacterData = requestUtil.get(getCharacterUrl, MarvelCharacterData.class);

        return marvelCharacterData;
    }

    public MarvelCharacterData getCharacterAll(){

        String getCharacterUrl = urlProperties.getBase() + "characters?";
        MarvelCharacterData marvelCharacterData = requestUtil.get(getCharacterUrl, MarvelCharacterData.class);

        return marvelCharacterData;
    }

}
