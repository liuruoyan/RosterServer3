package com.cc.web.rest;

import com.cc.service.EnumPfStatusService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumPfStatusDTO;
import com.cc.service.dto.EnumPfStatusCriteria;
import com.cc.service.EnumPfStatusQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumPfStatus}.
 */
@RestController
@RequestMapping("/api")
public class EnumPfStatusResource {

    private final Logger log = LoggerFactory.getLogger(EnumPfStatusResource.class);

    private static final String ENTITY_NAME = "enumPfStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumPfStatusService enumPfStatusService;

    private final EnumPfStatusQueryService enumPfStatusQueryService;

    public EnumPfStatusResource(EnumPfStatusService enumPfStatusService, EnumPfStatusQueryService enumPfStatusQueryService) {
        this.enumPfStatusService = enumPfStatusService;
        this.enumPfStatusQueryService = enumPfStatusQueryService;
    }

    /**
     * {@code POST  /enum-pf-statuses} : Create a new enumPfStatus.
     *
     * @param enumPfStatusDTO the enumPfStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumPfStatusDTO, or with status {@code 400 (Bad Request)} if the enumPfStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-pf-statuses")
    public ResponseEntity<EnumPfStatusDTO> createEnumPfStatus(@RequestBody EnumPfStatusDTO enumPfStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EnumPfStatus : {}", enumPfStatusDTO);
        if (enumPfStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumPfStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumPfStatusDTO result = enumPfStatusService.save(enumPfStatusDTO);
        return ResponseEntity.created(new URI("/api/enum-pf-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-pf-statuses} : Updates an existing enumPfStatus.
     *
     * @param enumPfStatusDTO the enumPfStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumPfStatusDTO,
     * or with status {@code 400 (Bad Request)} if the enumPfStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumPfStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-pf-statuses")
    public ResponseEntity<EnumPfStatusDTO> updateEnumPfStatus(@RequestBody EnumPfStatusDTO enumPfStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EnumPfStatus : {}", enumPfStatusDTO);
        if (enumPfStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumPfStatusDTO result = enumPfStatusService.save(enumPfStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumPfStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-pf-statuses} : get all the enumPfStatuses.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumPfStatuses in body.
     */
    @GetMapping("/enum-pf-statuses")
    public ResponseEntity<List<EnumPfStatusDTO>> getAllEnumPfStatuses(EnumPfStatusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumPfStatuses by criteria: {}", criteria);
        Page<EnumPfStatusDTO> page = enumPfStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-pf-statuses/count} : count all the enumPfStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-pf-statuses/count")
    public ResponseEntity<Long> countEnumPfStatuses(EnumPfStatusCriteria criteria) {
        log.debug("REST request to count EnumPfStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumPfStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-pf-statuses/:id} : get the "id" enumPfStatus.
     *
     * @param id the id of the enumPfStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumPfStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-pf-statuses/{id}")
    public ResponseEntity<EnumPfStatusDTO> getEnumPfStatus(@PathVariable Long id) {
        log.debug("REST request to get EnumPfStatus : {}", id);
        Optional<EnumPfStatusDTO> enumPfStatusDTO = enumPfStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumPfStatusDTO);
    }

    /**
     * {@code DELETE  /enum-pf-statuses/:id} : delete the "id" enumPfStatus.
     *
     * @param id the id of the enumPfStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-pf-statuses/{id}")
    public ResponseEntity<Void> deleteEnumPfStatus(@PathVariable Long id) {
        log.debug("REST request to delete EnumPfStatus : {}", id);
        enumPfStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
