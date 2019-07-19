import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumEmpType, defaultValue } from 'app/shared/model/enum-emp-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMEMPTYPE_LIST: 'enumEmpType/FETCH_ENUMEMPTYPE_LIST',
  FETCH_ENUMEMPTYPE: 'enumEmpType/FETCH_ENUMEMPTYPE',
  CREATE_ENUMEMPTYPE: 'enumEmpType/CREATE_ENUMEMPTYPE',
  UPDATE_ENUMEMPTYPE: 'enumEmpType/UPDATE_ENUMEMPTYPE',
  DELETE_ENUMEMPTYPE: 'enumEmpType/DELETE_ENUMEMPTYPE',
  RESET: 'enumEmpType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumEmpType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumEmpTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumEmpTypeState = initialState, action): EnumEmpTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMEMPTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMEMPTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMEMPTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMEMPTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMEMPTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMEMPTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMEMPTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMEMPTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMEMPTYPE):
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

const apiUrl = 'api/enum-emp-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumEmpType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPTYPE_LIST,
    payload: axios.get<IEnumEmpType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumEmpType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPTYPE,
    payload: axios.get<IEnumEmpType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumEmpType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMEMPTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumEmpType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMEMPTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumEmpType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMEMPTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
