package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumEmpTaxerType;
import com.cc.domain.EnumEmpTaxerType;
import com.cc.repository.EnumEmpTaxerTypeRepository;
import com.cc.service.EnumEmpTaxerTypeService;
import com.cc.service.dto.EnumEmpTaxerTypeDTO;
import com.cc.service.mapper.EnumEmpTaxerTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumEmpTaxerTypeCriteria;
import com.cc.service.EnumEmpTaxerTypeQueryService;

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
 * Integration tests for the {@Link EnumEmpTaxerTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumEmpTaxerTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumEmpTaxerTypeRepository enumEmpTaxerTypeRepository;

    @Autowired
    private EnumEmpTaxerTypeMapper enumEmpTaxerTypeMapper;

    @Autowired
    private EnumEmpTaxerTypeService enumEmpTaxerTypeService;

    @Autowired
    private EnumEmpTaxerTypeQueryService enumEmpTaxerTypeQueryService;

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

    private MockMvc restEnumEmpTaxerTypeMockMvc;

    private EnumEmpTaxerType enumEmpTaxerType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumEmpTaxerTypeResource enumEmpTaxerTypeResource = new EnumEmpTaxerTypeResource(enumEmpTaxerTypeService, enumEmpTaxerTypeQueryService);
        this.restEnumEmpTaxerTypeMockMvc = MockMvcBuilders.standaloneSetup(enumEmpTaxerTypeResource)
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
    public static EnumEmpTaxerType createEntity(EntityManager em) {
        EnumEmpTaxerType enumEmpTaxerType = new EnumEmpTaxerType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumEmpTaxerType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumEmpTaxerType createUpdatedEntity(EntityManager em) {
        EnumEmpTaxerType enumEmpTaxerType = new EnumEmpTaxerType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumEmpTaxerType;
    }

    @BeforeEach
    public void initTest() {
        enumEmpTaxerType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumEmpTaxerType() throws Exception {
        int databaseSizeBeforeCreate = enumEmpTaxerTypeRepository.findAll().size();

        // Create the EnumEmpTaxerType
        EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO = enumEmpTaxerTypeMapper.toDto(enumEmpTaxerType);
        restEnumEmpTaxerTypeMockMvc.perform(post("/api/enum-emp-taxer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxerTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumEmpTaxerType in the database
        List<EnumEmpTaxerType> enumEmpTaxerTypeList = enumEmpTaxerTypeRepository.findAll();
        assertThat(enumEmpTaxerTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumEmpTaxerType testEnumEmpTaxerType = enumEmpTaxerTypeList.get(enumEmpTaxerTypeList.size() - 1);
        assertThat(testEnumEmpTaxerType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumEmpTaxerType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumEmpTaxerType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumEmpTaxerTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumEmpTaxerTypeRepository.findAll().size();

        // Create the EnumEmpTaxerType with an existing ID
        enumEmpTaxerType.setId(1L);
        EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO = enumEmpTaxerTypeMapper.toDto(enumEmpTaxerType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumEmpTaxerTypeMockMvc.perform(post("/api/enum-emp-taxer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxerTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpTaxerType in the database
        List<EnumEmpTaxerType> enumEmpTaxerTypeList = enumEmpTaxerTypeRepository.findAll();
        assertThat(enumEmpTaxerTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypes() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList
        restEnumEmpTaxerTypeMockMvc.perform(get("/api/enum-emp-taxer-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpTaxerType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumEmpTaxerType() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get the enumEmpTaxerType
        restEnumEmpTaxerTypeMockMvc.perform(get("/api/enum-emp-taxer-types/{id}", enumEmpTaxerType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumEmpTaxerType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumEmpTaxerTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumEmpTaxerTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpTaxerTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumEmpTaxerTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumEmpTaxerTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpTaxerTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where valuez is not null
        defaultEnumEmpTaxerTypeShouldBeFound("valuez.specified=true");

        // Get all the enumEmpTaxerTypeList where valuez is null
        defaultEnumEmpTaxerTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumEmpTaxerTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTaxerTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpTaxerTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumEmpTaxerTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumEmpTaxerTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpTaxerTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where orderz is not null
        defaultEnumEmpTaxerTypeShouldBeFound("orderz.specified=true");

        // Get all the enumEmpTaxerTypeList where orderz is null
        defaultEnumEmpTaxerTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumEmpTaxerTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTaxerTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumEmpTaxerTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumEmpTaxerTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTaxerTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumEmpTaxerTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumEmpTaxerTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumEmpTaxerTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpTaxerTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumEmpTaxerTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumEmpTaxerTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpTaxerTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        // Get all the enumEmpTaxerTypeList where tenentCode is not null
        defaultEnumEmpTaxerTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumEmpTaxerTypeList where tenentCode is null
        defaultEnumEmpTaxerTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxerTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpTaxerType parent = EnumEmpTaxerTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumEmpTaxerType.setParent(parent);
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);
        Long parentId = parent.getId();

        // Get all the enumEmpTaxerTypeList where parent equals to parentId
        defaultEnumEmpTaxerTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumEmpTaxerTypeList where parent equals to parentId + 1
        defaultEnumEmpTaxerTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumEmpTaxerTypeShouldBeFound(String filter) throws Exception {
        restEnumEmpTaxerTypeMockMvc.perform(get("/api/enum-emp-taxer-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpTaxerType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumEmpTaxerTypeMockMvc.perform(get("/api/enum-emp-taxer-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumEmpTaxerTypeShouldNotBeFound(String filter) throws Exception {
        restEnumEmpTaxerTypeMockMvc.perform(get("/api/enum-emp-taxer-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumEmpTaxerTypeMockMvc.perform(get("/api/enum-emp-taxer-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumEmpTaxerType() throws Exception {
        // Get the enumEmpTaxerType
        restEnumEmpTaxerTypeMockMvc.perform(get("/api/enum-emp-taxer-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumEmpTaxerType() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        int databaseSizeBeforeUpdate = enumEmpTaxerTypeRepository.findAll().size();

        // Update the enumEmpTaxerType
        EnumEmpTaxerType updatedEnumEmpTaxerType = enumEmpTaxerTypeRepository.findById(enumEmpTaxerType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumEmpTaxerType are not directly saved in db
        em.detach(updatedEnumEmpTaxerType);
        updatedEnumEmpTaxerType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO = enumEmpTaxerTypeMapper.toDto(updatedEnumEmpTaxerType);

        restEnumEmpTaxerTypeMockMvc.perform(put("/api/enum-emp-taxer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxerTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumEmpTaxerType in the database
        List<EnumEmpTaxerType> enumEmpTaxerTypeList = enumEmpTaxerTypeRepository.findAll();
        assertThat(enumEmpTaxerTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumEmpTaxerType testEnumEmpTaxerType = enumEmpTaxerTypeList.get(enumEmpTaxerTypeList.size() - 1);
        assertThat(testEnumEmpTaxerType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumEmpTaxerType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumEmpTaxerType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumEmpTaxerType() throws Exception {
        int databaseSizeBeforeUpdate = enumEmpTaxerTypeRepository.findAll().size();

        // Create the EnumEmpTaxerType
        EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO = enumEmpTaxerTypeMapper.toDto(enumEmpTaxerType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumEmpTaxerTypeMockMvc.perform(put("/api/enum-emp-taxer-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxerTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpTaxerType in the database
        List<EnumEmpTaxerType> enumEmpTaxerTypeList = enumEmpTaxerTypeRepository.findAll();
        assertThat(enumEmpTaxerTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumEmpTaxerType() throws Exception {
        // Initialize the database
        enumEmpTaxerTypeRepository.saveAndFlush(enumEmpTaxerType);

        int databaseSizeBeforeDelete = enumEmpTaxerTypeRepository.findAll().size();

        // Delete the enumEmpTaxerType
        restEnumEmpTaxerTypeMockMvc.perform(delete("/api/enum-emp-taxer-types/{id}", enumEmpTaxerType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumEmpTaxerType> enumEmpTaxerTypeList = enumEmpTaxerTypeRepository.findAll();
        assertThat(enumEmpTaxerTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpTaxerType.class);
        EnumEmpTaxerType enumEmpTaxerType1 = new EnumEmpTaxerType();
        enumEmpTaxerType1.setId(1L);
        EnumEmpTaxerType enumEmpTaxerType2 = new EnumEmpTaxerType();
        enumEmpTaxerType2.setId(enumEmpTaxerType1.getId());
        assertThat(enumEmpTaxerType1).isEqualTo(enumEmpTaxerType2);
        enumEmpTaxerType2.setId(2L);
        assertThat(enumEmpTaxerType1).isNotEqualTo(enumEmpTaxerType2);
        enumEmpTaxerType1.setId(null);
        assertThat(enumEmpTaxerType1).isNotEqualTo(enumEmpTaxerType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpTaxerTypeDTO.class);
        EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO1 = new EnumEmpTaxerTypeDTO();
        enumEmpTaxerTypeDTO1.setId(1L);
        EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO2 = new EnumEmpTaxerTypeDTO();
        assertThat(enumEmpTaxerTypeDTO1).isNotEqualTo(enumEmpTaxerTypeDTO2);
        enumEmpTaxerTypeDTO2.setId(enumEmpTaxerTypeDTO1.getId());
        assertThat(enumEmpTaxerTypeDTO1).isEqualTo(enumEmpTaxerTypeDTO2);
        enumEmpTaxerTypeDTO2.setId(2L);
        assertThat(enumEmpTaxerTypeDTO1).isNotEqualTo(enumEmpTaxerTypeDTO2);
        enumEmpTaxerTypeDTO1.setId(null);
        assertThat(enumEmpTaxerTypeDTO1).isNotEqualTo(enumEmpTaxerTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumEmpTaxerTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumEmpTaxerTypeMapper.fromId(null)).isNull();
    }
}
