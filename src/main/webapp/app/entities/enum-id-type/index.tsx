import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumIdType from './enum-id-type';
import EnumIdTypeDetail from './enum-id-type-detail';
import EnumIdTypeUpdate from './enum-id-type-update';
import EnumIdTypeDeleteDialog from './enum-id-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumIdTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumIdTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumIdTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumIdType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumIdTypeDeleteDialog} />
  </>
);

export default Routes;
