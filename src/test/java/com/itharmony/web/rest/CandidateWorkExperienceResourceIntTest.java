package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.CandidateWorkExperience;
import com.itharmony.repository.CandidateWorkExperienceRepository;
import com.itharmony.service.dto.CandidateWorkExperienceDTO;
import com.itharmony.service.mapper.CandidateWorkExperienceMapper;
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

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.itharmony.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CandidateWorkExperienceResource REST controller.
 *
 * @see CandidateWorkExperienceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class CandidateWorkExperienceResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CURRENT_JOB = false;
    private static final Boolean UPDATED_CURRENT_JOB = true;

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CandidateWorkExperienceRepository candidateWorkExperienceRepository;

    @Autowired
    private CandidateWorkExperienceMapper candidateWorkExperienceMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCandidateWorkExperienceMockMvc;

    private CandidateWorkExperience candidateWorkExperience;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CandidateWorkExperienceResource candidateWorkExperienceResource = new CandidateWorkExperienceResource(candidateWorkExperienceRepository, candidateWorkExperienceMapper);
        this.restCandidateWorkExperienceMockMvc = MockMvcBuilders.standaloneSetup(candidateWorkExperienceResource)
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
    public static CandidateWorkExperience createEntity(EntityManager em) {
        CandidateWorkExperience candidateWorkExperience = new CandidateWorkExperience()
            .companyName(DEFAULT_COMPANY_NAME)
            .position(DEFAULT_POSITION)
            .currentJob(DEFAULT_CURRENT_JOB)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .description(DEFAULT_DESCRIPTION);
        return candidateWorkExperience;
    }

    @Before
    public void initTest() {
        candidateWorkExperience = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidateWorkExperience() throws Exception {
        int databaseSizeBeforeCreate = candidateWorkExperienceRepository.findAll().size();

        // Create the CandidateWorkExperience
        CandidateWorkExperienceDTO candidateWorkExperienceDTO = candidateWorkExperienceMapper.toDto(candidateWorkExperience);
        restCandidateWorkExperienceMockMvc.perform(post("/api/candidate-work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateWorkExperienceDTO)))
            .andExpect(status().isCreated());

        // Validate the CandidateWorkExperience in the database
        List<CandidateWorkExperience> candidateWorkExperienceList = candidateWorkExperienceRepository.findAll();
        assertThat(candidateWorkExperienceList).hasSize(databaseSizeBeforeCreate + 1);
        CandidateWorkExperience testCandidateWorkExperience = candidateWorkExperienceList.get(candidateWorkExperienceList.size() - 1);
        assertThat(testCandidateWorkExperience.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testCandidateWorkExperience.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testCandidateWorkExperience.isCurrentJob()).isEqualTo(DEFAULT_CURRENT_JOB);
        assertThat(testCandidateWorkExperience.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testCandidateWorkExperience.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testCandidateWorkExperience.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCandidateWorkExperienceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidateWorkExperienceRepository.findAll().size();

        // Create the CandidateWorkExperience with an existing ID
        candidateWorkExperience.setId(1L);
        CandidateWorkExperienceDTO candidateWorkExperienceDTO = candidateWorkExperienceMapper.toDto(candidateWorkExperience);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidateWorkExperienceMockMvc.perform(post("/api/candidate-work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateWorkExperienceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CandidateWorkExperience in the database
        List<CandidateWorkExperience> candidateWorkExperienceList = candidateWorkExperienceRepository.findAll();
        assertThat(candidateWorkExperienceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCandidateWorkExperiences() throws Exception {
        // Initialize the database
        candidateWorkExperienceRepository.saveAndFlush(candidateWorkExperience);

        // Get all the candidateWorkExperienceList
        restCandidateWorkExperienceMockMvc.perform(get("/api/candidate-work-experiences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidateWorkExperience.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].currentJob").value(hasItem(DEFAULT_CURRENT_JOB.booleanValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getCandidateWorkExperience() throws Exception {
        // Initialize the database
        candidateWorkExperienceRepository.saveAndFlush(candidateWorkExperience);

        // Get the candidateWorkExperience
        restCandidateWorkExperienceMockMvc.perform(get("/api/candidate-work-experiences/{id}", candidateWorkExperience.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(candidateWorkExperience.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.currentJob").value(DEFAULT_CURRENT_JOB.booleanValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCandidateWorkExperience() throws Exception {
        // Get the candidateWorkExperience
        restCandidateWorkExperienceMockMvc.perform(get("/api/candidate-work-experiences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidateWorkExperience() throws Exception {
        // Initialize the database
        candidateWorkExperienceRepository.saveAndFlush(candidateWorkExperience);
        int databaseSizeBeforeUpdate = candidateWorkExperienceRepository.findAll().size();

        // Update the candidateWorkExperience
        CandidateWorkExperience updatedCandidateWorkExperience = candidateWorkExperienceRepository.findOne(candidateWorkExperience.getId());
        // Disconnect from session so that the updates on updatedCandidateWorkExperience are not directly saved in db
        em.detach(updatedCandidateWorkExperience);
        updatedCandidateWorkExperience
            .companyName(UPDATED_COMPANY_NAME)
            .position(UPDATED_POSITION)
            .currentJob(UPDATED_CURRENT_JOB)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .description(UPDATED_DESCRIPTION);
        CandidateWorkExperienceDTO candidateWorkExperienceDTO = candidateWorkExperienceMapper.toDto(updatedCandidateWorkExperience);

        restCandidateWorkExperienceMockMvc.perform(put("/api/candidate-work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateWorkExperienceDTO)))
            .andExpect(status().isOk());

        // Validate the CandidateWorkExperience in the database
        List<CandidateWorkExperience> candidateWorkExperienceList = candidateWorkExperienceRepository.findAll();
        assertThat(candidateWorkExperienceList).hasSize(databaseSizeBeforeUpdate);
        CandidateWorkExperience testCandidateWorkExperience = candidateWorkExperienceList.get(candidateWorkExperienceList.size() - 1);
        assertThat(testCandidateWorkExperience.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testCandidateWorkExperience.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testCandidateWorkExperience.isCurrentJob()).isEqualTo(UPDATED_CURRENT_JOB);
        assertThat(testCandidateWorkExperience.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testCandidateWorkExperience.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testCandidateWorkExperience.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidateWorkExperience() throws Exception {
        int databaseSizeBeforeUpdate = candidateWorkExperienceRepository.findAll().size();

        // Create the CandidateWorkExperience
        CandidateWorkExperienceDTO candidateWorkExperienceDTO = candidateWorkExperienceMapper.toDto(candidateWorkExperience);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCandidateWorkExperienceMockMvc.perform(put("/api/candidate-work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateWorkExperienceDTO)))
            .andExpect(status().isCreated());

        // Validate the CandidateWorkExperience in the database
        List<CandidateWorkExperience> candidateWorkExperienceList = candidateWorkExperienceRepository.findAll();
        assertThat(candidateWorkExperienceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCandidateWorkExperience() throws Exception {
        // Initialize the database
        candidateWorkExperienceRepository.saveAndFlush(candidateWorkExperience);
        int databaseSizeBeforeDelete = candidateWorkExperienceRepository.findAll().size();

        // Get the candidateWorkExperience
        restCandidateWorkExperienceMockMvc.perform(delete("/api/candidate-work-experiences/{id}", candidateWorkExperience.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CandidateWorkExperience> candidateWorkExperienceList = candidateWorkExperienceRepository.findAll();
        assertThat(candidateWorkExperienceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidateWorkExperience.class);
        CandidateWorkExperience candidateWorkExperience1 = new CandidateWorkExperience();
        candidateWorkExperience1.setId(1L);
        CandidateWorkExperience candidateWorkExperience2 = new CandidateWorkExperience();
        candidateWorkExperience2.setId(candidateWorkExperience1.getId());
        assertThat(candidateWorkExperience1).isEqualTo(candidateWorkExperience2);
        candidateWorkExperience2.setId(2L);
        assertThat(candidateWorkExperience1).isNotEqualTo(candidateWorkExperience2);
        candidateWorkExperience1.setId(null);
        assertThat(candidateWorkExperience1).isNotEqualTo(candidateWorkExperience2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidateWorkExperienceDTO.class);
        CandidateWorkExperienceDTO candidateWorkExperienceDTO1 = new CandidateWorkExperienceDTO();
        candidateWorkExperienceDTO1.setId(1L);
        CandidateWorkExperienceDTO candidateWorkExperienceDTO2 = new CandidateWorkExperienceDTO();
        assertThat(candidateWorkExperienceDTO1).isNotEqualTo(candidateWorkExperienceDTO2);
        candidateWorkExperienceDTO2.setId(candidateWorkExperienceDTO1.getId());
        assertThat(candidateWorkExperienceDTO1).isEqualTo(candidateWorkExperienceDTO2);
        candidateWorkExperienceDTO2.setId(2L);
        assertThat(candidateWorkExperienceDTO1).isNotEqualTo(candidateWorkExperienceDTO2);
        candidateWorkExperienceDTO1.setId(null);
        assertThat(candidateWorkExperienceDTO1).isNotEqualTo(candidateWorkExperienceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(candidateWorkExperienceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(candidateWorkExperienceMapper.fromId(null)).isNull();
    }
}
