package com.cc.web.rest;

import com.cc.service.DirectSupervisorService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.DirectSupervisorDTO;
import com.cc.service.dto.DirectSupervisorCriteria;
import com.cc.service.DirectSupervisorQueryService;

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
 * REST controller for managing {@link com.cc.domain.DirectSupervisor}.
 */
@RestController
@RequestMapping("/api")
public class DirectSupervisorResource {

    private final Logger log = LoggerFactory.getLogger(DirectSupervisorResource.class);

    private static final String ENTITY_NAME = "directSupervisor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DirectSupervisorService directSupervisorService;

    private final DirectSupervisorQueryService directSupervisorQueryService;

    public DirectSupervisorResource(DirectSupervisorService directSupervisorService, DirectSupervisorQueryService directSupervisorQueryService) {
        this.directSupervisorService = directSupervisorService;
        this.directSupervisorQueryService = directSupervisorQueryService;
    }

    /**
     * {@code POST  /direct-supervisors} : Create a new directSupervisor.
     *
     * @param directSupervisorDTO the directSupervisorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new directSupervisorDTO, or with status {@code 400 (Bad Request)} if the directSupervisor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/direct-supervisors")
    public ResponseEntity<DirectSupervisorDTO> createDirectSupervisor(@RequestBody DirectSupervisorDTO directSupervisorDTO) throws URISyntaxException {
        log.debug("REST request to save DirectSupervisor : {}", directSupervisorDTO);
        if (directSupervisorDTO.getId() != null) {
            throw new BadRequestAlertException("A new directSupervisor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DirectSupervisorDTO result = directSupervisorService.save(directSupervisorDTO);
        return ResponseEntity.created(new URI("/api/direct-supervisors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /direct-supervisors} : Updates an existing directSupervisor.
     *
     * @param directSupervisorDTO the directSupervisorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated directSupervisorDTO,
     * or with status {@code 400 (Bad Request)} if the directSupervisorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the directSupervisorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/direct-supervisors")
    public ResponseEntity<DirectSupervisorDTO> updateDirectSupervisor(@RequestBody DirectSupervisorDTO directSupervisorDTO) throws URISyntaxException {
        log.debug("REST request to update DirectSupervisor : {}", directSupervisorDTO);
        if (directSupervisorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DirectSupervisorDTO result = directSupervisorService.save(directSupervisorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, directSupervisorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /direct-supervisors} : get all the directSupervisors.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directSupervisors in body.
     */
    @GetMapping("/direct-supervisors")
    public ResponseEntity<List<DirectSupervisorDTO>> getAllDirectSupervisors(DirectSupervisorCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get DirectSupervisors by criteria: {}", criteria);
        Page<DirectSupervisorDTO> page = directSupervisorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /direct-supervisors/count} : count all the directSupervisors.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/direct-supervisors/count")
    public ResponseEntity<Long> countDirectSupervisors(DirectSupervisorCriteria criteria) {
        log.debug("REST request to count DirectSupervisors by criteria: {}", criteria);
        return ResponseEntity.ok().body(directSupervisorQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /direct-supervisors/:id} : get the "id" directSupervisor.
     *
     * @param id the id of the directSupervisorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directSupervisorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direct-supervisors/{id}")
    public ResponseEntity<DirectSupervisorDTO> getDirectSupervisor(@PathVariable Long id) {
        log.debug("REST request to get DirectSupervisor : {}", id);
        Optional<DirectSupervisorDTO> directSupervisorDTO = directSupervisorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(directSupervisorDTO);
    }

    /**
     * {@code DELETE  /direct-supervisors/:id} : delete the "id" directSupervisor.
     *
     * @param id the id of the directSupervisorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/direct-supervisors/{id}")
    public ResponseEntity<Void> deleteDirectSupervisor(@PathVariable Long id) {
        log.debug("REST request to delete DirectSupervisor : {}", id);
        directSupervisorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
