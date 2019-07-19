package com.cc.web.rest;

import com.cc.service.EnumSsStatusService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumSsStatusDTO;
import com.cc.service.dto.EnumSsStatusCriteria;
import com.cc.service.EnumSsStatusQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumSsStatus}.
 */
@RestController
@RequestMapping("/api")
public class EnumSsStatusResource {

    private final Logger log = LoggerFactory.getLogger(EnumSsStatusResource.class);

    private static final String ENTITY_NAME = "enumSsStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumSsStatusService enumSsStatusService;

    private final EnumSsStatusQueryService enumSsStatusQueryService;

    public EnumSsStatusResource(EnumSsStatusService enumSsStatusService, EnumSsStatusQueryService enumSsStatusQueryService) {
        this.enumSsStatusService = enumSsStatusService;
        this.enumSsStatusQueryService = enumSsStatusQueryService;
    }

    /**
     * {@code POST  /enum-ss-statuses} : Create a new enumSsStatus.
     *
     * @param enumSsStatusDTO the enumSsStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumSsStatusDTO, or with status {@code 400 (Bad Request)} if the enumSsStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-ss-statuses")
    public ResponseEntity<EnumSsStatusDTO> createEnumSsStatus(@RequestBody EnumSsStatusDTO enumSsStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EnumSsStatus : {}", enumSsStatusDTO);
        if (enumSsStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumSsStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumSsStatusDTO result = enumSsStatusService.save(enumSsStatusDTO);
        return ResponseEntity.created(new URI("/api/enum-ss-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-ss-statuses} : Updates an existing enumSsStatus.
     *
     * @param enumSsStatusDTO the enumSsStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumSsStatusDTO,
     * or with status {@code 400 (Bad Request)} if the enumSsStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumSsStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-ss-statuses")
    public ResponseEntity<EnumSsStatusDTO> updateEnumSsStatus(@RequestBody EnumSsStatusDTO enumSsStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EnumSsStatus : {}", enumSsStatusDTO);
        if (enumSsStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumSsStatusDTO result = enumSsStatusService.save(enumSsStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumSsStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-ss-statuses} : get all the enumSsStatuses.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumSsStatuses in body.
     */
    @GetMapping("/enum-ss-statuses")
    public ResponseEntity<List<EnumSsStatusDTO>> getAllEnumSsStatuses(EnumSsStatusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumSsStatuses by criteria: {}", criteria);
        Page<EnumSsStatusDTO> page = enumSsStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-ss-statuses/count} : count all the enumSsStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-ss-statuses/count")
    public ResponseEntity<Long> countEnumSsStatuses(EnumSsStatusCriteria criteria) {
        log.debug("REST request to count EnumSsStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumSsStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-ss-statuses/:id} : get the "id" enumSsStatus.
     *
     * @param id the id of the enumSsStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumSsStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-ss-statuses/{id}")
    public ResponseEntity<EnumSsStatusDTO> getEnumSsStatus(@PathVariable Long id) {
        log.debug("REST request to get EnumSsStatus : {}", id);
        Optional<EnumSsStatusDTO> enumSsStatusDTO = enumSsStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumSsStatusDTO);
    }

    /**
     * {@code DELETE  /enum-ss-statuses/:id} : delete the "id" enumSsStatus.
     *
     * @param id the id of the enumSsStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-ss-statuses/{id}")
    public ResponseEntity<Void> deleteEnumSsStatus(@PathVariable Long id) {
        log.debug("REST request to delete EnumSsStatus : {}", id);
        enumSsStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
