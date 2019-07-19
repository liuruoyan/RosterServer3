import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PayCard from './pay-card';
import PayCardDetail from './pay-card-detail';
import PayCardUpdate from './pay-card-update';
import PayCardDeleteDialog from './pay-card-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PayCardUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PayCardUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PayCardDetail} />
      <ErrorBoundaryRoute path={match.url} component={PayCard} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PayCardDeleteDialog} />
  </>
);

export default Routes;
