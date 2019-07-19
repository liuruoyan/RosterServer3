package com.cc.web.rest;

import com.cc.service.EnumPfPaySchemeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumPfPaySchemeDTO;
import com.cc.service.dto.EnumPfPaySchemeCriteria;
import com.cc.service.EnumPfPaySchemeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumPfPayScheme}.
 */
@RestController
@RequestMapping("/api")
public class EnumPfPaySchemeResource {

    private final Logger log = LoggerFactory.getLogger(EnumPfPaySchemeResource.class);

    private static final String ENTITY_NAME = "enumPfPayScheme";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumPfPaySchemeService enumPfPaySchemeService;

    private final EnumPfPaySchemeQueryService enumPfPaySchemeQueryService;

    public EnumPfPaySchemeResource(EnumPfPaySchemeService enumPfPaySchemeService, EnumPfPaySchemeQueryService enumPfPaySchemeQueryService) {
        this.enumPfPaySchemeService = enumPfPaySchemeService;
        this.enumPfPaySchemeQueryService = enumPfPaySchemeQueryService;
    }

    /**
     * {@code POST  /enum-pf-pay-schemes} : Create a new enumPfPayScheme.
     *
     * @param enumPfPaySchemeDTO the enumPfPaySchemeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumPfPaySchemeDTO, or with status {@code 400 (Bad Request)} if the enumPfPayScheme has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-pf-pay-schemes")
    public ResponseEntity<EnumPfPaySchemeDTO> createEnumPfPayScheme(@RequestBody EnumPfPaySchemeDTO enumPfPaySchemeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumPfPayScheme : {}", enumPfPaySchemeDTO);
        if (enumPfPaySchemeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumPfPayScheme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumPfPaySchemeDTO result = enumPfPaySchemeService.save(enumPfPaySchemeDTO);
        return ResponseEntity.created(new URI("/api/enum-pf-pay-schemes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-pf-pay-schemes} : Updates an existing enumPfPayScheme.
     *
     * @param enumPfPaySchemeDTO the enumPfPaySchemeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumPfPaySchemeDTO,
     * or with status {@code 400 (Bad Request)} if the enumPfPaySchemeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumPfPaySchemeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-pf-pay-schemes")
    public ResponseEntity<EnumPfPaySchemeDTO> updateEnumPfPayScheme(@RequestBody EnumPfPaySchemeDTO enumPfPaySchemeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumPfPayScheme : {}", enumPfPaySchemeDTO);
        if (enumPfPaySchemeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumPfPaySchemeDTO result = enumPfPaySchemeService.save(enumPfPaySchemeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumPfPaySchemeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-pf-pay-schemes} : get all the enumPfPaySchemes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumPfPaySchemes in body.
     */
    @GetMapping("/enum-pf-pay-schemes")
    public ResponseEntity<List<EnumPfPaySchemeDTO>> getAllEnumPfPaySchemes(EnumPfPaySchemeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumPfPaySchemes by criteria: {}", criteria);
        Page<EnumPfPaySchemeDTO> page = enumPfPaySchemeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-pf-pay-schemes/count} : count all the enumPfPaySchemes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-pf-pay-schemes/count")
    public ResponseEntity<Long> countEnumPfPaySchemes(EnumPfPaySchemeCriteria criteria) {
        log.debug("REST request to count EnumPfPaySchemes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumPfPaySchemeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-pf-pay-schemes/:id} : get the "id" enumPfPayScheme.
     *
     * @param id the id of the enumPfPaySchemeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumPfPaySchemeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-pf-pay-schemes/{id}")
    public ResponseEntity<EnumPfPaySchemeDTO> getEnumPfPayScheme(@PathVariable Long id) {
        log.debug("REST request to get EnumPfPayScheme : {}", id);
        Optional<EnumPfPaySchemeDTO> enumPfPaySchemeDTO = enumPfPaySchemeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumPfPaySchemeDTO);
    }

    /**
     * {@code DELETE  /enum-pf-pay-schemes/:id} : delete the "id" enumPfPayScheme.
     *
     * @param id the id of the enumPfPaySchemeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-pf-pay-schemes/{id}")
    public ResponseEntity<Void> deleteEnumPfPayScheme(@PathVariable Long id) {
        log.debug("REST request to delete EnumPfPayScheme : {}", id);
        enumPfPaySchemeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
