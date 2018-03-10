package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.SkillsProfile;

import com.itharmony.repository.SkillsProfileRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.SkillsProfileDTO;
import com.itharmony.service.mapper.SkillsProfileMapper;
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
 * REST controller for managing SkillsProfile.
 */
@RestController
@RequestMapping("/api")
public class SkillsProfileResource {

    private final Logger log = LoggerFactory.getLogger(SkillsProfileResource.class);

    private static final String ENTITY_NAME = "skillsProfile";

    private final SkillsProfileRepository skillsProfileRepository;

    private final SkillsProfileMapper skillsProfileMapper;

    public SkillsProfileResource(SkillsProfileRepository skillsProfileRepository, SkillsProfileMapper skillsProfileMapper) {
        this.skillsProfileRepository = skillsProfileRepository;
        this.skillsProfileMapper = skillsProfileMapper;
    }

    /**
     * POST  /skills-profiles : Create a new skillsProfile.
     *
     * @param skillsProfileDTO the skillsProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new skillsProfileDTO, or with status 400 (Bad Request) if the skillsProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/skills-profiles")
    @Timed
    public ResponseEntity<SkillsProfileDTO> createSkillsProfile(@RequestBody SkillsProfileDTO skillsProfileDTO) throws URISyntaxException {
        log.debug("REST request to save SkillsProfile : {}", skillsProfileDTO);
        if (skillsProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new skillsProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillsProfile skillsProfile = skillsProfileMapper.toEntity(skillsProfileDTO);
        skillsProfile = skillsProfileRepository.save(skillsProfile);
        SkillsProfileDTO result = skillsProfileMapper.toDto(skillsProfile);
        return ResponseEntity.created(new URI("/api/skills-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /skills-profiles : Updates an existing skillsProfile.
     *
     * @param skillsProfileDTO the skillsProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated skillsProfileDTO,
     * or with status 400 (Bad Request) if the skillsProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the skillsProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/skills-profiles")
    @Timed
    public ResponseEntity<SkillsProfileDTO> updateSkillsProfile(@RequestBody SkillsProfileDTO skillsProfileDTO) throws URISyntaxException {
        log.debug("REST request to update SkillsProfile : {}", skillsProfileDTO);
        if (skillsProfileDTO.getId() == null) {
            return createSkillsProfile(skillsProfileDTO);
        }
        SkillsProfile skillsProfile = skillsProfileMapper.toEntity(skillsProfileDTO);
        skillsProfile = skillsProfileRepository.save(skillsProfile);
        SkillsProfileDTO result = skillsProfileMapper.toDto(skillsProfile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, skillsProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /skills-profiles : get all the skillsProfiles.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of skillsProfiles in body
     */
    @GetMapping("/skills-profiles")
    @Timed
    public List<SkillsProfileDTO> getAllSkillsProfiles(@RequestParam(required = false) String filter) {
        if ("jobpost-is-null".equals(filter)) {
            log.debug("REST request to get all SkillsProfiles where jobPost is null");
            return StreamSupport
                .stream(skillsProfileRepository.findAll().spliterator(), false)
                .filter(skillsProfile -> skillsProfile.getJobPost() == null)
                .map(skillsProfileMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        }
        log.debug("REST request to get all SkillsProfiles");
        List<SkillsProfile> skillsProfiles = skillsProfileRepository.findAll();
        return skillsProfileMapper.toDto(skillsProfiles);
        }

    /**
     * GET  /skills-profiles/:id : get the "id" skillsProfile.
     *
     * @param id the id of the skillsProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the skillsProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/skills-profiles/{id}")
    @Timed
    public ResponseEntity<SkillsProfileDTO> getSkillsProfile(@PathVariable Long id) {
        log.debug("REST request to get SkillsProfile : {}", id);
        SkillsProfile skillsProfile = skillsProfileRepository.findOne(id);
        SkillsProfileDTO skillsProfileDTO = skillsProfileMapper.toDto(skillsProfile);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(skillsProfileDTO));
    }

    /**
     * DELETE  /skills-profiles/:id : delete the "id" skillsProfile.
     *
     * @param id the id of the skillsProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/skills-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteSkillsProfile(@PathVariable Long id) {
        log.debug("REST request to delete SkillsProfile : {}", id);
        skillsProfileRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
