package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.WorkExperience;
import com.cc.domain.Employee;
import com.cc.repository.WorkExperienceRepository;
import com.cc.service.WorkExperienceService;
import com.cc.service.dto.WorkExperienceDTO;
import com.cc.service.mapper.WorkExperienceMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.WorkExperienceCriteria;
import com.cc.service.WorkExperienceQueryService;

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
 * Integration tests for the {@Link WorkExperienceResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class WorkExperienceResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_E_NAME = "AAAAAAAAAA";
    private static final String UPDATED_E_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUM = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_JOB = "AAAAAAAAAA";
    private static final String UPDATED_JOB = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_DESC = "AAAAAAAAAA";
    private static final String UPDATED_JOB_DESC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_HIRE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIRE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LEAVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LEAVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private WorkExperienceMapper workExperienceMapper;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private WorkExperienceQueryService workExperienceQueryService;

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

    private MockMvc restWorkExperienceMockMvc;

    private WorkExperience workExperience;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WorkExperienceResource workExperienceResource = new WorkExperienceResource(workExperienceService, workExperienceQueryService);
        this.restWorkExperienceMockMvc = MockMvcBuilders.standaloneSetup(workExperienceResource)
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
    public static WorkExperience createEntity(EntityManager em) {
        WorkExperience workExperience = new WorkExperience()
            .code(DEFAULT_CODE)
            .eName(DEFAULT_E_NAME)
            .phoneNum(DEFAULT_PHONE_NUM)
            .company(DEFAULT_COMPANY)
            .job(DEFAULT_JOB)
            .jobDesc(DEFAULT_JOB_DESC)
            .hireDate(DEFAULT_HIRE_DATE)
            .leaveDate(DEFAULT_LEAVE_DATE)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return workExperience;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkExperience createUpdatedEntity(EntityManager em) {
        WorkExperience workExperience = new WorkExperience()
            .code(UPDATED_CODE)
            .eName(UPDATED_E_NAME)
            .phoneNum(UPDATED_PHONE_NUM)
            .company(UPDATED_COMPANY)
            .job(UPDATED_JOB)
            .jobDesc(UPDATED_JOB_DESC)
            .hireDate(UPDATED_HIRE_DATE)
            .leaveDate(UPDATED_LEAVE_DATE)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return workExperience;
    }

    @BeforeEach
    public void initTest() {
        workExperience = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkExperience() throws Exception {
        int databaseSizeBeforeCreate = workExperienceRepository.findAll().size();

        // Create the WorkExperience
        WorkExperienceDTO workExperienceDTO = workExperienceMapper.toDto(workExperience);
        restWorkExperienceMockMvc.perform(post("/api/work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workExperienceDTO)))
            .andExpect(status().isCreated());

        // Validate the WorkExperience in the database
        List<WorkExperience> workExperienceList = workExperienceRepository.findAll();
        assertThat(workExperienceList).hasSize(databaseSizeBeforeCreate + 1);
        WorkExperience testWorkExperience = workExperienceList.get(workExperienceList.size() - 1);
        assertThat(testWorkExperience.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testWorkExperience.geteName()).isEqualTo(DEFAULT_E_NAME);
        assertThat(testWorkExperience.getPhoneNum()).isEqualTo(DEFAULT_PHONE_NUM);
        assertThat(testWorkExperience.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testWorkExperience.getJob()).isEqualTo(DEFAULT_JOB);
        assertThat(testWorkExperience.getJobDesc()).isEqualTo(DEFAULT_JOB_DESC);
        assertThat(testWorkExperience.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(testWorkExperience.getLeaveDate()).isEqualTo(DEFAULT_LEAVE_DATE);
        assertThat(testWorkExperience.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testWorkExperience.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createWorkExperienceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workExperienceRepository.findAll().size();

        // Create the WorkExperience with an existing ID
        workExperience.setId(1L);
        WorkExperienceDTO workExperienceDTO = workExperienceMapper.toDto(workExperience);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkExperienceMockMvc.perform(post("/api/work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workExperienceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WorkExperience in the database
        List<WorkExperience> workExperienceList = workExperienceRepository.findAll();
        assertThat(workExperienceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWorkExperiences() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList
        restWorkExperienceMockMvc.perform(get("/api/work-experiences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workExperience.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].eName").value(hasItem(DEFAULT_E_NAME.toString())))
            .andExpect(jsonPath("$.[*].phoneNum").value(hasItem(DEFAULT_PHONE_NUM.toString())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB.toString())))
            .andExpect(jsonPath("$.[*].jobDesc").value(hasItem(DEFAULT_JOB_DESC.toString())))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].leaveDate").value(hasItem(DEFAULT_LEAVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getWorkExperience() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get the workExperience
        restWorkExperienceMockMvc.perform(get("/api/work-experiences/{id}", workExperience.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(workExperience.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.eName").value(DEFAULT_E_NAME.toString()))
            .andExpect(jsonPath("$.phoneNum").value(DEFAULT_PHONE_NUM.toString()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY.toString()))
            .andExpect(jsonPath("$.job").value(DEFAULT_JOB.toString()))
            .andExpect(jsonPath("$.jobDesc").value(DEFAULT_JOB_DESC.toString()))
            .andExpect(jsonPath("$.hireDate").value(DEFAULT_HIRE_DATE.toString()))
            .andExpect(jsonPath("$.leaveDate").value(DEFAULT_LEAVE_DATE.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where code equals to DEFAULT_CODE
        defaultWorkExperienceShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the workExperienceList where code equals to UPDATED_CODE
        defaultWorkExperienceShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where code in DEFAULT_CODE or UPDATED_CODE
        defaultWorkExperienceShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the workExperienceList where code equals to UPDATED_CODE
        defaultWorkExperienceShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where code is not null
        defaultWorkExperienceShouldBeFound("code.specified=true");

        // Get all the workExperienceList where code is null
        defaultWorkExperienceShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where eName equals to DEFAULT_E_NAME
        defaultWorkExperienceShouldBeFound("eName.equals=" + DEFAULT_E_NAME);

        // Get all the workExperienceList where eName equals to UPDATED_E_NAME
        defaultWorkExperienceShouldNotBeFound("eName.equals=" + UPDATED_E_NAME);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByeNameIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where eName in DEFAULT_E_NAME or UPDATED_E_NAME
        defaultWorkExperienceShouldBeFound("eName.in=" + DEFAULT_E_NAME + "," + UPDATED_E_NAME);

        // Get all the workExperienceList where eName equals to UPDATED_E_NAME
        defaultWorkExperienceShouldNotBeFound("eName.in=" + UPDATED_E_NAME);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where eName is not null
        defaultWorkExperienceShouldBeFound("eName.specified=true");

        // Get all the workExperienceList where eName is null
        defaultWorkExperienceShouldNotBeFound("eName.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByPhoneNumIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where phoneNum equals to DEFAULT_PHONE_NUM
        defaultWorkExperienceShouldBeFound("phoneNum.equals=" + DEFAULT_PHONE_NUM);

        // Get all the workExperienceList where phoneNum equals to UPDATED_PHONE_NUM
        defaultWorkExperienceShouldNotBeFound("phoneNum.equals=" + UPDATED_PHONE_NUM);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByPhoneNumIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where phoneNum in DEFAULT_PHONE_NUM or UPDATED_PHONE_NUM
        defaultWorkExperienceShouldBeFound("phoneNum.in=" + DEFAULT_PHONE_NUM + "," + UPDATED_PHONE_NUM);

        // Get all the workExperienceList where phoneNum equals to UPDATED_PHONE_NUM
        defaultWorkExperienceShouldNotBeFound("phoneNum.in=" + UPDATED_PHONE_NUM);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByPhoneNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where phoneNum is not null
        defaultWorkExperienceShouldBeFound("phoneNum.specified=true");

        // Get all the workExperienceList where phoneNum is null
        defaultWorkExperienceShouldNotBeFound("phoneNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByCompanyIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where company equals to DEFAULT_COMPANY
        defaultWorkExperienceShouldBeFound("company.equals=" + DEFAULT_COMPANY);

        // Get all the workExperienceList where company equals to UPDATED_COMPANY
        defaultWorkExperienceShouldNotBeFound("company.equals=" + UPDATED_COMPANY);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByCompanyIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where company in DEFAULT_COMPANY or UPDATED_COMPANY
        defaultWorkExperienceShouldBeFound("company.in=" + DEFAULT_COMPANY + "," + UPDATED_COMPANY);

        // Get all the workExperienceList where company equals to UPDATED_COMPANY
        defaultWorkExperienceShouldNotBeFound("company.in=" + UPDATED_COMPANY);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByCompanyIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where company is not null
        defaultWorkExperienceShouldBeFound("company.specified=true");

        // Get all the workExperienceList where company is null
        defaultWorkExperienceShouldNotBeFound("company.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByJobIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where job equals to DEFAULT_JOB
        defaultWorkExperienceShouldBeFound("job.equals=" + DEFAULT_JOB);

        // Get all the workExperienceList where job equals to UPDATED_JOB
        defaultWorkExperienceShouldNotBeFound("job.equals=" + UPDATED_JOB);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByJobIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where job in DEFAULT_JOB or UPDATED_JOB
        defaultWorkExperienceShouldBeFound("job.in=" + DEFAULT_JOB + "," + UPDATED_JOB);

        // Get all the workExperienceList where job equals to UPDATED_JOB
        defaultWorkExperienceShouldNotBeFound("job.in=" + UPDATED_JOB);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByJobIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where job is not null
        defaultWorkExperienceShouldBeFound("job.specified=true");

        // Get all the workExperienceList where job is null
        defaultWorkExperienceShouldNotBeFound("job.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByJobDescIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where jobDesc equals to DEFAULT_JOB_DESC
        defaultWorkExperienceShouldBeFound("jobDesc.equals=" + DEFAULT_JOB_DESC);

        // Get all the workExperienceList where jobDesc equals to UPDATED_JOB_DESC
        defaultWorkExperienceShouldNotBeFound("jobDesc.equals=" + UPDATED_JOB_DESC);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByJobDescIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where jobDesc in DEFAULT_JOB_DESC or UPDATED_JOB_DESC
        defaultWorkExperienceShouldBeFound("jobDesc.in=" + DEFAULT_JOB_DESC + "," + UPDATED_JOB_DESC);

        // Get all the workExperienceList where jobDesc equals to UPDATED_JOB_DESC
        defaultWorkExperienceShouldNotBeFound("jobDesc.in=" + UPDATED_JOB_DESC);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByJobDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where jobDesc is not null
        defaultWorkExperienceShouldBeFound("jobDesc.specified=true");

        // Get all the workExperienceList where jobDesc is null
        defaultWorkExperienceShouldNotBeFound("jobDesc.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByHireDateIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where hireDate equals to DEFAULT_HIRE_DATE
        defaultWorkExperienceShouldBeFound("hireDate.equals=" + DEFAULT_HIRE_DATE);

        // Get all the workExperienceList where hireDate equals to UPDATED_HIRE_DATE
        defaultWorkExperienceShouldNotBeFound("hireDate.equals=" + UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByHireDateIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where hireDate in DEFAULT_HIRE_DATE or UPDATED_HIRE_DATE
        defaultWorkExperienceShouldBeFound("hireDate.in=" + DEFAULT_HIRE_DATE + "," + UPDATED_HIRE_DATE);

        // Get all the workExperienceList where hireDate equals to UPDATED_HIRE_DATE
        defaultWorkExperienceShouldNotBeFound("hireDate.in=" + UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByHireDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where hireDate is not null
        defaultWorkExperienceShouldBeFound("hireDate.specified=true");

        // Get all the workExperienceList where hireDate is null
        defaultWorkExperienceShouldNotBeFound("hireDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByHireDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where hireDate greater than or equals to DEFAULT_HIRE_DATE
        defaultWorkExperienceShouldBeFound("hireDate.greaterOrEqualThan=" + DEFAULT_HIRE_DATE);

        // Get all the workExperienceList where hireDate greater than or equals to UPDATED_HIRE_DATE
        defaultWorkExperienceShouldNotBeFound("hireDate.greaterOrEqualThan=" + UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByHireDateIsLessThanSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where hireDate less than or equals to DEFAULT_HIRE_DATE
        defaultWorkExperienceShouldNotBeFound("hireDate.lessThan=" + DEFAULT_HIRE_DATE);

        // Get all the workExperienceList where hireDate less than or equals to UPDATED_HIRE_DATE
        defaultWorkExperienceShouldBeFound("hireDate.lessThan=" + UPDATED_HIRE_DATE);
    }


    @Test
    @Transactional
    public void getAllWorkExperiencesByLeaveDateIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where leaveDate equals to DEFAULT_LEAVE_DATE
        defaultWorkExperienceShouldBeFound("leaveDate.equals=" + DEFAULT_LEAVE_DATE);

        // Get all the workExperienceList where leaveDate equals to UPDATED_LEAVE_DATE
        defaultWorkExperienceShouldNotBeFound("leaveDate.equals=" + UPDATED_LEAVE_DATE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByLeaveDateIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where leaveDate in DEFAULT_LEAVE_DATE or UPDATED_LEAVE_DATE
        defaultWorkExperienceShouldBeFound("leaveDate.in=" + DEFAULT_LEAVE_DATE + "," + UPDATED_LEAVE_DATE);

        // Get all the workExperienceList where leaveDate equals to UPDATED_LEAVE_DATE
        defaultWorkExperienceShouldNotBeFound("leaveDate.in=" + UPDATED_LEAVE_DATE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByLeaveDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where leaveDate is not null
        defaultWorkExperienceShouldBeFound("leaveDate.specified=true");

        // Get all the workExperienceList where leaveDate is null
        defaultWorkExperienceShouldNotBeFound("leaveDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByLeaveDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where leaveDate greater than or equals to DEFAULT_LEAVE_DATE
        defaultWorkExperienceShouldBeFound("leaveDate.greaterOrEqualThan=" + DEFAULT_LEAVE_DATE);

        // Get all the workExperienceList where leaveDate greater than or equals to UPDATED_LEAVE_DATE
        defaultWorkExperienceShouldNotBeFound("leaveDate.greaterOrEqualThan=" + UPDATED_LEAVE_DATE);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByLeaveDateIsLessThanSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where leaveDate less than or equals to DEFAULT_LEAVE_DATE
        defaultWorkExperienceShouldNotBeFound("leaveDate.lessThan=" + DEFAULT_LEAVE_DATE);

        // Get all the workExperienceList where leaveDate less than or equals to UPDATED_LEAVE_DATE
        defaultWorkExperienceShouldBeFound("leaveDate.lessThan=" + UPDATED_LEAVE_DATE);
    }


    @Test
    @Transactional
    public void getAllWorkExperiencesByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultWorkExperienceShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the workExperienceList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultWorkExperienceShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultWorkExperienceShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the workExperienceList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultWorkExperienceShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where isSelfVerify is not null
        defaultWorkExperienceShouldBeFound("isSelfVerify.specified=true");

        // Get all the workExperienceList where isSelfVerify is null
        defaultWorkExperienceShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultWorkExperienceShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the workExperienceList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultWorkExperienceShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultWorkExperienceShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the workExperienceList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultWorkExperienceShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperienceList where isHrVerify is not null
        defaultWorkExperienceShouldBeFound("isHrVerify.specified=true");

        // Get all the workExperienceList where isHrVerify is null
        defaultWorkExperienceShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllWorkExperiencesByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        workExperience.setEmp(emp);
        workExperienceRepository.saveAndFlush(workExperience);
        Long empId = emp.getId();

        // Get all the workExperienceList where emp equals to empId
        defaultWorkExperienceShouldBeFound("empId.equals=" + empId);

        // Get all the workExperienceList where emp equals to empId + 1
        defaultWorkExperienceShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultWorkExperienceShouldBeFound(String filter) throws Exception {
        restWorkExperienceMockMvc.perform(get("/api/work-experiences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workExperience.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].eName").value(hasItem(DEFAULT_E_NAME)))
            .andExpect(jsonPath("$.[*].phoneNum").value(hasItem(DEFAULT_PHONE_NUM)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB)))
            .andExpect(jsonPath("$.[*].jobDesc").value(hasItem(DEFAULT_JOB_DESC)))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].leaveDate").value(hasItem(DEFAULT_LEAVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restWorkExperienceMockMvc.perform(get("/api/work-experiences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultWorkExperienceShouldNotBeFound(String filter) throws Exception {
        restWorkExperienceMockMvc.perform(get("/api/work-experiences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restWorkExperienceMockMvc.perform(get("/api/work-experiences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingWorkExperience() throws Exception {
        // Get the workExperience
        restWorkExperienceMockMvc.perform(get("/api/work-experiences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkExperience() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        int databaseSizeBeforeUpdate = workExperienceRepository.findAll().size();

        // Update the workExperience
        WorkExperience updatedWorkExperience = workExperienceRepository.findById(workExperience.getId()).get();
        // Disconnect from session so that the updates on updatedWorkExperience are not directly saved in db
        em.detach(updatedWorkExperience);
        updatedWorkExperience
            .code(UPDATED_CODE)
            .eName(UPDATED_E_NAME)
            .phoneNum(UPDATED_PHONE_NUM)
            .company(UPDATED_COMPANY)
            .job(UPDATED_JOB)
            .jobDesc(UPDATED_JOB_DESC)
            .hireDate(UPDATED_HIRE_DATE)
            .leaveDate(UPDATED_LEAVE_DATE)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        WorkExperienceDTO workExperienceDTO = workExperienceMapper.toDto(updatedWorkExperience);

        restWorkExperienceMockMvc.perform(put("/api/work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workExperienceDTO)))
            .andExpect(status().isOk());

        // Validate the WorkExperience in the database
        List<WorkExperience> workExperienceList = workExperienceRepository.findAll();
        assertThat(workExperienceList).hasSize(databaseSizeBeforeUpdate);
        WorkExperience testWorkExperience = workExperienceList.get(workExperienceList.size() - 1);
        assertThat(testWorkExperience.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testWorkExperience.geteName()).isEqualTo(UPDATED_E_NAME);
        assertThat(testWorkExperience.getPhoneNum()).isEqualTo(UPDATED_PHONE_NUM);
        assertThat(testWorkExperience.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testWorkExperience.getJob()).isEqualTo(UPDATED_JOB);
        assertThat(testWorkExperience.getJobDesc()).isEqualTo(UPDATED_JOB_DESC);
        assertThat(testWorkExperience.getHireDate()).isEqualTo(UPDATED_HIRE_DATE);
        assertThat(testWorkExperience.getLeaveDate()).isEqualTo(UPDATED_LEAVE_DATE);
        assertThat(testWorkExperience.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testWorkExperience.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkExperience() throws Exception {
        int databaseSizeBeforeUpdate = workExperienceRepository.findAll().size();

        // Create the WorkExperience
        WorkExperienceDTO workExperienceDTO = workExperienceMapper.toDto(workExperience);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkExperienceMockMvc.perform(put("/api/work-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workExperienceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WorkExperience in the database
        List<WorkExperience> workExperienceList = workExperienceRepository.findAll();
        assertThat(workExperienceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWorkExperience() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        int databaseSizeBeforeDelete = workExperienceRepository.findAll().size();

        // Delete the workExperience
        restWorkExperienceMockMvc.perform(delete("/api/work-experiences/{id}", workExperience.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkExperience> workExperienceList = workExperienceRepository.findAll();
        assertThat(workExperienceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkExperience.class);
        WorkExperience workExperience1 = new WorkExperience();
        workExperience1.setId(1L);
        WorkExperience workExperience2 = new WorkExperience();
        workExperience2.setId(workExperience1.getId());
        assertThat(workExperience1).isEqualTo(workExperience2);
        workExperience2.setId(2L);
        assertThat(workExperience1).isNotEqualTo(workExperience2);
        workExperience1.setId(null);
        assertThat(workExperience1).isNotEqualTo(workExperience2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkExperienceDTO.class);
        WorkExperienceDTO workExperienceDTO1 = new WorkExperienceDTO();
        workExperienceDTO1.setId(1L);
        WorkExperienceDTO workExperienceDTO2 = new WorkExperienceDTO();
        assertThat(workExperienceDTO1).isNotEqualTo(workExperienceDTO2);
        workExperienceDTO2.setId(workExperienceDTO1.getId());
        assertThat(workExperienceDTO1).isEqualTo(workExperienceDTO2);
        workExperienceDTO2.setId(2L);
        assertThat(workExperienceDTO1).isNotEqualTo(workExperienceDTO2);
        workExperienceDTO1.setId(null);
        assertThat(workExperienceDTO1).isNotEqualTo(workExperienceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(workExperienceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(workExperienceMapper.fromId(null)).isNull();
    }
}
