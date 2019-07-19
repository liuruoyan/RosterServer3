import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EducationExperience from './education-experience';
import EducationExperienceDetail from './education-experience-detail';
import EducationExperienceUpdate from './education-experience-update';
import EducationExperienceDeleteDialog from './education-experience-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EducationExperienceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EducationExperienceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EducationExperienceDetail} />
      <ErrorBoundaryRoute path={match.url} component={EducationExperience} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EducationExperienceDeleteDialog} />
  </>
);

export default Routes;
