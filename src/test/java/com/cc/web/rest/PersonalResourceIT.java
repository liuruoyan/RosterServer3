package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.Personal;
import com.cc.domain.EnumAccountType;
import com.cc.domain.EnumHighestEducation;
import com.cc.domain.EnumPoliticsStatus;
import com.cc.domain.EnumMaritalStatus;
import com.cc.domain.Employee;
import com.cc.repository.PersonalRepository;
import com.cc.service.PersonalService;
import com.cc.service.dto.PersonalDTO;
import com.cc.service.mapper.PersonalMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.PersonalCriteria;
import com.cc.service.PersonalQueryService;

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

import com.cc.domain.enumeration.BloodType;
/**
 * Integration tests for the {@Link PersonalResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class PersonalResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ID_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NATION = "AAAAAAAAAA";
    private static final String UPDATED_NATION = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_LOC = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_LOC = "BBBBBBBBBB";

    private static final String DEFAULT_NATIVE_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_NATIVE_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_SPOUSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHILD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHILD_NAME = "BBBBBBBBBB";

    private static final BloodType DEFAULT_BLOOD_TYPE = BloodType.A;
    private static final BloodType UPDATED_BLOOD_TYPE = BloodType.B;

    private static final String DEFAULT_EMERGENCY_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_QQ = "AAAAAAAAAA";
    private static final String UPDATED_QQ = "BBBBBBBBBB";

    private static final String DEFAULT_WECHAT = "AAAAAAAAAA";
    private static final String UPDATED_WECHAT = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private PersonalQueryService personalQueryService;

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

    private MockMvc restPersonalMockMvc;

    private Personal personal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonalResource personalResource = new PersonalResource(personalService, personalQueryService);
        this.restPersonalMockMvc = MockMvcBuilders.standaloneSetup(personalResource)
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
    public static Personal createEntity(EntityManager em) {
        Personal personal = new Personal()
            .code(DEFAULT_CODE)
            .stageName(DEFAULT_STAGE_NAME)
            .idName(DEFAULT_ID_NAME)
            .nation(DEFAULT_NATION)
            .accountLoc(DEFAULT_ACCOUNT_LOC)
            .nativePlace(DEFAULT_NATIVE_PLACE)
            .currentAddr(DEFAULT_CURRENT_ADDR)
            .spouseName(DEFAULT_SPOUSE_NAME)
            .childName(DEFAULT_CHILD_NAME)
            .bloodType(DEFAULT_BLOOD_TYPE)
            .emergencyContactName(DEFAULT_EMERGENCY_CONTACT_NAME)
            .emergencyContactPhone(DEFAULT_EMERGENCY_CONTACT_PHONE)
            .qq(DEFAULT_QQ)
            .wechat(DEFAULT_WECHAT)
            .personalEmail(DEFAULT_PERSONAL_EMAIL)
            .remark(DEFAULT_REMARK)
            .others(DEFAULT_OTHERS)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return personal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personal createUpdatedEntity(EntityManager em) {
        Personal personal = new Personal()
            .code(UPDATED_CODE)
            .stageName(UPDATED_STAGE_NAME)
            .idName(UPDATED_ID_NAME)
            .nation(UPDATED_NATION)
            .accountLoc(UPDATED_ACCOUNT_LOC)
            .nativePlace(UPDATED_NATIVE_PLACE)
            .currentAddr(UPDATED_CURRENT_ADDR)
            .spouseName(UPDATED_SPOUSE_NAME)
            .childName(UPDATED_CHILD_NAME)
            .bloodType(UPDATED_BLOOD_TYPE)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactPhone(UPDATED_EMERGENCY_CONTACT_PHONE)
            .qq(UPDATED_QQ)
            .wechat(UPDATED_WECHAT)
            .personalEmail(UPDATED_PERSONAL_EMAIL)
            .remark(UPDATED_REMARK)
            .others(UPDATED_OTHERS)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return personal;
    }

    @BeforeEach
    public void initTest() {
        personal = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonal() throws Exception {
        int databaseSizeBeforeCreate = personalRepository.findAll().size();

        // Create the Personal
        PersonalDTO personalDTO = personalMapper.toDto(personal);
        restPersonalMockMvc.perform(post("/api/personals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDTO)))
            .andExpect(status().isCreated());

        // Validate the Personal in the database
        List<Personal> personalList = personalRepository.findAll();
        assertThat(personalList).hasSize(databaseSizeBeforeCreate + 1);
        Personal testPersonal = personalList.get(personalList.size() - 1);
        assertThat(testPersonal.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPersonal.getStageName()).isEqualTo(DEFAULT_STAGE_NAME);
        assertThat(testPersonal.getIdName()).isEqualTo(DEFAULT_ID_NAME);
        assertThat(testPersonal.getNation()).isEqualTo(DEFAULT_NATION);
        assertThat(testPersonal.getAccountLoc()).isEqualTo(DEFAULT_ACCOUNT_LOC);
        assertThat(testPersonal.getNativePlace()).isEqualTo(DEFAULT_NATIVE_PLACE);
        assertThat(testPersonal.getCurrentAddr()).isEqualTo(DEFAULT_CURRENT_ADDR);
        assertThat(testPersonal.getSpouseName()).isEqualTo(DEFAULT_SPOUSE_NAME);
        assertThat(testPersonal.getChildName()).isEqualTo(DEFAULT_CHILD_NAME);
        assertThat(testPersonal.getBloodType()).isEqualTo(DEFAULT_BLOOD_TYPE);
        assertThat(testPersonal.getEmergencyContactName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NAME);
        assertThat(testPersonal.getEmergencyContactPhone()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_PHONE);
        assertThat(testPersonal.getQq()).isEqualTo(DEFAULT_QQ);
        assertThat(testPersonal.getWechat()).isEqualTo(DEFAULT_WECHAT);
        assertThat(testPersonal.getPersonalEmail()).isEqualTo(DEFAULT_PERSONAL_EMAIL);
        assertThat(testPersonal.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testPersonal.getOthers()).isEqualTo(DEFAULT_OTHERS);
        assertThat(testPersonal.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testPersonal.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createPersonalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personalRepository.findAll().size();

        // Create the Personal with an existing ID
        personal.setId(1L);
        PersonalDTO personalDTO = personalMapper.toDto(personal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonalMockMvc.perform(post("/api/personals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Personal in the database
        List<Personal> personalList = personalRepository.findAll();
        assertThat(personalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersonals() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList
        restPersonalMockMvc.perform(get("/api/personals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personal.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].stageName").value(hasItem(DEFAULT_STAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].idName").value(hasItem(DEFAULT_ID_NAME.toString())))
            .andExpect(jsonPath("$.[*].nation").value(hasItem(DEFAULT_NATION.toString())))
            .andExpect(jsonPath("$.[*].accountLoc").value(hasItem(DEFAULT_ACCOUNT_LOC.toString())))
            .andExpect(jsonPath("$.[*].nativePlace").value(hasItem(DEFAULT_NATIVE_PLACE.toString())))
            .andExpect(jsonPath("$.[*].currentAddr").value(hasItem(DEFAULT_CURRENT_ADDR.toString())))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME.toString())))
            .andExpect(jsonPath("$.[*].childName").value(hasItem(DEFAULT_CHILD_NAME.toString())))
            .andExpect(jsonPath("$.[*].bloodType").value(hasItem(DEFAULT_BLOOD_TYPE.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactPhone").value(hasItem(DEFAULT_EMERGENCY_CONTACT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].qq").value(hasItem(DEFAULT_QQ.toString())))
            .andExpect(jsonPath("$.[*].wechat").value(hasItem(DEFAULT_WECHAT.toString())))
            .andExpect(jsonPath("$.[*].personalEmail").value(hasItem(DEFAULT_PERSONAL_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].others").value(hasItem(DEFAULT_OTHERS.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPersonal() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get the personal
        restPersonalMockMvc.perform(get("/api/personals/{id}", personal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(personal.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.stageName").value(DEFAULT_STAGE_NAME.toString()))
            .andExpect(jsonPath("$.idName").value(DEFAULT_ID_NAME.toString()))
            .andExpect(jsonPath("$.nation").value(DEFAULT_NATION.toString()))
            .andExpect(jsonPath("$.accountLoc").value(DEFAULT_ACCOUNT_LOC.toString()))
            .andExpect(jsonPath("$.nativePlace").value(DEFAULT_NATIVE_PLACE.toString()))
            .andExpect(jsonPath("$.currentAddr").value(DEFAULT_CURRENT_ADDR.toString()))
            .andExpect(jsonPath("$.spouseName").value(DEFAULT_SPOUSE_NAME.toString()))
            .andExpect(jsonPath("$.childName").value(DEFAULT_CHILD_NAME.toString()))
            .andExpect(jsonPath("$.bloodType").value(DEFAULT_BLOOD_TYPE.toString()))
            .andExpect(jsonPath("$.emergencyContactName").value(DEFAULT_EMERGENCY_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactPhone").value(DEFAULT_EMERGENCY_CONTACT_PHONE.toString()))
            .andExpect(jsonPath("$.qq").value(DEFAULT_QQ.toString()))
            .andExpect(jsonPath("$.wechat").value(DEFAULT_WECHAT.toString()))
            .andExpect(jsonPath("$.personalEmail").value(DEFAULT_PERSONAL_EMAIL.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.others").value(DEFAULT_OTHERS.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllPersonalsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where code equals to DEFAULT_CODE
        defaultPersonalShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the personalList where code equals to UPDATED_CODE
        defaultPersonalShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where code in DEFAULT_CODE or UPDATED_CODE
        defaultPersonalShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the personalList where code equals to UPDATED_CODE
        defaultPersonalShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where code is not null
        defaultPersonalShouldBeFound("code.specified=true");

        // Get all the personalList where code is null
        defaultPersonalShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByStageNameIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where stageName equals to DEFAULT_STAGE_NAME
        defaultPersonalShouldBeFound("stageName.equals=" + DEFAULT_STAGE_NAME);

        // Get all the personalList where stageName equals to UPDATED_STAGE_NAME
        defaultPersonalShouldNotBeFound("stageName.equals=" + UPDATED_STAGE_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByStageNameIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where stageName in DEFAULT_STAGE_NAME or UPDATED_STAGE_NAME
        defaultPersonalShouldBeFound("stageName.in=" + DEFAULT_STAGE_NAME + "," + UPDATED_STAGE_NAME);

        // Get all the personalList where stageName equals to UPDATED_STAGE_NAME
        defaultPersonalShouldNotBeFound("stageName.in=" + UPDATED_STAGE_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByStageNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where stageName is not null
        defaultPersonalShouldBeFound("stageName.specified=true");

        // Get all the personalList where stageName is null
        defaultPersonalShouldNotBeFound("stageName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByIdNameIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where idName equals to DEFAULT_ID_NAME
        defaultPersonalShouldBeFound("idName.equals=" + DEFAULT_ID_NAME);

        // Get all the personalList where idName equals to UPDATED_ID_NAME
        defaultPersonalShouldNotBeFound("idName.equals=" + UPDATED_ID_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByIdNameIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where idName in DEFAULT_ID_NAME or UPDATED_ID_NAME
        defaultPersonalShouldBeFound("idName.in=" + DEFAULT_ID_NAME + "," + UPDATED_ID_NAME);

        // Get all the personalList where idName equals to UPDATED_ID_NAME
        defaultPersonalShouldNotBeFound("idName.in=" + UPDATED_ID_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByIdNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where idName is not null
        defaultPersonalShouldBeFound("idName.specified=true");

        // Get all the personalList where idName is null
        defaultPersonalShouldNotBeFound("idName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByNationIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where nation equals to DEFAULT_NATION
        defaultPersonalShouldBeFound("nation.equals=" + DEFAULT_NATION);

        // Get all the personalList where nation equals to UPDATED_NATION
        defaultPersonalShouldNotBeFound("nation.equals=" + UPDATED_NATION);
    }

    @Test
    @Transactional
    public void getAllPersonalsByNationIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where nation in DEFAULT_NATION or UPDATED_NATION
        defaultPersonalShouldBeFound("nation.in=" + DEFAULT_NATION + "," + UPDATED_NATION);

        // Get all the personalList where nation equals to UPDATED_NATION
        defaultPersonalShouldNotBeFound("nation.in=" + UPDATED_NATION);
    }

    @Test
    @Transactional
    public void getAllPersonalsByNationIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where nation is not null
        defaultPersonalShouldBeFound("nation.specified=true");

        // Get all the personalList where nation is null
        defaultPersonalShouldNotBeFound("nation.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByAccountLocIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where accountLoc equals to DEFAULT_ACCOUNT_LOC
        defaultPersonalShouldBeFound("accountLoc.equals=" + DEFAULT_ACCOUNT_LOC);

        // Get all the personalList where accountLoc equals to UPDATED_ACCOUNT_LOC
        defaultPersonalShouldNotBeFound("accountLoc.equals=" + UPDATED_ACCOUNT_LOC);
    }

    @Test
    @Transactional
    public void getAllPersonalsByAccountLocIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where accountLoc in DEFAULT_ACCOUNT_LOC or UPDATED_ACCOUNT_LOC
        defaultPersonalShouldBeFound("accountLoc.in=" + DEFAULT_ACCOUNT_LOC + "," + UPDATED_ACCOUNT_LOC);

        // Get all the personalList where accountLoc equals to UPDATED_ACCOUNT_LOC
        defaultPersonalShouldNotBeFound("accountLoc.in=" + UPDATED_ACCOUNT_LOC);
    }

    @Test
    @Transactional
    public void getAllPersonalsByAccountLocIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where accountLoc is not null
        defaultPersonalShouldBeFound("accountLoc.specified=true");

        // Get all the personalList where accountLoc is null
        defaultPersonalShouldNotBeFound("accountLoc.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByNativePlaceIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where nativePlace equals to DEFAULT_NATIVE_PLACE
        defaultPersonalShouldBeFound("nativePlace.equals=" + DEFAULT_NATIVE_PLACE);

        // Get all the personalList where nativePlace equals to UPDATED_NATIVE_PLACE
        defaultPersonalShouldNotBeFound("nativePlace.equals=" + UPDATED_NATIVE_PLACE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByNativePlaceIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where nativePlace in DEFAULT_NATIVE_PLACE or UPDATED_NATIVE_PLACE
        defaultPersonalShouldBeFound("nativePlace.in=" + DEFAULT_NATIVE_PLACE + "," + UPDATED_NATIVE_PLACE);

        // Get all the personalList where nativePlace equals to UPDATED_NATIVE_PLACE
        defaultPersonalShouldNotBeFound("nativePlace.in=" + UPDATED_NATIVE_PLACE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByNativePlaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where nativePlace is not null
        defaultPersonalShouldBeFound("nativePlace.specified=true");

        // Get all the personalList where nativePlace is null
        defaultPersonalShouldNotBeFound("nativePlace.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByCurrentAddrIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where currentAddr equals to DEFAULT_CURRENT_ADDR
        defaultPersonalShouldBeFound("currentAddr.equals=" + DEFAULT_CURRENT_ADDR);

        // Get all the personalList where currentAddr equals to UPDATED_CURRENT_ADDR
        defaultPersonalShouldNotBeFound("currentAddr.equals=" + UPDATED_CURRENT_ADDR);
    }

    @Test
    @Transactional
    public void getAllPersonalsByCurrentAddrIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where currentAddr in DEFAULT_CURRENT_ADDR or UPDATED_CURRENT_ADDR
        defaultPersonalShouldBeFound("currentAddr.in=" + DEFAULT_CURRENT_ADDR + "," + UPDATED_CURRENT_ADDR);

        // Get all the personalList where currentAddr equals to UPDATED_CURRENT_ADDR
        defaultPersonalShouldNotBeFound("currentAddr.in=" + UPDATED_CURRENT_ADDR);
    }

    @Test
    @Transactional
    public void getAllPersonalsByCurrentAddrIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where currentAddr is not null
        defaultPersonalShouldBeFound("currentAddr.specified=true");

        // Get all the personalList where currentAddr is null
        defaultPersonalShouldNotBeFound("currentAddr.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsBySpouseNameIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where spouseName equals to DEFAULT_SPOUSE_NAME
        defaultPersonalShouldBeFound("spouseName.equals=" + DEFAULT_SPOUSE_NAME);

        // Get all the personalList where spouseName equals to UPDATED_SPOUSE_NAME
        defaultPersonalShouldNotBeFound("spouseName.equals=" + UPDATED_SPOUSE_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsBySpouseNameIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where spouseName in DEFAULT_SPOUSE_NAME or UPDATED_SPOUSE_NAME
        defaultPersonalShouldBeFound("spouseName.in=" + DEFAULT_SPOUSE_NAME + "," + UPDATED_SPOUSE_NAME);

        // Get all the personalList where spouseName equals to UPDATED_SPOUSE_NAME
        defaultPersonalShouldNotBeFound("spouseName.in=" + UPDATED_SPOUSE_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsBySpouseNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where spouseName is not null
        defaultPersonalShouldBeFound("spouseName.specified=true");

        // Get all the personalList where spouseName is null
        defaultPersonalShouldNotBeFound("spouseName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByChildNameIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where childName equals to DEFAULT_CHILD_NAME
        defaultPersonalShouldBeFound("childName.equals=" + DEFAULT_CHILD_NAME);

        // Get all the personalList where childName equals to UPDATED_CHILD_NAME
        defaultPersonalShouldNotBeFound("childName.equals=" + UPDATED_CHILD_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByChildNameIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where childName in DEFAULT_CHILD_NAME or UPDATED_CHILD_NAME
        defaultPersonalShouldBeFound("childName.in=" + DEFAULT_CHILD_NAME + "," + UPDATED_CHILD_NAME);

        // Get all the personalList where childName equals to UPDATED_CHILD_NAME
        defaultPersonalShouldNotBeFound("childName.in=" + UPDATED_CHILD_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByChildNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where childName is not null
        defaultPersonalShouldBeFound("childName.specified=true");

        // Get all the personalList where childName is null
        defaultPersonalShouldNotBeFound("childName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByBloodTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where bloodType equals to DEFAULT_BLOOD_TYPE
        defaultPersonalShouldBeFound("bloodType.equals=" + DEFAULT_BLOOD_TYPE);

        // Get all the personalList where bloodType equals to UPDATED_BLOOD_TYPE
        defaultPersonalShouldNotBeFound("bloodType.equals=" + UPDATED_BLOOD_TYPE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByBloodTypeIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where bloodType in DEFAULT_BLOOD_TYPE or UPDATED_BLOOD_TYPE
        defaultPersonalShouldBeFound("bloodType.in=" + DEFAULT_BLOOD_TYPE + "," + UPDATED_BLOOD_TYPE);

        // Get all the personalList where bloodType equals to UPDATED_BLOOD_TYPE
        defaultPersonalShouldNotBeFound("bloodType.in=" + UPDATED_BLOOD_TYPE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByBloodTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where bloodType is not null
        defaultPersonalShouldBeFound("bloodType.specified=true");

        // Get all the personalList where bloodType is null
        defaultPersonalShouldNotBeFound("bloodType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByEmergencyContactNameIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where emergencyContactName equals to DEFAULT_EMERGENCY_CONTACT_NAME
        defaultPersonalShouldBeFound("emergencyContactName.equals=" + DEFAULT_EMERGENCY_CONTACT_NAME);

        // Get all the personalList where emergencyContactName equals to UPDATED_EMERGENCY_CONTACT_NAME
        defaultPersonalShouldNotBeFound("emergencyContactName.equals=" + UPDATED_EMERGENCY_CONTACT_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByEmergencyContactNameIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where emergencyContactName in DEFAULT_EMERGENCY_CONTACT_NAME or UPDATED_EMERGENCY_CONTACT_NAME
        defaultPersonalShouldBeFound("emergencyContactName.in=" + DEFAULT_EMERGENCY_CONTACT_NAME + "," + UPDATED_EMERGENCY_CONTACT_NAME);

        // Get all the personalList where emergencyContactName equals to UPDATED_EMERGENCY_CONTACT_NAME
        defaultPersonalShouldNotBeFound("emergencyContactName.in=" + UPDATED_EMERGENCY_CONTACT_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalsByEmergencyContactNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where emergencyContactName is not null
        defaultPersonalShouldBeFound("emergencyContactName.specified=true");

        // Get all the personalList where emergencyContactName is null
        defaultPersonalShouldNotBeFound("emergencyContactName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByEmergencyContactPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where emergencyContactPhone equals to DEFAULT_EMERGENCY_CONTACT_PHONE
        defaultPersonalShouldBeFound("emergencyContactPhone.equals=" + DEFAULT_EMERGENCY_CONTACT_PHONE);

        // Get all the personalList where emergencyContactPhone equals to UPDATED_EMERGENCY_CONTACT_PHONE
        defaultPersonalShouldNotBeFound("emergencyContactPhone.equals=" + UPDATED_EMERGENCY_CONTACT_PHONE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByEmergencyContactPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where emergencyContactPhone in DEFAULT_EMERGENCY_CONTACT_PHONE or UPDATED_EMERGENCY_CONTACT_PHONE
        defaultPersonalShouldBeFound("emergencyContactPhone.in=" + DEFAULT_EMERGENCY_CONTACT_PHONE + "," + UPDATED_EMERGENCY_CONTACT_PHONE);

        // Get all the personalList where emergencyContactPhone equals to UPDATED_EMERGENCY_CONTACT_PHONE
        defaultPersonalShouldNotBeFound("emergencyContactPhone.in=" + UPDATED_EMERGENCY_CONTACT_PHONE);
    }

    @Test
    @Transactional
    public void getAllPersonalsByEmergencyContactPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where emergencyContactPhone is not null
        defaultPersonalShouldBeFound("emergencyContactPhone.specified=true");

        // Get all the personalList where emergencyContactPhone is null
        defaultPersonalShouldNotBeFound("emergencyContactPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByQqIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where qq equals to DEFAULT_QQ
        defaultPersonalShouldBeFound("qq.equals=" + DEFAULT_QQ);

        // Get all the personalList where qq equals to UPDATED_QQ
        defaultPersonalShouldNotBeFound("qq.equals=" + UPDATED_QQ);
    }

    @Test
    @Transactional
    public void getAllPersonalsByQqIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where qq in DEFAULT_QQ or UPDATED_QQ
        defaultPersonalShouldBeFound("qq.in=" + DEFAULT_QQ + "," + UPDATED_QQ);

        // Get all the personalList where qq equals to UPDATED_QQ
        defaultPersonalShouldNotBeFound("qq.in=" + UPDATED_QQ);
    }

    @Test
    @Transactional
    public void getAllPersonalsByQqIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where qq is not null
        defaultPersonalShouldBeFound("qq.specified=true");

        // Get all the personalList where qq is null
        defaultPersonalShouldNotBeFound("qq.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByWechatIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where wechat equals to DEFAULT_WECHAT
        defaultPersonalShouldBeFound("wechat.equals=" + DEFAULT_WECHAT);

        // Get all the personalList where wechat equals to UPDATED_WECHAT
        defaultPersonalShouldNotBeFound("wechat.equals=" + UPDATED_WECHAT);
    }

    @Test
    @Transactional
    public void getAllPersonalsByWechatIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where wechat in DEFAULT_WECHAT or UPDATED_WECHAT
        defaultPersonalShouldBeFound("wechat.in=" + DEFAULT_WECHAT + "," + UPDATED_WECHAT);

        // Get all the personalList where wechat equals to UPDATED_WECHAT
        defaultPersonalShouldNotBeFound("wechat.in=" + UPDATED_WECHAT);
    }

    @Test
    @Transactional
    public void getAllPersonalsByWechatIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where wechat is not null
        defaultPersonalShouldBeFound("wechat.specified=true");

        // Get all the personalList where wechat is null
        defaultPersonalShouldNotBeFound("wechat.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByPersonalEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where personalEmail equals to DEFAULT_PERSONAL_EMAIL
        defaultPersonalShouldBeFound("personalEmail.equals=" + DEFAULT_PERSONAL_EMAIL);

        // Get all the personalList where personalEmail equals to UPDATED_PERSONAL_EMAIL
        defaultPersonalShouldNotBeFound("personalEmail.equals=" + UPDATED_PERSONAL_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonalsByPersonalEmailIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where personalEmail in DEFAULT_PERSONAL_EMAIL or UPDATED_PERSONAL_EMAIL
        defaultPersonalShouldBeFound("personalEmail.in=" + DEFAULT_PERSONAL_EMAIL + "," + UPDATED_PERSONAL_EMAIL);

        // Get all the personalList where personalEmail equals to UPDATED_PERSONAL_EMAIL
        defaultPersonalShouldNotBeFound("personalEmail.in=" + UPDATED_PERSONAL_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonalsByPersonalEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where personalEmail is not null
        defaultPersonalShouldBeFound("personalEmail.specified=true");

        // Get all the personalList where personalEmail is null
        defaultPersonalShouldNotBeFound("personalEmail.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByRemarkIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where remark equals to DEFAULT_REMARK
        defaultPersonalShouldBeFound("remark.equals=" + DEFAULT_REMARK);

        // Get all the personalList where remark equals to UPDATED_REMARK
        defaultPersonalShouldNotBeFound("remark.equals=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void getAllPersonalsByRemarkIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where remark in DEFAULT_REMARK or UPDATED_REMARK
        defaultPersonalShouldBeFound("remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK);

        // Get all the personalList where remark equals to UPDATED_REMARK
        defaultPersonalShouldNotBeFound("remark.in=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void getAllPersonalsByRemarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where remark is not null
        defaultPersonalShouldBeFound("remark.specified=true");

        // Get all the personalList where remark is null
        defaultPersonalShouldNotBeFound("remark.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByOthersIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where others equals to DEFAULT_OTHERS
        defaultPersonalShouldBeFound("others.equals=" + DEFAULT_OTHERS);

        // Get all the personalList where others equals to UPDATED_OTHERS
        defaultPersonalShouldNotBeFound("others.equals=" + UPDATED_OTHERS);
    }

    @Test
    @Transactional
    public void getAllPersonalsByOthersIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where others in DEFAULT_OTHERS or UPDATED_OTHERS
        defaultPersonalShouldBeFound("others.in=" + DEFAULT_OTHERS + "," + UPDATED_OTHERS);

        // Get all the personalList where others equals to UPDATED_OTHERS
        defaultPersonalShouldNotBeFound("others.in=" + UPDATED_OTHERS);
    }

    @Test
    @Transactional
    public void getAllPersonalsByOthersIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where others is not null
        defaultPersonalShouldBeFound("others.specified=true");

        // Get all the personalList where others is null
        defaultPersonalShouldNotBeFound("others.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultPersonalShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the personalList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultPersonalShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPersonalsByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultPersonalShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the personalList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultPersonalShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPersonalsByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where isSelfVerify is not null
        defaultPersonalShouldBeFound("isSelfVerify.specified=true");

        // Get all the personalList where isSelfVerify is null
        defaultPersonalShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultPersonalShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the personalList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultPersonalShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPersonalsByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultPersonalShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the personalList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultPersonalShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPersonalsByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        // Get all the personalList where isHrVerify is not null
        defaultPersonalShouldBeFound("isHrVerify.specified=true");

        // Get all the personalList where isHrVerify is null
        defaultPersonalShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalsByAccountTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumAccountType accountType = EnumAccountTypeResourceIT.createEntity(em);
        em.persist(accountType);
        em.flush();
        personal.setAccountType(accountType);
        personalRepository.saveAndFlush(personal);
        Long accountTypeId = accountType.getId();

        // Get all the personalList where accountType equals to accountTypeId
        defaultPersonalShouldBeFound("accountTypeId.equals=" + accountTypeId);

        // Get all the personalList where accountType equals to accountTypeId + 1
        defaultPersonalShouldNotBeFound("accountTypeId.equals=" + (accountTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonalsByHighestEducationIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumHighestEducation highestEducation = EnumHighestEducationResourceIT.createEntity(em);
        em.persist(highestEducation);
        em.flush();
        personal.setHighestEducation(highestEducation);
        personalRepository.saveAndFlush(personal);
        Long highestEducationId = highestEducation.getId();

        // Get all the personalList where highestEducation equals to highestEducationId
        defaultPersonalShouldBeFound("highestEducationId.equals=" + highestEducationId);

        // Get all the personalList where highestEducation equals to highestEducationId + 1
        defaultPersonalShouldNotBeFound("highestEducationId.equals=" + (highestEducationId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonalsByPoliticsStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumPoliticsStatus politicsStatus = EnumPoliticsStatusResourceIT.createEntity(em);
        em.persist(politicsStatus);
        em.flush();
        personal.setPoliticsStatus(politicsStatus);
        personalRepository.saveAndFlush(personal);
        Long politicsStatusId = politicsStatus.getId();

        // Get all the personalList where politicsStatus equals to politicsStatusId
        defaultPersonalShouldBeFound("politicsStatusId.equals=" + politicsStatusId);

        // Get all the personalList where politicsStatus equals to politicsStatusId + 1
        defaultPersonalShouldNotBeFound("politicsStatusId.equals=" + (politicsStatusId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonalsByMaritalStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumMaritalStatus maritalStatus = EnumMaritalStatusResourceIT.createEntity(em);
        em.persist(maritalStatus);
        em.flush();
        personal.setMaritalStatus(maritalStatus);
        personalRepository.saveAndFlush(personal);
        Long maritalStatusId = maritalStatus.getId();

        // Get all the personalList where maritalStatus equals to maritalStatusId
        defaultPersonalShouldBeFound("maritalStatusId.equals=" + maritalStatusId);

        // Get all the personalList where maritalStatus equals to maritalStatusId + 1
        defaultPersonalShouldNotBeFound("maritalStatusId.equals=" + (maritalStatusId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonalsByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        personal.setEmp(emp);
        personalRepository.saveAndFlush(personal);
        Long empId = emp.getId();

        // Get all the personalList where emp equals to empId
        defaultPersonalShouldBeFound("empId.equals=" + empId);

        // Get all the personalList where emp equals to empId + 1
        defaultPersonalShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPersonalShouldBeFound(String filter) throws Exception {
        restPersonalMockMvc.perform(get("/api/personals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personal.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].stageName").value(hasItem(DEFAULT_STAGE_NAME)))
            .andExpect(jsonPath("$.[*].idName").value(hasItem(DEFAULT_ID_NAME)))
            .andExpect(jsonPath("$.[*].nation").value(hasItem(DEFAULT_NATION)))
            .andExpect(jsonPath("$.[*].accountLoc").value(hasItem(DEFAULT_ACCOUNT_LOC)))
            .andExpect(jsonPath("$.[*].nativePlace").value(hasItem(DEFAULT_NATIVE_PLACE)))
            .andExpect(jsonPath("$.[*].currentAddr").value(hasItem(DEFAULT_CURRENT_ADDR)))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME)))
            .andExpect(jsonPath("$.[*].childName").value(hasItem(DEFAULT_CHILD_NAME)))
            .andExpect(jsonPath("$.[*].bloodType").value(hasItem(DEFAULT_BLOOD_TYPE.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactPhone").value(hasItem(DEFAULT_EMERGENCY_CONTACT_PHONE)))
            .andExpect(jsonPath("$.[*].qq").value(hasItem(DEFAULT_QQ)))
            .andExpect(jsonPath("$.[*].wechat").value(hasItem(DEFAULT_WECHAT)))
            .andExpect(jsonPath("$.[*].personalEmail").value(hasItem(DEFAULT_PERSONAL_EMAIL)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].others").value(hasItem(DEFAULT_OTHERS)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restPersonalMockMvc.perform(get("/api/personals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPersonalShouldNotBeFound(String filter) throws Exception {
        restPersonalMockMvc.perform(get("/api/personals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPersonalMockMvc.perform(get("/api/personals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPersonal() throws Exception {
        // Get the personal
        restPersonalMockMvc.perform(get("/api/personals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonal() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        int databaseSizeBeforeUpdate = personalRepository.findAll().size();

        // Update the personal
        Personal updatedPersonal = personalRepository.findById(personal.getId()).get();
        // Disconnect from session so that the updates on updatedPersonal are not directly saved in db
        em.detach(updatedPersonal);
        updatedPersonal
            .code(UPDATED_CODE)
            .stageName(UPDATED_STAGE_NAME)
            .idName(UPDATED_ID_NAME)
            .nation(UPDATED_NATION)
            .accountLoc(UPDATED_ACCOUNT_LOC)
            .nativePlace(UPDATED_NATIVE_PLACE)
            .currentAddr(UPDATED_CURRENT_ADDR)
            .spouseName(UPDATED_SPOUSE_NAME)
            .childName(UPDATED_CHILD_NAME)
            .bloodType(UPDATED_BLOOD_TYPE)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactPhone(UPDATED_EMERGENCY_CONTACT_PHONE)
            .qq(UPDATED_QQ)
            .wechat(UPDATED_WECHAT)
            .personalEmail(UPDATED_PERSONAL_EMAIL)
            .remark(UPDATED_REMARK)
            .others(UPDATED_OTHERS)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        PersonalDTO personalDTO = personalMapper.toDto(updatedPersonal);

        restPersonalMockMvc.perform(put("/api/personals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDTO)))
            .andExpect(status().isOk());

        // Validate the Personal in the database
        List<Personal> personalList = personalRepository.findAll();
        assertThat(personalList).hasSize(databaseSizeBeforeUpdate);
        Personal testPersonal = personalList.get(personalList.size() - 1);
        assertThat(testPersonal.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPersonal.getStageName()).isEqualTo(UPDATED_STAGE_NAME);
        assertThat(testPersonal.getIdName()).isEqualTo(UPDATED_ID_NAME);
        assertThat(testPersonal.getNation()).isEqualTo(UPDATED_NATION);
        assertThat(testPersonal.getAccountLoc()).isEqualTo(UPDATED_ACCOUNT_LOC);
        assertThat(testPersonal.getNativePlace()).isEqualTo(UPDATED_NATIVE_PLACE);
        assertThat(testPersonal.getCurrentAddr()).isEqualTo(UPDATED_CURRENT_ADDR);
        assertThat(testPersonal.getSpouseName()).isEqualTo(UPDATED_SPOUSE_NAME);
        assertThat(testPersonal.getChildName()).isEqualTo(UPDATED_CHILD_NAME);
        assertThat(testPersonal.getBloodType()).isEqualTo(UPDATED_BLOOD_TYPE);
        assertThat(testPersonal.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testPersonal.getEmergencyContactPhone()).isEqualTo(UPDATED_EMERGENCY_CONTACT_PHONE);
        assertThat(testPersonal.getQq()).isEqualTo(UPDATED_QQ);
        assertThat(testPersonal.getWechat()).isEqualTo(UPDATED_WECHAT);
        assertThat(testPersonal.getPersonalEmail()).isEqualTo(UPDATED_PERSONAL_EMAIL);
        assertThat(testPersonal.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testPersonal.getOthers()).isEqualTo(UPDATED_OTHERS);
        assertThat(testPersonal.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testPersonal.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonal() throws Exception {
        int databaseSizeBeforeUpdate = personalRepository.findAll().size();

        // Create the Personal
        PersonalDTO personalDTO = personalMapper.toDto(personal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonalMockMvc.perform(put("/api/personals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Personal in the database
        List<Personal> personalList = personalRepository.findAll();
        assertThat(personalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonal() throws Exception {
        // Initialize the database
        personalRepository.saveAndFlush(personal);

        int databaseSizeBeforeDelete = personalRepository.findAll().size();

        // Delete the personal
        restPersonalMockMvc.perform(delete("/api/personals/{id}", personal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Personal> personalList = personalRepository.findAll();
        assertThat(personalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Personal.class);
        Personal personal1 = new Personal();
        personal1.setId(1L);
        Personal personal2 = new Personal();
        personal2.setId(personal1.getId());
        assertThat(personal1).isEqualTo(personal2);
        personal2.setId(2L);
        assertThat(personal1).isNotEqualTo(personal2);
        personal1.setId(null);
        assertThat(personal1).isNotEqualTo(personal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalDTO.class);
        PersonalDTO personalDTO1 = new PersonalDTO();
        personalDTO1.setId(1L);
        PersonalDTO personalDTO2 = new PersonalDTO();
        assertThat(personalDTO1).isNotEqualTo(personalDTO2);
        personalDTO2.setId(personalDTO1.getId());
        assertThat(personalDTO1).isEqualTo(personalDTO2);
        personalDTO2.setId(2L);
        assertThat(personalDTO1).isNotEqualTo(personalDTO2);
        personalDTO1.setId(null);
        assertThat(personalDTO1).isNotEqualTo(personalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(personalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(personalMapper.fromId(null)).isNull();
    }
}
