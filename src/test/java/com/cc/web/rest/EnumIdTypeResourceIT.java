package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumIdType;
import com.cc.domain.EnumIdType;
import com.cc.repository.EnumIdTypeRepository;
import com.cc.service.EnumIdTypeService;
import com.cc.service.dto.EnumIdTypeDTO;
import com.cc.service.mapper.EnumIdTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumIdTypeCriteria;
import com.cc.service.EnumIdTypeQueryService;

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
 * Integration tests for the {@Link EnumIdTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumIdTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumIdTypeRepository enumIdTypeRepository;

    @Autowired
    private EnumIdTypeMapper enumIdTypeMapper;

    @Autowired
    private EnumIdTypeService enumIdTypeService;

    @Autowired
    private EnumIdTypeQueryService enumIdTypeQueryService;

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

    private MockMvc restEnumIdTypeMockMvc;

    private EnumIdType enumIdType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumIdTypeResource enumIdTypeResource = new EnumIdTypeResource(enumIdTypeService, enumIdTypeQueryService);
        this.restEnumIdTypeMockMvc = MockMvcBuilders.standaloneSetup(enumIdTypeResource)
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
    public static EnumIdType createEntity(EntityManager em) {
        EnumIdType enumIdType = new EnumIdType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumIdType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumIdType createUpdatedEntity(EntityManager em) {
        EnumIdType enumIdType = new EnumIdType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumIdType;
    }

    @BeforeEach
    public void initTest() {
        enumIdType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumIdType() throws Exception {
        int databaseSizeBeforeCreate = enumIdTypeRepository.findAll().size();

        // Create the EnumIdType
        EnumIdTypeDTO enumIdTypeDTO = enumIdTypeMapper.toDto(enumIdType);
        restEnumIdTypeMockMvc.perform(post("/api/enum-id-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumIdTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumIdType in the database
        List<EnumIdType> enumIdTypeList = enumIdTypeRepository.findAll();
        assertThat(enumIdTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumIdType testEnumIdType = enumIdTypeList.get(enumIdTypeList.size() - 1);
        assertThat(testEnumIdType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumIdType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumIdType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumIdTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumIdTypeRepository.findAll().size();

        // Create the EnumIdType with an existing ID
        enumIdType.setId(1L);
        EnumIdTypeDTO enumIdTypeDTO = enumIdTypeMapper.toDto(enumIdType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumIdTypeMockMvc.perform(post("/api/enum-id-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumIdTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumIdType in the database
        List<EnumIdType> enumIdTypeList = enumIdTypeRepository.findAll();
        assertThat(enumIdTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumIdTypes() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList
        restEnumIdTypeMockMvc.perform(get("/api/enum-id-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumIdType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumIdType() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get the enumIdType
        restEnumIdTypeMockMvc.perform(get("/api/enum-id-types/{id}", enumIdType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumIdType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumIdTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumIdTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumIdTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumIdTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumIdTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumIdTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where valuez is not null
        defaultEnumIdTypeShouldBeFound("valuez.specified=true");

        // Get all the enumIdTypeList where valuez is null
        defaultEnumIdTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumIdTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumIdTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumIdTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumIdTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumIdTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumIdTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where orderz is not null
        defaultEnumIdTypeShouldBeFound("orderz.specified=true");

        // Get all the enumIdTypeList where orderz is null
        defaultEnumIdTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumIdTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumIdTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumIdTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumIdTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumIdTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumIdTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumIdTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumIdTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumIdTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumIdTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumIdTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumIdTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumIdTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        // Get all the enumIdTypeList where tenentCode is not null
        defaultEnumIdTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumIdTypeList where tenentCode is null
        defaultEnumIdTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumIdTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumIdType parent = EnumIdTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumIdType.setParent(parent);
        enumIdTypeRepository.saveAndFlush(enumIdType);
        Long parentId = parent.getId();

        // Get all the enumIdTypeList where parent equals to parentId
        defaultEnumIdTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumIdTypeList where parent equals to parentId + 1
        defaultEnumIdTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumIdTypeShouldBeFound(String filter) throws Exception {
        restEnumIdTypeMockMvc.perform(get("/api/enum-id-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumIdType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumIdTypeMockMvc.perform(get("/api/enum-id-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumIdTypeShouldNotBeFound(String filter) throws Exception {
        restEnumIdTypeMockMvc.perform(get("/api/enum-id-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumIdTypeMockMvc.perform(get("/api/enum-id-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumIdType() throws Exception {
        // Get the enumIdType
        restEnumIdTypeMockMvc.perform(get("/api/enum-id-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumIdType() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        int databaseSizeBeforeUpdate = enumIdTypeRepository.findAll().size();

        // Update the enumIdType
        EnumIdType updatedEnumIdType = enumIdTypeRepository.findById(enumIdType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumIdType are not directly saved in db
        em.detach(updatedEnumIdType);
        updatedEnumIdType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumIdTypeDTO enumIdTypeDTO = enumIdTypeMapper.toDto(updatedEnumIdType);

        restEnumIdTypeMockMvc.perform(put("/api/enum-id-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumIdTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumIdType in the database
        List<EnumIdType> enumIdTypeList = enumIdTypeRepository.findAll();
        assertThat(enumIdTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumIdType testEnumIdType = enumIdTypeList.get(enumIdTypeList.size() - 1);
        assertThat(testEnumIdType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumIdType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumIdType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumIdType() throws Exception {
        int databaseSizeBeforeUpdate = enumIdTypeRepository.findAll().size();

        // Create the EnumIdType
        EnumIdTypeDTO enumIdTypeDTO = enumIdTypeMapper.toDto(enumIdType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumIdTypeMockMvc.perform(put("/api/enum-id-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumIdTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumIdType in the database
        List<EnumIdType> enumIdTypeList = enumIdTypeRepository.findAll();
        assertThat(enumIdTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumIdType() throws Exception {
        // Initialize the database
        enumIdTypeRepository.saveAndFlush(enumIdType);

        int databaseSizeBeforeDelete = enumIdTypeRepository.findAll().size();

        // Delete the enumIdType
        restEnumIdTypeMockMvc.perform(delete("/api/enum-id-types/{id}", enumIdType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumIdType> enumIdTypeList = enumIdTypeRepository.findAll();
        assertThat(enumIdTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumIdType.class);
        EnumIdType enumIdType1 = new EnumIdType();
        enumIdType1.setId(1L);
        EnumIdType enumIdType2 = new EnumIdType();
        enumIdType2.setId(enumIdType1.getId());
        assertThat(enumIdType1).isEqualTo(enumIdType2);
        enumIdType2.setId(2L);
        assertThat(enumIdType1).isNotEqualTo(enumIdType2);
        enumIdType1.setId(null);
        assertThat(enumIdType1).isNotEqualTo(enumIdType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumIdTypeDTO.class);
        EnumIdTypeDTO enumIdTypeDTO1 = new EnumIdTypeDTO();
        enumIdTypeDTO1.setId(1L);
        EnumIdTypeDTO enumIdTypeDTO2 = new EnumIdTypeDTO();
        assertThat(enumIdTypeDTO1).isNotEqualTo(enumIdTypeDTO2);
        enumIdTypeDTO2.setId(enumIdTypeDTO1.getId());
        assertThat(enumIdTypeDTO1).isEqualTo(enumIdTypeDTO2);
        enumIdTypeDTO2.setId(2L);
        assertThat(enumIdTypeDTO1).isNotEqualTo(enumIdTypeDTO2);
        enumIdTypeDTO1.setId(null);
        assertThat(enumIdTypeDTO1).isNotEqualTo(enumIdTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumIdTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumIdTypeMapper.fromId(null)).isNull();
    }
}
