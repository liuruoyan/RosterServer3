package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumMaritalStatus;
import com.cc.domain.EnumMaritalStatus;
import com.cc.repository.EnumMaritalStatusRepository;
import com.cc.service.EnumMaritalStatusService;
import com.cc.service.dto.EnumMaritalStatusDTO;
import com.cc.service.mapper.EnumMaritalStatusMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumMaritalStatusCriteria;
import com.cc.service.EnumMaritalStatusQueryService;

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
 * Integration tests for the {@Link EnumMaritalStatusResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumMaritalStatusResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumMaritalStatusRepository enumMaritalStatusRepository;

    @Autowired
    private EnumMaritalStatusMapper enumMaritalStatusMapper;

    @Autowired
    private EnumMaritalStatusService enumMaritalStatusService;

    @Autowired
    private EnumMaritalStatusQueryService enumMaritalStatusQueryService;

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

    private MockMvc restEnumMaritalStatusMockMvc;

    private EnumMaritalStatus enumMaritalStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumMaritalStatusResource enumMaritalStatusResource = new EnumMaritalStatusResource(enumMaritalStatusService, enumMaritalStatusQueryService);
        this.restEnumMaritalStatusMockMvc = MockMvcBuilders.standaloneSetup(enumMaritalStatusResource)
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
    public static EnumMaritalStatus createEntity(EntityManager em) {
        EnumMaritalStatus enumMaritalStatus = new EnumMaritalStatus()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumMaritalStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumMaritalStatus createUpdatedEntity(EntityManager em) {
        EnumMaritalStatus enumMaritalStatus = new EnumMaritalStatus()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumMaritalStatus;
    }

    @BeforeEach
    public void initTest() {
        enumMaritalStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumMaritalStatus() throws Exception {
        int databaseSizeBeforeCreate = enumMaritalStatusRepository.findAll().size();

        // Create the EnumMaritalStatus
        EnumMaritalStatusDTO enumMaritalStatusDTO = enumMaritalStatusMapper.toDto(enumMaritalStatus);
        restEnumMaritalStatusMockMvc.perform(post("/api/enum-marital-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumMaritalStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumMaritalStatus in the database
        List<EnumMaritalStatus> enumMaritalStatusList = enumMaritalStatusRepository.findAll();
        assertThat(enumMaritalStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EnumMaritalStatus testEnumMaritalStatus = enumMaritalStatusList.get(enumMaritalStatusList.size() - 1);
        assertThat(testEnumMaritalStatus.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumMaritalStatus.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumMaritalStatus.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumMaritalStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumMaritalStatusRepository.findAll().size();

        // Create the EnumMaritalStatus with an existing ID
        enumMaritalStatus.setId(1L);
        EnumMaritalStatusDTO enumMaritalStatusDTO = enumMaritalStatusMapper.toDto(enumMaritalStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumMaritalStatusMockMvc.perform(post("/api/enum-marital-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumMaritalStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumMaritalStatus in the database
        List<EnumMaritalStatus> enumMaritalStatusList = enumMaritalStatusRepository.findAll();
        assertThat(enumMaritalStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumMaritalStatuses() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList
        restEnumMaritalStatusMockMvc.perform(get("/api/enum-marital-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumMaritalStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumMaritalStatus() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get the enumMaritalStatus
        restEnumMaritalStatusMockMvc.perform(get("/api/enum-marital-statuses/{id}", enumMaritalStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumMaritalStatus.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where valuez equals to DEFAULT_VALUEZ
        defaultEnumMaritalStatusShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumMaritalStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumMaritalStatusShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumMaritalStatusShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumMaritalStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumMaritalStatusShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where valuez is not null
        defaultEnumMaritalStatusShouldBeFound("valuez.specified=true");

        // Get all the enumMaritalStatusList where valuez is null
        defaultEnumMaritalStatusShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where orderz equals to DEFAULT_ORDERZ
        defaultEnumMaritalStatusShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumMaritalStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumMaritalStatusShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumMaritalStatusShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumMaritalStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumMaritalStatusShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where orderz is not null
        defaultEnumMaritalStatusShouldBeFound("orderz.specified=true");

        // Get all the enumMaritalStatusList where orderz is null
        defaultEnumMaritalStatusShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumMaritalStatusShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumMaritalStatusList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumMaritalStatusShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumMaritalStatusShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumMaritalStatusList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumMaritalStatusShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumMaritalStatusShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumMaritalStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumMaritalStatusShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumMaritalStatusShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumMaritalStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumMaritalStatusShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        // Get all the enumMaritalStatusList where tenentCode is not null
        defaultEnumMaritalStatusShouldBeFound("tenentCode.specified=true");

        // Get all the enumMaritalStatusList where tenentCode is null
        defaultEnumMaritalStatusShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumMaritalStatusesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumMaritalStatus parent = EnumMaritalStatusResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumMaritalStatus.setParent(parent);
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);
        Long parentId = parent.getId();

        // Get all the enumMaritalStatusList where parent equals to parentId
        defaultEnumMaritalStatusShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumMaritalStatusList where parent equals to parentId + 1
        defaultEnumMaritalStatusShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumMaritalStatusShouldBeFound(String filter) throws Exception {
        restEnumMaritalStatusMockMvc.perform(get("/api/enum-marital-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumMaritalStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumMaritalStatusMockMvc.perform(get("/api/enum-marital-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumMaritalStatusShouldNotBeFound(String filter) throws Exception {
        restEnumMaritalStatusMockMvc.perform(get("/api/enum-marital-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumMaritalStatusMockMvc.perform(get("/api/enum-marital-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumMaritalStatus() throws Exception {
        // Get the enumMaritalStatus
        restEnumMaritalStatusMockMvc.perform(get("/api/enum-marital-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumMaritalStatus() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        int databaseSizeBeforeUpdate = enumMaritalStatusRepository.findAll().size();

        // Update the enumMaritalStatus
        EnumMaritalStatus updatedEnumMaritalStatus = enumMaritalStatusRepository.findById(enumMaritalStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEnumMaritalStatus are not directly saved in db
        em.detach(updatedEnumMaritalStatus);
        updatedEnumMaritalStatus
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumMaritalStatusDTO enumMaritalStatusDTO = enumMaritalStatusMapper.toDto(updatedEnumMaritalStatus);

        restEnumMaritalStatusMockMvc.perform(put("/api/enum-marital-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumMaritalStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EnumMaritalStatus in the database
        List<EnumMaritalStatus> enumMaritalStatusList = enumMaritalStatusRepository.findAll();
        assertThat(enumMaritalStatusList).hasSize(databaseSizeBeforeUpdate);
        EnumMaritalStatus testEnumMaritalStatus = enumMaritalStatusList.get(enumMaritalStatusList.size() - 1);
        assertThat(testEnumMaritalStatus.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumMaritalStatus.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumMaritalStatus.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumMaritalStatus() throws Exception {
        int databaseSizeBeforeUpdate = enumMaritalStatusRepository.findAll().size();

        // Create the EnumMaritalStatus
        EnumMaritalStatusDTO enumMaritalStatusDTO = enumMaritalStatusMapper.toDto(enumMaritalStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumMaritalStatusMockMvc.perform(put("/api/enum-marital-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumMaritalStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumMaritalStatus in the database
        List<EnumMaritalStatus> enumMaritalStatusList = enumMaritalStatusRepository.findAll();
        assertThat(enumMaritalStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumMaritalStatus() throws Exception {
        // Initialize the database
        enumMaritalStatusRepository.saveAndFlush(enumMaritalStatus);

        int databaseSizeBeforeDelete = enumMaritalStatusRepository.findAll().size();

        // Delete the enumMaritalStatus
        restEnumMaritalStatusMockMvc.perform(delete("/api/enum-marital-statuses/{id}", enumMaritalStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumMaritalStatus> enumMaritalStatusList = enumMaritalStatusRepository.findAll();
        assertThat(enumMaritalStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumMaritalStatus.class);
        EnumMaritalStatus enumMaritalStatus1 = new EnumMaritalStatus();
        enumMaritalStatus1.setId(1L);
        EnumMaritalStatus enumMaritalStatus2 = new EnumMaritalStatus();
        enumMaritalStatus2.setId(enumMaritalStatus1.getId());
        assertThat(enumMaritalStatus1).isEqualTo(enumMaritalStatus2);
        enumMaritalStatus2.setId(2L);
        assertThat(enumMaritalStatus1).isNotEqualTo(enumMaritalStatus2);
        enumMaritalStatus1.setId(null);
        assertThat(enumMaritalStatus1).isNotEqualTo(enumMaritalStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumMaritalStatusDTO.class);
        EnumMaritalStatusDTO enumMaritalStatusDTO1 = new EnumMaritalStatusDTO();
        enumMaritalStatusDTO1.setId(1L);
        EnumMaritalStatusDTO enumMaritalStatusDTO2 = new EnumMaritalStatusDTO();
        assertThat(enumMaritalStatusDTO1).isNotEqualTo(enumMaritalStatusDTO2);
        enumMaritalStatusDTO2.setId(enumMaritalStatusDTO1.getId());
        assertThat(enumMaritalStatusDTO1).isEqualTo(enumMaritalStatusDTO2);
        enumMaritalStatusDTO2.setId(2L);
        assertThat(enumMaritalStatusDTO1).isNotEqualTo(enumMaritalStatusDTO2);
        enumMaritalStatusDTO1.setId(null);
        assertThat(enumMaritalStatusDTO1).isNotEqualTo(enumMaritalStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumMaritalStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumMaritalStatusMapper.fromId(null)).isNull();
    }
}
