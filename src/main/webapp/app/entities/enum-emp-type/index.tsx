import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumEmpType from './enum-emp-type';
import EnumEmpTypeDetail from './enum-emp-type-detail';
import EnumEmpTypeUpdate from './enum-emp-type-update';
import EnumEmpTypeDeleteDialog from './enum-emp-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumEmpTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumEmpTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumEmpTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumEmpType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumEmpTypeDeleteDialog} />
  </>
);

export default Routes;
