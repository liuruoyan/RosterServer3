package com.cc.web.rest;

import com.cc.service.WorkExperienceService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.WorkExperienceDTO;
import com.cc.service.dto.WorkExperienceCriteria;
import com.cc.service.WorkExperienceQueryService;

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
 * REST controller for managing {@link com.cc.domain.WorkExperience}.
 */
@RestController
@RequestMapping("/api")
public class WorkExperienceResource {

    private final Logger log = LoggerFactory.getLogger(WorkExperienceResource.class);

    private static final String ENTITY_NAME = "workExperience";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkExperienceService workExperienceService;

    private final WorkExperienceQueryService workExperienceQueryService;

    public WorkExperienceResource(WorkExperienceService workExperienceService, WorkExperienceQueryService workExperienceQueryService) {
        this.workExperienceService = workExperienceService;
        this.workExperienceQueryService = workExperienceQueryService;
    }

    /**
     * {@code POST  /work-experiences} : Create a new workExperience.
     *
     * @param workExperienceDTO the workExperienceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workExperienceDTO, or with status {@code 400 (Bad Request)} if the workExperience has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-experiences")
    public ResponseEntity<WorkExperienceDTO> createWorkExperience(@RequestBody WorkExperienceDTO workExperienceDTO) throws URISyntaxException {
        log.debug("REST request to save WorkExperience : {}", workExperienceDTO);
        if (workExperienceDTO.getId() != null) {
            throw new BadRequestAlertException("A new workExperience cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkExperienceDTO result = workExperienceService.save(workExperienceDTO);
        return ResponseEntity.created(new URI("/api/work-experiences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-experiences} : Updates an existing workExperience.
     *
     * @param workExperienceDTO the workExperienceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workExperienceDTO,
     * or with status {@code 400 (Bad Request)} if the workExperienceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workExperienceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-experiences")
    public ResponseEntity<WorkExperienceDTO> updateWorkExperience(@RequestBody WorkExperienceDTO workExperienceDTO) throws URISyntaxException {
        log.debug("REST request to update WorkExperience : {}", workExperienceDTO);
        if (workExperienceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkExperienceDTO result = workExperienceService.save(workExperienceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workExperienceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /work-experiences} : get all the workExperiences.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workExperiences in body.
     */
    @GetMapping("/work-experiences")
    public ResponseEntity<List<WorkExperienceDTO>> getAllWorkExperiences(WorkExperienceCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get WorkExperiences by criteria: {}", criteria);
        Page<WorkExperienceDTO> page = workExperienceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /work-experiences/count} : count all the workExperiences.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/work-experiences/count")
    public ResponseEntity<Long> countWorkExperiences(WorkExperienceCriteria criteria) {
        log.debug("REST request to count WorkExperiences by criteria: {}", criteria);
        return ResponseEntity.ok().body(workExperienceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /work-experiences/:id} : get the "id" workExperience.
     *
     * @param id the id of the workExperienceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workExperienceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-experiences/{id}")
    public ResponseEntity<WorkExperienceDTO> getWorkExperience(@PathVariable Long id) {
        log.debug("REST request to get WorkExperience : {}", id);
        Optional<WorkExperienceDTO> workExperienceDTO = workExperienceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workExperienceDTO);
    }

    /**
     * {@code DELETE  /work-experiences/:id} : delete the "id" workExperience.
     *
     * @param id the id of the workExperienceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-experiences/{id}")
    public ResponseEntity<Void> deleteWorkExperience(@PathVariable Long id) {
        log.debug("REST request to delete WorkExperience : {}", id);
        workExperienceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
