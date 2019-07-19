package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumSsPayScheme;
import com.cc.domain.EnumSsPayScheme;
import com.cc.repository.EnumSsPaySchemeRepository;
import com.cc.service.EnumSsPaySchemeService;
import com.cc.service.dto.EnumSsPaySchemeDTO;
import com.cc.service.mapper.EnumSsPaySchemeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumSsPaySchemeCriteria;
import com.cc.service.EnumSsPaySchemeQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link EnumSsPaySchemeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumSsPaySchemeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumSsPaySchemeRepository enumSsPaySchemeRepository;

    @Autowired
    private EnumSsPaySchemeMapper enumSsPaySchemeMapper;

    @Autowired
    private EnumSsPaySchemeService enumSsPaySchemeService;

    @Autowired
    private EnumSsPaySchemeQueryService enumSsPaySchemeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEnumSsPaySchemeMockMvc;

    private EnumSsPayScheme enumSsPayScheme;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumSsPaySchemeResource enumSsPaySchemeResource = new EnumSsPaySchemeResource(enumSsPaySchemeService, enumSsPaySchemeQueryService);
        this.restEnumSsPaySchemeMockMvc = MockMvcBuilders.standaloneSetup(enumSsPaySchemeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumSsPayScheme createEntity(EntityManager em) {
        EnumSsPayScheme enumSsPayScheme = new EnumSsPayScheme()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumSsPayScheme;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumSsPayScheme createUpdatedEntity(EntityManager em) {
        EnumSsPayScheme enumSsPayScheme = new EnumSsPayScheme()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumSsPayScheme;
    }

    @BeforeEach
    public void initTest() {
        enumSsPayScheme = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumSsPayScheme() throws Exception {
        int databaseSizeBeforeCreate = enumSsPaySchemeRepository.findAll().size();

        // Create the EnumSsPayScheme
        EnumSsPaySchemeDTO enumSsPaySchemeDTO = enumSsPaySchemeMapper.toDto(enumSsPayScheme);
        restEnumSsPaySchemeMockMvc.perform(post("/api/enum-ss-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsPaySchemeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumSsPayScheme in the database
        List<EnumSsPayScheme> enumSsPaySchemeList = enumSsPaySchemeRepository.findAll();
        assertThat(enumSsPaySchemeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumSsPayScheme testEnumSsPayScheme = enumSsPaySchemeList.get(enumSsPaySchemeList.size() - 1);
        assertThat(testEnumSsPayScheme.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumSsPayScheme.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumSsPayScheme.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumSsPaySchemeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumSsPaySchemeRepository.findAll().size();

        // Create the EnumSsPayScheme with an existing ID
        enumSsPayScheme.setId(1L);
        EnumSsPaySchemeDTO enumSsPaySchemeDTO = enumSsPaySchemeMapper.toDto(enumSsPayScheme);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumSsPaySchemeMockMvc.perform(post("/api/enum-ss-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsPaySchemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumSsPayScheme in the database
        List<EnumSsPayScheme> enumSsPaySchemeList = enumSsPaySchemeRepository.findAll();
        assertThat(enumSsPaySchemeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumSsPaySchemes() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList
        restEnumSsPaySchemeMockMvc.perform(get("/api/enum-ss-pay-schemes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumSsPayScheme.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumSsPayScheme() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get the enumSsPayScheme
        restEnumSsPaySchemeMockMvc.perform(get("/api/enum-ss-pay-schemes/{id}", enumSsPayScheme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumSsPayScheme.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumSsPaySchemeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumSsPaySchemeList where valuez equals to UPDATED_VALUEZ
        defaultEnumSsPaySchemeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumSsPaySchemeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumSsPaySchemeList where valuez equals to UPDATED_VALUEZ
        defaultEnumSsPaySchemeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where valuez is not null
        defaultEnumSsPaySchemeShouldBeFound("valuez.specified=true");

        // Get all the enumSsPaySchemeList where valuez is null
        defaultEnumSsPaySchemeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumSsPaySchemeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumSsPaySchemeList where orderz equals to UPDATED_ORDERZ
        defaultEnumSsPaySchemeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumSsPaySchemeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumSsPaySchemeList where orderz equals to UPDATED_ORDERZ
        defaultEnumSsPaySchemeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where orderz is not null
        defaultEnumSsPaySchemeShouldBeFound("orderz.specified=true");

        // Get all the enumSsPaySchemeList where orderz is null
        defaultEnumSsPaySchemeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumSsPaySchemeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumSsPaySchemeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumSsPaySchemeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumSsPaySchemeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumSsPaySchemeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumSsPaySchemeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumSsPaySchemeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumSsPaySchemeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumSsPaySchemeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumSsPaySchemeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumSsPaySchemeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumSsPaySchemeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        // Get all the enumSsPaySchemeList where tenentCode is not null
        defaultEnumSsPaySchemeShouldBeFound("tenentCode.specified=true");

        // Get all the enumSsPaySchemeList where tenentCode is null
        defaultEnumSsPaySchemeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumSsPaySchemesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumSsPayScheme parent = EnumSsPaySchemeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumSsPayScheme.setParent(parent);
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);
        Long parentId = parent.getId();

        // Get all the enumSsPaySchemeList where parent equals to parentId
        defaultEnumSsPaySchemeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumSsPaySchemeList where parent equals to parentId + 1
        defaultEnumSsPaySchemeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumSsPaySchemeShouldBeFound(String filter) throws Exception {
        restEnumSsPaySchemeMockMvc.perform(get("/api/enum-ss-pay-schemes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumSsPayScheme.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumSsPaySchemeMockMvc.perform(get("/api/enum-ss-pay-schemes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumSsPaySchemeShouldNotBeFound(String filter) throws Exception {
        restEnumSsPaySchemeMockMvc.perform(get("/api/enum-ss-pay-schemes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumSsPaySchemeMockMvc.perform(get("/api/enum-ss-pay-schemes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumSsPayScheme() throws Exception {
        // Get the enumSsPayScheme
        restEnumSsPaySchemeMockMvc.perform(get("/api/enum-ss-pay-schemes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumSsPayScheme() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        int databaseSizeBeforeUpdate = enumSsPaySchemeRepository.findAll().size();

        // Update the enumSsPayScheme
        EnumSsPayScheme updatedEnumSsPayScheme = enumSsPaySchemeRepository.findById(enumSsPayScheme.getId()).get();
        // Disconnect from session so that the updates on updatedEnumSsPayScheme are not directly saved in db
        em.detach(updatedEnumSsPayScheme);
        updatedEnumSsPayScheme
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumSsPaySchemeDTO enumSsPaySchemeDTO = enumSsPaySchemeMapper.toDto(updatedEnumSsPayScheme);

        restEnumSsPaySchemeMockMvc.perform(put("/api/enum-ss-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsPaySchemeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumSsPayScheme in the database
        List<EnumSsPayScheme> enumSsPaySchemeList = enumSsPaySchemeRepository.findAll();
        assertThat(enumSsPaySchemeList).hasSize(databaseSizeBeforeUpdate);
        EnumSsPayScheme testEnumSsPayScheme = enumSsPaySchemeList.get(enumSsPaySchemeList.size() - 1);
        assertThat(testEnumSsPayScheme.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumSsPayScheme.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumSsPayScheme.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumSsPayScheme() throws Exception {
        int databaseSizeBeforeUpdate = enumSsPaySchemeRepository.findAll().size();

        // Create the EnumSsPayScheme
        EnumSsPaySchemeDTO enumSsPaySchemeDTO = enumSsPaySchemeMapper.toDto(enumSsPayScheme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumSsPaySchemeMockMvc.perform(put("/api/enum-ss-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsPaySchemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumSsPayScheme in the database
        List<EnumSsPayScheme> enumSsPaySchemeList = enumSsPaySchemeRepository.findAll();
        assertThat(enumSsPaySchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumSsPayScheme() throws Exception {
        // Initialize the database
        enumSsPaySchemeRepository.saveAndFlush(enumSsPayScheme);

        int databaseSizeBeforeDelete = enumSsPaySchemeRepository.findAll().size();

        // Delete the enumSsPayScheme
        restEnumSsPaySchemeMockMvc.perform(delete("/api/enum-ss-pay-schemes/{id}", enumSsPayScheme.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumSsPayScheme> enumSsPaySchemeList = enumSsPaySchemeRepository.findAll();
        assertThat(enumSsPaySchemeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumSsPayScheme.class);
        EnumSsPayScheme enumSsPayScheme1 = new EnumSsPayScheme();
        enumSsPayScheme1.setId(1L);
        EnumSsPayScheme enumSsPayScheme2 = new EnumSsPayScheme();
        enumSsPayScheme2.setId(enumSsPayScheme1.getId());
        assertThat(enumSsPayScheme1).isEqualTo(enumSsPayScheme2);
        enumSsPayScheme2.setId(2L);
        assertThat(enumSsPayScheme1).isNotEqualTo(enumSsPayScheme2);
        enumSsPayScheme1.setId(null);
        assertThat(enumSsPayScheme1).isNotEqualTo(enumSsPayScheme2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumSsPaySchemeDTO.class);
        EnumSsPaySchemeDTO enumSsPaySchemeDTO1 = new EnumSsPaySchemeDTO();
        enumSsPaySchemeDTO1.setId(1L);
        EnumSsPaySchemeDTO enumSsPaySchemeDTO2 = new EnumSsPaySchemeDTO();
        assertThat(enumSsPaySchemeDTO1).isNotEqualTo(enumSsPaySchemeDTO2);
        enumSsPaySchemeDTO2.setId(enumSsPaySchemeDTO1.getId());
        assertThat(enumSsPaySchemeDTO1).isEqualTo(enumSsPaySchemeDTO2);
        enumSsPaySchemeDTO2.setId(2L);
        assertThat(enumSsPaySchemeDTO1).isNotEqualTo(enumSsPaySchemeDTO2);
        enumSsPaySchemeDTO1.setId(null);
        assertThat(enumSsPaySchemeDTO1).isNotEqualTo(enumSsPaySchemeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumSsPaySchemeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumSsPaySchemeMapper.fromId(null)).isNull();
    }
}
