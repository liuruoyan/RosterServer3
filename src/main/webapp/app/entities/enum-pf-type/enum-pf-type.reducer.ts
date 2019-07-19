import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumPfType, defaultValue } from 'app/shared/model/enum-pf-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMPFTYPE_LIST: 'enumPfType/FETCH_ENUMPFTYPE_LIST',
  FETCH_ENUMPFTYPE: 'enumPfType/FETCH_ENUMPFTYPE',
  CREATE_ENUMPFTYPE: 'enumPfType/CREATE_ENUMPFTYPE',
  UPDATE_ENUMPFTYPE: 'enumPfType/UPDATE_ENUMPFTYPE',
  DELETE_ENUMPFTYPE: 'enumPfType/DELETE_ENUMPFTYPE',
  RESET: 'enumPfType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumPfType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumPfTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumPfTypeState = initialState, action): EnumPfTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMPFTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMPFTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMPFTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMPFTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMPFTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMPFTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMPFTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMPFTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMPFTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMPFTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPFTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPFTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMPFTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMPFTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMPFTYPE):
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

const apiUrl = 'api/enum-pf-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumPfType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPFTYPE_LIST,
    payload: axios.get<IEnumPfType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumPfType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPFTYPE,
    payload: axios.get<IEnumPfType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumPfType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMPFTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumPfType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMPFTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumPfType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMPFTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
