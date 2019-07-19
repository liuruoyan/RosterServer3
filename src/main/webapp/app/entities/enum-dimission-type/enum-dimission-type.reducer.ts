import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumDimissionType, defaultValue } from 'app/shared/model/enum-dimission-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMDIMISSIONTYPE_LIST: 'enumDimissionType/FETCH_ENUMDIMISSIONTYPE_LIST',
  FETCH_ENUMDIMISSIONTYPE: 'enumDimissionType/FETCH_ENUMDIMISSIONTYPE',
  CREATE_ENUMDIMISSIONTYPE: 'enumDimissionType/CREATE_ENUMDIMISSIONTYPE',
  UPDATE_ENUMDIMISSIONTYPE: 'enumDimissionType/UPDATE_ENUMDIMISSIONTYPE',
  DELETE_ENUMDIMISSIONTYPE: 'enumDimissionType/DELETE_ENUMDIMISSIONTYPE',
  RESET: 'enumDimissionType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumDimissionType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumDimissionTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumDimissionTypeState = initialState, action): EnumDimissionTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMDIMISSIONTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMDIMISSIONTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMDIMISSIONTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMDIMISSIONTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMDIMISSIONTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMDIMISSIONTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMDIMISSIONTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMDIMISSIONTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMDIMISSIONTYPE):
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

const apiUrl = 'api/enum-dimission-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumDimissionType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE_LIST,
    payload: axios.get<IEnumDimissionType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumDimissionType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMDIMISSIONTYPE,
    payload: axios.get<IEnumDimissionType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumDimissionType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMDIMISSIONTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumDimissionType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMDIMISSIONTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumDimissionType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMDIMISSIONTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
