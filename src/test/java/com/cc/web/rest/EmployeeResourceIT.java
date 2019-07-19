package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.Employee;
import com.cc.domain.Contract;
import com.cc.domain.Personal;
import com.cc.domain.SocialSecurityBenefits;
import com.cc.domain.PayCard;
import com.cc.domain.Dimission;
import com.cc.domain.WorkExperience;
import com.cc.domain.EducationExperience;
import com.cc.domain.DirectSupervisor;
import com.cc.domain.AdditionalPost;
import com.cc.domain.EnumEmpStatus;
import com.cc.domain.EnumIdType;
import com.cc.domain.EnumContractType;
import com.cc.domain.EnumEmpType;
import com.cc.domain.EnumGender;
import com.cc.repository.EmployeeRepository;
import com.cc.service.EmployeeService;
import com.cc.service.dto.EmployeeDTO;
import com.cc.service.mapper.EmployeeMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EmployeeCriteria;
import com.cc.service.EmployeeQueryService;

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

import com.cc.domain.enumeration.BirthType;
/**
 * Integration tests for the {@Link EmployeeResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EmployeeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_HIRE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIRE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_JOB_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_JOB = "AAAAAAAAAA";
    private static final String UPDATED_JOB = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMP_NO = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_SENIORITY = 1;
    private static final Integer UPDATED_SENIORITY = 2;

    private static final String DEFAULT_CONTRACTOR = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTOR = "BBBBBBBBBB";

    private static final BirthType DEFAULT_BIRTH_TYPE = BirthType.LUNAR;
    private static final BirthType UPDATED_BIRTH_TYPE = BirthType.CALENDAR;

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDAY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_WORK_LOC = "AAAAAAAAAA";
    private static final String UPDATED_WORK_LOC = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeQueryService employeeQueryService;

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

    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeResource employeeResource = new EmployeeResource(employeeService, employeeQueryService);
        this.restEmployeeMockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
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
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .idNumber(DEFAULT_ID_NUMBER)
            .phone(DEFAULT_PHONE)
            .hireDate(DEFAULT_HIRE_DATE)
            .jobGrade(DEFAULT_JOB_GRADE)
            .position(DEFAULT_POSITION)
            .job(DEFAULT_JOB)
            .deptName(DEFAULT_DEPT_NAME)
            .empNo(DEFAULT_EMP_NO)
            .seniority(DEFAULT_SENIORITY)
            .contractor(DEFAULT_CONTRACTOR)
            .birthType(DEFAULT_BIRTH_TYPE)
            .birthday(DEFAULT_BIRTHDAY)
            .workLoc(DEFAULT_WORK_LOC)
            .contactAddr(DEFAULT_CONTACT_ADDR)
            .nationality(DEFAULT_NATIONALITY)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .others(DEFAULT_OTHERS)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return employee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createUpdatedEntity(EntityManager em) {
        Employee employee = new Employee()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .idNumber(UPDATED_ID_NUMBER)
            .phone(UPDATED_PHONE)
            .hireDate(UPDATED_HIRE_DATE)
            .jobGrade(UPDATED_JOB_GRADE)
            .position(UPDATED_POSITION)
            .job(UPDATED_JOB)
            .deptName(UPDATED_DEPT_NAME)
            .empNo(UPDATED_EMP_NO)
            .seniority(UPDATED_SENIORITY)
            .contractor(UPDATED_CONTRACTOR)
            .birthType(UPDATED_BIRTH_TYPE)
            .birthday(UPDATED_BIRTHDAY)
            .workLoc(UPDATED_WORK_LOC)
            .contactAddr(UPDATED_CONTACT_ADDR)
            .nationality(UPDATED_NATIONALITY)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .others(UPDATED_OTHERS)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return employee;
    }

    @BeforeEach
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testEmployee.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEmployee.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testEmployee.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testEmployee.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(testEmployee.getJobGrade()).isEqualTo(DEFAULT_JOB_GRADE);
        assertThat(testEmployee.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testEmployee.getJob()).isEqualTo(DEFAULT_JOB);
        assertThat(testEmployee.getDeptName()).isEqualTo(DEFAULT_DEPT_NAME);
        assertThat(testEmployee.getEmpNo()).isEqualTo(DEFAULT_EMP_NO);
        assertThat(testEmployee.getSeniority()).isEqualTo(DEFAULT_SENIORITY);
        assertThat(testEmployee.getContractor()).isEqualTo(DEFAULT_CONTRACTOR);
        assertThat(testEmployee.getBirthType()).isEqualTo(DEFAULT_BIRTH_TYPE);
        assertThat(testEmployee.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testEmployee.getWorkLoc()).isEqualTo(DEFAULT_WORK_LOC);
        assertThat(testEmployee.getContactAddr()).isEqualTo(DEFAULT_CONTACT_ADDR);
        assertThat(testEmployee.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testEmployee.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testEmployee.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testEmployee.getOthers()).isEqualTo(DEFAULT_OTHERS);
        assertThat(testEmployee.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testEmployee.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId(1L);
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setName(null);

        // Create the Employee, which fails.
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setIdNumber(null);

        // Create the Employee, which fails.
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setPhone(null);

        // Create the Employee, which fails.
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHireDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setHireDate(null);

        // Create the Employee, which fails.
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].jobGrade").value(hasItem(DEFAULT_JOB_GRADE.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB.toString())))
            .andExpect(jsonPath("$.[*].deptName").value(hasItem(DEFAULT_DEPT_NAME.toString())))
            .andExpect(jsonPath("$.[*].empNo").value(hasItem(DEFAULT_EMP_NO.toString())))
            .andExpect(jsonPath("$.[*].seniority").value(hasItem(DEFAULT_SENIORITY)))
            .andExpect(jsonPath("$.[*].contractor").value(hasItem(DEFAULT_CONTRACTOR.toString())))
            .andExpect(jsonPath("$.[*].birthType").value(hasItem(DEFAULT_BIRTH_TYPE.toString())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].workLoc").value(hasItem(DEFAULT_WORK_LOC.toString())))
            .andExpect(jsonPath("$.[*].contactAddr").value(hasItem(DEFAULT_CONTACT_ADDR.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].others").value(hasItem(DEFAULT_OTHERS.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.hireDate").value(DEFAULT_HIRE_DATE.toString()))
            .andExpect(jsonPath("$.jobGrade").value(DEFAULT_JOB_GRADE.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.job").value(DEFAULT_JOB.toString()))
            .andExpect(jsonPath("$.deptName").value(DEFAULT_DEPT_NAME.toString()))
            .andExpect(jsonPath("$.empNo").value(DEFAULT_EMP_NO.toString()))
            .andExpect(jsonPath("$.seniority").value(DEFAULT_SENIORITY))
            .andExpect(jsonPath("$.contractor").value(DEFAULT_CONTRACTOR.toString()))
            .andExpect(jsonPath("$.birthType").value(DEFAULT_BIRTH_TYPE.toString()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
            .andExpect(jsonPath("$.workLoc").value(DEFAULT_WORK_LOC.toString()))
            .andExpect(jsonPath("$.contactAddr").value(DEFAULT_CONTACT_ADDR.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.others").value(DEFAULT_OTHERS.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllEmployeesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where code equals to DEFAULT_CODE
        defaultEmployeeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the employeeList where code equals to UPDATED_CODE
        defaultEmployeeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultEmployeeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the employeeList where code equals to UPDATED_CODE
        defaultEmployeeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where code is not null
        defaultEmployeeShouldBeFound("code.specified=true");

        // Get all the employeeList where code is null
        defaultEmployeeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where name equals to DEFAULT_NAME
        defaultEmployeeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the employeeList where name equals to UPDATED_NAME
        defaultEmployeeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultEmployeeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the employeeList where name equals to UPDATED_NAME
        defaultEmployeeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where name is not null
        defaultEmployeeShouldBeFound("name.specified=true");

        // Get all the employeeList where name is null
        defaultEmployeeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByIdNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where idNumber equals to DEFAULT_ID_NUMBER
        defaultEmployeeShouldBeFound("idNumber.equals=" + DEFAULT_ID_NUMBER);

        // Get all the employeeList where idNumber equals to UPDATED_ID_NUMBER
        defaultEmployeeShouldNotBeFound("idNumber.equals=" + UPDATED_ID_NUMBER);
    }

    @Test
    @Transactional
    public void getAllEmployeesByIdNumberIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where idNumber in DEFAULT_ID_NUMBER or UPDATED_ID_NUMBER
        defaultEmployeeShouldBeFound("idNumber.in=" + DEFAULT_ID_NUMBER + "," + UPDATED_ID_NUMBER);

        // Get all the employeeList where idNumber equals to UPDATED_ID_NUMBER
        defaultEmployeeShouldNotBeFound("idNumber.in=" + UPDATED_ID_NUMBER);
    }

    @Test
    @Transactional
    public void getAllEmployeesByIdNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where idNumber is not null
        defaultEmployeeShouldBeFound("idNumber.specified=true");

        // Get all the employeeList where idNumber is null
        defaultEmployeeShouldNotBeFound("idNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where phone equals to DEFAULT_PHONE
        defaultEmployeeShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the employeeList where phone equals to UPDATED_PHONE
        defaultEmployeeShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultEmployeeShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the employeeList where phone equals to UPDATED_PHONE
        defaultEmployeeShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where phone is not null
        defaultEmployeeShouldBeFound("phone.specified=true");

        // Get all the employeeList where phone is null
        defaultEmployeeShouldNotBeFound("phone.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByHireDateIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where hireDate equals to DEFAULT_HIRE_DATE
        defaultEmployeeShouldBeFound("hireDate.equals=" + DEFAULT_HIRE_DATE);

        // Get all the employeeList where hireDate equals to UPDATED_HIRE_DATE
        defaultEmployeeShouldNotBeFound("hireDate.equals=" + UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByHireDateIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where hireDate in DEFAULT_HIRE_DATE or UPDATED_HIRE_DATE
        defaultEmployeeShouldBeFound("hireDate.in=" + DEFAULT_HIRE_DATE + "," + UPDATED_HIRE_DATE);

        // Get all the employeeList where hireDate equals to UPDATED_HIRE_DATE
        defaultEmployeeShouldNotBeFound("hireDate.in=" + UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByHireDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where hireDate is not null
        defaultEmployeeShouldBeFound("hireDate.specified=true");

        // Get all the employeeList where hireDate is null
        defaultEmployeeShouldNotBeFound("hireDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByHireDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where hireDate greater than or equals to DEFAULT_HIRE_DATE
        defaultEmployeeShouldBeFound("hireDate.greaterOrEqualThan=" + DEFAULT_HIRE_DATE);

        // Get all the employeeList where hireDate greater than or equals to UPDATED_HIRE_DATE
        defaultEmployeeShouldNotBeFound("hireDate.greaterOrEqualThan=" + UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByHireDateIsLessThanSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where hireDate less than or equals to DEFAULT_HIRE_DATE
        defaultEmployeeShouldNotBeFound("hireDate.lessThan=" + DEFAULT_HIRE_DATE);

        // Get all the employeeList where hireDate less than or equals to UPDATED_HIRE_DATE
        defaultEmployeeShouldBeFound("hireDate.lessThan=" + UPDATED_HIRE_DATE);
    }


    @Test
    @Transactional
    public void getAllEmployeesByJobGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where jobGrade equals to DEFAULT_JOB_GRADE
        defaultEmployeeShouldBeFound("jobGrade.equals=" + DEFAULT_JOB_GRADE);

        // Get all the employeeList where jobGrade equals to UPDATED_JOB_GRADE
        defaultEmployeeShouldNotBeFound("jobGrade.equals=" + UPDATED_JOB_GRADE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByJobGradeIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where jobGrade in DEFAULT_JOB_GRADE or UPDATED_JOB_GRADE
        defaultEmployeeShouldBeFound("jobGrade.in=" + DEFAULT_JOB_GRADE + "," + UPDATED_JOB_GRADE);

        // Get all the employeeList where jobGrade equals to UPDATED_JOB_GRADE
        defaultEmployeeShouldNotBeFound("jobGrade.in=" + UPDATED_JOB_GRADE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByJobGradeIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where jobGrade is not null
        defaultEmployeeShouldBeFound("jobGrade.specified=true");

        // Get all the employeeList where jobGrade is null
        defaultEmployeeShouldNotBeFound("jobGrade.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByPositionIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where position equals to DEFAULT_POSITION
        defaultEmployeeShouldBeFound("position.equals=" + DEFAULT_POSITION);

        // Get all the employeeList where position equals to UPDATED_POSITION
        defaultEmployeeShouldNotBeFound("position.equals=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllEmployeesByPositionIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where position in DEFAULT_POSITION or UPDATED_POSITION
        defaultEmployeeShouldBeFound("position.in=" + DEFAULT_POSITION + "," + UPDATED_POSITION);

        // Get all the employeeList where position equals to UPDATED_POSITION
        defaultEmployeeShouldNotBeFound("position.in=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllEmployeesByPositionIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where position is not null
        defaultEmployeeShouldBeFound("position.specified=true");

        // Get all the employeeList where position is null
        defaultEmployeeShouldNotBeFound("position.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByJobIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where job equals to DEFAULT_JOB
        defaultEmployeeShouldBeFound("job.equals=" + DEFAULT_JOB);

        // Get all the employeeList where job equals to UPDATED_JOB
        defaultEmployeeShouldNotBeFound("job.equals=" + UPDATED_JOB);
    }

    @Test
    @Transactional
    public void getAllEmployeesByJobIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where job in DEFAULT_JOB or UPDATED_JOB
        defaultEmployeeShouldBeFound("job.in=" + DEFAULT_JOB + "," + UPDATED_JOB);

        // Get all the employeeList where job equals to UPDATED_JOB
        defaultEmployeeShouldNotBeFound("job.in=" + UPDATED_JOB);
    }

    @Test
    @Transactional
    public void getAllEmployeesByJobIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where job is not null
        defaultEmployeeShouldBeFound("job.specified=true");

        // Get all the employeeList where job is null
        defaultEmployeeShouldNotBeFound("job.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByDeptNameIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where deptName equals to DEFAULT_DEPT_NAME
        defaultEmployeeShouldBeFound("deptName.equals=" + DEFAULT_DEPT_NAME);

        // Get all the employeeList where deptName equals to UPDATED_DEPT_NAME
        defaultEmployeeShouldNotBeFound("deptName.equals=" + UPDATED_DEPT_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByDeptNameIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where deptName in DEFAULT_DEPT_NAME or UPDATED_DEPT_NAME
        defaultEmployeeShouldBeFound("deptName.in=" + DEFAULT_DEPT_NAME + "," + UPDATED_DEPT_NAME);

        // Get all the employeeList where deptName equals to UPDATED_DEPT_NAME
        defaultEmployeeShouldNotBeFound("deptName.in=" + UPDATED_DEPT_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByDeptNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where deptName is not null
        defaultEmployeeShouldBeFound("deptName.specified=true");

        // Get all the employeeList where deptName is null
        defaultEmployeeShouldNotBeFound("deptName.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmpNoIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where empNo equals to DEFAULT_EMP_NO
        defaultEmployeeShouldBeFound("empNo.equals=" + DEFAULT_EMP_NO);

        // Get all the employeeList where empNo equals to UPDATED_EMP_NO
        defaultEmployeeShouldNotBeFound("empNo.equals=" + UPDATED_EMP_NO);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmpNoIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where empNo in DEFAULT_EMP_NO or UPDATED_EMP_NO
        defaultEmployeeShouldBeFound("empNo.in=" + DEFAULT_EMP_NO + "," + UPDATED_EMP_NO);

        // Get all the employeeList where empNo equals to UPDATED_EMP_NO
        defaultEmployeeShouldNotBeFound("empNo.in=" + UPDATED_EMP_NO);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmpNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where empNo is not null
        defaultEmployeeShouldBeFound("empNo.specified=true");

        // Get all the employeeList where empNo is null
        defaultEmployeeShouldNotBeFound("empNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesBySeniorityIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where seniority equals to DEFAULT_SENIORITY
        defaultEmployeeShouldBeFound("seniority.equals=" + DEFAULT_SENIORITY);

        // Get all the employeeList where seniority equals to UPDATED_SENIORITY
        defaultEmployeeShouldNotBeFound("seniority.equals=" + UPDATED_SENIORITY);
    }

    @Test
    @Transactional
    public void getAllEmployeesBySeniorityIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where seniority in DEFAULT_SENIORITY or UPDATED_SENIORITY
        defaultEmployeeShouldBeFound("seniority.in=" + DEFAULT_SENIORITY + "," + UPDATED_SENIORITY);

        // Get all the employeeList where seniority equals to UPDATED_SENIORITY
        defaultEmployeeShouldNotBeFound("seniority.in=" + UPDATED_SENIORITY);
    }

    @Test
    @Transactional
    public void getAllEmployeesBySeniorityIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where seniority is not null
        defaultEmployeeShouldBeFound("seniority.specified=true");

        // Get all the employeeList where seniority is null
        defaultEmployeeShouldNotBeFound("seniority.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesBySeniorityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where seniority greater than or equals to DEFAULT_SENIORITY
        defaultEmployeeShouldBeFound("seniority.greaterOrEqualThan=" + DEFAULT_SENIORITY);

        // Get all the employeeList where seniority greater than or equals to UPDATED_SENIORITY
        defaultEmployeeShouldNotBeFound("seniority.greaterOrEqualThan=" + UPDATED_SENIORITY);
    }

    @Test
    @Transactional
    public void getAllEmployeesBySeniorityIsLessThanSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where seniority less than or equals to DEFAULT_SENIORITY
        defaultEmployeeShouldNotBeFound("seniority.lessThan=" + DEFAULT_SENIORITY);

        // Get all the employeeList where seniority less than or equals to UPDATED_SENIORITY
        defaultEmployeeShouldBeFound("seniority.lessThan=" + UPDATED_SENIORITY);
    }


    @Test
    @Transactional
    public void getAllEmployeesByContractorIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where contractor equals to DEFAULT_CONTRACTOR
        defaultEmployeeShouldBeFound("contractor.equals=" + DEFAULT_CONTRACTOR);

        // Get all the employeeList where contractor equals to UPDATED_CONTRACTOR
        defaultEmployeeShouldNotBeFound("contractor.equals=" + UPDATED_CONTRACTOR);
    }

    @Test
    @Transactional
    public void getAllEmployeesByContractorIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where contractor in DEFAULT_CONTRACTOR or UPDATED_CONTRACTOR
        defaultEmployeeShouldBeFound("contractor.in=" + DEFAULT_CONTRACTOR + "," + UPDATED_CONTRACTOR);

        // Get all the employeeList where contractor equals to UPDATED_CONTRACTOR
        defaultEmployeeShouldNotBeFound("contractor.in=" + UPDATED_CONTRACTOR);
    }

    @Test
    @Transactional
    public void getAllEmployeesByContractorIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where contractor is not null
        defaultEmployeeShouldBeFound("contractor.specified=true");

        // Get all the employeeList where contractor is null
        defaultEmployeeShouldNotBeFound("contractor.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthType equals to DEFAULT_BIRTH_TYPE
        defaultEmployeeShouldBeFound("birthType.equals=" + DEFAULT_BIRTH_TYPE);

        // Get all the employeeList where birthType equals to UPDATED_BIRTH_TYPE
        defaultEmployeeShouldNotBeFound("birthType.equals=" + UPDATED_BIRTH_TYPE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthTypeIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthType in DEFAULT_BIRTH_TYPE or UPDATED_BIRTH_TYPE
        defaultEmployeeShouldBeFound("birthType.in=" + DEFAULT_BIRTH_TYPE + "," + UPDATED_BIRTH_TYPE);

        // Get all the employeeList where birthType equals to UPDATED_BIRTH_TYPE
        defaultEmployeeShouldNotBeFound("birthType.in=" + UPDATED_BIRTH_TYPE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthType is not null
        defaultEmployeeShouldBeFound("birthType.specified=true");

        // Get all the employeeList where birthType is null
        defaultEmployeeShouldNotBeFound("birthType.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthdayIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthday equals to DEFAULT_BIRTHDAY
        defaultEmployeeShouldBeFound("birthday.equals=" + DEFAULT_BIRTHDAY);

        // Get all the employeeList where birthday equals to UPDATED_BIRTHDAY
        defaultEmployeeShouldNotBeFound("birthday.equals=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthdayIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthday in DEFAULT_BIRTHDAY or UPDATED_BIRTHDAY
        defaultEmployeeShouldBeFound("birthday.in=" + DEFAULT_BIRTHDAY + "," + UPDATED_BIRTHDAY);

        // Get all the employeeList where birthday equals to UPDATED_BIRTHDAY
        defaultEmployeeShouldNotBeFound("birthday.in=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthdayIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthday is not null
        defaultEmployeeShouldBeFound("birthday.specified=true");

        // Get all the employeeList where birthday is null
        defaultEmployeeShouldNotBeFound("birthday.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthdayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthday greater than or equals to DEFAULT_BIRTHDAY
        defaultEmployeeShouldBeFound("birthday.greaterOrEqualThan=" + DEFAULT_BIRTHDAY);

        // Get all the employeeList where birthday greater than or equals to UPDATED_BIRTHDAY
        defaultEmployeeShouldNotBeFound("birthday.greaterOrEqualThan=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByBirthdayIsLessThanSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where birthday less than or equals to DEFAULT_BIRTHDAY
        defaultEmployeeShouldNotBeFound("birthday.lessThan=" + DEFAULT_BIRTHDAY);

        // Get all the employeeList where birthday less than or equals to UPDATED_BIRTHDAY
        defaultEmployeeShouldBeFound("birthday.lessThan=" + UPDATED_BIRTHDAY);
    }


    @Test
    @Transactional
    public void getAllEmployeesByWorkLocIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where workLoc equals to DEFAULT_WORK_LOC
        defaultEmployeeShouldBeFound("workLoc.equals=" + DEFAULT_WORK_LOC);

        // Get all the employeeList where workLoc equals to UPDATED_WORK_LOC
        defaultEmployeeShouldNotBeFound("workLoc.equals=" + UPDATED_WORK_LOC);
    }

    @Test
    @Transactional
    public void getAllEmployeesByWorkLocIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where workLoc in DEFAULT_WORK_LOC or UPDATED_WORK_LOC
        defaultEmployeeShouldBeFound("workLoc.in=" + DEFAULT_WORK_LOC + "," + UPDATED_WORK_LOC);

        // Get all the employeeList where workLoc equals to UPDATED_WORK_LOC
        defaultEmployeeShouldNotBeFound("workLoc.in=" + UPDATED_WORK_LOC);
    }

    @Test
    @Transactional
    public void getAllEmployeesByWorkLocIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where workLoc is not null
        defaultEmployeeShouldBeFound("workLoc.specified=true");

        // Get all the employeeList where workLoc is null
        defaultEmployeeShouldNotBeFound("workLoc.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByContactAddrIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where contactAddr equals to DEFAULT_CONTACT_ADDR
        defaultEmployeeShouldBeFound("contactAddr.equals=" + DEFAULT_CONTACT_ADDR);

        // Get all the employeeList where contactAddr equals to UPDATED_CONTACT_ADDR
        defaultEmployeeShouldNotBeFound("contactAddr.equals=" + UPDATED_CONTACT_ADDR);
    }

    @Test
    @Transactional
    public void getAllEmployeesByContactAddrIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where contactAddr in DEFAULT_CONTACT_ADDR or UPDATED_CONTACT_ADDR
        defaultEmployeeShouldBeFound("contactAddr.in=" + DEFAULT_CONTACT_ADDR + "," + UPDATED_CONTACT_ADDR);

        // Get all the employeeList where contactAddr equals to UPDATED_CONTACT_ADDR
        defaultEmployeeShouldNotBeFound("contactAddr.in=" + UPDATED_CONTACT_ADDR);
    }

    @Test
    @Transactional
    public void getAllEmployeesByContactAddrIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where contactAddr is not null
        defaultEmployeeShouldBeFound("contactAddr.specified=true");

        // Get all the employeeList where contactAddr is null
        defaultEmployeeShouldNotBeFound("contactAddr.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByNationalityIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where nationality equals to DEFAULT_NATIONALITY
        defaultEmployeeShouldBeFound("nationality.equals=" + DEFAULT_NATIONALITY);

        // Get all the employeeList where nationality equals to UPDATED_NATIONALITY
        defaultEmployeeShouldNotBeFound("nationality.equals=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByNationalityIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where nationality in DEFAULT_NATIONALITY or UPDATED_NATIONALITY
        defaultEmployeeShouldBeFound("nationality.in=" + DEFAULT_NATIONALITY + "," + UPDATED_NATIONALITY);

        // Get all the employeeList where nationality equals to UPDATED_NATIONALITY
        defaultEmployeeShouldNotBeFound("nationality.in=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByNationalityIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where nationality is not null
        defaultEmployeeShouldBeFound("nationality.specified=true");

        // Get all the employeeList where nationality is null
        defaultEmployeeShouldNotBeFound("nationality.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where firstName equals to DEFAULT_FIRST_NAME
        defaultEmployeeShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the employeeList where firstName equals to UPDATED_FIRST_NAME
        defaultEmployeeShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultEmployeeShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the employeeList where firstName equals to UPDATED_FIRST_NAME
        defaultEmployeeShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where firstName is not null
        defaultEmployeeShouldBeFound("firstName.specified=true");

        // Get all the employeeList where firstName is null
        defaultEmployeeShouldNotBeFound("firstName.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where lastName equals to DEFAULT_LAST_NAME
        defaultEmployeeShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the employeeList where lastName equals to UPDATED_LAST_NAME
        defaultEmployeeShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultEmployeeShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the employeeList where lastName equals to UPDATED_LAST_NAME
        defaultEmployeeShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where lastName is not null
        defaultEmployeeShouldBeFound("lastName.specified=true");

        // Get all the employeeList where lastName is null
        defaultEmployeeShouldNotBeFound("lastName.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByOthersIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where others equals to DEFAULT_OTHERS
        defaultEmployeeShouldBeFound("others.equals=" + DEFAULT_OTHERS);

        // Get all the employeeList where others equals to UPDATED_OTHERS
        defaultEmployeeShouldNotBeFound("others.equals=" + UPDATED_OTHERS);
    }

    @Test
    @Transactional
    public void getAllEmployeesByOthersIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where others in DEFAULT_OTHERS or UPDATED_OTHERS
        defaultEmployeeShouldBeFound("others.in=" + DEFAULT_OTHERS + "," + UPDATED_OTHERS);

        // Get all the employeeList where others equals to UPDATED_OTHERS
        defaultEmployeeShouldNotBeFound("others.in=" + UPDATED_OTHERS);
    }

    @Test
    @Transactional
    public void getAllEmployeesByOthersIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where others is not null
        defaultEmployeeShouldBeFound("others.specified=true");

        // Get all the employeeList where others is null
        defaultEmployeeShouldNotBeFound("others.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultEmployeeShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the employeeList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultEmployeeShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultEmployeeShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the employeeList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultEmployeeShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where isSelfVerify is not null
        defaultEmployeeShouldBeFound("isSelfVerify.specified=true");

        // Get all the employeeList where isSelfVerify is null
        defaultEmployeeShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultEmployeeShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the employeeList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultEmployeeShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultEmployeeShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the employeeList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultEmployeeShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEmployeesByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where isHrVerify is not null
        defaultEmployeeShouldBeFound("isHrVerify.specified=true");

        // Get all the employeeList where isHrVerify is null
        defaultEmployeeShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByContractsIsEqualToSomething() throws Exception {
        // Initialize the database
        Contract contracts = ContractResourceIT.createEntity(em);
        em.persist(contracts);
        em.flush();
        employee.addContracts(contracts);
        employeeRepository.saveAndFlush(employee);
        Long contractsId = contracts.getId();

        // Get all the employeeList where contracts equals to contractsId
        defaultEmployeeShouldBeFound("contractsId.equals=" + contractsId);

        // Get all the employeeList where contracts equals to contractsId + 1
        defaultEmployeeShouldNotBeFound("contractsId.equals=" + (contractsId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByPersonalsIsEqualToSomething() throws Exception {
        // Initialize the database
        Personal personals = PersonalResourceIT.createEntity(em);
        em.persist(personals);
        em.flush();
        employee.addPersonals(personals);
        employeeRepository.saveAndFlush(employee);
        Long personalsId = personals.getId();

        // Get all the employeeList where personals equals to personalsId
        defaultEmployeeShouldBeFound("personalsId.equals=" + personalsId);

        // Get all the employeeList where personals equals to personalsId + 1
        defaultEmployeeShouldNotBeFound("personalsId.equals=" + (personalsId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesBySocialSecurityBenefitsIsEqualToSomething() throws Exception {
        // Initialize the database
        SocialSecurityBenefits socialSecurityBenefits = SocialSecurityBenefitsResourceIT.createEntity(em);
        em.persist(socialSecurityBenefits);
        em.flush();
        employee.addSocialSecurityBenefits(socialSecurityBenefits);
        employeeRepository.saveAndFlush(employee);
        Long socialSecurityBenefitsId = socialSecurityBenefits.getId();

        // Get all the employeeList where socialSecurityBenefits equals to socialSecurityBenefitsId
        defaultEmployeeShouldBeFound("socialSecurityBenefitsId.equals=" + socialSecurityBenefitsId);

        // Get all the employeeList where socialSecurityBenefits equals to socialSecurityBenefitsId + 1
        defaultEmployeeShouldNotBeFound("socialSecurityBenefitsId.equals=" + (socialSecurityBenefitsId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByPayCardsIsEqualToSomething() throws Exception {
        // Initialize the database
        PayCard payCards = PayCardResourceIT.createEntity(em);
        em.persist(payCards);
        em.flush();
        employee.addPayCards(payCards);
        employeeRepository.saveAndFlush(employee);
        Long payCardsId = payCards.getId();

        // Get all the employeeList where payCards equals to payCardsId
        defaultEmployeeShouldBeFound("payCardsId.equals=" + payCardsId);

        // Get all the employeeList where payCards equals to payCardsId + 1
        defaultEmployeeShouldNotBeFound("payCardsId.equals=" + (payCardsId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByDimissionsIsEqualToSomething() throws Exception {
        // Initialize the database
        Dimission dimissions = DimissionResourceIT.createEntity(em);
        em.persist(dimissions);
        em.flush();
        employee.addDimissions(dimissions);
        employeeRepository.saveAndFlush(employee);
        Long dimissionsId = dimissions.getId();

        // Get all the employeeList where dimissions equals to dimissionsId
        defaultEmployeeShouldBeFound("dimissionsId.equals=" + dimissionsId);

        // Get all the employeeList where dimissions equals to dimissionsId + 1
        defaultEmployeeShouldNotBeFound("dimissionsId.equals=" + (dimissionsId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByWorkExperiencesIsEqualToSomething() throws Exception {
        // Initialize the database
        WorkExperience workExperiences = WorkExperienceResourceIT.createEntity(em);
        em.persist(workExperiences);
        em.flush();
        employee.addWorkExperiences(workExperiences);
        employeeRepository.saveAndFlush(employee);
        Long workExperiencesId = workExperiences.getId();

        // Get all the employeeList where workExperiences equals to workExperiencesId
        defaultEmployeeShouldBeFound("workExperiencesId.equals=" + workExperiencesId);

        // Get all the employeeList where workExperiences equals to workExperiencesId + 1
        defaultEmployeeShouldNotBeFound("workExperiencesId.equals=" + (workExperiencesId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByEducationExperiencesIsEqualToSomething() throws Exception {
        // Initialize the database
        EducationExperience educationExperiences = EducationExperienceResourceIT.createEntity(em);
        em.persist(educationExperiences);
        em.flush();
        employee.addEducationExperiences(educationExperiences);
        employeeRepository.saveAndFlush(employee);
        Long educationExperiencesId = educationExperiences.getId();

        // Get all the employeeList where educationExperiences equals to educationExperiencesId
        defaultEmployeeShouldBeFound("educationExperiencesId.equals=" + educationExperiencesId);

        // Get all the employeeList where educationExperiences equals to educationExperiencesId + 1
        defaultEmployeeShouldNotBeFound("educationExperiencesId.equals=" + (educationExperiencesId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByDirectSupervisorsIsEqualToSomething() throws Exception {
        // Initialize the database
        DirectSupervisor directSupervisors = DirectSupervisorResourceIT.createEntity(em);
        em.persist(directSupervisors);
        em.flush();
        employee.addDirectSupervisors(directSupervisors);
        employeeRepository.saveAndFlush(employee);
        Long directSupervisorsId = directSupervisors.getId();

        // Get all the employeeList where directSupervisors equals to directSupervisorsId
        defaultEmployeeShouldBeFound("directSupervisorsId.equals=" + directSupervisorsId);

        // Get all the employeeList where directSupervisors equals to directSupervisorsId + 1
        defaultEmployeeShouldNotBeFound("directSupervisorsId.equals=" + (directSupervisorsId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByAdditionalPostsIsEqualToSomething() throws Exception {
        // Initialize the database
        AdditionalPost additionalPosts = AdditionalPostResourceIT.createEntity(em);
        em.persist(additionalPosts);
        em.flush();
        employee.addAdditionalPosts(additionalPosts);
        employeeRepository.saveAndFlush(employee);
        Long additionalPostsId = additionalPosts.getId();

        // Get all the employeeList where additionalPosts equals to additionalPostsId
        defaultEmployeeShouldBeFound("additionalPostsId.equals=" + additionalPostsId);

        // Get all the employeeList where additionalPosts equals to additionalPostsId + 1
        defaultEmployeeShouldNotBeFound("additionalPostsId.equals=" + (additionalPostsId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpStatus status = EnumEmpStatusResourceIT.createEntity(em);
        em.persist(status);
        em.flush();
        employee.setStatus(status);
        employeeRepository.saveAndFlush(employee);
        Long statusId = status.getId();

        // Get all the employeeList where status equals to statusId
        defaultEmployeeShouldBeFound("statusId.equals=" + statusId);

        // Get all the employeeList where status equals to statusId + 1
        defaultEmployeeShouldNotBeFound("statusId.equals=" + (statusId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByIdTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumIdType idType = EnumIdTypeResourceIT.createEntity(em);
        em.persist(idType);
        em.flush();
        employee.setIdType(idType);
        employeeRepository.saveAndFlush(employee);
        Long idTypeId = idType.getId();

        // Get all the employeeList where idType equals to idTypeId
        defaultEmployeeShouldBeFound("idTypeId.equals=" + idTypeId);

        // Get all the employeeList where idType equals to idTypeId + 1
        defaultEmployeeShouldNotBeFound("idTypeId.equals=" + (idTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByContractTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumContractType contractType = EnumContractTypeResourceIT.createEntity(em);
        em.persist(contractType);
        em.flush();
        employee.setContractType(contractType);
        employeeRepository.saveAndFlush(employee);
        Long contractTypeId = contractType.getId();

        // Get all the employeeList where contractType equals to contractTypeId
        defaultEmployeeShouldBeFound("contractTypeId.equals=" + contractTypeId);

        // Get all the employeeList where contractType equals to contractTypeId + 1
        defaultEmployeeShouldNotBeFound("contractTypeId.equals=" + (contractTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByEmpTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpType empType = EnumEmpTypeResourceIT.createEntity(em);
        em.persist(empType);
        em.flush();
        employee.setEmpType(empType);
        employeeRepository.saveAndFlush(employee);
        Long empTypeId = empType.getId();

        // Get all the employeeList where empType equals to empTypeId
        defaultEmployeeShouldBeFound("empTypeId.equals=" + empTypeId);

        // Get all the employeeList where empType equals to empTypeId + 1
        defaultEmployeeShouldNotBeFound("empTypeId.equals=" + (empTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumGender gender = EnumGenderResourceIT.createEntity(em);
        em.persist(gender);
        em.flush();
        employee.setGender(gender);
        employeeRepository.saveAndFlush(employee);
        Long genderId = gender.getId();

        // Get all the employeeList where gender equals to genderId
        defaultEmployeeShouldBeFound("genderId.equals=" + genderId);

        // Get all the employeeList where gender equals to genderId + 1
        defaultEmployeeShouldNotBeFound("genderId.equals=" + (genderId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEmployeeShouldBeFound(String filter) throws Exception {
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].jobGrade").value(hasItem(DEFAULT_JOB_GRADE)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB)))
            .andExpect(jsonPath("$.[*].deptName").value(hasItem(DEFAULT_DEPT_NAME)))
            .andExpect(jsonPath("$.[*].empNo").value(hasItem(DEFAULT_EMP_NO)))
            .andExpect(jsonPath("$.[*].seniority").value(hasItem(DEFAULT_SENIORITY)))
            .andExpect(jsonPath("$.[*].contractor").value(hasItem(DEFAULT_CONTRACTOR)))
            .andExpect(jsonPath("$.[*].birthType").value(hasItem(DEFAULT_BIRTH_TYPE.toString())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].workLoc").value(hasItem(DEFAULT_WORK_LOC)))
            .andExpect(jsonPath("$.[*].contactAddr").value(hasItem(DEFAULT_CONTACT_ADDR)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].others").value(hasItem(DEFAULT_OTHERS)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restEmployeeMockMvc.perform(get("/api/employees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEmployeeShouldNotBeFound(String filter) throws Exception {
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEmployeeMockMvc.perform(get("/api/employees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .idNumber(UPDATED_ID_NUMBER)
            .phone(UPDATED_PHONE)
            .hireDate(UPDATED_HIRE_DATE)
            .jobGrade(UPDATED_JOB_GRADE)
            .position(UPDATED_POSITION)
            .job(UPDATED_JOB)
            .deptName(UPDATED_DEPT_NAME)
            .empNo(UPDATED_EMP_NO)
            .seniority(UPDATED_SENIORITY)
            .contractor(UPDATED_CONTRACTOR)
            .birthType(UPDATED_BIRTH_TYPE)
            .birthday(UPDATED_BIRTHDAY)
            .workLoc(UPDATED_WORK_LOC)
            .contactAddr(UPDATED_CONTACT_ADDR)
            .nationality(UPDATED_NATIONALITY)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .others(UPDATED_OTHERS)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testEmployee.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEmployee.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testEmployee.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testEmployee.getHireDate()).isEqualTo(UPDATED_HIRE_DATE);
        assertThat(testEmployee.getJobGrade()).isEqualTo(UPDATED_JOB_GRADE);
        assertThat(testEmployee.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testEmployee.getJob()).isEqualTo(UPDATED_JOB);
        assertThat(testEmployee.getDeptName()).isEqualTo(UPDATED_DEPT_NAME);
        assertThat(testEmployee.getEmpNo()).isEqualTo(UPDATED_EMP_NO);
        assertThat(testEmployee.getSeniority()).isEqualTo(UPDATED_SENIORITY);
        assertThat(testEmployee.getContractor()).isEqualTo(UPDATED_CONTRACTOR);
        assertThat(testEmployee.getBirthType()).isEqualTo(UPDATED_BIRTH_TYPE);
        assertThat(testEmployee.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testEmployee.getWorkLoc()).isEqualTo(UPDATED_WORK_LOC);
        assertThat(testEmployee.getContactAddr()).isEqualTo(UPDATED_CONTACT_ADDR);
        assertThat(testEmployee.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testEmployee.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testEmployee.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testEmployee.getOthers()).isEqualTo(UPDATED_OTHERS);
        assertThat(testEmployee.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testEmployee.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Delete the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setId(2L);
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO2.setId(employeeDTO1.getId());
        assertThat(employeeDTO1).isEqualTo(employeeDTO2);
        employeeDTO2.setId(2L);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO1.setId(null);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(employeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(employeeMapper.fromId(null)).isNull();
    }
}
