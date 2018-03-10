package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.CandidateEducation;
import com.itharmony.repository.CandidateEducationRepository;
import com.itharmony.service.dto.CandidateEducationDTO;
import com.itharmony.service.mapper.CandidateEducationMapper;
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

import com.itharmony.domain.enumeration.DegreeType;
/**
 * Test class for the CandidateEducationResource REST controller.
 *
 * @see CandidateEducationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class CandidateEducationResourceIntTest {

    private static final String DEFAULT_SCHOOL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL_NAME = "BBBBBBBBBB";

    private static final DegreeType DEFAULT_DEGREE_TYPE = DegreeType.ASSOCIATES;
    private static final DegreeType UPDATED_DEGREE_TYPE = DegreeType.BACHELOR;

    private static final Boolean DEFAULT_IN_PROGRESS = false;
    private static final Boolean UPDATED_IN_PROGRESS = true;

    private static final LocalDate DEFAULT_GRADUATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_GRADUATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MAJOR = "AAAAAAAAAA";
    private static final String UPDATED_MAJOR = "BBBBBBBBBB";

    private static final Float DEFAULT_GPA = 1F;
    private static final Float UPDATED_GPA = 2F;

    @Autowired
    private CandidateEducationRepository candidateEducationRepository;

    @Autowired
    private CandidateEducationMapper candidateEducationMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCandidateEducationMockMvc;

    private CandidateEducation candidateEducation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CandidateEducationResource candidateEducationResource = new CandidateEducationResource(candidateEducationRepository, candidateEducationMapper);
        this.restCandidateEducationMockMvc = MockMvcBuilders.standaloneSetup(candidateEducationResource)
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
    public static CandidateEducation createEntity(EntityManager em) {
        CandidateEducation candidateEducation = new CandidateEducation()
            .schoolName(DEFAULT_SCHOOL_NAME)
            .degreeType(DEFAULT_DEGREE_TYPE)
            .inProgress(DEFAULT_IN_PROGRESS)
            .graduationDate(DEFAULT_GRADUATION_DATE)
            .major(DEFAULT_MAJOR)
            .gpa(DEFAULT_GPA);
        return candidateEducation;
    }

    @Before
    public void initTest() {
        candidateEducation = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidateEducation() throws Exception {
        int databaseSizeBeforeCreate = candidateEducationRepository.findAll().size();

        // Create the CandidateEducation
        CandidateEducationDTO candidateEducationDTO = candidateEducationMapper.toDto(candidateEducation);
        restCandidateEducationMockMvc.perform(post("/api/candidate-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateEducationDTO)))
            .andExpect(status().isCreated());

        // Validate the CandidateEducation in the database
        List<CandidateEducation> candidateEducationList = candidateEducationRepository.findAll();
        assertThat(candidateEducationList).hasSize(databaseSizeBeforeCreate + 1);
        CandidateEducation testCandidateEducation = candidateEducationList.get(candidateEducationList.size() - 1);
        assertThat(testCandidateEducation.getSchoolName()).isEqualTo(DEFAULT_SCHOOL_NAME);
        assertThat(testCandidateEducation.getDegreeType()).isEqualTo(DEFAULT_DEGREE_TYPE);
        assertThat(testCandidateEducation.isInProgress()).isEqualTo(DEFAULT_IN_PROGRESS);
        assertThat(testCandidateEducation.getGraduationDate()).isEqualTo(DEFAULT_GRADUATION_DATE);
        assertThat(testCandidateEducation.getMajor()).isEqualTo(DEFAULT_MAJOR);
        assertThat(testCandidateEducation.getGpa()).isEqualTo(DEFAULT_GPA);
    }

    @Test
    @Transactional
    public void createCandidateEducationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidateEducationRepository.findAll().size();

        // Create the CandidateEducation with an existing ID
        candidateEducation.setId(1L);
        CandidateEducationDTO candidateEducationDTO = candidateEducationMapper.toDto(candidateEducation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidateEducationMockMvc.perform(post("/api/candidate-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateEducationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CandidateEducation in the database
        List<CandidateEducation> candidateEducationList = candidateEducationRepository.findAll();
        assertThat(candidateEducationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCandidateEducations() throws Exception {
        // Initialize the database
        candidateEducationRepository.saveAndFlush(candidateEducation);

        // Get all the candidateEducationList
        restCandidateEducationMockMvc.perform(get("/api/candidate-educations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidateEducation.getId().intValue())))
            .andExpect(jsonPath("$.[*].schoolName").value(hasItem(DEFAULT_SCHOOL_NAME.toString())))
            .andExpect(jsonPath("$.[*].degreeType").value(hasItem(DEFAULT_DEGREE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].inProgress").value(hasItem(DEFAULT_IN_PROGRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].graduationDate").value(hasItem(DEFAULT_GRADUATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].major").value(hasItem(DEFAULT_MAJOR.toString())))
            .andExpect(jsonPath("$.[*].gpa").value(hasItem(DEFAULT_GPA.doubleValue())));
    }

    @Test
    @Transactional
    public void getCandidateEducation() throws Exception {
        // Initialize the database
        candidateEducationRepository.saveAndFlush(candidateEducation);

        // Get the candidateEducation
        restCandidateEducationMockMvc.perform(get("/api/candidate-educations/{id}", candidateEducation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(candidateEducation.getId().intValue()))
            .andExpect(jsonPath("$.schoolName").value(DEFAULT_SCHOOL_NAME.toString()))
            .andExpect(jsonPath("$.degreeType").value(DEFAULT_DEGREE_TYPE.toString()))
            .andExpect(jsonPath("$.inProgress").value(DEFAULT_IN_PROGRESS.booleanValue()))
            .andExpect(jsonPath("$.graduationDate").value(DEFAULT_GRADUATION_DATE.toString()))
            .andExpect(jsonPath("$.major").value(DEFAULT_MAJOR.toString()))
            .andExpect(jsonPath("$.gpa").value(DEFAULT_GPA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCandidateEducation() throws Exception {
        // Get the candidateEducation
        restCandidateEducationMockMvc.perform(get("/api/candidate-educations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidateEducation() throws Exception {
        // Initialize the database
        candidateEducationRepository.saveAndFlush(candidateEducation);
        int databaseSizeBeforeUpdate = candidateEducationRepository.findAll().size();

        // Update the candidateEducation
        CandidateEducation updatedCandidateEducation = candidateEducationRepository.findOne(candidateEducation.getId());
        // Disconnect from session so that the updates on updatedCandidateEducation are not directly saved in db
        em.detach(updatedCandidateEducation);
        updatedCandidateEducation
            .schoolName(UPDATED_SCHOOL_NAME)
            .degreeType(UPDATED_DEGREE_TYPE)
            .inProgress(UPDATED_IN_PROGRESS)
            .graduationDate(UPDATED_GRADUATION_DATE)
            .major(UPDATED_MAJOR)
            .gpa(UPDATED_GPA);
        CandidateEducationDTO candidateEducationDTO = candidateEducationMapper.toDto(updatedCandidateEducation);

        restCandidateEducationMockMvc.perform(put("/api/candidate-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateEducationDTO)))
            .andExpect(status().isOk());

        // Validate the CandidateEducation in the database
        List<CandidateEducation> candidateEducationList = candidateEducationRepository.findAll();
        assertThat(candidateEducationList).hasSize(databaseSizeBeforeUpdate);
        CandidateEducation testCandidateEducation = candidateEducationList.get(candidateEducationList.size() - 1);
        assertThat(testCandidateEducation.getSchoolName()).isEqualTo(UPDATED_SCHOOL_NAME);
        assertThat(testCandidateEducation.getDegreeType()).isEqualTo(UPDATED_DEGREE_TYPE);
        assertThat(testCandidateEducation.isInProgress()).isEqualTo(UPDATED_IN_PROGRESS);
        assertThat(testCandidateEducation.getGraduationDate()).isEqualTo(UPDATED_GRADUATION_DATE);
        assertThat(testCandidateEducation.getMajor()).isEqualTo(UPDATED_MAJOR);
        assertThat(testCandidateEducation.getGpa()).isEqualTo(UPDATED_GPA);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidateEducation() throws Exception {
        int databaseSizeBeforeUpdate = candidateEducationRepository.findAll().size();

        // Create the CandidateEducation
        CandidateEducationDTO candidateEducationDTO = candidateEducationMapper.toDto(candidateEducation);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCandidateEducationMockMvc.perform(put("/api/candidate-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidateEducationDTO)))
            .andExpect(status().isCreated());

        // Validate the CandidateEducation in the database
        List<CandidateEducation> candidateEducationList = candidateEducationRepository.findAll();
        assertThat(candidateEducationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCandidateEducation() throws Exception {
        // Initialize the database
        candidateEducationRepository.saveAndFlush(candidateEducation);
        int databaseSizeBeforeDelete = candidateEducationRepository.findAll().size();

        // Get the candidateEducation
        restCandidateEducationMockMvc.perform(delete("/api/candidate-educations/{id}", candidateEducation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CandidateEducation> candidateEducationList = candidateEducationRepository.findAll();
        assertThat(candidateEducationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidateEducation.class);
        CandidateEducation candidateEducation1 = new CandidateEducation();
        candidateEducation1.setId(1L);
        CandidateEducation candidateEducation2 = new CandidateEducation();
        candidateEducation2.setId(candidateEducation1.getId());
        assertThat(candidateEducation1).isEqualTo(candidateEducation2);
        candidateEducation2.setId(2L);
        assertThat(candidateEducation1).isNotEqualTo(candidateEducation2);
        candidateEducation1.setId(null);
        assertThat(candidateEducation1).isNotEqualTo(candidateEducation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidateEducationDTO.class);
        CandidateEducationDTO candidateEducationDTO1 = new CandidateEducationDTO();
        candidateEducationDTO1.setId(1L);
        CandidateEducationDTO candidateEducationDTO2 = new CandidateEducationDTO();
        assertThat(candidateEducationDTO1).isNotEqualTo(candidateEducationDTO2);
        candidateEducationDTO2.setId(candidateEducationDTO1.getId());
        assertThat(candidateEducationDTO1).isEqualTo(candidateEducationDTO2);
        candidateEducationDTO2.setId(2L);
        assertThat(candidateEducationDTO1).isNotEqualTo(candidateEducationDTO2);
        candidateEducationDTO1.setId(null);
        assertThat(candidateEducationDTO1).isNotEqualTo(candidateEducationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(candidateEducationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(candidateEducationMapper.fromId(null)).isNull();
    }
}
