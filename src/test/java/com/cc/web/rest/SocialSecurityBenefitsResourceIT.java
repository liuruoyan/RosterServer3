package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.SocialSecurityBenefits;
import com.cc.domain.EnumPfType;
import com.cc.domain.EnumPfStatus;
import com.cc.domain.EnumPfPayScheme;
import com.cc.domain.EnumSsPayScheme;
import com.cc.domain.EnumSsStatus;
import com.cc.domain.EnumEmpLaborType;
import com.cc.domain.EnumEmpTaxerType;
import com.cc.domain.EnumEmpTaxArea;
import com.cc.domain.Employee;
import com.cc.repository.SocialSecurityBenefitsRepository;
import com.cc.service.SocialSecurityBenefitsService;
import com.cc.service.dto.SocialSecurityBenefitsDTO;
import com.cc.service.mapper.SocialSecurityBenefitsMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.SocialSecurityBenefitsCriteria;
import com.cc.service.SocialSecurityBenefitsQueryService;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.cc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link SocialSecurityBenefitsResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class SocialSecurityBenefitsResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PF_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_PF_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SPF_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_SPF_ACCOUNT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PF_START_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PF_START_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PF_BASE = 1;
    private static final Integer UPDATED_PF_BASE = 2;

    private static final LocalDate DEFAULT_PF_STOP_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PF_STOP_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PF_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_PF_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_SS_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_SS_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SS_CITY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SS_START_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SS_START_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_SS_BASE = 1;
    private static final Integer UPDATED_SS_BASE = 2;

    private static final LocalDate DEFAULT_SS_STOP_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SS_STOP_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SS_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_SS_REMARK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ALLOWANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ALLOWANCE = new BigDecimal(2);

    private static final String DEFAULT_TAXPAYER = "AAAAAAAAAA";
    private static final String UPDATED_TAXPAYER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private SocialSecurityBenefitsRepository socialSecurityBenefitsRepository;

    @Autowired
    private SocialSecurityBenefitsMapper socialSecurityBenefitsMapper;

    @Autowired
    private SocialSecurityBenefitsService socialSecurityBenefitsService;

    @Autowired
    private SocialSecurityBenefitsQueryService socialSecurityBenefitsQueryService;

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

    private MockMvc restSocialSecurityBenefitsMockMvc;

    private SocialSecurityBenefits socialSecurityBenefits;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SocialSecurityBenefitsResource socialSecurityBenefitsResource = new SocialSecurityBenefitsResource(socialSecurityBenefitsService, socialSecurityBenefitsQueryService);
        this.restSocialSecurityBenefitsMockMvc = MockMvcBuilders.standaloneSetup(socialSecurityBenefitsResource)
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
    public static SocialSecurityBenefits createEntity(EntityManager em) {
        SocialSecurityBenefits socialSecurityBenefits = new SocialSecurityBenefits()
            .code(DEFAULT_CODE)
            .pfAccount(DEFAULT_PF_ACCOUNT)
            .spfAccount(DEFAULT_SPF_ACCOUNT)
            .pfStartMonth(DEFAULT_PF_START_MONTH)
            .pfBase(DEFAULT_PF_BASE)
            .pfStopMonth(DEFAULT_PF_STOP_MONTH)
            .pfRemark(DEFAULT_PF_REMARK)
            .ssAccount(DEFAULT_SS_ACCOUNT)
            .ssCity(DEFAULT_SS_CITY)
            .ssStartMonth(DEFAULT_SS_START_MONTH)
            .ssBase(DEFAULT_SS_BASE)
            .ssStopMonth(DEFAULT_SS_STOP_MONTH)
            .ssRemark(DEFAULT_SS_REMARK)
            .allowance(DEFAULT_ALLOWANCE)
            .taxpayer(DEFAULT_TAXPAYER)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return socialSecurityBenefits;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocialSecurityBenefits createUpdatedEntity(EntityManager em) {
        SocialSecurityBenefits socialSecurityBenefits = new SocialSecurityBenefits()
            .code(UPDATED_CODE)
            .pfAccount(UPDATED_PF_ACCOUNT)
            .spfAccount(UPDATED_SPF_ACCOUNT)
            .pfStartMonth(UPDATED_PF_START_MONTH)
            .pfBase(UPDATED_PF_BASE)
            .pfStopMonth(UPDATED_PF_STOP_MONTH)
            .pfRemark(UPDATED_PF_REMARK)
            .ssAccount(UPDATED_SS_ACCOUNT)
            .ssCity(UPDATED_SS_CITY)
            .ssStartMonth(UPDATED_SS_START_MONTH)
            .ssBase(UPDATED_SS_BASE)
            .ssStopMonth(UPDATED_SS_STOP_MONTH)
            .ssRemark(UPDATED_SS_REMARK)
            .allowance(UPDATED_ALLOWANCE)
            .taxpayer(UPDATED_TAXPAYER)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return socialSecurityBenefits;
    }

    @BeforeEach
    public void initTest() {
        socialSecurityBenefits = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocialSecurityBenefits() throws Exception {
        int databaseSizeBeforeCreate = socialSecurityBenefitsRepository.findAll().size();

        // Create the SocialSecurityBenefits
        SocialSecurityBenefitsDTO socialSecurityBenefitsDTO = socialSecurityBenefitsMapper.toDto(socialSecurityBenefits);
        restSocialSecurityBenefitsMockMvc.perform(post("/api/social-security-benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socialSecurityBenefitsDTO)))
            .andExpect(status().isCreated());

        // Validate the SocialSecurityBenefits in the database
        List<SocialSecurityBenefits> socialSecurityBenefitsList = socialSecurityBenefitsRepository.findAll();
        assertThat(socialSecurityBenefitsList).hasSize(databaseSizeBeforeCreate + 1);
        SocialSecurityBenefits testSocialSecurityBenefits = socialSecurityBenefitsList.get(socialSecurityBenefitsList.size() - 1);
        assertThat(testSocialSecurityBenefits.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSocialSecurityBenefits.getPfAccount()).isEqualTo(DEFAULT_PF_ACCOUNT);
        assertThat(testSocialSecurityBenefits.getSpfAccount()).isEqualTo(DEFAULT_SPF_ACCOUNT);
        assertThat(testSocialSecurityBenefits.getPfStartMonth()).isEqualTo(DEFAULT_PF_START_MONTH);
        assertThat(testSocialSecurityBenefits.getPfBase()).isEqualTo(DEFAULT_PF_BASE);
        assertThat(testSocialSecurityBenefits.getPfStopMonth()).isEqualTo(DEFAULT_PF_STOP_MONTH);
        assertThat(testSocialSecurityBenefits.getPfRemark()).isEqualTo(DEFAULT_PF_REMARK);
        assertThat(testSocialSecurityBenefits.getSsAccount()).isEqualTo(DEFAULT_SS_ACCOUNT);
        assertThat(testSocialSecurityBenefits.getSsCity()).isEqualTo(DEFAULT_SS_CITY);
        assertThat(testSocialSecurityBenefits.getSsStartMonth()).isEqualTo(DEFAULT_SS_START_MONTH);
        assertThat(testSocialSecurityBenefits.getSsBase()).isEqualTo(DEFAULT_SS_BASE);
        assertThat(testSocialSecurityBenefits.getSsStopMonth()).isEqualTo(DEFAULT_SS_STOP_MONTH);
        assertThat(testSocialSecurityBenefits.getSsRemark()).isEqualTo(DEFAULT_SS_REMARK);
        assertThat(testSocialSecurityBenefits.getAllowance()).isEqualTo(DEFAULT_ALLOWANCE);
        assertThat(testSocialSecurityBenefits.getTaxpayer()).isEqualTo(DEFAULT_TAXPAYER);
        assertThat(testSocialSecurityBenefits.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testSocialSecurityBenefits.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createSocialSecurityBenefitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = socialSecurityBenefitsRepository.findAll().size();

        // Create the SocialSecurityBenefits with an existing ID
        socialSecurityBenefits.setId(1L);
        SocialSecurityBenefitsDTO socialSecurityBenefitsDTO = socialSecurityBenefitsMapper.toDto(socialSecurityBenefits);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocialSecurityBenefitsMockMvc.perform(post("/api/social-security-benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socialSecurityBenefitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SocialSecurityBenefits in the database
        List<SocialSecurityBenefits> socialSecurityBenefitsList = socialSecurityBenefitsRepository.findAll();
        assertThat(socialSecurityBenefitsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefits() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList
        restSocialSecurityBenefitsMockMvc.perform(get("/api/social-security-benefits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socialSecurityBenefits.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].pfAccount").value(hasItem(DEFAULT_PF_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].spfAccount").value(hasItem(DEFAULT_SPF_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].pfStartMonth").value(hasItem(DEFAULT_PF_START_MONTH.toString())))
            .andExpect(jsonPath("$.[*].pfBase").value(hasItem(DEFAULT_PF_BASE)))
            .andExpect(jsonPath("$.[*].pfStopMonth").value(hasItem(DEFAULT_PF_STOP_MONTH.toString())))
            .andExpect(jsonPath("$.[*].pfRemark").value(hasItem(DEFAULT_PF_REMARK.toString())))
            .andExpect(jsonPath("$.[*].ssAccount").value(hasItem(DEFAULT_SS_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].ssCity").value(hasItem(DEFAULT_SS_CITY.toString())))
            .andExpect(jsonPath("$.[*].ssStartMonth").value(hasItem(DEFAULT_SS_START_MONTH.toString())))
            .andExpect(jsonPath("$.[*].ssBase").value(hasItem(DEFAULT_SS_BASE)))
            .andExpect(jsonPath("$.[*].ssStopMonth").value(hasItem(DEFAULT_SS_STOP_MONTH.toString())))
            .andExpect(jsonPath("$.[*].ssRemark").value(hasItem(DEFAULT_SS_REMARK.toString())))
            .andExpect(jsonPath("$.[*].allowance").value(hasItem(DEFAULT_ALLOWANCE.intValue())))
            .andExpect(jsonPath("$.[*].taxpayer").value(hasItem(DEFAULT_TAXPAYER.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSocialSecurityBenefits() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get the socialSecurityBenefits
        restSocialSecurityBenefitsMockMvc.perform(get("/api/social-security-benefits/{id}", socialSecurityBenefits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(socialSecurityBenefits.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.pfAccount").value(DEFAULT_PF_ACCOUNT.toString()))
            .andExpect(jsonPath("$.spfAccount").value(DEFAULT_SPF_ACCOUNT.toString()))
            .andExpect(jsonPath("$.pfStartMonth").value(DEFAULT_PF_START_MONTH.toString()))
            .andExpect(jsonPath("$.pfBase").value(DEFAULT_PF_BASE))
            .andExpect(jsonPath("$.pfStopMonth").value(DEFAULT_PF_STOP_MONTH.toString()))
            .andExpect(jsonPath("$.pfRemark").value(DEFAULT_PF_REMARK.toString()))
            .andExpect(jsonPath("$.ssAccount").value(DEFAULT_SS_ACCOUNT.toString()))
            .andExpect(jsonPath("$.ssCity").value(DEFAULT_SS_CITY.toString()))
            .andExpect(jsonPath("$.ssStartMonth").value(DEFAULT_SS_START_MONTH.toString()))
            .andExpect(jsonPath("$.ssBase").value(DEFAULT_SS_BASE))
            .andExpect(jsonPath("$.ssStopMonth").value(DEFAULT_SS_STOP_MONTH.toString()))
            .andExpect(jsonPath("$.ssRemark").value(DEFAULT_SS_REMARK.toString()))
            .andExpect(jsonPath("$.allowance").value(DEFAULT_ALLOWANCE.intValue()))
            .andExpect(jsonPath("$.taxpayer").value(DEFAULT_TAXPAYER.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where code equals to DEFAULT_CODE
        defaultSocialSecurityBenefitsShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the socialSecurityBenefitsList where code equals to UPDATED_CODE
        defaultSocialSecurityBenefitsShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where code in DEFAULT_CODE or UPDATED_CODE
        defaultSocialSecurityBenefitsShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the socialSecurityBenefitsList where code equals to UPDATED_CODE
        defaultSocialSecurityBenefitsShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where code is not null
        defaultSocialSecurityBenefitsShouldBeFound("code.specified=true");

        // Get all the socialSecurityBenefitsList where code is null
        defaultSocialSecurityBenefitsShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfAccount equals to DEFAULT_PF_ACCOUNT
        defaultSocialSecurityBenefitsShouldBeFound("pfAccount.equals=" + DEFAULT_PF_ACCOUNT);

        // Get all the socialSecurityBenefitsList where pfAccount equals to UPDATED_PF_ACCOUNT
        defaultSocialSecurityBenefitsShouldNotBeFound("pfAccount.equals=" + UPDATED_PF_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfAccountIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfAccount in DEFAULT_PF_ACCOUNT or UPDATED_PF_ACCOUNT
        defaultSocialSecurityBenefitsShouldBeFound("pfAccount.in=" + DEFAULT_PF_ACCOUNT + "," + UPDATED_PF_ACCOUNT);

        // Get all the socialSecurityBenefitsList where pfAccount equals to UPDATED_PF_ACCOUNT
        defaultSocialSecurityBenefitsShouldNotBeFound("pfAccount.in=" + UPDATED_PF_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfAccountIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfAccount is not null
        defaultSocialSecurityBenefitsShouldBeFound("pfAccount.specified=true");

        // Get all the socialSecurityBenefitsList where pfAccount is null
        defaultSocialSecurityBenefitsShouldNotBeFound("pfAccount.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySpfAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where spfAccount equals to DEFAULT_SPF_ACCOUNT
        defaultSocialSecurityBenefitsShouldBeFound("spfAccount.equals=" + DEFAULT_SPF_ACCOUNT);

        // Get all the socialSecurityBenefitsList where spfAccount equals to UPDATED_SPF_ACCOUNT
        defaultSocialSecurityBenefitsShouldNotBeFound("spfAccount.equals=" + UPDATED_SPF_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySpfAccountIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where spfAccount in DEFAULT_SPF_ACCOUNT or UPDATED_SPF_ACCOUNT
        defaultSocialSecurityBenefitsShouldBeFound("spfAccount.in=" + DEFAULT_SPF_ACCOUNT + "," + UPDATED_SPF_ACCOUNT);

        // Get all the socialSecurityBenefitsList where spfAccount equals to UPDATED_SPF_ACCOUNT
        defaultSocialSecurityBenefitsShouldNotBeFound("spfAccount.in=" + UPDATED_SPF_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySpfAccountIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where spfAccount is not null
        defaultSocialSecurityBenefitsShouldBeFound("spfAccount.specified=true");

        // Get all the socialSecurityBenefitsList where spfAccount is null
        defaultSocialSecurityBenefitsShouldNotBeFound("spfAccount.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStartMonthIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStartMonth equals to DEFAULT_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStartMonth.equals=" + DEFAULT_PF_START_MONTH);

        // Get all the socialSecurityBenefitsList where pfStartMonth equals to UPDATED_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStartMonth.equals=" + UPDATED_PF_START_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStartMonthIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStartMonth in DEFAULT_PF_START_MONTH or UPDATED_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStartMonth.in=" + DEFAULT_PF_START_MONTH + "," + UPDATED_PF_START_MONTH);

        // Get all the socialSecurityBenefitsList where pfStartMonth equals to UPDATED_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStartMonth.in=" + UPDATED_PF_START_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStartMonthIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStartMonth is not null
        defaultSocialSecurityBenefitsShouldBeFound("pfStartMonth.specified=true");

        // Get all the socialSecurityBenefitsList where pfStartMonth is null
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStartMonth.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStartMonthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStartMonth greater than or equals to DEFAULT_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStartMonth.greaterOrEqualThan=" + DEFAULT_PF_START_MONTH);

        // Get all the socialSecurityBenefitsList where pfStartMonth greater than or equals to UPDATED_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStartMonth.greaterOrEqualThan=" + UPDATED_PF_START_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStartMonthIsLessThanSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStartMonth less than or equals to DEFAULT_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStartMonth.lessThan=" + DEFAULT_PF_START_MONTH);

        // Get all the socialSecurityBenefitsList where pfStartMonth less than or equals to UPDATED_PF_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStartMonth.lessThan=" + UPDATED_PF_START_MONTH);
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfBaseIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfBase equals to DEFAULT_PF_BASE
        defaultSocialSecurityBenefitsShouldBeFound("pfBase.equals=" + DEFAULT_PF_BASE);

        // Get all the socialSecurityBenefitsList where pfBase equals to UPDATED_PF_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("pfBase.equals=" + UPDATED_PF_BASE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfBaseIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfBase in DEFAULT_PF_BASE or UPDATED_PF_BASE
        defaultSocialSecurityBenefitsShouldBeFound("pfBase.in=" + DEFAULT_PF_BASE + "," + UPDATED_PF_BASE);

        // Get all the socialSecurityBenefitsList where pfBase equals to UPDATED_PF_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("pfBase.in=" + UPDATED_PF_BASE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfBaseIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfBase is not null
        defaultSocialSecurityBenefitsShouldBeFound("pfBase.specified=true");

        // Get all the socialSecurityBenefitsList where pfBase is null
        defaultSocialSecurityBenefitsShouldNotBeFound("pfBase.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfBaseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfBase greater than or equals to DEFAULT_PF_BASE
        defaultSocialSecurityBenefitsShouldBeFound("pfBase.greaterOrEqualThan=" + DEFAULT_PF_BASE);

        // Get all the socialSecurityBenefitsList where pfBase greater than or equals to UPDATED_PF_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("pfBase.greaterOrEqualThan=" + UPDATED_PF_BASE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfBaseIsLessThanSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfBase less than or equals to DEFAULT_PF_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("pfBase.lessThan=" + DEFAULT_PF_BASE);

        // Get all the socialSecurityBenefitsList where pfBase less than or equals to UPDATED_PF_BASE
        defaultSocialSecurityBenefitsShouldBeFound("pfBase.lessThan=" + UPDATED_PF_BASE);
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStopMonthIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStopMonth equals to DEFAULT_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStopMonth.equals=" + DEFAULT_PF_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where pfStopMonth equals to UPDATED_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStopMonth.equals=" + UPDATED_PF_STOP_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStopMonthIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStopMonth in DEFAULT_PF_STOP_MONTH or UPDATED_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStopMonth.in=" + DEFAULT_PF_STOP_MONTH + "," + UPDATED_PF_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where pfStopMonth equals to UPDATED_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStopMonth.in=" + UPDATED_PF_STOP_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStopMonthIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStopMonth is not null
        defaultSocialSecurityBenefitsShouldBeFound("pfStopMonth.specified=true");

        // Get all the socialSecurityBenefitsList where pfStopMonth is null
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStopMonth.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStopMonthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStopMonth greater than or equals to DEFAULT_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStopMonth.greaterOrEqualThan=" + DEFAULT_PF_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where pfStopMonth greater than or equals to UPDATED_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStopMonth.greaterOrEqualThan=" + UPDATED_PF_STOP_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStopMonthIsLessThanSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfStopMonth less than or equals to DEFAULT_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStopMonth.lessThan=" + DEFAULT_PF_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where pfStopMonth less than or equals to UPDATED_PF_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("pfStopMonth.lessThan=" + UPDATED_PF_STOP_MONTH);
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfRemarkIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfRemark equals to DEFAULT_PF_REMARK
        defaultSocialSecurityBenefitsShouldBeFound("pfRemark.equals=" + DEFAULT_PF_REMARK);

        // Get all the socialSecurityBenefitsList where pfRemark equals to UPDATED_PF_REMARK
        defaultSocialSecurityBenefitsShouldNotBeFound("pfRemark.equals=" + UPDATED_PF_REMARK);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfRemarkIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfRemark in DEFAULT_PF_REMARK or UPDATED_PF_REMARK
        defaultSocialSecurityBenefitsShouldBeFound("pfRemark.in=" + DEFAULT_PF_REMARK + "," + UPDATED_PF_REMARK);

        // Get all the socialSecurityBenefitsList where pfRemark equals to UPDATED_PF_REMARK
        defaultSocialSecurityBenefitsShouldNotBeFound("pfRemark.in=" + UPDATED_PF_REMARK);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfRemarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where pfRemark is not null
        defaultSocialSecurityBenefitsShouldBeFound("pfRemark.specified=true");

        // Get all the socialSecurityBenefitsList where pfRemark is null
        defaultSocialSecurityBenefitsShouldNotBeFound("pfRemark.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssAccount equals to DEFAULT_SS_ACCOUNT
        defaultSocialSecurityBenefitsShouldBeFound("ssAccount.equals=" + DEFAULT_SS_ACCOUNT);

        // Get all the socialSecurityBenefitsList where ssAccount equals to UPDATED_SS_ACCOUNT
        defaultSocialSecurityBenefitsShouldNotBeFound("ssAccount.equals=" + UPDATED_SS_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsAccountIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssAccount in DEFAULT_SS_ACCOUNT or UPDATED_SS_ACCOUNT
        defaultSocialSecurityBenefitsShouldBeFound("ssAccount.in=" + DEFAULT_SS_ACCOUNT + "," + UPDATED_SS_ACCOUNT);

        // Get all the socialSecurityBenefitsList where ssAccount equals to UPDATED_SS_ACCOUNT
        defaultSocialSecurityBenefitsShouldNotBeFound("ssAccount.in=" + UPDATED_SS_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsAccountIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssAccount is not null
        defaultSocialSecurityBenefitsShouldBeFound("ssAccount.specified=true");

        // Get all the socialSecurityBenefitsList where ssAccount is null
        defaultSocialSecurityBenefitsShouldNotBeFound("ssAccount.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsCityIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssCity equals to DEFAULT_SS_CITY
        defaultSocialSecurityBenefitsShouldBeFound("ssCity.equals=" + DEFAULT_SS_CITY);

        // Get all the socialSecurityBenefitsList where ssCity equals to UPDATED_SS_CITY
        defaultSocialSecurityBenefitsShouldNotBeFound("ssCity.equals=" + UPDATED_SS_CITY);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsCityIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssCity in DEFAULT_SS_CITY or UPDATED_SS_CITY
        defaultSocialSecurityBenefitsShouldBeFound("ssCity.in=" + DEFAULT_SS_CITY + "," + UPDATED_SS_CITY);

        // Get all the socialSecurityBenefitsList where ssCity equals to UPDATED_SS_CITY
        defaultSocialSecurityBenefitsShouldNotBeFound("ssCity.in=" + UPDATED_SS_CITY);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssCity is not null
        defaultSocialSecurityBenefitsShouldBeFound("ssCity.specified=true");

        // Get all the socialSecurityBenefitsList where ssCity is null
        defaultSocialSecurityBenefitsShouldNotBeFound("ssCity.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStartMonthIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStartMonth equals to DEFAULT_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStartMonth.equals=" + DEFAULT_SS_START_MONTH);

        // Get all the socialSecurityBenefitsList where ssStartMonth equals to UPDATED_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStartMonth.equals=" + UPDATED_SS_START_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStartMonthIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStartMonth in DEFAULT_SS_START_MONTH or UPDATED_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStartMonth.in=" + DEFAULT_SS_START_MONTH + "," + UPDATED_SS_START_MONTH);

        // Get all the socialSecurityBenefitsList where ssStartMonth equals to UPDATED_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStartMonth.in=" + UPDATED_SS_START_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStartMonthIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStartMonth is not null
        defaultSocialSecurityBenefitsShouldBeFound("ssStartMonth.specified=true");

        // Get all the socialSecurityBenefitsList where ssStartMonth is null
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStartMonth.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStartMonthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStartMonth greater than or equals to DEFAULT_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStartMonth.greaterOrEqualThan=" + DEFAULT_SS_START_MONTH);

        // Get all the socialSecurityBenefitsList where ssStartMonth greater than or equals to UPDATED_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStartMonth.greaterOrEqualThan=" + UPDATED_SS_START_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStartMonthIsLessThanSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStartMonth less than or equals to DEFAULT_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStartMonth.lessThan=" + DEFAULT_SS_START_MONTH);

        // Get all the socialSecurityBenefitsList where ssStartMonth less than or equals to UPDATED_SS_START_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStartMonth.lessThan=" + UPDATED_SS_START_MONTH);
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsBaseIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssBase equals to DEFAULT_SS_BASE
        defaultSocialSecurityBenefitsShouldBeFound("ssBase.equals=" + DEFAULT_SS_BASE);

        // Get all the socialSecurityBenefitsList where ssBase equals to UPDATED_SS_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("ssBase.equals=" + UPDATED_SS_BASE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsBaseIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssBase in DEFAULT_SS_BASE or UPDATED_SS_BASE
        defaultSocialSecurityBenefitsShouldBeFound("ssBase.in=" + DEFAULT_SS_BASE + "," + UPDATED_SS_BASE);

        // Get all the socialSecurityBenefitsList where ssBase equals to UPDATED_SS_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("ssBase.in=" + UPDATED_SS_BASE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsBaseIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssBase is not null
        defaultSocialSecurityBenefitsShouldBeFound("ssBase.specified=true");

        // Get all the socialSecurityBenefitsList where ssBase is null
        defaultSocialSecurityBenefitsShouldNotBeFound("ssBase.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsBaseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssBase greater than or equals to DEFAULT_SS_BASE
        defaultSocialSecurityBenefitsShouldBeFound("ssBase.greaterOrEqualThan=" + DEFAULT_SS_BASE);

        // Get all the socialSecurityBenefitsList where ssBase greater than or equals to UPDATED_SS_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("ssBase.greaterOrEqualThan=" + UPDATED_SS_BASE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsBaseIsLessThanSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssBase less than or equals to DEFAULT_SS_BASE
        defaultSocialSecurityBenefitsShouldNotBeFound("ssBase.lessThan=" + DEFAULT_SS_BASE);

        // Get all the socialSecurityBenefitsList where ssBase less than or equals to UPDATED_SS_BASE
        defaultSocialSecurityBenefitsShouldBeFound("ssBase.lessThan=" + UPDATED_SS_BASE);
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStopMonthIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStopMonth equals to DEFAULT_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStopMonth.equals=" + DEFAULT_SS_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where ssStopMonth equals to UPDATED_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStopMonth.equals=" + UPDATED_SS_STOP_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStopMonthIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStopMonth in DEFAULT_SS_STOP_MONTH or UPDATED_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStopMonth.in=" + DEFAULT_SS_STOP_MONTH + "," + UPDATED_SS_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where ssStopMonth equals to UPDATED_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStopMonth.in=" + UPDATED_SS_STOP_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStopMonthIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStopMonth is not null
        defaultSocialSecurityBenefitsShouldBeFound("ssStopMonth.specified=true");

        // Get all the socialSecurityBenefitsList where ssStopMonth is null
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStopMonth.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStopMonthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStopMonth greater than or equals to DEFAULT_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStopMonth.greaterOrEqualThan=" + DEFAULT_SS_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where ssStopMonth greater than or equals to UPDATED_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStopMonth.greaterOrEqualThan=" + UPDATED_SS_STOP_MONTH);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStopMonthIsLessThanSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssStopMonth less than or equals to DEFAULT_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStopMonth.lessThan=" + DEFAULT_SS_STOP_MONTH);

        // Get all the socialSecurityBenefitsList where ssStopMonth less than or equals to UPDATED_SS_STOP_MONTH
        defaultSocialSecurityBenefitsShouldBeFound("ssStopMonth.lessThan=" + UPDATED_SS_STOP_MONTH);
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsRemarkIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssRemark equals to DEFAULT_SS_REMARK
        defaultSocialSecurityBenefitsShouldBeFound("ssRemark.equals=" + DEFAULT_SS_REMARK);

        // Get all the socialSecurityBenefitsList where ssRemark equals to UPDATED_SS_REMARK
        defaultSocialSecurityBenefitsShouldNotBeFound("ssRemark.equals=" + UPDATED_SS_REMARK);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsRemarkIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssRemark in DEFAULT_SS_REMARK or UPDATED_SS_REMARK
        defaultSocialSecurityBenefitsShouldBeFound("ssRemark.in=" + DEFAULT_SS_REMARK + "," + UPDATED_SS_REMARK);

        // Get all the socialSecurityBenefitsList where ssRemark equals to UPDATED_SS_REMARK
        defaultSocialSecurityBenefitsShouldNotBeFound("ssRemark.in=" + UPDATED_SS_REMARK);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsRemarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where ssRemark is not null
        defaultSocialSecurityBenefitsShouldBeFound("ssRemark.specified=true");

        // Get all the socialSecurityBenefitsList where ssRemark is null
        defaultSocialSecurityBenefitsShouldNotBeFound("ssRemark.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where allowance equals to DEFAULT_ALLOWANCE
        defaultSocialSecurityBenefitsShouldBeFound("allowance.equals=" + DEFAULT_ALLOWANCE);

        // Get all the socialSecurityBenefitsList where allowance equals to UPDATED_ALLOWANCE
        defaultSocialSecurityBenefitsShouldNotBeFound("allowance.equals=" + UPDATED_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where allowance in DEFAULT_ALLOWANCE or UPDATED_ALLOWANCE
        defaultSocialSecurityBenefitsShouldBeFound("allowance.in=" + DEFAULT_ALLOWANCE + "," + UPDATED_ALLOWANCE);

        // Get all the socialSecurityBenefitsList where allowance equals to UPDATED_ALLOWANCE
        defaultSocialSecurityBenefitsShouldNotBeFound("allowance.in=" + UPDATED_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where allowance is not null
        defaultSocialSecurityBenefitsShouldBeFound("allowance.specified=true");

        // Get all the socialSecurityBenefitsList where allowance is null
        defaultSocialSecurityBenefitsShouldNotBeFound("allowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByTaxpayerIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where taxpayer equals to DEFAULT_TAXPAYER
        defaultSocialSecurityBenefitsShouldBeFound("taxpayer.equals=" + DEFAULT_TAXPAYER);

        // Get all the socialSecurityBenefitsList where taxpayer equals to UPDATED_TAXPAYER
        defaultSocialSecurityBenefitsShouldNotBeFound("taxpayer.equals=" + UPDATED_TAXPAYER);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByTaxpayerIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where taxpayer in DEFAULT_TAXPAYER or UPDATED_TAXPAYER
        defaultSocialSecurityBenefitsShouldBeFound("taxpayer.in=" + DEFAULT_TAXPAYER + "," + UPDATED_TAXPAYER);

        // Get all the socialSecurityBenefitsList where taxpayer equals to UPDATED_TAXPAYER
        defaultSocialSecurityBenefitsShouldNotBeFound("taxpayer.in=" + UPDATED_TAXPAYER);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByTaxpayerIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where taxpayer is not null
        defaultSocialSecurityBenefitsShouldBeFound("taxpayer.specified=true");

        // Get all the socialSecurityBenefitsList where taxpayer is null
        defaultSocialSecurityBenefitsShouldNotBeFound("taxpayer.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultSocialSecurityBenefitsShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the socialSecurityBenefitsList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultSocialSecurityBenefitsShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultSocialSecurityBenefitsShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the socialSecurityBenefitsList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultSocialSecurityBenefitsShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where isSelfVerify is not null
        defaultSocialSecurityBenefitsShouldBeFound("isSelfVerify.specified=true");

        // Get all the socialSecurityBenefitsList where isSelfVerify is null
        defaultSocialSecurityBenefitsShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultSocialSecurityBenefitsShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the socialSecurityBenefitsList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultSocialSecurityBenefitsShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultSocialSecurityBenefitsShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the socialSecurityBenefitsList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultSocialSecurityBenefitsShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        // Get all the socialSecurityBenefitsList where isHrVerify is not null
        defaultSocialSecurityBenefitsShouldBeFound("isHrVerify.specified=true");

        // Get all the socialSecurityBenefitsList where isHrVerify is null
        defaultSocialSecurityBenefitsShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPfType pfType = EnumPfTypeResourceIT.createEntity(em);
        em.persist(pfType);
        em.flush();
        socialSecurityBenefits.setPfType(pfType);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long pfTypeId = pfType.getId();

        // Get all the socialSecurityBenefitsList where pfType equals to pfTypeId
        defaultSocialSecurityBenefitsShouldBeFound("pfTypeId.equals=" + pfTypeId);

        // Get all the socialSecurityBenefitsList where pfType equals to pfTypeId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("pfTypeId.equals=" + (pfTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByPfStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPfStatus pfStatus = EnumPfStatusResourceIT.createEntity(em);
        em.persist(pfStatus);
        em.flush();
        socialSecurityBenefits.setPfStatus(pfStatus);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long pfStatusId = pfStatus.getId();

        // Get all the socialSecurityBenefitsList where pfStatus equals to pfStatusId
        defaultSocialSecurityBenefitsShouldBeFound("pfStatusId.equals=" + pfStatusId);

        // Get all the socialSecurityBenefitsList where pfStatus equals to pfStatusId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("pfStatusId.equals=" + (pfStatusId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByProvidentPaySchemeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPfPayScheme providentPayScheme = EnumPfPaySchemeResourceIT.createEntity(em);
        em.persist(providentPayScheme);
        em.flush();
        socialSecurityBenefits.setProvidentPayScheme(providentPayScheme);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long providentPaySchemeId = providentPayScheme.getId();

        // Get all the socialSecurityBenefitsList where providentPayScheme equals to providentPaySchemeId
        defaultSocialSecurityBenefitsShouldBeFound("providentPaySchemeId.equals=" + providentPaySchemeId);

        // Get all the socialSecurityBenefitsList where providentPayScheme equals to providentPaySchemeId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("providentPaySchemeId.equals=" + (providentPaySchemeId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySocialSecurityPaySchemeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumSsPayScheme socialSecurityPayScheme = EnumSsPaySchemeResourceIT.createEntity(em);
        em.persist(socialSecurityPayScheme);
        em.flush();
        socialSecurityBenefits.setSocialSecurityPayScheme(socialSecurityPayScheme);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long socialSecurityPaySchemeId = socialSecurityPayScheme.getId();

        // Get all the socialSecurityBenefitsList where socialSecurityPayScheme equals to socialSecurityPaySchemeId
        defaultSocialSecurityBenefitsShouldBeFound("socialSecurityPaySchemeId.equals=" + socialSecurityPaySchemeId);

        // Get all the socialSecurityBenefitsList where socialSecurityPayScheme equals to socialSecurityPaySchemeId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("socialSecurityPaySchemeId.equals=" + (socialSecurityPaySchemeId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsBySsStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumSsStatus ssStatus = EnumSsStatusResourceIT.createEntity(em);
        em.persist(ssStatus);
        em.flush();
        socialSecurityBenefits.setSsStatus(ssStatus);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long ssStatusId = ssStatus.getId();

        // Get all the socialSecurityBenefitsList where ssStatus equals to ssStatusId
        defaultSocialSecurityBenefitsShouldBeFound("ssStatusId.equals=" + ssStatusId);

        // Get all the socialSecurityBenefitsList where ssStatus equals to ssStatusId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("ssStatusId.equals=" + (ssStatusId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByLaborTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpLaborType laborType = EnumEmpLaborTypeResourceIT.createEntity(em);
        em.persist(laborType);
        em.flush();
        socialSecurityBenefits.setLaborType(laborType);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long laborTypeId = laborType.getId();

        // Get all the socialSecurityBenefitsList where laborType equals to laborTypeId
        defaultSocialSecurityBenefitsShouldBeFound("laborTypeId.equals=" + laborTypeId);

        // Get all the socialSecurityBenefitsList where laborType equals to laborTypeId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("laborTypeId.equals=" + (laborTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByTaxerTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpTaxerType taxerType = EnumEmpTaxerTypeResourceIT.createEntity(em);
        em.persist(taxerType);
        em.flush();
        socialSecurityBenefits.setTaxerType(taxerType);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long taxerTypeId = taxerType.getId();

        // Get all the socialSecurityBenefitsList where taxerType equals to taxerTypeId
        defaultSocialSecurityBenefitsShouldBeFound("taxerTypeId.equals=" + taxerTypeId);

        // Get all the socialSecurityBenefitsList where taxerType equals to taxerTypeId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("taxerTypeId.equals=" + (taxerTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByTaxAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpTaxArea taxArea = EnumEmpTaxAreaResourceIT.createEntity(em);
        em.persist(taxArea);
        em.flush();
        socialSecurityBenefits.setTaxArea(taxArea);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long taxAreaId = taxArea.getId();

        // Get all the socialSecurityBenefitsList where taxArea equals to taxAreaId
        defaultSocialSecurityBenefitsShouldBeFound("taxAreaId.equals=" + taxAreaId);

        // Get all the socialSecurityBenefitsList where taxArea equals to taxAreaId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("taxAreaId.equals=" + (taxAreaId + 1));
    }


    @Test
    @Transactional
    public void getAllSocialSecurityBenefitsByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        socialSecurityBenefits.setEmp(emp);
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);
        Long empId = emp.getId();

        // Get all the socialSecurityBenefitsList where emp equals to empId
        defaultSocialSecurityBenefitsShouldBeFound("empId.equals=" + empId);

        // Get all the socialSecurityBenefitsList where emp equals to empId + 1
        defaultSocialSecurityBenefitsShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSocialSecurityBenefitsShouldBeFound(String filter) throws Exception {
        restSocialSecurityBenefitsMockMvc.perform(get("/api/social-security-benefits?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socialSecurityBenefits.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].pfAccount").value(hasItem(DEFAULT_PF_ACCOUNT)))
            .andExpect(jsonPath("$.[*].spfAccount").value(hasItem(DEFAULT_SPF_ACCOUNT)))
            .andExpect(jsonPath("$.[*].pfStartMonth").value(hasItem(DEFAULT_PF_START_MONTH.toString())))
            .andExpect(jsonPath("$.[*].pfBase").value(hasItem(DEFAULT_PF_BASE)))
            .andExpect(jsonPath("$.[*].pfStopMonth").value(hasItem(DEFAULT_PF_STOP_MONTH.toString())))
            .andExpect(jsonPath("$.[*].pfRemark").value(hasItem(DEFAULT_PF_REMARK)))
            .andExpect(jsonPath("$.[*].ssAccount").value(hasItem(DEFAULT_SS_ACCOUNT)))
            .andExpect(jsonPath("$.[*].ssCity").value(hasItem(DEFAULT_SS_CITY)))
            .andExpect(jsonPath("$.[*].ssStartMonth").value(hasItem(DEFAULT_SS_START_MONTH.toString())))
            .andExpect(jsonPath("$.[*].ssBase").value(hasItem(DEFAULT_SS_BASE)))
            .andExpect(jsonPath("$.[*].ssStopMonth").value(hasItem(DEFAULT_SS_STOP_MONTH.toString())))
            .andExpect(jsonPath("$.[*].ssRemark").value(hasItem(DEFAULT_SS_REMARK)))
            .andExpect(jsonPath("$.[*].allowance").value(hasItem(DEFAULT_ALLOWANCE.intValue())))
            .andExpect(jsonPath("$.[*].taxpayer").value(hasItem(DEFAULT_TAXPAYER)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restSocialSecurityBenefitsMockMvc.perform(get("/api/social-security-benefits/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSocialSecurityBenefitsShouldNotBeFound(String filter) throws Exception {
        restSocialSecurityBenefitsMockMvc.perform(get("/api/social-security-benefits?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSocialSecurityBenefitsMockMvc.perform(get("/api/social-security-benefits/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSocialSecurityBenefits() throws Exception {
        // Get the socialSecurityBenefits
        restSocialSecurityBenefitsMockMvc.perform(get("/api/social-security-benefits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSocialSecurityBenefits() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        int databaseSizeBeforeUpdate = socialSecurityBenefitsRepository.findAll().size();

        // Update the socialSecurityBenefits
        SocialSecurityBenefits updatedSocialSecurityBenefits = socialSecurityBenefitsRepository.findById(socialSecurityBenefits.getId()).get();
        // Disconnect from session so that the updates on updatedSocialSecurityBenefits are not directly saved in db
        em.detach(updatedSocialSecurityBenefits);
        updatedSocialSecurityBenefits
            .code(UPDATED_CODE)
            .pfAccount(UPDATED_PF_ACCOUNT)
            .spfAccount(UPDATED_SPF_ACCOUNT)
            .pfStartMonth(UPDATED_PF_START_MONTH)
            .pfBase(UPDATED_PF_BASE)
            .pfStopMonth(UPDATED_PF_STOP_MONTH)
            .pfRemark(UPDATED_PF_REMARK)
            .ssAccount(UPDATED_SS_ACCOUNT)
            .ssCity(UPDATED_SS_CITY)
            .ssStartMonth(UPDATED_SS_START_MONTH)
            .ssBase(UPDATED_SS_BASE)
            .ssStopMonth(UPDATED_SS_STOP_MONTH)
            .ssRemark(UPDATED_SS_REMARK)
            .allowance(UPDATED_ALLOWANCE)
            .taxpayer(UPDATED_TAXPAYER)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        SocialSecurityBenefitsDTO socialSecurityBenefitsDTO = socialSecurityBenefitsMapper.toDto(updatedSocialSecurityBenefits);

        restSocialSecurityBenefitsMockMvc.perform(put("/api/social-security-benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socialSecurityBenefitsDTO)))
            .andExpect(status().isOk());

        // Validate the SocialSecurityBenefits in the database
        List<SocialSecurityBenefits> socialSecurityBenefitsList = socialSecurityBenefitsRepository.findAll();
        assertThat(socialSecurityBenefitsList).hasSize(databaseSizeBeforeUpdate);
        SocialSecurityBenefits testSocialSecurityBenefits = socialSecurityBenefitsList.get(socialSecurityBenefitsList.size() - 1);
        assertThat(testSocialSecurityBenefits.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSocialSecurityBenefits.getPfAccount()).isEqualTo(UPDATED_PF_ACCOUNT);
        assertThat(testSocialSecurityBenefits.getSpfAccount()).isEqualTo(UPDATED_SPF_ACCOUNT);
        assertThat(testSocialSecurityBenefits.getPfStartMonth()).isEqualTo(UPDATED_PF_START_MONTH);
        assertThat(testSocialSecurityBenefits.getPfBase()).isEqualTo(UPDATED_PF_BASE);
        assertThat(testSocialSecurityBenefits.getPfStopMonth()).isEqualTo(UPDATED_PF_STOP_MONTH);
        assertThat(testSocialSecurityBenefits.getPfRemark()).isEqualTo(UPDATED_PF_REMARK);
        assertThat(testSocialSecurityBenefits.getSsAccount()).isEqualTo(UPDATED_SS_ACCOUNT);
        assertThat(testSocialSecurityBenefits.getSsCity()).isEqualTo(UPDATED_SS_CITY);
        assertThat(testSocialSecurityBenefits.getSsStartMonth()).isEqualTo(UPDATED_SS_START_MONTH);
        assertThat(testSocialSecurityBenefits.getSsBase()).isEqualTo(UPDATED_SS_BASE);
        assertThat(testSocialSecurityBenefits.getSsStopMonth()).isEqualTo(UPDATED_SS_STOP_MONTH);
        assertThat(testSocialSecurityBenefits.getSsRemark()).isEqualTo(UPDATED_SS_REMARK);
        assertThat(testSocialSecurityBenefits.getAllowance()).isEqualTo(UPDATED_ALLOWANCE);
        assertThat(testSocialSecurityBenefits.getTaxpayer()).isEqualTo(UPDATED_TAXPAYER);
        assertThat(testSocialSecurityBenefits.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testSocialSecurityBenefits.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingSocialSecurityBenefits() throws Exception {
        int databaseSizeBeforeUpdate = socialSecurityBenefitsRepository.findAll().size();

        // Create the SocialSecurityBenefits
        SocialSecurityBenefitsDTO socialSecurityBenefitsDTO = socialSecurityBenefitsMapper.toDto(socialSecurityBenefits);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocialSecurityBenefitsMockMvc.perform(put("/api/social-security-benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socialSecurityBenefitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SocialSecurityBenefits in the database
        List<SocialSecurityBenefits> socialSecurityBenefitsList = socialSecurityBenefitsRepository.findAll();
        assertThat(socialSecurityBenefitsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSocialSecurityBenefits() throws Exception {
        // Initialize the database
        socialSecurityBenefitsRepository.saveAndFlush(socialSecurityBenefits);

        int databaseSizeBeforeDelete = socialSecurityBenefitsRepository.findAll().size();

        // Delete the socialSecurityBenefits
        restSocialSecurityBenefitsMockMvc.perform(delete("/api/social-security-benefits/{id}", socialSecurityBenefits.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SocialSecurityBenefits> socialSecurityBenefitsList = socialSecurityBenefitsRepository.findAll();
        assertThat(socialSecurityBenefitsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialSecurityBenefits.class);
        SocialSecurityBenefits socialSecurityBenefits1 = new SocialSecurityBenefits();
        socialSecurityBenefits1.setId(1L);
        SocialSecurityBenefits socialSecurityBenefits2 = new SocialSecurityBenefits();
        socialSecurityBenefits2.setId(socialSecurityBenefits1.getId());
        assertThat(socialSecurityBenefits1).isEqualTo(socialSecurityBenefits2);
        socialSecurityBenefits2.setId(2L);
        assertThat(socialSecurityBenefits1).isNotEqualTo(socialSecurityBenefits2);
        socialSecurityBenefits1.setId(null);
        assertThat(socialSecurityBenefits1).isNotEqualTo(socialSecurityBenefits2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialSecurityBenefitsDTO.class);
        SocialSecurityBenefitsDTO socialSecurityBenefitsDTO1 = new SocialSecurityBenefitsDTO();
        socialSecurityBenefitsDTO1.setId(1L);
        SocialSecurityBenefitsDTO socialSecurityBenefitsDTO2 = new SocialSecurityBenefitsDTO();
        assertThat(socialSecurityBenefitsDTO1).isNotEqualTo(socialSecurityBenefitsDTO2);
        socialSecurityBenefitsDTO2.setId(socialSecurityBenefitsDTO1.getId());
        assertThat(socialSecurityBenefitsDTO1).isEqualTo(socialSecurityBenefitsDTO2);
        socialSecurityBenefitsDTO2.setId(2L);
        assertThat(socialSecurityBenefitsDTO1).isNotEqualTo(socialSecurityBenefitsDTO2);
        socialSecurityBenefitsDTO1.setId(null);
        assertThat(socialSecurityBenefitsDTO1).isNotEqualTo(socialSecurityBenefitsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(socialSecurityBenefitsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(socialSecurityBenefitsMapper.fromId(null)).isNull();
    }
}
