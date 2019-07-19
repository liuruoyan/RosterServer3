import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumEducation from './enum-education';
import EnumEducationDetail from './enum-education-detail';
import EnumEducationUpdate from './enum-education-update';
import EnumEducationDeleteDialog from './enum-education-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumEducationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumEducationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumEducationDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumEducation} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumEducationDeleteDialog} />
  </>
);

export default Routes;
