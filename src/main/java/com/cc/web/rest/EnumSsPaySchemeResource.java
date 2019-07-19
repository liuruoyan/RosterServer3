package com.cc.web.rest;

import com.cc.service.EnumSsPaySchemeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumSsPaySchemeDTO;
import com.cc.service.dto.EnumSsPaySchemeCriteria;
import com.cc.service.EnumSsPaySchemeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumSsPayScheme}.
 */
@RestController
@RequestMapping("/api")
public class EnumSsPaySchemeResource {

    private final Logger log = LoggerFactory.getLogger(EnumSsPaySchemeResource.class);

    private static final String ENTITY_NAME = "enumSsPayScheme";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumSsPaySchemeService enumSsPaySchemeService;

    private final EnumSsPaySchemeQueryService enumSsPaySchemeQueryService;

    public EnumSsPaySchemeResource(EnumSsPaySchemeService enumSsPaySchemeService, EnumSsPaySchemeQueryService enumSsPaySchemeQueryService) {
        this.enumSsPaySchemeService = enumSsPaySchemeService;
        this.enumSsPaySchemeQueryService = enumSsPaySchemeQueryService;
    }

    /**
     * {@code POST  /enum-ss-pay-schemes} : Create a new enumSsPayScheme.
     *
     * @param enumSsPaySchemeDTO the enumSsPaySchemeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumSsPaySchemeDTO, or with status {@code 400 (Bad Request)} if the enumSsPayScheme has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-ss-pay-schemes")
    public ResponseEntity<EnumSsPaySchemeDTO> createEnumSsPayScheme(@RequestBody EnumSsPaySchemeDTO enumSsPaySchemeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumSsPayScheme : {}", enumSsPaySchemeDTO);
        if (enumSsPaySchemeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumSsPayScheme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumSsPaySchemeDTO result = enumSsPaySchemeService.save(enumSsPaySchemeDTO);
        return ResponseEntity.created(new URI("/api/enum-ss-pay-schemes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-ss-pay-schemes} : Updates an existing enumSsPayScheme.
     *
     * @param enumSsPaySchemeDTO the enumSsPaySchemeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumSsPaySchemeDTO,
     * or with status {@code 400 (Bad Request)} if the enumSsPaySchemeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumSsPaySchemeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-ss-pay-schemes")
    public ResponseEntity<EnumSsPaySchemeDTO> updateEnumSsPayScheme(@RequestBody EnumSsPaySchemeDTO enumSsPaySchemeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumSsPayScheme : {}", enumSsPaySchemeDTO);
        if (enumSsPaySchemeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumSsPaySchemeDTO result = enumSsPaySchemeService.save(enumSsPaySchemeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumSsPaySchemeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-ss-pay-schemes} : get all the enumSsPaySchemes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumSsPaySchemes in body.
     */
    @GetMapping("/enum-ss-pay-schemes")
    public ResponseEntity<List<EnumSsPaySchemeDTO>> getAllEnumSsPaySchemes(EnumSsPaySchemeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumSsPaySchemes by criteria: {}", criteria);
        Page<EnumSsPaySchemeDTO> page = enumSsPaySchemeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-ss-pay-schemes/count} : count all the enumSsPaySchemes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-ss-pay-schemes/count")
    public ResponseEntity<Long> countEnumSsPaySchemes(EnumSsPaySchemeCriteria criteria) {
        log.debug("REST request to count EnumSsPaySchemes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumSsPaySchemeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-ss-pay-schemes/:id} : get the "id" enumSsPayScheme.
     *
     * @param id the id of the enumSsPaySchemeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumSsPaySchemeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-ss-pay-schemes/{id}")
    public ResponseEntity<EnumSsPaySchemeDTO> getEnumSsPayScheme(@PathVariable Long id) {
        log.debug("REST request to get EnumSsPayScheme : {}", id);
        Optional<EnumSsPaySchemeDTO> enumSsPaySchemeDTO = enumSsPaySchemeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumSsPaySchemeDTO);
    }

    /**
     * {@code DELETE  /enum-ss-pay-schemes/:id} : delete the "id" enumSsPayScheme.
     *
     * @param id the id of the enumSsPaySchemeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-ss-pay-schemes/{id}")
    public ResponseEntity<Void> deleteEnumSsPayScheme(@PathVariable Long id) {
        log.debug("REST request to delete EnumSsPayScheme : {}", id);
        enumSsPaySchemeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
