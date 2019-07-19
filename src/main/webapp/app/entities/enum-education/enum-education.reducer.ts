import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumEducation, defaultValue } from 'app/shared/model/enum-education.model';

export const ACTION_TYPES = {
  FETCH_ENUMEDUCATION_LIST: 'enumEducation/FETCH_ENUMEDUCATION_LIST',
  FETCH_ENUMEDUCATION: 'enumEducation/FETCH_ENUMEDUCATION',
  CREATE_ENUMEDUCATION: 'enumEducation/CREATE_ENUMEDUCATION',
  UPDATE_ENUMEDUCATION: 'enumEducation/UPDATE_ENUMEDUCATION',
  DELETE_ENUMEDUCATION: 'enumEducation/DELETE_ENUMEDUCATION',
  RESET: 'enumEducation/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumEducation>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumEducationState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumEducationState = initialState, action): EnumEducationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMEDUCATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMEDUCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMEDUCATION):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMEDUCATION):
    case REQUEST(ACTION_TYPES.DELETE_ENUMEDUCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMEDUCATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMEDUCATION):
    case FAILURE(ACTION_TYPES.CREATE_ENUMEDUCATION):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMEDUCATION):
    case FAILURE(ACTION_TYPES.DELETE_ENUMEDUCATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEDUCATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEDUCATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMEDUCATION):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMEDUCATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMEDUCATION):
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

const apiUrl = 'api/enum-educations';

// Actions

export const getEntities: ICrudGetAllAction<IEnumEducation> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEDUCATION_LIST,
    payload: axios.get<IEnumEducation>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumEducation> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEDUCATION,
    payload: axios.get<IEnumEducation>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumEducation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMEDUCATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumEducation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMEDUCATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumEducation> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMEDUCATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
