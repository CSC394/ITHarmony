package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.CultureProfile;
import com.itharmony.repository.CultureProfileRepository;
import com.itharmony.service.dto.CultureProfileDTO;
import com.itharmony.service.mapper.CultureProfileMapper;
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
 * Test class for the CultureProfileResource REST controller.
 *
 * @see CultureProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class CultureProfileResourceIntTest {

    private static final Integer DEFAULT_DRESSCODE = 1;
    private static final Integer UPDATED_DRESSCODE = 2;

    private static final Integer DEFAULT_COMPANYSIZE = 1;
    private static final Integer UPDATED_COMPANYSIZE = 2;

    private static final Integer DEFAULT_FLOORPLAN = 1;
    private static final Integer UPDATED_FLOORPLAN = 2;

    private static final Integer DEFAULT_HOURS = 1;
    private static final Integer UPDATED_HOURS = 2;

    private static final Integer DEFAULT_GROUP_WORK = 1;
    private static final Integer UPDATED_GROUP_WORK = 2;

    private static final Integer DEFAULT_PACE = 1;
    private static final Integer UPDATED_PACE = 2;

    private static final Integer DEFAULT_RULES = 1;
    private static final Integer UPDATED_RULES = 2;

    private static final Integer DEFAULT_AMENITIES = 1;
    private static final Integer UPDATED_AMENITIES = 2;

    private static final Integer DEFAULT_MEETINGS = 1;
    private static final Integer UPDATED_MEETINGS = 2;

    private static final Integer DEFAULT_PHILANTHROPY = 1;
    private static final Integer UPDATED_PHILANTHROPY = 2;

    private static final Integer DEFAULT_OUTINGS = 1;
    private static final Integer UPDATED_OUTINGS = 2;

    @Autowired
    private CultureProfileRepository cultureProfileRepository;

    @Autowired
    private CultureProfileMapper cultureProfileMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCultureProfileMockMvc;

    private CultureProfile cultureProfile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CultureProfileResource cultureProfileResource = new CultureProfileResource(cultureProfileRepository, cultureProfileMapper);
        this.restCultureProfileMockMvc = MockMvcBuilders.standaloneSetup(cultureProfileResource)
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
    public static CultureProfile createEntity(EntityManager em) {
        CultureProfile cultureProfile = new CultureProfile()
            .dresscode(DEFAULT_DRESSCODE)
            .companysize(DEFAULT_COMPANYSIZE)
            .floorplan(DEFAULT_FLOORPLAN)
            .hours(DEFAULT_HOURS)
            .groupWork(DEFAULT_GROUP_WORK)
            .pace(DEFAULT_PACE)
            .rules(DEFAULT_RULES)
            .amenities(DEFAULT_AMENITIES)
            .meetings(DEFAULT_MEETINGS)
            .philanthropy(DEFAULT_PHILANTHROPY)
            .outings(DEFAULT_OUTINGS);
        return cultureProfile;
    }

    @Before
    public void initTest() {
        cultureProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createCultureProfile() throws Exception {
        int databaseSizeBeforeCreate = cultureProfileRepository.findAll().size();

        // Create the CultureProfile
        CultureProfileDTO cultureProfileDTO = cultureProfileMapper.toDto(cultureProfile);
        restCultureProfileMockMvc.perform(post("/api/culture-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the CultureProfile in the database
        List<CultureProfile> cultureProfileList = cultureProfileRepository.findAll();
        assertThat(cultureProfileList).hasSize(databaseSizeBeforeCreate + 1);
        CultureProfile testCultureProfile = cultureProfileList.get(cultureProfileList.size() - 1);
        assertThat(testCultureProfile.getDresscode()).isEqualTo(DEFAULT_DRESSCODE);
        assertThat(testCultureProfile.getCompanysize()).isEqualTo(DEFAULT_COMPANYSIZE);
        assertThat(testCultureProfile.getFloorplan()).isEqualTo(DEFAULT_FLOORPLAN);
        assertThat(testCultureProfile.getHours()).isEqualTo(DEFAULT_HOURS);
        assertThat(testCultureProfile.getGroupWork()).isEqualTo(DEFAULT_GROUP_WORK);
        assertThat(testCultureProfile.getPace()).isEqualTo(DEFAULT_PACE);
        assertThat(testCultureProfile.getRules()).isEqualTo(DEFAULT_RULES);
        assertThat(testCultureProfile.getAmenities()).isEqualTo(DEFAULT_AMENITIES);
        assertThat(testCultureProfile.getMeetings()).isEqualTo(DEFAULT_MEETINGS);
        assertThat(testCultureProfile.getPhilanthropy()).isEqualTo(DEFAULT_PHILANTHROPY);
        assertThat(testCultureProfile.getOutings()).isEqualTo(DEFAULT_OUTINGS);
    }

    @Test
    @Transactional
    public void createCultureProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cultureProfileRepository.findAll().size();

        // Create the CultureProfile with an existing ID
        cultureProfile.setId(1L);
        CultureProfileDTO cultureProfileDTO = cultureProfileMapper.toDto(cultureProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCultureProfileMockMvc.perform(post("/api/culture-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CultureProfile in the database
        List<CultureProfile> cultureProfileList = cultureProfileRepository.findAll();
        assertThat(cultureProfileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCultureProfiles() throws Exception {
        // Initialize the database
        cultureProfileRepository.saveAndFlush(cultureProfile);

        // Get all the cultureProfileList
        restCultureProfileMockMvc.perform(get("/api/culture-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cultureProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].dresscode").value(hasItem(DEFAULT_DRESSCODE)))
            .andExpect(jsonPath("$.[*].companysize").value(hasItem(DEFAULT_COMPANYSIZE)))
            .andExpect(jsonPath("$.[*].floorplan").value(hasItem(DEFAULT_FLOORPLAN)))
            .andExpect(jsonPath("$.[*].hours").value(hasItem(DEFAULT_HOURS)))
            .andExpect(jsonPath("$.[*].groupWork").value(hasItem(DEFAULT_GROUP_WORK)))
            .andExpect(jsonPath("$.[*].pace").value(hasItem(DEFAULT_PACE)))
            .andExpect(jsonPath("$.[*].rules").value(hasItem(DEFAULT_RULES)))
            .andExpect(jsonPath("$.[*].amenities").value(hasItem(DEFAULT_AMENITIES)))
            .andExpect(jsonPath("$.[*].meetings").value(hasItem(DEFAULT_MEETINGS)))
            .andExpect(jsonPath("$.[*].philanthropy").value(hasItem(DEFAULT_PHILANTHROPY)))
            .andExpect(jsonPath("$.[*].outings").value(hasItem(DEFAULT_OUTINGS)));
    }

    @Test
    @Transactional
    public void getCultureProfile() throws Exception {
        // Initialize the database
        cultureProfileRepository.saveAndFlush(cultureProfile);

        // Get the cultureProfile
        restCultureProfileMockMvc.perform(get("/api/culture-profiles/{id}", cultureProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cultureProfile.getId().intValue()))
            .andExpect(jsonPath("$.dresscode").value(DEFAULT_DRESSCODE))
            .andExpect(jsonPath("$.companysize").value(DEFAULT_COMPANYSIZE))
            .andExpect(jsonPath("$.floorplan").value(DEFAULT_FLOORPLAN))
            .andExpect(jsonPath("$.hours").value(DEFAULT_HOURS))
            .andExpect(jsonPath("$.groupWork").value(DEFAULT_GROUP_WORK))
            .andExpect(jsonPath("$.pace").value(DEFAULT_PACE))
            .andExpect(jsonPath("$.rules").value(DEFAULT_RULES))
            .andExpect(jsonPath("$.amenities").value(DEFAULT_AMENITIES))
            .andExpect(jsonPath("$.meetings").value(DEFAULT_MEETINGS))
            .andExpect(jsonPath("$.philanthropy").value(DEFAULT_PHILANTHROPY))
            .andExpect(jsonPath("$.outings").value(DEFAULT_OUTINGS));
    }

    @Test
    @Transactional
    public void getNonExistingCultureProfile() throws Exception {
        // Get the cultureProfile
        restCultureProfileMockMvc.perform(get("/api/culture-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCultureProfile() throws Exception {
        // Initialize the database
        cultureProfileRepository.saveAndFlush(cultureProfile);
        int databaseSizeBeforeUpdate = cultureProfileRepository.findAll().size();

        // Update the cultureProfile
        CultureProfile updatedCultureProfile = cultureProfileRepository.findOne(cultureProfile.getId());
        // Disconnect from session so that the updates on updatedCultureProfile are not directly saved in db
        em.detach(updatedCultureProfile);
        updatedCultureProfile
            .dresscode(UPDATED_DRESSCODE)
            .companysize(UPDATED_COMPANYSIZE)
            .floorplan(UPDATED_FLOORPLAN)
            .hours(UPDATED_HOURS)
            .groupWork(UPDATED_GROUP_WORK)
            .pace(UPDATED_PACE)
            .rules(UPDATED_RULES)
            .amenities(UPDATED_AMENITIES)
            .meetings(UPDATED_MEETINGS)
            .philanthropy(UPDATED_PHILANTHROPY)
            .outings(UPDATED_OUTINGS);
        CultureProfileDTO cultureProfileDTO = cultureProfileMapper.toDto(updatedCultureProfile);

        restCultureProfileMockMvc.perform(put("/api/culture-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureProfileDTO)))
            .andExpect(status().isOk());

        // Validate the CultureProfile in the database
        List<CultureProfile> cultureProfileList = cultureProfileRepository.findAll();
        assertThat(cultureProfileList).hasSize(databaseSizeBeforeUpdate);
        CultureProfile testCultureProfile = cultureProfileList.get(cultureProfileList.size() - 1);
        assertThat(testCultureProfile.getDresscode()).isEqualTo(UPDATED_DRESSCODE);
        assertThat(testCultureProfile.getCompanysize()).isEqualTo(UPDATED_COMPANYSIZE);
        assertThat(testCultureProfile.getFloorplan()).isEqualTo(UPDATED_FLOORPLAN);
        assertThat(testCultureProfile.getHours()).isEqualTo(UPDATED_HOURS);
        assertThat(testCultureProfile.getGroupWork()).isEqualTo(UPDATED_GROUP_WORK);
        assertThat(testCultureProfile.getPace()).isEqualTo(UPDATED_PACE);
        assertThat(testCultureProfile.getRules()).isEqualTo(UPDATED_RULES);
        assertThat(testCultureProfile.getAmenities()).isEqualTo(UPDATED_AMENITIES);
        assertThat(testCultureProfile.getMeetings()).isEqualTo(UPDATED_MEETINGS);
        assertThat(testCultureProfile.getPhilanthropy()).isEqualTo(UPDATED_PHILANTHROPY);
        assertThat(testCultureProfile.getOutings()).isEqualTo(UPDATED_OUTINGS);
    }

    @Test
    @Transactional
    public void updateNonExistingCultureProfile() throws Exception {
        int databaseSizeBeforeUpdate = cultureProfileRepository.findAll().size();

        // Create the CultureProfile
        CultureProfileDTO cultureProfileDTO = cultureProfileMapper.toDto(cultureProfile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCultureProfileMockMvc.perform(put("/api/culture-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the CultureProfile in the database
        List<CultureProfile> cultureProfileList = cultureProfileRepository.findAll();
        assertThat(cultureProfileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCultureProfile() throws Exception {
        // Initialize the database
        cultureProfileRepository.saveAndFlush(cultureProfile);
        int databaseSizeBeforeDelete = cultureProfileRepository.findAll().size();

        // Get the cultureProfile
        restCultureProfileMockMvc.perform(delete("/api/culture-profiles/{id}", cultureProfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CultureProfile> cultureProfileList = cultureProfileRepository.findAll();
        assertThat(cultureProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CultureProfile.class);
        CultureProfile cultureProfile1 = new CultureProfile();
        cultureProfile1.setId(1L);
        CultureProfile cultureProfile2 = new CultureProfile();
        cultureProfile2.setId(cultureProfile1.getId());
        assertThat(cultureProfile1).isEqualTo(cultureProfile2);
        cultureProfile2.setId(2L);
        assertThat(cultureProfile1).isNotEqualTo(cultureProfile2);
        cultureProfile1.setId(null);
        assertThat(cultureProfile1).isNotEqualTo(cultureProfile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CultureProfileDTO.class);
        CultureProfileDTO cultureProfileDTO1 = new CultureProfileDTO();
        cultureProfileDTO1.setId(1L);
        CultureProfileDTO cultureProfileDTO2 = new CultureProfileDTO();
        assertThat(cultureProfileDTO1).isNotEqualTo(cultureProfileDTO2);
        cultureProfileDTO2.setId(cultureProfileDTO1.getId());
        assertThat(cultureProfileDTO1).isEqualTo(cultureProfileDTO2);
        cultureProfileDTO2.setId(2L);
        assertThat(cultureProfileDTO1).isNotEqualTo(cultureProfileDTO2);
        cultureProfileDTO1.setId(null);
        assertThat(cultureProfileDTO1).isNotEqualTo(cultureProfileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cultureProfileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cultureProfileMapper.fromId(null)).isNull();
    }
}
