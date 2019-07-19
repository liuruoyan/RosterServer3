package com.cc.web.rest;

import com.cc.service.PersonalService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.PersonalDTO;
import com.cc.service.dto.PersonalCriteria;
import com.cc.service.PersonalQueryService;

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
 * REST controller for managing {@link com.cc.domain.Personal}.
 */
@RestController
@RequestMapping("/api")
public class PersonalResource {

    private final Logger log = LoggerFactory.getLogger(PersonalResource.class);

    private static final String ENTITY_NAME = "personal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonalService personalService;

    private final PersonalQueryService personalQueryService;

    public PersonalResource(PersonalService personalService, PersonalQueryService personalQueryService) {
        this.personalService = personalService;
        this.personalQueryService = personalQueryService;
    }

    /**
     * {@code POST  /personals} : Create a new personal.
     *
     * @param personalDTO the personalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personalDTO, or with status {@code 400 (Bad Request)} if the personal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personals")
    public ResponseEntity<PersonalDTO> createPersonal(@RequestBody PersonalDTO personalDTO) throws URISyntaxException {
        log.debug("REST request to save Personal : {}", personalDTO);
        if (personalDTO.getId() != null) {
            throw new BadRequestAlertException("A new personal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonalDTO result = personalService.save(personalDTO);
        return ResponseEntity.created(new URI("/api/personals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personals} : Updates an existing personal.
     *
     * @param personalDTO the personalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personalDTO,
     * or with status {@code 400 (Bad Request)} if the personalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personals")
    public ResponseEntity<PersonalDTO> updatePersonal(@RequestBody PersonalDTO personalDTO) throws URISyntaxException {
        log.debug("REST request to update Personal : {}", personalDTO);
        if (personalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonalDTO result = personalService.save(personalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /personals} : get all the personals.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personals in body.
     */
    @GetMapping("/personals")
    public ResponseEntity<List<PersonalDTO>> getAllPersonals(PersonalCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Personals by criteria: {}", criteria);
        Page<PersonalDTO> page = personalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /personals/count} : count all the personals.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/personals/count")
    public ResponseEntity<Long> countPersonals(PersonalCriteria criteria) {
        log.debug("REST request to count Personals by criteria: {}", criteria);
        return ResponseEntity.ok().body(personalQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /personals/:id} : get the "id" personal.
     *
     * @param id the id of the personalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personals/{id}")
    public ResponseEntity<PersonalDTO> getPersonal(@PathVariable Long id) {
        log.debug("REST request to get Personal : {}", id);
        Optional<PersonalDTO> personalDTO = personalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personalDTO);
    }

    /**
     * {@code DELETE  /personals/:id} : delete the "id" personal.
     *
     * @param id the id of the personalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personals/{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable Long id) {
        log.debug("REST request to delete Personal : {}", id);
        personalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
