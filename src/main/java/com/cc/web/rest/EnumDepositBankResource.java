package com.cc.web.rest;

import com.cc.service.EnumDepositBankService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumDepositBankDTO;
import com.cc.service.dto.EnumDepositBankCriteria;
import com.cc.service.EnumDepositBankQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cc.domain.EnumDepositBank}.
 */
@RestController
@RequestMapping("/api")
public class EnumDepositBankResource {

    private final Logger log = LoggerFactory.getLogger(EnumDepositBankResource.class);

    private static final String ENTITY_NAME = "enumDepositBank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumDepositBankService enumDepositBankService;

    private final EnumDepositBankQueryService enumDepositBankQueryService;

    public EnumDepositBankResource(EnumDepositBankService enumDepositBankService, EnumDepositBankQueryService enumDepositBankQueryService) {
        this.enumDepositBankService = enumDepositBankService;
        this.enumDepositBankQueryService = enumDepositBankQueryService;
    }

    /**
     * {@code POST  /enum-deposit-banks} : Create a new enumDepositBank.
     *
     * @param enumDepositBankDTO the enumDepositBankDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumDepositBankDTO, or with status {@code 400 (Bad Request)} if the enumDepositBank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-deposit-banks")
    public ResponseEntity<EnumDepositBankDTO> createEnumDepositBank(@RequestBody EnumDepositBankDTO enumDepositBankDTO) throws URISyntaxException {
        log.debug("REST request to save EnumDepositBank : {}", enumDepositBankDTO);
        if (enumDepositBankDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumDepositBank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumDepositBankDTO result = enumDepositBankService.save(enumDepositBankDTO);
        return ResponseEntity.created(new URI("/api/enum-deposit-banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-deposit-banks} : Updates an existing enumDepositBank.
     *
     * @param enumDepositBankDTO the enumDepositBankDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumDepositBankDTO,
     * or with status {@code 400 (Bad Request)} if the enumDepositBankDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumDepositBankDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-deposit-banks")
    public ResponseEntity<EnumDepositBankDTO> updateEnumDepositBank(@RequestBody EnumDepositBankDTO enumDepositBankDTO) throws URISyntaxException {
        log.debug("REST request to update EnumDepositBank : {}", enumDepositBankDTO);
        if (enumDepositBankDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumDepositBankDTO result = enumDepositBankService.save(enumDepositBankDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumDepositBankDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-deposit-banks} : get all the enumDepositBanks.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumDepositBanks in body.
     */
    @GetMapping("/enum-deposit-banks")
    public ResponseEntity<List<EnumDepositBankDTO>> getAllEnumDepositBanks(EnumDepositBankCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumDepositBanks by criteria: {}", criteria);
        Page<EnumDepositBankDTO> page = enumDepositBankQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-deposit-banks/count} : count all the enumDepositBanks.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-deposit-banks/count")
    public ResponseEntity<Long> countEnumDepositBanks(EnumDepositBankCriteria criteria) {
        log.debug("REST request to count EnumDepositBanks by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumDepositBankQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-deposit-banks/:id} : get the "id" enumDepositBank.
     *
     * @param id the id of the enumDepositBankDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumDepositBankDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-deposit-banks/{id}")
    public ResponseEntity<EnumDepositBankDTO> getEnumDepositBank(@PathVariable Long id) {
        log.debug("REST request to get EnumDepositBank : {}", id);
        Optional<EnumDepositBankDTO> enumDepositBankDTO = enumDepositBankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumDepositBankDTO);
    }

    /**
     * {@code DELETE  /enum-deposit-banks/:id} : delete the "id" enumDepositBank.
     *
     * @param id the id of the enumDepositBankDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-deposit-banks/{id}")
    public ResponseEntity<Void> deleteEnumDepositBank(@PathVariable Long id) {
        log.debug("REST request to delete EnumDepositBank : {}", id);
        enumDepositBankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
