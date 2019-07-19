import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumPfPayScheme from './enum-pf-pay-scheme';
import EnumPfPaySchemeDetail from './enum-pf-pay-scheme-detail';
import EnumPfPaySchemeUpdate from './enum-pf-pay-scheme-update';
import EnumPfPaySchemeDeleteDialog from './enum-pf-pay-scheme-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumPfPaySchemeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumPfPaySchemeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumPfPaySchemeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumPfPayScheme} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumPfPaySchemeDeleteDialog} />
  </>
);

export default Routes;
