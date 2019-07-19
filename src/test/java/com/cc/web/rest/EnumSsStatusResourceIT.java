package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumSsStatus;
import com.cc.domain.EnumSsStatus;
import com.cc.repository.EnumSsStatusRepository;
import com.cc.service.EnumSsStatusService;
import com.cc.service.dto.EnumSsStatusDTO;
import com.cc.service.mapper.EnumSsStatusMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumSsStatusCriteria;
import com.cc.service.EnumSsStatusQueryService;

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
 * Integration tests for the {@Link EnumSsStatusResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumSsStatusResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumSsStatusRepository enumSsStatusRepository;

    @Autowired
    private EnumSsStatusMapper enumSsStatusMapper;

    @Autowired
    private EnumSsStatusService enumSsStatusService;

    @Autowired
    private EnumSsStatusQueryService enumSsStatusQueryService;

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

    private MockMvc restEnumSsStatusMockMvc;

    private EnumSsStatus enumSsStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumSsStatusResource enumSsStatusResource = new EnumSsStatusResource(enumSsStatusService, enumSsStatusQueryService);
        this.restEnumSsStatusMockMvc = MockMvcBuilders.standaloneSetup(enumSsStatusResource)
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
    public static EnumSsStatus createEntity(EntityManager em) {
        EnumSsStatus enumSsStatus = new EnumSsStatus()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumSsStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumSsStatus createUpdatedEntity(EntityManager em) {
        EnumSsStatus enumSsStatus = new EnumSsStatus()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumSsStatus;
    }

    @BeforeEach
    public void initTest() {
        enumSsStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumSsStatus() throws Exception {
        int databaseSizeBeforeCreate = enumSsStatusRepository.findAll().size();

        // Create the EnumSsStatus
        EnumSsStatusDTO enumSsStatusDTO = enumSsStatusMapper.toDto(enumSsStatus);
        restEnumSsStatusMockMvc.perform(post("/api/enum-ss-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumSsStatus in the database
        List<EnumSsStatus> enumSsStatusList = enumSsStatusRepository.findAll();
        assertThat(enumSsStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EnumSsStatus testEnumSsStatus = enumSsStatusList.get(enumSsStatusList.size() - 1);
        assertThat(testEnumSsStatus.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumSsStatus.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumSsStatus.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumSsStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumSsStatusRepository.findAll().size();

        // Create the EnumSsStatus with an existing ID
        enumSsStatus.setId(1L);
        EnumSsStatusDTO enumSsStatusDTO = enumSsStatusMapper.toDto(enumSsStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumSsStatusMockMvc.perform(post("/api/enum-ss-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumSsStatus in the database
        List<EnumSsStatus> enumSsStatusList = enumSsStatusRepository.findAll();
        assertThat(enumSsStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumSsStatuses() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList
        restEnumSsStatusMockMvc.perform(get("/api/enum-ss-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumSsStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumSsStatus() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get the enumSsStatus
        restEnumSsStatusMockMvc.perform(get("/api/enum-ss-statuses/{id}", enumSsStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumSsStatus.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where valuez equals to DEFAULT_VALUEZ
        defaultEnumSsStatusShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumSsStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumSsStatusShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumSsStatusShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumSsStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumSsStatusShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where valuez is not null
        defaultEnumSsStatusShouldBeFound("valuez.specified=true");

        // Get all the enumSsStatusList where valuez is null
        defaultEnumSsStatusShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where orderz equals to DEFAULT_ORDERZ
        defaultEnumSsStatusShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumSsStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumSsStatusShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumSsStatusShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumSsStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumSsStatusShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where orderz is not null
        defaultEnumSsStatusShouldBeFound("orderz.specified=true");

        // Get all the enumSsStatusList where orderz is null
        defaultEnumSsStatusShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumSsStatusShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumSsStatusList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumSsStatusShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumSsStatusShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumSsStatusList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumSsStatusShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumSsStatusesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumSsStatusShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumSsStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumSsStatusShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumSsStatusShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumSsStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumSsStatusShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        // Get all the enumSsStatusList where tenentCode is not null
        defaultEnumSsStatusShouldBeFound("tenentCode.specified=true");

        // Get all the enumSsStatusList where tenentCode is null
        defaultEnumSsStatusShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumSsStatusesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumSsStatus parent = EnumSsStatusResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumSsStatus.setParent(parent);
        enumSsStatusRepository.saveAndFlush(enumSsStatus);
        Long parentId = parent.getId();

        // Get all the enumSsStatusList where parent equals to parentId
        defaultEnumSsStatusShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumSsStatusList where parent equals to parentId + 1
        defaultEnumSsStatusShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumSsStatusShouldBeFound(String filter) throws Exception {
        restEnumSsStatusMockMvc.perform(get("/api/enum-ss-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumSsStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumSsStatusMockMvc.perform(get("/api/enum-ss-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumSsStatusShouldNotBeFound(String filter) throws Exception {
        restEnumSsStatusMockMvc.perform(get("/api/enum-ss-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumSsStatusMockMvc.perform(get("/api/enum-ss-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumSsStatus() throws Exception {
        // Get the enumSsStatus
        restEnumSsStatusMockMvc.perform(get("/api/enum-ss-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumSsStatus() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        int databaseSizeBeforeUpdate = enumSsStatusRepository.findAll().size();

        // Update the enumSsStatus
        EnumSsStatus updatedEnumSsStatus = enumSsStatusRepository.findById(enumSsStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEnumSsStatus are not directly saved in db
        em.detach(updatedEnumSsStatus);
        updatedEnumSsStatus
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumSsStatusDTO enumSsStatusDTO = enumSsStatusMapper.toDto(updatedEnumSsStatus);

        restEnumSsStatusMockMvc.perform(put("/api/enum-ss-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EnumSsStatus in the database
        List<EnumSsStatus> enumSsStatusList = enumSsStatusRepository.findAll();
        assertThat(enumSsStatusList).hasSize(databaseSizeBeforeUpdate);
        EnumSsStatus testEnumSsStatus = enumSsStatusList.get(enumSsStatusList.size() - 1);
        assertThat(testEnumSsStatus.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumSsStatus.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumSsStatus.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumSsStatus() throws Exception {
        int databaseSizeBeforeUpdate = enumSsStatusRepository.findAll().size();

        // Create the EnumSsStatus
        EnumSsStatusDTO enumSsStatusDTO = enumSsStatusMapper.toDto(enumSsStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumSsStatusMockMvc.perform(put("/api/enum-ss-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumSsStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumSsStatus in the database
        List<EnumSsStatus> enumSsStatusList = enumSsStatusRepository.findAll();
        assertThat(enumSsStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumSsStatus() throws Exception {
        // Initialize the database
        enumSsStatusRepository.saveAndFlush(enumSsStatus);

        int databaseSizeBeforeDelete = enumSsStatusRepository.findAll().size();

        // Delete the enumSsStatus
        restEnumSsStatusMockMvc.perform(delete("/api/enum-ss-statuses/{id}", enumSsStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumSsStatus> enumSsStatusList = enumSsStatusRepository.findAll();
        assertThat(enumSsStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumSsStatus.class);
        EnumSsStatus enumSsStatus1 = new EnumSsStatus();
        enumSsStatus1.setId(1L);
        EnumSsStatus enumSsStatus2 = new EnumSsStatus();
        enumSsStatus2.setId(enumSsStatus1.getId());
        assertThat(enumSsStatus1).isEqualTo(enumSsStatus2);
        enumSsStatus2.setId(2L);
        assertThat(enumSsStatus1).isNotEqualTo(enumSsStatus2);
        enumSsStatus1.setId(null);
        assertThat(enumSsStatus1).isNotEqualTo(enumSsStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumSsStatusDTO.class);
        EnumSsStatusDTO enumSsStatusDTO1 = new EnumSsStatusDTO();
        enumSsStatusDTO1.setId(1L);
        EnumSsStatusDTO enumSsStatusDTO2 = new EnumSsStatusDTO();
        assertThat(enumSsStatusDTO1).isNotEqualTo(enumSsStatusDTO2);
        enumSsStatusDTO2.setId(enumSsStatusDTO1.getId());
        assertThat(enumSsStatusDTO1).isEqualTo(enumSsStatusDTO2);
        enumSsStatusDTO2.setId(2L);
        assertThat(enumSsStatusDTO1).isNotEqualTo(enumSsStatusDTO2);
        enumSsStatusDTO1.setId(null);
        assertThat(enumSsStatusDTO1).isNotEqualTo(enumSsStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumSsStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumSsStatusMapper.fromId(null)).isNull();
    }
}
