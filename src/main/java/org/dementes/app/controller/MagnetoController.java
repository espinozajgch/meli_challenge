package org.dementes.app.controller;

import org.dementes.app.AppApplication;
import org.dementes.app.dto.MutantStatsDto;
import org.dementes.app.pojo.DnaWrapper;
import org.dementes.app.services.MutantStatsService;
import org.dementes.app.services.MutantValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MagnetoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppApplication.class);

    @Autowired
    private MutantValidatorService mutantValidatorService;

    @Autowired
    private MutantStatsService mutantStatsService;

    @ResponseBody
    @PostMapping(
            value = "/mutant")
    public ResponseEntity<Boolean> dnaRecognition(
            @RequestBody DnaWrapper dna
            ){

        if(mutantValidatorService.validator(dna.listToArrays()))
            return new ResponseEntity<>((Boolean) null, HttpStatus.OK);
        else/**/
            return new ResponseEntity<>((Boolean) null,HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<MutantStatsDto> dnaStats(){
            return new ResponseEntity(new MutantStatsDto(mutantStatsService.findAllCount()), HttpStatus.OK);
    }
}
