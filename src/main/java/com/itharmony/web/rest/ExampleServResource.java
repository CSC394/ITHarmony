package com.itharmony.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itharmony.web.rest.vm.ProcessVM;


/**
 * ExampleServ controller
 */
@RestController
@RequestMapping("/api/example-serv")
public class ExampleServResource {

    private final Logger log = LoggerFactory.getLogger(ExampleServResource.class);

    ProcessVM processVM;
    /**
     *
    * POST process
    */
    @PostMapping("/process")
    public String process(ProcessVM processVM) {
        return processVM.getFileName();
    }

    public void process(String filename) {
        processVM.setFileName(filename);
    }

}
