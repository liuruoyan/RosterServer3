import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumIdType, defaultValue } from 'app/shared/model/enum-id-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMIDTYPE_LIST: 'enumIdType/FETCH_ENUMIDTYPE_LIST',
  FETCH_ENUMIDTYPE: 'enumIdType/FETCH_ENUMIDTYPE',
  CREATE_ENUMIDTYPE: 'enumIdType/CREATE_ENUMIDTYPE',
  UPDATE_ENUMIDTYPE: 'enumIdType/UPDATE_ENUMIDTYPE',
  DELETE_ENUMIDTYPE: 'enumIdType/DELETE_ENUMIDTYPE',
  RESET: 'enumIdType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumIdType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumIdTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumIdTypeState = initialState, action): EnumIdTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMIDTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMIDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMIDTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMIDTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMIDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMIDTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMIDTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMIDTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMIDTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMIDTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMIDTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMIDTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMIDTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMIDTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMIDTYPE):
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

const apiUrl = 'api/enum-id-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumIdType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMIDTYPE_LIST,
    payload: axios.get<IEnumIdType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumIdType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMIDTYPE,
    payload: axios.get<IEnumIdType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumIdType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMIDTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumIdType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMIDTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumIdType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMIDTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
