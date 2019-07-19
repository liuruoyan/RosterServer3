import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumPfPayScheme, defaultValue } from 'app/shared/model/enum-pf-pay-scheme.model';

export const ACTION_TYPES = {
  FETCH_ENUMPFPAYSCHEME_LIST: 'enumPfPayScheme/FETCH_ENUMPFPAYSCHEME_LIST',
  FETCH_ENUMPFPAYSCHEME: 'enumPfPayScheme/FETCH_ENUMPFPAYSCHEME',
  CREATE_ENUMPFPAYSCHEME: 'enumPfPayScheme/CREATE_ENUMPFPAYSCHEME',
  UPDATE_ENUMPFPAYSCHEME: 'enumPfPayScheme/UPDATE_ENUMPFPAYSCHEME',
  DELETE_ENUMPFPAYSCHEME: 'enumPfPayScheme/DELETE_ENUMPFPAYSCHEME',
  RESET: 'enumPfPayScheme/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumPfPayScheme>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumPfPaySchemeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumPfPaySchemeState = initialState, action): EnumPfPaySchemeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMPFPAYSCHEME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMPFPAYSCHEME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMPFPAYSCHEME):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMPFPAYSCHEME):
    case REQUEST(ACTION_TYPES.DELETE_ENUMPFPAYSCHEME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMPFPAYSCHEME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMPFPAYSCHEME):
    case FAILURE(ACTION_TYPES.CREATE_ENUMPFPAYSCHEME):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMPFPAYSCHEME):
    case FAILURE(ACTION_TYPES.DELETE_ENUMPFPAYSCHEME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPFPAYSCHEME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPFPAYSCHEME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMPFPAYSCHEME):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMPFPAYSCHEME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMPFPAYSCHEME):
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

const apiUrl = 'api/enum-pf-pay-schemes';

// Actions

export const getEntities: ICrudGetAllAction<IEnumPfPayScheme> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPFPAYSCHEME_LIST,
    payload: axios.get<IEnumPfPayScheme>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumPfPayScheme> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPFPAYSCHEME,
    payload: axios.get<IEnumPfPayScheme>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumPfPayScheme> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMPFPAYSCHEME,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumPfPayScheme> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMPFPAYSCHEME,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumPfPayScheme> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMPFPAYSCHEME,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
