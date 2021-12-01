package com.clint.marvel.util;

import com.clint.marvel.exception.BaseServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RequestUtil {

    private static final String DEBUG_LOG_STRING_POST_REQUEST_URL = "post request url: {}";

    @Autowired
    RestTemplate restTemplate;

    @Value("${com.marvel.private.key}")
    public String privateApiKey;

    @Value("${com.marvel.public.key}")
    public String publicApiKey;

    public <U> U get(String url, Class<U> clazz) {

        Map<String, Serializable> uriVariables = new HashMap<String, Serializable>();

        String hash = MarvelUtil.MD5hash(privateApiKey, publicApiKey, MarvelUtil.timeStamp);
        log.debug("hash: {} " + hash);
        uriVariables.put("hash", hash);
        uriVariables.put("apikey", publicApiKey);
        uriVariables.put("ts", MarvelUtil.timeStamp);

        url += "ts={ts}&apikey={apikey}&hash={hash}";
        URI uri = restTemplate.getUriTemplateHandler().expand(url, uriVariables);

        log.debug("get request urlaaaa: {}", uri.toString());
        return get(uri, clazz);
    }

    public <U> U get(URI url, Class<U> clazz) {
        log.debug("get request urlqqqq: {} ", url);

        U response = null;
        try{
            HttpEntity httpEntity = new HttpEntity(null);
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            String responseStr = exchange.getBody();
            JsonUtil.parseObject(responseStr, clazz);
            response = JsonUtil.parseObject(responseStr, clazz);

        } catch (Exception ex) {
//            throw new BaseServiceException(ex, HttpStatus.BAD_REQUEST, MarvelUtil.instant + "");
            log.debug(ex.getMessage());
            throw new BaseServiceException(ex);
        }

        return response;
    }



}
