import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumSsPayScheme, defaultValue } from 'app/shared/model/enum-ss-pay-scheme.model';

export const ACTION_TYPES = {
  FETCH_ENUMSSPAYSCHEME_LIST: 'enumSsPayScheme/FETCH_ENUMSSPAYSCHEME_LIST',
  FETCH_ENUMSSPAYSCHEME: 'enumSsPayScheme/FETCH_ENUMSSPAYSCHEME',
  CREATE_ENUMSSPAYSCHEME: 'enumSsPayScheme/CREATE_ENUMSSPAYSCHEME',
  UPDATE_ENUMSSPAYSCHEME: 'enumSsPayScheme/UPDATE_ENUMSSPAYSCHEME',
  DELETE_ENUMSSPAYSCHEME: 'enumSsPayScheme/DELETE_ENUMSSPAYSCHEME',
  RESET: 'enumSsPayScheme/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumSsPayScheme>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumSsPaySchemeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumSsPaySchemeState = initialState, action): EnumSsPaySchemeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMSSPAYSCHEME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMSSPAYSCHEME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMSSPAYSCHEME):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMSSPAYSCHEME):
    case REQUEST(ACTION_TYPES.DELETE_ENUMSSPAYSCHEME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMSSPAYSCHEME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMSSPAYSCHEME):
    case FAILURE(ACTION_TYPES.CREATE_ENUMSSPAYSCHEME):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMSSPAYSCHEME):
    case FAILURE(ACTION_TYPES.DELETE_ENUMSSPAYSCHEME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMSSPAYSCHEME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMSSPAYSCHEME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMSSPAYSCHEME):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMSSPAYSCHEME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMSSPAYSCHEME):
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

const apiUrl = 'api/enum-ss-pay-schemes';

// Actions

export const getEntities: ICrudGetAllAction<IEnumSsPayScheme> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMSSPAYSCHEME_LIST,
    payload: axios.get<IEnumSsPayScheme>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumSsPayScheme> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMSSPAYSCHEME,
    payload: axios.get<IEnumSsPayScheme>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumSsPayScheme> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMSSPAYSCHEME,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumSsPayScheme> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMSSPAYSCHEME,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumSsPayScheme> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMSSPAYSCHEME,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
