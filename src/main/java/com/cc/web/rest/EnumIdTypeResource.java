package com.cc.web.rest;

import com.cc.service.EnumIdTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumIdTypeDTO;
import com.cc.service.dto.EnumIdTypeCriteria;
import com.cc.service.EnumIdTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumIdType}.
 */
@RestController
@RequestMapping("/api")
public class EnumIdTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumIdTypeResource.class);

    private static final String ENTITY_NAME = "enumIdType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumIdTypeService enumIdTypeService;

    private final EnumIdTypeQueryService enumIdTypeQueryService;

    public EnumIdTypeResource(EnumIdTypeService enumIdTypeService, EnumIdTypeQueryService enumIdTypeQueryService) {
        this.enumIdTypeService = enumIdTypeService;
        this.enumIdTypeQueryService = enumIdTypeQueryService;
    }

    /**
     * {@code POST  /enum-id-types} : Create a new enumIdType.
     *
     * @param enumIdTypeDTO the enumIdTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumIdTypeDTO, or with status {@code 400 (Bad Request)} if the enumIdType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-id-types")
    public ResponseEntity<EnumIdTypeDTO> createEnumIdType(@RequestBody EnumIdTypeDTO enumIdTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumIdType : {}", enumIdTypeDTO);
        if (enumIdTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumIdType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumIdTypeDTO result = enumIdTypeService.save(enumIdTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-id-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-id-types} : Updates an existing enumIdType.
     *
     * @param enumIdTypeDTO the enumIdTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumIdTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumIdTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumIdTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-id-types")
    public ResponseEntity<EnumIdTypeDTO> updateEnumIdType(@RequestBody EnumIdTypeDTO enumIdTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumIdType : {}", enumIdTypeDTO);
        if (enumIdTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumIdTypeDTO result = enumIdTypeService.save(enumIdTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumIdTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-id-types} : get all the enumIdTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumIdTypes in body.
     */
    @GetMapping("/enum-id-types")
    public ResponseEntity<List<EnumIdTypeDTO>> getAllEnumIdTypes(EnumIdTypeCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumIdTypes by criteria: {}", criteria);
        Page<EnumIdTypeDTO> page = enumIdTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-id-types/count} : count all the enumIdTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-id-types/count")
    public ResponseEntity<Long> countEnumIdTypes(EnumIdTypeCriteria criteria) {
        log.debug("REST request to count EnumIdTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumIdTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-id-types/:id} : get the "id" enumIdType.
     *
     * @param id the id of the enumIdTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumIdTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-id-types/{id}")
    public ResponseEntity<EnumIdTypeDTO> getEnumIdType(@PathVariable Long id) {
        log.debug("REST request to get EnumIdType : {}", id);
        Optional<EnumIdTypeDTO> enumIdTypeDTO = enumIdTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumIdTypeDTO);
    }

    /**
     * {@code DELETE  /enum-id-types/:id} : delete the "id" enumIdType.
     *
     * @param id the id of the enumIdTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-id-types/{id}")
    public ResponseEntity<Void> deleteEnumIdType(@PathVariable Long id) {
        log.debug("REST request to delete EnumIdType : {}", id);
        enumIdTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
