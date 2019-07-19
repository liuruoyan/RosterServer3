import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumEmpStatus, defaultValue } from 'app/shared/model/enum-emp-status.model';

export const ACTION_TYPES = {
  FETCH_ENUMEMPSTATUS_LIST: 'enumEmpStatus/FETCH_ENUMEMPSTATUS_LIST',
  FETCH_ENUMEMPSTATUS: 'enumEmpStatus/FETCH_ENUMEMPSTATUS',
  CREATE_ENUMEMPSTATUS: 'enumEmpStatus/CREATE_ENUMEMPSTATUS',
  UPDATE_ENUMEMPSTATUS: 'enumEmpStatus/UPDATE_ENUMEMPSTATUS',
  DELETE_ENUMEMPSTATUS: 'enumEmpStatus/DELETE_ENUMEMPSTATUS',
  RESET: 'enumEmpStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumEmpStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumEmpStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumEmpStatusState = initialState, action): EnumEmpStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMEMPSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMEMPSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_ENUMEMPSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_ENUMEMPSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMEMPSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_ENUMEMPSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMEMPSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMEMPSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMEMPSTATUS):
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

const apiUrl = 'api/enum-emp-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IEnumEmpStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPSTATUS_LIST,
    payload: axios.get<IEnumEmpStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumEmpStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPSTATUS,
    payload: axios.get<IEnumEmpStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumEmpStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMEMPSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumEmpStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMEMPSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumEmpStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMEMPSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
