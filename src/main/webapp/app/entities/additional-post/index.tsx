import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdditionalPost from './additional-post';
import AdditionalPostDetail from './additional-post-detail';
import AdditionalPostUpdate from './additional-post-update';
import AdditionalPostDeleteDialog from './additional-post-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdditionalPostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdditionalPostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdditionalPostDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdditionalPost} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AdditionalPostDeleteDialog} />
  </>
);

export default Routes;
