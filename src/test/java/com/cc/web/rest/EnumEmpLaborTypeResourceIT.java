package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumEmpLaborType;
import com.cc.domain.EnumEmpLaborType;
import com.cc.repository.EnumEmpLaborTypeRepository;
import com.cc.service.EnumEmpLaborTypeService;
import com.cc.service.dto.EnumEmpLaborTypeDTO;
import com.cc.service.mapper.EnumEmpLaborTypeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumEmpLaborTypeCriteria;
import com.cc.service.EnumEmpLaborTypeQueryService;

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
 * Integration tests for the {@Link EnumEmpLaborTypeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumEmpLaborTypeResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumEmpLaborTypeRepository enumEmpLaborTypeRepository;

    @Autowired
    private EnumEmpLaborTypeMapper enumEmpLaborTypeMapper;

    @Autowired
    private EnumEmpLaborTypeService enumEmpLaborTypeService;

    @Autowired
    private EnumEmpLaborTypeQueryService enumEmpLaborTypeQueryService;

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

    private MockMvc restEnumEmpLaborTypeMockMvc;

    private EnumEmpLaborType enumEmpLaborType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumEmpLaborTypeResource enumEmpLaborTypeResource = new EnumEmpLaborTypeResource(enumEmpLaborTypeService, enumEmpLaborTypeQueryService);
        this.restEnumEmpLaborTypeMockMvc = MockMvcBuilders.standaloneSetup(enumEmpLaborTypeResource)
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
    public static EnumEmpLaborType createEntity(EntityManager em) {
        EnumEmpLaborType enumEmpLaborType = new EnumEmpLaborType()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumEmpLaborType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumEmpLaborType createUpdatedEntity(EntityManager em) {
        EnumEmpLaborType enumEmpLaborType = new EnumEmpLaborType()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumEmpLaborType;
    }

    @BeforeEach
    public void initTest() {
        enumEmpLaborType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumEmpLaborType() throws Exception {
        int databaseSizeBeforeCreate = enumEmpLaborTypeRepository.findAll().size();

        // Create the EnumEmpLaborType
        EnumEmpLaborTypeDTO enumEmpLaborTypeDTO = enumEmpLaborTypeMapper.toDto(enumEmpLaborType);
        restEnumEmpLaborTypeMockMvc.perform(post("/api/enum-emp-labor-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpLaborTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumEmpLaborType in the database
        List<EnumEmpLaborType> enumEmpLaborTypeList = enumEmpLaborTypeRepository.findAll();
        assertThat(enumEmpLaborTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EnumEmpLaborType testEnumEmpLaborType = enumEmpLaborTypeList.get(enumEmpLaborTypeList.size() - 1);
        assertThat(testEnumEmpLaborType.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumEmpLaborType.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumEmpLaborType.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumEmpLaborTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumEmpLaborTypeRepository.findAll().size();

        // Create the EnumEmpLaborType with an existing ID
        enumEmpLaborType.setId(1L);
        EnumEmpLaborTypeDTO enumEmpLaborTypeDTO = enumEmpLaborTypeMapper.toDto(enumEmpLaborType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumEmpLaborTypeMockMvc.perform(post("/api/enum-emp-labor-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpLaborTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpLaborType in the database
        List<EnumEmpLaborType> enumEmpLaborTypeList = enumEmpLaborTypeRepository.findAll();
        assertThat(enumEmpLaborTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumEmpLaborTypes() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList
        restEnumEmpLaborTypeMockMvc.perform(get("/api/enum-emp-labor-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpLaborType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumEmpLaborType() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get the enumEmpLaborType
        restEnumEmpLaborTypeMockMvc.perform(get("/api/enum-emp-labor-types/{id}", enumEmpLaborType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumEmpLaborType.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where valuez equals to DEFAULT_VALUEZ
        defaultEnumEmpLaborTypeShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumEmpLaborTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpLaborTypeShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumEmpLaborTypeShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumEmpLaborTypeList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpLaborTypeShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where valuez is not null
        defaultEnumEmpLaborTypeShouldBeFound("valuez.specified=true");

        // Get all the enumEmpLaborTypeList where valuez is null
        defaultEnumEmpLaborTypeShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where orderz equals to DEFAULT_ORDERZ
        defaultEnumEmpLaborTypeShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumEmpLaborTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpLaborTypeShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumEmpLaborTypeShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumEmpLaborTypeList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpLaborTypeShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where orderz is not null
        defaultEnumEmpLaborTypeShouldBeFound("orderz.specified=true");

        // Get all the enumEmpLaborTypeList where orderz is null
        defaultEnumEmpLaborTypeShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumEmpLaborTypeShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpLaborTypeList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumEmpLaborTypeShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumEmpLaborTypeShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpLaborTypeList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumEmpLaborTypeShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumEmpLaborTypeShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumEmpLaborTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpLaborTypeShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumEmpLaborTypeShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumEmpLaborTypeList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpLaborTypeShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        // Get all the enumEmpLaborTypeList where tenentCode is not null
        defaultEnumEmpLaborTypeShouldBeFound("tenentCode.specified=true");

        // Get all the enumEmpLaborTypeList where tenentCode is null
        defaultEnumEmpLaborTypeShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpLaborTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpLaborType parent = EnumEmpLaborTypeResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumEmpLaborType.setParent(parent);
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);
        Long parentId = parent.getId();

        // Get all the enumEmpLaborTypeList where parent equals to parentId
        defaultEnumEmpLaborTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumEmpLaborTypeList where parent equals to parentId + 1
        defaultEnumEmpLaborTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumEmpLaborTypeShouldBeFound(String filter) throws Exception {
        restEnumEmpLaborTypeMockMvc.perform(get("/api/enum-emp-labor-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpLaborType.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumEmpLaborTypeMockMvc.perform(get("/api/enum-emp-labor-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumEmpLaborTypeShouldNotBeFound(String filter) throws Exception {
        restEnumEmpLaborTypeMockMvc.perform(get("/api/enum-emp-labor-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumEmpLaborTypeMockMvc.perform(get("/api/enum-emp-labor-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumEmpLaborType() throws Exception {
        // Get the enumEmpLaborType
        restEnumEmpLaborTypeMockMvc.perform(get("/api/enum-emp-labor-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumEmpLaborType() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        int databaseSizeBeforeUpdate = enumEmpLaborTypeRepository.findAll().size();

        // Update the enumEmpLaborType
        EnumEmpLaborType updatedEnumEmpLaborType = enumEmpLaborTypeRepository.findById(enumEmpLaborType.getId()).get();
        // Disconnect from session so that the updates on updatedEnumEmpLaborType are not directly saved in db
        em.detach(updatedEnumEmpLaborType);
        updatedEnumEmpLaborType
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumEmpLaborTypeDTO enumEmpLaborTypeDTO = enumEmpLaborTypeMapper.toDto(updatedEnumEmpLaborType);

        restEnumEmpLaborTypeMockMvc.perform(put("/api/enum-emp-labor-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpLaborTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EnumEmpLaborType in the database
        List<EnumEmpLaborType> enumEmpLaborTypeList = enumEmpLaborTypeRepository.findAll();
        assertThat(enumEmpLaborTypeList).hasSize(databaseSizeBeforeUpdate);
        EnumEmpLaborType testEnumEmpLaborType = enumEmpLaborTypeList.get(enumEmpLaborTypeList.size() - 1);
        assertThat(testEnumEmpLaborType.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumEmpLaborType.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumEmpLaborType.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumEmpLaborType() throws Exception {
        int databaseSizeBeforeUpdate = enumEmpLaborTypeRepository.findAll().size();

        // Create the EnumEmpLaborType
        EnumEmpLaborTypeDTO enumEmpLaborTypeDTO = enumEmpLaborTypeMapper.toDto(enumEmpLaborType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumEmpLaborTypeMockMvc.perform(put("/api/enum-emp-labor-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpLaborTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpLaborType in the database
        List<EnumEmpLaborType> enumEmpLaborTypeList = enumEmpLaborTypeRepository.findAll();
        assertThat(enumEmpLaborTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumEmpLaborType() throws Exception {
        // Initialize the database
        enumEmpLaborTypeRepository.saveAndFlush(enumEmpLaborType);

        int databaseSizeBeforeDelete = enumEmpLaborTypeRepository.findAll().size();

        // Delete the enumEmpLaborType
        restEnumEmpLaborTypeMockMvc.perform(delete("/api/enum-emp-labor-types/{id}", enumEmpLaborType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumEmpLaborType> enumEmpLaborTypeList = enumEmpLaborTypeRepository.findAll();
        assertThat(enumEmpLaborTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpLaborType.class);
        EnumEmpLaborType enumEmpLaborType1 = new EnumEmpLaborType();
        enumEmpLaborType1.setId(1L);
        EnumEmpLaborType enumEmpLaborType2 = new EnumEmpLaborType();
        enumEmpLaborType2.setId(enumEmpLaborType1.getId());
        assertThat(enumEmpLaborType1).isEqualTo(enumEmpLaborType2);
        enumEmpLaborType2.setId(2L);
        assertThat(enumEmpLaborType1).isNotEqualTo(enumEmpLaborType2);
        enumEmpLaborType1.setId(null);
        assertThat(enumEmpLaborType1).isNotEqualTo(enumEmpLaborType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpLaborTypeDTO.class);
        EnumEmpLaborTypeDTO enumEmpLaborTypeDTO1 = new EnumEmpLaborTypeDTO();
        enumEmpLaborTypeDTO1.setId(1L);
        EnumEmpLaborTypeDTO enumEmpLaborTypeDTO2 = new EnumEmpLaborTypeDTO();
        assertThat(enumEmpLaborTypeDTO1).isNotEqualTo(enumEmpLaborTypeDTO2);
        enumEmpLaborTypeDTO2.setId(enumEmpLaborTypeDTO1.getId());
        assertThat(enumEmpLaborTypeDTO1).isEqualTo(enumEmpLaborTypeDTO2);
        enumEmpLaborTypeDTO2.setId(2L);
        assertThat(enumEmpLaborTypeDTO1).isNotEqualTo(enumEmpLaborTypeDTO2);
        enumEmpLaborTypeDTO1.setId(null);
        assertThat(enumEmpLaborTypeDTO1).isNotEqualTo(enumEmpLaborTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumEmpLaborTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumEmpLaborTypeMapper.fromId(null)).isNull();
    }
}
