package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.CompanyProfile;

import com.itharmony.repository.CompanyProfileRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.CompanyProfileDTO;
import com.itharmony.service.mapper.CompanyProfileMapper;
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
 * REST controller for managing CompanyProfile.
 */
@RestController
@RequestMapping("/api")
public class CompanyProfileResource {

    private final Logger log = LoggerFactory.getLogger(CompanyProfileResource.class);

    private static final String ENTITY_NAME = "companyProfile";

    private final CompanyProfileRepository companyProfileRepository;

    private final CompanyProfileMapper companyProfileMapper;

    public CompanyProfileResource(CompanyProfileRepository companyProfileRepository, CompanyProfileMapper companyProfileMapper) {
        this.companyProfileRepository = companyProfileRepository;
        this.companyProfileMapper = companyProfileMapper;
    }

    /**
     * POST  /company-profiles : Create a new companyProfile.
     *
     * @param companyProfileDTO the companyProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new companyProfileDTO, or with status 400 (Bad Request) if the companyProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/company-profiles")
    @Timed
    public ResponseEntity<CompanyProfileDTO> createCompanyProfile(@Valid @RequestBody CompanyProfileDTO companyProfileDTO) throws URISyntaxException {
        log.debug("REST request to save CompanyProfile : {}", companyProfileDTO);
        if (companyProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new companyProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompanyProfile companyProfile = companyProfileMapper.toEntity(companyProfileDTO);
        companyProfile = companyProfileRepository.save(companyProfile);
        CompanyProfileDTO result = companyProfileMapper.toDto(companyProfile);
        return ResponseEntity.created(new URI("/api/company-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /company-profiles : Updates an existing companyProfile.
     *
     * @param companyProfileDTO the companyProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated companyProfileDTO,
     * or with status 400 (Bad Request) if the companyProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the companyProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/company-profiles")
    @Timed
    public ResponseEntity<CompanyProfileDTO> updateCompanyProfile(@Valid @RequestBody CompanyProfileDTO companyProfileDTO) throws URISyntaxException {
        log.debug("REST request to update CompanyProfile : {}", companyProfileDTO);
        if (companyProfileDTO.getId() == null) {
            return createCompanyProfile(companyProfileDTO);
        }
        CompanyProfile companyProfile = companyProfileMapper.toEntity(companyProfileDTO);
        companyProfile = companyProfileRepository.save(companyProfile);
        CompanyProfileDTO result = companyProfileMapper.toDto(companyProfile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, companyProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /company-profiles : get all the companyProfiles.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of companyProfiles in body
     */
    @GetMapping("/company-profiles")
    @Timed
    public List<CompanyProfileDTO> getAllCompanyProfiles(@RequestParam(required = false) String filter) {
        if ("userprofile-is-null".equals(filter)) {
            log.debug("REST request to get all CompanyProfiles where userProfile is null");
            return StreamSupport
                .stream(companyProfileRepository.findAll().spliterator(), false)
                .filter(companyProfile -> companyProfile.getUserProfile() == null)
                .map(companyProfileMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        }
        log.debug("REST request to get all CompanyProfiles");
        List<CompanyProfile> companyProfiles = companyProfileRepository.findAll();
        return companyProfileMapper.toDto(companyProfiles);
        }

    /**
     * GET  /company-profiles/:id : get the "id" companyProfile.
     *
     * @param id the id of the companyProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/company-profiles/{id}")
    @Timed
    public ResponseEntity<CompanyProfileDTO> getCompanyProfile(@PathVariable Long id) {
        log.debug("REST request to get CompanyProfile : {}", id);
        CompanyProfile companyProfile = companyProfileRepository.findOne(id);
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(companyProfileDTO));
    }

    /**
     * DELETE  /company-profiles/:id : delete the "id" companyProfile.
     *
     * @param id the id of the companyProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/company-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompanyProfile(@PathVariable Long id) {
        log.debug("REST request to delete CompanyProfile : {}", id);
        companyProfileRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
