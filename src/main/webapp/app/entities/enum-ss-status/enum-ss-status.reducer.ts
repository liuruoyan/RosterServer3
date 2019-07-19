import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumSsStatus, defaultValue } from 'app/shared/model/enum-ss-status.model';

export const ACTION_TYPES = {
  FETCH_ENUMSSSTATUS_LIST: 'enumSsStatus/FETCH_ENUMSSSTATUS_LIST',
  FETCH_ENUMSSSTATUS: 'enumSsStatus/FETCH_ENUMSSSTATUS',
  CREATE_ENUMSSSTATUS: 'enumSsStatus/CREATE_ENUMSSSTATUS',
  UPDATE_ENUMSSSTATUS: 'enumSsStatus/UPDATE_ENUMSSSTATUS',
  DELETE_ENUMSSSTATUS: 'enumSsStatus/DELETE_ENUMSSSTATUS',
  RESET: 'enumSsStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumSsStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumSsStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumSsStatusState = initialState, action): EnumSsStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMSSSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMSSSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMSSSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMSSSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_ENUMSSSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMSSSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMSSSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_ENUMSSSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMSSSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_ENUMSSSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMSSSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMSSSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMSSSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMSSSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMSSSTATUS):
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

const apiUrl = 'api/enum-ss-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IEnumSsStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMSSSTATUS_LIST,
    payload: axios.get<IEnumSsStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumSsStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMSSSTATUS,
    payload: axios.get<IEnumSsStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumSsStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMSSSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumSsStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMSSSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumSsStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMSSSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
