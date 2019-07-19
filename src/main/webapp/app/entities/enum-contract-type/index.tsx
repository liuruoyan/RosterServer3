import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumContractType from './enum-contract-type';
import EnumContractTypeDetail from './enum-contract-type-detail';
import EnumContractTypeUpdate from './enum-contract-type-update';
import EnumContractTypeDeleteDialog from './enum-contract-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumContractTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumContractTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumContractTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumContractType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumContractTypeDeleteDialog} />
  </>
);

export default Routes;
