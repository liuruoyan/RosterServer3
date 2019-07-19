import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Dimission from './dimission';
import DimissionDetail from './dimission-detail';
import DimissionUpdate from './dimission-update';
import DimissionDeleteDialog from './dimission-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DimissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DimissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DimissionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Dimission} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DimissionDeleteDialog} />
  </>
);

export default Routes;
