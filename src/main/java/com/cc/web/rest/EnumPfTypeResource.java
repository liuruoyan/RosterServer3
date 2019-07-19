package com.cc.web.rest;

import com.cc.service.EnumPfTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumPfTypeDTO;
import com.cc.service.dto.EnumPfTypeCriteria;
import com.cc.service.EnumPfTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumPfType}.
 */
@RestController
@RequestMapping("/api")
public class EnumPfTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumPfTypeResource.class);

    private static final String ENTITY_NAME = "enumPfType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumPfTypeService enumPfTypeService;

    private final EnumPfTypeQueryService enumPfTypeQueryService;

    public EnumPfTypeResource(EnumPfTypeService enumPfTypeService, EnumPfTypeQueryService enumPfTypeQueryService) {
        this.enumPfTypeService = enumPfTypeService;
        this.enumPfTypeQueryService = enumPfTypeQueryService;
    }

    /**
     * {@code POST  /enum-pf-types} : Create a new enumPfType.
     *
     * @param enumPfTypeDTO the enumPfTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumPfTypeDTO, or with status {@code 400 (Bad Request)} if the enumPfType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-pf-types")
    public ResponseEntity<EnumPfTypeDTO> createEnumPfType(@RequestBody EnumPfTypeDTO enumPfTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumPfType : {}", enumPfTypeDTO);
        if (enumPfTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumPfType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumPfTypeDTO result = enumPfTypeService.save(enumPfTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-pf-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-pf-types} : Updates an existing enumPfType.
     *
     * @param enumPfTypeDTO the enumPfTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumPfTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumPfTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumPfTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-pf-types")
    public ResponseEntity<EnumPfTypeDTO> updateEnumPfType(@RequestBody EnumPfTypeDTO enumPfTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumPfType : {}", enumPfTypeDTO);
        if (enumPfTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumPfTypeDTO result = enumPfTypeService.save(enumPfTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumPfTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-pf-types} : get all the enumPfTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumPfTypes in body.
     */
    @GetMapping("/enum-pf-types")
    public ResponseEntity<List<EnumPfTypeDTO>> getAllEnumPfTypes(EnumPfTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumPfTypes by criteria: {}", criteria);
        Page<EnumPfTypeDTO> page = enumPfTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-pf-types/count} : count all the enumPfTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-pf-types/count")
    public ResponseEntity<Long> countEnumPfTypes(EnumPfTypeCriteria criteria) {
        log.debug("REST request to count EnumPfTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumPfTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-pf-types/:id} : get the "id" enumPfType.
     *
     * @param id the id of the enumPfTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumPfTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-pf-types/{id}")
    public ResponseEntity<EnumPfTypeDTO> getEnumPfType(@PathVariable Long id) {
        log.debug("REST request to get EnumPfType : {}", id);
        Optional<EnumPfTypeDTO> enumPfTypeDTO = enumPfTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumPfTypeDTO);
    }

    /**
     * {@code DELETE  /enum-pf-types/:id} : delete the "id" enumPfType.
     *
     * @param id the id of the enumPfTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-pf-types/{id}")
    public ResponseEntity<Void> deleteEnumPfType(@PathVariable Long id) {
        log.debug("REST request to delete EnumPfType : {}", id);
        enumPfTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
