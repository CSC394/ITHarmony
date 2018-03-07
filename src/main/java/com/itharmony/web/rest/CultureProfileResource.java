package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.CultureProfile;

import com.itharmony.repository.CultureProfileRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.CultureProfileDTO;
import com.itharmony.service.mapper.CultureProfileMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing CultureProfile.
 */
@RestController
@RequestMapping("/api")
public class CultureProfileResource {

    private final Logger log = LoggerFactory.getLogger(CultureProfileResource.class);

    private static final String ENTITY_NAME = "cultureProfile";

    private final CultureProfileRepository cultureProfileRepository;

    private final CultureProfileMapper cultureProfileMapper;

    public CultureProfileResource(CultureProfileRepository cultureProfileRepository, CultureProfileMapper cultureProfileMapper) {
        this.cultureProfileRepository = cultureProfileRepository;
        this.cultureProfileMapper = cultureProfileMapper;
    }

    /**
     * POST  /culture-profiles : Create a new cultureProfile.
     *
     * @param cultureProfileDTO the cultureProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cultureProfileDTO, or with status 400 (Bad Request) if the cultureProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/culture-profiles")
    @Timed
    public ResponseEntity<CultureProfileDTO> createCultureProfile(@RequestBody CultureProfileDTO cultureProfileDTO) throws URISyntaxException {
        log.debug("REST request to save CultureProfile : {}", cultureProfileDTO);
        if (cultureProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new cultureProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CultureProfile cultureProfile = cultureProfileMapper.toEntity(cultureProfileDTO);
        cultureProfile = cultureProfileRepository.save(cultureProfile);
        CultureProfileDTO result = cultureProfileMapper.toDto(cultureProfile);
        return ResponseEntity.created(new URI("/api/culture-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /culture-profiles : Updates an existing cultureProfile.
     *
     * @param cultureProfileDTO the cultureProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cultureProfileDTO,
     * or with status 400 (Bad Request) if the cultureProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the cultureProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/culture-profiles")
    @Timed
    public ResponseEntity<CultureProfileDTO> updateCultureProfile(@RequestBody CultureProfileDTO cultureProfileDTO) throws URISyntaxException {
        log.debug("REST request to update CultureProfile : {}", cultureProfileDTO);
        if (cultureProfileDTO.getId() == null) {
            return createCultureProfile(cultureProfileDTO);
        }
        CultureProfile cultureProfile = cultureProfileMapper.toEntity(cultureProfileDTO);
        cultureProfile = cultureProfileRepository.save(cultureProfile);
        CultureProfileDTO result = cultureProfileMapper.toDto(cultureProfile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cultureProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /culture-profiles : get all the cultureProfiles.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of cultureProfiles in body
     */
    @GetMapping("/culture-profiles")
    @Timed
    public List<CultureProfileDTO> getAllCultureProfiles(@RequestParam(required = false) String filter) {
        if ("userprofile-is-null".equals(filter)) {
            log.debug("REST request to get all CultureProfiles where userProfile is null");
            return StreamSupport
                .stream(cultureProfileRepository.findAll().spliterator(), false)
                .filter(cultureProfile -> cultureProfile.getUserProfile() == null)
                .map(cultureProfileMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        }
        log.debug("REST request to get all CultureProfiles");
        List<CultureProfile> cultureProfiles = cultureProfileRepository.findAll();
        return cultureProfileMapper.toDto(cultureProfiles);
        }

    /**
     * GET  /culture-profiles/:id : get the "id" cultureProfile.
     *
     * @param id the id of the cultureProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cultureProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/culture-profiles/{id}")
    @Timed
    public ResponseEntity<CultureProfileDTO> getCultureProfile(@PathVariable Long id) {
        log.debug("REST request to get CultureProfile : {}", id);
        CultureProfile cultureProfile = cultureProfileRepository.findOne(id);
        CultureProfileDTO cultureProfileDTO = cultureProfileMapper.toDto(cultureProfile);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cultureProfileDTO));
    }

    /**
     * DELETE  /culture-profiles/:id : delete the "id" cultureProfile.
     *
     * @param id the id of the cultureProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/culture-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteCultureProfile(@PathVariable Long id) {
        log.debug("REST request to delete CultureProfile : {}", id);
        cultureProfileRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
