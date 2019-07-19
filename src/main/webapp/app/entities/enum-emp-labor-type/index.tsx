import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumEmpLaborType from './enum-emp-labor-type';
import EnumEmpLaborTypeDetail from './enum-emp-labor-type-detail';
import EnumEmpLaborTypeUpdate from './enum-emp-labor-type-update';
import EnumEmpLaborTypeDeleteDialog from './enum-emp-labor-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumEmpLaborTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumEmpLaborTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumEmpLaborTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumEmpLaborType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumEmpLaborTypeDeleteDialog} />
  </>
);

export default Routes;
