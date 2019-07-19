package com.cc.web.rest;

import com.cc.service.DimissionService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.DimissionDTO;
import com.cc.service.dto.DimissionCriteria;
import com.cc.service.DimissionQueryService;

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
 * REST controller for managing {@link com.cc.domain.Dimission}.
 */
@RestController
@RequestMapping("/api")
public class DimissionResource {

    private final Logger log = LoggerFactory.getLogger(DimissionResource.class);

    private static final String ENTITY_NAME = "dimission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DimissionService dimissionService;

    private final DimissionQueryService dimissionQueryService;

    public DimissionResource(DimissionService dimissionService, DimissionQueryService dimissionQueryService) {
        this.dimissionService = dimissionService;
        this.dimissionQueryService = dimissionQueryService;
    }

    /**
     * {@code POST  /dimissions} : Create a new dimission.
     *
     * @param dimissionDTO the dimissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dimissionDTO, or with status {@code 400 (Bad Request)} if the dimission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dimissions")
    public ResponseEntity<DimissionDTO> createDimission(@RequestBody DimissionDTO dimissionDTO) throws URISyntaxException {
        log.debug("REST request to save Dimission : {}", dimissionDTO);
        if (dimissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new dimission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DimissionDTO result = dimissionService.save(dimissionDTO);
        return ResponseEntity.created(new URI("/api/dimissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dimissions} : Updates an existing dimission.
     *
     * @param dimissionDTO the dimissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dimissionDTO,
     * or with status {@code 400 (Bad Request)} if the dimissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dimissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dimissions")
    public ResponseEntity<DimissionDTO> updateDimission(@RequestBody DimissionDTO dimissionDTO) throws URISyntaxException {
        log.debug("REST request to update Dimission : {}", dimissionDTO);
        if (dimissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DimissionDTO result = dimissionService.save(dimissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dimissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dimissions} : get all the dimissions.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dimissions in body.
     */
    @GetMapping("/dimissions")
    public ResponseEntity<List<DimissionDTO>> getAllDimissions(DimissionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Dimissions by criteria: {}", criteria);
        Page<DimissionDTO> page = dimissionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dimissions/count} : count all the dimissions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dimissions/count")
    public ResponseEntity<Long> countDimissions(DimissionCriteria criteria) {
        log.debug("REST request to count Dimissions by criteria: {}", criteria);
        return ResponseEntity.ok().body(dimissionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dimissions/:id} : get the "id" dimission.
     *
     * @param id the id of the dimissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dimissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dimissions/{id}")
    public ResponseEntity<DimissionDTO> getDimission(@PathVariable Long id) {
        log.debug("REST request to get Dimission : {}", id);
        Optional<DimissionDTO> dimissionDTO = dimissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dimissionDTO);
    }

    /**
     * {@code DELETE  /dimissions/:id} : delete the "id" dimission.
     *
     * @param id the id of the dimissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dimissions/{id}")
    public ResponseEntity<Void> deleteDimission(@PathVariable Long id) {
        log.debug("REST request to delete Dimission : {}", id);
        dimissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
