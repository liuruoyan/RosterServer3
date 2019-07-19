package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumHighestEducation;
import com.cc.domain.EnumHighestEducation;
import com.cc.repository.EnumHighestEducationRepository;
import com.cc.service.EnumHighestEducationService;
import com.cc.service.dto.EnumHighestEducationDTO;
import com.cc.service.mapper.EnumHighestEducationMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumHighestEducationCriteria;
import com.cc.service.EnumHighestEducationQueryService;

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
 * Integration tests for the {@Link EnumHighestEducationResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumHighestEducationResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumHighestEducationRepository enumHighestEducationRepository;

    @Autowired
    private EnumHighestEducationMapper enumHighestEducationMapper;

    @Autowired
    private EnumHighestEducationService enumHighestEducationService;

    @Autowired
    private EnumHighestEducationQueryService enumHighestEducationQueryService;

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

    private MockMvc restEnumHighestEducationMockMvc;

    private EnumHighestEducation enumHighestEducation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumHighestEducationResource enumHighestEducationResource = new EnumHighestEducationResource(enumHighestEducationService, enumHighestEducationQueryService);
        this.restEnumHighestEducationMockMvc = MockMvcBuilders.standaloneSetup(enumHighestEducationResource)
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
    public static EnumHighestEducation createEntity(EntityManager em) {
        EnumHighestEducation enumHighestEducation = new EnumHighestEducation()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumHighestEducation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumHighestEducation createUpdatedEntity(EntityManager em) {
        EnumHighestEducation enumHighestEducation = new EnumHighestEducation()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumHighestEducation;
    }

    @BeforeEach
    public void initTest() {
        enumHighestEducation = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumHighestEducation() throws Exception {
        int databaseSizeBeforeCreate = enumHighestEducationRepository.findAll().size();

        // Create the EnumHighestEducation
        EnumHighestEducationDTO enumHighestEducationDTO = enumHighestEducationMapper.toDto(enumHighestEducation);
        restEnumHighestEducationMockMvc.perform(post("/api/enum-highest-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumHighestEducationDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumHighestEducation in the database
        List<EnumHighestEducation> enumHighestEducationList = enumHighestEducationRepository.findAll();
        assertThat(enumHighestEducationList).hasSize(databaseSizeBeforeCreate + 1);
        EnumHighestEducation testEnumHighestEducation = enumHighestEducationList.get(enumHighestEducationList.size() - 1);
        assertThat(testEnumHighestEducation.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumHighestEducation.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumHighestEducation.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumHighestEducationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumHighestEducationRepository.findAll().size();

        // Create the EnumHighestEducation with an existing ID
        enumHighestEducation.setId(1L);
        EnumHighestEducationDTO enumHighestEducationDTO = enumHighestEducationMapper.toDto(enumHighestEducation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumHighestEducationMockMvc.perform(post("/api/enum-highest-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumHighestEducationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumHighestEducation in the database
        List<EnumHighestEducation> enumHighestEducationList = enumHighestEducationRepository.findAll();
        assertThat(enumHighestEducationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumHighestEducations() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList
        restEnumHighestEducationMockMvc.perform(get("/api/enum-highest-educations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumHighestEducation.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumHighestEducation() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get the enumHighestEducation
        restEnumHighestEducationMockMvc.perform(get("/api/enum-highest-educations/{id}", enumHighestEducation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumHighestEducation.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where valuez equals to DEFAULT_VALUEZ
        defaultEnumHighestEducationShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumHighestEducationList where valuez equals to UPDATED_VALUEZ
        defaultEnumHighestEducationShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumHighestEducationShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumHighestEducationList where valuez equals to UPDATED_VALUEZ
        defaultEnumHighestEducationShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where valuez is not null
        defaultEnumHighestEducationShouldBeFound("valuez.specified=true");

        // Get all the enumHighestEducationList where valuez is null
        defaultEnumHighestEducationShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where orderz equals to DEFAULT_ORDERZ
        defaultEnumHighestEducationShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumHighestEducationList where orderz equals to UPDATED_ORDERZ
        defaultEnumHighestEducationShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumHighestEducationShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumHighestEducationList where orderz equals to UPDATED_ORDERZ
        defaultEnumHighestEducationShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where orderz is not null
        defaultEnumHighestEducationShouldBeFound("orderz.specified=true");

        // Get all the enumHighestEducationList where orderz is null
        defaultEnumHighestEducationShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumHighestEducationShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumHighestEducationList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumHighestEducationShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumHighestEducationShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumHighestEducationList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumHighestEducationShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumHighestEducationsByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumHighestEducationShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumHighestEducationList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumHighestEducationShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumHighestEducationShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumHighestEducationList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumHighestEducationShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        // Get all the enumHighestEducationList where tenentCode is not null
        defaultEnumHighestEducationShouldBeFound("tenentCode.specified=true");

        // Get all the enumHighestEducationList where tenentCode is null
        defaultEnumHighestEducationShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumHighestEducationsByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumHighestEducation parent = EnumHighestEducationResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumHighestEducation.setParent(parent);
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);
        Long parentId = parent.getId();

        // Get all the enumHighestEducationList where parent equals to parentId
        defaultEnumHighestEducationShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumHighestEducationList where parent equals to parentId + 1
        defaultEnumHighestEducationShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumHighestEducationShouldBeFound(String filter) throws Exception {
        restEnumHighestEducationMockMvc.perform(get("/api/enum-highest-educations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumHighestEducation.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumHighestEducationMockMvc.perform(get("/api/enum-highest-educations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumHighestEducationShouldNotBeFound(String filter) throws Exception {
        restEnumHighestEducationMockMvc.perform(get("/api/enum-highest-educations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumHighestEducationMockMvc.perform(get("/api/enum-highest-educations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumHighestEducation() throws Exception {
        // Get the enumHighestEducation
        restEnumHighestEducationMockMvc.perform(get("/api/enum-highest-educations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumHighestEducation() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        int databaseSizeBeforeUpdate = enumHighestEducationRepository.findAll().size();

        // Update the enumHighestEducation
        EnumHighestEducation updatedEnumHighestEducation = enumHighestEducationRepository.findById(enumHighestEducation.getId()).get();
        // Disconnect from session so that the updates on updatedEnumHighestEducation are not directly saved in db
        em.detach(updatedEnumHighestEducation);
        updatedEnumHighestEducation
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumHighestEducationDTO enumHighestEducationDTO = enumHighestEducationMapper.toDto(updatedEnumHighestEducation);

        restEnumHighestEducationMockMvc.perform(put("/api/enum-highest-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumHighestEducationDTO)))
            .andExpect(status().isOk());

        // Validate the EnumHighestEducation in the database
        List<EnumHighestEducation> enumHighestEducationList = enumHighestEducationRepository.findAll();
        assertThat(enumHighestEducationList).hasSize(databaseSizeBeforeUpdate);
        EnumHighestEducation testEnumHighestEducation = enumHighestEducationList.get(enumHighestEducationList.size() - 1);
        assertThat(testEnumHighestEducation.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumHighestEducation.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumHighestEducation.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumHighestEducation() throws Exception {
        int databaseSizeBeforeUpdate = enumHighestEducationRepository.findAll().size();

        // Create the EnumHighestEducation
        EnumHighestEducationDTO enumHighestEducationDTO = enumHighestEducationMapper.toDto(enumHighestEducation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumHighestEducationMockMvc.perform(put("/api/enum-highest-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumHighestEducationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumHighestEducation in the database
        List<EnumHighestEducation> enumHighestEducationList = enumHighestEducationRepository.findAll();
        assertThat(enumHighestEducationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumHighestEducation() throws Exception {
        // Initialize the database
        enumHighestEducationRepository.saveAndFlush(enumHighestEducation);

        int databaseSizeBeforeDelete = enumHighestEducationRepository.findAll().size();

        // Delete the enumHighestEducation
        restEnumHighestEducationMockMvc.perform(delete("/api/enum-highest-educations/{id}", enumHighestEducation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumHighestEducation> enumHighestEducationList = enumHighestEducationRepository.findAll();
        assertThat(enumHighestEducationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumHighestEducation.class);
        EnumHighestEducation enumHighestEducation1 = new EnumHighestEducation();
        enumHighestEducation1.setId(1L);
        EnumHighestEducation enumHighestEducation2 = new EnumHighestEducation();
        enumHighestEducation2.setId(enumHighestEducation1.getId());
        assertThat(enumHighestEducation1).isEqualTo(enumHighestEducation2);
        enumHighestEducation2.setId(2L);
        assertThat(enumHighestEducation1).isNotEqualTo(enumHighestEducation2);
        enumHighestEducation1.setId(null);
        assertThat(enumHighestEducation1).isNotEqualTo(enumHighestEducation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumHighestEducationDTO.class);
        EnumHighestEducationDTO enumHighestEducationDTO1 = new EnumHighestEducationDTO();
        enumHighestEducationDTO1.setId(1L);
        EnumHighestEducationDTO enumHighestEducationDTO2 = new EnumHighestEducationDTO();
        assertThat(enumHighestEducationDTO1).isNotEqualTo(enumHighestEducationDTO2);
        enumHighestEducationDTO2.setId(enumHighestEducationDTO1.getId());
        assertThat(enumHighestEducationDTO1).isEqualTo(enumHighestEducationDTO2);
        enumHighestEducationDTO2.setId(2L);
        assertThat(enumHighestEducationDTO1).isNotEqualTo(enumHighestEducationDTO2);
        enumHighestEducationDTO1.setId(null);
        assertThat(enumHighestEducationDTO1).isNotEqualTo(enumHighestEducationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumHighestEducationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumHighestEducationMapper.fromId(null)).isNull();
    }
}
