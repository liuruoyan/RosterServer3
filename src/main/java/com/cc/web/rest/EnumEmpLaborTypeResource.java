package com.cc.web.rest;

import com.cc.service.EnumEmpLaborTypeService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumEmpLaborTypeDTO;
import com.cc.service.dto.EnumEmpLaborTypeCriteria;
import com.cc.service.EnumEmpLaborTypeQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumEmpLaborType}.
 */
@RestController
@RequestMapping("/api")
public class EnumEmpLaborTypeResource {

    private final Logger log = LoggerFactory.getLogger(EnumEmpLaborTypeResource.class);

    private static final String ENTITY_NAME = "enumEmpLaborType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumEmpLaborTypeService enumEmpLaborTypeService;

    private final EnumEmpLaborTypeQueryService enumEmpLaborTypeQueryService;

    public EnumEmpLaborTypeResource(EnumEmpLaborTypeService enumEmpLaborTypeService, EnumEmpLaborTypeQueryService enumEmpLaborTypeQueryService) {
        this.enumEmpLaborTypeService = enumEmpLaborTypeService;
        this.enumEmpLaborTypeQueryService = enumEmpLaborTypeQueryService;
    }

    /**
     * {@code POST  /enum-emp-labor-types} : Create a new enumEmpLaborType.
     *
     * @param enumEmpLaborTypeDTO the enumEmpLaborTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumEmpLaborTypeDTO, or with status {@code 400 (Bad Request)} if the enumEmpLaborType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-emp-labor-types")
    public ResponseEntity<EnumEmpLaborTypeDTO> createEnumEmpLaborType(@RequestBody EnumEmpLaborTypeDTO enumEmpLaborTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EnumEmpLaborType : {}", enumEmpLaborTypeDTO);
        if (enumEmpLaborTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumEmpLaborType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumEmpLaborTypeDTO result = enumEmpLaborTypeService.save(enumEmpLaborTypeDTO);
        return ResponseEntity.created(new URI("/api/enum-emp-labor-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-emp-labor-types} : Updates an existing enumEmpLaborType.
     *
     * @param enumEmpLaborTypeDTO the enumEmpLaborTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumEmpLaborTypeDTO,
     * or with status {@code 400 (Bad Request)} if the enumEmpLaborTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumEmpLaborTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-emp-labor-types")
    public ResponseEntity<EnumEmpLaborTypeDTO> updateEnumEmpLaborType(@RequestBody EnumEmpLaborTypeDTO enumEmpLaborTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EnumEmpLaborType : {}", enumEmpLaborTypeDTO);
        if (enumEmpLaborTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumEmpLaborTypeDTO result = enumEmpLaborTypeService.save(enumEmpLaborTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumEmpLaborTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-emp-labor-types} : get all the enumEmpLaborTypes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumEmpLaborTypes in body.
     */
    @GetMapping("/enum-emp-labor-types")
    public ResponseEntity<List<EnumEmpLaborTypeDTO>> getAllEnumEmpLaborTypes(EnumEmpLaborTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumEmpLaborTypes by criteria: {}", criteria);
        Page<EnumEmpLaborTypeDTO> page = enumEmpLaborTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-emp-labor-types/count} : count all the enumEmpLaborTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-emp-labor-types/count")
    public ResponseEntity<Long> countEnumEmpLaborTypes(EnumEmpLaborTypeCriteria criteria) {
        log.debug("REST request to count EnumEmpLaborTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumEmpLaborTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-emp-labor-types/:id} : get the "id" enumEmpLaborType.
     *
     * @param id the id of the enumEmpLaborTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumEmpLaborTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-emp-labor-types/{id}")
    public ResponseEntity<EnumEmpLaborTypeDTO> getEnumEmpLaborType(@PathVariable Long id) {
        log.debug("REST request to get EnumEmpLaborType : {}", id);
        Optional<EnumEmpLaborTypeDTO> enumEmpLaborTypeDTO = enumEmpLaborTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumEmpLaborTypeDTO);
    }

    /**
     * {@code DELETE  /enum-emp-labor-types/:id} : delete the "id" enumEmpLaborType.
     *
     * @param id the id of the enumEmpLaborTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-emp-labor-types/{id}")
    public ResponseEntity<Void> deleteEnumEmpLaborType(@PathVariable Long id) {
        log.debug("REST request to delete EnumEmpLaborType : {}", id);
        enumEmpLaborTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
