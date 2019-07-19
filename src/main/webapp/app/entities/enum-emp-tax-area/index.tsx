import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumEmpTaxArea from './enum-emp-tax-area';
import EnumEmpTaxAreaDetail from './enum-emp-tax-area-detail';
import EnumEmpTaxAreaUpdate from './enum-emp-tax-area-update';
import EnumEmpTaxAreaDeleteDialog from './enum-emp-tax-area-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumEmpTaxAreaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumEmpTaxAreaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumEmpTaxAreaDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumEmpTaxArea} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumEmpTaxAreaDeleteDialog} />
  </>
);

export default Routes;
