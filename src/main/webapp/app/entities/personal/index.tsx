import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Personal from './personal';
import PersonalDetail from './personal-detail';
import PersonalUpdate from './personal-update';
import PersonalDeleteDialog from './personal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PersonalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PersonalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PersonalDetail} />
      <ErrorBoundaryRoute path={match.url} component={Personal} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PersonalDeleteDialog} />
  </>
);

export default Routes;
