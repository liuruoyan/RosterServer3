import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumPfType from './enum-pf-type';
import EnumPfTypeDetail from './enum-pf-type-detail';
import EnumPfTypeUpdate from './enum-pf-type-update';
import EnumPfTypeDeleteDialog from './enum-pf-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumPfTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumPfTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumPfTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumPfType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumPfTypeDeleteDialog} />
  </>
);

export default Routes;
