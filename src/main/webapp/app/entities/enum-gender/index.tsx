import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumGender from './enum-gender';
import EnumGenderDetail from './enum-gender-detail';
import EnumGenderUpdate from './enum-gender-update';
import EnumGenderDeleteDialog from './enum-gender-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumGenderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumGenderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumGenderDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumGender} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumGenderDeleteDialog} />
  </>
);

export default Routes;
