package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumPfPayScheme;
import com.cc.domain.EnumPfPayScheme;
import com.cc.repository.EnumPfPaySchemeRepository;
import com.cc.service.EnumPfPaySchemeService;
import com.cc.service.dto.EnumPfPaySchemeDTO;
import com.cc.service.mapper.EnumPfPaySchemeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumPfPaySchemeCriteria;
import com.cc.service.EnumPfPaySchemeQueryService;

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
 * Integration tests for the {@Link EnumPfPaySchemeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumPfPaySchemeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumPfPaySchemeRepository enumPfPaySchemeRepository;

    @Autowired
    private EnumPfPaySchemeMapper enumPfPaySchemeMapper;

    @Autowired
    private EnumPfPaySchemeService enumPfPaySchemeService;

    @Autowired
    private EnumPfPaySchemeQueryService enumPfPaySchemeQueryService;

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

    private MockMvc restEnumPfPaySchemeMockMvc;

    private EnumPfPayScheme enumPfPayScheme;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumPfPaySchemeResource enumPfPaySchemeResource = new EnumPfPaySchemeResource(enumPfPaySchemeService, enumPfPaySchemeQueryService);
        this.restEnumPfPaySchemeMockMvc = MockMvcBuilders.standaloneSetup(enumPfPaySchemeResource)
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
    public static EnumPfPayScheme createEntity(EntityManager em) {
        EnumPfPayScheme enumPfPayScheme = new EnumPfPayScheme()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumPfPayScheme;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumPfPayScheme createUpdatedEntity(EntityManager em) {
        EnumPfPayScheme enumPfPayScheme = new EnumPfPayScheme()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumPfPayScheme;
    }

    @BeforeEach
    public void initTest() {
        enumPfPayScheme = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumPfPayScheme() throws Exception {
        int databaseSizeBeforeCreate = enumPfPaySchemeRepository.findAll().size();

        // Create the EnumPfPayScheme
        EnumPfPaySchemeDTO enumPfPaySchemeDTO = enumPfPaySchemeMapper.toDto(enumPfPayScheme);
        restEnumPfPaySchemeMockMvc.perform(post("/api/enum-pf-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfPaySchemeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumPfPayScheme in the database
        List<EnumPfPayScheme> enumPfPaySchemeList = enumPfPaySchemeRepository.findAll();
        assertThat(enumPfPaySchemeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumPfPayScheme testEnumPfPayScheme = enumPfPaySchemeList.get(enumPfPaySchemeList.size() - 1);
        assertThat(testEnumPfPayScheme.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumPfPayScheme.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumPfPayScheme.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumPfPaySchemeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumPfPaySchemeRepository.findAll().size();

        // Create the EnumPfPayScheme with an existing ID
        enumPfPayScheme.setId(1L);
        EnumPfPaySchemeDTO enumPfPaySchemeDTO = enumPfPaySchemeMapper.toDto(enumPfPayScheme);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumPfPaySchemeMockMvc.perform(post("/api/enum-pf-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfPaySchemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPfPayScheme in the database
        List<EnumPfPayScheme> enumPfPaySchemeList = enumPfPaySchemeRepository.findAll();
        assertThat(enumPfPaySchemeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumPfPaySchemes() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList
        restEnumPfPaySchemeMockMvc.perform(get("/api/enum-pf-pay-schemes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPfPayScheme.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumPfPayScheme() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get the enumPfPayScheme
        restEnumPfPaySchemeMockMvc.perform(get("/api/enum-pf-pay-schemes/{id}", enumPfPayScheme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumPfPayScheme.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumPfPaySchemeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumPfPaySchemeList where valuez equals to UPDATED_VALUEZ
        defaultEnumPfPaySchemeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumPfPaySchemeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumPfPaySchemeList where valuez equals to UPDATED_VALUEZ
        defaultEnumPfPaySchemeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where valuez is not null
        defaultEnumPfPaySchemeShouldBeFound("valuez.specified=true");

        // Get all the enumPfPaySchemeList where valuez is null
        defaultEnumPfPaySchemeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumPfPaySchemeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumPfPaySchemeList where orderz equals to UPDATED_ORDERZ
        defaultEnumPfPaySchemeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumPfPaySchemeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumPfPaySchemeList where orderz equals to UPDATED_ORDERZ
        defaultEnumPfPaySchemeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where orderz is not null
        defaultEnumPfPaySchemeShouldBeFound("orderz.specified=true");

        // Get all the enumPfPaySchemeList where orderz is null
        defaultEnumPfPaySchemeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumPfPaySchemeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumPfPaySchemeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumPfPaySchemeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumPfPaySchemeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumPfPaySchemeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumPfPaySchemeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumPfPaySchemeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumPfPaySchemeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPfPaySchemeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumPfPaySchemeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumPfPaySchemeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPfPaySchemeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        // Get all the enumPfPaySchemeList where tenentCode is not null
        defaultEnumPfPaySchemeShouldBeFound("tenentCode.specified=true");

        // Get all the enumPfPaySchemeList where tenentCode is null
        defaultEnumPfPaySchemeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfPaySchemesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPfPayScheme parent = EnumPfPaySchemeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumPfPayScheme.setParent(parent);
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);
        Long parentId = parent.getId();

        // Get all the enumPfPaySchemeList where parent equals to parentId
        defaultEnumPfPaySchemeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumPfPaySchemeList where parent equals to parentId + 1
        defaultEnumPfPaySchemeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumPfPaySchemeShouldBeFound(String filter) throws Exception {
        restEnumPfPaySchemeMockMvc.perform(get("/api/enum-pf-pay-schemes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPfPayScheme.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumPfPaySchemeMockMvc.perform(get("/api/enum-pf-pay-schemes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumPfPaySchemeShouldNotBeFound(String filter) throws Exception {
        restEnumPfPaySchemeMockMvc.perform(get("/api/enum-pf-pay-schemes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumPfPaySchemeMockMvc.perform(get("/api/enum-pf-pay-schemes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumPfPayScheme() throws Exception {
        // Get the enumPfPayScheme
        restEnumPfPaySchemeMockMvc.perform(get("/api/enum-pf-pay-schemes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumPfPayScheme() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        int databaseSizeBeforeUpdate = enumPfPaySchemeRepository.findAll().size();

        // Update the enumPfPayScheme
        EnumPfPayScheme updatedEnumPfPayScheme = enumPfPaySchemeRepository.findById(enumPfPayScheme.getId()).get();
        // Disconnect from session so that the updates on updatedEnumPfPayScheme are not directly saved in db
        em.detach(updatedEnumPfPayScheme);
        updatedEnumPfPayScheme
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumPfPaySchemeDTO enumPfPaySchemeDTO = enumPfPaySchemeMapper.toDto(updatedEnumPfPayScheme);

        restEnumPfPaySchemeMockMvc.perform(put("/api/enum-pf-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfPaySchemeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumPfPayScheme in the database
        List<EnumPfPayScheme> enumPfPaySchemeList = enumPfPaySchemeRepository.findAll();
        assertThat(enumPfPaySchemeList).hasSize(databaseSizeBeforeUpdate);
        EnumPfPayScheme testEnumPfPayScheme = enumPfPaySchemeList.get(enumPfPaySchemeList.size() - 1);
        assertThat(testEnumPfPayScheme.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumPfPayScheme.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumPfPayScheme.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumPfPayScheme() throws Exception {
        int databaseSizeBeforeUpdate = enumPfPaySchemeRepository.findAll().size();

        // Create the EnumPfPayScheme
        EnumPfPaySchemeDTO enumPfPaySchemeDTO = enumPfPaySchemeMapper.toDto(enumPfPayScheme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumPfPaySchemeMockMvc.perform(put("/api/enum-pf-pay-schemes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfPaySchemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPfPayScheme in the database
        List<EnumPfPayScheme> enumPfPaySchemeList = enumPfPaySchemeRepository.findAll();
        assertThat(enumPfPaySchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumPfPayScheme() throws Exception {
        // Initialize the database
        enumPfPaySchemeRepository.saveAndFlush(enumPfPayScheme);

        int databaseSizeBeforeDelete = enumPfPaySchemeRepository.findAll().size();

        // Delete the enumPfPayScheme
        restEnumPfPaySchemeMockMvc.perform(delete("/api/enum-pf-pay-schemes/{id}", enumPfPayScheme.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumPfPayScheme> enumPfPaySchemeList = enumPfPaySchemeRepository.findAll();
        assertThat(enumPfPaySchemeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPfPayScheme.class);
        EnumPfPayScheme enumPfPayScheme1 = new EnumPfPayScheme();
        enumPfPayScheme1.setId(1L);
        EnumPfPayScheme enumPfPayScheme2 = new EnumPfPayScheme();
        enumPfPayScheme2.setId(enumPfPayScheme1.getId());
        assertThat(enumPfPayScheme1).isEqualTo(enumPfPayScheme2);
        enumPfPayScheme2.setId(2L);
        assertThat(enumPfPayScheme1).isNotEqualTo(enumPfPayScheme2);
        enumPfPayScheme1.setId(null);
        assertThat(enumPfPayScheme1).isNotEqualTo(enumPfPayScheme2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPfPaySchemeDTO.class);
        EnumPfPaySchemeDTO enumPfPaySchemeDTO1 = new EnumPfPaySchemeDTO();
        enumPfPaySchemeDTO1.setId(1L);
        EnumPfPaySchemeDTO enumPfPaySchemeDTO2 = new EnumPfPaySchemeDTO();
        assertThat(enumPfPaySchemeDTO1).isNotEqualTo(enumPfPaySchemeDTO2);
        enumPfPaySchemeDTO2.setId(enumPfPaySchemeDTO1.getId());
        assertThat(enumPfPaySchemeDTO1).isEqualTo(enumPfPaySchemeDTO2);
        enumPfPaySchemeDTO2.setId(2L);
        assertThat(enumPfPaySchemeDTO1).isNotEqualTo(enumPfPaySchemeDTO2);
        enumPfPaySchemeDTO1.setId(null);
        assertThat(enumPfPaySchemeDTO1).isNotEqualTo(enumPfPaySchemeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumPfPaySchemeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumPfPaySchemeMapper.fromId(null)).isNull();
    }
}
