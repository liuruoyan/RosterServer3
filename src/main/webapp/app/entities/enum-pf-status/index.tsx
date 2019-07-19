import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumPfStatus from './enum-pf-status';
import EnumPfStatusDetail from './enum-pf-status-detail';
import EnumPfStatusUpdate from './enum-pf-status-update';
import EnumPfStatusDeleteDialog from './enum-pf-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumPfStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumPfStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumPfStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumPfStatus} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumPfStatusDeleteDialog} />
  </>
);

export default Routes;
