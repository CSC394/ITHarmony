package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.JobMatch;
import com.itharmony.repository.JobMatchRepository;
import com.itharmony.service.dto.JobMatchDTO;
import com.itharmony.service.mapper.JobMatchMapper;
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
import java.util.List;

import static com.itharmony.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JobMatchResource REST controller.
 *
 * @see JobMatchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class JobMatchResourceIntTest {

    private static final Integer DEFAULT_SKILL_RANK = 1;
    private static final Integer UPDATED_SKILL_RANK = 2;

    private static final Integer DEFAULT_CULTURE_RANK = 1;
    private static final Integer UPDATED_CULTURE_RANK = 2;

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Boolean DEFAULT_CANDIDATE_APPLIED = false;
    private static final Boolean UPDATED_CANDIDATE_APPLIED = true;

    private static final Boolean DEFAULT_COMPANY_REJECTED = false;
    private static final Boolean UPDATED_COMPANY_REJECTED = true;

    @Autowired
    private JobMatchRepository jobMatchRepository;

    @Autowired
    private JobMatchMapper jobMatchMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJobMatchMockMvc;

    private JobMatch jobMatch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JobMatchResource jobMatchResource = new JobMatchResource(jobMatchRepository, jobMatchMapper);
        this.restJobMatchMockMvc = MockMvcBuilders.standaloneSetup(jobMatchResource)
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
    public static JobMatch createEntity(EntityManager em) {
        JobMatch jobMatch = new JobMatch()
            .skillRank(DEFAULT_SKILL_RANK)
            .cultureRank(DEFAULT_CULTURE_RANK)
            .active(DEFAULT_ACTIVE)
            .candidateApplied(DEFAULT_CANDIDATE_APPLIED)
            .companyRejected(DEFAULT_COMPANY_REJECTED);
        return jobMatch;
    }

    @Before
    public void initTest() {
        jobMatch = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobMatch() throws Exception {
        int databaseSizeBeforeCreate = jobMatchRepository.findAll().size();

        // Create the JobMatch
        JobMatchDTO jobMatchDTO = jobMatchMapper.toDto(jobMatch);
        restJobMatchMockMvc.perform(post("/api/job-matches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobMatchDTO)))
            .andExpect(status().isCreated());

        // Validate the JobMatch in the database
        List<JobMatch> jobMatchList = jobMatchRepository.findAll();
        assertThat(jobMatchList).hasSize(databaseSizeBeforeCreate + 1);
        JobMatch testJobMatch = jobMatchList.get(jobMatchList.size() - 1);
        assertThat(testJobMatch.getSkillRank()).isEqualTo(DEFAULT_SKILL_RANK);
        assertThat(testJobMatch.getCultureRank()).isEqualTo(DEFAULT_CULTURE_RANK);
        assertThat(testJobMatch.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testJobMatch.isCandidateApplied()).isEqualTo(DEFAULT_CANDIDATE_APPLIED);
        assertThat(testJobMatch.isCompanyRejected()).isEqualTo(DEFAULT_COMPANY_REJECTED);
    }

    @Test
    @Transactional
    public void createJobMatchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobMatchRepository.findAll().size();

        // Create the JobMatch with an existing ID
        jobMatch.setId(1L);
        JobMatchDTO jobMatchDTO = jobMatchMapper.toDto(jobMatch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobMatchMockMvc.perform(post("/api/job-matches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobMatchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobMatch in the database
        List<JobMatch> jobMatchList = jobMatchRepository.findAll();
        assertThat(jobMatchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllJobMatches() throws Exception {
        // Initialize the database
        jobMatchRepository.saveAndFlush(jobMatch);

        // Get all the jobMatchList
        restJobMatchMockMvc.perform(get("/api/job-matches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobMatch.getId().intValue())))
            .andExpect(jsonPath("$.[*].skillRank").value(hasItem(DEFAULT_SKILL_RANK)))
            .andExpect(jsonPath("$.[*].cultureRank").value(hasItem(DEFAULT_CULTURE_RANK)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].candidateApplied").value(hasItem(DEFAULT_CANDIDATE_APPLIED.booleanValue())))
            .andExpect(jsonPath("$.[*].companyRejected").value(hasItem(DEFAULT_COMPANY_REJECTED.booleanValue())));
    }

    @Test
    @Transactional
    public void getJobMatch() throws Exception {
        // Initialize the database
        jobMatchRepository.saveAndFlush(jobMatch);

        // Get the jobMatch
        restJobMatchMockMvc.perform(get("/api/job-matches/{id}", jobMatch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jobMatch.getId().intValue()))
            .andExpect(jsonPath("$.skillRank").value(DEFAULT_SKILL_RANK))
            .andExpect(jsonPath("$.cultureRank").value(DEFAULT_CULTURE_RANK))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.candidateApplied").value(DEFAULT_CANDIDATE_APPLIED.booleanValue()))
            .andExpect(jsonPath("$.companyRejected").value(DEFAULT_COMPANY_REJECTED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingJobMatch() throws Exception {
        // Get the jobMatch
        restJobMatchMockMvc.perform(get("/api/job-matches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobMatch() throws Exception {
        // Initialize the database
        jobMatchRepository.saveAndFlush(jobMatch);
        int databaseSizeBeforeUpdate = jobMatchRepository.findAll().size();

        // Update the jobMatch
        JobMatch updatedJobMatch = jobMatchRepository.findOne(jobMatch.getId());
        // Disconnect from session so that the updates on updatedJobMatch are not directly saved in db
        em.detach(updatedJobMatch);
        updatedJobMatch
            .skillRank(UPDATED_SKILL_RANK)
            .cultureRank(UPDATED_CULTURE_RANK)
            .active(UPDATED_ACTIVE)
            .candidateApplied(UPDATED_CANDIDATE_APPLIED)
            .companyRejected(UPDATED_COMPANY_REJECTED);
        JobMatchDTO jobMatchDTO = jobMatchMapper.toDto(updatedJobMatch);

        restJobMatchMockMvc.perform(put("/api/job-matches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobMatchDTO)))
            .andExpect(status().isOk());

        // Validate the JobMatch in the database
        List<JobMatch> jobMatchList = jobMatchRepository.findAll();
        assertThat(jobMatchList).hasSize(databaseSizeBeforeUpdate);
        JobMatch testJobMatch = jobMatchList.get(jobMatchList.size() - 1);
        assertThat(testJobMatch.getSkillRank()).isEqualTo(UPDATED_SKILL_RANK);
        assertThat(testJobMatch.getCultureRank()).isEqualTo(UPDATED_CULTURE_RANK);
        assertThat(testJobMatch.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testJobMatch.isCandidateApplied()).isEqualTo(UPDATED_CANDIDATE_APPLIED);
        assertThat(testJobMatch.isCompanyRejected()).isEqualTo(UPDATED_COMPANY_REJECTED);
    }

    @Test
    @Transactional
    public void updateNonExistingJobMatch() throws Exception {
        int databaseSizeBeforeUpdate = jobMatchRepository.findAll().size();

        // Create the JobMatch
        JobMatchDTO jobMatchDTO = jobMatchMapper.toDto(jobMatch);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJobMatchMockMvc.perform(put("/api/job-matches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobMatchDTO)))
            .andExpect(status().isCreated());

        // Validate the JobMatch in the database
        List<JobMatch> jobMatchList = jobMatchRepository.findAll();
        assertThat(jobMatchList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJobMatch() throws Exception {
        // Initialize the database
        jobMatchRepository.saveAndFlush(jobMatch);
        int databaseSizeBeforeDelete = jobMatchRepository.findAll().size();

        // Get the jobMatch
        restJobMatchMockMvc.perform(delete("/api/job-matches/{id}", jobMatch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JobMatch> jobMatchList = jobMatchRepository.findAll();
        assertThat(jobMatchList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobMatch.class);
        JobMatch jobMatch1 = new JobMatch();
        jobMatch1.setId(1L);
        JobMatch jobMatch2 = new JobMatch();
        jobMatch2.setId(jobMatch1.getId());
        assertThat(jobMatch1).isEqualTo(jobMatch2);
        jobMatch2.setId(2L);
        assertThat(jobMatch1).isNotEqualTo(jobMatch2);
        jobMatch1.setId(null);
        assertThat(jobMatch1).isNotEqualTo(jobMatch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobMatchDTO.class);
        JobMatchDTO jobMatchDTO1 = new JobMatchDTO();
        jobMatchDTO1.setId(1L);
        JobMatchDTO jobMatchDTO2 = new JobMatchDTO();
        assertThat(jobMatchDTO1).isNotEqualTo(jobMatchDTO2);
        jobMatchDTO2.setId(jobMatchDTO1.getId());
        assertThat(jobMatchDTO1).isEqualTo(jobMatchDTO2);
        jobMatchDTO2.setId(2L);
        assertThat(jobMatchDTO1).isNotEqualTo(jobMatchDTO2);
        jobMatchDTO1.setId(null);
        assertThat(jobMatchDTO1).isNotEqualTo(jobMatchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(jobMatchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(jobMatchMapper.fromId(null)).isNull();
    }
}
