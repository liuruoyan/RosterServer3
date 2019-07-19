package com.cc.web.rest;

import com.cc.service.EducationExperienceService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.EducationExperienceDTO;
import com.cc.service.dto.EducationExperienceCriteria;
import com.cc.service.EducationExperienceQueryService;

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
 * REST controller for managing {@link com.cc.domain.EducationExperience}.
 */
@RestController
@RequestMapping("/api")
public class EducationExperienceResource {

    private final Logger log = LoggerFactory.getLogger(EducationExperienceResource.class);

    private static final String ENTITY_NAME = "educationExperience";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EducationExperienceService educationExperienceService;

    private final EducationExperienceQueryService educationExperienceQueryService;

    public EducationExperienceResource(EducationExperienceService educationExperienceService, EducationExperienceQueryService educationExperienceQueryService) {
        this.educationExperienceService = educationExperienceService;
        this.educationExperienceQueryService = educationExperienceQueryService;
    }

    /**
     * {@code POST  /education-experiences} : Create a new educationExperience.
     *
     * @param educationExperienceDTO the educationExperienceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new educationExperienceDTO, or with status {@code 400 (Bad Request)} if the educationExperience has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/education-experiences")
    public ResponseEntity<EducationExperienceDTO> createEducationExperience(@RequestBody EducationExperienceDTO educationExperienceDTO) throws URISyntaxException {
        log.debug("REST request to save EducationExperience : {}", educationExperienceDTO);
        if (educationExperienceDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationExperience cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EducationExperienceDTO result = educationExperienceService.save(educationExperienceDTO);
        return ResponseEntity.created(new URI("/api/education-experiences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /education-experiences} : Updates an existing educationExperience.
     *
     * @param educationExperienceDTO the educationExperienceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated educationExperienceDTO,
     * or with status {@code 400 (Bad Request)} if the educationExperienceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the educationExperienceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/education-experiences")
    public ResponseEntity<EducationExperienceDTO> updateEducationExperience(@RequestBody EducationExperienceDTO educationExperienceDTO) throws URISyntaxException {
        log.debug("REST request to update EducationExperience : {}", educationExperienceDTO);
        if (educationExperienceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EducationExperienceDTO result = educationExperienceService.save(educationExperienceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, educationExperienceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /education-experiences} : get all the educationExperiences.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of educationExperiences in body.
     */
    @GetMapping("/education-experiences")
    public ResponseEntity<List<EducationExperienceDTO>> getAllEducationExperiences(EducationExperienceCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EducationExperiences by criteria: {}", criteria);
        Page<EducationExperienceDTO> page = educationExperienceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /education-experiences/count} : count all the educationExperiences.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/education-experiences/count")
    public ResponseEntity<Long> countEducationExperiences(EducationExperienceCriteria criteria) {
        log.debug("REST request to count EducationExperiences by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationExperienceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /education-experiences/:id} : get the "id" educationExperience.
     *
     * @param id the id of the educationExperienceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the educationExperienceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/education-experiences/{id}")
    public ResponseEntity<EducationExperienceDTO> getEducationExperience(@PathVariable Long id) {
        log.debug("REST request to get EducationExperience : {}", id);
        Optional<EducationExperienceDTO> educationExperienceDTO = educationExperienceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationExperienceDTO);
    }

    /**
     * {@code DELETE  /education-experiences/:id} : delete the "id" educationExperience.
     *
     * @param id the id of the educationExperienceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/education-experiences/{id}")
    public ResponseEntity<Void> deleteEducationExperience(@PathVariable Long id) {
        log.debug("REST request to delete EducationExperience : {}", id);
        educationExperienceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
