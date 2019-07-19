package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumContractType;
import com.cc.domain.EnumContractType;
import com.cc.repository.EnumContractTypeRepository;
import com.cc.service.EnumContractTypeService;
import com.cc.service.dto.EnumContractTypeDTO;
import com.cc.service.mapper.EnumContractTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumContractTypeCriteria;
import com.cc.service.EnumContractTypeQueryService;

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
 * Integration tests for the {@Link EnumContractTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumContractTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumContractTypeRepository enumContractTypeRepository;

    @Autowired
    private EnumContractTypeMapper enumContractTypeMapper;

    @Autowired
    private EnumContractTypeService enumContractTypeService;

    @Autowired
    private EnumContractTypeQueryService enumContractTypeQueryService;

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

    private MockMvc restEnumContractTypeMockMvc;

    private EnumContractType enumContractType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumContractTypeResource enumContractTypeResource = new EnumContractTypeResource(enumContractTypeService, enumContractTypeQueryService);
        this.restEnumContractTypeMockMvc = MockMvcBuilders.standaloneSetup(enumContractTypeResource)
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
    public static EnumContractType createEntity(EntityManager em) {
        EnumContractType enumContractType = new EnumContractType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumContractType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumContractType createUpdatedEntity(EntityManager em) {
        EnumContractType enumContractType = new EnumContractType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumContractType;
    }

    @BeforeEach
    public void initTest() {
        enumContractType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumContractType() throws Exception {
        int databaseSizeBeforeCreate = enumContractTypeRepository.findAll().size();

        // Create the EnumContractType
        EnumContractTypeDTO enumContractTypeDTO = enumContractTypeMapper.toDto(enumContractType);
        restEnumContractTypeMockMvc.perform(post("/api/enum-contract-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumContractTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumContractType in the database
        List<EnumContractType> enumContractTypeList = enumContractTypeRepository.findAll();
        assertThat(enumContractTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumContractType testEnumContractType = enumContractTypeList.get(enumContractTypeList.size() - 1);
        assertThat(testEnumContractType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumContractType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumContractType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumContractTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumContractTypeRepository.findAll().size();

        // Create the EnumContractType with an existing ID
        enumContractType.setId(1L);
        EnumContractTypeDTO enumContractTypeDTO = enumContractTypeMapper.toDto(enumContractType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumContractTypeMockMvc.perform(post("/api/enum-contract-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumContractTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumContractType in the database
        List<EnumContractType> enumContractTypeList = enumContractTypeRepository.findAll();
        assertThat(enumContractTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumContractTypes() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList
        restEnumContractTypeMockMvc.perform(get("/api/enum-contract-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumContractType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumContractType() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get the enumContractType
        restEnumContractTypeMockMvc.perform(get("/api/enum-contract-types/{id}", enumContractType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumContractType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumContractTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumContractTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumContractTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumContractTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumContractTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumContractTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where valuez is not null
        defaultEnumContractTypeShouldBeFound("valuez.specified=true");

        // Get all the enumContractTypeList where valuez is null
        defaultEnumContractTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumContractTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumContractTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumContractTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumContractTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumContractTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumContractTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where orderz is not null
        defaultEnumContractTypeShouldBeFound("orderz.specified=true");

        // Get all the enumContractTypeList where orderz is null
        defaultEnumContractTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumContractTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumContractTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumContractTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumContractTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumContractTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumContractTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumContractTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumContractTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumContractTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumContractTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumContractTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumContractTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumContractTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        // Get all the enumContractTypeList where tenentCode is not null
        defaultEnumContractTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumContractTypeList where tenentCode is null
        defaultEnumContractTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumContractTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumContractType parent = EnumContractTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumContractType.setParent(parent);
        enumContractTypeRepository.saveAndFlush(enumContractType);
        Long parentId = parent.getId();

        // Get all the enumContractTypeList where parent equals to parentId
        defaultEnumContractTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumContractTypeList where parent equals to parentId + 1
        defaultEnumContractTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumContractTypeShouldBeFound(String filter) throws Exception {
        restEnumContractTypeMockMvc.perform(get("/api/enum-contract-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumContractType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumContractTypeMockMvc.perform(get("/api/enum-contract-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumContractTypeShouldNotBeFound(String filter) throws Exception {
        restEnumContractTypeMockMvc.perform(get("/api/enum-contract-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumContractTypeMockMvc.perform(get("/api/enum-contract-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumContractType() throws Exception {
        // Get the enumContractType
        restEnumContractTypeMockMvc.perform(get("/api/enum-contract-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumContractType() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        int databaseSizeBeforeUpdate = enumContractTypeRepository.findAll().size();

        // Update the enumContractType
        EnumContractType updatedEnumContractType = enumContractTypeRepository.findById(enumContractType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumContractType are not directly saved in db
        em.detach(updatedEnumContractType);
        updatedEnumContractType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumContractTypeDTO enumContractTypeDTO = enumContractTypeMapper.toDto(updatedEnumContractType);

        restEnumContractTypeMockMvc.perform(put("/api/enum-contract-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumContractTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumContractType in the database
        List<EnumContractType> enumContractTypeList = enumContractTypeRepository.findAll();
        assertThat(enumContractTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumContractType testEnumContractType = enumContractTypeList.get(enumContractTypeList.size() - 1);
        assertThat(testEnumContractType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumContractType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumContractType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumContractType() throws Exception {
        int databaseSizeBeforeUpdate = enumContractTypeRepository.findAll().size();

        // Create the EnumContractType
        EnumContractTypeDTO enumContractTypeDTO = enumContractTypeMapper.toDto(enumContractType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumContractTypeMockMvc.perform(put("/api/enum-contract-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumContractTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumContractType in the database
        List<EnumContractType> enumContractTypeList = enumContractTypeRepository.findAll();
        assertThat(enumContractTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumContractType() throws Exception {
        // Initialize the database
        enumContractTypeRepository.saveAndFlush(enumContractType);

        int databaseSizeBeforeDelete = enumContractTypeRepository.findAll().size();

        // Delete the enumContractType
        restEnumContractTypeMockMvc.perform(delete("/api/enum-contract-types/{id}", enumContractType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumContractType> enumContractTypeList = enumContractTypeRepository.findAll();
        assertThat(enumContractTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumContractType.class);
        EnumContractType enumContractType1 = new EnumContractType();
        enumContractType1.setId(1L);
        EnumContractType enumContractType2 = new EnumContractType();
        enumContractType2.setId(enumContractType1.getId());
        assertThat(enumContractType1).isEqualTo(enumContractType2);
        enumContractType2.setId(2L);
        assertThat(enumContractType1).isNotEqualTo(enumContractType2);
        enumContractType1.setId(null);
        assertThat(enumContractType1).isNotEqualTo(enumContractType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumContractTypeDTO.class);
        EnumContractTypeDTO enumContractTypeDTO1 = new EnumContractTypeDTO();
        enumContractTypeDTO1.setId(1L);
        EnumContractTypeDTO enumContractTypeDTO2 = new EnumContractTypeDTO();
        assertThat(enumContractTypeDTO1).isNotEqualTo(enumContractTypeDTO2);
        enumContractTypeDTO2.setId(enumContractTypeDTO1.getId());
        assertThat(enumContractTypeDTO1).isEqualTo(enumContractTypeDTO2);
        enumContractTypeDTO2.setId(2L);
        assertThat(enumContractTypeDTO1).isNotEqualTo(enumContractTypeDTO2);
        enumContractTypeDTO1.setId(null);
        assertThat(enumContractTypeDTO1).isNotEqualTo(enumContractTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumContractTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumContractTypeMapper.fromId(null)).isNull();
    }
}
