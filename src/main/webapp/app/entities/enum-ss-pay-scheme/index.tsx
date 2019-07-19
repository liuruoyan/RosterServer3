import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumSsPayScheme from './enum-ss-pay-scheme';
import EnumSsPaySchemeDetail from './enum-ss-pay-scheme-detail';
import EnumSsPaySchemeUpdate from './enum-ss-pay-scheme-update';
import EnumSsPaySchemeDeleteDialog from './enum-ss-pay-scheme-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumSsPaySchemeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumSsPaySchemeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumSsPaySchemeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumSsPayScheme} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumSsPaySchemeDeleteDialog} />
  </>
);

export default Routes;
