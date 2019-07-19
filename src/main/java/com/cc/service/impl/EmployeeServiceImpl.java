package com.cc.service.impl;

import com.cc.repository.*;
import com.cc.service.EmployeeService;
import com.cc.domain.Employee;
import com.cc.repository.EmployeeRepository;
import com.cc.service.dto.EmployeeDTO;
import com.cc.service.mapper.EmployeeMapper;
import com.cc.service.util.ValidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PersonalRepository personalRepository;
    @Autowired
    private SocialSecurityBenefitsRepository socialSecurityBenefitsRepository;
    @Autowired
    private PayCardRepository payCardRepository;
    @Autowired
    private DimissionRepository dimissionRepository;
    @Autowired
    private WorkExperienceRepository workExperienceRepository;
    @Autowired
    private EducationExperienceRepository educationExperienceRepository;
    @Autowired
    private DirectSupervisorRepository directSupervisorRepository;
    @Autowired
    private AdditionalPostRepository additionalPostRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.debug("Request to save Employee : {}", employeeDTO);
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDTO save(Employee employee) {
        log.debug("Request to save Employee : {}", employee);
        // 验证
        ValidUtil.deleteEmptyRelation(employee);

        Employee save = employeeRepository.save(employee);
        // 处理一对多关系
        employee.getContracts().forEach(e -> {e.setEmp(save); contractRepository.save(e);});
        employee.getPersonals().forEach(e -> {e.setEmp(save); personalRepository.save(e);});
        employee.getSocialSecurityBenefits().forEach(e -> {e.setEmp(save); socialSecurityBenefitsRepository.save(e);});
        employee.getPayCards().forEach(e -> {e.setEmp(save); payCardRepository.save(e);});
        employee.getDimissions().forEach(e -> {e.setEmp(save); dimissionRepository.save(e);});
        employee.getWorkExperiences().forEach(e -> {e.setEmp(save); workExperienceRepository.save(e);});
        employee.getEducationExperiences().forEach(e -> {e.setEmp(save); educationExperienceRepository.save(e);});
        employee.getDirectSupervisors().forEach(e -> {e.setEmp(save); directSupervisorRepository.save(e);});
        employee.getAdditionalPosts().forEach(e -> {e.setEmp(save); additionalPostRepository.save(e);});

        return employeeMapper.toDto(save);
    }



    /**
     * Get all the employees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll(pageable)
            .map(employeeMapper::toDto);
    }


    /**
     * Get one employee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findById(id)
            .map(employeeMapper::toDto);
    }

    /**
     * Delete the employee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
    }
}
