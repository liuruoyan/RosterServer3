package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumGender;
import com.cc.domain.EnumGender;
import com.cc.repository.EnumGenderRepository;
import com.cc.service.EnumGenderService;
import com.cc.service.dto.EnumGenderDTO;
import com.cc.service.mapper.EnumGenderMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumGenderCriteria;
import com.cc.service.EnumGenderQueryService;

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
 * Integration tests for the {@Link EnumGenderResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumGenderResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumGenderRepository enumGenderRepository;

    @Autowired
    private EnumGenderMapper enumGenderMapper;

    @Autowired
    private EnumGenderService enumGenderService;

    @Autowired
    private EnumGenderQueryService enumGenderQueryService;

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

    private MockMvc restEnumGenderMockMvc;

    private EnumGender enumGender;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumGenderResource enumGenderResource = new EnumGenderResource(enumGenderService, enumGenderQueryService);
        this.restEnumGenderMockMvc = MockMvcBuilders.standaloneSetup(enumGenderResource)
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
    public static EnumGender createEntity(EntityManager em) {
        EnumGender enumGender = new EnumGender()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumGender;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumGender createUpdatedEntity(EntityManager em) {
        EnumGender enumGender = new EnumGender()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumGender;
    }

    @BeforeEach
    public void initTest() {
        enumGender = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumGender() throws Exception {
        int databaseSizeBeforeCreate = enumGenderRepository.findAll().size();

        // Create the EnumGender
        EnumGenderDTO enumGenderDTO = enumGenderMapper.toDto(enumGender);
        restEnumGenderMockMvc.perform(post("/api/enum-genders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumGenderDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumGender in the database
        List<EnumGender> enumGenderList = enumGenderRepository.findAll();
        assertThat(enumGenderList).hasSize(databaseSizeBeforeCreate + 1);
        EnumGender testEnumGender = enumGenderList.get(enumGenderList.size() - 1);
        assertThat(testEnumGender.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumGender.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumGender.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumGenderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumGenderRepository.findAll().size();

        // Create the EnumGender with an existing ID
        enumGender.setId(1L);
        EnumGenderDTO enumGenderDTO = enumGenderMapper.toDto(enumGender);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumGenderMockMvc.perform(post("/api/enum-genders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumGenderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumGender in the database
        List<EnumGender> enumGenderList = enumGenderRepository.findAll();
        assertThat(enumGenderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumGenders() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList
        restEnumGenderMockMvc.perform(get("/api/enum-genders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumGender.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumGender() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get the enumGender
        restEnumGenderMockMvc.perform(get("/api/enum-genders/{id}", enumGender.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumGender.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumGendersByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where valuez equals to DEFAULT_VALUEZ
        defaultEnumGenderShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumGenderList where valuez equals to UPDATED_VALUEZ
        defaultEnumGenderShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumGendersByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumGenderShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumGenderList where valuez equals to UPDATED_VALUEZ
        defaultEnumGenderShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumGendersByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where valuez is not null
        defaultEnumGenderShouldBeFound("valuez.specified=true");

        // Get all the enumGenderList where valuez is null
        defaultEnumGenderShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumGendersByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where orderz equals to DEFAULT_ORDERZ
        defaultEnumGenderShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumGenderList where orderz equals to UPDATED_ORDERZ
        defaultEnumGenderShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumGendersByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumGenderShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumGenderList where orderz equals to UPDATED_ORDERZ
        defaultEnumGenderShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumGendersByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where orderz is not null
        defaultEnumGenderShouldBeFound("orderz.specified=true");

        // Get all the enumGenderList where orderz is null
        defaultEnumGenderShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumGendersByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumGenderShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumGenderList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumGenderShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumGendersByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumGenderShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumGenderList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumGenderShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumGendersByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumGenderShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumGenderList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumGenderShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumGendersByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumGenderShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumGenderList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumGenderShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumGendersByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        // Get all the enumGenderList where tenentCode is not null
        defaultEnumGenderShouldBeFound("tenentCode.specified=true");

        // Get all the enumGenderList where tenentCode is null
        defaultEnumGenderShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumGendersByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumGender parent = EnumGenderResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumGender.setParent(parent);
        enumGenderRepository.saveAndFlush(enumGender);
        Long parentId = parent.getId();

        // Get all the enumGenderList where parent equals to parentId
        defaultEnumGenderShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumGenderList where parent equals to parentId + 1
        defaultEnumGenderShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumGenderShouldBeFound(String filter) throws Exception {
        restEnumGenderMockMvc.perform(get("/api/enum-genders?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumGender.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumGenderMockMvc.perform(get("/api/enum-genders/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumGenderShouldNotBeFound(String filter) throws Exception {
        restEnumGenderMockMvc.perform(get("/api/enum-genders?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumGenderMockMvc.perform(get("/api/enum-genders/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumGender() throws Exception {
        // Get the enumGender
        restEnumGenderMockMvc.perform(get("/api/enum-genders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumGender() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        int databaseSizeBeforeUpdate = enumGenderRepository.findAll().size();

        // Update the enumGender
        EnumGender updatedEnumGender = enumGenderRepository.findById(enumGender.getId()).get();
        // Disconnect from session so that the updates on updatedEnumGender are not directly saved in db
        em.detach(updatedEnumGender);
        updatedEnumGender
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumGenderDTO enumGenderDTO = enumGenderMapper.toDto(updatedEnumGender);

        restEnumGenderMockMvc.perform(put("/api/enum-genders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumGenderDTO)))
            .andExpect(status().isOk());

        // Validate the EnumGender in the database
        List<EnumGender> enumGenderList = enumGenderRepository.findAll();
        assertThat(enumGenderList).hasSize(databaseSizeBeforeUpdate);
        EnumGender testEnumGender = enumGenderList.get(enumGenderList.size() - 1);
        assertThat(testEnumGender.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumGender.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumGender.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumGender() throws Exception {
        int databaseSizeBeforeUpdate = enumGenderRepository.findAll().size();

        // Create the EnumGender
        EnumGenderDTO enumGenderDTO = enumGenderMapper.toDto(enumGender);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumGenderMockMvc.perform(put("/api/enum-genders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumGenderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumGender in the database
        List<EnumGender> enumGenderList = enumGenderRepository.findAll();
        assertThat(enumGenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumGender() throws Exception {
        // Initialize the database
        enumGenderRepository.saveAndFlush(enumGender);

        int databaseSizeBeforeDelete = enumGenderRepository.findAll().size();

        // Delete the enumGender
        restEnumGenderMockMvc.perform(delete("/api/enum-genders/{id}", enumGender.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumGender> enumGenderList = enumGenderRepository.findAll();
        assertThat(enumGenderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumGender.class);
        EnumGender enumGender1 = new EnumGender();
        enumGender1.setId(1L);
        EnumGender enumGender2 = new EnumGender();
        enumGender2.setId(enumGender1.getId());
        assertThat(enumGender1).isEqualTo(enumGender2);
        enumGender2.setId(2L);
        assertThat(enumGender1).isNotEqualTo(enumGender2);
        enumGender1.setId(null);
        assertThat(enumGender1).isNotEqualTo(enumGender2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumGenderDTO.class);
        EnumGenderDTO enumGenderDTO1 = new EnumGenderDTO();
        enumGenderDTO1.setId(1L);
        EnumGenderDTO enumGenderDTO2 = new EnumGenderDTO();
        assertThat(enumGenderDTO1).isNotEqualTo(enumGenderDTO2);
        enumGenderDTO2.setId(enumGenderDTO1.getId());
        assertThat(enumGenderDTO1).isEqualTo(enumGenderDTO2);
        enumGenderDTO2.setId(2L);
        assertThat(enumGenderDTO1).isNotEqualTo(enumGenderDTO2);
        enumGenderDTO1.setId(null);
        assertThat(enumGenderDTO1).isNotEqualTo(enumGenderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumGenderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumGenderMapper.fromId(null)).isNull();
    }
}
