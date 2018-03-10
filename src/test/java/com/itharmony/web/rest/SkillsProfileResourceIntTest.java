package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.SkillsProfile;
import com.itharmony.repository.SkillsProfileRepository;
import com.itharmony.service.dto.SkillsProfileDTO;
import com.itharmony.service.mapper.SkillsProfileMapper;
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

import com.itharmony.domain.enumeration.Specialty;
/**
 * Test class for the SkillsProfileResource REST controller.
 *
 * @see SkillsProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class SkillsProfileResourceIntTest {

    private static final Boolean DEFAULT_FOR_CANDIDATE = false;
    private static final Boolean UPDATED_FOR_CANDIDATE = true;

    private static final Specialty DEFAULT_SPECIALTY = Specialty.SOFTWARE_ENGINEERING;
    private static final Specialty UPDATED_SPECIALTY = Specialty.WEB_DEVELOPMENT;

    private static final Integer DEFAULT_SPECIALTY_XP = 1;
    private static final Integer UPDATED_SPECIALTY_XP = 2;

    private static final String DEFAULT_SKILL_1 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_2 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_3 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_4 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_4 = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_5 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_5 = "BBBBBBBBBB";

    private static final Integer DEFAULT_SKILL_1_XP = 1;
    private static final Integer UPDATED_SKILL_1_XP = 2;

    private static final Integer DEFAULT_SKILL_2_XP = 1;
    private static final Integer UPDATED_SKILL_2_XP = 2;

    private static final Integer DEFAULT_SKILL_3_XP = 1;
    private static final Integer UPDATED_SKILL_3_XP = 2;

    private static final Integer DEFAULT_SKILL_4_XP = 1;
    private static final Integer UPDATED_SKILL_4_XP = 2;

    private static final Integer DEFAULT_SKILL_5_XP = 1;
    private static final Integer UPDATED_SKILL_5_XP = 2;

    @Autowired
    private SkillsProfileRepository skillsProfileRepository;

    @Autowired
    private SkillsProfileMapper skillsProfileMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSkillsProfileMockMvc;

    private SkillsProfile skillsProfile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SkillsProfileResource skillsProfileResource = new SkillsProfileResource(skillsProfileRepository, skillsProfileMapper);
        this.restSkillsProfileMockMvc = MockMvcBuilders.standaloneSetup(skillsProfileResource)
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
    public static SkillsProfile createEntity(EntityManager em) {
        SkillsProfile skillsProfile = new SkillsProfile()
            .forCandidate(DEFAULT_FOR_CANDIDATE)
            .specialty(DEFAULT_SPECIALTY)
            .specialtyXP(DEFAULT_SPECIALTY_XP)
            .skill1(DEFAULT_SKILL_1)
            .skill2(DEFAULT_SKILL_2)
            .skill3(DEFAULT_SKILL_3)
            .skill4(DEFAULT_SKILL_4)
            .skill5(DEFAULT_SKILL_5)
            .skill1XP(DEFAULT_SKILL_1_XP)
            .skill2XP(DEFAULT_SKILL_2_XP)
            .skill3XP(DEFAULT_SKILL_3_XP)
            .skill4XP(DEFAULT_SKILL_4_XP)
            .skill5XP(DEFAULT_SKILL_5_XP);
        return skillsProfile;
    }

    @Before
    public void initTest() {
        skillsProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkillsProfile() throws Exception {
        int databaseSizeBeforeCreate = skillsProfileRepository.findAll().size();

        // Create the SkillsProfile
        SkillsProfileDTO skillsProfileDTO = skillsProfileMapper.toDto(skillsProfile);
        restSkillsProfileMockMvc.perform(post("/api/skills-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the SkillsProfile in the database
        List<SkillsProfile> skillsProfileList = skillsProfileRepository.findAll();
        assertThat(skillsProfileList).hasSize(databaseSizeBeforeCreate + 1);
        SkillsProfile testSkillsProfile = skillsProfileList.get(skillsProfileList.size() - 1);
        assertThat(testSkillsProfile.isForCandidate()).isEqualTo(DEFAULT_FOR_CANDIDATE);
        assertThat(testSkillsProfile.getSpecialty()).isEqualTo(DEFAULT_SPECIALTY);
        assertThat(testSkillsProfile.getSpecialtyXP()).isEqualTo(DEFAULT_SPECIALTY_XP);
        assertThat(testSkillsProfile.getSkill1()).isEqualTo(DEFAULT_SKILL_1);
        assertThat(testSkillsProfile.getSkill2()).isEqualTo(DEFAULT_SKILL_2);
        assertThat(testSkillsProfile.getSkill3()).isEqualTo(DEFAULT_SKILL_3);
        assertThat(testSkillsProfile.getSkill4()).isEqualTo(DEFAULT_SKILL_4);
        assertThat(testSkillsProfile.getSkill5()).isEqualTo(DEFAULT_SKILL_5);
        assertThat(testSkillsProfile.getSkill1XP()).isEqualTo(DEFAULT_SKILL_1_XP);
        assertThat(testSkillsProfile.getSkill2XP()).isEqualTo(DEFAULT_SKILL_2_XP);
        assertThat(testSkillsProfile.getSkill3XP()).isEqualTo(DEFAULT_SKILL_3_XP);
        assertThat(testSkillsProfile.getSkill4XP()).isEqualTo(DEFAULT_SKILL_4_XP);
        assertThat(testSkillsProfile.getSkill5XP()).isEqualTo(DEFAULT_SKILL_5_XP);
    }

    @Test
    @Transactional
    public void createSkillsProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillsProfileRepository.findAll().size();

        // Create the SkillsProfile with an existing ID
        skillsProfile.setId(1L);
        SkillsProfileDTO skillsProfileDTO = skillsProfileMapper.toDto(skillsProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillsProfileMockMvc.perform(post("/api/skills-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SkillsProfile in the database
        List<SkillsProfile> skillsProfileList = skillsProfileRepository.findAll();
        assertThat(skillsProfileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSkillsProfiles() throws Exception {
        // Initialize the database
        skillsProfileRepository.saveAndFlush(skillsProfile);

        // Get all the skillsProfileList
        restSkillsProfileMockMvc.perform(get("/api/skills-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skillsProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].forCandidate").value(hasItem(DEFAULT_FOR_CANDIDATE.booleanValue())))
            .andExpect(jsonPath("$.[*].specialty").value(hasItem(DEFAULT_SPECIALTY.toString())))
            .andExpect(jsonPath("$.[*].specialtyXP").value(hasItem(DEFAULT_SPECIALTY_XP)))
            .andExpect(jsonPath("$.[*].skill1").value(hasItem(DEFAULT_SKILL_1.toString())))
            .andExpect(jsonPath("$.[*].skill2").value(hasItem(DEFAULT_SKILL_2.toString())))
            .andExpect(jsonPath("$.[*].skill3").value(hasItem(DEFAULT_SKILL_3.toString())))
            .andExpect(jsonPath("$.[*].skill4").value(hasItem(DEFAULT_SKILL_4.toString())))
            .andExpect(jsonPath("$.[*].skill5").value(hasItem(DEFAULT_SKILL_5.toString())))
            .andExpect(jsonPath("$.[*].skill1XP").value(hasItem(DEFAULT_SKILL_1_XP)))
            .andExpect(jsonPath("$.[*].skill2XP").value(hasItem(DEFAULT_SKILL_2_XP)))
            .andExpect(jsonPath("$.[*].skill3XP").value(hasItem(DEFAULT_SKILL_3_XP)))
            .andExpect(jsonPath("$.[*].skill4XP").value(hasItem(DEFAULT_SKILL_4_XP)))
            .andExpect(jsonPath("$.[*].skill5XP").value(hasItem(DEFAULT_SKILL_5_XP)));
    }

    @Test
    @Transactional
    public void getSkillsProfile() throws Exception {
        // Initialize the database
        skillsProfileRepository.saveAndFlush(skillsProfile);

        // Get the skillsProfile
        restSkillsProfileMockMvc.perform(get("/api/skills-profiles/{id}", skillsProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(skillsProfile.getId().intValue()))
            .andExpect(jsonPath("$.forCandidate").value(DEFAULT_FOR_CANDIDATE.booleanValue()))
            .andExpect(jsonPath("$.specialty").value(DEFAULT_SPECIALTY.toString()))
            .andExpect(jsonPath("$.specialtyXP").value(DEFAULT_SPECIALTY_XP))
            .andExpect(jsonPath("$.skill1").value(DEFAULT_SKILL_1.toString()))
            .andExpect(jsonPath("$.skill2").value(DEFAULT_SKILL_2.toString()))
            .andExpect(jsonPath("$.skill3").value(DEFAULT_SKILL_3.toString()))
            .andExpect(jsonPath("$.skill4").value(DEFAULT_SKILL_4.toString()))
            .andExpect(jsonPath("$.skill5").value(DEFAULT_SKILL_5.toString()))
            .andExpect(jsonPath("$.skill1XP").value(DEFAULT_SKILL_1_XP))
            .andExpect(jsonPath("$.skill2XP").value(DEFAULT_SKILL_2_XP))
            .andExpect(jsonPath("$.skill3XP").value(DEFAULT_SKILL_3_XP))
            .andExpect(jsonPath("$.skill4XP").value(DEFAULT_SKILL_4_XP))
            .andExpect(jsonPath("$.skill5XP").value(DEFAULT_SKILL_5_XP));
    }

    @Test
    @Transactional
    public void getNonExistingSkillsProfile() throws Exception {
        // Get the skillsProfile
        restSkillsProfileMockMvc.perform(get("/api/skills-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkillsProfile() throws Exception {
        // Initialize the database
        skillsProfileRepository.saveAndFlush(skillsProfile);
        int databaseSizeBeforeUpdate = skillsProfileRepository.findAll().size();

        // Update the skillsProfile
        SkillsProfile updatedSkillsProfile = skillsProfileRepository.findOne(skillsProfile.getId());
        // Disconnect from session so that the updates on updatedSkillsProfile are not directly saved in db
        em.detach(updatedSkillsProfile);
        updatedSkillsProfile
            .forCandidate(UPDATED_FOR_CANDIDATE)
            .specialty(UPDATED_SPECIALTY)
            .specialtyXP(UPDATED_SPECIALTY_XP)
            .skill1(UPDATED_SKILL_1)
            .skill2(UPDATED_SKILL_2)
            .skill3(UPDATED_SKILL_3)
            .skill4(UPDATED_SKILL_4)
            .skill5(UPDATED_SKILL_5)
            .skill1XP(UPDATED_SKILL_1_XP)
            .skill2XP(UPDATED_SKILL_2_XP)
            .skill3XP(UPDATED_SKILL_3_XP)
            .skill4XP(UPDATED_SKILL_4_XP)
            .skill5XP(UPDATED_SKILL_5_XP);
        SkillsProfileDTO skillsProfileDTO = skillsProfileMapper.toDto(updatedSkillsProfile);

        restSkillsProfileMockMvc.perform(put("/api/skills-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsProfileDTO)))
            .andExpect(status().isOk());

        // Validate the SkillsProfile in the database
        List<SkillsProfile> skillsProfileList = skillsProfileRepository.findAll();
        assertThat(skillsProfileList).hasSize(databaseSizeBeforeUpdate);
        SkillsProfile testSkillsProfile = skillsProfileList.get(skillsProfileList.size() - 1);
        assertThat(testSkillsProfile.isForCandidate()).isEqualTo(UPDATED_FOR_CANDIDATE);
        assertThat(testSkillsProfile.getSpecialty()).isEqualTo(UPDATED_SPECIALTY);
        assertThat(testSkillsProfile.getSpecialtyXP()).isEqualTo(UPDATED_SPECIALTY_XP);
        assertThat(testSkillsProfile.getSkill1()).isEqualTo(UPDATED_SKILL_1);
        assertThat(testSkillsProfile.getSkill2()).isEqualTo(UPDATED_SKILL_2);
        assertThat(testSkillsProfile.getSkill3()).isEqualTo(UPDATED_SKILL_3);
        assertThat(testSkillsProfile.getSkill4()).isEqualTo(UPDATED_SKILL_4);
        assertThat(testSkillsProfile.getSkill5()).isEqualTo(UPDATED_SKILL_5);
        assertThat(testSkillsProfile.getSkill1XP()).isEqualTo(UPDATED_SKILL_1_XP);
        assertThat(testSkillsProfile.getSkill2XP()).isEqualTo(UPDATED_SKILL_2_XP);
        assertThat(testSkillsProfile.getSkill3XP()).isEqualTo(UPDATED_SKILL_3_XP);
        assertThat(testSkillsProfile.getSkill4XP()).isEqualTo(UPDATED_SKILL_4_XP);
        assertThat(testSkillsProfile.getSkill5XP()).isEqualTo(UPDATED_SKILL_5_XP);
    }

    @Test
    @Transactional
    public void updateNonExistingSkillsProfile() throws Exception {
        int databaseSizeBeforeUpdate = skillsProfileRepository.findAll().size();

        // Create the SkillsProfile
        SkillsProfileDTO skillsProfileDTO = skillsProfileMapper.toDto(skillsProfile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSkillsProfileMockMvc.perform(put("/api/skills-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the SkillsProfile in the database
        List<SkillsProfile> skillsProfileList = skillsProfileRepository.findAll();
        assertThat(skillsProfileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSkillsProfile() throws Exception {
        // Initialize the database
        skillsProfileRepository.saveAndFlush(skillsProfile);
        int databaseSizeBeforeDelete = skillsProfileRepository.findAll().size();

        // Get the skillsProfile
        restSkillsProfileMockMvc.perform(delete("/api/skills-profiles/{id}", skillsProfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SkillsProfile> skillsProfileList = skillsProfileRepository.findAll();
        assertThat(skillsProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillsProfile.class);
        SkillsProfile skillsProfile1 = new SkillsProfile();
        skillsProfile1.setId(1L);
        SkillsProfile skillsProfile2 = new SkillsProfile();
        skillsProfile2.setId(skillsProfile1.getId());
        assertThat(skillsProfile1).isEqualTo(skillsProfile2);
        skillsProfile2.setId(2L);
        assertThat(skillsProfile1).isNotEqualTo(skillsProfile2);
        skillsProfile1.setId(null);
        assertThat(skillsProfile1).isNotEqualTo(skillsProfile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillsProfileDTO.class);
        SkillsProfileDTO skillsProfileDTO1 = new SkillsProfileDTO();
        skillsProfileDTO1.setId(1L);
        SkillsProfileDTO skillsProfileDTO2 = new SkillsProfileDTO();
        assertThat(skillsProfileDTO1).isNotEqualTo(skillsProfileDTO2);
        skillsProfileDTO2.setId(skillsProfileDTO1.getId());
        assertThat(skillsProfileDTO1).isEqualTo(skillsProfileDTO2);
        skillsProfileDTO2.setId(2L);
        assertThat(skillsProfileDTO1).isNotEqualTo(skillsProfileDTO2);
        skillsProfileDTO1.setId(null);
        assertThat(skillsProfileDTO1).isNotEqualTo(skillsProfileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(skillsProfileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(skillsProfileMapper.fromId(null)).isNull();
    }
}
