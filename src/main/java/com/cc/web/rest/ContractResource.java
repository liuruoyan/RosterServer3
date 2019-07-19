package com.cc.web.rest;

import com.cc.service.ContractService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.ContractDTO;
import com.cc.service.dto.ContractCriteria;
import com.cc.service.ContractQueryService;

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
 * REST controller for managing {@link com.cc.domain.Contract}.
 */
@RestController
@RequestMapping("/api")
public class ContractResource {

    private final Logger log = LoggerFactory.getLogger(ContractResource.class);

    private static final String ENTITY_NAME = "contract";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractService contractService;

    private final ContractQueryService contractQueryService;

    public ContractResource(ContractService contractService, ContractQueryService contractQueryService) {
        this.contractService = contractService;
        this.contractQueryService = contractQueryService;
    }

    /**
     * {@code POST  /contracts} : Create a new contract.
     *
     * @param contractDTO the contractDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractDTO, or with status {@code 400 (Bad Request)} if the contract has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contracts")
    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contractDTO) throws URISyntaxException {
        log.debug("REST request to save Contract : {}", contractDTO);
        if (contractDTO.getId() != null) {
            throw new BadRequestAlertException("A new contract cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractDTO result = contractService.save(contractDTO);
        return ResponseEntity.created(new URI("/api/contracts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contracts} : Updates an existing contract.
     *
     * @param contractDTO the contractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractDTO,
     * or with status {@code 400 (Bad Request)} if the contractDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contracts")
    public ResponseEntity<ContractDTO> updateContract(@RequestBody ContractDTO contractDTO) throws URISyntaxException {
        log.debug("REST request to update Contract : {}", contractDTO);
        if (contractDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContractDTO result = contractService.save(contractDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contracts} : get all the contracts.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contracts in body.
     */
    @GetMapping("/contracts")
    public ResponseEntity<List<ContractDTO>> getAllContracts(ContractCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Contracts by criteria: {}", criteria);
        Page<ContractDTO> page = contractQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /contracts/count} : count all the contracts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/contracts/count")
    public ResponseEntity<Long> countContracts(ContractCriteria criteria) {
        log.debug("REST request to count Contracts by criteria: {}", criteria);
        return ResponseEntity.ok().body(contractQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /contracts/:id} : get the "id" contract.
     *
     * @param id the id of the contractDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contracts/{id}")
    public ResponseEntity<ContractDTO> getContract(@PathVariable Long id) {
        log.debug("REST request to get Contract : {}", id);
        Optional<ContractDTO> contractDTO = contractService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractDTO);
    }

    /**
     * {@code DELETE  /contracts/:id} : delete the "id" contract.
     *
     * @param id the id of the contractDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        log.debug("REST request to delete Contract : {}", id);
        contractService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
