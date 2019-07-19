package com.cc.web.rest;

import com.cc.service.PayCardService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.PayCardDTO;
import com.cc.service.dto.PayCardCriteria;
import com.cc.service.PayCardQueryService;

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
 * REST controller for managing {@link com.cc.domain.PayCard}.
 */
@RestController
@RequestMapping("/api")
public class PayCardResource {

    private final Logger log = LoggerFactory.getLogger(PayCardResource.class);

    private static final String ENTITY_NAME = "payCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayCardService payCardService;

    private final PayCardQueryService payCardQueryService;

    public PayCardResource(PayCardService payCardService, PayCardQueryService payCardQueryService) {
        this.payCardService = payCardService;
        this.payCardQueryService = payCardQueryService;
    }

    /**
     * {@code POST  /pay-cards} : Create a new payCard.
     *
     * @param payCardDTO the payCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payCardDTO, or with status {@code 400 (Bad Request)} if the payCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pay-cards")
    public ResponseEntity<PayCardDTO> createPayCard(@RequestBody PayCardDTO payCardDTO) throws URISyntaxException {
        log.debug("REST request to save PayCard : {}", payCardDTO);
        if (payCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new payCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayCardDTO result = payCardService.save(payCardDTO);
        return ResponseEntity.created(new URI("/api/pay-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pay-cards} : Updates an existing payCard.
     *
     * @param payCardDTO the payCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payCardDTO,
     * or with status {@code 400 (Bad Request)} if the payCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pay-cards")
    public ResponseEntity<PayCardDTO> updatePayCard(@RequestBody PayCardDTO payCardDTO) throws URISyntaxException {
        log.debug("REST request to update PayCard : {}", payCardDTO);
        if (payCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PayCardDTO result = payCardService.save(payCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, payCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pay-cards} : get all the payCards.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payCards in body.
     */
    @GetMapping("/pay-cards")
    public ResponseEntity<List<PayCardDTO>> getAllPayCards(PayCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PayCards by criteria: {}", criteria);
        Page<PayCardDTO> page = payCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pay-cards/count} : count all the payCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pay-cards/count")
    public ResponseEntity<Long> countPayCards(PayCardCriteria criteria) {
        log.debug("REST request to count PayCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(payCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pay-cards/:id} : get the "id" payCard.
     *
     * @param id the id of the payCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pay-cards/{id}")
    public ResponseEntity<PayCardDTO> getPayCard(@PathVariable Long id) {
        log.debug("REST request to get PayCard : {}", id);
        Optional<PayCardDTO> payCardDTO = payCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payCardDTO);
    }

    /**
     * {@code DELETE  /pay-cards/:id} : delete the "id" payCard.
     *
     * @param id the id of the payCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pay-cards/{id}")
    public ResponseEntity<Void> deletePayCard(@PathVariable Long id) {
        log.debug("REST request to delete PayCard : {}", id);
        payCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
