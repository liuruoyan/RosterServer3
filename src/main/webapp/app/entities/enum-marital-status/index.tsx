import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumMaritalStatus from './enum-marital-status';
import EnumMaritalStatusDetail from './enum-marital-status-detail';
import EnumMaritalStatusUpdate from './enum-marital-status-update';
import EnumMaritalStatusDeleteDialog from './enum-marital-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumMaritalStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumMaritalStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumMaritalStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumMaritalStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumMaritalStatusDeleteDialog} />
  </>
);

export default Routes;
