package com.cc.web.rest;

import com.cc.service.EnumEmpTaxerTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumEmpTaxerTypeDTO;
import com.cc.service.dto.EnumEmpTaxerTypeCriteria;
import com.cc.service.EnumEmpTaxerTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumEmpTaxerType}.
 */
@RestController
@RequestMapping("/api")
public class EnumEmpTaxerTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTaxerTypeResource.class);

    private static final String ENTITY_NAME = "enumEmpTaxerType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumEmpTaxerTypeService enumEmpTaxerTypeService;

    private final EnumEmpTaxerTypeQueryService enumEmpTaxerTypeQueryService;

    public EnumEmpTaxerTypeResource(EnumEmpTaxerTypeService enumEmpTaxerTypeService, EnumEmpTaxerTypeQueryService enumEmpTaxerTypeQueryService) {
        this.enumEmpTaxerTypeService = enumEmpTaxerTypeService;
        this.enumEmpTaxerTypeQueryService = enumEmpTaxerTypeQueryService;
    }

    /**
     * {@code POST  /enum-emp-taxer-types} : Create a new enumEmpTaxerType.
     *
     * @param enumEmpTaxerTypeDTO the enumEmpTaxerTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumEmpTaxerTypeDTO, or with status {@code 400 (Bad Request)} if the enumEmpTaxerType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-emp-taxer-types")
    public ResponseEntity<EnumEmpTaxerTypeDTO> createEnumEmpTaxerType(@RequestBody EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumEmpTaxerType : {}", enumEmpTaxerTypeDTO);
        if (enumEmpTaxerTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumEmpTaxerType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumEmpTaxerTypeDTO result = enumEmpTaxerTypeService.save(enumEmpTaxerTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-emp-taxer-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-emp-taxer-types} : Updates an existing enumEmpTaxerType.
     *
     * @param enumEmpTaxerTypeDTO the enumEmpTaxerTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumEmpTaxerTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumEmpTaxerTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumEmpTaxerTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-emp-taxer-types")
    public ResponseEntity<EnumEmpTaxerTypeDTO> updateEnumEmpTaxerType(@RequestBody EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumEmpTaxerType : {}", enumEmpTaxerTypeDTO);
        if (enumEmpTaxerTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumEmpTaxerTypeDTO result = enumEmpTaxerTypeService.save(enumEmpTaxerTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumEmpTaxerTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-emp-taxer-types} : get all the enumEmpTaxerTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumEmpTaxerTypes in body.
     */
    @GetMapping("/enum-emp-taxer-types")
    public ResponseEntity<List<EnumEmpTaxerTypeDTO>> getAllEnumEmpTaxerTypes(EnumEmpTaxerTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumEmpTaxerTypes by criteria: {}", criteria);
        Page<EnumEmpTaxerTypeDTO> page = enumEmpTaxerTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-emp-taxer-types/count} : count all the enumEmpTaxerTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-emp-taxer-types/count")
    public ResponseEntity<Long> countEnumEmpTaxerTypes(EnumEmpTaxerTypeCriteria criteria) {
        log.debug("REST request to count EnumEmpTaxerTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumEmpTaxerTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-emp-taxer-types/:id} : get the "id" enumEmpTaxerType.
     *
     * @param id the id of the enumEmpTaxerTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumEmpTaxerTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-emp-taxer-types/{id}")
    public ResponseEntity<EnumEmpTaxerTypeDTO> getEnumEmpTaxerType(@PathVariable Long id) {
        log.debug("REST request to get EnumEmpTaxerType : {}", id);
        Optional<EnumEmpTaxerTypeDTO> enumEmpTaxerTypeDTO = enumEmpTaxerTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumEmpTaxerTypeDTO);
    }

    /**
     * {@code DELETE  /enum-emp-taxer-types/:id} : delete the "id" enumEmpTaxerType.
     *
     * @param id the id of the enumEmpTaxerTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-emp-taxer-types/{id}")
    public ResponseEntity<Void> deleteEnumEmpTaxerType(@PathVariable Long id) {
        log.debug("REST request to delete EnumEmpTaxerType : {}", id);
        enumEmpTaxerTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
