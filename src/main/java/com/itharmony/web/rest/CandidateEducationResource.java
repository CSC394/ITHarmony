package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.CandidateEducation;

import com.itharmony.repository.CandidateEducationRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.CandidateEducationDTO;
import com.itharmony.service.mapper.CandidateEducationMapper;
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
 * REST controller for managing CandidateEducation.
 */
@RestController
@RequestMapping("/api")
public class CandidateEducationResource {

    private final Logger log = LoggerFactory.getLogger(CandidateEducationResource.class);

    private static final String ENTITY_NAME = "candidateEducation";

    private final CandidateEducationRepository candidateEducationRepository;

    private final CandidateEducationMapper candidateEducationMapper;

    public CandidateEducationResource(CandidateEducationRepository candidateEducationRepository, CandidateEducationMapper candidateEducationMapper) {
        this.candidateEducationRepository = candidateEducationRepository;
        this.candidateEducationMapper = candidateEducationMapper;
    }

    /**
     * POST  /candidate-educations : Create a new candidateEducation.
     *
     * @param candidateEducationDTO the candidateEducationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new candidateEducationDTO, or with status 400 (Bad Request) if the candidateEducation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/candidate-educations")
    @Timed
    public ResponseEntity<CandidateEducationDTO> createCandidateEducation(@RequestBody CandidateEducationDTO candidateEducationDTO) throws URISyntaxException {
        log.debug("REST request to save CandidateEducation : {}", candidateEducationDTO);
        if (candidateEducationDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidateEducation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CandidateEducation candidateEducation = candidateEducationMapper.toEntity(candidateEducationDTO);
        candidateEducation = candidateEducationRepository.save(candidateEducation);
        CandidateEducationDTO result = candidateEducationMapper.toDto(candidateEducation);
        return ResponseEntity.created(new URI("/api/candidate-educations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /candidate-educations : Updates an existing candidateEducation.
     *
     * @param candidateEducationDTO the candidateEducationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated candidateEducationDTO,
     * or with status 400 (Bad Request) if the candidateEducationDTO is not valid,
     * or with status 500 (Internal Server Error) if the candidateEducationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/candidate-educations")
    @Timed
    public ResponseEntity<CandidateEducationDTO> updateCandidateEducation(@RequestBody CandidateEducationDTO candidateEducationDTO) throws URISyntaxException {
        log.debug("REST request to update CandidateEducation : {}", candidateEducationDTO);
        if (candidateEducationDTO.getId() == null) {
            return createCandidateEducation(candidateEducationDTO);
        }
        CandidateEducation candidateEducation = candidateEducationMapper.toEntity(candidateEducationDTO);
        candidateEducation = candidateEducationRepository.save(candidateEducation);
        CandidateEducationDTO result = candidateEducationMapper.toDto(candidateEducation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, candidateEducationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /candidate-educations : get all the candidateEducations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of candidateEducations in body
     */
    @GetMapping("/candidate-educations")
    @Timed
    public List<CandidateEducationDTO> getAllCandidateEducations() {
        log.debug("REST request to get all CandidateEducations");
        List<CandidateEducation> candidateEducations = candidateEducationRepository.findAll();
        return candidateEducationMapper.toDto(candidateEducations);
        }

    /**
     * GET  /candidate-educations/:id : get the "id" candidateEducation.
     *
     * @param id the id of the candidateEducationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the candidateEducationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/candidate-educations/{id}")
    @Timed
    public ResponseEntity<CandidateEducationDTO> getCandidateEducation(@PathVariable Long id) {
        log.debug("REST request to get CandidateEducation : {}", id);
        CandidateEducation candidateEducation = candidateEducationRepository.findOne(id);
        CandidateEducationDTO candidateEducationDTO = candidateEducationMapper.toDto(candidateEducation);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(candidateEducationDTO));
    }

    /**
     * DELETE  /candidate-educations/:id : delete the "id" candidateEducation.
     *
     * @param id the id of the candidateEducationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/candidate-educations/{id}")
    @Timed
    public ResponseEntity<Void> deleteCandidateEducation(@PathVariable Long id) {
        log.debug("REST request to delete CandidateEducation : {}", id);
        candidateEducationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
