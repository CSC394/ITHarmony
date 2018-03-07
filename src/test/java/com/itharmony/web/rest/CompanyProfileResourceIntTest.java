package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.CompanyProfile;
import com.itharmony.repository.CompanyProfileRepository;
import com.itharmony.service.dto.CompanyProfileDTO;
import com.itharmony.service.mapper.CompanyProfileMapper;
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
 * Test class for the CompanyProfileResource REST controller.
 *
 * @see CompanyProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class CompanyProfileResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_POSTINGS = 1;
    private static final Integer UPDATED_POSTINGS = 2;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_INDUSTRY = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY = "BBBBBBBBBB";

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    private CompanyProfileMapper companyProfileMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCompanyProfileMockMvc;

    private CompanyProfile companyProfile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompanyProfileResource companyProfileResource = new CompanyProfileResource(companyProfileRepository, companyProfileMapper);
        this.restCompanyProfileMockMvc = MockMvcBuilders.standaloneSetup(companyProfileResource)
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
    public static CompanyProfile createEntity(EntityManager em) {
        CompanyProfile companyProfile = new CompanyProfile()
            .companyName(DEFAULT_COMPANY_NAME)
            .postings(DEFAULT_POSTINGS)
            .email(DEFAULT_EMAIL)
            .industry(DEFAULT_INDUSTRY);
        return companyProfile;
    }

    @Before
    public void initTest() {
        companyProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompanyProfile() throws Exception {
        int databaseSizeBeforeCreate = companyProfileRepository.findAll().size();

        // Create the CompanyProfile
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);
        restCompanyProfileMockMvc.perform(post("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeCreate + 1);
        CompanyProfile testCompanyProfile = companyProfileList.get(companyProfileList.size() - 1);
        assertThat(testCompanyProfile.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testCompanyProfile.getPostings()).isEqualTo(DEFAULT_POSTINGS);
        assertThat(testCompanyProfile.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCompanyProfile.getIndustry()).isEqualTo(DEFAULT_INDUSTRY);
    }

    @Test
    @Transactional
    public void createCompanyProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyProfileRepository.findAll().size();

        // Create the CompanyProfile with an existing ID
        companyProfile.setId(1L);
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyProfileMockMvc.perform(post("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyProfileRepository.findAll().size();
        // set the field null
        companyProfile.setCompanyName(null);

        // Create the CompanyProfile, which fails.
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);

        restCompanyProfileMockMvc.perform(post("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isBadRequest());

        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPostingsIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyProfileRepository.findAll().size();
        // set the field null
        companyProfile.setPostings(null);

        // Create the CompanyProfile, which fails.
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);

        restCompanyProfileMockMvc.perform(post("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isBadRequest());

        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyProfileRepository.findAll().size();
        // set the field null
        companyProfile.setEmail(null);

        // Create the CompanyProfile, which fails.
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);

        restCompanyProfileMockMvc.perform(post("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isBadRequest());

        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompanyProfiles() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);

        // Get all the companyProfileList
        restCompanyProfileMockMvc.perform(get("/api/company-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companyProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].postings").value(hasItem(DEFAULT_POSTINGS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].industry").value(hasItem(DEFAULT_INDUSTRY.toString())));
    }

    @Test
    @Transactional
    public void getCompanyProfile() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);

        // Get the companyProfile
        restCompanyProfileMockMvc.perform(get("/api/company-profiles/{id}", companyProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(companyProfile.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.postings").value(DEFAULT_POSTINGS))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.industry").value(DEFAULT_INDUSTRY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCompanyProfile() throws Exception {
        // Get the companyProfile
        restCompanyProfileMockMvc.perform(get("/api/company-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompanyProfile() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);
        int databaseSizeBeforeUpdate = companyProfileRepository.findAll().size();

        // Update the companyProfile
        CompanyProfile updatedCompanyProfile = companyProfileRepository.findOne(companyProfile.getId());
        // Disconnect from session so that the updates on updatedCompanyProfile are not directly saved in db
        em.detach(updatedCompanyProfile);
        updatedCompanyProfile
            .companyName(UPDATED_COMPANY_NAME)
            .postings(UPDATED_POSTINGS)
            .email(UPDATED_EMAIL)
            .industry(UPDATED_INDUSTRY);
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(updatedCompanyProfile);

        restCompanyProfileMockMvc.perform(put("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isOk());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeUpdate);
        CompanyProfile testCompanyProfile = companyProfileList.get(companyProfileList.size() - 1);
        assertThat(testCompanyProfile.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testCompanyProfile.getPostings()).isEqualTo(UPDATED_POSTINGS);
        assertThat(testCompanyProfile.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCompanyProfile.getIndustry()).isEqualTo(UPDATED_INDUSTRY);
    }

    @Test
    @Transactional
    public void updateNonExistingCompanyProfile() throws Exception {
        int databaseSizeBeforeUpdate = companyProfileRepository.findAll().size();

        // Create the CompanyProfile
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompanyProfileMockMvc.perform(put("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCompanyProfile() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);
        int databaseSizeBeforeDelete = companyProfileRepository.findAll().size();

        // Get the companyProfile
        restCompanyProfileMockMvc.perform(delete("/api/company-profiles/{id}", companyProfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyProfile.class);
        CompanyProfile companyProfile1 = new CompanyProfile();
        companyProfile1.setId(1L);
        CompanyProfile companyProfile2 = new CompanyProfile();
        companyProfile2.setId(companyProfile1.getId());
        assertThat(companyProfile1).isEqualTo(companyProfile2);
        companyProfile2.setId(2L);
        assertThat(companyProfile1).isNotEqualTo(companyProfile2);
        companyProfile1.setId(null);
        assertThat(companyProfile1).isNotEqualTo(companyProfile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyProfileDTO.class);
        CompanyProfileDTO companyProfileDTO1 = new CompanyProfileDTO();
        companyProfileDTO1.setId(1L);
        CompanyProfileDTO companyProfileDTO2 = new CompanyProfileDTO();
        assertThat(companyProfileDTO1).isNotEqualTo(companyProfileDTO2);
        companyProfileDTO2.setId(companyProfileDTO1.getId());
        assertThat(companyProfileDTO1).isEqualTo(companyProfileDTO2);
        companyProfileDTO2.setId(2L);
        assertThat(companyProfileDTO1).isNotEqualTo(companyProfileDTO2);
        companyProfileDTO1.setId(null);
        assertThat(companyProfileDTO1).isNotEqualTo(companyProfileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(companyProfileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(companyProfileMapper.fromId(null)).isNull();
    }
}
