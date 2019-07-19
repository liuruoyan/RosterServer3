package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumAccountType;
import com.cc.domain.EnumAccountType;
import com.cc.repository.EnumAccountTypeRepository;
import com.cc.service.EnumAccountTypeService;
import com.cc.service.dto.EnumAccountTypeDTO;
import com.cc.service.mapper.EnumAccountTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumAccountTypeCriteria;
import com.cc.service.EnumAccountTypeQueryService;

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
 * Integration tests for the {@Link EnumAccountTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumAccountTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumAccountTypeRepository enumAccountTypeRepository;

    @Autowired
    private EnumAccountTypeMapper enumAccountTypeMapper;

    @Autowired
    private EnumAccountTypeService enumAccountTypeService;

    @Autowired
    private EnumAccountTypeQueryService enumAccountTypeQueryService;

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

    private MockMvc restEnumAccountTypeMockMvc;

    private EnumAccountType enumAccountType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumAccountTypeResource enumAccountTypeResource = new EnumAccountTypeResource(enumAccountTypeService, enumAccountTypeQueryService);
        this.restEnumAccountTypeMockMvc = MockMvcBuilders.standaloneSetup(enumAccountTypeResource)
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
    public static EnumAccountType createEntity(EntityManager em) {
        EnumAccountType enumAccountType = new EnumAccountType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumAccountType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumAccountType createUpdatedEntity(EntityManager em) {
        EnumAccountType enumAccountType = new EnumAccountType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumAccountType;
    }

    @BeforeEach
    public void initTest() {
        enumAccountType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumAccountType() throws Exception {
        int databaseSizeBeforeCreate = enumAccountTypeRepository.findAll().size();

        // Create the EnumAccountType
        EnumAccountTypeDTO enumAccountTypeDTO = enumAccountTypeMapper.toDto(enumAccountType);
        restEnumAccountTypeMockMvc.perform(post("/api/enum-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumAccountTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumAccountType in the database
        List<EnumAccountType> enumAccountTypeList = enumAccountTypeRepository.findAll();
        assertThat(enumAccountTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumAccountType testEnumAccountType = enumAccountTypeList.get(enumAccountTypeList.size() - 1);
        assertThat(testEnumAccountType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumAccountType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumAccountType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumAccountTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumAccountTypeRepository.findAll().size();

        // Create the EnumAccountType with an existing ID
        enumAccountType.setId(1L);
        EnumAccountTypeDTO enumAccountTypeDTO = enumAccountTypeMapper.toDto(enumAccountType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumAccountTypeMockMvc.perform(post("/api/enum-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumAccountTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumAccountType in the database
        List<EnumAccountType> enumAccountTypeList = enumAccountTypeRepository.findAll();
        assertThat(enumAccountTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumAccountTypes() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList
        restEnumAccountTypeMockMvc.perform(get("/api/enum-account-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumAccountType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumAccountType() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get the enumAccountType
        restEnumAccountTypeMockMvc.perform(get("/api/enum-account-types/{id}", enumAccountType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumAccountType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumAccountTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumAccountTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumAccountTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumAccountTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumAccountTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumAccountTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where valuez is not null
        defaultEnumAccountTypeShouldBeFound("valuez.specified=true");

        // Get all the enumAccountTypeList where valuez is null
        defaultEnumAccountTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumAccountTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumAccountTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumAccountTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumAccountTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumAccountTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumAccountTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where orderz is not null
        defaultEnumAccountTypeShouldBeFound("orderz.specified=true");

        // Get all the enumAccountTypeList where orderz is null
        defaultEnumAccountTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumAccountTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumAccountTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumAccountTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumAccountTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumAccountTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumAccountTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumAccountTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumAccountTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumAccountTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumAccountTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumAccountTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumAccountTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumAccountTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        // Get all the enumAccountTypeList where tenentCode is not null
        defaultEnumAccountTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumAccountTypeList where tenentCode is null
        defaultEnumAccountTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumAccountTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumAccountType parent = EnumAccountTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumAccountType.setParent(parent);
        enumAccountTypeRepository.saveAndFlush(enumAccountType);
        Long parentId = parent.getId();

        // Get all the enumAccountTypeList where parent equals to parentId
        defaultEnumAccountTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumAccountTypeList where parent equals to parentId + 1
        defaultEnumAccountTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumAccountTypeShouldBeFound(String filter) throws Exception {
        restEnumAccountTypeMockMvc.perform(get("/api/enum-account-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumAccountType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumAccountTypeMockMvc.perform(get("/api/enum-account-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumAccountTypeShouldNotBeFound(String filter) throws Exception {
        restEnumAccountTypeMockMvc.perform(get("/api/enum-account-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumAccountTypeMockMvc.perform(get("/api/enum-account-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumAccountType() throws Exception {
        // Get the enumAccountType
        restEnumAccountTypeMockMvc.perform(get("/api/enum-account-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumAccountType() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        int databaseSizeBeforeUpdate = enumAccountTypeRepository.findAll().size();

        // Update the enumAccountType
        EnumAccountType updatedEnumAccountType = enumAccountTypeRepository.findById(enumAccountType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumAccountType are not directly saved in db
        em.detach(updatedEnumAccountType);
        updatedEnumAccountType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumAccountTypeDTO enumAccountTypeDTO = enumAccountTypeMapper.toDto(updatedEnumAccountType);

        restEnumAccountTypeMockMvc.perform(put("/api/enum-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumAccountTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumAccountType in the database
        List<EnumAccountType> enumAccountTypeList = enumAccountTypeRepository.findAll();
        assertThat(enumAccountTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumAccountType testEnumAccountType = enumAccountTypeList.get(enumAccountTypeList.size() - 1);
        assertThat(testEnumAccountType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumAccountType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumAccountType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumAccountType() throws Exception {
        int databaseSizeBeforeUpdate = enumAccountTypeRepository.findAll().size();

        // Create the EnumAccountType
        EnumAccountTypeDTO enumAccountTypeDTO = enumAccountTypeMapper.toDto(enumAccountType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumAccountTypeMockMvc.perform(put("/api/enum-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumAccountTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumAccountType in the database
        List<EnumAccountType> enumAccountTypeList = enumAccountTypeRepository.findAll();
        assertThat(enumAccountTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumAccountType() throws Exception {
        // Initialize the database
        enumAccountTypeRepository.saveAndFlush(enumAccountType);

        int databaseSizeBeforeDelete = enumAccountTypeRepository.findAll().size();

        // Delete the enumAccountType
        restEnumAccountTypeMockMvc.perform(delete("/api/enum-account-types/{id}", enumAccountType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumAccountType> enumAccountTypeList = enumAccountTypeRepository.findAll();
        assertThat(enumAccountTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumAccountType.class);
        EnumAccountType enumAccountType1 = new EnumAccountType();
        enumAccountType1.setId(1L);
        EnumAccountType enumAccountType2 = new EnumAccountType();
        enumAccountType2.setId(enumAccountType1.getId());
        assertThat(enumAccountType1).isEqualTo(enumAccountType2);
        enumAccountType2.setId(2L);
        assertThat(enumAccountType1).isNotEqualTo(enumAccountType2);
        enumAccountType1.setId(null);
        assertThat(enumAccountType1).isNotEqualTo(enumAccountType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumAccountTypeDTO.class);
        EnumAccountTypeDTO enumAccountTypeDTO1 = new EnumAccountTypeDTO();
        enumAccountTypeDTO1.setId(1L);
        EnumAccountTypeDTO enumAccountTypeDTO2 = new EnumAccountTypeDTO();
        assertThat(enumAccountTypeDTO1).isNotEqualTo(enumAccountTypeDTO2);
        enumAccountTypeDTO2.setId(enumAccountTypeDTO1.getId());
        assertThat(enumAccountTypeDTO1).isEqualTo(enumAccountTypeDTO2);
        enumAccountTypeDTO2.setId(2L);
        assertThat(enumAccountTypeDTO1).isNotEqualTo(enumAccountTypeDTO2);
        enumAccountTypeDTO1.setId(null);
        assertThat(enumAccountTypeDTO1).isNotEqualTo(enumAccountTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumAccountTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumAccountTypeMapper.fromId(null)).isNull();
    }
}
