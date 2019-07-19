package com.cc.web.rest;

import com.cc.service.EnumDimissionTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumDimissionTypeDTO;
import com.cc.service.dto.EnumDimissionTypeCriteria;
import com.cc.service.EnumDimissionTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumDimissionType}.
 */
@RestController
@RequestMapping("/api")
public class EnumDimissionTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumDimissionTypeResource.class);

    private static final String ENTITY_NAME = "enumDimissionType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumDimissionTypeService enumDimissionTypeService;

    private final EnumDimissionTypeQueryService enumDimissionTypeQueryService;

    public EnumDimissionTypeResource(EnumDimissionTypeService enumDimissionTypeService, EnumDimissionTypeQueryService enumDimissionTypeQueryService) {
        this.enumDimissionTypeService = enumDimissionTypeService;
        this.enumDimissionTypeQueryService = enumDimissionTypeQueryService;
    }

    /**
     * {@code POST  /enum-dimission-types} : Create a new enumDimissionType.
     *
     * @param enumDimissionTypeDTO the enumDimissionTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumDimissionTypeDTO, or with status {@code 400 (Bad Request)} if the enumDimissionType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-dimission-types")
    public ResponseEntity<EnumDimissionTypeDTO> createEnumDimissionType(@RequestBody EnumDimissionTypeDTO enumDimissionTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumDimissionType : {}", enumDimissionTypeDTO);
        if (enumDimissionTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumDimissionType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumDimissionTypeDTO result = enumDimissionTypeService.save(enumDimissionTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-dimission-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-dimission-types} : Updates an existing enumDimissionType.
     *
     * @param enumDimissionTypeDTO the enumDimissionTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumDimissionTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumDimissionTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumDimissionTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-dimission-types")
    public ResponseEntity<EnumDimissionTypeDTO> updateEnumDimissionType(@RequestBody EnumDimissionTypeDTO enumDimissionTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumDimissionType : {}", enumDimissionTypeDTO);
        if (enumDimissionTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumDimissionTypeDTO result = enumDimissionTypeService.save(enumDimissionTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumDimissionTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-dimission-types} : get all the enumDimissionTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumDimissionTypes in body.
     */
    @GetMapping("/enum-dimission-types")
    public ResponseEntity<List<EnumDimissionTypeDTO>> getAllEnumDimissionTypes(EnumDimissionTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumDimissionTypes by criteria: {}", criteria);
        Page<EnumDimissionTypeDTO> page = enumDimissionTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-dimission-types/count} : count all the enumDimissionTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-dimission-types/count")
    public ResponseEntity<Long> countEnumDimissionTypes(EnumDimissionTypeCriteria criteria) {
        log.debug("REST request to count EnumDimissionTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumDimissionTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-dimission-types/:id} : get the "id" enumDimissionType.
     *
     * @param id the id of the enumDimissionTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumDimissionTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-dimission-types/{id}")
    public ResponseEntity<EnumDimissionTypeDTO> getEnumDimissionType(@PathVariable Long id) {
        log.debug("REST request to get EnumDimissionType : {}", id);
        Optional<EnumDimissionTypeDTO> enumDimissionTypeDTO = enumDimissionTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumDimissionTypeDTO);
    }

    /**
     * {@code DELETE  /enum-dimission-types/:id} : delete the "id" enumDimissionType.
     *
     * @param id the id of the enumDimissionTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-dimission-types/{id}")
    public ResponseEntity<Void> deleteEnumDimissionType(@PathVariable Long id) {
        log.debug("REST request to delete EnumDimissionType : {}", id);
        enumDimissionTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
