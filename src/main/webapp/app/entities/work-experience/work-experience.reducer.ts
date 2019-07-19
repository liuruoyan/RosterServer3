import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IWorkExperience, defaultValue } from 'app/shared/model/work-experience.model';

export const ACTION_TYPES = {
  FETCH_WORKEXPERIENCE_LIST: 'workExperience/FETCH_WORKEXPERIENCE_LIST',
  FETCH_WORKEXPERIENCE: 'workExperience/FETCH_WORKEXPERIENCE',
  CREATE_WORKEXPERIENCE: 'workExperience/CREATE_WORKEXPERIENCE',
  UPDATE_WORKEXPERIENCE: 'workExperience/UPDATE_WORKEXPERIENCE',
  DELETE_WORKEXPERIENCE: 'workExperience/DELETE_WORKEXPERIENCE',
  RESET: 'workExperience/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IWorkExperience>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type WorkExperienceState = Readonly<typeof initialState>;

// Reducer

export default (state: WorkExperienceState = initialState, action): WorkExperienceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_WORKEXPERIENCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_WORKEXPERIENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_WORKEXPERIENCE):
    case REQUEST(ACTION_TYPES.UPDATE_WORKEXPERIENCE):
    case REQUEST(ACTION_TYPES.DELETE_WORKEXPERIENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_WORKEXPERIENCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_WORKEXPERIENCE):
    case FAILURE(ACTION_TYPES.CREATE_WORKEXPERIENCE):
    case FAILURE(ACTION_TYPES.UPDATE_WORKEXPERIENCE):
    case FAILURE(ACTION_TYPES.DELETE_WORKEXPERIENCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_WORKEXPERIENCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_WORKEXPERIENCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_WORKEXPERIENCE):
    case SUCCESS(ACTION_TYPES.UPDATE_WORKEXPERIENCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_WORKEXPERIENCE):
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

const apiUrl = 'api/work-experiences';

// Actions

export const getEntities: ICrudGetAllAction<IWorkExperience> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_WORKEXPERIENCE_LIST,
    payload: axios.get<IWorkExperience>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IWorkExperience> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_WORKEXPERIENCE,
    payload: axios.get<IWorkExperience>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IWorkExperience> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_WORKEXPERIENCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IWorkExperience> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_WORKEXPERIENCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IWorkExperience> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_WORKEXPERIENCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
