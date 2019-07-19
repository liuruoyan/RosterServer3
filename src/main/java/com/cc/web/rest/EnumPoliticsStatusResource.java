package com.cc.web.rest;

import com.cc.service.EnumPoliticsStatusService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumPoliticsStatusDTO;
import com.cc.service.dto.EnumPoliticsStatusCriteria;
import com.cc.service.EnumPoliticsStatusQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumPoliticsStatus}.
 */
@RestController
@RequestMapping("/api")
public class EnumPoliticsStatusResource {

    private final Logger log = LoggerFactory.getLogger(EnumPoliticsStatusResource.class);

    private static final String ENTITY_NAME = "enumPoliticsStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumPoliticsStatusService enumPoliticsStatusService;

    private final EnumPoliticsStatusQueryService enumPoliticsStatusQueryService;

    public EnumPoliticsStatusResource(EnumPoliticsStatusService enumPoliticsStatusService, EnumPoliticsStatusQueryService enumPoliticsStatusQueryService) {
        this.enumPoliticsStatusService = enumPoliticsStatusService;
        this.enumPoliticsStatusQueryService = enumPoliticsStatusQueryService;
    }

    /**
     * {@code POST  /enum-politics-statuses} : Create a new enumPoliticsStatus.
     *
     * @param enumPoliticsStatusDTO the enumPoliticsStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumPoliticsStatusDTO, or with status {@code 400 (Bad Request)} if the enumPoliticsStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-politics-statuses")
    public ResponseEntity<EnumPoliticsStatusDTO> createEnumPoliticsStatus(@RequestBody EnumPoliticsStatusDTO enumPoliticsStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EnumPoliticsStatus : {}", enumPoliticsStatusDTO);
        if (enumPoliticsStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumPoliticsStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumPoliticsStatusDTO result = enumPoliticsStatusService.save(enumPoliticsStatusDTO);
        return ResponseEntity.created(new URI("/api/enum-politics-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-politics-statuses} : Updates an existing enumPoliticsStatus.
     *
     * @param enumPoliticsStatusDTO the enumPoliticsStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumPoliticsStatusDTO,
     * or with status {@code 400 (Bad Request)} if the enumPoliticsStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumPoliticsStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-politics-statuses")
    public ResponseEntity<EnumPoliticsStatusDTO> updateEnumPoliticsStatus(@RequestBody EnumPoliticsStatusDTO enumPoliticsStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EnumPoliticsStatus : {}", enumPoliticsStatusDTO);
        if (enumPoliticsStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumPoliticsStatusDTO result = enumPoliticsStatusService.save(enumPoliticsStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumPoliticsStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-politics-statuses} : get all the enumPoliticsStatuses.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumPoliticsStatuses in body.
     */
    @GetMapping("/enum-politics-statuses")
    public ResponseEntity<List<EnumPoliticsStatusDTO>> getAllEnumPoliticsStatuses(EnumPoliticsStatusCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumPoliticsStatuses by criteria: {}", criteria);
        Page<EnumPoliticsStatusDTO> page = enumPoliticsStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-politics-statuses/count} : count all the enumPoliticsStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-politics-statuses/count")
    public ResponseEntity<Long> countEnumPoliticsStatuses(EnumPoliticsStatusCriteria criteria) {
        log.debug("REST request to count EnumPoliticsStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumPoliticsStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-politics-statuses/:id} : get the "id" enumPoliticsStatus.
     *
     * @param id the id of the enumPoliticsStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumPoliticsStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-politics-statuses/{id}")
    public ResponseEntity<EnumPoliticsStatusDTO> getEnumPoliticsStatus(@PathVariable Long id) {
        log.debug("REST request to get EnumPoliticsStatus : {}", id);
        Optional<EnumPoliticsStatusDTO> enumPoliticsStatusDTO = enumPoliticsStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumPoliticsStatusDTO);
    }

    /**
     * {@code DELETE  /enum-politics-statuses/:id} : delete the "id" enumPoliticsStatus.
     *
     * @param id the id of the enumPoliticsStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-politics-statuses/{id}")
    public ResponseEntity<Void> deleteEnumPoliticsStatus(@PathVariable Long id) {
        log.debug("REST request to delete EnumPoliticsStatus : {}", id);
        enumPoliticsStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
