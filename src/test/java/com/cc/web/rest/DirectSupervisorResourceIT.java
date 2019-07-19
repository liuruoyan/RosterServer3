package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.DirectSupervisor;
import com.cc.domain.Employee;
import com.cc.repository.DirectSupervisorRepository;
import com.cc.service.DirectSupervisorService;
import com.cc.service.dto.DirectSupervisorDTO;
import com.cc.service.mapper.DirectSupervisorMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.DirectSupervisorCriteria;
import com.cc.service.DirectSupervisorQueryService;

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
 * Integration tests for the {@Link DirectSupervisorResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class DirectSupervisorResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_A_SUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_A_SUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_A_SUP_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_A_SUP_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_B_SUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_B_SUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_B_SUP_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_B_SUP_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_F_SUB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_F_SUB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_F_SUB_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_F_SUB_PHONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private DirectSupervisorRepository directSupervisorRepository;

    @Autowired
    private DirectSupervisorMapper directSupervisorMapper;

    @Autowired
    private DirectSupervisorService directSupervisorService;

    @Autowired
    private DirectSupervisorQueryService directSupervisorQueryService;

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

    private MockMvc restDirectSupervisorMockMvc;

    private DirectSupervisor directSupervisor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DirectSupervisorResource directSupervisorResource = new DirectSupervisorResource(directSupervisorService, directSupervisorQueryService);
        this.restDirectSupervisorMockMvc = MockMvcBuilders.standaloneSetup(directSupervisorResource)
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
    public static DirectSupervisor createEntity(EntityManager em) {
        DirectSupervisor directSupervisor = new DirectSupervisor()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .aSupName(DEFAULT_A_SUP_NAME)
            .aSupPhone(DEFAULT_A_SUP_PHONE)
            .bSupName(DEFAULT_B_SUP_NAME)
            .bSupPhone(DEFAULT_B_SUP_PHONE)
            .fSubName(DEFAULT_F_SUB_NAME)
            .fSubPhone(DEFAULT_F_SUB_PHONE)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return directSupervisor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DirectSupervisor createUpdatedEntity(EntityManager em) {
        DirectSupervisor directSupervisor = new DirectSupervisor()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .aSupName(UPDATED_A_SUP_NAME)
            .aSupPhone(UPDATED_A_SUP_PHONE)
            .bSupName(UPDATED_B_SUP_NAME)
            .bSupPhone(UPDATED_B_SUP_PHONE)
            .fSubName(UPDATED_F_SUB_NAME)
            .fSubPhone(UPDATED_F_SUB_PHONE)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return directSupervisor;
    }

    @BeforeEach
    public void initTest() {
        directSupervisor = createEntity(em);
    }

    @Test
    @Transactional
    public void createDirectSupervisor() throws Exception {
        int databaseSizeBeforeCreate = directSupervisorRepository.findAll().size();

        // Create the DirectSupervisor
        DirectSupervisorDTO directSupervisorDTO = directSupervisorMapper.toDto(directSupervisor);
        restDirectSupervisorMockMvc.perform(post("/api/direct-supervisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directSupervisorDTO)))
            .andExpect(status().isCreated());

        // Validate the DirectSupervisor in the database
        List<DirectSupervisor> directSupervisorList = directSupervisorRepository.findAll();
        assertThat(directSupervisorList).hasSize(databaseSizeBeforeCreate + 1);
        DirectSupervisor testDirectSupervisor = directSupervisorList.get(directSupervisorList.size() - 1);
        assertThat(testDirectSupervisor.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDirectSupervisor.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDirectSupervisor.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testDirectSupervisor.getaSupName()).isEqualTo(DEFAULT_A_SUP_NAME);
        assertThat(testDirectSupervisor.getaSupPhone()).isEqualTo(DEFAULT_A_SUP_PHONE);
        assertThat(testDirectSupervisor.getbSupName()).isEqualTo(DEFAULT_B_SUP_NAME);
        assertThat(testDirectSupervisor.getbSupPhone()).isEqualTo(DEFAULT_B_SUP_PHONE);
        assertThat(testDirectSupervisor.getfSubName()).isEqualTo(DEFAULT_F_SUB_NAME);
        assertThat(testDirectSupervisor.getfSubPhone()).isEqualTo(DEFAULT_F_SUB_PHONE);
        assertThat(testDirectSupervisor.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testDirectSupervisor.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createDirectSupervisorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = directSupervisorRepository.findAll().size();

        // Create the DirectSupervisor with an existing ID
        directSupervisor.setId(1L);
        DirectSupervisorDTO directSupervisorDTO = directSupervisorMapper.toDto(directSupervisor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDirectSupervisorMockMvc.perform(post("/api/direct-supervisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directSupervisorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DirectSupervisor in the database
        List<DirectSupervisor> directSupervisorList = directSupervisorRepository.findAll();
        assertThat(directSupervisorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDirectSupervisors() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList
        restDirectSupervisorMockMvc.perform(get("/api/direct-supervisors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(directSupervisor.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].aSupName").value(hasItem(DEFAULT_A_SUP_NAME.toString())))
            .andExpect(jsonPath("$.[*].aSupPhone").value(hasItem(DEFAULT_A_SUP_PHONE.toString())))
            .andExpect(jsonPath("$.[*].bSupName").value(hasItem(DEFAULT_B_SUP_NAME.toString())))
            .andExpect(jsonPath("$.[*].bSupPhone").value(hasItem(DEFAULT_B_SUP_PHONE.toString())))
            .andExpect(jsonPath("$.[*].fSubName").value(hasItem(DEFAULT_F_SUB_NAME.toString())))
            .andExpect(jsonPath("$.[*].fSubPhone").value(hasItem(DEFAULT_F_SUB_PHONE.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDirectSupervisor() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get the directSupervisor
        restDirectSupervisorMockMvc.perform(get("/api/direct-supervisors/{id}", directSupervisor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(directSupervisor.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.aSupName").value(DEFAULT_A_SUP_NAME.toString()))
            .andExpect(jsonPath("$.aSupPhone").value(DEFAULT_A_SUP_PHONE.toString()))
            .andExpect(jsonPath("$.bSupName").value(DEFAULT_B_SUP_NAME.toString()))
            .andExpect(jsonPath("$.bSupPhone").value(DEFAULT_B_SUP_PHONE.toString()))
            .andExpect(jsonPath("$.fSubName").value(DEFAULT_F_SUB_NAME.toString()))
            .andExpect(jsonPath("$.fSubPhone").value(DEFAULT_F_SUB_PHONE.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where code equals to DEFAULT_CODE
        defaultDirectSupervisorShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the directSupervisorList where code equals to UPDATED_CODE
        defaultDirectSupervisorShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where code in DEFAULT_CODE or UPDATED_CODE
        defaultDirectSupervisorShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the directSupervisorList where code equals to UPDATED_CODE
        defaultDirectSupervisorShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where code is not null
        defaultDirectSupervisorShouldBeFound("code.specified=true");

        // Get all the directSupervisorList where code is null
        defaultDirectSupervisorShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where name equals to DEFAULT_NAME
        defaultDirectSupervisorShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the directSupervisorList where name equals to UPDATED_NAME
        defaultDirectSupervisorShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDirectSupervisorShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the directSupervisorList where name equals to UPDATED_NAME
        defaultDirectSupervisorShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where name is not null
        defaultDirectSupervisorShouldBeFound("name.specified=true");

        // Get all the directSupervisorList where name is null
        defaultDirectSupervisorShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where phone equals to DEFAULT_PHONE
        defaultDirectSupervisorShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the directSupervisorList where phone equals to UPDATED_PHONE
        defaultDirectSupervisorShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultDirectSupervisorShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the directSupervisorList where phone equals to UPDATED_PHONE
        defaultDirectSupervisorShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where phone is not null
        defaultDirectSupervisorShouldBeFound("phone.specified=true");

        // Get all the directSupervisorList where phone is null
        defaultDirectSupervisorShouldNotBeFound("phone.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByaSupNameIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where aSupName equals to DEFAULT_A_SUP_NAME
        defaultDirectSupervisorShouldBeFound("aSupName.equals=" + DEFAULT_A_SUP_NAME);

        // Get all the directSupervisorList where aSupName equals to UPDATED_A_SUP_NAME
        defaultDirectSupervisorShouldNotBeFound("aSupName.equals=" + UPDATED_A_SUP_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByaSupNameIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where aSupName in DEFAULT_A_SUP_NAME or UPDATED_A_SUP_NAME
        defaultDirectSupervisorShouldBeFound("aSupName.in=" + DEFAULT_A_SUP_NAME + "," + UPDATED_A_SUP_NAME);

        // Get all the directSupervisorList where aSupName equals to UPDATED_A_SUP_NAME
        defaultDirectSupervisorShouldNotBeFound("aSupName.in=" + UPDATED_A_SUP_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByaSupNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where aSupName is not null
        defaultDirectSupervisorShouldBeFound("aSupName.specified=true");

        // Get all the directSupervisorList where aSupName is null
        defaultDirectSupervisorShouldNotBeFound("aSupName.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByaSupPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where aSupPhone equals to DEFAULT_A_SUP_PHONE
        defaultDirectSupervisorShouldBeFound("aSupPhone.equals=" + DEFAULT_A_SUP_PHONE);

        // Get all the directSupervisorList where aSupPhone equals to UPDATED_A_SUP_PHONE
        defaultDirectSupervisorShouldNotBeFound("aSupPhone.equals=" + UPDATED_A_SUP_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByaSupPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where aSupPhone in DEFAULT_A_SUP_PHONE or UPDATED_A_SUP_PHONE
        defaultDirectSupervisorShouldBeFound("aSupPhone.in=" + DEFAULT_A_SUP_PHONE + "," + UPDATED_A_SUP_PHONE);

        // Get all the directSupervisorList where aSupPhone equals to UPDATED_A_SUP_PHONE
        defaultDirectSupervisorShouldNotBeFound("aSupPhone.in=" + UPDATED_A_SUP_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByaSupPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where aSupPhone is not null
        defaultDirectSupervisorShouldBeFound("aSupPhone.specified=true");

        // Get all the directSupervisorList where aSupPhone is null
        defaultDirectSupervisorShouldNotBeFound("aSupPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsBybSupNameIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where bSupName equals to DEFAULT_B_SUP_NAME
        defaultDirectSupervisorShouldBeFound("bSupName.equals=" + DEFAULT_B_SUP_NAME);

        // Get all the directSupervisorList where bSupName equals to UPDATED_B_SUP_NAME
        defaultDirectSupervisorShouldNotBeFound("bSupName.equals=" + UPDATED_B_SUP_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsBybSupNameIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where bSupName in DEFAULT_B_SUP_NAME or UPDATED_B_SUP_NAME
        defaultDirectSupervisorShouldBeFound("bSupName.in=" + DEFAULT_B_SUP_NAME + "," + UPDATED_B_SUP_NAME);

        // Get all the directSupervisorList where bSupName equals to UPDATED_B_SUP_NAME
        defaultDirectSupervisorShouldNotBeFound("bSupName.in=" + UPDATED_B_SUP_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsBybSupNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where bSupName is not null
        defaultDirectSupervisorShouldBeFound("bSupName.specified=true");

        // Get all the directSupervisorList where bSupName is null
        defaultDirectSupervisorShouldNotBeFound("bSupName.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsBybSupPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where bSupPhone equals to DEFAULT_B_SUP_PHONE
        defaultDirectSupervisorShouldBeFound("bSupPhone.equals=" + DEFAULT_B_SUP_PHONE);

        // Get all the directSupervisorList where bSupPhone equals to UPDATED_B_SUP_PHONE
        defaultDirectSupervisorShouldNotBeFound("bSupPhone.equals=" + UPDATED_B_SUP_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsBybSupPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where bSupPhone in DEFAULT_B_SUP_PHONE or UPDATED_B_SUP_PHONE
        defaultDirectSupervisorShouldBeFound("bSupPhone.in=" + DEFAULT_B_SUP_PHONE + "," + UPDATED_B_SUP_PHONE);

        // Get all the directSupervisorList where bSupPhone equals to UPDATED_B_SUP_PHONE
        defaultDirectSupervisorShouldNotBeFound("bSupPhone.in=" + UPDATED_B_SUP_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsBybSupPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where bSupPhone is not null
        defaultDirectSupervisorShouldBeFound("bSupPhone.specified=true");

        // Get all the directSupervisorList where bSupPhone is null
        defaultDirectSupervisorShouldNotBeFound("bSupPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByfSubNameIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where fSubName equals to DEFAULT_F_SUB_NAME
        defaultDirectSupervisorShouldBeFound("fSubName.equals=" + DEFAULT_F_SUB_NAME);

        // Get all the directSupervisorList where fSubName equals to UPDATED_F_SUB_NAME
        defaultDirectSupervisorShouldNotBeFound("fSubName.equals=" + UPDATED_F_SUB_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByfSubNameIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where fSubName in DEFAULT_F_SUB_NAME or UPDATED_F_SUB_NAME
        defaultDirectSupervisorShouldBeFound("fSubName.in=" + DEFAULT_F_SUB_NAME + "," + UPDATED_F_SUB_NAME);

        // Get all the directSupervisorList where fSubName equals to UPDATED_F_SUB_NAME
        defaultDirectSupervisorShouldNotBeFound("fSubName.in=" + UPDATED_F_SUB_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByfSubNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where fSubName is not null
        defaultDirectSupervisorShouldBeFound("fSubName.specified=true");

        // Get all the directSupervisorList where fSubName is null
        defaultDirectSupervisorShouldNotBeFound("fSubName.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByfSubPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where fSubPhone equals to DEFAULT_F_SUB_PHONE
        defaultDirectSupervisorShouldBeFound("fSubPhone.equals=" + DEFAULT_F_SUB_PHONE);

        // Get all the directSupervisorList where fSubPhone equals to UPDATED_F_SUB_PHONE
        defaultDirectSupervisorShouldNotBeFound("fSubPhone.equals=" + UPDATED_F_SUB_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByfSubPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where fSubPhone in DEFAULT_F_SUB_PHONE or UPDATED_F_SUB_PHONE
        defaultDirectSupervisorShouldBeFound("fSubPhone.in=" + DEFAULT_F_SUB_PHONE + "," + UPDATED_F_SUB_PHONE);

        // Get all the directSupervisorList where fSubPhone equals to UPDATED_F_SUB_PHONE
        defaultDirectSupervisorShouldNotBeFound("fSubPhone.in=" + UPDATED_F_SUB_PHONE);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByfSubPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where fSubPhone is not null
        defaultDirectSupervisorShouldBeFound("fSubPhone.specified=true");

        // Get all the directSupervisorList where fSubPhone is null
        defaultDirectSupervisorShouldNotBeFound("fSubPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultDirectSupervisorShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the directSupervisorList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultDirectSupervisorShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultDirectSupervisorShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the directSupervisorList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultDirectSupervisorShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where isSelfVerify is not null
        defaultDirectSupervisorShouldBeFound("isSelfVerify.specified=true");

        // Get all the directSupervisorList where isSelfVerify is null
        defaultDirectSupervisorShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultDirectSupervisorShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the directSupervisorList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultDirectSupervisorShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultDirectSupervisorShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the directSupervisorList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultDirectSupervisorShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        // Get all the directSupervisorList where isHrVerify is not null
        defaultDirectSupervisorShouldBeFound("isHrVerify.specified=true");

        // Get all the directSupervisorList where isHrVerify is null
        defaultDirectSupervisorShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllDirectSupervisorsByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        directSupervisor.setEmp(emp);
        directSupervisorRepository.saveAndFlush(directSupervisor);
        Long empId = emp.getId();

        // Get all the directSupervisorList where emp equals to empId
        defaultDirectSupervisorShouldBeFound("empId.equals=" + empId);

        // Get all the directSupervisorList where emp equals to empId + 1
        defaultDirectSupervisorShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDirectSupervisorShouldBeFound(String filter) throws Exception {
        restDirectSupervisorMockMvc.perform(get("/api/direct-supervisors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(directSupervisor.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].aSupName").value(hasItem(DEFAULT_A_SUP_NAME)))
            .andExpect(jsonPath("$.[*].aSupPhone").value(hasItem(DEFAULT_A_SUP_PHONE)))
            .andExpect(jsonPath("$.[*].bSupName").value(hasItem(DEFAULT_B_SUP_NAME)))
            .andExpect(jsonPath("$.[*].bSupPhone").value(hasItem(DEFAULT_B_SUP_PHONE)))
            .andExpect(jsonPath("$.[*].fSubName").value(hasItem(DEFAULT_F_SUB_NAME)))
            .andExpect(jsonPath("$.[*].fSubPhone").value(hasItem(DEFAULT_F_SUB_PHONE)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restDirectSupervisorMockMvc.perform(get("/api/direct-supervisors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDirectSupervisorShouldNotBeFound(String filter) throws Exception {
        restDirectSupervisorMockMvc.perform(get("/api/direct-supervisors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDirectSupervisorMockMvc.perform(get("/api/direct-supervisors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDirectSupervisor() throws Exception {
        // Get the directSupervisor
        restDirectSupervisorMockMvc.perform(get("/api/direct-supervisors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDirectSupervisor() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        int databaseSizeBeforeUpdate = directSupervisorRepository.findAll().size();

        // Update the directSupervisor
        DirectSupervisor updatedDirectSupervisor = directSupervisorRepository.findById(directSupervisor.getId()).get();
        // Disconnect from session so that the updates on updatedDirectSupervisor are not directly saved in db
        em.detach(updatedDirectSupervisor);
        updatedDirectSupervisor
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .aSupName(UPDATED_A_SUP_NAME)
            .aSupPhone(UPDATED_A_SUP_PHONE)
            .bSupName(UPDATED_B_SUP_NAME)
            .bSupPhone(UPDATED_B_SUP_PHONE)
            .fSubName(UPDATED_F_SUB_NAME)
            .fSubPhone(UPDATED_F_SUB_PHONE)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        DirectSupervisorDTO directSupervisorDTO = directSupervisorMapper.toDto(updatedDirectSupervisor);

        restDirectSupervisorMockMvc.perform(put("/api/direct-supervisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directSupervisorDTO)))
            .andExpect(status().isOk());

        // Validate the DirectSupervisor in the database
        List<DirectSupervisor> directSupervisorList = directSupervisorRepository.findAll();
        assertThat(directSupervisorList).hasSize(databaseSizeBeforeUpdate);
        DirectSupervisor testDirectSupervisor = directSupervisorList.get(directSupervisorList.size() - 1);
        assertThat(testDirectSupervisor.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDirectSupervisor.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDirectSupervisor.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testDirectSupervisor.getaSupName()).isEqualTo(UPDATED_A_SUP_NAME);
        assertThat(testDirectSupervisor.getaSupPhone()).isEqualTo(UPDATED_A_SUP_PHONE);
        assertThat(testDirectSupervisor.getbSupName()).isEqualTo(UPDATED_B_SUP_NAME);
        assertThat(testDirectSupervisor.getbSupPhone()).isEqualTo(UPDATED_B_SUP_PHONE);
        assertThat(testDirectSupervisor.getfSubName()).isEqualTo(UPDATED_F_SUB_NAME);
        assertThat(testDirectSupervisor.getfSubPhone()).isEqualTo(UPDATED_F_SUB_PHONE);
        assertThat(testDirectSupervisor.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testDirectSupervisor.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingDirectSupervisor() throws Exception {
        int databaseSizeBeforeUpdate = directSupervisorRepository.findAll().size();

        // Create the DirectSupervisor
        DirectSupervisorDTO directSupervisorDTO = directSupervisorMapper.toDto(directSupervisor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDirectSupervisorMockMvc.perform(put("/api/direct-supervisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directSupervisorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DirectSupervisor in the database
        List<DirectSupervisor> directSupervisorList = directSupervisorRepository.findAll();
        assertThat(directSupervisorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDirectSupervisor() throws Exception {
        // Initialize the database
        directSupervisorRepository.saveAndFlush(directSupervisor);

        int databaseSizeBeforeDelete = directSupervisorRepository.findAll().size();

        // Delete the directSupervisor
        restDirectSupervisorMockMvc.perform(delete("/api/direct-supervisors/{id}", directSupervisor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DirectSupervisor> directSupervisorList = directSupervisorRepository.findAll();
        assertThat(directSupervisorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DirectSupervisor.class);
        DirectSupervisor directSupervisor1 = new DirectSupervisor();
        directSupervisor1.setId(1L);
        DirectSupervisor directSupervisor2 = new DirectSupervisor();
        directSupervisor2.setId(directSupervisor1.getId());
        assertThat(directSupervisor1).isEqualTo(directSupervisor2);
        directSupervisor2.setId(2L);
        assertThat(directSupervisor1).isNotEqualTo(directSupervisor2);
        directSupervisor1.setId(null);
        assertThat(directSupervisor1).isNotEqualTo(directSupervisor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DirectSupervisorDTO.class);
        DirectSupervisorDTO directSupervisorDTO1 = new DirectSupervisorDTO();
        directSupervisorDTO1.setId(1L);
        DirectSupervisorDTO directSupervisorDTO2 = new DirectSupervisorDTO();
        assertThat(directSupervisorDTO1).isNotEqualTo(directSupervisorDTO2);
        directSupervisorDTO2.setId(directSupervisorDTO1.getId());
        assertThat(directSupervisorDTO1).isEqualTo(directSupervisorDTO2);
        directSupervisorDTO2.setId(2L);
        assertThat(directSupervisorDTO1).isNotEqualTo(directSupervisorDTO2);
        directSupervisorDTO1.setId(null);
        assertThat(directSupervisorDTO1).isNotEqualTo(directSupervisorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(directSupervisorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(directSupervisorMapper.fromId(null)).isNull();
    }
}
