import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EnumDepositBank from './enum-deposit-bank';
import EnumDepositBankDetail from './enum-deposit-bank-detail';
import EnumDepositBankUpdate from './enum-deposit-bank-update';
import EnumDepositBankDeleteDialog from './enum-deposit-bank-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EnumDepositBankUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EnumDepositBankUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EnumDepositBankDetail} />
      <ErrorBoundaryRoute path={match.url} component={EnumDepositBank} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EnumDepositBankDeleteDialog} />
  </>
);

export default Routes;
