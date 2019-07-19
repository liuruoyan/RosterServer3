package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumPoliticsStatus;
import com.cc.domain.EnumPoliticsStatus;
import com.cc.repository.EnumPoliticsStatusRepository;
import com.cc.service.EnumPoliticsStatusService;
import com.cc.service.dto.EnumPoliticsStatusDTO;
import com.cc.service.mapper.EnumPoliticsStatusMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumPoliticsStatusCriteria;
import com.cc.service.EnumPoliticsStatusQueryService;

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
 * Integration tests for the {@Link EnumPoliticsStatusResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumPoliticsStatusResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumPoliticsStatusRepository enumPoliticsStatusRepository;

    @Autowired
    private EnumPoliticsStatusMapper enumPoliticsStatusMapper;

    @Autowired
    private EnumPoliticsStatusService enumPoliticsStatusService;

    @Autowired
    private EnumPoliticsStatusQueryService enumPoliticsStatusQueryService;

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

    private MockMvc restEnumPoliticsStatusMockMvc;

    private EnumPoliticsStatus enumPoliticsStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumPoliticsStatusResource enumPoliticsStatusResource = new EnumPoliticsStatusResource(enumPoliticsStatusService, enumPoliticsStatusQueryService);
        this.restEnumPoliticsStatusMockMvc = MockMvcBuilders.standaloneSetup(enumPoliticsStatusResource)
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
    public static EnumPoliticsStatus createEntity(EntityManager em) {
        EnumPoliticsStatus enumPoliticsStatus = new EnumPoliticsStatus()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumPoliticsStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumPoliticsStatus createUpdatedEntity(EntityManager em) {
        EnumPoliticsStatus enumPoliticsStatus = new EnumPoliticsStatus()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumPoliticsStatus;
    }

    @BeforeEach
    public void initTest() {
        enumPoliticsStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumPoliticsStatus() throws Exception {
        int databaseSizeBeforeCreate = enumPoliticsStatusRepository.findAll().size();

        // Create the EnumPoliticsStatus
        EnumPoliticsStatusDTO enumPoliticsStatusDTO = enumPoliticsStatusMapper.toDto(enumPoliticsStatus);
        restEnumPoliticsStatusMockMvc.perform(post("/api/enum-politics-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPoliticsStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumPoliticsStatus in the database
        List<EnumPoliticsStatus> enumPoliticsStatusList = enumPoliticsStatusRepository.findAll();
        assertThat(enumPoliticsStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EnumPoliticsStatus testEnumPoliticsStatus = enumPoliticsStatusList.get(enumPoliticsStatusList.size() - 1);
        assertThat(testEnumPoliticsStatus.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumPoliticsStatus.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumPoliticsStatus.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumPoliticsStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumPoliticsStatusRepository.findAll().size();

        // Create the EnumPoliticsStatus with an existing ID
        enumPoliticsStatus.setId(1L);
        EnumPoliticsStatusDTO enumPoliticsStatusDTO = enumPoliticsStatusMapper.toDto(enumPoliticsStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumPoliticsStatusMockMvc.perform(post("/api/enum-politics-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPoliticsStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPoliticsStatus in the database
        List<EnumPoliticsStatus> enumPoliticsStatusList = enumPoliticsStatusRepository.findAll();
        assertThat(enumPoliticsStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumPoliticsStatuses() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList
        restEnumPoliticsStatusMockMvc.perform(get("/api/enum-politics-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPoliticsStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumPoliticsStatus() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get the enumPoliticsStatus
        restEnumPoliticsStatusMockMvc.perform(get("/api/enum-politics-statuses/{id}", enumPoliticsStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumPoliticsStatus.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where valuez equals to DEFAULT_VALUEZ
        defaultEnumPoliticsStatusShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumPoliticsStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumPoliticsStatusShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumPoliticsStatusShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumPoliticsStatusList where valuez equals to UPDATED_VALUEZ
        defaultEnumPoliticsStatusShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where valuez is not null
        defaultEnumPoliticsStatusShouldBeFound("valuez.specified=true");

        // Get all the enumPoliticsStatusList where valuez is null
        defaultEnumPoliticsStatusShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where orderz equals to DEFAULT_ORDERZ
        defaultEnumPoliticsStatusShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumPoliticsStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumPoliticsStatusShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumPoliticsStatusShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumPoliticsStatusList where orderz equals to UPDATED_ORDERZ
        defaultEnumPoliticsStatusShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where orderz is not null
        defaultEnumPoliticsStatusShouldBeFound("orderz.specified=true");

        // Get all the enumPoliticsStatusList where orderz is null
        defaultEnumPoliticsStatusShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumPoliticsStatusShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumPoliticsStatusList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumPoliticsStatusShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumPoliticsStatusShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumPoliticsStatusList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumPoliticsStatusShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumPoliticsStatusShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumPoliticsStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPoliticsStatusShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumPoliticsStatusShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumPoliticsStatusList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumPoliticsStatusShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        // Get all the enumPoliticsStatusList where tenentCode is not null
        defaultEnumPoliticsStatusShouldBeFound("tenentCode.specified=true");

        // Get all the enumPoliticsStatusList where tenentCode is null
        defaultEnumPoliticsStatusShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumPoliticsStatusesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPoliticsStatus parent = EnumPoliticsStatusResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumPoliticsStatus.setParent(parent);
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);
        Long parentId = parent.getId();

        // Get all the enumPoliticsStatusList where parent equals to parentId
        defaultEnumPoliticsStatusShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumPoliticsStatusList where parent equals to parentId + 1
        defaultEnumPoliticsStatusShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumPoliticsStatusShouldBeFound(String filter) throws Exception {
        restEnumPoliticsStatusMockMvc.perform(get("/api/enum-politics-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumPoliticsStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumPoliticsStatusMockMvc.perform(get("/api/enum-politics-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumPoliticsStatusShouldNotBeFound(String filter) throws Exception {
        restEnumPoliticsStatusMockMvc.perform(get("/api/enum-politics-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumPoliticsStatusMockMvc.perform(get("/api/enum-politics-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumPoliticsStatus() throws Exception {
        // Get the enumPoliticsStatus
        restEnumPoliticsStatusMockMvc.perform(get("/api/enum-politics-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumPoliticsStatus() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        int databaseSizeBeforeUpdate = enumPoliticsStatusRepository.findAll().size();

        // Update the enumPoliticsStatus
        EnumPoliticsStatus updatedEnumPoliticsStatus = enumPoliticsStatusRepository.findById(enumPoliticsStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEnumPoliticsStatus are not directly saved in db
        em.detach(updatedEnumPoliticsStatus);
        updatedEnumPoliticsStatus
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumPoliticsStatusDTO enumPoliticsStatusDTO = enumPoliticsStatusMapper.toDto(updatedEnumPoliticsStatus);

        restEnumPoliticsStatusMockMvc.perform(put("/api/enum-politics-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPoliticsStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EnumPoliticsStatus in the database
        List<EnumPoliticsStatus> enumPoliticsStatusList = enumPoliticsStatusRepository.findAll();
        assertThat(enumPoliticsStatusList).hasSize(databaseSizeBeforeUpdate);
        EnumPoliticsStatus testEnumPoliticsStatus = enumPoliticsStatusList.get(enumPoliticsStatusList.size() - 1);
        assertThat(testEnumPoliticsStatus.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumPoliticsStatus.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumPoliticsStatus.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumPoliticsStatus() throws Exception {
        int databaseSizeBeforeUpdate = enumPoliticsStatusRepository.findAll().size();

        // Create the EnumPoliticsStatus
        EnumPoliticsStatusDTO enumPoliticsStatusDTO = enumPoliticsStatusMapper.toDto(enumPoliticsStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumPoliticsStatusMockMvc.perform(put("/api/enum-politics-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumPoliticsStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumPoliticsStatus in the database
        List<EnumPoliticsStatus> enumPoliticsStatusList = enumPoliticsStatusRepository.findAll();
        assertThat(enumPoliticsStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumPoliticsStatus() throws Exception {
        // Initialize the database
        enumPoliticsStatusRepository.saveAndFlush(enumPoliticsStatus);

        int databaseSizeBeforeDelete = enumPoliticsStatusRepository.findAll().size();

        // Delete the enumPoliticsStatus
        restEnumPoliticsStatusMockMvc.perform(delete("/api/enum-politics-statuses/{id}", enumPoliticsStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumPoliticsStatus> enumPoliticsStatusList = enumPoliticsStatusRepository.findAll();
        assertThat(enumPoliticsStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPoliticsStatus.class);
        EnumPoliticsStatus enumPoliticsStatus1 = new EnumPoliticsStatus();
        enumPoliticsStatus1.setId(1L);
        EnumPoliticsStatus enumPoliticsStatus2 = new EnumPoliticsStatus();
        enumPoliticsStatus2.setId(enumPoliticsStatus1.getId());
        assertThat(enumPoliticsStatus1).isEqualTo(enumPoliticsStatus2);
        enumPoliticsStatus2.setId(2L);
        assertThat(enumPoliticsStatus1).isNotEqualTo(enumPoliticsStatus2);
        enumPoliticsStatus1.setId(null);
        assertThat(enumPoliticsStatus1).isNotEqualTo(enumPoliticsStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumPoliticsStatusDTO.class);
        EnumPoliticsStatusDTO enumPoliticsStatusDTO1 = new EnumPoliticsStatusDTO();
        enumPoliticsStatusDTO1.setId(1L);
        EnumPoliticsStatusDTO enumPoliticsStatusDTO2 = new EnumPoliticsStatusDTO();
        assertThat(enumPoliticsStatusDTO1).isNotEqualTo(enumPoliticsStatusDTO2);
        enumPoliticsStatusDTO2.setId(enumPoliticsStatusDTO1.getId());
        assertThat(enumPoliticsStatusDTO1).isEqualTo(enumPoliticsStatusDTO2);
        enumPoliticsStatusDTO2.setId(2L);
        assertThat(enumPoliticsStatusDTO1).isNotEqualTo(enumPoliticsStatusDTO2);
        enumPoliticsStatusDTO1.setId(null);
        assertThat(enumPoliticsStatusDTO1).isNotEqualTo(enumPoliticsStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumPoliticsStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumPoliticsStatusMapper.fromId(null)).isNull();
    }
}
