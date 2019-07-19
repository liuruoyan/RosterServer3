import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SocialSecurityBenefits from './social-security-benefits';
import SocialSecurityBenefitsDetail from './social-security-benefits-detail';
import SocialSecurityBenefitsUpdate from './social-security-benefits-update';
import SocialSecurityBenefitsDeleteDialog from './social-security-benefits-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SocialSecurityBenefitsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SocialSecurityBenefitsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SocialSecurityBenefitsDetail} />
      <ErrorBoundaryRoute path={match.url} component={SocialSecurityBenefits} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SocialSecurityBenefitsDeleteDialog} />
  </>
);

export default Routes;
