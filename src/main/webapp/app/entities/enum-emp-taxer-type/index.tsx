import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumEmpTaxerType from './enum-emp-taxer-type';
import EnumEmpTaxerTypeDetail from './enum-emp-taxer-type-detail';
import EnumEmpTaxerTypeUpdate from './enum-emp-taxer-type-update';
import EnumEmpTaxerTypeDeleteDialog from './enum-emp-taxer-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumEmpTaxerTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumEmpTaxerTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumEmpTaxerTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumEmpTaxerType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumEmpTaxerTypeDeleteDialog} />
  </>
);

export default Routes;
