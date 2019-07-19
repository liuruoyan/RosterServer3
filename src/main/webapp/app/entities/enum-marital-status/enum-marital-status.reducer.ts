import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumMaritalStatus, defaultValue } from 'app/shared/model/enum-marital-status.model';

export const ACTION_TYPES = {
  FETCH_ENUMMARITALSTATUS_LIST: 'enumMaritalStatus/FETCH_ENUMMARITALSTATUS_LIST',
  FETCH_ENUMMARITALSTATUS: 'enumMaritalStatus/FETCH_ENUMMARITALSTATUS',
  CREATE_ENUMMARITALSTATUS: 'enumMaritalStatus/CREATE_ENUMMARITALSTATUS',
  UPDATE_ENUMMARITALSTATUS: 'enumMaritalStatus/UPDATE_ENUMMARITALSTATUS',
  DELETE_ENUMMARITALSTATUS: 'enumMaritalStatus/DELETE_ENUMMARITALSTATUS',
  RESET: 'enumMaritalStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumMaritalStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumMaritalStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumMaritalStatusState = initialState, action): EnumMaritalStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMMARITALSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMMARITALSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMMARITALSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMMARITALSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_ENUMMARITALSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMMARITALSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMMARITALSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_ENUMMARITALSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMMARITALSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_ENUMMARITALSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMMARITALSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMMARITALSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMMARITALSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMMARITALSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMMARITALSTATUS):
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

const apiUrl = 'api/enum-marital-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IEnumMaritalStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMMARITALSTATUS_LIST,
    payload: axios.get<IEnumMaritalStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumMaritalStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMMARITALSTATUS,
    payload: axios.get<IEnumMaritalStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumMaritalStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMMARITALSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumMaritalStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMMARITALSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumMaritalStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMMARITALSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
