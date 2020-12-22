package com.marvelapi.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.marvelapi.service.MarvelService;
import com.marvelapi.domain.ResponseREST;

@RestController
@RequestMapping("api")
public class MarvelController {

    private final MarvelService validationService;

    @Autowired
    public MarvelController(MarvelService validationService) {
        this.validationService = validationService;
    }

    @GetMapping(value = "/characters/{character}")
    public ResponseREST getCharacter(@PathVariable String character) {
        return validationService.getCharacter(character);
    }

    @GetMapping(value = "/colaborators/{character}")
    public ResponseREST getColaborators(@PathVariable String character) {
        return validationService.getColaborators(character);
    }
}
