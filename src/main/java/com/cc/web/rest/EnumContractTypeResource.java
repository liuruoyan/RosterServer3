package com.cc.web.rest;

import com.cc.service.EnumContractTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumContractTypeDTO;
import com.cc.service.dto.EnumContractTypeCriteria;
import com.cc.service.EnumContractTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumContractType}.
 */
@RestController
@RequestMapping("/api")
public class EnumContractTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumContractTypeResource.class);

    private static final String ENTITY_NAME = "enumContractType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumContractTypeService enumContractTypeService;

    private final EnumContractTypeQueryService enumContractTypeQueryService;

    public EnumContractTypeResource(EnumContractTypeService enumContractTypeService, EnumContractTypeQueryService enumContractTypeQueryService) {
        this.enumContractTypeService = enumContractTypeService;
        this.enumContractTypeQueryService = enumContractTypeQueryService;
    }

    /**
     * {@code POST  /enum-contract-types} : Create a new enumContractType.
     *
     * @param enumContractTypeDTO the enumContractTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumContractTypeDTO, or with status {@code 400 (Bad Request)} if the enumContractType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-contract-types")
    public ResponseEntity<EnumContractTypeDTO> createEnumContractType(@RequestBody EnumContractTypeDTO enumContractTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumContractType : {}", enumContractTypeDTO);
        if (enumContractTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumContractType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumContractTypeDTO result = enumContractTypeService.save(enumContractTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-contract-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-contract-types} : Updates an existing enumContractType.
     *
     * @param enumContractTypeDTO the enumContractTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumContractTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumContractTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumContractTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-contract-types")
    public ResponseEntity<EnumContractTypeDTO> updateEnumContractType(@RequestBody EnumContractTypeDTO enumContractTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumContractType : {}", enumContractTypeDTO);
        if (enumContractTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumContractTypeDTO result = enumContractTypeService.save(enumContractTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumContractTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-contract-types} : get all the enumContractTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumContractTypes in body.
     */
    @GetMapping("/enum-contract-types")
    public ResponseEntity<List<EnumContractTypeDTO>> getAllEnumContractTypes(EnumContractTypeCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumContractTypes by criteria: {}", criteria);
        Page<EnumContractTypeDTO> page = enumContractTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-contract-types/count} : count all the enumContractTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-contract-types/count")
    public ResponseEntity<Long> countEnumContractTypes(EnumContractTypeCriteria criteria) {
        log.debug("REST request to count EnumContractTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumContractTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-contract-types/:id} : get the "id" enumContractType.
     *
     * @param id the id of the enumContractTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumContractTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-contract-types/{id}")
    public ResponseEntity<EnumContractTypeDTO> getEnumContractType(@PathVariable Long id) {
        log.debug("REST request to get EnumContractType : {}", id);
        Optional<EnumContractTypeDTO> enumContractTypeDTO = enumContractTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumContractTypeDTO);
    }

    /**
     * {@code DELETE  /enum-contract-types/:id} : delete the "id" enumContractType.
     *
     * @param id the id of the enumContractTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-contract-types/{id}")
    public ResponseEntity<Void> deleteEnumContractType(@PathVariable Long id) {
        log.debug("REST request to delete EnumContractType : {}", id);
        enumContractTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
