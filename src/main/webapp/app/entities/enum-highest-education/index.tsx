import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumHighestEducation from './enum-highest-education';
import EnumHighestEducationDetail from './enum-highest-education-detail';
import EnumHighestEducationUpdate from './enum-highest-education-update';
import EnumHighestEducationDeleteDialog from './enum-highest-education-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumHighestEducationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumHighestEducationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumHighestEducationDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumHighestEducation} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumHighestEducationDeleteDialog} />
  </>
);

export default Routes;
