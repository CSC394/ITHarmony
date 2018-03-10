package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.JobPost;
import com.itharmony.repository.JobPostRepository;
import com.itharmony.service.dto.JobPostDTO;
import com.itharmony.service.mapper.JobPostMapper;
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

import com.itharmony.domain.enumeration.JobType;
/**
 * Test class for the JobPostResource REST controller.
 *
 * @see JobPostResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class JobPostResourceIntTest {

    private static final String DEFAULT_POSITION_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_POSITION_TITLE = "BBBBBBBBBB";

    private static final JobType DEFAULT_POSITION_TYPE = JobType.INTERNSHIP;
    private static final JobType UPDATED_POSITION_TYPE = JobType.PARTTIME;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_DEGREE_REQUIRED = false;
    private static final Boolean UPDATED_DEGREE_REQUIRED = true;

    private static final Float DEFAULT_MINIMUM_GPA = 1F;
    private static final Float UPDATED_MINIMUM_GPA = 2F;

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private JobPostMapper jobPostMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJobPostMockMvc;

    private JobPost jobPost;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JobPostResource jobPostResource = new JobPostResource(jobPostRepository, jobPostMapper);
        this.restJobPostMockMvc = MockMvcBuilders.standaloneSetup(jobPostResource)
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
    public static JobPost createEntity(EntityManager em) {
        JobPost jobPost = new JobPost()
            .positionTitle(DEFAULT_POSITION_TITLE)
            .positionType(DEFAULT_POSITION_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .startDate(DEFAULT_START_DATE)
            .degreeRequired(DEFAULT_DEGREE_REQUIRED)
            .minimumGPA(DEFAULT_MINIMUM_GPA);
        return jobPost;
    }

    @Before
    public void initTest() {
        jobPost = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobPost() throws Exception {
        int databaseSizeBeforeCreate = jobPostRepository.findAll().size();

        // Create the JobPost
        JobPostDTO jobPostDTO = jobPostMapper.toDto(jobPost);
        restJobPostMockMvc.perform(post("/api/job-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobPostDTO)))
            .andExpect(status().isCreated());

        // Validate the JobPost in the database
        List<JobPost> jobPostList = jobPostRepository.findAll();
        assertThat(jobPostList).hasSize(databaseSizeBeforeCreate + 1);
        JobPost testJobPost = jobPostList.get(jobPostList.size() - 1);
        assertThat(testJobPost.getPositionTitle()).isEqualTo(DEFAULT_POSITION_TITLE);
        assertThat(testJobPost.getPositionType()).isEqualTo(DEFAULT_POSITION_TYPE);
        assertThat(testJobPost.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testJobPost.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testJobPost.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testJobPost.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testJobPost.isDegreeRequired()).isEqualTo(DEFAULT_DEGREE_REQUIRED);
        assertThat(testJobPost.getMinimumGPA()).isEqualTo(DEFAULT_MINIMUM_GPA);
    }

    @Test
    @Transactional
    public void createJobPostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobPostRepository.findAll().size();

        // Create the JobPost with an existing ID
        jobPost.setId(1L);
        JobPostDTO jobPostDTO = jobPostMapper.toDto(jobPost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobPostMockMvc.perform(post("/api/job-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobPostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobPost in the database
        List<JobPost> jobPostList = jobPostRepository.findAll();
        assertThat(jobPostList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllJobPosts() throws Exception {
        // Initialize the database
        jobPostRepository.saveAndFlush(jobPost);

        // Get all the jobPostList
        restJobPostMockMvc.perform(get("/api/job-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].positionTitle").value(hasItem(DEFAULT_POSITION_TITLE.toString())))
            .andExpect(jsonPath("$.[*].positionType").value(hasItem(DEFAULT_POSITION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].degreeRequired").value(hasItem(DEFAULT_DEGREE_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].minimumGPA").value(hasItem(DEFAULT_MINIMUM_GPA.doubleValue())));
    }

    @Test
    @Transactional
    public void getJobPost() throws Exception {
        // Initialize the database
        jobPostRepository.saveAndFlush(jobPost);

        // Get the jobPost
        restJobPostMockMvc.perform(get("/api/job-posts/{id}", jobPost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jobPost.getId().intValue()))
            .andExpect(jsonPath("$.positionTitle").value(DEFAULT_POSITION_TITLE.toString()))
            .andExpect(jsonPath("$.positionType").value(DEFAULT_POSITION_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.degreeRequired").value(DEFAULT_DEGREE_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.minimumGPA").value(DEFAULT_MINIMUM_GPA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingJobPost() throws Exception {
        // Get the jobPost
        restJobPostMockMvc.perform(get("/api/job-posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobPost() throws Exception {
        // Initialize the database
        jobPostRepository.saveAndFlush(jobPost);
        int databaseSizeBeforeUpdate = jobPostRepository.findAll().size();

        // Update the jobPost
        JobPost updatedJobPost = jobPostRepository.findOne(jobPost.getId());
        // Disconnect from session so that the updates on updatedJobPost are not directly saved in db
        em.detach(updatedJobPost);
        updatedJobPost
            .positionTitle(UPDATED_POSITION_TITLE)
            .positionType(UPDATED_POSITION_TYPE)
            .description(UPDATED_DESCRIPTION)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .startDate(UPDATED_START_DATE)
            .degreeRequired(UPDATED_DEGREE_REQUIRED)
            .minimumGPA(UPDATED_MINIMUM_GPA);
        JobPostDTO jobPostDTO = jobPostMapper.toDto(updatedJobPost);

        restJobPostMockMvc.perform(put("/api/job-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobPostDTO)))
            .andExpect(status().isOk());

        // Validate the JobPost in the database
        List<JobPost> jobPostList = jobPostRepository.findAll();
        assertThat(jobPostList).hasSize(databaseSizeBeforeUpdate);
        JobPost testJobPost = jobPostList.get(jobPostList.size() - 1);
        assertThat(testJobPost.getPositionTitle()).isEqualTo(UPDATED_POSITION_TITLE);
        assertThat(testJobPost.getPositionType()).isEqualTo(UPDATED_POSITION_TYPE);
        assertThat(testJobPost.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testJobPost.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testJobPost.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testJobPost.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testJobPost.isDegreeRequired()).isEqualTo(UPDATED_DEGREE_REQUIRED);
        assertThat(testJobPost.getMinimumGPA()).isEqualTo(UPDATED_MINIMUM_GPA);
    }

    @Test
    @Transactional
    public void updateNonExistingJobPost() throws Exception {
        int databaseSizeBeforeUpdate = jobPostRepository.findAll().size();

        // Create the JobPost
        JobPostDTO jobPostDTO = jobPostMapper.toDto(jobPost);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJobPostMockMvc.perform(put("/api/job-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobPostDTO)))
            .andExpect(status().isCreated());

        // Validate the JobPost in the database
        List<JobPost> jobPostList = jobPostRepository.findAll();
        assertThat(jobPostList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJobPost() throws Exception {
        // Initialize the database
        jobPostRepository.saveAndFlush(jobPost);
        int databaseSizeBeforeDelete = jobPostRepository.findAll().size();

        // Get the jobPost
        restJobPostMockMvc.perform(delete("/api/job-posts/{id}", jobPost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JobPost> jobPostList = jobPostRepository.findAll();
        assertThat(jobPostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobPost.class);
        JobPost jobPost1 = new JobPost();
        jobPost1.setId(1L);
        JobPost jobPost2 = new JobPost();
        jobPost2.setId(jobPost1.getId());
        assertThat(jobPost1).isEqualTo(jobPost2);
        jobPost2.setId(2L);
        assertThat(jobPost1).isNotEqualTo(jobPost2);
        jobPost1.setId(null);
        assertThat(jobPost1).isNotEqualTo(jobPost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobPostDTO.class);
        JobPostDTO jobPostDTO1 = new JobPostDTO();
        jobPostDTO1.setId(1L);
        JobPostDTO jobPostDTO2 = new JobPostDTO();
        assertThat(jobPostDTO1).isNotEqualTo(jobPostDTO2);
        jobPostDTO2.setId(jobPostDTO1.getId());
        assertThat(jobPostDTO1).isEqualTo(jobPostDTO2);
        jobPostDTO2.setId(2L);
        assertThat(jobPostDTO1).isNotEqualTo(jobPostDTO2);
        jobPostDTO1.setId(null);
        assertThat(jobPostDTO1).isNotEqualTo(jobPostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(jobPostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(jobPostMapper.fromId(null)).isNull();
    }
}
