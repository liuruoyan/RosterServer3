package com.cc.web.rest;

import com.cc.service.EnumGenderService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumGenderDTO;
import com.cc.service.dto.EnumGenderCriteria;
import com.cc.service.EnumGenderQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumGender}.
 */
@RestController
@RequestMapping("/api")
public class EnumGenderResource {

    private final Logger log = LoggerFactory.getLogger(EnumGenderResource.class);

    private static final String ENTITY_NAME = "enumGender";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumGenderService enumGenderService;

    private final EnumGenderQueryService enumGenderQueryService;

    public EnumGenderResource(EnumGenderService enumGenderService, EnumGenderQueryService enumGenderQueryService) {
        this.enumGenderService = enumGenderService;
        this.enumGenderQueryService = enumGenderQueryService;
    }

    /**
     * {@code POST  /enum-genders} : Create a new enumGender.
     *
     * @param enumGenderDTO the enumGenderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumGenderDTO, or with status {@code 400 (Bad Request)} if the enumGender has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-genders")
    public ResponseEntity<EnumGenderDTO> createEnumGender(@RequestBody EnumGenderDTO enumGenderDTO) throws URISyntaxException {
        log.debug("REST request to save EnumGender : {}", enumGenderDTO);
        if (enumGenderDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumGender cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumGenderDTO result = enumGenderService.save(enumGenderDTO);
        return ResponseEntity.created(new URI("/api/enum-genders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-genders} : Updates an existing enumGender.
     *
     * @param enumGenderDTO the enumGenderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumGenderDTO,
     * or with status {@code 400 (Bad Request)} if the enumGenderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumGenderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-genders")
    public ResponseEntity<EnumGenderDTO> updateEnumGender(@RequestBody EnumGenderDTO enumGenderDTO) throws URISyntaxException {
        log.debug("REST request to update EnumGender : {}", enumGenderDTO);
        if (enumGenderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumGenderDTO result = enumGenderService.save(enumGenderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumGenderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-genders} : get all the enumGenders.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumGenders in body.
     */
    @GetMapping("/enum-genders")
    public ResponseEntity<List<EnumGenderDTO>> getAllEnumGenders(EnumGenderCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumGenders by criteria: {}", criteria);
        Page<EnumGenderDTO> page = enumGenderQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-genders/count} : count all the enumGenders.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-genders/count")
    public ResponseEntity<Long> countEnumGenders(EnumGenderCriteria criteria) {
        log.debug("REST request to count EnumGenders by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumGenderQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-genders/:id} : get the "id" enumGender.
     *
     * @param id the id of the enumGenderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumGenderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-genders/{id}")
    public ResponseEntity<EnumGenderDTO> getEnumGender(@PathVariable Long id) {
        log.debug("REST request to get EnumGender : {}", id);
        Optional<EnumGenderDTO> enumGenderDTO = enumGenderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumGenderDTO);
    }

    /**
     * {@code DELETE  /enum-genders/:id} : delete the "id" enumGender.
     *
     * @param id the id of the enumGenderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-genders/{id}")
    public ResponseEntity<Void> deleteEnumGender(@PathVariable Long id) {
        log.debug("REST request to delete EnumGender : {}", id);
        enumGenderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
