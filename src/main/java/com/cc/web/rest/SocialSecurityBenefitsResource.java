package com.cc.web.rest;

import com.cc.service.SocialSecurityBenefitsService;
import com.cc.web.rest.errors.BadRequestAlertException;
import com.cc.service.dto.SocialSecurityBenefitsDTO;
import com.cc.service.dto.SocialSecurityBenefitsCriteria;
import com.cc.service.SocialSecurityBenefitsQueryService;

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
 * REST controller for managing {@link com.cc.domain.SocialSecurityBenefits}.
 */
@RestController
@RequestMapping("/api")
public class SocialSecurityBenefitsResource {

    private final Logger log = LoggerFactory.getLogger(SocialSecurityBenefitsResource.class);

    private static final String ENTITY_NAME = "socialSecurityBenefits";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocialSecurityBenefitsService socialSecurityBenefitsService;

    private final SocialSecurityBenefitsQueryService socialSecurityBenefitsQueryService;

    public SocialSecurityBenefitsResource(SocialSecurityBenefitsService socialSecurityBenefitsService, SocialSecurityBenefitsQueryService socialSecurityBenefitsQueryService) {
        this.socialSecurityBenefitsService = socialSecurityBenefitsService;
        this.socialSecurityBenefitsQueryService = socialSecurityBenefitsQueryService;
    }

    /**
     * {@code POST  /social-security-benefits} : Create a new socialSecurityBenefits.
     *
     * @param socialSecurityBenefitsDTO the socialSecurityBenefitsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new socialSecurityBenefitsDTO, or with status {@code 400 (Bad Request)} if the socialSecurityBenefits has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/social-security-benefits")
    public ResponseEntity<SocialSecurityBenefitsDTO> createSocialSecurityBenefits(@RequestBody SocialSecurityBenefitsDTO socialSecurityBenefitsDTO) throws URISyntaxException {
        log.debug("REST request to save SocialSecurityBenefits : {}", socialSecurityBenefitsDTO);
        if (socialSecurityBenefitsDTO.getId() != null) {
            throw new BadRequestAlertException("A new socialSecurityBenefits cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocialSecurityBenefitsDTO result = socialSecurityBenefitsService.save(socialSecurityBenefitsDTO);
        return ResponseEntity.created(new URI("/api/social-security-benefits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /social-security-benefits} : Updates an existing socialSecurityBenefits.
     *
     * @param socialSecurityBenefitsDTO the socialSecurityBenefitsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated socialSecurityBenefitsDTO,
     * or with status {@code 400 (Bad Request)} if the socialSecurityBenefitsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the socialSecurityBenefitsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/social-security-benefits")
    public ResponseEntity<SocialSecurityBenefitsDTO> updateSocialSecurityBenefits(@RequestBody SocialSecurityBenefitsDTO socialSecurityBenefitsDTO) throws URISyntaxException {
        log.debug("REST request to update SocialSecurityBenefits : {}", socialSecurityBenefitsDTO);
        if (socialSecurityBenefitsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SocialSecurityBenefitsDTO result = socialSecurityBenefitsService.save(socialSecurityBenefitsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, socialSecurityBenefitsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /social-security-benefits} : get all the socialSecurityBenefits.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of socialSecurityBenefits in body.
     */
    @GetMapping("/social-security-benefits")
    public ResponseEntity<List<SocialSecurityBenefitsDTO>> getAllSocialSecurityBenefits(SocialSecurityBenefitsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get SocialSecurityBenefits by criteria: {}", criteria);
        Page<SocialSecurityBenefitsDTO> page = socialSecurityBenefitsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /social-security-benefits/count} : count all the socialSecurityBenefits.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/social-security-benefits/count")
    public ResponseEntity<Long> countSocialSecurityBenefits(SocialSecurityBenefitsCriteria criteria) {
        log.debug("REST request to count SocialSecurityBenefits by criteria: {}", criteria);
        return ResponseEntity.ok().body(socialSecurityBenefitsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /social-security-benefits/:id} : get the "id" socialSecurityBenefits.
     *
     * @param id the id of the socialSecurityBenefitsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the socialSecurityBenefitsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/social-security-benefits/{id}")
    public ResponseEntity<SocialSecurityBenefitsDTO> getSocialSecurityBenefits(@PathVariable Long id) {
        log.debug("REST request to get SocialSecurityBenefits : {}", id);
        Optional<SocialSecurityBenefitsDTO> socialSecurityBenefitsDTO = socialSecurityBenefitsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(socialSecurityBenefitsDTO);
    }

    /**
     * {@code DELETE  /social-security-benefits/:id} : delete the "id" socialSecurityBenefits.
     *
     * @param id the id of the socialSecurityBenefitsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/social-security-benefits/{id}")
    public ResponseEntity<Void> deleteSocialSecurityBenefits(@PathVariable Long id) {
        log.debug("REST request to delete SocialSecurityBenefits : {}", id);
        socialSecurityBenefitsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
