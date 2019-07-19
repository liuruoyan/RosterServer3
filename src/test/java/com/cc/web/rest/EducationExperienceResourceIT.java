package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EducationExperience;
import com.cc.domain.EnumEducation;
import com.cc.domain.Employee;
import com.cc.repository.EducationExperienceRepository;
import com.cc.service.EducationExperienceService;
import com.cc.service.dto.EducationExperienceDTO;
import com.cc.service.mapper.EducationExperienceMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EducationExperienceCriteria;
import com.cc.service.EducationExperienceQueryService;

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
 * Integration tests for the {@Link EducationExperienceResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EducationExperienceResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SCHOOL = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL = "BBBBBBBBBB";

    private static final String DEFAULT_MAJOR = "AAAAAAAAAA";
    private static final String UPDATED_MAJOR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_IN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_GRADUATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_GRADUATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_INCEPTION = false;
    private static final Boolean UPDATED_INCEPTION = true;

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private EducationExperienceRepository educationExperienceRepository;

    @Autowired
    private EducationExperienceMapper educationExperienceMapper;

    @Autowired
    private EducationExperienceService educationExperienceService;

    @Autowired
    private EducationExperienceQueryService educationExperienceQueryService;

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

    private MockMvc restEducationExperienceMockMvc;

    private EducationExperience educationExperience;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EducationExperienceResource educationExperienceResource = new EducationExperienceResource(educationExperienceService, educationExperienceQueryService);
        this.restEducationExperienceMockMvc = MockMvcBuilders.standaloneSetup(educationExperienceResource)
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
    public static EducationExperience createEntity(EntityManager em) {
        EducationExperience educationExperience = new EducationExperience()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .school(DEFAULT_SCHOOL)
            .major(DEFAULT_MAJOR)
            .inDate(DEFAULT_IN_DATE)
            .graduationDate(DEFAULT_GRADUATION_DATE)
            .inception(DEFAULT_INCEPTION)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return educationExperience;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EducationExperience createUpdatedEntity(EntityManager em) {
        EducationExperience educationExperience = new EducationExperience()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .school(UPDATED_SCHOOL)
            .major(UPDATED_MAJOR)
            .inDate(UPDATED_IN_DATE)
            .graduationDate(UPDATED_GRADUATION_DATE)
            .inception(UPDATED_INCEPTION)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return educationExperience;
    }

    @BeforeEach
    public void initTest() {
        educationExperience = createEntity(em);
    }

    @Test
    @Transactional
    public void createEducationExperience() throws Exception {
        int databaseSizeBeforeCreate = educationExperienceRepository.findAll().size();

        // Create the EducationExperience
        EducationExperienceDTO educationExperienceDTO = educationExperienceMapper.toDto(educationExperience);
        restEducationExperienceMockMvc.perform(post("/api/education-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(educationExperienceDTO)))
            .andExpect(status().isCreated());

        // Validate the EducationExperience in the database
        List<EducationExperience> educationExperienceList = educationExperienceRepository.findAll();
        assertThat(educationExperienceList).hasSize(databaseSizeBeforeCreate + 1);
        EducationExperience testEducationExperience = educationExperienceList.get(educationExperienceList.size() - 1);
        assertThat(testEducationExperience.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testEducationExperience.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEducationExperience.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testEducationExperience.getSchool()).isEqualTo(DEFAULT_SCHOOL);
        assertThat(testEducationExperience.getMajor()).isEqualTo(DEFAULT_MAJOR);
        assertThat(testEducationExperience.getInDate()).isEqualTo(DEFAULT_IN_DATE);
        assertThat(testEducationExperience.getGraduationDate()).isEqualTo(DEFAULT_GRADUATION_DATE);
        assertThat(testEducationExperience.isInception()).isEqualTo(DEFAULT_INCEPTION);
        assertThat(testEducationExperience.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testEducationExperience.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createEducationExperienceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = educationExperienceRepository.findAll().size();

        // Create the EducationExperience with an existing ID
        educationExperience.setId(1L);
        EducationExperienceDTO educationExperienceDTO = educationExperienceMapper.toDto(educationExperience);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEducationExperienceMockMvc.perform(post("/api/education-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(educationExperienceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EducationExperience in the database
        List<EducationExperience> educationExperienceList = educationExperienceRepository.findAll();
        assertThat(educationExperienceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEducationExperiences() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList
        restEducationExperienceMockMvc.perform(get("/api/education-experiences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(educationExperience.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].school").value(hasItem(DEFAULT_SCHOOL.toString())))
            .andExpect(jsonPath("$.[*].major").value(hasItem(DEFAULT_MAJOR.toString())))
            .andExpect(jsonPath("$.[*].inDate").value(hasItem(DEFAULT_IN_DATE.toString())))
            .andExpect(jsonPath("$.[*].graduationDate").value(hasItem(DEFAULT_GRADUATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].inception").value(hasItem(DEFAULT_INCEPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEducationExperience() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get the educationExperience
        restEducationExperienceMockMvc.perform(get("/api/education-experiences/{id}", educationExperience.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(educationExperience.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.school").value(DEFAULT_SCHOOL.toString()))
            .andExpect(jsonPath("$.major").value(DEFAULT_MAJOR.toString()))
            .andExpect(jsonPath("$.inDate").value(DEFAULT_IN_DATE.toString()))
            .andExpect(jsonPath("$.graduationDate").value(DEFAULT_GRADUATION_DATE.toString()))
            .andExpect(jsonPath("$.inception").value(DEFAULT_INCEPTION.booleanValue()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where code equals to DEFAULT_CODE
        defaultEducationExperienceShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the educationExperienceList where code equals to UPDATED_CODE
        defaultEducationExperienceShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where code in DEFAULT_CODE or UPDATED_CODE
        defaultEducationExperienceShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the educationExperienceList where code equals to UPDATED_CODE
        defaultEducationExperienceShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where code is not null
        defaultEducationExperienceShouldBeFound("code.specified=true");

        // Get all the educationExperienceList where code is null
        defaultEducationExperienceShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where name equals to DEFAULT_NAME
        defaultEducationExperienceShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the educationExperienceList where name equals to UPDATED_NAME
        defaultEducationExperienceShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where name in DEFAULT_NAME or UPDATED_NAME
        defaultEducationExperienceShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the educationExperienceList where name equals to UPDATED_NAME
        defaultEducationExperienceShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where name is not null
        defaultEducationExperienceShouldBeFound("name.specified=true");

        // Get all the educationExperienceList where name is null
        defaultEducationExperienceShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where phone equals to DEFAULT_PHONE
        defaultEducationExperienceShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the educationExperienceList where phone equals to UPDATED_PHONE
        defaultEducationExperienceShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultEducationExperienceShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the educationExperienceList where phone equals to UPDATED_PHONE
        defaultEducationExperienceShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where phone is not null
        defaultEducationExperienceShouldBeFound("phone.specified=true");

        // Get all the educationExperienceList where phone is null
        defaultEducationExperienceShouldNotBeFound("phone.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesBySchoolIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where school equals to DEFAULT_SCHOOL
        defaultEducationExperienceShouldBeFound("school.equals=" + DEFAULT_SCHOOL);

        // Get all the educationExperienceList where school equals to UPDATED_SCHOOL
        defaultEducationExperienceShouldNotBeFound("school.equals=" + UPDATED_SCHOOL);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesBySchoolIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where school in DEFAULT_SCHOOL or UPDATED_SCHOOL
        defaultEducationExperienceShouldBeFound("school.in=" + DEFAULT_SCHOOL + "," + UPDATED_SCHOOL);

        // Get all the educationExperienceList where school equals to UPDATED_SCHOOL
        defaultEducationExperienceShouldNotBeFound("school.in=" + UPDATED_SCHOOL);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesBySchoolIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where school is not null
        defaultEducationExperienceShouldBeFound("school.specified=true");

        // Get all the educationExperienceList where school is null
        defaultEducationExperienceShouldNotBeFound("school.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByMajorIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where major equals to DEFAULT_MAJOR
        defaultEducationExperienceShouldBeFound("major.equals=" + DEFAULT_MAJOR);

        // Get all the educationExperienceList where major equals to UPDATED_MAJOR
        defaultEducationExperienceShouldNotBeFound("major.equals=" + UPDATED_MAJOR);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByMajorIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where major in DEFAULT_MAJOR or UPDATED_MAJOR
        defaultEducationExperienceShouldBeFound("major.in=" + DEFAULT_MAJOR + "," + UPDATED_MAJOR);

        // Get all the educationExperienceList where major equals to UPDATED_MAJOR
        defaultEducationExperienceShouldNotBeFound("major.in=" + UPDATED_MAJOR);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByMajorIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where major is not null
        defaultEducationExperienceShouldBeFound("major.specified=true");

        // Get all the educationExperienceList where major is null
        defaultEducationExperienceShouldNotBeFound("major.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByInDateIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inDate equals to DEFAULT_IN_DATE
        defaultEducationExperienceShouldBeFound("inDate.equals=" + DEFAULT_IN_DATE);

        // Get all the educationExperienceList where inDate equals to UPDATED_IN_DATE
        defaultEducationExperienceShouldNotBeFound("inDate.equals=" + UPDATED_IN_DATE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByInDateIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inDate in DEFAULT_IN_DATE or UPDATED_IN_DATE
        defaultEducationExperienceShouldBeFound("inDate.in=" + DEFAULT_IN_DATE + "," + UPDATED_IN_DATE);

        // Get all the educationExperienceList where inDate equals to UPDATED_IN_DATE
        defaultEducationExperienceShouldNotBeFound("inDate.in=" + UPDATED_IN_DATE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByInDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inDate is not null
        defaultEducationExperienceShouldBeFound("inDate.specified=true");

        // Get all the educationExperienceList where inDate is null
        defaultEducationExperienceShouldNotBeFound("inDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByInDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inDate greater than or equals to DEFAULT_IN_DATE
        defaultEducationExperienceShouldBeFound("inDate.greaterOrEqualThan=" + DEFAULT_IN_DATE);

        // Get all the educationExperienceList where inDate greater than or equals to UPDATED_IN_DATE
        defaultEducationExperienceShouldNotBeFound("inDate.greaterOrEqualThan=" + UPDATED_IN_DATE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByInDateIsLessThanSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inDate less than or equals to DEFAULT_IN_DATE
        defaultEducationExperienceShouldNotBeFound("inDate.lessThan=" + DEFAULT_IN_DATE);

        // Get all the educationExperienceList where inDate less than or equals to UPDATED_IN_DATE
        defaultEducationExperienceShouldBeFound("inDate.lessThan=" + UPDATED_IN_DATE);
    }


    @Test
    @Transactional
    public void getAllEducationExperiencesByGraduationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where graduationDate equals to DEFAULT_GRADUATION_DATE
        defaultEducationExperienceShouldBeFound("graduationDate.equals=" + DEFAULT_GRADUATION_DATE);

        // Get all the educationExperienceList where graduationDate equals to UPDATED_GRADUATION_DATE
        defaultEducationExperienceShouldNotBeFound("graduationDate.equals=" + UPDATED_GRADUATION_DATE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByGraduationDateIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where graduationDate in DEFAULT_GRADUATION_DATE or UPDATED_GRADUATION_DATE
        defaultEducationExperienceShouldBeFound("graduationDate.in=" + DEFAULT_GRADUATION_DATE + "," + UPDATED_GRADUATION_DATE);

        // Get all the educationExperienceList where graduationDate equals to UPDATED_GRADUATION_DATE
        defaultEducationExperienceShouldNotBeFound("graduationDate.in=" + UPDATED_GRADUATION_DATE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByGraduationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where graduationDate is not null
        defaultEducationExperienceShouldBeFound("graduationDate.specified=true");

        // Get all the educationExperienceList where graduationDate is null
        defaultEducationExperienceShouldNotBeFound("graduationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByGraduationDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where graduationDate greater than or equals to DEFAULT_GRADUATION_DATE
        defaultEducationExperienceShouldBeFound("graduationDate.greaterOrEqualThan=" + DEFAULT_GRADUATION_DATE);

        // Get all the educationExperienceList where graduationDate greater than or equals to UPDATED_GRADUATION_DATE
        defaultEducationExperienceShouldNotBeFound("graduationDate.greaterOrEqualThan=" + UPDATED_GRADUATION_DATE);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByGraduationDateIsLessThanSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where graduationDate less than or equals to DEFAULT_GRADUATION_DATE
        defaultEducationExperienceShouldNotBeFound("graduationDate.lessThan=" + DEFAULT_GRADUATION_DATE);

        // Get all the educationExperienceList where graduationDate less than or equals to UPDATED_GRADUATION_DATE
        defaultEducationExperienceShouldBeFound("graduationDate.lessThan=" + UPDATED_GRADUATION_DATE);
    }


    @Test
    @Transactional
    public void getAllEducationExperiencesByInceptionIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inception equals to DEFAULT_INCEPTION
        defaultEducationExperienceShouldBeFound("inception.equals=" + DEFAULT_INCEPTION);

        // Get all the educationExperienceList where inception equals to UPDATED_INCEPTION
        defaultEducationExperienceShouldNotBeFound("inception.equals=" + UPDATED_INCEPTION);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByInceptionIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inception in DEFAULT_INCEPTION or UPDATED_INCEPTION
        defaultEducationExperienceShouldBeFound("inception.in=" + DEFAULT_INCEPTION + "," + UPDATED_INCEPTION);

        // Get all the educationExperienceList where inception equals to UPDATED_INCEPTION
        defaultEducationExperienceShouldNotBeFound("inception.in=" + UPDATED_INCEPTION);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByInceptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where inception is not null
        defaultEducationExperienceShouldBeFound("inception.specified=true");

        // Get all the educationExperienceList where inception is null
        defaultEducationExperienceShouldNotBeFound("inception.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultEducationExperienceShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the educationExperienceList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultEducationExperienceShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultEducationExperienceShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the educationExperienceList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultEducationExperienceShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where isSelfVerify is not null
        defaultEducationExperienceShouldBeFound("isSelfVerify.specified=true");

        // Get all the educationExperienceList where isSelfVerify is null
        defaultEducationExperienceShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultEducationExperienceShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the educationExperienceList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultEducationExperienceShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultEducationExperienceShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the educationExperienceList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultEducationExperienceShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        // Get all the educationExperienceList where isHrVerify is not null
        defaultEducationExperienceShouldBeFound("isHrVerify.specified=true");

        // Get all the educationExperienceList where isHrVerify is null
        defaultEducationExperienceShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllEducationExperiencesByEducationIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEducation education = EnumEducationResourceIT.createEntity(em);
        em.persist(education);
        em.flush();
        educationExperience.setEducation(education);
        educationExperienceRepository.saveAndFlush(educationExperience);
        Long educationId = education.getId();

        // Get all the educationExperienceList where education equals to educationId
        defaultEducationExperienceShouldBeFound("educationId.equals=" + educationId);

        // Get all the educationExperienceList where education equals to educationId + 1
        defaultEducationExperienceShouldNotBeFound("educationId.equals=" + (educationId + 1));
    }


    @Test
    @Transactional
    public void getAllEducationExperiencesByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        educationExperience.setEmp(emp);
        educationExperienceRepository.saveAndFlush(educationExperience);
        Long empId = emp.getId();

        // Get all the educationExperienceList where emp equals to empId
        defaultEducationExperienceShouldBeFound("empId.equals=" + empId);

        // Get all the educationExperienceList where emp equals to empId + 1
        defaultEducationExperienceShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEducationExperienceShouldBeFound(String filter) throws Exception {
        restEducationExperienceMockMvc.perform(get("/api/education-experiences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(educationExperience.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].school").value(hasItem(DEFAULT_SCHOOL)))
            .andExpect(jsonPath("$.[*].major").value(hasItem(DEFAULT_MAJOR)))
            .andExpect(jsonPath("$.[*].inDate").value(hasItem(DEFAULT_IN_DATE.toString())))
            .andExpect(jsonPath("$.[*].graduationDate").value(hasItem(DEFAULT_GRADUATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].inception").value(hasItem(DEFAULT_INCEPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restEducationExperienceMockMvc.perform(get("/api/education-experiences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEducationExperienceShouldNotBeFound(String filter) throws Exception {
        restEducationExperienceMockMvc.perform(get("/api/education-experiences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEducationExperienceMockMvc.perform(get("/api/education-experiences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEducationExperience() throws Exception {
        // Get the educationExperience
        restEducationExperienceMockMvc.perform(get("/api/education-experiences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEducationExperience() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        int databaseSizeBeforeUpdate = educationExperienceRepository.findAll().size();

        // Update the educationExperience
        EducationExperience updatedEducationExperience = educationExperienceRepository.findById(educationExperience.getId()).get();
        // Disconnect from session so that the updates on updatedEducationExperience are not directly saved in db
        em.detach(updatedEducationExperience);
        updatedEducationExperience
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .school(UPDATED_SCHOOL)
            .major(UPDATED_MAJOR)
            .inDate(UPDATED_IN_DATE)
            .graduationDate(UPDATED_GRADUATION_DATE)
            .inception(UPDATED_INCEPTION)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        EducationExperienceDTO educationExperienceDTO = educationExperienceMapper.toDto(updatedEducationExperience);

        restEducationExperienceMockMvc.perform(put("/api/education-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(educationExperienceDTO)))
            .andExpect(status().isOk());

        // Validate the EducationExperience in the database
        List<EducationExperience> educationExperienceList = educationExperienceRepository.findAll();
        assertThat(educationExperienceList).hasSize(databaseSizeBeforeUpdate);
        EducationExperience testEducationExperience = educationExperienceList.get(educationExperienceList.size() - 1);
        assertThat(testEducationExperience.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testEducationExperience.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEducationExperience.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testEducationExperience.getSchool()).isEqualTo(UPDATED_SCHOOL);
        assertThat(testEducationExperience.getMajor()).isEqualTo(UPDATED_MAJOR);
        assertThat(testEducationExperience.getInDate()).isEqualTo(UPDATED_IN_DATE);
        assertThat(testEducationExperience.getGraduationDate()).isEqualTo(UPDATED_GRADUATION_DATE);
        assertThat(testEducationExperience.isInception()).isEqualTo(UPDATED_INCEPTION);
        assertThat(testEducationExperience.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testEducationExperience.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingEducationExperience() throws Exception {
        int databaseSizeBeforeUpdate = educationExperienceRepository.findAll().size();

        // Create the EducationExperience
        EducationExperienceDTO educationExperienceDTO = educationExperienceMapper.toDto(educationExperience);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEducationExperienceMockMvc.perform(put("/api/education-experiences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(educationExperienceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EducationExperience in the database
        List<EducationExperience> educationExperienceList = educationExperienceRepository.findAll();
        assertThat(educationExperienceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEducationExperience() throws Exception {
        // Initialize the database
        educationExperienceRepository.saveAndFlush(educationExperience);

        int databaseSizeBeforeDelete = educationExperienceRepository.findAll().size();

        // Delete the educationExperience
        restEducationExperienceMockMvc.perform(delete("/api/education-experiences/{id}", educationExperience.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EducationExperience> educationExperienceList = educationExperienceRepository.findAll();
        assertThat(educationExperienceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EducationExperience.class);
        EducationExperience educationExperience1 = new EducationExperience();
        educationExperience1.setId(1L);
        EducationExperience educationExperience2 = new EducationExperience();
        educationExperience2.setId(educationExperience1.getId());
        assertThat(educationExperience1).isEqualTo(educationExperience2);
        educationExperience2.setId(2L);
        assertThat(educationExperience1).isNotEqualTo(educationExperience2);
        educationExperience1.setId(null);
        assertThat(educationExperience1).isNotEqualTo(educationExperience2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EducationExperienceDTO.class);
        EducationExperienceDTO educationExperienceDTO1 = new EducationExperienceDTO();
        educationExperienceDTO1.setId(1L);
        EducationExperienceDTO educationExperienceDTO2 = new EducationExperienceDTO();
        assertThat(educationExperienceDTO1).isNotEqualTo(educationExperienceDTO2);
        educationExperienceDTO2.setId(educationExperienceDTO1.getId());
        assertThat(educationExperienceDTO1).isEqualTo(educationExperienceDTO2);
        educationExperienceDTO2.setId(2L);
        assertThat(educationExperienceDTO1).isNotEqualTo(educationExperienceDTO2);
        educationExperienceDTO1.setId(null);
        assertThat(educationExperienceDTO1).isNotEqualTo(educationExperienceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(educationExperienceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(educationExperienceMapper.fromId(null)).isNull();
    }
}
