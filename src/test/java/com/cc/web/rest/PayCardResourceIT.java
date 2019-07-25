package com.cc.web.rest;

import com.cc.RosterServer3App;
import com.cc.domain.PayCard;
import com.cc.domain.Employee;
import com.cc.repository.PayCardRepository;
import com.cc.service.PayCardService;
import com.cc.service.dto.PayCardDTO;
import com.cc.service.mapper.PayCardMapper;
import com.cc.web.rest.errors.ExceptionTranslator;
import com.cc.service.dto.PayCardCriteria;
import com.cc.service.PayCardQueryService;

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
 * Integration tests for the {@Link PayCardResource} REST controller.
 */
@SpringBootTest(classes = RosterServer3App.class)
public class PayCardResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DEPOSIT_BANK = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_BANK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SELF_VERIFY = false;
    private static final Boolean UPDATED_IS_SELF_VERIFY = true;

    private static final Boolean DEFAULT_IS_HR_VERIFY = false;
    private static final Boolean UPDATED_IS_HR_VERIFY = true;

    @Autowired
    private PayCardRepository payCardRepository;

    @Autowired
    private PayCardMapper payCardMapper;

    @Autowired
    private PayCardService payCardService;

    @Autowired
    private PayCardQueryService payCardQueryService;

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

    private MockMvc restPayCardMockMvc;

    private PayCard payCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PayCardResource payCardResource = new PayCardResource(payCardService, payCardQueryService);
        this.restPayCardMockMvc = MockMvcBuilders.standaloneSetup(payCardResource)
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
    public static PayCard createEntity(EntityManager em) {
        PayCard payCard = new PayCard()
            .code(DEFAULT_CODE)
            .branch(DEFAULT_BRANCH)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .bankAccount(DEFAULT_BANK_ACCOUNT)
            .depositBank(DEFAULT_DEPOSIT_BANK)
            .isSelfVerify(DEFAULT_IS_SELF_VERIFY)
            .isHrVerify(DEFAULT_IS_HR_VERIFY);
        return payCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayCard createUpdatedEntity(EntityManager em) {
        PayCard payCard = new PayCard()
            .code(UPDATED_CODE)
            .branch(UPDATED_BRANCH)
            .accountName(UPDATED_ACCOUNT_NAME)
            .bankAccount(UPDATED_BANK_ACCOUNT)
            .depositBank(UPDATED_DEPOSIT_BANK)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        return payCard;
    }

    @BeforeEach
    public void initTest() {
        payCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createPayCard() throws Exception {
        int databaseSizeBeforeCreate = payCardRepository.findAll().size();

        // Create the PayCard
        PayCardDTO payCardDTO = payCardMapper.toDto(payCard);
        restPayCardMockMvc.perform(post("/api/pay-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payCardDTO)))
            .andExpect(status().isCreated());

        // Validate the PayCard in the database
        List<PayCard> payCardList = payCardRepository.findAll();
        assertThat(payCardList).hasSize(databaseSizeBeforeCreate + 1);
        PayCard testPayCard = payCardList.get(payCardList.size() - 1);
        assertThat(testPayCard.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPayCard.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testPayCard.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testPayCard.getBankAccount()).isEqualTo(DEFAULT_BANK_ACCOUNT);
        assertThat(testPayCard.getDepositBank()).isEqualTo(DEFAULT_DEPOSIT_BANK);
        assertThat(testPayCard.isIsSelfVerify()).isEqualTo(DEFAULT_IS_SELF_VERIFY);
        assertThat(testPayCard.isIsHrVerify()).isEqualTo(DEFAULT_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void createPayCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = payCardRepository.findAll().size();

        // Create the PayCard with an existing ID
        payCard.setId(1L);
        PayCardDTO payCardDTO = payCardMapper.toDto(payCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayCardMockMvc.perform(post("/api/pay-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PayCard in the database
        List<PayCard> payCardList = payCardRepository.findAll();
        assertThat(payCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPayCards() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList
        restPayCardMockMvc.perform(get("/api/pay-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH.toString())))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DEFAULT_BANK_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].depositBank").value(hasItem(DEFAULT_DEPOSIT_BANK.toString())))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPayCard() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get the payCard
        restPayCardMockMvc.perform(get("/api/pay-cards/{id}", payCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(payCard.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH.toString()))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME.toString()))
            .andExpect(jsonPath("$.bankAccount").value(DEFAULT_BANK_ACCOUNT.toString()))
            .andExpect(jsonPath("$.depositBank").value(DEFAULT_DEPOSIT_BANK.toString()))
            .andExpect(jsonPath("$.isSelfVerify").value(DEFAULT_IS_SELF_VERIFY.booleanValue()))
            .andExpect(jsonPath("$.isHrVerify").value(DEFAULT_IS_HR_VERIFY.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllPayCardsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where code equals to DEFAULT_CODE
        defaultPayCardShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the payCardList where code equals to UPDATED_CODE
        defaultPayCardShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllPayCardsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where code in DEFAULT_CODE or UPDATED_CODE
        defaultPayCardShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the payCardList where code equals to UPDATED_CODE
        defaultPayCardShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllPayCardsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where code is not null
        defaultPayCardShouldBeFound("code.specified=true");

        // Get all the payCardList where code is null
        defaultPayCardShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllPayCardsByBranchIsEqualToSomething() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where branch equals to DEFAULT_BRANCH
        defaultPayCardShouldBeFound("branch.equals=" + DEFAULT_BRANCH);

        // Get all the payCardList where branch equals to UPDATED_BRANCH
        defaultPayCardShouldNotBeFound("branch.equals=" + UPDATED_BRANCH);
    }

    @Test
    @Transactional
    public void getAllPayCardsByBranchIsInShouldWork() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where branch in DEFAULT_BRANCH or UPDATED_BRANCH
        defaultPayCardShouldBeFound("branch.in=" + DEFAULT_BRANCH + "," + UPDATED_BRANCH);

        // Get all the payCardList where branch equals to UPDATED_BRANCH
        defaultPayCardShouldNotBeFound("branch.in=" + UPDATED_BRANCH);
    }

    @Test
    @Transactional
    public void getAllPayCardsByBranchIsNullOrNotNull() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where branch is not null
        defaultPayCardShouldBeFound("branch.specified=true");

        // Get all the payCardList where branch is null
        defaultPayCardShouldNotBeFound("branch.specified=false");
    }

    @Test
    @Transactional
    public void getAllPayCardsByAccountNameIsEqualToSomething() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where accountName equals to DEFAULT_ACCOUNT_NAME
        defaultPayCardShouldBeFound("accountName.equals=" + DEFAULT_ACCOUNT_NAME);

        // Get all the payCardList where accountName equals to UPDATED_ACCOUNT_NAME
        defaultPayCardShouldNotBeFound("accountName.equals=" + UPDATED_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllPayCardsByAccountNameIsInShouldWork() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where accountName in DEFAULT_ACCOUNT_NAME or UPDATED_ACCOUNT_NAME
        defaultPayCardShouldBeFound("accountName.in=" + DEFAULT_ACCOUNT_NAME + "," + UPDATED_ACCOUNT_NAME);

        // Get all the payCardList where accountName equals to UPDATED_ACCOUNT_NAME
        defaultPayCardShouldNotBeFound("accountName.in=" + UPDATED_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllPayCardsByAccountNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where accountName is not null
        defaultPayCardShouldBeFound("accountName.specified=true");

        // Get all the payCardList where accountName is null
        defaultPayCardShouldNotBeFound("accountName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPayCardsByBankAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where bankAccount equals to DEFAULT_BANK_ACCOUNT
        defaultPayCardShouldBeFound("bankAccount.equals=" + DEFAULT_BANK_ACCOUNT);

        // Get all the payCardList where bankAccount equals to UPDATED_BANK_ACCOUNT
        defaultPayCardShouldNotBeFound("bankAccount.equals=" + UPDATED_BANK_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllPayCardsByBankAccountIsInShouldWork() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where bankAccount in DEFAULT_BANK_ACCOUNT or UPDATED_BANK_ACCOUNT
        defaultPayCardShouldBeFound("bankAccount.in=" + DEFAULT_BANK_ACCOUNT + "," + UPDATED_BANK_ACCOUNT);

        // Get all the payCardList where bankAccount equals to UPDATED_BANK_ACCOUNT
        defaultPayCardShouldNotBeFound("bankAccount.in=" + UPDATED_BANK_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllPayCardsByBankAccountIsNullOrNotNull() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where bankAccount is not null
        defaultPayCardShouldBeFound("bankAccount.specified=true");

        // Get all the payCardList where bankAccount is null
        defaultPayCardShouldNotBeFound("bankAccount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPayCardsByDepositBankIsEqualToSomething() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where depositBank equals to DEFAULT_DEPOSIT_BANK
        defaultPayCardShouldBeFound("depositBank.equals=" + DEFAULT_DEPOSIT_BANK);

        // Get all the payCardList where depositBank equals to UPDATED_DEPOSIT_BANK
        defaultPayCardShouldNotBeFound("depositBank.equals=" + UPDATED_DEPOSIT_BANK);
    }

    @Test
    @Transactional
    public void getAllPayCardsByDepositBankIsInShouldWork() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where depositBank in DEFAULT_DEPOSIT_BANK or UPDATED_DEPOSIT_BANK
        defaultPayCardShouldBeFound("depositBank.in=" + DEFAULT_DEPOSIT_BANK + "," + UPDATED_DEPOSIT_BANK);

        // Get all the payCardList where depositBank equals to UPDATED_DEPOSIT_BANK
        defaultPayCardShouldNotBeFound("depositBank.in=" + UPDATED_DEPOSIT_BANK);
    }

    @Test
    @Transactional
    public void getAllPayCardsByDepositBankIsNullOrNotNull() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where depositBank is not null
        defaultPayCardShouldBeFound("depositBank.specified=true");

        // Get all the payCardList where depositBank is null
        defaultPayCardShouldNotBeFound("depositBank.specified=false");
    }

    @Test
    @Transactional
    public void getAllPayCardsByIsSelfVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where isSelfVerify equals to DEFAULT_IS_SELF_VERIFY
        defaultPayCardShouldBeFound("isSelfVerify.equals=" + DEFAULT_IS_SELF_VERIFY);

        // Get all the payCardList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultPayCardShouldNotBeFound("isSelfVerify.equals=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPayCardsByIsSelfVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where isSelfVerify in DEFAULT_IS_SELF_VERIFY or UPDATED_IS_SELF_VERIFY
        defaultPayCardShouldBeFound("isSelfVerify.in=" + DEFAULT_IS_SELF_VERIFY + "," + UPDATED_IS_SELF_VERIFY);

        // Get all the payCardList where isSelfVerify equals to UPDATED_IS_SELF_VERIFY
        defaultPayCardShouldNotBeFound("isSelfVerify.in=" + UPDATED_IS_SELF_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPayCardsByIsSelfVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where isSelfVerify is not null
        defaultPayCardShouldBeFound("isSelfVerify.specified=true");

        // Get all the payCardList where isSelfVerify is null
        defaultPayCardShouldNotBeFound("isSelfVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllPayCardsByIsHrVerifyIsEqualToSomething() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where isHrVerify equals to DEFAULT_IS_HR_VERIFY
        defaultPayCardShouldBeFound("isHrVerify.equals=" + DEFAULT_IS_HR_VERIFY);

        // Get all the payCardList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultPayCardShouldNotBeFound("isHrVerify.equals=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPayCardsByIsHrVerifyIsInShouldWork() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where isHrVerify in DEFAULT_IS_HR_VERIFY or UPDATED_IS_HR_VERIFY
        defaultPayCardShouldBeFound("isHrVerify.in=" + DEFAULT_IS_HR_VERIFY + "," + UPDATED_IS_HR_VERIFY);

        // Get all the payCardList where isHrVerify equals to UPDATED_IS_HR_VERIFY
        defaultPayCardShouldNotBeFound("isHrVerify.in=" + UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void getAllPayCardsByIsHrVerifyIsNullOrNotNull() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        // Get all the payCardList where isHrVerify is not null
        defaultPayCardShouldBeFound("isHrVerify.specified=true");

        // Get all the payCardList where isHrVerify is null
        defaultPayCardShouldNotBeFound("isHrVerify.specified=false");
    }

    @Test
    @Transactional
    public void getAllPayCardsByEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee emp = EmployeeResourceIT.createEntity(em);
        em.persist(emp);
        em.flush();
        payCard.setEmp(emp);
        payCardRepository.saveAndFlush(payCard);
        Long empId = emp.getId();

        // Get all the payCardList where emp equals to empId
        defaultPayCardShouldBeFound("empId.equals=" + empId);

        // Get all the payCardList where emp equals to empId + 1
        defaultPayCardShouldNotBeFound("empId.equals=" + (empId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPayCardShouldBeFound(String filter) throws Exception {
        restPayCardMockMvc.perform(get("/api/pay-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DEFAULT_BANK_ACCOUNT)))
            .andExpect(jsonPath("$.[*].depositBank").value(hasItem(DEFAULT_DEPOSIT_BANK)))
            .andExpect(jsonPath("$.[*].isSelfVerify").value(hasItem(DEFAULT_IS_SELF_VERIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].isHrVerify").value(hasItem(DEFAULT_IS_HR_VERIFY.booleanValue())));

        // Check, that the count call also returns 1
        restPayCardMockMvc.perform(get("/api/pay-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPayCardShouldNotBeFound(String filter) throws Exception {
        restPayCardMockMvc.perform(get("/api/pay-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPayCardMockMvc.perform(get("/api/pay-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPayCard() throws Exception {
        // Get the payCard
        restPayCardMockMvc.perform(get("/api/pay-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePayCard() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        int databaseSizeBeforeUpdate = payCardRepository.findAll().size();

        // Update the payCard
        PayCard updatedPayCard = payCardRepository.findById(payCard.getId()).get();
        // Disconnect from session so that the updates on updatedPayCard are not directly saved in db
        em.detach(updatedPayCard);
        updatedPayCard
            .code(UPDATED_CODE)
            .branch(UPDATED_BRANCH)
            .accountName(UPDATED_ACCOUNT_NAME)
            .bankAccount(UPDATED_BANK_ACCOUNT)
            .depositBank(UPDATED_DEPOSIT_BANK)
            .isSelfVerify(UPDATED_IS_SELF_VERIFY)
            .isHrVerify(UPDATED_IS_HR_VERIFY);
        PayCardDTO payCardDTO = payCardMapper.toDto(updatedPayCard);

        restPayCardMockMvc.perform(put("/api/pay-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payCardDTO)))
            .andExpect(status().isOk());

        // Validate the PayCard in the database
        List<PayCard> payCardList = payCardRepository.findAll();
        assertThat(payCardList).hasSize(databaseSizeBeforeUpdate);
        PayCard testPayCard = payCardList.get(payCardList.size() - 1);
        assertThat(testPayCard.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPayCard.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testPayCard.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testPayCard.getBankAccount()).isEqualTo(UPDATED_BANK_ACCOUNT);
        assertThat(testPayCard.getDepositBank()).isEqualTo(UPDATED_DEPOSIT_BANK);
        assertThat(testPayCard.isIsSelfVerify()).isEqualTo(UPDATED_IS_SELF_VERIFY);
        assertThat(testPayCard.isIsHrVerify()).isEqualTo(UPDATED_IS_HR_VERIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingPayCard() throws Exception {
        int databaseSizeBeforeUpdate = payCardRepository.findAll().size();

        // Create the PayCard
        PayCardDTO payCardDTO = payCardMapper.toDto(payCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayCardMockMvc.perform(put("/api/pay-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PayCard in the database
        List<PayCard> payCardList = payCardRepository.findAll();
        assertThat(payCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePayCard() throws Exception {
        // Initialize the database
        payCardRepository.saveAndFlush(payCard);

        int databaseSizeBeforeDelete = payCardRepository.findAll().size();

        // Delete the payCard
        restPayCardMockMvc.perform(delete("/api/pay-cards/{id}", payCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PayCard> payCardList = payCardRepository.findAll();
        assertThat(payCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayCard.class);
        PayCard payCard1 = new PayCard();
        payCard1.setId(1L);
        PayCard payCard2 = new PayCard();
        payCard2.setId(payCard1.getId());
        assertThat(payCard1).isEqualTo(payCard2);
        payCard2.setId(2L);
        assertThat(payCard1).isNotEqualTo(payCard2);
        payCard1.setId(null);
        assertThat(payCard1).isNotEqualTo(payCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayCardDTO.class);
        PayCardDTO payCardDTO1 = new PayCardDTO();
        payCardDTO1.setId(1L);
        PayCardDTO payCardDTO2 = new PayCardDTO();
        assertThat(payCardDTO1).isNotEqualTo(payCardDTO2);
        payCardDTO2.setId(payCardDTO1.getId());
        assertThat(payCardDTO1).isEqualTo(payCardDTO2);
        payCardDTO2.setId(2L);
        assertThat(payCardDTO1).isNotEqualTo(payCardDTO2);
        payCardDTO1.setId(null);
        assertThat(payCardDTO1).isNotEqualTo(payCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(payCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(payCardMapper.fromId(null)).isNull();
    }
}
