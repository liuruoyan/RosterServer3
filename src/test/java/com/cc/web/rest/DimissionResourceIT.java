package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.Dimission;
import com.cc.domain.EnumDimissionType;
import com.cc.domain.Employee;
import com.cc.repository.DimissionRepository;
import com.cc.service.DimissionService;
import com.cc.service.dto.DimissionDTO;
import com.cc.service.mapper.DimissionMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.DimissionCriteria;
import com.cc.service.DimissionQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.cc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link DimissionResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class DimissionResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private DimissionRepository dimissionRepository;

    @Autowired
    private DimissionMapper dimissionMapper;

    @Autowired
    private DimissionService dimissionService;

    @Autowired
    private DimissionQueryService dimissionQueryService;

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

    private MockMvc restDimissionMockMvc;

    private Dimission dimission;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DimissionResource dimissionResource = new DimissionResource(dimissionService, dimissionQueryService);
        this.restDimissionMockMvc = MockMvcBuilders.standaloneSetup(dimissionResource)
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
    public static Dimission createEntity(EntityManager em) {
        Dimission dimission = new Dimission()
            .code(DEFAULT_CODE)
            .lastDate(DEFAULT_LAST_DATE)
            .reason(DEFAULT_REASON)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return dimission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dimission createUpdatedEntity(EntityManager em) {
        Dimission dimission = new Dimission()
            .code(UPDATED_CODE)
            .lastDate(UPDATED_LAST_DATE)
            .reason(UPDATED_REASON)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return dimission;
    }

    @BeforeEach
    public void initTest() {
        dimission = createEntity(em);
    }

    @Test
    @Transactional
    public void createDimission() throws Exception {
        int databaseSizeBeforeCreate = dimissionRepository.findAll().size();

        // Create the Dimission
        DimissionDTO dimissionDTO = dimissionMapper.toDto(dimission);
        restDimissionMockMvc.perform(post("/api/dimissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dimissionDTO)))
            .andExpect(status().isCreated());

        // Validate the Dimission in the database
        List<Dimission> dimissionList = dimissionRepository.findAll();
        assertThat(dimissionList).hasSize(databaseSizeBeforeCreate + 1);
        Dimission testDimission = dimissionList.get(dimissionList.size() - 1);
        assertThat(testDimission.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDimission.getLastDate()).isEqualTo(DEFAULT_LAST_DATE);
        assertThat(testDimission.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testDimission.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testDimission.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createDimissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dimissionRepository.findAll().size();

        // Create the Dimission with an existing ID
        dimission.setId(1L);
        DimissionDTO dimissionDTO = dimissionMapper.toDto(dimission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDimissionMockMvc.perform(post("/api/dimissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dimissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dimission in the database
        List<Dimission> dimissionList = dimissionRepository.findAll();
        assertThat(dimissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDimissions() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList
        restDimissionMockMvc.perform(get("/api/dimissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dimission.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].lastDate").value(hasItem(DEFAULT_LAST_DATE.toString())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDimission() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get the dimission
        restDimissionMockMvc.perform(get("/api/dimissions/{id}", dimission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dimission.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.lastDate").value(DEFAULT_LAST_DATE.toString()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllDimissionsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where code equals to DEFAULT_CODE
        defaultDimissionShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the dimissionList where code equals to UPDATED_CODE
        defaultDimissionShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllDimissionsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where code in DEFAULT_CODE or UPDATED_CODE
        defaultDimissionShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the dimissionList where code equals to UPDATED_CODE
        defaultDimissionShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllDimissionsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where code is not null
        defaultDimissionShouldBeFound("code.specified=true");

        // Get all the dimissionList where code is null
        defaultDimissionShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllDimissionsByLastDateIsEqualToSomething() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where lastDate equals to DEFAULT_LAST_DATE
        defaultDimissionShouldBeFound("lastDate.equals=" + DEFAULT_LAST_DATE);

        // Get all the dimissionList where lastDate equals to UPDATED_LAST_DATE
        defaultDimissionShouldNotBeFound("lastDate.equals=" + UPDATED_LAST_DATE);
    }

    @Test
    @Transactional
    public void getAllDimissionsByLastDateIsInShouldWork() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where lastDate in DEFAULT_LAST_DATE or UPDATED_LAST_DATE
        defaultDimissionShouldBeFound("lastDate.in=" + DEFAULT_LAST_DATE + "," + UPDATED_LAST_DATE);

        // Get all the dimissionList where lastDate equals to UPDATED_LAST_DATE
        defaultDimissionShouldNotBeFound("lastDate.in=" + UPDATED_LAST_DATE);
    }

    @Test
    @Transactional
    public void getAllDimissionsByLastDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where lastDate is not null
        defaultDimissionShouldBeFound("lastDate.specified=true");

        // Get all the dimissionList where lastDate is null
        defaultDimissionShouldNotBeFound("lastDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllDimissionsByLastDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where lastDate greater than or equals to DEFAULT_LAST_DATE
        defaultDimissionShouldBeFound("lastDate.greaterOrEqualThan=" + DEFAULT_LAST_DATE);

        // Get all the dimissionList where lastDate greater than or equals to UPDATED_LAST_DATE
        defaultDimissionShouldNotBeFound("lastDate.greaterOrEqualThan=" + UPDATED_LAST_DATE);
    }

    @Test
    @Transactional
    public void getAllDimissionsByLastDateIsLessThanSomething() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where lastDate less than or equals to DEFAULT_LAST_DATE
        defaultDimissionShouldNotBeFound("lastDate.lessThan=" + DEFAULT_LAST_DATE);

        // Get all the dimissionList where lastDate less than or equals to UPDATED_LAST_DATE
        defaultDimissionShouldBeFound("lastDate.lessThan=" + UPDATED_LAST_DATE);
    }


    @Test
    @Transactional
    public void getAllDimissionsByReasonIsEqualToSomething() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where reason equals to DEFAULT_REASON
        defaultDimissionShouldBeFound("reason.equals=" + DEFAULT_REASON);

        // Get all the dimissionList where reason equals to UPDATED_REASON
        defaultDimissionShouldNotBeFound("reason.equals=" + UPDATED_REASON);
    }

    @Test
    @Transactional
    public void getAllDimissionsByReasonIsInShouldWork() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where reason in DEFAULT_REASON or UPDATED_REASON
        defaultDimissionShouldBeFound("reason.in=" + DEFAULT_REASON + "," + UPDATED_REASON);

        // Get all the dimissionList where reason equals to UPDATED_REASON
        defaultDimissionShouldNotBeFound("reason.in=" + UPDATED_REASON);
    }

    @Test
    @Transactional
    public void getAllDimissionsByReasonIsNullOrNotNull() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where reason is not null
        defaultDimissionShouldBeFound("reason.specified=true");

        // Get all the dimissionList where reason is null
        defaultDimissionShouldNotBeFound("reason.specified=false");
    }

    @Test
    @Transactional
    public void getAllDimissionsByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultDimissionShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the dimissionList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultDimissionShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDimissionsByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultDimissionShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the dimissionList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultDimissionShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDimissionsByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where isSelfVerify is not null
        defaultDimissionShouldBeFound("isSelfVerify.specified=true");

        // Get all the dimissionList where isSelfVerify is null
        defaultDimissionShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllDimissionsByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultDimissionShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the dimissionList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultDimissionShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDimissionsByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultDimissionShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the dimissionList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultDimissionShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDimissionsByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        // Get all the dimissionList where isHrVerify is not null
        defaultDimissionShouldBeFound("isHrVerify.specified=true");

        // Get all the dimissionList where isHrVerify is null
        defaultDimissionShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllDimissionsByDimissionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumDimissionType dimissionType = EnumDimissionTypeResourceIT.createEntity(em);
        em.persist(dimissionType);
        em.flush();
        dimission.setDimissionType(dimissionType);
        dimissionRepository.saveAndFlush(dimission);
        Long dimissionTypeId = dimissionType.getId();

        // Get all the dimissionList where dimissionType equals to dimissionTypeId
        defaultDimissionShouldBeFound("dimissionTypeId.equals=" + dimissionTypeId);

        // Get all the dimissionList where dimissionType equals to dimissionTypeId + 1
        defaultDimissionShouldNotBeFound("dimissionTypeId.equals=" + (dimissionTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllDimissionsByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        dimission.setEmp(emp);
        dimissionRepository.saveAndFlush(dimission);
        Long empId = emp.getId();

        // Get all the dimissionList where emp equals to empId
        defaultDimissionShouldBeFound("empId.equals=" + empId);

        // Get all the dimissionList where emp equals to empId + 1
        defaultDimissionShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDimissionShouldBeFound(String filter) throws Exception {
        restDimissionMockMvc.perform(get("/api/dimissions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dimission.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].lastDate").value(hasItem(DEFAULT_LAST_DATE.toString())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restDimissionMockMvc.perform(get("/api/dimissions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDimissionShouldNotBeFound(String filter) throws Exception {
        restDimissionMockMvc.perform(get("/api/dimissions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDimissionMockMvc.perform(get("/api/dimissions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDimission() throws Exception {
        // Get the dimission
        restDimissionMockMvc.perform(get("/api/dimissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDimission() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        int databaseSizeBeforeUpdate = dimissionRepository.findAll().size();

        // Update the dimission
        Dimission updatedDimission = dimissionRepository.findById(dimission.getId()).get();
        // Disconnect from session so that the updates on updatedDimission are not directly saved in db
        em.detach(updatedDimission);
        updatedDimission
            .code(UPDATED_CODE)
            .lastDate(UPDATED_LAST_DATE)
            .reason(UPDATED_REASON)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        DimissionDTO dimissionDTO = dimissionMapper.toDto(updatedDimission);

        restDimissionMockMvc.perform(put("/api/dimissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dimissionDTO)))
            .andExpect(status().isOk());

        // Validate the Dimission in the database
        List<Dimission> dimissionList = dimissionRepository.findAll();
        assertThat(dimissionList).hasSize(databaseSizeBeforeUpdate);
        Dimission testDimission = dimissionList.get(dimissionList.size() - 1);
        assertThat(testDimission.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDimission.getLastDate()).isEqualTo(UPDATED_LAST_DATE);
        assertThat(testDimission.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testDimission.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testDimission.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingDimission() throws Exception {
        int databaseSizeBeforeUpdate = dimissionRepository.findAll().size();

        // Create the Dimission
        DimissionDTO dimissionDTO = dimissionMapper.toDto(dimission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDimissionMockMvc.perform(put("/api/dimissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dimissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dimission in the database
        List<Dimission> dimissionList = dimissionRepository.findAll();
        assertThat(dimissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDimission() throws Exception {
        // Initialize the database
        dimissionRepository.saveAndFlush(dimission);

        int databaseSizeBeforeDelete = dimissionRepository.findAll().size();

        // Delete the dimission
        restDimissionMockMvc.perform(delete("/api/dimissions/{id}", dimission.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dimission> dimissionList = dimissionRepository.findAll();
        assertThat(dimissionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dimission.class);
        Dimission dimission1 = new Dimission();
        dimission1.setId(1L);
        Dimission dimission2 = new Dimission();
        dimission2.setId(dimission1.getId());
        assertThat(dimission1).isEqualTo(dimission2);
        dimission2.setId(2L);
        assertThat(dimission1).isNotEqualTo(dimission2);
        dimission1.setId(null);
        assertThat(dimission1).isNotEqualTo(dimission2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DimissionDTO.class);
        DimissionDTO dimissionDTO1 = new DimissionDTO();
        dimissionDTO1.setId(1L);
        DimissionDTO dimissionDTO2 = new DimissionDTO();
        assertThat(dimissionDTO1).isNotEqualTo(dimissionDTO2);
        dimissionDTO2.setId(dimissionDTO1.getId());
        assertThat(dimissionDTO1).isEqualTo(dimissionDTO2);
        dimissionDTO2.setId(2L);
        assertThat(dimissionDTO1).isNotEqualTo(dimissionDTO2);
        dimissionDTO1.setId(null);
        assertThat(dimissionDTO1).isNotEqualTo(dimissionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dimissionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dimissionMapper.fromId(null)).isNull();
    }
}
