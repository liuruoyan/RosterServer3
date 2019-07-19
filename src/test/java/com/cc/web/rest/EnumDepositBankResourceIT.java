package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.EnumDepositBank;
import com.cc.domain.EnumDepositBank;
import com.cc.repository.EnumDepositBankRepository;
import com.cc.service.EnumDepositBankService;
import com.cc.service.dto.EnumDepositBankDTO;
import com.cc.service.mapper.EnumDepositBankMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.EnumDepositBankCriteria;
import com.cc.service.EnumDepositBankQueryService;

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
 * Integration tests for the {@Link EnumDepositBankResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class EnumDepositBankResourceIT {

    private static final String DEFAULT_VALUEZ = "AAAAAAAAAA";
    private static final String UPDATED_VALUEZ = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERZ = 1;
    private static final Integer UPDATED_ORDERZ = 2;

    private static final String DEFAULT_TENENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TENENT_CODE = "BBBBBBBBBB";

    @Autowired
    private EnumDepositBankRepository enumDepositBankRepository;

    @Autowired
    private EnumDepositBankMapper enumDepositBankMapper;

    @Autowired
    private EnumDepositBankService enumDepositBankService;

    @Autowired
    private EnumDepositBankQueryService enumDepositBankQueryService;

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

    private MockMvc restEnumDepositBankMockMvc;

    private EnumDepositBank enumDepositBank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnumDepositBankResource enumDepositBankResource = new EnumDepositBankResource(enumDepositBankService, enumDepositBankQueryService);
        this.restEnumDepositBankMockMvc = MockMvcBuilders.standaloneSetup(enumDepositBankResource)
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
    public static EnumDepositBank createEntity(EntityManager em) {
        EnumDepositBank enumDepositBank = new EnumDepositBank()
            .valuez(DEFAULT_VALUEZ)
            .orderz(DEFAULT_ORDERZ)
            .tenentCode(DEFAULT_TENENT_CODE);
        return enumDepositBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EnumDepositBank createUpdatedEntity(EntityManager em) {
        EnumDepositBank enumDepositBank = new EnumDepositBank()
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        return enumDepositBank;
    }

    @BeforeEach
    public void initTest() {
        enumDepositBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnumDepositBank() throws Exception {
        int databaseSizeBeforeCreate = enumDepositBankRepository.findAll().size();

        // Create the EnumDepositBank
        EnumDepositBankDTO enumDepositBankDTO = enumDepositBankMapper.toDto(enumDepositBank);
        restEnumDepositBankMockMvc.perform(post("/api/enum-deposit-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDepositBankDTO)))
            .andExpect(status().isCreated());

        // Validate the EnumDepositBank in the database
        List<EnumDepositBank> enumDepositBankList = enumDepositBankRepository.findAll();
        assertThat(enumDepositBankList).hasSize(databaseSizeBeforeCreate + 1);
        EnumDepositBank testEnumDepositBank = enumDepositBankList.get(enumDepositBankList.size() - 1);
        assertThat(testEnumDepositBank.getValuez()).isEqualTo(DEFAULT_VALUEZ);
        assertThat(testEnumDepositBank.getOrderz()).isEqualTo(DEFAULT_ORDERZ);
        assertThat(testEnumDepositBank.getTenentCode()).isEqualTo(DEFAULT_TENENT_CODE);
    }

    @Test
    @Transactional
    public void createEnumDepositBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enumDepositBankRepository.findAll().size();

        // Create the EnumDepositBank with an existing ID
        enumDepositBank.setId(1L);
        EnumDepositBankDTO enumDepositBankDTO = enumDepositBankMapper.toDto(enumDepositBank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnumDepositBankMockMvc.perform(post("/api/enum-deposit-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDepositBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumDepositBank in the database
        List<EnumDepositBank> enumDepositBankList = enumDepositBankRepository.findAll();
        assertThat(enumDepositBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnumDepositBanks() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList
        restEnumDepositBankMockMvc.perform(get("/api/enum-deposit-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumDepositBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ.toString())))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnumDepositBank() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get the enumDepositBank
        restEnumDepositBankMockMvc.perform(get("/api/enum-deposit-banks/{id}", enumDepositBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enumDepositBank.getId().intValue()))
            .andExpect(jsonPath("$.valuez").value(DEFAULT_VALUEZ.toString()))
            .andExpect(jsonPath("$.orderz").value(DEFAULT_ORDERZ))
            .andExpect(jsonPath("$.tenentCode").value(DEFAULT_TENENT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByValuezIsEqualToSomething() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where valuez equals to DEFAULT_VALUEZ
        defaultEnumDepositBankShouldBeFound("valuez.equals=" + DEFAULT_VALUEZ);

        // Get all the enumDepositBankList where valuez equals to UPDATED_VALUEZ
        defaultEnumDepositBankShouldNotBeFound("valuez.equals=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByValuezIsInShouldWork() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where valuez in DEFAULT_VALUEZ or UPDATED_VALUEZ
        defaultEnumDepositBankShouldBeFound("valuez.in=" + DEFAULT_VALUEZ + "," + UPDATED_VALUEZ);

        // Get all the enumDepositBankList where valuez equals to UPDATED_VALUEZ
        defaultEnumDepositBankShouldNotBeFound("valuez.in=" + UPDATED_VALUEZ);
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByValuezIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where valuez is not null
        defaultEnumDepositBankShouldBeFound("valuez.specified=true");

        // Get all the enumDepositBankList where valuez is null
        defaultEnumDepositBankShouldNotBeFound("valuez.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByOrderzIsEqualToSomething() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where orderz equals to DEFAULT_ORDERZ
        defaultEnumDepositBankShouldBeFound("orderz.equals=" + DEFAULT_ORDERZ);

        // Get all the enumDepositBankList where orderz equals to UPDATED_ORDERZ
        defaultEnumDepositBankShouldNotBeFound("orderz.equals=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByOrderzIsInShouldWork() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where orderz in DEFAULT_ORDERZ or UPDATED_ORDERZ
        defaultEnumDepositBankShouldBeFound("orderz.in=" + DEFAULT_ORDERZ + "," + UPDATED_ORDERZ);

        // Get all the enumDepositBankList where orderz equals to UPDATED_ORDERZ
        defaultEnumDepositBankShouldNotBeFound("orderz.in=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByOrderzIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where orderz is not null
        defaultEnumDepositBankShouldBeFound("orderz.specified=true");

        // Get all the enumDepositBankList where orderz is null
        defaultEnumDepositBankShouldNotBeFound("orderz.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByOrderzIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where orderz greater than or equals to DEFAULT_ORDERZ
        defaultEnumDepositBankShouldBeFound("orderz.greaterOrEqualThan=" + DEFAULT_ORDERZ);

        // Get all the enumDepositBankList where orderz greater than or equals to UPDATED_ORDERZ
        defaultEnumDepositBankShouldNotBeFound("orderz.greaterOrEqualThan=" + UPDATED_ORDERZ);
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByOrderzIsLessThanSomething() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where orderz less than or equals to DEFAULT_ORDERZ
        defaultEnumDepositBankShouldNotBeFound("orderz.lessThan=" + DEFAULT_ORDERZ);

        // Get all the enumDepositBankList where orderz less than or equals to UPDATED_ORDERZ
        defaultEnumDepositBankShouldBeFound("orderz.lessThan=" + UPDATED_ORDERZ);
    }


    @Test
    @Transactional
    public void getAllEnumDepositBanksByTenentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where tenentCode equals to DEFAULT_TENENT_CODE
        defaultEnumDepositBankShouldBeFound("tenentCode.equals=" + DEFAULT_TENENT_CODE);

        // Get all the enumDepositBankList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumDepositBankShouldNotBeFound("tenentCode.equals=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByTenentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where tenentCode in DEFAULT_TENENT_CODE or UPDATED_TENENT_CODE
        defaultEnumDepositBankShouldBeFound("tenentCode.in=" + DEFAULT_TENENT_CODE + "," + UPDATED_TENENT_CODE);

        // Get all the enumDepositBankList where tenentCode equals to UPDATED_TENENT_CODE
        defaultEnumDepositBankShouldNotBeFound("tenentCode.in=" + UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByTenentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        // Get all the enumDepositBankList where tenentCode is not null
        defaultEnumDepositBankShouldBeFound("tenentCode.specified=true");

        // Get all the enumDepositBankList where tenentCode is null
        defaultEnumDepositBankShouldNotBeFound("tenentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllEnumDepositBanksByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        EnumDepositBank parent = EnumDepositBankResourceIT.createEntity(em);
        em.persist(parent);
        em.flush();
        enumDepositBank.setParent(parent);
        enumDepositBankRepository.saveAndFlush(enumDepositBank);
        Long parentId = parent.getId();

        // Get all the enumDepositBankList where parent equals to parentId
        defaultEnumDepositBankShouldBeFound("parentId.equals=" + parentId);

        // Get all the enumDepositBankList where parent equals to parentId + 1
        defaultEnumDepositBankShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEnumDepositBankShouldBeFound(String filter) throws Exception {
        restEnumDepositBankMockMvc.perform(get("/api/enum-deposit-banks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enumDepositBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].valuez").value(hasItem(DEFAULT_VALUEZ)))
            .andExpect(jsonPath("$.[*].orderz").value(hasItem(DEFAULT_ORDERZ)))
            .andExpect(jsonPath("$.[*].tenentCode").value(hasItem(DEFAULT_TENENT_CODE)));

        // Check, that the count call also returns 1
        restEnumDepositBankMockMvc.perform(get("/api/enum-deposit-banks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEnumDepositBankShouldNotBeFound(String filter) throws Exception {
        restEnumDepositBankMockMvc.perform(get("/api/enum-deposit-banks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnumDepositBankMockMvc.perform(get("/api/enum-deposit-banks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnumDepositBank() throws Exception {
        // Get the enumDepositBank
        restEnumDepositBankMockMvc.perform(get("/api/enum-deposit-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnumDepositBank() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        int databaseSizeBeforeUpdate = enumDepositBankRepository.findAll().size();

        // Update the enumDepositBank
        EnumDepositBank updatedEnumDepositBank = enumDepositBankRepository.findById(enumDepositBank.getId()).get();
        // Disconnect from session so that the updates on updatedEnumDepositBank are not directly saved in db
        em.detach(updatedEnumDepositBank);
        updatedEnumDepositBank
            .valuez(UPDATED_VALUEZ)
            .orderz(UPDATED_ORDERZ)
            .tenentCode(UPDATED_TENENT_CODE);
        EnumDepositBankDTO enumDepositBankDTO = enumDepositBankMapper.toDto(updatedEnumDepositBank);

        restEnumDepositBankMockMvc.perform(put("/api/enum-deposit-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDepositBankDTO)))
            .andExpect(status().isOk());

        // Validate the EnumDepositBank in the database
        List<EnumDepositBank> enumDepositBankList = enumDepositBankRepository.findAll();
        assertThat(enumDepositBankList).hasSize(databaseSizeBeforeUpdate);
        EnumDepositBank testEnumDepositBank = enumDepositBankList.get(enumDepositBankList.size() - 1);
        assertThat(testEnumDepositBank.getValuez()).isEqualTo(UPDATED_VALUEZ);
        assertThat(testEnumDepositBank.getOrderz()).isEqualTo(UPDATED_ORDERZ);
        assertThat(testEnumDepositBank.getTenentCode()).isEqualTo(UPDATED_TENENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnumDepositBank() throws Exception {
        int databaseSizeBeforeUpdate = enumDepositBankRepository.findAll().size();

        // Create the EnumDepositBank
        EnumDepositBankDTO enumDepositBankDTO = enumDepositBankMapper.toDto(enumDepositBank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnumDepositBankMockMvc.perform(put("/api/enum-deposit-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enumDepositBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnumDepositBank in the database
        List<EnumDepositBank> enumDepositBankList = enumDepositBankRepository.findAll();
        assertThat(enumDepositBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnumDepositBank() throws Exception {
        // Initialize the database
        enumDepositBankRepository.saveAndFlush(enumDepositBank);

        int databaseSizeBeforeDelete = enumDepositBankRepository.findAll().size();

        // Delete the enumDepositBank
        restEnumDepositBankMockMvc.perform(delete("/api/enum-deposit-banks/{id}", enumDepositBank.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EnumDepositBank> enumDepositBankList = enumDepositBankRepository.findAll();
        assertThat(enumDepositBankList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumDepositBank.class);
        EnumDepositBank enumDepositBank1 = new EnumDepositBank();
        enumDepositBank1.setId(1L);
        EnumDepositBank enumDepositBank2 = new EnumDepositBank();
        enumDepositBank2.setId(enumDepositBank1.getId());
        assertThat(enumDepositBank1).isEqualTo(enumDepositBank2);
        enumDepositBank2.setId(2L);
        assertThat(enumDepositBank1).isNotEqualTo(enumDepositBank2);
        enumDepositBank1.setId(null);
        assertThat(enumDepositBank1).isNotEqualTo(enumDepositBank2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnumDepositBankDTO.class);
        EnumDepositBankDTO enumDepositBankDTO1 = new EnumDepositBankDTO();
        enumDepositBankDTO1.setId(1L);
        EnumDepositBankDTO enumDepositBankDTO2 = new EnumDepositBankDTO();
        assertThat(enumDepositBankDTO1).isNotEqualTo(enumDepositBankDTO2);
        enumDepositBankDTO2.setId(enumDepositBankDTO1.getId());
        assertThat(enumDepositBankDTO1).isEqualTo(enumDepositBankDTO2);
        enumDepositBankDTO2.setId(2L);
        assertThat(enumDepositBankDTO1).isNotEqualTo(enumDepositBankDTO2);
        enumDepositBankDTO1.setId(null);
        assertThat(enumDepositBankDTO1).isNotEqualTo(enumDepositBankDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(enumDepositBankMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(enumDepositBankMapper.fromId(null)).isNull();
    }
}
