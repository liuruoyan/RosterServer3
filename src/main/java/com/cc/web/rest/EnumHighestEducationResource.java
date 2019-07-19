package com.cc.web.rest;

import com.cc.service.EnumHighestEducationService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumHighestEducationDTO;
import com.cc.service.dto.EnumHighestEducationCriteria;
import com.cc.service.EnumHighestEducationQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumHighestEducation}.
 */
@RestController
@RequestMapping("/api")
public class EnumHighestEducationResource {

    private final Logger log = LoggerFactory.getLogger(EnumHighestEducationResource.class);

    private static final String ENTITY_NAME = "enumHighestEducation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumHighestEducationService enumHighestEducationService;

    private final EnumHighestEducationQueryService enumHighestEducationQueryService;

    public EnumHighestEducationResource(EnumHighestEducationService enumHighestEducationService, EnumHighestEducationQueryService enumHighestEducationQueryService) {
        this.enumHighestEducationService = enumHighestEducationService;
        this.enumHighestEducationQueryService = enumHighestEducationQueryService;
    }

    /**
     * {@code POST  /enum-highest-educations} : Create a new enumHighestEducation.
     *
     * @param enumHighestEducationDTO the enumHighestEducationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumHighestEducationDTO, or with status {@code 400 (Bad Request)} if the enumHighestEducation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-highest-educations")
    public ResponseEntity<EnumHighestEducationDTO> createEnumHighestEducation(@RequestBody EnumHighestEducationDTO enumHighestEducationDTO) throws URISyntaxException {
        log.debug("REST request to save EnumHighestEducation : {}", enumHighestEducationDTO);
        if (enumHighestEducationDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumHighestEducation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumHighestEducationDTO result = enumHighestEducationService.save(enumHighestEducationDTO);
        return ResponseEntity.created(new URI("/api/enum-highest-educations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-highest-educations} : Updates an existing enumHighestEducation.
     *
     * @param enumHighestEducationDTO the enumHighestEducationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumHighestEducationDTO,
     * or with status {@code 400 (Bad Request)} if the enumHighestEducationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumHighestEducationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-highest-educations")
    public ResponseEntity<EnumHighestEducationDTO> updateEnumHighestEducation(@RequestBody EnumHighestEducationDTO enumHighestEducationDTO) throws URISyntaxException {
        log.debug("REST request to update EnumHighestEducation : {}", enumHighestEducationDTO);
        if (enumHighestEducationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumHighestEducationDTO result = enumHighestEducationService.save(enumHighestEducationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumHighestEducationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-highest-educations} : get all the enumHighestEducations.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumHighestEducations in body.
     */
    @GetMapping("/enum-highest-educations")
    public ResponseEntity<List<EnumHighestEducationDTO>> getAllEnumHighestEducations(EnumHighestEducationCriteria criteria, Pageable pageable, @RequestParam(required = false) MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumHighestEducations by criteria: {}", criteria);
        Page<EnumHighestEducationDTO> page = enumHighestEducationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-highest-educations/count} : count all the enumHighestEducations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-highest-educations/count")
    public ResponseEntity<Long> countEnumHighestEducations(EnumHighestEducationCriteria criteria) {
        log.debug("REST request to count EnumHighestEducations by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumHighestEducationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-highest-educations/:id} : get the "id" enumHighestEducation.
     *
     * @param id the id of the enumHighestEducationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumHighestEducationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-highest-educations/{id}")
    public ResponseEntity<EnumHighestEducationDTO> getEnumHighestEducation(@PathVariable Long id) {
        log.debug("REST request to get EnumHighestEducation : {}", id);
        Optional<EnumHighestEducationDTO> enumHighestEducationDTO = enumHighestEducationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumHighestEducationDTO);
    }

    /**
     * {@code DELETE  /enum-highest-educations/:id} : delete the "id" enumHighestEducation.
     *
     * @param id the id of the enumHighestEducationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-highest-educations/{id}")
    public ResponseEntity<Void> deleteEnumHighestEducation(@PathVariable Long id) {
        log.debug("REST request to delete EnumHighestEducation : {}", id);
        enumHighestEducationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
