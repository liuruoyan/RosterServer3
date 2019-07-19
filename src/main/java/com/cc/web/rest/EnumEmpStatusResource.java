package com.cc.web.rest;

import com.cc.service.EnumEmpStatusService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumEmpStatusDTO;
import com.cc.service.dto.EnumEmpStatusCriteria;
import com.cc.service.EnumEmpStatusQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumEmpStatus}.
 */
@RestController
@RequestMapping("/api")
public class EnumEmpStatusResource {

    private final Logger log = LoggerFactory.getLogger(EnumEmpStatusResource.class);

    private static final String ENTITY_NAME = "enumEmpStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumEmpStatusService enumEmpStatusService;

    private final EnumEmpStatusQueryService enumEmpStatusQueryService;

    public EnumEmpStatusResource(EnumEmpStatusService enumEmpStatusService, EnumEmpStatusQueryService enumEmpStatusQueryService) {
        this.enumEmpStatusService = enumEmpStatusService;
        this.enumEmpStatusQueryService = enumEmpStatusQueryService;
    }

    /**
     * {@code POST  /enum-emp-statuses} : Create a new enumEmpStatus.
     *
     * @param enumEmpStatusDTO the enumEmpStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumEmpStatusDTO, or with status {@code 400 (Bad Request)} if the enumEmpStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-emp-statuses")
    public ResponseEntity<EnumEmpStatusDTO> createEnumEmpStatus(@RequestBody EnumEmpStatusDTO enumEmpStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EnumEmpStatus : {}", enumEmpStatusDTO);
        if (enumEmpStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumEmpStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumEmpStatusDTO result = enumEmpStatusService.save(enumEmpStatusDTO);
        return ResponseEntity.created(new URI("/api/enum-emp-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-emp-statuses} : Updates an existing enumEmpStatus.
     *
     * @param enumEmpStatusDTO the enumEmpStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumEmpStatusDTO,
     * or with status {@code 400 (Bad Request)} if the enumEmpStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumEmpStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-emp-statuses")
    public ResponseEntity<EnumEmpStatusDTO> updateEnumEmpStatus(@RequestBody EnumEmpStatusDTO enumEmpStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EnumEmpStatus : {}", enumEmpStatusDTO);
        if (enumEmpStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumEmpStatusDTO result = enumEmpStatusService.save(enumEmpStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumEmpStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-emp-statuses} : get all the enumEmpStatuses.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumEmpStatuses in body.
     */
    @GetMapping("/enum-emp-statuses")
    public ResponseEntity<List<EnumEmpStatusDTO>> getAllEnumEmpStatuses(EnumEmpStatusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumEmpStatuses by criteria: {}", criteria);
        Page<EnumEmpStatusDTO> page = enumEmpStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-emp-statuses/count} : count all the enumEmpStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-emp-statuses/count")
    public ResponseEntity<Long> countEnumEmpStatuses(EnumEmpStatusCriteria criteria) {
        log.debug("REST request to count EnumEmpStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumEmpStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-emp-statuses/:id} : get the "id" enumEmpStatus.
     *
     * @param id the id of the enumEmpStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumEmpStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-emp-statuses/{id}")
    public ResponseEntity<EnumEmpStatusDTO> getEnumEmpStatus(@PathVariable Long id) {
        log.debug("REST request to get EnumEmpStatus : {}", id);
        Optional<EnumEmpStatusDTO> enumEmpStatusDTO = enumEmpStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumEmpStatusDTO);
    }

    /**
     * {@code DELETE  /enum-emp-statuses/:id} : delete the "id" enumEmpStatus.
     *
     * @param id the id of the enumEmpStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-emp-statuses/{id}")
    public ResponseEntity<Void> deleteEnumEmpStatus(@PathVariable Long id) {
        log.debug("REST request to delete EnumEmpStatus : {}", id);
        enumEmpStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
