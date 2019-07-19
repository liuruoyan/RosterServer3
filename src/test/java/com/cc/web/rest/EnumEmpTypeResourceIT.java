package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumEmpType;
import com.cc.domain.EnumEmpType;
import com.cc.repository.EnumEmpTypeRepository;
import com.cc.service.EnumEmpTypeService;
import com.cc.service.dto.EnumEmpTypeDTO;
import com.cc.service.mapper.EnumEmpTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumEmpTypeCriteria;
import com.cc.service.EnumEmpTypeQueryService;

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
 * Integration tests for the {@Link EnumEmpTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumEmpTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumEmpTypeRepository enumEmpTypeRepository;

    @Autowired
    private EnumEmpTypeMapper enumEmpTypeMapper;

    @Autowired
    private EnumEmpTypeService enumEmpTypeService;

    @Autowired
    private EnumEmpTypeQueryService enumEmpTypeQueryService;

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

    private MockMvc restEnumEmpTypeMockMvc;

    private EnumEmpType enumEmpType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumEmpTypeResource enumEmpTypeResource = new EnumEmpTypeResource(enumEmpTypeService, enumEmpTypeQueryService);
        this.restEnumEmpTypeMockMvc = MockMvcBuilders.standaloneSetup(enumEmpTypeResource)
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
    public static EnumEmpType createEntity(EntityManager em) {
        EnumEmpType enumEmpType = new EnumEmpType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumEmpType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumEmpType createUpdatedEntity(EntityManager em) {
        EnumEmpType enumEmpType = new EnumEmpType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumEmpType;
    }

    @BeforeEach
    public void initTest() {
        enumEmpType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumEmpType() throws Exception {
        int databaseSizeBeforeCreate = enumEmpTypeRepository.findAll().size();

        // Create the EnumEmpType
        EnumEmpTypeDTO enumEmpTypeDTO = enumEmpTypeMapper.toDto(enumEmpType);
        restEnumEmpTypeMockMvc.perform(post("/api/enum-emp-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumEmpType in the database
        List<EnumEmpType> enumEmpTypeList = enumEmpTypeRepository.findAll();
        assertThat(enumEmpTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumEmpType testEnumEmpType = enumEmpTypeList.get(enumEmpTypeList.size() - 1);
        assertThat(testEnumEmpType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumEmpType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumEmpType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumEmpTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumEmpTypeRepository.findAll().size();

        // Create the EnumEmpType with an existing ID
        enumEmpType.setId(1L);
        EnumEmpTypeDTO enumEmpTypeDTO = enumEmpTypeMapper.toDto(enumEmpType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumEmpTypeMockMvc.perform(post("/api/enum-emp-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpType in the database
        List<EnumEmpType> enumEmpTypeList = enumEmpTypeRepository.findAll();
        assertThat(enumEmpTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumEmpTypes() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList
        restEnumEmpTypeMockMvc.perform(get("/api/enum-emp-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumEmpType() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get the enumEmpType
        restEnumEmpTypeMockMvc.perform(get("/api/enum-emp-types/{id}", enumEmpType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumEmpType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumEmpTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumEmpTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumEmpTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumEmpTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where valuez is not null
        defaultEnumEmpTypeShouldBeFound("valuez.specified=true");

        // Get all the enumEmpTypeList where valuez is null
        defaultEnumEmpTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumEmpTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumEmpTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumEmpTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where orderz is not null
        defaultEnumEmpTypeShouldBeFound("orderz.specified=true");

        // Get all the enumEmpTypeList where orderz is null
        defaultEnumEmpTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumEmpTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumEmpTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumEmpTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumEmpTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumEmpTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumEmpTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumEmpTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumEmpTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumEmpTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        // Get all the enumEmpTypeList where tenentCode is not null
        defaultEnumEmpTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumEmpTypeList where tenentCode is null
        defaultEnumEmpTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpType parent = EnumEmpTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumEmpType.setParent(parent);
        enumEmpTypeRepository.saveAndFlush(enumEmpType);
        Long parentId = parent.getId();

        // Get all the enumEmpTypeList where parent equals to parentId
        defaultEnumEmpTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumEmpTypeList where parent equals to parentId + 1
        defaultEnumEmpTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumEmpTypeShouldBeFound(String filter) throws Exception {
        restEnumEmpTypeMockMvc.perform(get("/api/enum-emp-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumEmpTypeMockMvc.perform(get("/api/enum-emp-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumEmpTypeShouldNotBeFound(String filter) throws Exception {
        restEnumEmpTypeMockMvc.perform(get("/api/enum-emp-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumEmpTypeMockMvc.perform(get("/api/enum-emp-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumEmpType() throws Exception {
        // Get the enumEmpType
        restEnumEmpTypeMockMvc.perform(get("/api/enum-emp-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumEmpType() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        int databaseSizeBeforeUpdate = enumEmpTypeRepository.findAll().size();

        // Update the enumEmpType
        EnumEmpType updatedEnumEmpType = enumEmpTypeRepository.findById(enumEmpType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumEmpType are not directly saved in db
        em.detach(updatedEnumEmpType);
        updatedEnumEmpType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumEmpTypeDTO enumEmpTypeDTO = enumEmpTypeMapper.toDto(updatedEnumEmpType);

        restEnumEmpTypeMockMvc.perform(put("/api/enum-emp-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumEmpType in the database
        List<EnumEmpType> enumEmpTypeList = enumEmpTypeRepository.findAll();
        assertThat(enumEmpTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumEmpType testEnumEmpType = enumEmpTypeList.get(enumEmpTypeList.size() - 1);
        assertThat(testEnumEmpType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumEmpType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumEmpType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumEmpType() throws Exception {
        int databaseSizeBeforeUpdate = enumEmpTypeRepository.findAll().size();

        // Create the EnumEmpType
        EnumEmpTypeDTO enumEmpTypeDTO = enumEmpTypeMapper.toDto(enumEmpType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumEmpTypeMockMvc.perform(put("/api/enum-emp-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpType in the database
        List<EnumEmpType> enumEmpTypeList = enumEmpTypeRepository.findAll();
        assertThat(enumEmpTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumEmpType() throws Exception {
        // Initialize the database
        enumEmpTypeRepository.saveAndFlush(enumEmpType);

        int databaseSizeBeforeDelete = enumEmpTypeRepository.findAll().size();

        // Delete the enumEmpType
        restEnumEmpTypeMockMvc.perform(delete("/api/enum-emp-types/{id}", enumEmpType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumEmpType> enumEmpTypeList = enumEmpTypeRepository.findAll();
        assertThat(enumEmpTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpType.class);
        EnumEmpType enumEmpType1 = new EnumEmpType();
        enumEmpType1.setId(1L);
        EnumEmpType enumEmpType2 = new EnumEmpType();
        enumEmpType2.setId(enumEmpType1.getId());
        assertThat(enumEmpType1).isEqualTo(enumEmpType2);
        enumEmpType2.setId(2L);
        assertThat(enumEmpType1).isNotEqualTo(enumEmpType2);
        enumEmpType1.setId(null);
        assertThat(enumEmpType1).isNotEqualTo(enumEmpType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpTypeDTO.class);
        EnumEmpTypeDTO enumEmpTypeDTO1 = new EnumEmpTypeDTO();
        enumEmpTypeDTO1.setId(1L);
        EnumEmpTypeDTO enumEmpTypeDTO2 = new EnumEmpTypeDTO();
        assertThat(enumEmpTypeDTO1).isNotEqualTo(enumEmpTypeDTO2);
        enumEmpTypeDTO2.setId(enumEmpTypeDTO1.getId());
        assertThat(enumEmpTypeDTO1).isEqualTo(enumEmpTypeDTO2);
        enumEmpTypeDTO2.setId(2L);
        assertThat(enumEmpTypeDTO1).isNotEqualTo(enumEmpTypeDTO2);
        enumEmpTypeDTO1.setId(null);
        assertThat(enumEmpTypeDTO1).isNotEqualTo(enumEmpTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumEmpTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumEmpTypeMapper.fromId(null)).isNull();
    }
}
