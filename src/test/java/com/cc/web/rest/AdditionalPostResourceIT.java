package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.AdditionalPost;
import com.cc.domain.Employee;
import com.cc.repository.AdditionalPostRepository;
import com.cc.service.AdditionalPostService;
import com.cc.service.dto.AdditionalPostDTO;
import com.cc.service.mapper.AdditionalPostMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.AdditionalPostCriteria;
import com.cc.service.AdditionalPostQueryService;

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
 * Integration tests for the {@Link AdditionalPostResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class AdditionalPostResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT = "AAAAAAAAAA";
    private static final String UPDATED_DEPT = "BBBBBBBBBB";

    private static final String DEFAULT_JOB = "AAAAAAAAAA";
    private static final String UPDATED_JOB = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private AdditionalPostRepository additionalPostRepository;

    @Autowired
    private AdditionalPostMapper additionalPostMapper;

    @Autowired
    private AdditionalPostService additionalPostService;

    @Autowired
    private AdditionalPostQueryService additionalPostQueryService;

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

    private MockMvc restAdditionalPostMockMvc;

    private AdditionalPost additionalPost;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdditionalPostResource additionalPostResource = new AdditionalPostResource(additionalPostService, additionalPostQueryService);
        this.restAdditionalPostMockMvc = MockMvcBuilders.standaloneSetup(additionalPostResource)
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
    public static AdditionalPost createEntity(EntityManager em) {
        AdditionalPost additionalPost = new AdditionalPost()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .dept(DEFAULT_DEPT)
            .job(DEFAULT_JOB)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .remark(DEFAULT_REMARK)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return additionalPost;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdditionalPost createUpdatedEntity(EntityManager em) {
        AdditionalPost additionalPost = new AdditionalPost()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .dept(UPDATED_DEPT)
            .job(UPDATED_JOB)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .remark(UPDATED_REMARK)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return additionalPost;
    }

    @BeforeEach
    public void initTest() {
        additionalPost = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdditionalPost() throws Exception {
        int databaseSizeBeforeCreate = additionalPostRepository.findAll().size();

        // Create the AdditionalPost
        AdditionalPostDTO additionalPostDTO = additionalPostMapper.toDto(additionalPost);
        restAdditionalPostMockMvc.perform(post("/api/additional-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(additionalPostDTO)))
            .andExpect(status().isCreated());

        // Validate the AdditionalPost in the database
        List<AdditionalPost> additionalPostList = additionalPostRepository.findAll();
        assertThat(additionalPostList).hasSize(databaseSizeBeforeCreate + 1);
        AdditionalPost testAdditionalPost = additionalPostList.get(additionalPostList.size() - 1);
        assertThat(testAdditionalPost.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAdditionalPost.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdditionalPost.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testAdditionalPost.getDept()).isEqualTo(DEFAULT_DEPT);
        assertThat(testAdditionalPost.getJob()).isEqualTo(DEFAULT_JOB);
        assertThat(testAdditionalPost.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAdditionalPost.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testAdditionalPost.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testAdditionalPost.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testAdditionalPost.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createAdditionalPostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = additionalPostRepository.findAll().size();

        // Create the AdditionalPost with an existing ID
        additionalPost.setId(1L);
        AdditionalPostDTO additionalPostDTO = additionalPostMapper.toDto(additionalPost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdditionalPostMockMvc.perform(post("/api/additional-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(additionalPostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdditionalPost in the database
        List<AdditionalPost> additionalPostList = additionalPostRepository.findAll();
        assertThat(additionalPostList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdditionalPosts() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList
        restAdditionalPostMockMvc.perform(get("/api/additional-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(additionalPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].dept").value(hasItem(DEFAULT_DEPT.toString())))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAdditionalPost() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get the additionalPost
        restAdditionalPostMockMvc.perform(get("/api/additional-posts/{id}", additionalPost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(additionalPost.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.dept").value(DEFAULT_DEPT.toString()))
            .andExpect(jsonPath("$.job").value(DEFAULT_JOB.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where code equals to DEFAULT_CODE
        defaultAdditionalPostShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the additionalPostList where code equals to UPDATED_CODE
        defaultAdditionalPostShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where code in DEFAULT_CODE or UPDATED_CODE
        defaultAdditionalPostShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the additionalPostList where code equals to UPDATED_CODE
        defaultAdditionalPostShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where code is not null
        defaultAdditionalPostShouldBeFound("code.specified=true");

        // Get all the additionalPostList where code is null
        defaultAdditionalPostShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where name equals to DEFAULT_NAME
        defaultAdditionalPostShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the additionalPostList where name equals to UPDATED_NAME
        defaultAdditionalPostShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where name in DEFAULT_NAME or UPDATED_NAME
        defaultAdditionalPostShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the additionalPostList where name equals to UPDATED_NAME
        defaultAdditionalPostShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where name is not null
        defaultAdditionalPostShouldBeFound("name.specified=true");

        // Get all the additionalPostList where name is null
        defaultAdditionalPostShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where phone equals to DEFAULT_PHONE
        defaultAdditionalPostShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the additionalPostList where phone equals to UPDATED_PHONE
        defaultAdditionalPostShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultAdditionalPostShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the additionalPostList where phone equals to UPDATED_PHONE
        defaultAdditionalPostShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where phone is not null
        defaultAdditionalPostShouldBeFound("phone.specified=true");

        // Get all the additionalPostList where phone is null
        defaultAdditionalPostShouldNotBeFound("phone.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByDeptIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where dept equals to DEFAULT_DEPT
        defaultAdditionalPostShouldBeFound("dept.equals=" + DEFAULT_DEPT);

        // Get all the additionalPostList where dept equals to UPDATED_DEPT
        defaultAdditionalPostShouldNotBeFound("dept.equals=" + UPDATED_DEPT);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByDeptIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where dept in DEFAULT_DEPT or UPDATED_DEPT
        defaultAdditionalPostShouldBeFound("dept.in=" + DEFAULT_DEPT + "," + UPDATED_DEPT);

        // Get all the additionalPostList where dept equals to UPDATED_DEPT
        defaultAdditionalPostShouldNotBeFound("dept.in=" + UPDATED_DEPT);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByDeptIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where dept is not null
        defaultAdditionalPostShouldBeFound("dept.specified=true");

        // Get all the additionalPostList where dept is null
        defaultAdditionalPostShouldNotBeFound("dept.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByJobIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where job equals to DEFAULT_JOB
        defaultAdditionalPostShouldBeFound("job.equals=" + DEFAULT_JOB);

        // Get all the additionalPostList where job equals to UPDATED_JOB
        defaultAdditionalPostShouldNotBeFound("job.equals=" + UPDATED_JOB);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByJobIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where job in DEFAULT_JOB or UPDATED_JOB
        defaultAdditionalPostShouldBeFound("job.in=" + DEFAULT_JOB + "," + UPDATED_JOB);

        // Get all the additionalPostList where job equals to UPDATED_JOB
        defaultAdditionalPostShouldNotBeFound("job.in=" + UPDATED_JOB);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByJobIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where job is not null
        defaultAdditionalPostShouldBeFound("job.specified=true");

        // Get all the additionalPostList where job is null
        defaultAdditionalPostShouldNotBeFound("job.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByStartDateIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where startDate equals to DEFAULT_START_DATE
        defaultAdditionalPostShouldBeFound("startDate.equals=" + DEFAULT_START_DATE);

        // Get all the additionalPostList where startDate equals to UPDATED_START_DATE
        defaultAdditionalPostShouldNotBeFound("startDate.equals=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByStartDateIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where startDate in DEFAULT_START_DATE or UPDATED_START_DATE
        defaultAdditionalPostShouldBeFound("startDate.in=" + DEFAULT_START_DATE + "," + UPDATED_START_DATE);

        // Get all the additionalPostList where startDate equals to UPDATED_START_DATE
        defaultAdditionalPostShouldNotBeFound("startDate.in=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByStartDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where startDate is not null
        defaultAdditionalPostShouldBeFound("startDate.specified=true");

        // Get all the additionalPostList where startDate is null
        defaultAdditionalPostShouldNotBeFound("startDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByStartDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where startDate greater than or equals to DEFAULT_START_DATE
        defaultAdditionalPostShouldBeFound("startDate.greaterOrEqualThan=" + DEFAULT_START_DATE);

        // Get all the additionalPostList where startDate greater than or equals to UPDATED_START_DATE
        defaultAdditionalPostShouldNotBeFound("startDate.greaterOrEqualThan=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByStartDateIsLessThanSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where startDate less than or equals to DEFAULT_START_DATE
        defaultAdditionalPostShouldNotBeFound("startDate.lessThan=" + DEFAULT_START_DATE);

        // Get all the additionalPostList where startDate less than or equals to UPDATED_START_DATE
        defaultAdditionalPostShouldBeFound("startDate.lessThan=" + UPDATED_START_DATE);
    }


    @Test
    @Transactional
    public void getAllAdditionalPostsByEndDateIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where endDate equals to DEFAULT_END_DATE
        defaultAdditionalPostShouldBeFound("endDate.equals=" + DEFAULT_END_DATE);

        // Get all the additionalPostList where endDate equals to UPDATED_END_DATE
        defaultAdditionalPostShouldNotBeFound("endDate.equals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByEndDateIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where endDate in DEFAULT_END_DATE or UPDATED_END_DATE
        defaultAdditionalPostShouldBeFound("endDate.in=" + DEFAULT_END_DATE + "," + UPDATED_END_DATE);

        // Get all the additionalPostList where endDate equals to UPDATED_END_DATE
        defaultAdditionalPostShouldNotBeFound("endDate.in=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByEndDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where endDate is not null
        defaultAdditionalPostShouldBeFound("endDate.specified=true");

        // Get all the additionalPostList where endDate is null
        defaultAdditionalPostShouldNotBeFound("endDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByEndDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where endDate greater than or equals to DEFAULT_END_DATE
        defaultAdditionalPostShouldBeFound("endDate.greaterOrEqualThan=" + DEFAULT_END_DATE);

        // Get all the additionalPostList where endDate greater than or equals to UPDATED_END_DATE
        defaultAdditionalPostShouldNotBeFound("endDate.greaterOrEqualThan=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByEndDateIsLessThanSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where endDate less than or equals to DEFAULT_END_DATE
        defaultAdditionalPostShouldNotBeFound("endDate.lessThan=" + DEFAULT_END_DATE);

        // Get all the additionalPostList where endDate less than or equals to UPDATED_END_DATE
        defaultAdditionalPostShouldBeFound("endDate.lessThan=" + UPDATED_END_DATE);
    }


    @Test
    @Transactional
    public void getAllAdditionalPostsByRemarkIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where remark equals to DEFAULT_REMARK
        defaultAdditionalPostShouldBeFound("remark.equals=" + DEFAULT_REMARK);

        // Get all the additionalPostList where remark equals to UPDATED_REMARK
        defaultAdditionalPostShouldNotBeFound("remark.equals=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByRemarkIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where remark in DEFAULT_REMARK or UPDATED_REMARK
        defaultAdditionalPostShouldBeFound("remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK);

        // Get all the additionalPostList where remark equals to UPDATED_REMARK
        defaultAdditionalPostShouldNotBeFound("remark.in=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByRemarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where remark is not null
        defaultAdditionalPostShouldBeFound("remark.specified=true");

        // Get all the additionalPostList where remark is null
        defaultAdditionalPostShouldNotBeFound("remark.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultAdditionalPostShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the additionalPostList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultAdditionalPostShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultAdditionalPostShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the additionalPostList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultAdditionalPostShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where isSelfVerify is not null
        defaultAdditionalPostShouldBeFound("isSelfVerify.specified=true");

        // Get all the additionalPostList where isSelfVerify is null
        defaultAdditionalPostShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultAdditionalPostShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the additionalPostList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultAdditionalPostShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultAdditionalPostShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the additionalPostList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultAdditionalPostShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        // Get all the additionalPostList where isHrVerify is not null
        defaultAdditionalPostShouldBeFound("isHrVerify.specified=true");

        // Get all the additionalPostList where isHrVerify is null
        defaultAdditionalPostShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdditionalPostsByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        additionalPost.setEmp(emp);
        additionalPostRepository.saveAndFlush(additionalPost);
        Long empId = emp.getId();

        // Get all the additionalPostList where emp equals to empId
        defaultAdditionalPostShouldBeFound("empId.equals=" + empId);

        // Get all the additionalPostList where emp equals to empId + 1
        defaultAdditionalPostShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAdditionalPostShouldBeFound(String filter) throws Exception {
        restAdditionalPostMockMvc.perform(get("/api/additional-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(additionalPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].dept").value(hasItem(DEFAULT_DEPT)))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restAdditionalPostMockMvc.perform(get("/api/additional-posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAdditionalPostShouldNotBeFound(String filter) throws Exception {
        restAdditionalPostMockMvc.perform(get("/api/additional-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAdditionalPostMockMvc.perform(get("/api/additional-posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAdditionalPost() throws Exception {
        // Get the additionalPost
        restAdditionalPostMockMvc.perform(get("/api/additional-posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdditionalPost() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        int databaseSizeBeforeUpdate = additionalPostRepository.findAll().size();

        // Update the additionalPost
        AdditionalPost updatedAdditionalPost = additionalPostRepository.findById(additionalPost.getId()).get();
        // Disconnect from session so that the updates on updatedAdditionalPost are not directly saved in db
        em.detach(updatedAdditionalPost);
        updatedAdditionalPost
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .dept(UPDATED_DEPT)
            .job(UPDATED_JOB)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .remark(UPDATED_REMARK)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        AdditionalPostDTO additionalPostDTO = additionalPostMapper.toDto(updatedAdditionalPost);

        restAdditionalPostMockMvc.perform(put("/api/additional-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(additionalPostDTO)))
            .andExpect(status().isOk());

        // Validate the AdditionalPost in the database
        List<AdditionalPost> additionalPostList = additionalPostRepository.findAll();
        assertThat(additionalPostList).hasSize(databaseSizeBeforeUpdate);
        AdditionalPost testAdditionalPost = additionalPostList.get(additionalPostList.size() - 1);
        assertThat(testAdditionalPost.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAdditionalPost.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdditionalPost.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testAdditionalPost.getDept()).isEqualTo(UPDATED_DEPT);
        assertThat(testAdditionalPost.getJob()).isEqualTo(UPDATED_JOB);
        assertThat(testAdditionalPost.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAdditionalPost.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testAdditionalPost.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testAdditionalPost.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testAdditionalPost.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingAdditionalPost() throws Exception {
        int databaseSizeBeforeUpdate = additionalPostRepository.findAll().size();

        // Create the AdditionalPost
        AdditionalPostDTO additionalPostDTO = additionalPostMapper.toDto(additionalPost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdditionalPostMockMvc.perform(put("/api/additional-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(additionalPostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdditionalPost in the database
        List<AdditionalPost> additionalPostList = additionalPostRepository.findAll();
        assertThat(additionalPostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdditionalPost() throws Exception {
        // Initialize the database
        additionalPostRepository.saveAndFlush(additionalPost);

        int databaseSizeBeforeDelete = additionalPostRepository.findAll().size();

        // Delete the additionalPost
        restAdditionalPostMockMvc.perform(delete("/api/additional-posts/{id}", additionalPost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdditionalPost> additionalPostList = additionalPostRepository.findAll();
        assertThat(additionalPostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdditionalPost.class);
        AdditionalPost additionalPost1 = new AdditionalPost();
        additionalPost1.setId(1L);
        AdditionalPost additionalPost2 = new AdditionalPost();
        additionalPost2.setId(additionalPost1.getId());
        assertThat(additionalPost1).isEqualTo(additionalPost2);
        additionalPost2.setId(2L);
        assertThat(additionalPost1).isNotEqualTo(additionalPost2);
        additionalPost1.setId(null);
        assertThat(additionalPost1).isNotEqualTo(additionalPost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdditionalPostDTO.class);
        AdditionalPostDTO additionalPostDTO1 = new AdditionalPostDTO();
        additionalPostDTO1.setId(1L);
        AdditionalPostDTO additionalPostDTO2 = new AdditionalPostDTO();
        assertThat(additionalPostDTO1).isNotEqualTo(additionalPostDTO2);
        additionalPostDTO2.setId(additionalPostDTO1.getId());
        assertThat(additionalPostDTO1).isEqualTo(additionalPostDTO2);
        additionalPostDTO2.setId(2L);
        assertThat(additionalPostDTO1).isNotEqualTo(additionalPostDTO2);
        additionalPostDTO1.setId(null);
        assertThat(additionalPostDTO1).isNotEqualTo(additionalPostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(additionalPostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(additionalPostMapper.fromId(null)).isNull();
    }
}
