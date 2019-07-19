import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumDimissionType from './enum-dimission-type';
import EnumDimissionTypeDetail from './enum-dimission-type-detail';
import EnumDimissionTypeUpdate from './enum-dimission-type-update';
import EnumDimissionTypeDeleteDialog from './enum-dimission-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumDimissionTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumDimissionTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumDimissionTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumDimissionType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumDimissionTypeDeleteDialog} />
  </>
);

export default Routes;
