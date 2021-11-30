package com.clint.marvel.util;

import com.clint.marvel.exception.BaseServiceException;
import com.clint.marvel.exception.ErrorCodes;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
            handleError(responseStr);
            //get response data
            response = JsonUtil.parseObject(responseStr, clazz);
            if (response == null){
                throw new BaseServiceException(ErrorCodes.SERVER_ERROR);
            }

        } catch (Exception e) {
            throw new BaseServiceException(ErrorCodes.SOME_OTHER_EXCEPTIONS);
        }

        return response;
    }

    private static void handleError(String responseStr) throws IOException {
        log.debug("responsestr -- {} ", responseStr);
        JsonNode errorDetailNode = JsonUtil.getJsonNode(responseStr, "ErrorDetail");
    }


}
