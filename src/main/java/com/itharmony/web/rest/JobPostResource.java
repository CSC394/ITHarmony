package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.JobPost;

import com.itharmony.repository.JobPostRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.JobPostDTO;
import com.itharmony.service.mapper.JobPostMapper;
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
 * REST controller for managing JobPost.
 */
@RestController
@RequestMapping("/api")
public class JobPostResource {

    private final Logger log = LoggerFactory.getLogger(JobPostResource.class);

    private static final String ENTITY_NAME = "jobPost";

    private final JobPostRepository jobPostRepository;

    private final JobPostMapper jobPostMapper;

    public JobPostResource(JobPostRepository jobPostRepository, JobPostMapper jobPostMapper) {
        this.jobPostRepository = jobPostRepository;
        this.jobPostMapper = jobPostMapper;
    }

    /**
     * POST  /job-posts : Create a new jobPost.
     *
     * @param jobPostDTO the jobPostDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobPostDTO, or with status 400 (Bad Request) if the jobPost has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-posts")
    @Timed
    public ResponseEntity<JobPostDTO> createJobPost(@RequestBody JobPostDTO jobPostDTO) throws URISyntaxException {
        log.debug("REST request to save JobPost : {}", jobPostDTO);
        if (jobPostDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobPost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobPost jobPost = jobPostMapper.toEntity(jobPostDTO);
        jobPost = jobPostRepository.save(jobPost);
        JobPostDTO result = jobPostMapper.toDto(jobPost);
        return ResponseEntity.created(new URI("/api/job-posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-posts : Updates an existing jobPost.
     *
     * @param jobPostDTO the jobPostDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobPostDTO,
     * or with status 400 (Bad Request) if the jobPostDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobPostDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-posts")
    @Timed
    public ResponseEntity<JobPostDTO> updateJobPost(@RequestBody JobPostDTO jobPostDTO) throws URISyntaxException {
        log.debug("REST request to update JobPost : {}", jobPostDTO);
        if (jobPostDTO.getId() == null) {
            return createJobPost(jobPostDTO);
        }
        JobPost jobPost = jobPostMapper.toEntity(jobPostDTO);
        jobPost = jobPostRepository.save(jobPost);
        JobPostDTO result = jobPostMapper.toDto(jobPost);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobPostDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-posts : get all the jobPosts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jobPosts in body
     */
    @GetMapping("/job-posts")
    @Timed
    public List<JobPostDTO> getAllJobPosts() {
        log.debug("REST request to get all JobPosts");
        List<JobPost> jobPosts = jobPostRepository.findAll();
        return jobPostMapper.toDto(jobPosts);
        }

    /**
     * GET  /job-posts/:id : get the "id" jobPost.
     *
     * @param id the id of the jobPostDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobPostDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-posts/{id}")
    @Timed
    public ResponseEntity<JobPostDTO> getJobPost(@PathVariable Long id) {
        log.debug("REST request to get JobPost : {}", id);
        JobPost jobPost = jobPostRepository.findOne(id);
        JobPostDTO jobPostDTO = jobPostMapper.toDto(jobPost);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(jobPostDTO));
    }

    /**
     * DELETE  /job-posts/:id : delete the "id" jobPost.
     *
     * @param id the id of the jobPostDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-posts/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long id) {
        log.debug("REST request to delete JobPost : {}", id);
        jobPostRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
