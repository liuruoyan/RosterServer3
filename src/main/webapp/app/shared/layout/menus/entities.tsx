import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/employee">
      <Translate contentKey="global.menu.entities.employee" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-emp-status">
      <Translate contentKey="global.menu.entities.enumEmpStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-id-type">
      <Translate contentKey="global.menu.entities.enumIdType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-contract-type">
      <Translate contentKey="global.menu.entities.enumContractType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-emp-type">
      <Translate contentKey="global.menu.entities.enumEmpType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-gender">
      <Translate contentKey="global.menu.entities.enumGender" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/contract">
      <Translate contentKey="global.menu.entities.contract" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/personal">
      <Translate contentKey="global.menu.entities.personal" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-account-type">
      <Translate contentKey="global.menu.entities.enumAccountType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-highest-education">
      <Translate contentKey="global.menu.entities.enumHighestEducation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-politics-status">
      <Translate contentKey="global.menu.entities.enumPoliticsStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-marital-status">
      <Translate contentKey="global.menu.entities.enumMaritalStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/social-security-benefits">
      <Translate contentKey="global.menu.entities.socialSecurityBenefits" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-pf-type">
      <Translate contentKey="global.menu.entities.enumPfType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-pf-status">
      <Translate contentKey="global.menu.entities.enumPfStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-pf-pay-scheme">
      <Translate contentKey="global.menu.entities.enumPfPayScheme" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-ss-pay-scheme">
      <Translate contentKey="global.menu.entities.enumSsPayScheme" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-ss-status">
      <Translate contentKey="global.menu.entities.enumSsStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-emp-labor-type">
      <Translate contentKey="global.menu.entities.enumEmpLaborType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-emp-taxer-type">
      <Translate contentKey="global.menu.entities.enumEmpTaxerType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-emp-tax-area">
      <Translate contentKey="global.menu.entities.enumEmpTaxArea" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pay-card">
      <Translate contentKey="global.menu.entities.payCard" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-deposit-bank">
      <Translate contentKey="global.menu.entities.enumDepositBank" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/dimission">
      <Translate contentKey="global.menu.entities.dimission" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-dimission-type">
      <Translate contentKey="global.menu.entities.enumDimissionType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/work-experience">
      <Translate contentKey="global.menu.entities.workExperience" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/education-experience">
      <Translate contentKey="global.menu.entities.educationExperience" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/enum-education">
      <Translate contentKey="global.menu.entities.enumEducation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/direct-supervisor">
      <Translate contentKey="global.menu.entities.directSupervisor" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/additional-post">
      <Translate contentKey="global.menu.entities.additionalPost" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
