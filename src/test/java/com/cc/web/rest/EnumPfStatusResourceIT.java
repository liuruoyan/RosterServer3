package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumPfStatus;
import com.cc.domain.EnumPfStatus;
import com.cc.repository.EnumPfStatusRepository;
import com.cc.service.EnumPfStatusService;
import com.cc.service.dto.EnumPfStatusDTO;
import com.cc.service.mapper.EnumPfStatusMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumPfStatusCriteria;
import com.cc.service.EnumPfStatusQueryService;

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
 * Integration tests for the {@Link EnumPfStatusResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumPfStatusResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumPfStatusRepository enumPfStatusRepository;

    @Autowired
    private EnumPfStatusMapper enumPfStatusMapper;

    @Autowired
    private EnumPfStatusService enumPfStatusService;

    @Autowired
    private EnumPfStatusQueryService enumPfStatusQueryService;

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

    private MockMvc restEnumPfStatusMockMvc;

    private EnumPfStatus enumPfStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumPfStatusResource enumPfStatusResource = new EnumPfStatusResource(enumPfStatusService, enumPfStatusQueryService);
        this.restEnumPfStatusMockMvc = MockMvcBuilders.standaloneSetup(enumPfStatusResource)
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
    public static EnumPfStatus createEntity(EntityManager em) {
        EnumPfStatus enumPfStatus = new EnumPfStatus()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumPfStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumPfStatus createUpdatedEntity(EntityManager em) {
        EnumPfStatus enumPfStatus = new EnumPfStatus()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumPfStatus;
    }

    @BeforeEach
    public void initTest() {
        enumPfStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumPfStatus() throws Exception {
        int databaseSizeBeforeCreate = enumPfStatusRepository.findAll().size();

        // Create the EnumPfStatus
        EnumPfStatusDTO enumPfStatusDTO = enumPfStatusMapper.toDto(enumPfStatus);
        restEnumPfStatusMockMvc.perform(post("/api/enum-pf-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumPfStatus in the database
        List<EnumPfStatus> enumPfStatusList = enumPfStatusRepository.findAll();
        assertThat(enumPfStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EnumPfStatus testEnumPfStatus = enumPfStatusList.get(enumPfStatusList.size() - 1);
        assertThat(testEnumPfStatus.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumPfStatus.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumPfStatus.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumPfStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumPfStatusRepository.findAll().size();

        // Create the EnumPfStatus with an existing ID
        enumPfStatus.setId(1L);
        EnumPfStatusDTO enumPfStatusDTO = enumPfStatusMapper.toDto(enumPfStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumPfStatusMockMvc.perform(post("/api/enum-pf-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPfStatus in the database
        List<EnumPfStatus> enumPfStatusList = enumPfStatusRepository.findAll();
        assertThat(enumPfStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumPfStatuses() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList
        restEnumPfStatusMockMvc.perform(get("/api/enum-pf-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPfStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumPfStatus() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get the enumPfStatus
        restEnumPfStatusMockMvc.perform(get("/api/enum-pf-statuses/{id}", enumPfStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumPfStatus.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where valuez equals to DEFAULT_VALUEZ
        defaultEnumPfStatusShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumPfStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumPfStatusShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumPfStatusShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumPfStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumPfStatusShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where valuez is not null
        defaultEnumPfStatusShouldBeFound("valuez.specified=true");

        // Get all the enumPfStatusList where valuez is null
        defaultEnumPfStatusShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where orderz equals to DEFAULT_ORDERZ
        defaultEnumPfStatusShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumPfStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumPfStatusShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumPfStatusShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumPfStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumPfStatusShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where orderz is not null
        defaultEnumPfStatusShouldBeFound("orderz.specified=true");

        // Get all the enumPfStatusList where orderz is null
        defaultEnumPfStatusShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumPfStatusShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumPfStatusList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumPfStatusShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumPfStatusShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumPfStatusList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumPfStatusShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumPfStatusesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumPfStatusShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumPfStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPfStatusShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumPfStatusShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumPfStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPfStatusShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        // Get all the enumPfStatusList where tenentCode is not null
        defaultEnumPfStatusShouldBeFound("tenentCode.specified=true");

        // Get all the enumPfStatusList where tenentCode is null
        defaultEnumPfStatusShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPfStatusesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPfStatus parent = EnumPfStatusResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumPfStatus.setParent(parent);
        enumPfStatusRepository.saveAndFlush(enumPfStatus);
        Long parentId = parent.getId();

        // Get all the enumPfStatusList where parent equals to parentId
        defaultEnumPfStatusShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumPfStatusList where parent equals to parentId + 1
        defaultEnumPfStatusShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumPfStatusShouldBeFound(String filter) throws Exception {
        restEnumPfStatusMockMvc.perform(get("/api/enum-pf-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPfStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumPfStatusMockMvc.perform(get("/api/enum-pf-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumPfStatusShouldNotBeFound(String filter) throws Exception {
        restEnumPfStatusMockMvc.perform(get("/api/enum-pf-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumPfStatusMockMvc.perform(get("/api/enum-pf-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumPfStatus() throws Exception {
        // Get the enumPfStatus
        restEnumPfStatusMockMvc.perform(get("/api/enum-pf-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumPfStatus() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        int databaseSizeBeforeUpdate = enumPfStatusRepository.findAll().size();

        // Update the enumPfStatus
        EnumPfStatus updatedEnumPfStatus = enumPfStatusRepository.findById(enumPfStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEnumPfStatus are not directly saved in db
        em.detach(updatedEnumPfStatus);
        updatedEnumPfStatus
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumPfStatusDTO enumPfStatusDTO = enumPfStatusMapper.toDto(updatedEnumPfStatus);

        restEnumPfStatusMockMvc.perform(put("/api/enum-pf-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EnumPfStatus in the database
        List<EnumPfStatus> enumPfStatusList = enumPfStatusRepository.findAll();
        assertThat(enumPfStatusList).hasSize(databaseSizeBeforeUpdate);
        EnumPfStatus testEnumPfStatus = enumPfStatusList.get(enumPfStatusList.size() - 1);
        assertThat(testEnumPfStatus.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumPfStatus.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumPfStatus.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumPfStatus() throws Exception {
        int databaseSizeBeforeUpdate = enumPfStatusRepository.findAll().size();

        // Create the EnumPfStatus
        EnumPfStatusDTO enumPfStatusDTO = enumPfStatusMapper.toDto(enumPfStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumPfStatusMockMvc.perform(put("/api/enum-pf-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPfStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPfStatus in the database
        List<EnumPfStatus> enumPfStatusList = enumPfStatusRepository.findAll();
        assertThat(enumPfStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumPfStatus() throws Exception {
        // Initialize the database
        enumPfStatusRepository.saveAndFlush(enumPfStatus);

        int databaseSizeBeforeDelete = enumPfStatusRepository.findAll().size();

        // Delete the enumPfStatus
        restEnumPfStatusMockMvc.perform(delete("/api/enum-pf-statuses/{id}", enumPfStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumPfStatus> enumPfStatusList = enumPfStatusRepository.findAll();
        assertThat(enumPfStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPfStatus.class);
        EnumPfStatus enumPfStatus1 = new EnumPfStatus();
        enumPfStatus1.setId(1L);
        EnumPfStatus enumPfStatus2 = new EnumPfStatus();
        enumPfStatus2.setId(enumPfStatus1.getId());
        assertThat(enumPfStatus1).isEqualTo(enumPfStatus2);
        enumPfStatus2.setId(2L);
        assertThat(enumPfStatus1).isNotEqualTo(enumPfStatus2);
        enumPfStatus1.setId(null);
        assertThat(enumPfStatus1).isNotEqualTo(enumPfStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPfStatusDTO.class);
        EnumPfStatusDTO enumPfStatusDTO1 = new EnumPfStatusDTO();
        enumPfStatusDTO1.setId(1L);
        EnumPfStatusDTO enumPfStatusDTO2 = new EnumPfStatusDTO();
        assertThat(enumPfStatusDTO1).isNotEqualTo(enumPfStatusDTO2);
        enumPfStatusDTO2.setId(enumPfStatusDTO1.getId());
        assertThat(enumPfStatusDTO1).isEqualTo(enumPfStatusDTO2);
        enumPfStatusDTO2.setId(2L);
        assertThat(enumPfStatusDTO1).isNotEqualTo(enumPfStatusDTO2);
        enumPfStatusDTO1.setId(null);
        assertThat(enumPfStatusDTO1).isNotEqualTo(enumPfStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumPfStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumPfStatusMapper.fromId(null)).isNull();
    }
}
