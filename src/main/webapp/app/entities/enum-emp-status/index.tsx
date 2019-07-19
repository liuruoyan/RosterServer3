import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumEmpStatus from './enum-emp-status';
import EnumEmpStatusDetail from './enum-emp-status-detail';
import EnumEmpStatusUpdate from './enum-emp-status-update';
import EnumEmpStatusDeleteDialog from './enum-emp-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumEmpStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumEmpStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumEmpStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumEmpStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumEmpStatusDeleteDialog} />
  </>
);

export default Routes;
