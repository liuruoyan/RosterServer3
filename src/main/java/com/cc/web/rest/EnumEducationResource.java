package com.cc.web.rest;

import com.cc.service.EnumEducationService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EnumEducationDTO;
import com.cc.service.dto.EnumEducationCriteria;
import com.cc.service.EnumEducationQueryService;

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
 * REST controller for managing {@link com.cc.domain.EnumEducation}.
 */
@RestController
@RequestMapping("/api")
public class EnumEducationResource {

    private final Logger log = LoggerFactory.getLogger(EnumEducationResource.class);

    private static final String ENTITY_NAME = "enumEducation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnumEducationService enumEducationService;

    private final EnumEducationQueryService enumEducationQueryService;

    public EnumEducationResource(EnumEducationService enumEducationService, EnumEducationQueryService enumEducationQueryService) {
        this.enumEducationService = enumEducationService;
        this.enumEducationQueryService = enumEducationQueryService;
    }

    /**
     * {@code POST  /enum-educations} : Create a new enumEducation.
     *
     * @param enumEducationDTO the enumEducationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enumEducationDTO, or with status {@code 400 (Bad Request)} if the enumEducation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enum-educations")
    public ResponseEntity<EnumEducationDTO> createEnumEducation(@RequestBody EnumEducationDTO enumEducationDTO) throws URISyntaxException {
        log.debug("REST request to save EnumEducation : {}", enumEducationDTO);
        if (enumEducationDTO.getId() != null) {
            throw new BadRequestAlertException("A new enumEducation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnumEducationDTO result = enumEducationService.save(enumEducationDTO);
        return ResponseEntity.created(new URI("/api/enum-educations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enum-educations} : Updates an existing enumEducation.
     *
     * @param enumEducationDTO the enumEducationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enumEducationDTO,
     * or with status {@code 400 (Bad Request)} if the enumEducationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enumEducationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enum-educations")
    public ResponseEntity<EnumEducationDTO> updateEnumEducation(@RequestBody EnumEducationDTO enumEducationDTO) throws URISyntaxException {
        log.debug("REST request to update EnumEducation : {}", enumEducationDTO);
        if (enumEducationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnumEducationDTO result = enumEducationService.save(enumEducationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enumEducationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enum-educations} : get all the enumEducations.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enumEducations in body.
     */
    @GetMapping("/enum-educations")
    public ResponseEntity<List<EnumEducationDTO>> getAllEnumEducations(EnumEducationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EnumEducations by criteria: {}", criteria);
        Page<EnumEducationDTO> page = enumEducationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /enum-educations/count} : count all the enumEducations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/enum-educations/count")
    public ResponseEntity<Long> countEnumEducations(EnumEducationCriteria criteria) {
        log.debug("REST request to count EnumEducations by criteria: {}", criteria);
        return ResponseEntity.ok().body(enumEducationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /enum-educations/:id} : get the "id" enumEducation.
     *
     * @param id the id of the enumEducationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enumEducationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enum-educations/{id}")
    public ResponseEntity<EnumEducationDTO> getEnumEducation(@PathVariable Long id) {
        log.debug("REST request to get EnumEducation : {}", id);
        Optional<EnumEducationDTO> enumEducationDTO = enumEducationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enumEducationDTO);
    }

    /**
     * {@code DELETE  /enum-educations/:id} : delete the "id" enumEducation.
     *
     * @param id the id of the enumEducationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enum-educations/{id}")
    public ResponseEntity<Void> deleteEnumEducation(@PathVariable Long id) {
        log.debug("REST request to delete EnumEducation : {}", id);
        enumEducationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
