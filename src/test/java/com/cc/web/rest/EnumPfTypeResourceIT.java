package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumPfType;
import com.cc.domain.EnumPfType;
import com.cc.repository.EnumPfTypeRepository;
import com.cc.service.EnumPfTypeService;
import com.cc.service.dto.EnumPfTypeDTO;
import com.cc.service.mapper.EnumPfTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumPfTypeCriteria;
import com.cc.service.EnumPfTypeQueryService;

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
 * Integration tests for the {@Link EnumPfTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumPfTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumPfTypeRepository enumPfTypeRepository;

    @Autowired
    private EnumPfTypeMapper enumPfTypeMapper;

    @Autowired
    private EnumPfTypeService enumPfTypeService;

    @Autowired
    private EnumPfTypeQueryService enumPfTypeQueryService;

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

    private MockMvc restEnumPfTypeMockMvc;

    private EnumPfType enumPfType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumPfTypeResource enumPfTypeResource = new EnumPfTypeResource(enumPfTypeService, enumPfTypeQueryService);
        this.restEnumPfTypeMockMvc = MockMvcBuilders.standaloneSetup(enumPfTypeResource)
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
    public static EnumPfType createEntity(EntityManager em) {
        EnumPfType enumPfType = new EnumPfType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumPfType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumPfType createUpdatedEntity(EntityManager em) {
        EnumPfType enumPfType = new EnumPfType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumPfType;
    }

    @BeforeEach
    public void initTest() {
        enumPfType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumPfType() throws Exception {
        int databaseSizeBeforeCreate = enumPfTypeRepository.findAll().size();

        // Create the EnumPfType
        EnumPfTypeDTO enumPfTypeDTO = enumPfTypeMapper.toDto(enumPfType);
        restEnumPfTypeMockMvc.perform(post("/api/enum-pf-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumPfType in the database
        List<EnumPfType> enumPfTypeList = enumPfTypeRepository.findAll();
        assertThat(enumPfTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumPfType testEnumPfType = enumPfTypeList.get(enumPfTypeList.size() - 1);
        assertThat(testEnumPfType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumPfType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumPfType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumPfTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumPfTypeRepository.findAll().size();

        // Create the EnumPfType with an existing ID
        enumPfType.setId(1L);
        EnumPfTypeDTO enumPfTypeDTO = enumPfTypeMapper.toDto(enumPfType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumPfTypeMockMvc.perform(post("/api/enum-pf-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPfType in the database
        List<EnumPfType> enumPfTypeList = enumPfTypeRepository.findAll();
        assertThat(enumPfTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumPfTypes() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList
        restEnumPfTypeMockMvc.perform(get("/api/enum-pf-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPfType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumPfType() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get the enumPfType
        restEnumPfTypeMockMvc.perform(get("/api/enum-pf-types/{id}", enumPfType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumPfType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumPfTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumPfTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumPfTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumPfTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumPfTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumPfTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where valuez is not null
        defaultEnumPfTypeShouldBeFound("valuez.specified=true");

        // Get all the enumPfTypeList where valuez is null
        defaultEnumPfTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumPfTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumPfTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumPfTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumPfTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumPfTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumPfTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where orderz is not null
        defaultEnumPfTypeShouldBeFound("orderz.specified=true");

        // Get all the enumPfTypeList where orderz is null
        defaultEnumPfTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumPfTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumPfTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumPfTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumPfTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumPfTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumPfTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumPfTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumPfTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumPfTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPfTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumPfTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumPfTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPfTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        // Get all the enumPfTypeList where tenentCode is not null
        defaultEnumPfTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumPfTypeList where tenentCode is null
        defaultEnumPfTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPfType parent = EnumPfTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumPfType.setParent(parent);
        enumPfTypeRepository.saveAndFlush(enumPfType);
        Long parentId = parent.getId();

        // Get all the enumPfTypeList where parent equals to parentId
        defaultEnumPfTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumPfTypeList where parent equals to parentId + 1
        defaultEnumPfTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumPfTypeShouldBeFound(String filter) throws Exception {
        restEnumPfTypeMockMvc.perform(get("/api/enum-pf-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPfType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumPfTypeMockMvc.perform(get("/api/enum-pf-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumPfTypeShouldNotBeFound(String filter) throws Exception {
        restEnumPfTypeMockMvc.perform(get("/api/enum-pf-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumPfTypeMockMvc.perform(get("/api/enum-pf-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumPfType() throws Exception {
        // Get the enumPfType
        restEnumPfTypeMockMvc.perform(get("/api/enum-pf-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumPfType() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        int databaseSizeBeforeUpdate = enumPfTypeRepository.findAll().size();

        // Update the enumPfType
        EnumPfType updatedEnumPfType = enumPfTypeRepository.findById(enumPfType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumPfType are not directly saved in db
        em.detach(updatedEnumPfType);
        updatedEnumPfType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumPfTypeDTO enumPfTypeDTO = enumPfTypeMapper.toDto(updatedEnumPfType);

        restEnumPfTypeMockMvc.perform(put("/api/enum-pf-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumPfType in the database
        List<EnumPfType> enumPfTypeList = enumPfTypeRepository.findAll();
        assertThat(enumPfTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumPfType testEnumPfType = enumPfTypeList.get(enumPfTypeList.size() - 1);
        assertThat(testEnumPfType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumPfType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumPfType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumPfType() throws Exception {
        int databaseSizeBeforeUpdate = enumPfTypeRepository.findAll().size();

        // Create the EnumPfType
        EnumPfTypeDTO enumPfTypeDTO = enumPfTypeMapper.toDto(enumPfType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumPfTypeMockMvc.perform(put("/api/enum-pf-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPfType in the database
        List<EnumPfType> enumPfTypeList = enumPfTypeRepository.findAll();
        assertThat(enumPfTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumPfType() throws Exception {
        // Initialize the database
        enumPfTypeRepository.saveAndFlush(enumPfType);

        int databaseSizeBeforeDelete = enumPfTypeRepository.findAll().size();

        // Delete the enumPfType
        restEnumPfTypeMockMvc.perform(delete("/api/enum-pf-types/{id}", enumPfType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumPfType> enumPfTypeList = enumPfTypeRepository.findAll();
        assertThat(enumPfTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPfType.class);
        EnumPfType enumPfType1 = new EnumPfType();
        enumPfType1.setId(1L);
        EnumPfType enumPfType2 = new EnumPfType();
        enumPfType2.setId(enumPfType1.getId());
        assertThat(enumPfType1).isEqualTo(enumPfType2);
        enumPfType2.setId(2L);
        assertThat(enumPfType1).isNotEqualTo(enumPfType2);
        enumPfType1.setId(null);
        assertThat(enumPfType1).isNotEqualTo(enumPfType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPfTypeDTO.class);
        EnumPfTypeDTO enumPfTypeDTO1 = new EnumPfTypeDTO();
        enumPfTypeDTO1.setId(1L);
        EnumPfTypeDTO enumPfTypeDTO2 = new EnumPfTypeDTO();
        assertThat(enumPfTypeDTO1).isNotEqualTo(enumPfTypeDTO2);
        enumPfTypeDTO2.setId(enumPfTypeDTO1.getId());
        assertThat(enumPfTypeDTO1).isEqualTo(enumPfTypeDTO2);
        enumPfTypeDTO2.setId(2L);
        assertThat(enumPfTypeDTO1).isNotEqualTo(enumPfTypeDTO2);
        enumPfTypeDTO1.setId(null);
        assertThat(enumPfTypeDTO1).isNotEqualTo(enumPfTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumPfTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumPfTypeMapper.fromId(null)).isNull();
    }
}
