package com.itharmony.web.rest;

import com.itharmony.ItHarmonyApp;

import com.itharmony.domain.UserProfileExtra;
import com.itharmony.repository.UserProfileExtraRepository;
import com.itharmony.service.dto.UserProfileExtraDTO;
import com.itharmony.service.mapper.UserProfileExtraMapper;
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

import com.itharmony.domain.enumeration.UserTypeT;
/**
 * Test class for the UserProfileExtraResource REST controller.
 *
 * @see UserProfileExtraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItHarmonyApp.class)
public class UserProfileExtraResourceIntTest {

    private static final UserTypeT DEFAULT_USER_TYPE_T = UserTypeT.CANDIDATE;
    private static final UserTypeT UPDATED_USER_TYPE_T = UserTypeT.COMPANY;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BIO = "AAAAAAAAAA";
    private static final String UPDATED_BIO = "BBBBBBBBBB";

    @Autowired
    private UserProfileExtraRepository userProfileExtraRepository;

    @Autowired
    private UserProfileExtraMapper userProfileExtraMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserProfileExtraMockMvc;

    private UserProfileExtra userProfileExtra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserProfileExtraResource userProfileExtraResource = new UserProfileExtraResource(userProfileExtraRepository, userProfileExtraMapper);
        this.restUserProfileExtraMockMvc = MockMvcBuilders.standaloneSetup(userProfileExtraResource)
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
    public static UserProfileExtra createEntity(EntityManager em) {
        UserProfileExtra userProfileExtra = new UserProfileExtra()
            .userTypeT(DEFAULT_USER_TYPE_T)
            .phone(DEFAULT_PHONE)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .bio(DEFAULT_BIO);
        return userProfileExtra;
    }

    @Before
    public void initTest() {
        userProfileExtra = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserProfileExtra() throws Exception {
        int databaseSizeBeforeCreate = userProfileExtraRepository.findAll().size();

        // Create the UserProfileExtra
        UserProfileExtraDTO userProfileExtraDTO = userProfileExtraMapper.toDto(userProfileExtra);
        restUserProfileExtraMockMvc.perform(post("/api/user-profile-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileExtraDTO)))
            .andExpect(status().isCreated());

        // Validate the UserProfileExtra in the database
        List<UserProfileExtra> userProfileExtraList = userProfileExtraRepository.findAll();
        assertThat(userProfileExtraList).hasSize(databaseSizeBeforeCreate + 1);
        UserProfileExtra testUserProfileExtra = userProfileExtraList.get(userProfileExtraList.size() - 1);
        assertThat(testUserProfileExtra.getUserTypeT()).isEqualTo(DEFAULT_USER_TYPE_T);
        assertThat(testUserProfileExtra.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserProfileExtra.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUserProfileExtra.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testUserProfileExtra.getBio()).isEqualTo(DEFAULT_BIO);
    }

    @Test
    @Transactional
    public void createUserProfileExtraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userProfileExtraRepository.findAll().size();

        // Create the UserProfileExtra with an existing ID
        userProfileExtra.setId(1L);
        UserProfileExtraDTO userProfileExtraDTO = userProfileExtraMapper.toDto(userProfileExtra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserProfileExtraMockMvc.perform(post("/api/user-profile-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileExtraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserProfileExtra in the database
        List<UserProfileExtra> userProfileExtraList = userProfileExtraRepository.findAll();
        assertThat(userProfileExtraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserTypeTIsRequired() throws Exception {
        int databaseSizeBeforeTest = userProfileExtraRepository.findAll().size();
        // set the field null
        userProfileExtra.setUserTypeT(null);

        // Create the UserProfileExtra, which fails.
        UserProfileExtraDTO userProfileExtraDTO = userProfileExtraMapper.toDto(userProfileExtra);

        restUserProfileExtraMockMvc.perform(post("/api/user-profile-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileExtraDTO)))
            .andExpect(status().isBadRequest());

        List<UserProfileExtra> userProfileExtraList = userProfileExtraRepository.findAll();
        assertThat(userProfileExtraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserProfileExtras() throws Exception {
        // Initialize the database
        userProfileExtraRepository.saveAndFlush(userProfileExtra);

        // Get all the userProfileExtraList
        restUserProfileExtraMockMvc.perform(get("/api/user-profile-extras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userProfileExtra.getId().intValue())))
            .andExpect(jsonPath("$.[*].userTypeT").value(hasItem(DEFAULT_USER_TYPE_T.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())));
    }

    @Test
    @Transactional
    public void getUserProfileExtra() throws Exception {
        // Initialize the database
        userProfileExtraRepository.saveAndFlush(userProfileExtra);

        // Get the userProfileExtra
        restUserProfileExtraMockMvc.perform(get("/api/user-profile-extras/{id}", userProfileExtra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userProfileExtra.getId().intValue()))
            .andExpect(jsonPath("$.userTypeT").value(DEFAULT_USER_TYPE_T.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.bio").value(DEFAULT_BIO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserProfileExtra() throws Exception {
        // Get the userProfileExtra
        restUserProfileExtraMockMvc.perform(get("/api/user-profile-extras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserProfileExtra() throws Exception {
        // Initialize the database
        userProfileExtraRepository.saveAndFlush(userProfileExtra);
        int databaseSizeBeforeUpdate = userProfileExtraRepository.findAll().size();

        // Update the userProfileExtra
        UserProfileExtra updatedUserProfileExtra = userProfileExtraRepository.findOne(userProfileExtra.getId());
        // Disconnect from session so that the updates on updatedUserProfileExtra are not directly saved in db
        em.detach(updatedUserProfileExtra);
        updatedUserProfileExtra
            .userTypeT(UPDATED_USER_TYPE_T)
            .phone(UPDATED_PHONE)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .bio(UPDATED_BIO);
        UserProfileExtraDTO userProfileExtraDTO = userProfileExtraMapper.toDto(updatedUserProfileExtra);

        restUserProfileExtraMockMvc.perform(put("/api/user-profile-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileExtraDTO)))
            .andExpect(status().isOk());

        // Validate the UserProfileExtra in the database
        List<UserProfileExtra> userProfileExtraList = userProfileExtraRepository.findAll();
        assertThat(userProfileExtraList).hasSize(databaseSizeBeforeUpdate);
        UserProfileExtra testUserProfileExtra = userProfileExtraList.get(userProfileExtraList.size() - 1);
        assertThat(testUserProfileExtra.getUserTypeT()).isEqualTo(UPDATED_USER_TYPE_T);
        assertThat(testUserProfileExtra.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserProfileExtra.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserProfileExtra.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUserProfileExtra.getBio()).isEqualTo(UPDATED_BIO);
    }

    @Test
    @Transactional
    public void updateNonExistingUserProfileExtra() throws Exception {
        int databaseSizeBeforeUpdate = userProfileExtraRepository.findAll().size();

        // Create the UserProfileExtra
        UserProfileExtraDTO userProfileExtraDTO = userProfileExtraMapper.toDto(userProfileExtra);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserProfileExtraMockMvc.perform(put("/api/user-profile-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileExtraDTO)))
            .andExpect(status().isCreated());

        // Validate the UserProfileExtra in the database
        List<UserProfileExtra> userProfileExtraList = userProfileExtraRepository.findAll();
        assertThat(userProfileExtraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserProfileExtra() throws Exception {
        // Initialize the database
        userProfileExtraRepository.saveAndFlush(userProfileExtra);
        int databaseSizeBeforeDelete = userProfileExtraRepository.findAll().size();

        // Get the userProfileExtra
        restUserProfileExtraMockMvc.perform(delete("/api/user-profile-extras/{id}", userProfileExtra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserProfileExtra> userProfileExtraList = userProfileExtraRepository.findAll();
        assertThat(userProfileExtraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProfileExtra.class);
        UserProfileExtra userProfileExtra1 = new UserProfileExtra();
        userProfileExtra1.setId(1L);
        UserProfileExtra userProfileExtra2 = new UserProfileExtra();
        userProfileExtra2.setId(userProfileExtra1.getId());
        assertThat(userProfileExtra1).isEqualTo(userProfileExtra2);
        userProfileExtra2.setId(2L);
        assertThat(userProfileExtra1).isNotEqualTo(userProfileExtra2);
        userProfileExtra1.setId(null);
        assertThat(userProfileExtra1).isNotEqualTo(userProfileExtra2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProfileExtraDTO.class);
        UserProfileExtraDTO userProfileExtraDTO1 = new UserProfileExtraDTO();
        userProfileExtraDTO1.setId(1L);
        UserProfileExtraDTO userProfileExtraDTO2 = new UserProfileExtraDTO();
        assertThat(userProfileExtraDTO1).isNotEqualTo(userProfileExtraDTO2);
        userProfileExtraDTO2.setId(userProfileExtraDTO1.getId());
        assertThat(userProfileExtraDTO1).isEqualTo(userProfileExtraDTO2);
        userProfileExtraDTO2.setId(2L);
        assertThat(userProfileExtraDTO1).isNotEqualTo(userProfileExtraDTO2);
        userProfileExtraDTO1.setId(null);
        assertThat(userProfileExtraDTO1).isNotEqualTo(userProfileExtraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userProfileExtraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userProfileExtraMapper.fromId(null)).isNull();
    }
}
