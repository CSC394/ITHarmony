package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.CandidateProfile;

import com.itharmony.repository.CandidateProfileRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.CandidateProfileDTO;
import com.itharmony.service.mapper.CandidateProfileMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing CandidateProfile.
 */
@RestController
@RequestMapping("/api")
public class CandidateProfileResource {

    private final Logger log = LoggerFactory.getLogger(CandidateProfileResource.class);

    private static final String ENTITY_NAME = "candidateProfile";

    private final CandidateProfileRepository candidateProfileRepository;

    private final CandidateProfileMapper candidateProfileMapper;

    public CandidateProfileResource(CandidateProfileRepository candidateProfileRepository, CandidateProfileMapper candidateProfileMapper) {
        this.candidateProfileRepository = candidateProfileRepository;
        this.candidateProfileMapper = candidateProfileMapper;
    }

    /**
     * POST  /candidate-profiles : Create a new candidateProfile.
     *
     * @param candidateProfileDTO the candidateProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new candidateProfileDTO, or with status 400 (Bad Request) if the candidateProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/candidate-profiles")
    @Timed
    public ResponseEntity<CandidateProfileDTO> createCandidateProfile(@Valid @RequestBody CandidateProfileDTO candidateProfileDTO) throws URISyntaxException {
        log.debug("REST request to save CandidateProfile : {}", candidateProfileDTO);
        if (candidateProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidateProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CandidateProfile candidateProfile = candidateProfileMapper.toEntity(candidateProfileDTO);
        candidateProfile = candidateProfileRepository.save(candidateProfile);
        CandidateProfileDTO result = candidateProfileMapper.toDto(candidateProfile);
        return ResponseEntity.created(new URI("/api/candidate-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /candidate-profiles : Updates an existing candidateProfile.
     *
     * @param candidateProfileDTO the candidateProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated candidateProfileDTO,
     * or with status 400 (Bad Request) if the candidateProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the candidateProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/candidate-profiles")
    @Timed
    public ResponseEntity<CandidateProfileDTO> updateCandidateProfile(@Valid @RequestBody CandidateProfileDTO candidateProfileDTO) throws URISyntaxException {
        log.debug("REST request to update CandidateProfile : {}", candidateProfileDTO);
        if (candidateProfileDTO.getId() == null) {
            return createCandidateProfile(candidateProfileDTO);
        }
        CandidateProfile candidateProfile = candidateProfileMapper.toEntity(candidateProfileDTO);
        candidateProfile = candidateProfileRepository.save(candidateProfile);
        CandidateProfileDTO result = candidateProfileMapper.toDto(candidateProfile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, candidateProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /candidate-profiles : get all the candidateProfiles.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of candidateProfiles in body
     */
    @GetMapping("/candidate-profiles")
    @Timed
    public List<CandidateProfileDTO> getAllCandidateProfiles(@RequestParam(required = false) String filter) {
        if ("userprofile-is-null".equals(filter)) {
            log.debug("REST request to get all CandidateProfiles where userProfile is null");
            return StreamSupport
                .stream(candidateProfileRepository.findAll().spliterator(), false)
                .filter(candidateProfile -> candidateProfile.getUserProfile() == null)
                .map(candidateProfileMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        }
        log.debug("REST request to get all CandidateProfiles");
        List<CandidateProfile> candidateProfiles = candidateProfileRepository.findAll();
        return candidateProfileMapper.toDto(candidateProfiles);
        }

    /**
     * GET  /candidate-profiles/:id : get the "id" candidateProfile.
     *
     * @param id the id of the candidateProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the candidateProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/candidate-profiles/{id}")
    @Timed
    public ResponseEntity<CandidateProfileDTO> getCandidateProfile(@PathVariable Long id) {
        log.debug("REST request to get CandidateProfile : {}", id);
        CandidateProfile candidateProfile = candidateProfileRepository.findOne(id);
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(candidateProfileDTO));
    }

    /**
     * DELETE  /candidate-profiles/:id : delete the "id" candidateProfile.
     *
     * @param id the id of the candidateProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/candidate-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteCandidateProfile(@PathVariable Long id) {
        log.debug("REST request to delete CandidateProfile : {}", id);
        candidateProfileRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
