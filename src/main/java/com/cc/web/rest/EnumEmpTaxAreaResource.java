package com.cc.web.rest;

import com.cc.service.EnumEmpTaxAreaService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumEmpTaxAreaDTO;
import com.cc.service.dto.EnumEmpTaxAreaCriteria;
import com.cc.service.EnumEmpTaxAreaQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumEmpTaxArea}.
 */
@RestController
@RequestMapping("/api")
public class EnumEmpTaxAreaResource {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTaxAreaResource.class);

    private static final String ENTITY_NAME = "enumEmpTaxArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumEmpTaxAreaService enumEmpTaxAreaService;

    private final EnumEmpTaxAreaQueryService enumEmpTaxAreaQueryService;

    public EnumEmpTaxAreaResource(EnumEmpTaxAreaService enumEmpTaxAreaService, EnumEmpTaxAreaQueryService enumEmpTaxAreaQueryService) {
        this.enumEmpTaxAreaService = enumEmpTaxAreaService;
        this.enumEmpTaxAreaQueryService = enumEmpTaxAreaQueryService;
    }

    /**
     * {@code POST  /enum-emp-tax-areas} : Create a new enumEmpTaxArea.
     *
     * @param enumEmpTaxAreaDTO the enumEmpTaxAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumEmpTaxAreaDTO, or with status {@code 400 (Bad Request)} if the enumEmpTaxArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-emp-tax-areas")
    public ResponseEntity<EnumEmpTaxAreaDTO> createEnumEmpTaxArea(@RequestBody EnumEmpTaxAreaDTO enumEmpTaxAreaDTO) throws URISyntaxException {
        log.debug("REST request to save EnumEmpTaxArea : {}", enumEmpTaxAreaDTO);
        if (enumEmpTaxAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumEmpTaxArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumEmpTaxAreaDTO result = enumEmpTaxAreaService.save(enumEmpTaxAreaDTO);
        return ResponseEntity.created(new URI("/api/enum-emp-tax-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-emp-tax-areas} : Updates an existing enumEmpTaxArea.
     *
     * @param enumEmpTaxAreaDTO the enumEmpTaxAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumEmpTaxAreaDTO,
     * or with status {@code 400 (Bad Request)} if the enumEmpTaxAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumEmpTaxAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-emp-tax-areas")
    public ResponseEntity<EnumEmpTaxAreaDTO> updateEnumEmpTaxArea(@RequestBody EnumEmpTaxAreaDTO enumEmpTaxAreaDTO) throws URISyntaxException {
        log.debug("REST request to update EnumEmpTaxArea : {}", enumEmpTaxAreaDTO);
        if (enumEmpTaxAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumEmpTaxAreaDTO result = enumEmpTaxAreaService.save(enumEmpTaxAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumEmpTaxAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-emp-tax-areas} : get all the enumEmpTaxAreas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumEmpTaxAreas in body.
     */
    @GetMapping("/enum-emp-tax-areas")
    public ResponseEntity<List<EnumEmpTaxAreaDTO>> getAllEnumEmpTaxAreas(EnumEmpTaxAreaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumEmpTaxAreas by criteria: {}", criteria);
        Page<EnumEmpTaxAreaDTO> page = enumEmpTaxAreaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-emp-tax-areas/count} : count all the enumEmpTaxAreas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-emp-tax-areas/count")
    public ResponseEntity<Long> countEnumEmpTaxAreas(EnumEmpTaxAreaCriteria criteria) {
        log.debug("REST request to count EnumEmpTaxAreas by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumEmpTaxAreaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-emp-tax-areas/:id} : get the "id" enumEmpTaxArea.
     *
     * @param id the id of the enumEmpTaxAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumEmpTaxAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-emp-tax-areas/{id}")
    public ResponseEntity<EnumEmpTaxAreaDTO> getEnumEmpTaxArea(@PathVariable Long id) {
        log.debug("REST request to get EnumEmpTaxArea : {}", id);
        Optional<EnumEmpTaxAreaDTO> enumEmpTaxAreaDTO = enumEmpTaxAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumEmpTaxAreaDTO);
    }

    /**
     * {@code DELETE  /enum-emp-tax-areas/:id} : delete the "id" enumEmpTaxArea.
     *
     * @param id the id of the enumEmpTaxAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-emp-tax-areas/{id}")
    public ResponseEntity<Void> deleteEnumEmpTaxArea(@PathVariable Long id) {
        log.debug("REST request to delete EnumEmpTaxArea : {}", id);
        enumEmpTaxAreaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
