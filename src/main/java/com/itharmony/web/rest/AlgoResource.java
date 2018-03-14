package com.itharmony.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Algo controller
 */
@RestController
@RequestMapping("/api/algo")
public class AlgoResource {

    private final Logger log = LoggerFactory.getLogger(AlgoResource.class);

    /**
    * GET cultureAlgo
    */
    @GetMapping("/culture-algo")
    public String cultureAlgo() {
        return "cultureAlgo";
    }

    /**
    * GET skillsAlgo
    */
    @GetMapping("/skills-algo")
    public String skillsAlgo() {
        return "skillsAlgo";
    }

}
