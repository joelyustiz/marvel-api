package com.marvelapi.service;

import com.marvelapi.domain.ResponseREST;
import com.marvelapi.util.RestAssistant;
import com.marvelapi.util.Http;
import com.marvelapi.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.ResponseEntity;

@Service
public class MarvelService {

    private final RestTemplate restTemplate;

    public MarvelService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseREST getCharacter(String character) {
        RestAssistant assistant = new RestAssistant();
        try {
            ResponseEntity<String> response
                    = this.restTemplate.getForEntity(getUrl("https://gateway.marvel.com:443/v1/public/characters?nameStartsWith=" + character + "&limit=10"), String.class);
            assistant.addResponse(response);
        } catch (CannotGetJdbcConnectionException e) {
            assistant.addMsgErrorDataSource();
        } catch (BadSqlGrammarException | UncategorizedSQLException e) {

            assistant.addMsgErrorSQLGrammar();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MarvelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assistant.getResponse();
    }

    public ResponseREST getColaborators(String character) {
        RestAssistant assistant = new RestAssistant();
        try {
            ResponseEntity<String> response
                    = this.restTemplate.getForEntity(getUrl("https://gateway.marvel.com:443/v1/public/characters?name=" + character + "&limit=10"), String.class);
            assistant.addResponse(response);
        } catch (CannotGetJdbcConnectionException e) {
            assistant.addMsgErrorDataSource();
        } catch (BadSqlGrammarException | UncategorizedSQLException e) {

            assistant.addMsgErrorSQLGrammar();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MarvelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assistant.getResponse();
    }

    public String getUrl(String url) {
        String link = "";
        try {
            Date date = new Date();
            String dateKey = new Timestamp(date.getTime()).toString();
            String key = dateKey + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            link = url + "&apikey=" + Constants.PUBLIC_KEY + "&hash=" + sb.toString() + "&ts=" + dateKey;
            System.out.println("url:" + url);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MarvelService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return link;
    }
}
