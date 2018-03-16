package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.CandidateWorkExperience;

import com.itharmony.repository.CandidateWorkExperienceRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.CandidateWorkExperienceDTO;
import com.itharmony.service.mapper.CandidateWorkExperienceMapper;
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
 * REST controller for managing CandidateWorkExperience.
 */
@RestController
@RequestMapping("/api")
public class CandidateWorkExperienceResource {

    private final Logger log = LoggerFactory.getLogger(CandidateWorkExperienceResource.class);

    private static final String ENTITY_NAME = "candidateWorkExperience";

    private final CandidateWorkExperienceRepository candidateWorkExperienceRepository;

    private final CandidateWorkExperienceMapper candidateWorkExperienceMapper;

    public CandidateWorkExperienceResource(CandidateWorkExperienceRepository candidateWorkExperienceRepository, CandidateWorkExperienceMapper candidateWorkExperienceMapper) {
        this.candidateWorkExperienceRepository = candidateWorkExperienceRepository;
        this.candidateWorkExperienceMapper = candidateWorkExperienceMapper;
    }

    /**
     * POST  /candidate-work-experiences : Create a new candidateWorkExperience.
     *
     * @param candidateWorkExperienceDTO the candidateWorkExperienceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new candidateWorkExperienceDTO, or with status 400 (Bad Request) if the candidateWorkExperience has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/candidate-work-experiences")
    @Timed
    public ResponseEntity<CandidateWorkExperienceDTO> createCandidateWorkExperience(@RequestBody CandidateWorkExperienceDTO candidateWorkExperienceDTO) throws URISyntaxException {
        log.debug("REST request to save CandidateWorkExperience : {}", candidateWorkExperienceDTO);
        if (candidateWorkExperienceDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidateWorkExperience cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CandidateWorkExperience candidateWorkExperience = candidateWorkExperienceMapper.toEntity(candidateWorkExperienceDTO);
        candidateWorkExperience = candidateWorkExperienceRepository.save(candidateWorkExperience);
        CandidateWorkExperienceDTO result = candidateWorkExperienceMapper.toDto(candidateWorkExperience);
        return ResponseEntity.created(new URI("/api/candidate-work-experiences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /candidate-work-experiences : Updates an existing candidateWorkExperience.
     *
     * @param candidateWorkExperienceDTO the candidateWorkExperienceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated candidateWorkExperienceDTO,
     * or with status 400 (Bad Request) if the candidateWorkExperienceDTO is not valid,
     * or with status 500 (Internal Server Error) if the candidateWorkExperienceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/candidate-work-experiences")
    @Timed
    public ResponseEntity<CandidateWorkExperienceDTO> updateCandidateWorkExperience(@RequestBody CandidateWorkExperienceDTO candidateWorkExperienceDTO) throws URISyntaxException {
        log.debug("REST request to update CandidateWorkExperience : {}", candidateWorkExperienceDTO);
        if (candidateWorkExperienceDTO.getId() == null) {
            return createCandidateWorkExperience(candidateWorkExperienceDTO);
        }
        CandidateWorkExperience candidateWorkExperience = candidateWorkExperienceMapper.toEntity(candidateWorkExperienceDTO);
        candidateWorkExperience = candidateWorkExperienceRepository.save(candidateWorkExperience);
        CandidateWorkExperienceDTO result = candidateWorkExperienceMapper.toDto(candidateWorkExperience);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, candidateWorkExperienceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /candidate-work-experiences : get all the candidateWorkExperiences.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of candidateWorkExperiences in body
     */
    @GetMapping("/candidate-work-experiences")
    @Timed
    public List<CandidateWorkExperienceDTO> getAllCandidateWorkExperiences() {
        log.debug("REST request to get all CandidateWorkExperiences");
        List<CandidateWorkExperience> candidateWorkExperiences = candidateWorkExperienceRepository.findAll();
        return candidateWorkExperienceMapper.toDto(candidateWorkExperiences);
        }

    /**
     * GET  /candidate-work-experiences/:id : get the "id" candidateWorkExperience.
     *
     * @param id the id of the candidateWorkExperienceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the candidateWorkExperienceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/candidate-work-experiences/{id}")
    @Timed
    public ResponseEntity<CandidateWorkExperienceDTO> getCandidateWorkExperience(@PathVariable Long id) {
        log.debug("REST request to get CandidateWorkExperience : {}", id);
        CandidateWorkExperience candidateWorkExperience = candidateWorkExperienceRepository.findOne(id);
        CandidateWorkExperienceDTO candidateWorkExperienceDTO = candidateWorkExperienceMapper.toDto(candidateWorkExperience);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(candidateWorkExperienceDTO));
    }

    /**
     * DELETE  /candidate-work-experiences/:id : delete the "id" candidateWorkExperience.
     *
     * @param id the id of the candidateWorkExperienceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/candidate-work-experiences/{id}")
    @Timed
    public ResponseEntity<Void> deleteCandidateWorkExperience(@PathVariable Long id) {
        log.debug("REST request to delete CandidateWorkExperience : {}", id);
        candidateWorkExperienceRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
