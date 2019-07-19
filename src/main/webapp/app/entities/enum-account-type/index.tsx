import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumAccountType from './enum-account-type';
import EnumAccountTypeDetail from './enum-account-type-detail';
import EnumAccountTypeUpdate from './enum-account-type-update';
import EnumAccountTypeDeleteDialog from './enum-account-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumAccountTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumAccountTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumAccountTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumAccountType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumAccountTypeDeleteDialog} />
  </>
);

export default Routes;
