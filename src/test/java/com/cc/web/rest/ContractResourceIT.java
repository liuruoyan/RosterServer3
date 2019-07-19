package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.Contract;
import com.cc.domain.EnumContractType;
import com.cc.domain.Employee;
import com.cc.repository.ContractRepository;
import com.cc.service.ContractService;
import com.cc.service.dto.ContractDTO;
import com.cc.service.mapper.ContractMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.ContractCriteria;
import com.cc.service.ContractQueryService;

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
 * Integration tests for the {@Link ContractResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class ContractResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_TEL = "AAAAAAAAAA";
    private static final String UPDATED_WORK_TEL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROBATION_END_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROBATION_END_DAY = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PROBATION_LENGTH = 1;
    private static final Integer UPDATED_PROBATION_LENGTH = 2;

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractQueryService contractQueryService;

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

    private MockMvc restContractMockMvc;

    private Contract contract;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContractResource contractResource = new ContractResource(contractService, contractQueryService);
        this.restContractMockMvc = MockMvcBuilders.standaloneSetup(contractResource)
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
    public static Contract createEntity(EntityManager em) {
        Contract contract = new Contract()
            .code(DEFAULT_CODE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .email(DEFAULT_EMAIL)
            .workTel(DEFAULT_WORK_TEL)
            .probationEndDay(DEFAULT_PROBATION_END_DAY)
            .probationLength(DEFAULT_PROBATION_LENGTH)
            .other(DEFAULT_OTHER)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return contract;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contract createUpdatedEntity(EntityManager em) {
        Contract contract = new Contract()
            .code(UPDATED_CODE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .email(UPDATED_EMAIL)
            .workTel(UPDATED_WORK_TEL)
            .probationEndDay(UPDATED_PROBATION_END_DAY)
            .probationLength(UPDATED_PROBATION_LENGTH)
            .other(UPDATED_OTHER)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return contract;
    }

    @BeforeEach
    public void initTest() {
        contract = createEntity(em);
    }

    @Test
    @Transactional
    public void createContract() throws Exception {
        int databaseSizeBeforeCreate = contractRepository.findAll().size();

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);
        restContractMockMvc.perform(post("/api/contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contractDTO)))
            .andExpect(status().isCreated());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeCreate + 1);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testContract.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testContract.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testContract.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testContract.getWorkTel()).isEqualTo(DEFAULT_WORK_TEL);
        assertThat(testContract.getProbationEndDay()).isEqualTo(DEFAULT_PROBATION_END_DAY);
        assertThat(testContract.getProbationLength()).isEqualTo(DEFAULT_PROBATION_LENGTH);
        assertThat(testContract.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testContract.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testContract.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createContractWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contractRepository.findAll().size();

        // Create the Contract with an existing ID
        contract.setId(1L);
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractMockMvc.perform(post("/api/contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contractDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllContracts() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList
        restContractMockMvc.perform(get("/api/contracts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contract.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].workTel").value(hasItem(DEFAULT_WORK_TEL.toString())))
            .andExpect(jsonPath("$.[*].probationEndDay").value(hasItem(DEFAULT_PROBATION_END_DAY.toString())))
            .andExpect(jsonPath("$.[*].probationLength").value(hasItem(DEFAULT_PROBATION_LENGTH)))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get the contract
        restContractMockMvc.perform(get("/api/contracts/{id}", contract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contract.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.workTel").value(DEFAULT_WORK_TEL.toString()))
            .andExpect(jsonPath("$.probationEndDay").value(DEFAULT_PROBATION_END_DAY.toString()))
            .andExpect(jsonPath("$.probationLength").value(DEFAULT_PROBATION_LENGTH))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllContractsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where code equals to DEFAULT_CODE
        defaultContractShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the contractList where code equals to UPDATED_CODE
        defaultContractShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllContractsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where code in DEFAULT_CODE or UPDATED_CODE
        defaultContractShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the contractList where code equals to UPDATED_CODE
        defaultContractShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllContractsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where code is not null
        defaultContractShouldBeFound("code.specified=true");

        // Get all the contractList where code is null
        defaultContractShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByStartDateIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where startDate equals to DEFAULT_START_DATE
        defaultContractShouldBeFound("startDate.equals=" + DEFAULT_START_DATE);

        // Get all the contractList where startDate equals to UPDATED_START_DATE
        defaultContractShouldNotBeFound("startDate.equals=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllContractsByStartDateIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where startDate in DEFAULT_START_DATE or UPDATED_START_DATE
        defaultContractShouldBeFound("startDate.in=" + DEFAULT_START_DATE + "," + UPDATED_START_DATE);

        // Get all the contractList where startDate equals to UPDATED_START_DATE
        defaultContractShouldNotBeFound("startDate.in=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllContractsByStartDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where startDate is not null
        defaultContractShouldBeFound("startDate.specified=true");

        // Get all the contractList where startDate is null
        defaultContractShouldNotBeFound("startDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByStartDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where startDate greater than or equals to DEFAULT_START_DATE
        defaultContractShouldBeFound("startDate.greaterOrEqualThan=" + DEFAULT_START_DATE);

        // Get all the contractList where startDate greater than or equals to UPDATED_START_DATE
        defaultContractShouldNotBeFound("startDate.greaterOrEqualThan=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllContractsByStartDateIsLessThanSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where startDate less than or equals to DEFAULT_START_DATE
        defaultContractShouldNotBeFound("startDate.lessThan=" + DEFAULT_START_DATE);

        // Get all the contractList where startDate less than or equals to UPDATED_START_DATE
        defaultContractShouldBeFound("startDate.lessThan=" + UPDATED_START_DATE);
    }


    @Test
    @Transactional
    public void getAllContractsByEndDateIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where endDate equals to DEFAULT_END_DATE
        defaultContractShouldBeFound("endDate.equals=" + DEFAULT_END_DATE);

        // Get all the contractList where endDate equals to UPDATED_END_DATE
        defaultContractShouldNotBeFound("endDate.equals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllContractsByEndDateIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where endDate in DEFAULT_END_DATE or UPDATED_END_DATE
        defaultContractShouldBeFound("endDate.in=" + DEFAULT_END_DATE + "," + UPDATED_END_DATE);

        // Get all the contractList where endDate equals to UPDATED_END_DATE
        defaultContractShouldNotBeFound("endDate.in=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllContractsByEndDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where endDate is not null
        defaultContractShouldBeFound("endDate.specified=true");

        // Get all the contractList where endDate is null
        defaultContractShouldNotBeFound("endDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByEndDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where endDate greater than or equals to DEFAULT_END_DATE
        defaultContractShouldBeFound("endDate.greaterOrEqualThan=" + DEFAULT_END_DATE);

        // Get all the contractList where endDate greater than or equals to UPDATED_END_DATE
        defaultContractShouldNotBeFound("endDate.greaterOrEqualThan=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllContractsByEndDateIsLessThanSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where endDate less than or equals to DEFAULT_END_DATE
        defaultContractShouldNotBeFound("endDate.lessThan=" + DEFAULT_END_DATE);

        // Get all the contractList where endDate less than or equals to UPDATED_END_DATE
        defaultContractShouldBeFound("endDate.lessThan=" + UPDATED_END_DATE);
    }


    @Test
    @Transactional
    public void getAllContractsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where email equals to DEFAULT_EMAIL
        defaultContractShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the contractList where email equals to UPDATED_EMAIL
        defaultContractShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllContractsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultContractShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the contractList where email equals to UPDATED_EMAIL
        defaultContractShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllContractsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where email is not null
        defaultContractShouldBeFound("email.specified=true");

        // Get all the contractList where email is null
        defaultContractShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByWorkTelIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where workTel equals to DEFAULT_WORK_TEL
        defaultContractShouldBeFound("workTel.equals=" + DEFAULT_WORK_TEL);

        // Get all the contractList where workTel equals to UPDATED_WORK_TEL
        defaultContractShouldNotBeFound("workTel.equals=" + UPDATED_WORK_TEL);
    }

    @Test
    @Transactional
    public void getAllContractsByWorkTelIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where workTel in DEFAULT_WORK_TEL or UPDATED_WORK_TEL
        defaultContractShouldBeFound("workTel.in=" + DEFAULT_WORK_TEL + "," + UPDATED_WORK_TEL);

        // Get all the contractList where workTel equals to UPDATED_WORK_TEL
        defaultContractShouldNotBeFound("workTel.in=" + UPDATED_WORK_TEL);
    }

    @Test
    @Transactional
    public void getAllContractsByWorkTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where workTel is not null
        defaultContractShouldBeFound("workTel.specified=true");

        // Get all the contractList where workTel is null
        defaultContractShouldNotBeFound("workTel.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByProbationEndDayIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationEndDay equals to DEFAULT_PROBATION_END_DAY
        defaultContractShouldBeFound("probationEndDay.equals=" + DEFAULT_PROBATION_END_DAY);

        // Get all the contractList where probationEndDay equals to UPDATED_PROBATION_END_DAY
        defaultContractShouldNotBeFound("probationEndDay.equals=" + UPDATED_PROBATION_END_DAY);
    }

    @Test
    @Transactional
    public void getAllContractsByProbationEndDayIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationEndDay in DEFAULT_PROBATION_END_DAY or UPDATED_PROBATION_END_DAY
        defaultContractShouldBeFound("probationEndDay.in=" + DEFAULT_PROBATION_END_DAY + "," + UPDATED_PROBATION_END_DAY);

        // Get all the contractList where probationEndDay equals to UPDATED_PROBATION_END_DAY
        defaultContractShouldNotBeFound("probationEndDay.in=" + UPDATED_PROBATION_END_DAY);
    }

    @Test
    @Transactional
    public void getAllContractsByProbationEndDayIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationEndDay is not null
        defaultContractShouldBeFound("probationEndDay.specified=true");

        // Get all the contractList where probationEndDay is null
        defaultContractShouldNotBeFound("probationEndDay.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByProbationEndDayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationEndDay greater than or equals to DEFAULT_PROBATION_END_DAY
        defaultContractShouldBeFound("probationEndDay.greaterOrEqualThan=" + DEFAULT_PROBATION_END_DAY);

        // Get all the contractList where probationEndDay greater than or equals to UPDATED_PROBATION_END_DAY
        defaultContractShouldNotBeFound("probationEndDay.greaterOrEqualThan=" + UPDATED_PROBATION_END_DAY);
    }

    @Test
    @Transactional
    public void getAllContractsByProbationEndDayIsLessThanSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationEndDay less than or equals to DEFAULT_PROBATION_END_DAY
        defaultContractShouldNotBeFound("probationEndDay.lessThan=" + DEFAULT_PROBATION_END_DAY);

        // Get all the contractList where probationEndDay less than or equals to UPDATED_PROBATION_END_DAY
        defaultContractShouldBeFound("probationEndDay.lessThan=" + UPDATED_PROBATION_END_DAY);
    }


    @Test
    @Transactional
    public void getAllContractsByProbationLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationLength equals to DEFAULT_PROBATION_LENGTH
        defaultContractShouldBeFound("probationLength.equals=" + DEFAULT_PROBATION_LENGTH);

        // Get all the contractList where probationLength equals to UPDATED_PROBATION_LENGTH
        defaultContractShouldNotBeFound("probationLength.equals=" + UPDATED_PROBATION_LENGTH);
    }

    @Test
    @Transactional
    public void getAllContractsByProbationLengthIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationLength in DEFAULT_PROBATION_LENGTH or UPDATED_PROBATION_LENGTH
        defaultContractShouldBeFound("probationLength.in=" + DEFAULT_PROBATION_LENGTH + "," + UPDATED_PROBATION_LENGTH);

        // Get all the contractList where probationLength equals to UPDATED_PROBATION_LENGTH
        defaultContractShouldNotBeFound("probationLength.in=" + UPDATED_PROBATION_LENGTH);
    }

    @Test
    @Transactional
    public void getAllContractsByProbationLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationLength is not null
        defaultContractShouldBeFound("probationLength.specified=true");

        // Get all the contractList where probationLength is null
        defaultContractShouldNotBeFound("probationLength.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByProbationLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationLength greater than or equals to DEFAULT_PROBATION_LENGTH
        defaultContractShouldBeFound("probationLength.greaterOrEqualThan=" + DEFAULT_PROBATION_LENGTH);

        // Get all the contractList where probationLength greater than or equals to UPDATED_PROBATION_LENGTH
        defaultContractShouldNotBeFound("probationLength.greaterOrEqualThan=" + UPDATED_PROBATION_LENGTH);
    }

    @Test
    @Transactional
    public void getAllContractsByProbationLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where probationLength less than or equals to DEFAULT_PROBATION_LENGTH
        defaultContractShouldNotBeFound("probationLength.lessThan=" + DEFAULT_PROBATION_LENGTH);

        // Get all the contractList where probationLength less than or equals to UPDATED_PROBATION_LENGTH
        defaultContractShouldBeFound("probationLength.lessThan=" + UPDATED_PROBATION_LENGTH);
    }


    @Test
    @Transactional
    public void getAllContractsByOtherIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where other equals to DEFAULT_OTHER
        defaultContractShouldBeFound("other.equals=" + DEFAULT_OTHER);

        // Get all the contractList where other equals to UPDATED_OTHER
        defaultContractShouldNotBeFound("other.equals=" + UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void getAllContractsByOtherIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where other in DEFAULT_OTHER or UPDATED_OTHER
        defaultContractShouldBeFound("other.in=" + DEFAULT_OTHER + "," + UPDATED_OTHER);

        // Get all the contractList where other equals to UPDATED_OTHER
        defaultContractShouldNotBeFound("other.in=" + UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void getAllContractsByOtherIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where other is not null
        defaultContractShouldBeFound("other.specified=true");

        // Get all the contractList where other is null
        defaultContractShouldNotBeFound("other.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultContractShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the contractList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultContractShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllContractsByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultContractShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the contractList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultContractShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllContractsByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where isSelfVerify is not null
        defaultContractShouldBeFound("isSelfVerify.specified=true");

        // Get all the contractList where isSelfVerify is null
        defaultContractShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultContractShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the contractList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultContractShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllContractsByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultContractShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the contractList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultContractShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllContractsByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList where isHrVerify is not null
        defaultContractShouldBeFound("isHrVerify.specified=true");

        // Get all the contractList where isHrVerify is null
        defaultContractShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllContractsByContractTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumContractType contractType = EnumContractTypeResourceIT.createEntity(em);
        em.persist(contractType);
        em.flush();
        contract.setContractType(contractType);
        contractRepository.saveAndFlush(contract);
        Long contractTypeId = contractType.getId();

        // Get all the contractList where contractType equals to contractTypeId
        defaultContractShouldBeFound("contractTypeId.equals=" + contractTypeId);

        // Get all the contractList where contractType equals to contractTypeId + 1
        defaultContractShouldNotBeFound("contractTypeId.equals=" + (contractTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllContractsByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        contract.setEmp(emp);
        contractRepository.saveAndFlush(contract);
        Long empId = emp.getId();

        // Get all the contractList where emp equals to empId
        defaultContractShouldBeFound("empId.equals=" + empId);

        // Get all the contractList where emp equals to empId + 1
        defaultContractShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultContractShouldBeFound(String filter) throws Exception {
        restContractMockMvc.perform(get("/api/contracts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contract.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].workTel").value(hasItem(DEFAULT_WORK_TEL)))
            .andExpect(jsonPath("$.[*].probationEndDay").value(hasItem(DEFAULT_PROBATION_END_DAY.toString())))
            .andExpect(jsonPath("$.[*].probationLength").value(hasItem(DEFAULT_PROBATION_LENGTH)))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restContractMockMvc.perform(get("/api/contracts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultContractShouldNotBeFound(String filter) throws Exception {
        restContractMockMvc.perform(get("/api/contracts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restContractMockMvc.perform(get("/api/contracts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingContract() throws Exception {
        // Get the contract
        restContractMockMvc.perform(get("/api/contracts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Update the contract
        Contract updatedContract = contractRepository.findById(contract.getId()).get();
        // Disconnect from session so that the updates on updatedContract are not directly saved in db
        em.detach(updatedContract);
        updatedContract
            .code(UPDATED_CODE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .email(UPDATED_EMAIL)
            .workTel(UPDATED_WORK_TEL)
            .probationEndDay(UPDATED_PROBATION_END_DAY)
            .probationLength(UPDATED_PROBATION_LENGTH)
            .other(UPDATED_OTHER)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        ContractDTO contractDTO = contractMapper.toDto(updatedContract);

        restContractMockMvc.perform(put("/api/contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contractDTO)))
            .andExpect(status().isOk());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testContract.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testContract.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testContract.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testContract.getWorkTel()).isEqualTo(UPDATED_WORK_TEL);
        assertThat(testContract.getProbationEndDay()).isEqualTo(UPDATED_PROBATION_END_DAY);
        assertThat(testContract.getProbationLength()).isEqualTo(UPDATED_PROBATION_LENGTH);
        assertThat(testContract.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testContract.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testContract.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractMockMvc.perform(put("/api/contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contractDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeDelete = contractRepository.findAll().size();

        // Delete the contract
        restContractMockMvc.perform(delete("/api/contracts/{id}", contract.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contract.class);
        Contract contract1 = new Contract();
        contract1.setId(1L);
        Contract contract2 = new Contract();
        contract2.setId(contract1.getId());
        assertThat(contract1).isEqualTo(contract2);
        contract2.setId(2L);
        assertThat(contract1).isNotEqualTo(contract2);
        contract1.setId(null);
        assertThat(contract1).isNotEqualTo(contract2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractDTO.class);
        ContractDTO contractDTO1 = new ContractDTO();
        contractDTO1.setId(1L);
        ContractDTO contractDTO2 = new ContractDTO();
        assertThat(contractDTO1).isNotEqualTo(contractDTO2);
        contractDTO2.setId(contractDTO1.getId());
        assertThat(contractDTO1).isEqualTo(contractDTO2);
        contractDTO2.setId(2L);
        assertThat(contractDTO1).isNotEqualTo(contractDTO2);
        contractDTO1.setId(null);
        assertThat(contractDTO1).isNotEqualTo(contractDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contractMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contractMapper.fromId(null)).isNull();
    }
}
