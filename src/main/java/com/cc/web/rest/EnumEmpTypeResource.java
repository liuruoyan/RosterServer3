package com.cc.web.rest;

import com.cc.service.EnumEmpTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumEmpTypeDTO;
import com.cc.service.dto.EnumEmpTypeCriteria;
import com.cc.service.EnumEmpTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumEmpType}.
 */
@RestController
@RequestMapping("/api")
public class EnumEmpTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTypeResource.class);

    private static final String ENTITY_NAME = "enumEmpType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumEmpTypeService enumEmpTypeService;

    private final EnumEmpTypeQueryService enumEmpTypeQueryService;

    public EnumEmpTypeResource(EnumEmpTypeService enumEmpTypeService, EnumEmpTypeQueryService enumEmpTypeQueryService) {
        this.enumEmpTypeService = enumEmpTypeService;
        this.enumEmpTypeQueryService = enumEmpTypeQueryService;
    }

    /**
     * {@code POST  /enum-emp-types} : Create a new enumEmpType.
     *
     * @param enumEmpTypeDTO the enumEmpTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumEmpTypeDTO, or with status {@code 400 (Bad Request)} if the enumEmpType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-emp-types")
    public ResponseEntity<EnumEmpTypeDTO> createEnumEmpType(@RequestBody EnumEmpTypeDTO enumEmpTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumEmpType : {}", enumEmpTypeDTO);
        if (enumEmpTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumEmpType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumEmpTypeDTO result = enumEmpTypeService.save(enumEmpTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-emp-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-emp-types} : Updates an existing enumEmpType.
     *
     * @param enumEmpTypeDTO the enumEmpTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumEmpTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumEmpTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumEmpTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-emp-types")
    public ResponseEntity<EnumEmpTypeDTO> updateEnumEmpType(@RequestBody EnumEmpTypeDTO enumEmpTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumEmpType : {}", enumEmpTypeDTO);
        if (enumEmpTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumEmpTypeDTO result = enumEmpTypeService.save(enumEmpTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumEmpTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-emp-types} : get all the enumEmpTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumEmpTypes in body.
     */
    @GetMapping("/enum-emp-types")
    public ResponseEntity<List<EnumEmpTypeDTO>> getAllEnumEmpTypes(EnumEmpTypeCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumEmpTypes by criteria: {}", criteria);
        Page<EnumEmpTypeDTO> page = enumEmpTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-emp-types/count} : count all the enumEmpTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-emp-types/count")
    public ResponseEntity<Long> countEnumEmpTypes(EnumEmpTypeCriteria criteria) {
        log.debug("REST request to count EnumEmpTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumEmpTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-emp-types/:id} : get the "id" enumEmpType.
     *
     * @param id the id of the enumEmpTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumEmpTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-emp-types/{id}")
    public ResponseEntity<EnumEmpTypeDTO> getEnumEmpType(@PathVariable Long id) {
        log.debug("REST request to get EnumEmpType : {}", id);
        Optional<EnumEmpTypeDTO> enumEmpTypeDTO = enumEmpTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumEmpTypeDTO);
    }

    /**
     * {@code DELETE  /enum-emp-types/:id} : delete the "id" enumEmpType.
     *
     * @param id the id of the enumEmpTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-emp-types/{id}")
    public ResponseEntity<Void> deleteEnumEmpType(@PathVariable Long id) {
        log.debug("REST request to delete EnumEmpType : {}", id);
        enumEmpTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
