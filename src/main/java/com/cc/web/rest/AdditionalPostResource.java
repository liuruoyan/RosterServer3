package com.cc.web.rest;

import com.cc.service.AdditionalPostService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.AdditionalPostDTO;
import com.cc.service.dto.AdditionalPostCriteria;
import com.cc.service.AdditionalPostQueryService;

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
 * REST controller for managing {@link com.cc.domain.AdditionalPost}.
 */
@RestController
@RequestMapping("/api")
public class AdditionalPostResource {

    private final Logger log = LoggerFactory.getLogger(AdditionalPostResource.class);

    private static final String ENTITY_NAME = "additionalPost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdditionalPostService additionalPostService;

    private final AdditionalPostQueryService additionalPostQueryService;

    public AdditionalPostResource(AdditionalPostService additionalPostService, AdditionalPostQueryService additionalPostQueryService) {
        this.additionalPostService = additionalPostService;
        this.additionalPostQueryService = additionalPostQueryService;
    }

    /**
     * {@code POST  /additional-posts} : Create a new additionalPost.
     *
     * @param additionalPostDTO the additionalPostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new additionalPostDTO, or with status {@code 400 (Bad Request)} if the additionalPost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/additional-posts")
    public ResponseEntity<AdditionalPostDTO> createAdditionalPost(@RequestBody AdditionalPostDTO additionalPostDTO) throws URISyntaxException {
        log.debug("REST request to save AdditionalPost : {}", additionalPostDTO);
        if (additionalPostDTO.getId() != null) {
            throw new BadRequestAlertException("A new additionalPost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdditionalPostDTO result = additionalPostService.save(additionalPostDTO);
        return ResponseEntity.created(new URI("/api/additional-posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /additional-posts} : Updates an existing additionalPost.
     *
     * @param additionalPostDTO the additionalPostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated additionalPostDTO,
     * or with status {@code 400 (Bad Request)} if the additionalPostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the additionalPostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/additional-posts")
    public ResponseEntity<AdditionalPostDTO> updateAdditionalPost(@RequestBody AdditionalPostDTO additionalPostDTO) throws URISyntaxException {
        log.debug("REST request to update AdditionalPost : {}", additionalPostDTO);
        if (additionalPostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdditionalPostDTO result = additionalPostService.save(additionalPostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, additionalPostDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /additional-posts} : get all the additionalPosts.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of additionalPosts in body.
     */
    @GetMapping("/additional-posts")
    public ResponseEntity<List<AdditionalPostDTO>> getAllAdditionalPosts(AdditionalPostCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get AdditionalPosts by criteria: {}", criteria);
        Page<AdditionalPostDTO> page = additionalPostQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /additional-posts/count} : count all the additionalPosts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/additional-posts/count")
    public ResponseEntity<Long> countAdditionalPosts(AdditionalPostCriteria criteria) {
        log.debug("REST request to count AdditionalPosts by criteria: {}", criteria);
        return ResponseEntity.ok().body(additionalPostQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /additional-posts/:id} : get the "id" additionalPost.
     *
     * @param id the id of the additionalPostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the additionalPostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/additional-posts/{id}")
    public ResponseEntity<AdditionalPostDTO> getAdditionalPost(@PathVariable Long id) {
        log.debug("REST request to get AdditionalPost : {}", id);
        Optional<AdditionalPostDTO> additionalPostDTO = additionalPostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(additionalPostDTO);
    }

    /**
     * {@code DELETE  /additional-posts/:id} : delete the "id" additionalPost.
     *
     * @param id the id of the additionalPostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/additional-posts/{id}")
    public ResponseEntity<Void> deleteAdditionalPost(@PathVariable Long id) {
        log.debug("REST request to delete AdditionalPost : {}", id);
        additionalPostService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
