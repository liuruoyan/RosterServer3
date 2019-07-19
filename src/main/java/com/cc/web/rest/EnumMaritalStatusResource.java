package com.cc.web.rest;

import com.cc.service.EnumMaritalStatusService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumMaritalStatusDTO;
import com.cc.service.dto.EnumMaritalStatusCriteria;
import com.cc.service.EnumMaritalStatusQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumMaritalStatus}.
 */
@RestController
@RequestMapping("/api")
public class EnumMaritalStatusResource {

    private final Logger log = LoggerFactory.getLogger(EnumMaritalStatusResource.class);

    private static final String ENTITY_NAME = "enumMaritalStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumMaritalStatusService enumMaritalStatusService;

    private final EnumMaritalStatusQueryService enumMaritalStatusQueryService;

    public EnumMaritalStatusResource(EnumMaritalStatusService enumMaritalStatusService, EnumMaritalStatusQueryService enumMaritalStatusQueryService) {
        this.enumMaritalStatusService = enumMaritalStatusService;
        this.enumMaritalStatusQueryService = enumMaritalStatusQueryService;
    }

    /**
     * {@code POST  /enum-marital-statuses} : Create a new enumMaritalStatus.
     *
     * @param enumMaritalStatusDTO the enumMaritalStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumMaritalStatusDTO, or with status {@code 400 (Bad Request)} if the enumMaritalStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-marital-statuses")
    public ResponseEntity<EnumMaritalStatusDTO> createEnumMaritalStatus(@RequestBody EnumMaritalStatusDTO enumMaritalStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EnumMaritalStatus : {}", enumMaritalStatusDTO);
        if (enumMaritalStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumMaritalStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumMaritalStatusDTO result = enumMaritalStatusService.save(enumMaritalStatusDTO);
        return ResponseEntity.created(new URI("/api/enum-marital-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-marital-statuses} : Updates an existing enumMaritalStatus.
     *
     * @param enumMaritalStatusDTO the enumMaritalStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumMaritalStatusDTO,
     * or with status {@code 400 (Bad Request)} if the enumMaritalStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumMaritalStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-marital-statuses")
    public ResponseEntity<EnumMaritalStatusDTO> updateEnumMaritalStatus(@RequestBody EnumMaritalStatusDTO enumMaritalStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EnumMaritalStatus : {}", enumMaritalStatusDTO);
        if (enumMaritalStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumMaritalStatusDTO result = enumMaritalStatusService.save(enumMaritalStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumMaritalStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-marital-statuses} : get all the enumMaritalStatuses.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumMaritalStatuses in body.
     */
    @GetMapping("/enum-marital-statuses")
    public ResponseEntity<List<EnumMaritalStatusDTO>> getAllEnumMaritalStatuses(EnumMaritalStatusCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumMaritalStatuses by criteria: {}", criteria);
        Page<EnumMaritalStatusDTO> page = enumMaritalStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-marital-statuses/count} : count all the enumMaritalStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-marital-statuses/count")
    public ResponseEntity<Long> countEnumMaritalStatuses(EnumMaritalStatusCriteria criteria) {
        log.debug("REST request to count EnumMaritalStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumMaritalStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-marital-statuses/:id} : get the "id" enumMaritalStatus.
     *
     * @param id the id of the enumMaritalStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumMaritalStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-marital-statuses/{id}")
    public ResponseEntity<EnumMaritalStatusDTO> getEnumMaritalStatus(@PathVariable Long id) {
        log.debug("REST request to get EnumMaritalStatus : {}", id);
        Optional<EnumMaritalStatusDTO> enumMaritalStatusDTO = enumMaritalStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumMaritalStatusDTO);
    }

    /**
     * {@code DELETE  /enum-marital-statuses/:id} : delete the "id" enumMaritalStatus.
     *
     * @param id the id of the enumMaritalStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-marital-statuses/{id}")
    public ResponseEntity<Void> deleteEnumMaritalStatus(@PathVariable Long id) {
        log.debug("REST request to delete EnumMaritalStatus : {}", id);
        enumMaritalStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
