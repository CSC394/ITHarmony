package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.JobMatch;

import com.itharmony.repository.JobMatchRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.JobMatchDTO;
import com.itharmony.service.mapper.JobMatchMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JobMatch.
 */
@RestController
@RequestMapping("/api")
public class JobMatchResource {

    private final Logger log = LoggerFactory.getLogger(JobMatchResource.class);

    private static final String ENTITY_NAME = "jobMatch";

    private final JobMatchRepository jobMatchRepository;

    private final JobMatchMapper jobMatchMapper;

    public JobMatchResource(JobMatchRepository jobMatchRepository, JobMatchMapper jobMatchMapper) {
        this.jobMatchRepository = jobMatchRepository;
        this.jobMatchMapper = jobMatchMapper;
    }

    /**
     * POST  /job-matches : Create a new jobMatch.
     *
     * @param jobMatchDTO the jobMatchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobMatchDTO, or with status 400 (Bad Request) if the jobMatch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-matches")
    @Timed
    public ResponseEntity<JobMatchDTO> createJobMatch(@RequestBody JobMatchDTO jobMatchDTO) throws URISyntaxException {
        log.debug("REST request to save JobMatch : {}", jobMatchDTO);
        if (jobMatchDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobMatch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobMatch jobMatch = jobMatchMapper.toEntity(jobMatchDTO);
        jobMatch = jobMatchRepository.save(jobMatch);
        JobMatchDTO result = jobMatchMapper.toDto(jobMatch);
        return ResponseEntity.created(new URI("/api/job-matches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-matches : Updates an existing jobMatch.
     *
     * @param jobMatchDTO the jobMatchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobMatchDTO,
     * or with status 400 (Bad Request) if the jobMatchDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobMatchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-matches")
    @Timed
    public ResponseEntity<JobMatchDTO> updateJobMatch(@RequestBody JobMatchDTO jobMatchDTO) throws URISyntaxException {
        log.debug("REST request to update JobMatch : {}", jobMatchDTO);
        if (jobMatchDTO.getId() == null) {
            return createJobMatch(jobMatchDTO);
        }
        JobMatch jobMatch = jobMatchMapper.toEntity(jobMatchDTO);
        jobMatch = jobMatchRepository.save(jobMatch);
        JobMatchDTO result = jobMatchMapper.toDto(jobMatch);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobMatchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-matches : get all the jobMatches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jobMatches in body
     */
    @GetMapping("/job-matches")
    @Timed
    public List<JobMatchDTO> getAllJobMatches() {
        log.debug("REST request to get all JobMatches");
        List<JobMatch> jobMatches = jobMatchRepository.findAll();
        return jobMatchMapper.toDto(jobMatches);
        }

    /**
     * GET  /job-matches/:id : get the "id" jobMatch.
     *
     * @param id the id of the jobMatchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobMatchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-matches/{id}")
    @Timed
    public ResponseEntity<JobMatchDTO> getJobMatch(@PathVariable Long id) {
        log.debug("REST request to get JobMatch : {}", id);
        JobMatch jobMatch = jobMatchRepository.findOne(id);
        JobMatchDTO jobMatchDTO = jobMatchMapper.toDto(jobMatch);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(jobMatchDTO));
    }

    /**
     * DELETE  /job-matches/:id : delete the "id" jobMatch.
     *
     * @param id the id of the jobMatchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-matches/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobMatch(@PathVariable Long id) {
        log.debug("REST request to delete JobMatch : {}", id);
        jobMatchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
