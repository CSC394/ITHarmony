package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.CandidateProfile;
import com.itharmony.repository.CandidateProfileRepository;
import com.itharmony.service.dto.CandidateProfileDTO;
import com.itharmony.service.mapper.CandidateProfileMapper;
import com.itharmony.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.itharmony.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.itharmony.domain.enumeration.JobType;
/**
 * Test class for the CandidateProfileResource REST controller.
 *
 * @see CandidateProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class CandidateProfileResourceIntTest {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final JobType DEFAULT_DESIRED_JOB_TYPE = JobType.INTERNSHIP;
    private static final JobType UPDATED_DESIRED_JOB_TYPE = JobType.PARTTIME;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_LINKED_IN = "AAAAAAAAAA";
    private static final String UPDATED_LINKED_IN = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_GITHUB = "AAAAAAAAAA";
    private static final String UPDATED_GITHUB = "BBBBBBBBBB";

    private static final byte[] DEFAULT_RESUME = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RESUME = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_RESUME_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RESUME_CONTENT_TYPE = "image/png";

    @Autowired
    private CandidateProfileRepository candidateProfileRepository;

    @Autowired
    private CandidateProfileMapper candidateProfileMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCandidateProfileMockMvc;

    private CandidateProfile candidateProfile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CandidateProfileResource candidateProfileResource = new CandidateProfileResource(candidateProfileRepository, candidateProfileMapper);
        this.restCandidateProfileMockMvc = MockMvcBuilders.standaloneSetup(candidateProfileResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CandidateProfile createEntity(EntityManager em) {
        CandidateProfile candidateProfile = new CandidateProfile()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .desiredJobType(DEFAULT_DESIRED_JOB_TYPE)
            .email(DEFAULT_EMAIL)
            .linkedIn(DEFAULT_LINKED_IN)
            .website(DEFAULT_WEBSITE)
            .github(DEFAULT_GITHUB)
            .resume(DEFAULT_RESUME)
            .resumeContentType(DEFAULT_RESUME_CONTENT_TYPE);
        return candidateProfile;
    }

    @Before
    public void initTest() {
        candidateProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidateProfile() throws Exception {
        int databaseSizeBeforeCreate = candidateProfileRepository.findAll().size();

        // Create the CandidateProfile
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);
        restCandidateProfileMockMvc.perform(post("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the CandidateProfile in the database
        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeCreate + 1);
        CandidateProfile testCandidateProfile = candidateProfileList.get(candidateProfileList.size() - 1);
        assertThat(testCandidateProfile.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCandidateProfile.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testCandidateProfile.getDesiredJobType()).isEqualTo(DEFAULT_DESIRED_JOB_TYPE);
        assertThat(testCandidateProfile.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCandidateProfile.getLinkedIn()).isEqualTo(DEFAULT_LINKED_IN);
        assertThat(testCandidateProfile.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testCandidateProfile.getGithub()).isEqualTo(DEFAULT_GITHUB);
        assertThat(testCandidateProfile.getResume()).isEqualTo(DEFAULT_RESUME);
        assertThat(testCandidateProfile.getResumeContentType()).isEqualTo(DEFAULT_RESUME_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createCandidateProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidateProfileRepository.findAll().size();

        // Create the CandidateProfile with an existing ID
        candidateProfile.setId(1L);
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidateProfileMockMvc.perform(post("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CandidateProfile in the database
        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidateProfileRepository.findAll().size();
        // set the field null
        candidateProfile.setFirstName(null);

        // Create the CandidateProfile, which fails.
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);

        restCandidateProfileMockMvc.perform(post("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isBadRequest());

        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidateProfileRepository.findAll().size();
        // set the field null
        candidateProfile.setLastName(null);

        // Create the CandidateProfile, which fails.
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);

        restCandidateProfileMockMvc.perform(post("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isBadRequest());

        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesiredJobTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidateProfileRepository.findAll().size();
        // set the field null
        candidateProfile.setDesiredJobType(null);

        // Create the CandidateProfile, which fails.
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);

        restCandidateProfileMockMvc.perform(post("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isBadRequest());

        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidateProfileRepository.findAll().size();
        // set the field null
        candidateProfile.setEmail(null);

        // Create the CandidateProfile, which fails.
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);

        restCandidateProfileMockMvc.perform(post("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isBadRequest());

        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCandidateProfiles() throws Exception {
        // Initialize the database
        candidateProfileRepository.saveAndFlush(candidateProfile);

        // Get all the candidateProfileList
        restCandidateProfileMockMvc.perform(get("/api/candidate-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidateProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].desiredJobType").value(hasItem(DEFAULT_DESIRED_JOB_TYPE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].linkedIn").value(hasItem(DEFAULT_LINKED_IN.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].github").value(hasItem(DEFAULT_GITHUB.toString())))
            .andExpect(jsonPath("$.[*].resumeContentType").value(hasItem(DEFAULT_RESUME_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].resume").value(hasItem(Base64Utils.encodeToString(DEFAULT_RESUME))));
    }

    @Test
    @Transactional
    public void getCandidateProfile() throws Exception {
        // Initialize the database
        candidateProfileRepository.saveAndFlush(candidateProfile);

        // Get the candidateProfile
        restCandidateProfileMockMvc.perform(get("/api/candidate-profiles/{id}", candidateProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(candidateProfile.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.desiredJobType").value(DEFAULT_DESIRED_JOB_TYPE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.linkedIn").value(DEFAULT_LINKED_IN.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.github").value(DEFAULT_GITHUB.toString()))
            .andExpect(jsonPath("$.resumeContentType").value(DEFAULT_RESUME_CONTENT_TYPE))
            .andExpect(jsonPath("$.resume").value(Base64Utils.encodeToString(DEFAULT_RESUME)));
    }

    @Test
    @Transactional
    public void getNonExistingCandidateProfile() throws Exception {
        // Get the candidateProfile
        restCandidateProfileMockMvc.perform(get("/api/candidate-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidateProfile() throws Exception {
        // Initialize the database
        candidateProfileRepository.saveAndFlush(candidateProfile);
        int databaseSizeBeforeUpdate = candidateProfileRepository.findAll().size();

        // Update the candidateProfile
        CandidateProfile updatedCandidateProfile = candidateProfileRepository.findOne(candidateProfile.getId());
        // Disconnect from session so that the updates on updatedCandidateProfile are not directly saved in db
        em.detach(updatedCandidateProfile);
        updatedCandidateProfile
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .desiredJobType(UPDATED_DESIRED_JOB_TYPE)
            .email(UPDATED_EMAIL)
            .linkedIn(UPDATED_LINKED_IN)
            .website(UPDATED_WEBSITE)
            .github(UPDATED_GITHUB)
            .resume(UPDATED_RESUME)
            .resumeContentType(UPDATED_RESUME_CONTENT_TYPE);
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(updatedCandidateProfile);

        restCandidateProfileMockMvc.perform(put("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isOk());

        // Validate the CandidateProfile in the database
        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeUpdate);
        CandidateProfile testCandidateProfile = candidateProfileList.get(candidateProfileList.size() - 1);
        assertThat(testCandidateProfile.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCandidateProfile.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testCandidateProfile.getDesiredJobType()).isEqualTo(UPDATED_DESIRED_JOB_TYPE);
        assertThat(testCandidateProfile.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCandidateProfile.getLinkedIn()).isEqualTo(UPDATED_LINKED_IN);
        assertThat(testCandidateProfile.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testCandidateProfile.getGithub()).isEqualTo(UPDATED_GITHUB);
        assertThat(testCandidateProfile.getResume()).isEqualTo(UPDATED_RESUME);
        assertThat(testCandidateProfile.getResumeContentType()).isEqualTo(UPDATED_RESUME_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidateProfile() throws Exception {
        int databaseSizeBeforeUpdate = candidateProfileRepository.findAll().size();

        // Create the CandidateProfile
        CandidateProfileDTO candidateProfileDTO = candidateProfileMapper.toDto(candidateProfile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCandidateProfileMockMvc.perform(put("/api/candidate-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the CandidateProfile in the database
        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCandidateProfile() throws Exception {
        // Initialize the database
        candidateProfileRepository.saveAndFlush(candidateProfile);
        int databaseSizeBeforeDelete = candidateProfileRepository.findAll().size();

        // Get the candidateProfile
        restCandidateProfileMockMvc.perform(delete("/api/candidate-profiles/{id}", candidateProfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CandidateProfile> candidateProfileList = candidateProfileRepository.findAll();
        assertThat(candidateProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidateProfile.class);
        CandidateProfile candidateProfile1 = new CandidateProfile();
        candidateProfile1.setId(1L);
        CandidateProfile candidateProfile2 = new CandidateProfile();
        candidateProfile2.setId(candidateProfile1.getId());
        assertThat(candidateProfile1).isEqualTo(candidateProfile2);
        candidateProfile2.setId(2L);
        assertThat(candidateProfile1).isNotEqualTo(candidateProfile2);
        candidateProfile1.setId(null);
        assertThat(candidateProfile1).isNotEqualTo(candidateProfile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidateProfileDTO.class);
        CandidateProfileDTO candidateProfileDTO1 = new CandidateProfileDTO();
        candidateProfileDTO1.setId(1L);
        CandidateProfileDTO candidateProfileDTO2 = new CandidateProfileDTO();
        assertThat(candidateProfileDTO1).isNotEqualTo(candidateProfileDTO2);
        candidateProfileDTO2.setId(candidateProfileDTO1.getId());
        assertThat(candidateProfileDTO1).isEqualTo(candidateProfileDTO2);
        candidateProfileDTO2.setId(2L);
        assertThat(candidateProfileDTO1).isNotEqualTo(candidateProfileDTO2);
        candidateProfileDTO1.setId(null);
        assertThat(candidateProfileDTO1).isNotEqualTo(candidateProfileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(candidateProfileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(candidateProfileMapper.fromId(null)).isNull();
    }
}
