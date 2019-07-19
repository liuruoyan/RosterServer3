package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumEmpStatus;
import com.cc.domain.EnumEmpStatus;
import com.cc.repository.EnumEmpStatusRepository;
import com.cc.service.EnumEmpStatusService;
import com.cc.service.dto.EnumEmpStatusDTO;
import com.cc.service.mapper.EnumEmpStatusMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumEmpStatusCriteria;
import com.cc.service.EnumEmpStatusQueryService;

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
 * Integration tests for the {@Link EnumEmpStatusResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumEmpStatusResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumEmpStatusRepository enumEmpStatusRepository;

    @Autowired
    private EnumEmpStatusMapper enumEmpStatusMapper;

    @Autowired
    private EnumEmpStatusService enumEmpStatusService;

    @Autowired
    private EnumEmpStatusQueryService enumEmpStatusQueryService;

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

    private MockMvc restEnumEmpStatusMockMvc;

    private EnumEmpStatus enumEmpStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumEmpStatusResource enumEmpStatusResource = new EnumEmpStatusResource(enumEmpStatusService, enumEmpStatusQueryService);
        this.restEnumEmpStatusMockMvc = MockMvcBuilders.standaloneSetup(enumEmpStatusResource)
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
    public static EnumEmpStatus createEntity(EntityManager em) {
        EnumEmpStatus enumEmpStatus = new EnumEmpStatus()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumEmpStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumEmpStatus createUpdatedEntity(EntityManager em) {
        EnumEmpStatus enumEmpStatus = new EnumEmpStatus()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumEmpStatus;
    }

    @BeforeEach
    public void initTest() {
        enumEmpStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumEmpStatus() throws Exception {
        int databaseSizeBeforeCreate = enumEmpStatusRepository.findAll().size();

        // Create the EnumEmpStatus
        EnumEmpStatusDTO enumEmpStatusDTO = enumEmpStatusMapper.toDto(enumEmpStatus);
        restEnumEmpStatusMockMvc.perform(post("/api/enum-emp-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumEmpStatus in the database
        List<EnumEmpStatus> enumEmpStatusList = enumEmpStatusRepository.findAll();
        assertThat(enumEmpStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EnumEmpStatus testEnumEmpStatus = enumEmpStatusList.get(enumEmpStatusList.size() - 1);
        assertThat(testEnumEmpStatus.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumEmpStatus.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumEmpStatus.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumEmpStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumEmpStatusRepository.findAll().size();

        // Create the EnumEmpStatus with an existing ID
        enumEmpStatus.setId(1L);
        EnumEmpStatusDTO enumEmpStatusDTO = enumEmpStatusMapper.toDto(enumEmpStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumEmpStatusMockMvc.perform(post("/api/enum-emp-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpStatus in the database
        List<EnumEmpStatus> enumEmpStatusList = enumEmpStatusRepository.findAll();
        assertThat(enumEmpStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumEmpStatuses() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList
        restEnumEmpStatusMockMvc.perform(get("/api/enum-emp-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumEmpStatus() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get the enumEmpStatus
        restEnumEmpStatusMockMvc.perform(get("/api/enum-emp-statuses/{id}", enumEmpStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumEmpStatus.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where valuez equals to DEFAULT_VALUEZ
        defaultEnumEmpStatusShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumEmpStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpStatusShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumEmpStatusShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumEmpStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpStatusShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where valuez is not null
        defaultEnumEmpStatusShouldBeFound("valuez.specified=true");

        // Get all the enumEmpStatusList where valuez is null
        defaultEnumEmpStatusShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where orderz equals to DEFAULT_ORDERZ
        defaultEnumEmpStatusShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumEmpStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpStatusShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumEmpStatusShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumEmpStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpStatusShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where orderz is not null
        defaultEnumEmpStatusShouldBeFound("orderz.specified=true");

        // Get all the enumEmpStatusList where orderz is null
        defaultEnumEmpStatusShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumEmpStatusShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpStatusList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumEmpStatusShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumEmpStatusShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpStatusList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumEmpStatusShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumEmpStatusesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumEmpStatusShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumEmpStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpStatusShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumEmpStatusShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumEmpStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpStatusShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        // Get all the enumEmpStatusList where tenentCode is not null
        defaultEnumEmpStatusShouldBeFound("tenentCode.specified=true");

        // Get all the enumEmpStatusList where tenentCode is null
        defaultEnumEmpStatusShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpStatusesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpStatus parent = EnumEmpStatusResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumEmpStatus.setParent(parent);
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);
        Long parentId = parent.getId();

        // Get all the enumEmpStatusList where parent equals to parentId
        defaultEnumEmpStatusShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumEmpStatusList where parent equals to parentId + 1
        defaultEnumEmpStatusShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumEmpStatusShouldBeFound(String filter) throws Exception {
        restEnumEmpStatusMockMvc.perform(get("/api/enum-emp-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumEmpStatusMockMvc.perform(get("/api/enum-emp-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumEmpStatusShouldNotBeFound(String filter) throws Exception {
        restEnumEmpStatusMockMvc.perform(get("/api/enum-emp-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumEmpStatusMockMvc.perform(get("/api/enum-emp-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumEmpStatus() throws Exception {
        // Get the enumEmpStatus
        restEnumEmpStatusMockMvc.perform(get("/api/enum-emp-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumEmpStatus() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        int databaseSizeBeforeUpdate = enumEmpStatusRepository.findAll().size();

        // Update the enumEmpStatus
        EnumEmpStatus updatedEnumEmpStatus = enumEmpStatusRepository.findById(enumEmpStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEnumEmpStatus are not directly saved in db
        em.detach(updatedEnumEmpStatus);
        updatedEnumEmpStatus
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumEmpStatusDTO enumEmpStatusDTO = enumEmpStatusMapper.toDto(updatedEnumEmpStatus);

        restEnumEmpStatusMockMvc.perform(put("/api/enum-emp-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EnumEmpStatus in the database
        List<EnumEmpStatus> enumEmpStatusList = enumEmpStatusRepository.findAll();
        assertThat(enumEmpStatusList).hasSize(databaseSizeBeforeUpdate);
        EnumEmpStatus testEnumEmpStatus = enumEmpStatusList.get(enumEmpStatusList.size() - 1);
        assertThat(testEnumEmpStatus.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumEmpStatus.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumEmpStatus.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumEmpStatus() throws Exception {
        int databaseSizeBeforeUpdate = enumEmpStatusRepository.findAll().size();

        // Create the EnumEmpStatus
        EnumEmpStatusDTO enumEmpStatusDTO = enumEmpStatusMapper.toDto(enumEmpStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumEmpStatusMockMvc.perform(put("/api/enum-emp-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpStatus in the database
        List<EnumEmpStatus> enumEmpStatusList = enumEmpStatusRepository.findAll();
        assertThat(enumEmpStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumEmpStatus() throws Exception {
        // Initialize the database
        enumEmpStatusRepository.saveAndFlush(enumEmpStatus);

        int databaseSizeBeforeDelete = enumEmpStatusRepository.findAll().size();

        // Delete the enumEmpStatus
        restEnumEmpStatusMockMvc.perform(delete("/api/enum-emp-statuses/{id}", enumEmpStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumEmpStatus> enumEmpStatusList = enumEmpStatusRepository.findAll();
        assertThat(enumEmpStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpStatus.class);
        EnumEmpStatus enumEmpStatus1 = new EnumEmpStatus();
        enumEmpStatus1.setId(1L);
        EnumEmpStatus enumEmpStatus2 = new EnumEmpStatus();
        enumEmpStatus2.setId(enumEmpStatus1.getId());
        assertThat(enumEmpStatus1).isEqualTo(enumEmpStatus2);
        enumEmpStatus2.setId(2L);
        assertThat(enumEmpStatus1).isNotEqualTo(enumEmpStatus2);
        enumEmpStatus1.setId(null);
        assertThat(enumEmpStatus1).isNotEqualTo(enumEmpStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpStatusDTO.class);
        EnumEmpStatusDTO enumEmpStatusDTO1 = new EnumEmpStatusDTO();
        enumEmpStatusDTO1.setId(1L);
        EnumEmpStatusDTO enumEmpStatusDTO2 = new EnumEmpStatusDTO();
        assertThat(enumEmpStatusDTO1).isNotEqualTo(enumEmpStatusDTO2);
        enumEmpStatusDTO2.setId(enumEmpStatusDTO1.getId());
        assertThat(enumEmpStatusDTO1).isEqualTo(enumEmpStatusDTO2);
        enumEmpStatusDTO2.setId(2L);
        assertThat(enumEmpStatusDTO1).isNotEqualTo(enumEmpStatusDTO2);
        enumEmpStatusDTO1.setId(null);
        assertThat(enumEmpStatusDTO1).isNotEqualTo(enumEmpStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumEmpStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumEmpStatusMapper.fromId(null)).isNull();
    }
}
