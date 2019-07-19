import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumPoliticsStatus from './enum-politics-status';
import EnumPoliticsStatusDetail from './enum-politics-status-detail';
import EnumPoliticsStatusUpdate from './enum-politics-status-update';
import EnumPoliticsStatusDeleteDialog from './enum-politics-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumPoliticsStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumPoliticsStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumPoliticsStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumPoliticsStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumPoliticsStatusDeleteDialog} />
  </>
);

export default Routes;
