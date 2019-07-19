package com.cc.web.rest;

import com.cc.service.EnumAccountTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumAccountTypeDTO;
import com.cc.service.dto.EnumAccountTypeCriteria;
import com.cc.service.EnumAccountTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumAccountType}.
 */
@RestController
@RequestMapping("/api")
public class EnumAccountTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumAccountTypeResource.class);

    private static final String ENTITY_NAME = "enumAccountType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumAccountTypeService enumAccountTypeService;

    private final EnumAccountTypeQueryService enumAccountTypeQueryService;

    public EnumAccountTypeResource(EnumAccountTypeService enumAccountTypeService, EnumAccountTypeQueryService enumAccountTypeQueryService) {
        this.enumAccountTypeService = enumAccountTypeService;
        this.enumAccountTypeQueryService = enumAccountTypeQueryService;
    }

    /**
     * {@code POST  /enum-account-types} : Create a new enumAccountType.
     *
     * @param enumAccountTypeDTO the enumAccountTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumAccountTypeDTO, or with status {@code 400 (Bad Request)} if the enumAccountType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-account-types")
    public ResponseEntity<EnumAccountTypeDTO> createEnumAccountType(@RequestBody EnumAccountTypeDTO enumAccountTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumAccountType : {}", enumAccountTypeDTO);
        if (enumAccountTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumAccountType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumAccountTypeDTO result = enumAccountTypeService.save(enumAccountTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-account-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-account-types} : Updates an existing enumAccountType.
     *
     * @param enumAccountTypeDTO the enumAccountTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumAccountTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumAccountTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumAccountTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-account-types")
    public ResponseEntity<EnumAccountTypeDTO> updateEnumAccountType(@RequestBody EnumAccountTypeDTO enumAccountTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumAccountType : {}", enumAccountTypeDTO);
        if (enumAccountTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumAccountTypeDTO result = enumAccountTypeService.save(enumAccountTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumAccountTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-account-types} : get all the enumAccountTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumAccountTypes in body.
     */
    @GetMapping("/enum-account-types")
    public ResponseEntity<List<EnumAccountTypeDTO>> getAllEnumAccountTypes(EnumAccountTypeCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumAccountTypes by criteria: {}", criteria);
        Page<EnumAccountTypeDTO> page = enumAccountTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-account-types/count} : count all the enumAccountTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-account-types/count")
    public ResponseEntity<Long> countEnumAccountTypes(EnumAccountTypeCriteria criteria) {
        log.debug("REST request to count EnumAccountTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumAccountTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-account-types/:id} : get the "id" enumAccountType.
     *
     * @param id the id of the enumAccountTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumAccountTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-account-types/{id}")
    public ResponseEntity<EnumAccountTypeDTO> getEnumAccountType(@PathVariable Long id) {
        log.debug("REST request to get EnumAccountType : {}", id);
        Optional<EnumAccountTypeDTO> enumAccountTypeDTO = enumAccountTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumAccountTypeDTO);
    }

    /**
     * {@code DELETE  /enum-account-types/:id} : delete the "id" enumAccountType.
     *
     * @param id the id of the enumAccountTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-account-types/{id}")
    public ResponseEntity<Void> deleteEnumAccountType(@PathVariable Long id) {
        log.debug("REST request to delete EnumAccountType : {}", id);
        enumAccountTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
