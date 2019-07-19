import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumSsStatus from './enum-ss-status';
import EnumSsStatusDetail from './enum-ss-status-detail';
import EnumSsStatusUpdate from './enum-ss-status-update';
import EnumSsStatusDeleteDialog from './enum-ss-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumSsStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumSsStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumSsStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumSsStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumSsStatusDeleteDialog} />
  </>
);

export default Routes;
