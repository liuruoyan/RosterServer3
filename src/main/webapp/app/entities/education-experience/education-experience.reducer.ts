import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEducationExperience, defaultValue } from 'app/shared/model/education-experience.model';

export const ACTION_TYPES = {
  FETCH_EDUCATIONEXPERIENCE_LIST: 'educationExperience/FETCH_EDUCATIONEXPERIENCE_LIST',
  FETCH_EDUCATIONEXPERIENCE: 'educationExperience/FETCH_EDUCATIONEXPERIENCE',
  CREATE_EDUCATIONEXPERIENCE: 'educationExperience/CREATE_EDUCATIONEXPERIENCE',
  UPDATE_EDUCATIONEXPERIENCE: 'educationExperience/UPDATE_EDUCATIONEXPERIENCE',
  DELETE_EDUCATIONEXPERIENCE: 'educationExperience/DELETE_EDUCATIONEXPERIENCE',
  RESET: 'educationExperience/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEducationExperience>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EducationExperienceState = Readonly<typeof initialState>;

// Reducer

export default (state: EducationExperienceState = initialState, action): EducationExperienceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EDUCATIONEXPERIENCE):
    case REQUEST(ACTION_TYPES.UPDATE_EDUCATIONEXPERIENCE):
    case REQUEST(ACTION_TYPES.DELETE_EDUCATIONEXPERIENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE):
    case FAILURE(ACTION_TYPES.CREATE_EDUCATIONEXPERIENCE):
    case FAILURE(ACTION_TYPES.UPDATE_EDUCATIONEXPERIENCE):
    case FAILURE(ACTION_TYPES.DELETE_EDUCATIONEXPERIENCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EDUCATIONEXPERIENCE):
    case SUCCESS(ACTION_TYPES.UPDATE_EDUCATIONEXPERIENCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EDUCATIONEXPERIENCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/education-experiences';

// Actions

export const getEntities: ICrudGetAllAction<IEducationExperience> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE_LIST,
    payload: axios.get<IEducationExperience>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEducationExperience> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EDUCATIONEXPERIENCE,
    payload: axios.get<IEducationExperience>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEducationExperience> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EDUCATIONEXPERIENCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEducationExperience> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EDUCATIONEXPERIENCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEducationExperience> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EDUCATIONEXPERIENCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
