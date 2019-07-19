package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumEducation;
import com.cc.domain.EnumEducation;
import com.cc.repository.EnumEducationRepository;
import com.cc.service.EnumEducationService;
import com.cc.service.dto.EnumEducationDTO;
import com.cc.service.mapper.EnumEducationMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumEducationCriteria;
import com.cc.service.EnumEducationQueryService;

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
 * Integration tests for the {@Link EnumEducationResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumEducationResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumEducationRepository enumEducationRepository;

    @Autowired
    private EnumEducationMapper enumEducationMapper;

    @Autowired
    private EnumEducationService enumEducationService;

    @Autowired
    private EnumEducationQueryService enumEducationQueryService;

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

    private MockMvc restEnumEducationMockMvc;

    private EnumEducation enumEducation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumEducationResource enumEducationResource = new EnumEducationResource(enumEducationService, enumEducationQueryService);
        this.restEnumEducationMockMvc = MockMvcBuilders.standaloneSetup(enumEducationResource)
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
    public static EnumEducation createEntity(EntityManager em) {
        EnumEducation enumEducation = new EnumEducation()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumEducation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumEducation createUpdatedEntity(EntityManager em) {
        EnumEducation enumEducation = new EnumEducation()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumEducation;
    }

    @BeforeEach
    public void initTest() {
        enumEducation = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumEducation() throws Exception {
        int databaseSizeBeforeCreate = enumEducationRepository.findAll().size();

        // Create the EnumEducation
        EnumEducationDTO enumEducationDTO = enumEducationMapper.toDto(enumEducation);
        restEnumEducationMockMvc.perform(post("/api/enum-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEducationDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumEducation in the database
        List<EnumEducation> enumEducationList = enumEducationRepository.findAll();
        assertThat(enumEducationList).hasSize(databaseSizeBeforeCreate + 1);
        EnumEducation testEnumEducation = enumEducationList.get(enumEducationList.size() - 1);
        assertThat(testEnumEducation.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumEducation.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumEducation.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumEducationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumEducationRepository.findAll().size();

        // Create the EnumEducation with an existing ID
        enumEducation.setId(1L);
        EnumEducationDTO enumEducationDTO = enumEducationMapper.toDto(enumEducation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumEducationMockMvc.perform(post("/api/enum-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEducationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEducation in the database
        List<EnumEducation> enumEducationList = enumEducationRepository.findAll();
        assertThat(enumEducationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumEducations() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList
        restEnumEducationMockMvc.perform(get("/api/enum-educations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEducation.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumEducation() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get the enumEducation
        restEnumEducationMockMvc.perform(get("/api/enum-educations/{id}", enumEducation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumEducation.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where valuez equals to DEFAULT_VALUEZ
        defaultEnumEducationShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumEducationList where valuez equals to UPDATED_VALUEZ
        defaultEnumEducationShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumEducationShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumEducationList where valuez equals to UPDATED_VALUEZ
        defaultEnumEducationShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where valuez is not null
        defaultEnumEducationShouldBeFound("valuez.specified=true");

        // Get all the enumEducationList where valuez is null
        defaultEnumEducationShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where orderz equals to DEFAULT_ORDERZ
        defaultEnumEducationShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumEducationList where orderz equals to UPDATED_ORDERZ
        defaultEnumEducationShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumEducationShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumEducationList where orderz equals to UPDATED_ORDERZ
        defaultEnumEducationShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where orderz is not null
        defaultEnumEducationShouldBeFound("orderz.specified=true");

        // Get all the enumEducationList where orderz is null
        defaultEnumEducationShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumEducationShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumEducationList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumEducationShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumEducationShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumEducationList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumEducationShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumEducationsByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumEducationShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumEducationList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEducationShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumEducationShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumEducationList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEducationShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        // Get all the enumEducationList where tenentCode is not null
        defaultEnumEducationShouldBeFound("tenentCode.specified=true");

        // Get all the enumEducationList where tenentCode is null
        defaultEnumEducationShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEducationsByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEducation parent = EnumEducationResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumEducation.setParent(parent);
        enumEducationRepository.saveAndFlush(enumEducation);
        Long parentId = parent.getId();

        // Get all the enumEducationList where parent equals to parentId
        defaultEnumEducationShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumEducationList where parent equals to parentId + 1
        defaultEnumEducationShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumEducationShouldBeFound(String filter) throws Exception {
        restEnumEducationMockMvc.perform(get("/api/enum-educations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEducation.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumEducationMockMvc.perform(get("/api/enum-educations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumEducationShouldNotBeFound(String filter) throws Exception {
        restEnumEducationMockMvc.perform(get("/api/enum-educations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumEducationMockMvc.perform(get("/api/enum-educations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumEducation() throws Exception {
        // Get the enumEducation
        restEnumEducationMockMvc.perform(get("/api/enum-educations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumEducation() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        int databaseSizeBeforeUpdate = enumEducationRepository.findAll().size();

        // Update the enumEducation
        EnumEducation updatedEnumEducation = enumEducationRepository.findById(enumEducation.getId()).get();
        // Disconnect from session so that the updates on updatedEnumEducation are not directly saved in db
        em.detach(updatedEnumEducation);
        updatedEnumEducation
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumEducationDTO enumEducationDTO = enumEducationMapper.toDto(updatedEnumEducation);

        restEnumEducationMockMvc.perform(put("/api/enum-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEducationDTO)))
            .andExpect(status().isOk());

        // Validate the EnumEducation in the database
        List<EnumEducation> enumEducationList = enumEducationRepository.findAll();
        assertThat(enumEducationList).hasSize(databaseSizeBeforeUpdate);
        EnumEducation testEnumEducation = enumEducationList.get(enumEducationList.size() - 1);
        assertThat(testEnumEducation.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumEducation.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumEducation.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumEducation() throws Exception {
        int databaseSizeBeforeUpdate = enumEducationRepository.findAll().size();

        // Create the EnumEducation
        EnumEducationDTO enumEducationDTO = enumEducationMapper.toDto(enumEducation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumEducationMockMvc.perform(put("/api/enum-educations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEducationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEducation in the database
        List<EnumEducation> enumEducationList = enumEducationRepository.findAll();
        assertThat(enumEducationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumEducation() throws Exception {
        // Initialize the database
        enumEducationRepository.saveAndFlush(enumEducation);

        int databaseSizeBeforeDelete = enumEducationRepository.findAll().size();

        // Delete the enumEducation
        restEnumEducationMockMvc.perform(delete("/api/enum-educations/{id}", enumEducation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumEducation> enumEducationList = enumEducationRepository.findAll();
        assertThat(enumEducationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEducation.class);
        EnumEducation enumEducation1 = new EnumEducation();
        enumEducation1.setId(1L);
        EnumEducation enumEducation2 = new EnumEducation();
        enumEducation2.setId(enumEducation1.getId());
        assertThat(enumEducation1).isEqualTo(enumEducation2);
        enumEducation2.setId(2L);
        assertThat(enumEducation1).isNotEqualTo(enumEducation2);
        enumEducation1.setId(null);
        assertThat(enumEducation1).isNotEqualTo(enumEducation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEducationDTO.class);
        EnumEducationDTO enumEducationDTO1 = new EnumEducationDTO();
        enumEducationDTO1.setId(1L);
        EnumEducationDTO enumEducationDTO2 = new EnumEducationDTO();
        assertThat(enumEducationDTO1).isNotEqualTo(enumEducationDTO2);
        enumEducationDTO2.setId(enumEducationDTO1.getId());
        assertThat(enumEducationDTO1).isEqualTo(enumEducationDTO2);
        enumEducationDTO2.setId(2L);
        assertThat(enumEducationDTO1).isNotEqualTo(enumEducationDTO2);
        enumEducationDTO1.setId(null);
        assertThat(enumEducationDTO1).isNotEqualTo(enumEducationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumEducationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumEducationMapper.fromId(null)).isNull();
    }
}
