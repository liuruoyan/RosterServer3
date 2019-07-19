import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DirectSupervisor from './direct-supervisor';
import DirectSupervisorDetail from './direct-supervisor-detail';
import DirectSupervisorUpdate from './direct-supervisor-update';
import DirectSupervisorDeleteDialog from './direct-supervisor-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DirectSupervisorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DirectSupervisorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DirectSupervisorDetail} />
      <ErrorBoundaryRoute path={match.url} component={DirectSupervisor} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DirectSupervisorDeleteDialog} />
  </>
);

export default Routes;
