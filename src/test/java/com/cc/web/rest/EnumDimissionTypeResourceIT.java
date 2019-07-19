package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumDimissionType;
import com.cc.domain.EnumDimissionType;
import com.cc.repository.EnumDimissionTypeRepository;
import com.cc.service.EnumDimissionTypeService;
import com.cc.service.dto.EnumDimissionTypeDTO;
import com.cc.service.mapper.EnumDimissionTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumDimissionTypeCriteria;
import com.cc.service.EnumDimissionTypeQueryService;

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
 * Integration tests for the {@Link EnumDimissionTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumDimissionTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumDimissionTypeRepository enumDimissionTypeRepository;

    @Autowired
    private EnumDimissionTypeMapper enumDimissionTypeMapper;

    @Autowired
    private EnumDimissionTypeService enumDimissionTypeService;

    @Autowired
    private EnumDimissionTypeQueryService enumDimissionTypeQueryService;

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

    private MockMvc restEnumDimissionTypeMockMvc;

    private EnumDimissionType enumDimissionType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumDimissionTypeResource enumDimissionTypeResource = new EnumDimissionTypeResource(enumDimissionTypeService, enumDimissionTypeQueryService);
        this.restEnumDimissionTypeMockMvc = MockMvcBuilders.standaloneSetup(enumDimissionTypeResource)
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
    public static EnumDimissionType createEntity(EntityManager em) {
        EnumDimissionType enumDimissionType = new EnumDimissionType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumDimissionType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumDimissionType createUpdatedEntity(EntityManager em) {
        EnumDimissionType enumDimissionType = new EnumDimissionType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumDimissionType;
    }

    @BeforeEach
    public void initTest() {
        enumDimissionType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumDimissionType() throws Exception {
        int databaseSizeBeforeCreate = enumDimissionTypeRepository.findAll().size();

        // Create the EnumDimissionType
        EnumDimissionTypeDTO enumDimissionTypeDTO = enumDimissionTypeMapper.toDto(enumDimissionType);
        restEnumDimissionTypeMockMvc.perform(post("/api/enum-dimission-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDimissionTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumDimissionType in the database
        List<EnumDimissionType> enumDimissionTypeList = enumDimissionTypeRepository.findAll();
        assertThat(enumDimissionTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumDimissionType testEnumDimissionType = enumDimissionTypeList.get(enumDimissionTypeList.size() - 1);
        assertThat(testEnumDimissionType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumDimissionType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumDimissionType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumDimissionTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumDimissionTypeRepository.findAll().size();

        // Create the EnumDimissionType with an existing ID
        enumDimissionType.setId(1L);
        EnumDimissionTypeDTO enumDimissionTypeDTO = enumDimissionTypeMapper.toDto(enumDimissionType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumDimissionTypeMockMvc.perform(post("/api/enum-dimission-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDimissionTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumDimissionType in the database
        List<EnumDimissionType> enumDimissionTypeList = enumDimissionTypeRepository.findAll();
        assertThat(enumDimissionTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumDimissionTypes() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList
        restEnumDimissionTypeMockMvc.perform(get("/api/enum-dimission-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumDimissionType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumDimissionType() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get the enumDimissionType
        restEnumDimissionTypeMockMvc.perform(get("/api/enum-dimission-types/{id}", enumDimissionType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumDimissionType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumDimissionTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumDimissionTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumDimissionTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumDimissionTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumDimissionTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumDimissionTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where valuez is not null
        defaultEnumDimissionTypeShouldBeFound("valuez.specified=true");

        // Get all the enumDimissionTypeList where valuez is null
        defaultEnumDimissionTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumDimissionTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumDimissionTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumDimissionTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumDimissionTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumDimissionTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumDimissionTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where orderz is not null
        defaultEnumDimissionTypeShouldBeFound("orderz.specified=true");

        // Get all the enumDimissionTypeList where orderz is null
        defaultEnumDimissionTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumDimissionTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumDimissionTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumDimissionTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumDimissionTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumDimissionTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumDimissionTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumDimissionTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumDimissionTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumDimissionTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumDimissionTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumDimissionTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumDimissionTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumDimissionTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        // Get all the enumDimissionTypeList where tenentCode is not null
        defaultEnumDimissionTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumDimissionTypeList where tenentCode is null
        defaultEnumDimissionTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumDimissionTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumDimissionType parent = EnumDimissionTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumDimissionType.setParent(parent);
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);
        Long parentId = parent.getId();

        // Get all the enumDimissionTypeList where parent equals to parentId
        defaultEnumDimissionTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumDimissionTypeList where parent equals to parentId + 1
        defaultEnumDimissionTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumDimissionTypeShouldBeFound(String filter) throws Exception {
        restEnumDimissionTypeMockMvc.perform(get("/api/enum-dimission-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumDimissionType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumDimissionTypeMockMvc.perform(get("/api/enum-dimission-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumDimissionTypeShouldNotBeFound(String filter) throws Exception {
        restEnumDimissionTypeMockMvc.perform(get("/api/enum-dimission-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumDimissionTypeMockMvc.perform(get("/api/enum-dimission-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumDimissionType() throws Exception {
        // Get the enumDimissionType
        restEnumDimissionTypeMockMvc.perform(get("/api/enum-dimission-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumDimissionType() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        int databaseSizeBeforeUpdate = enumDimissionTypeRepository.findAll().size();

        // Update the enumDimissionType
        EnumDimissionType updatedEnumDimissionType = enumDimissionTypeRepository.findById(enumDimissionType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumDimissionType are not directly saved in db
        em.detach(updatedEnumDimissionType);
        updatedEnumDimissionType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumDimissionTypeDTO enumDimissionTypeDTO = enumDimissionTypeMapper.toDto(updatedEnumDimissionType);

        restEnumDimissionTypeMockMvc.perform(put("/api/enum-dimission-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDimissionTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumDimissionType in the database
        List<EnumDimissionType> enumDimissionTypeList = enumDimissionTypeRepository.findAll();
        assertThat(enumDimissionTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumDimissionType testEnumDimissionType = enumDimissionTypeList.get(enumDimissionTypeList.size() - 1);
        assertThat(testEnumDimissionType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumDimissionType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumDimissionType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumDimissionType() throws Exception {
        int databaseSizeBeforeUpdate = enumDimissionTypeRepository.findAll().size();

        // Create the EnumDimissionType
        EnumDimissionTypeDTO enumDimissionTypeDTO = enumDimissionTypeMapper.toDto(enumDimissionType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumDimissionTypeMockMvc.perform(put("/api/enum-dimission-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDimissionTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumDimissionType in the database
        List<EnumDimissionType> enumDimissionTypeList = enumDimissionTypeRepository.findAll();
        assertThat(enumDimissionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumDimissionType() throws Exception {
        // Initialize the database
        enumDimissionTypeRepository.saveAndFlush(enumDimissionType);

        int databaseSizeBeforeDelete = enumDimissionTypeRepository.findAll().size();

        // Delete the enumDimissionType
        restEnumDimissionTypeMockMvc.perform(delete("/api/enum-dimission-types/{id}", enumDimissionType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumDimissionType> enumDimissionTypeList = enumDimissionTypeRepository.findAll();
        assertThat(enumDimissionTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumDimissionType.class);
        EnumDimissionType enumDimissionType1 = new EnumDimissionType();
        enumDimissionType1.setId(1L);
        EnumDimissionType enumDimissionType2 = new EnumDimissionType();
        enumDimissionType2.setId(enumDimissionType1.getId());
        assertThat(enumDimissionType1).isEqualTo(enumDimissionType2);
        enumDimissionType2.setId(2L);
        assertThat(enumDimissionType1).isNotEqualTo(enumDimissionType2);
        enumDimissionType1.setId(null);
        assertThat(enumDimissionType1).isNotEqualTo(enumDimissionType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumDimissionTypeDTO.class);
        EnumDimissionTypeDTO enumDimissionTypeDTO1 = new EnumDimissionTypeDTO();
        enumDimissionTypeDTO1.setId(1L);
        EnumDimissionTypeDTO enumDimissionTypeDTO2 = new EnumDimissionTypeDTO();
        assertThat(enumDimissionTypeDTO1).isNotEqualTo(enumDimissionTypeDTO2);
        enumDimissionTypeDTO2.setId(enumDimissionTypeDTO1.getId());
        assertThat(enumDimissionTypeDTO1).isEqualTo(enumDimissionTypeDTO2);
        enumDimissionTypeDTO2.setId(2L);
        assertThat(enumDimissionTypeDTO1).isNotEqualTo(enumDimissionTypeDTO2);
        enumDimissionTypeDTO1.setId(null);
        assertThat(enumDimissionTypeDTO1).isNotEqualTo(enumDimissionTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumDimissionTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumDimissionTypeMapper.fromId(null)).isNull();
    }
}
