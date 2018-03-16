package com.itharmony.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itharmony.domain.UserProfileExtra;

import com.itharmony.repository.UserProfileExtraRepository;
import com.itharmony.web.rest.errors.BadRequestAlertException;
import com.itharmony.web.rest.util.HeaderUtil;
import com.itharmony.service.dto.UserProfileExtraDTO;
import com.itharmony.service.mapper.UserProfileExtraMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserProfileExtra.
 */
@RestController
@RequestMapping("/api")
public class UserProfileExtraResource {

    private final Logger log = LoggerFactory.getLogger(UserProfileExtraResource.class);

    private static final String ENTITY_NAME = "userProfileExtra";

    private final UserProfileExtraRepository userProfileExtraRepository;

    private final UserProfileExtraMapper userProfileExtraMapper;

    public UserProfileExtraResource(UserProfileExtraRepository userProfileExtraRepository, UserProfileExtraMapper userProfileExtraMapper) {
        this.userProfileExtraRepository = userProfileExtraRepository;
        this.userProfileExtraMapper = userProfileExtraMapper;
    }

    /**
     * POST  /user-profile-extras : Create a new userProfileExtra.
     *
     * @param userProfileExtraDTO the userProfileExtraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userProfileExtraDTO, or with status 400 (Bad Request) if the userProfileExtra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-profile-extras")
    @Timed
    public ResponseEntity<UserProfileExtraDTO> createUserProfileExtra(@Valid @RequestBody UserProfileExtraDTO userProfileExtraDTO) throws URISyntaxException {
        log.debug("REST request to save UserProfileExtra : {}", userProfileExtraDTO);
        if (userProfileExtraDTO.getId() != null) {
            throw new BadRequestAlertException("A new userProfileExtra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserProfileExtra userProfileExtra = userProfileExtraMapper.toEntity(userProfileExtraDTO);
        userProfileExtra = userProfileExtraRepository.save(userProfileExtra);
        UserProfileExtraDTO result = userProfileExtraMapper.toDto(userProfileExtra);
        return ResponseEntity.created(new URI("/api/user-profile-extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-profile-extras : Updates an existing userProfileExtra.
     *
     * @param userProfileExtraDTO the userProfileExtraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userProfileExtraDTO,
     * or with status 400 (Bad Request) if the userProfileExtraDTO is not valid,
     * or with status 500 (Internal Server Error) if the userProfileExtraDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-profile-extras")
    @Timed
    public ResponseEntity<UserProfileExtraDTO> updateUserProfileExtra(@Valid @RequestBody UserProfileExtraDTO userProfileExtraDTO) throws URISyntaxException {
        log.debug("REST request to update UserProfileExtra : {}", userProfileExtraDTO);
        if (userProfileExtraDTO.getId() == null) {
            return createUserProfileExtra(userProfileExtraDTO);
        }
        UserProfileExtra userProfileExtra = userProfileExtraMapper.toEntity(userProfileExtraDTO);
        userProfileExtra = userProfileExtraRepository.save(userProfileExtra);
        UserProfileExtraDTO result = userProfileExtraMapper.toDto(userProfileExtra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userProfileExtraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-profile-extras : get all the userProfileExtras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userProfileExtras in body
     */
    @GetMapping("/user-profile-extras")
    @Timed
    public List<UserProfileExtraDTO> getAllUserProfileExtras() {
        log.debug("REST request to get all UserProfileExtras");
        List<UserProfileExtra> userProfileExtras = userProfileExtraRepository.findAll();
        return userProfileExtraMapper.toDto(userProfileExtras);
        }

    /**
     * GET  /user-profile-extras/:id : get the "id" userProfileExtra.
     *
     * @param id the id of the userProfileExtraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userProfileExtraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-profile-extras/{id}")
    @Timed
    public ResponseEntity<UserProfileExtraDTO> getUserProfileExtra(@PathVariable Long id) {
        log.debug("REST request to get UserProfileExtra : {}", id);
        UserProfileExtra userProfileExtra = userProfileExtraRepository.findOne(id);
        UserProfileExtraDTO userProfileExtraDTO = userProfileExtraMapper.toDto(userProfileExtra);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userProfileExtraDTO));
    }

    /**
     * DELETE  /user-profile-extras/:id : delete the "id" userProfileExtra.
     *
     * @param id the id of the userProfileExtraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-profile-extras/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserProfileExtra(@PathVariable Long id) {
        log.debug("REST request to delete UserProfileExtra : {}", id);
        userProfileExtraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
