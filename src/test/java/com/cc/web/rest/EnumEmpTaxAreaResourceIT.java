package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumEmpTaxArea;
import com.cc.domain.EnumEmpTaxArea;
import com.cc.repository.EnumEmpTaxAreaRepository;
import com.cc.service.EnumEmpTaxAreaService;
import com.cc.service.dto.EnumEmpTaxAreaDTO;
import com.cc.service.mapper.EnumEmpTaxAreaMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumEmpTaxAreaCriteria;
import com.cc.service.EnumEmpTaxAreaQueryService;

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
 * Integration tests for the {@Link EnumEmpTaxAreaResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumEmpTaxAreaResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumEmpTaxAreaRepository enumEmpTaxAreaRepository;

    @Autowired
    private EnumEmpTaxAreaMapper enumEmpTaxAreaMapper;

    @Autowired
    private EnumEmpTaxAreaService enumEmpTaxAreaService;

    @Autowired
    private EnumEmpTaxAreaQueryService enumEmpTaxAreaQueryService;

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

    private MockMvc restEnumEmpTaxAreaMockMvc;

    private EnumEmpTaxArea enumEmpTaxArea;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumEmpTaxAreaResource enumEmpTaxAreaResource = new EnumEmpTaxAreaResource(enumEmpTaxAreaService, enumEmpTaxAreaQueryService);
        this.restEnumEmpTaxAreaMockMvc = MockMvcBuilders.standaloneSetup(enumEmpTaxAreaResource)
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
    public static EnumEmpTaxArea createEntity(EntityManager em) {
        EnumEmpTaxArea enumEmpTaxArea = new EnumEmpTaxArea()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumEmpTaxArea;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumEmpTaxArea createUpdatedEntity(EntityManager em) {
        EnumEmpTaxArea enumEmpTaxArea = new EnumEmpTaxArea()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumEmpTaxArea;
    }

    @BeforeEach
    public void initTest() {
        enumEmpTaxArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumEmpTaxArea() throws Exception {
        int databaseSizeBeforeCreate = enumEmpTaxAreaRepository.findAll().size();

        // Create the EnumEmpTaxArea
        EnumEmpTaxAreaDTO enumEmpTaxAreaDTO = enumEmpTaxAreaMapper.toDto(enumEmpTaxArea);
        restEnumEmpTaxAreaMockMvc.perform(post("/api/enum-emp-tax-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumEmpTaxArea in the database
        List<EnumEmpTaxArea> enumEmpTaxAreaList = enumEmpTaxAreaRepository.findAll();
        assertThat(enumEmpTaxAreaList).hasSize(databaseSizeBeforeCreate + 1);
        EnumEmpTaxArea testEnumEmpTaxArea = enumEmpTaxAreaList.get(enumEmpTaxAreaList.size() - 1);
        assertThat(testEnumEmpTaxArea.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumEmpTaxArea.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumEmpTaxArea.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumEmpTaxAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumEmpTaxAreaRepository.findAll().size();

        // Create the EnumEmpTaxArea with an existing ID
        enumEmpTaxArea.setId(1L);
        EnumEmpTaxAreaDTO enumEmpTaxAreaDTO = enumEmpTaxAreaMapper.toDto(enumEmpTaxArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumEmpTaxAreaMockMvc.perform(post("/api/enum-emp-tax-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpTaxArea in the database
        List<EnumEmpTaxArea> enumEmpTaxAreaList = enumEmpTaxAreaRepository.findAll();
        assertThat(enumEmpTaxAreaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumEmpTaxAreas() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList
        restEnumEmpTaxAreaMockMvc.perform(get("/api/enum-emp-tax-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpTaxArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumEmpTaxArea() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get the enumEmpTaxArea
        restEnumEmpTaxAreaMockMvc.perform(get("/api/enum-emp-tax-areas/{id}", enumEmpTaxArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumEmpTaxArea.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where valuez equals to DEFAULT_VALUEZ
        defaultEnumEmpTaxAreaShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumEmpTaxAreaList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpTaxAreaShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumEmpTaxAreaShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumEmpTaxAreaList where valuez equals to UPDATED_VALUEZ
        defaultEnumEmpTaxAreaShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where valuez is not null
        defaultEnumEmpTaxAreaShouldBeFound("valuez.specified=true");

        // Get all the enumEmpTaxAreaList where valuez is null
        defaultEnumEmpTaxAreaShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where orderz equals to DEFAULT_ORDERZ
        defaultEnumEmpTaxAreaShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTaxAreaList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpTaxAreaShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumEmpTaxAreaShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumEmpTaxAreaList where orderz equals to UPDATED_ORDERZ
        defaultEnumEmpTaxAreaShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where orderz is not null
        defaultEnumEmpTaxAreaShouldBeFound("orderz.specified=true");

        // Get all the enumEmpTaxAreaList where orderz is null
        defaultEnumEmpTaxAreaShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumEmpTaxAreaShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTaxAreaList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumEmpTaxAreaShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumEmpTaxAreaShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumEmpTaxAreaList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumEmpTaxAreaShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumEmpTaxAreaShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumEmpTaxAreaList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpTaxAreaShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumEmpTaxAreaShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumEmpTaxAreaList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumEmpTaxAreaShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        // Get all the enumEmpTaxAreaList where tenentCode is not null
        defaultEnumEmpTaxAreaShouldBeFound("tenentCode.specified=true");

        // Get all the enumEmpTaxAreaList where tenentCode is null
        defaultEnumEmpTaxAreaShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumEmpTaxAreasByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumEmpTaxArea parent = EnumEmpTaxAreaResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumEmpTaxArea.setParent(parent);
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);
        Long parentId = parent.getId();

        // Get all the enumEmpTaxAreaList where parent equals to parentId
        defaultEnumEmpTaxAreaShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumEmpTaxAreaList where parent equals to parentId + 1
        defaultEnumEmpTaxAreaShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumEmpTaxAreaShouldBeFound(String filter) throws Exception {
        restEnumEmpTaxAreaMockMvc.perform(get("/api/enum-emp-tax-areas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumEmpTaxArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumEmpTaxAreaMockMvc.perform(get("/api/enum-emp-tax-areas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumEmpTaxAreaShouldNotBeFound(String filter) throws Exception {
        restEnumEmpTaxAreaMockMvc.perform(get("/api/enum-emp-tax-areas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumEmpTaxAreaMockMvc.perform(get("/api/enum-emp-tax-areas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumEmpTaxArea() throws Exception {
        // Get the enumEmpTaxArea
        restEnumEmpTaxAreaMockMvc.perform(get("/api/enum-emp-tax-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumEmpTaxArea() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        int databaseSizeBeforeUpdate = enumEmpTaxAreaRepository.findAll().size();

        // Update the enumEmpTaxArea
        EnumEmpTaxArea updatedEnumEmpTaxArea = enumEmpTaxAreaRepository.findById(enumEmpTaxArea.getId()).get();
        // Disconnect from session so that the updates on updatedEnumEmpTaxArea are not directly saved in db
        em.detach(updatedEnumEmpTaxArea);
        updatedEnumEmpTaxArea
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumEmpTaxAreaDTO enumEmpTaxAreaDTO = enumEmpTaxAreaMapper.toDto(updatedEnumEmpTaxArea);

        restEnumEmpTaxAreaMockMvc.perform(put("/api/enum-emp-tax-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxAreaDTO)))
            .andExpect(status().isOk());

        // Validate the EnumEmpTaxArea in the database
        List<EnumEmpTaxArea> enumEmpTaxAreaList = enumEmpTaxAreaRepository.findAll();
        assertThat(enumEmpTaxAreaList).hasSize(databaseSizeBeforeUpdate);
        EnumEmpTaxArea testEnumEmpTaxArea = enumEmpTaxAreaList.get(enumEmpTaxAreaList.size() - 1);
        assertThat(testEnumEmpTaxArea.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumEmpTaxArea.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumEmpTaxArea.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumEmpTaxArea() throws Exception {
        int databaseSizeBeforeUpdate = enumEmpTaxAreaRepository.findAll().size();

        // Create the EnumEmpTaxArea
        EnumEmpTaxAreaDTO enumEmpTaxAreaDTO = enumEmpTaxAreaMapper.toDto(enumEmpTaxArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumEmpTaxAreaMockMvc.perform(put("/api/enum-emp-tax-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumEmpTaxAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumEmpTaxArea in the database
        List<EnumEmpTaxArea> enumEmpTaxAreaList = enumEmpTaxAreaRepository.findAll();
        assertThat(enumEmpTaxAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumEmpTaxArea() throws Exception {
        // Initialize the database
        enumEmpTaxAreaRepository.saveAndFlush(enumEmpTaxArea);

        int databaseSizeBeforeDelete = enumEmpTaxAreaRepository.findAll().size();

        // Delete the enumEmpTaxArea
        restEnumEmpTaxAreaMockMvc.perform(delete("/api/enum-emp-tax-areas/{id}", enumEmpTaxArea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumEmpTaxArea> enumEmpTaxAreaList = enumEmpTaxAreaRepository.findAll();
        assertThat(enumEmpTaxAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpTaxArea.class);
        EnumEmpTaxArea enumEmpTaxArea1 = new EnumEmpTaxArea();
        enumEmpTaxArea1.setId(1L);
        EnumEmpTaxArea enumEmpTaxArea2 = new EnumEmpTaxArea();
        enumEmpTaxArea2.setId(enumEmpTaxArea1.getId());
        assertThat(enumEmpTaxArea1).isEqualTo(enumEmpTaxArea2);
        enumEmpTaxArea2.setId(2L);
        assertThat(enumEmpTaxArea1).isNotEqualTo(enumEmpTaxArea2);
        enumEmpTaxArea1.setId(null);
        assertThat(enumEmpTaxArea1).isNotEqualTo(enumEmpTaxArea2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumEmpTaxAreaDTO.class);
        EnumEmpTaxAreaDTO enumEmpTaxAreaDTO1 = new EnumEmpTaxAreaDTO();
        enumEmpTaxAreaDTO1.setId(1L);
        EnumEmpTaxAreaDTO enumEmpTaxAreaDTO2 = new EnumEmpTaxAreaDTO();
        assertThat(enumEmpTaxAreaDTO1).isNotEqualTo(enumEmpTaxAreaDTO2);
        enumEmpTaxAreaDTO2.setId(enumEmpTaxAreaDTO1.getId());
        assertThat(enumEmpTaxAreaDTO1).isEqualTo(enumEmpTaxAreaDTO2);
        enumEmpTaxAreaDTO2.setId(2L);
        assertThat(enumEmpTaxAreaDTO1).isNotEqualTo(enumEmpTaxAreaDTO2);
        enumEmpTaxAreaDTO1.setId(null);
        assertThat(enumEmpTaxAreaDTO1).isNotEqualTo(enumEmpTaxAreaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumEmpTaxAreaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumEmpTaxAreaMapper.fromId(null)).isNull();
    }
}
