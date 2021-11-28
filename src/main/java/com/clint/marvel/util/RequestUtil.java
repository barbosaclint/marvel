package com.clint.marvel.util;

import com.clint.marvel.exception.BaseServiceException;
import com.clint.marvel.exception.ExceptionCodes;
import com.clint.marvel.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

@Slf4j
@Component
public class RequestUtil {

    private static final String DEBUG_LOG_STRING_POST_REQUEST_URL = "post request url: {}";

    @Autowired
    RestTemplate restTemplate;

    public <U> U get(String url, HttpHeaders headers, Map<String, Serializable> uriVariables, Class<U> clazz) {
        URI uri = restTemplate.getUriTemplateHandler().expand(url, uriVariables);

        log.debug("get request url: {}", uri.toString());
        return get(uri, headers, clazz);
    }

    public <U> U get(URI url, HttpHeaders headers, Class<U> clazz) {
        log.debug("get request url: {} ", url);

        U response = null;
        try{
            headers.remove("Content-Type");
            HttpEntity httpEntity = new HttpEntity(null, headers);
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            String responseStr = exchange.getBody();
            handleError(responseStr);
            JsonUtil.parseObject(responseStr, clazz);

            //get response data
            response = JsonUtil.parseObject(responseStr, clazz);
            if (response == null){
                log.error("---------- request error, response is null ----------");
                throw new ServiceException(ExceptionCodes.SERVER_ERROR,
                        "response null for the request end point url " + url);
            }

        } catch (BaseServiceException ex) {
            throw new BaseServiceException(ex);
        } catch (Exception ex) {
//            throw new ServiceException(ExceptionCodes.SERVER_ERROR, ExceptionUtils.);
        }

        return response;
    }

    private static void handleError(String responseStr) throws IOException {

        JsonNode errorDetailNode = JsonUtil.getJsonNode(responseStr, "ErrorDetail");
    }


}
