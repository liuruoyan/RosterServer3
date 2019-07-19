import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumPfStatus, defaultValue } from 'app/shared/model/enum-pf-status.model';

export const ACTION_TYPES = {
  FETCH_ENUMPFSTATUS_LIST: 'enumPfStatus/FETCH_ENUMPFSTATUS_LIST',
  FETCH_ENUMPFSTATUS: 'enumPfStatus/FETCH_ENUMPFSTATUS',
  CREATE_ENUMPFSTATUS: 'enumPfStatus/CREATE_ENUMPFSTATUS',
  UPDATE_ENUMPFSTATUS: 'enumPfStatus/UPDATE_ENUMPFSTATUS',
  DELETE_ENUMPFSTATUS: 'enumPfStatus/DELETE_ENUMPFSTATUS',
  RESET: 'enumPfStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumPfStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumPfStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumPfStatusState = initialState, action): EnumPfStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMPFSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMPFSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMPFSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMPFSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_ENUMPFSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMPFSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMPFSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_ENUMPFSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMPFSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_ENUMPFSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPFSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPFSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMPFSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMPFSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMPFSTATUS):
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

const apiUrl = 'api/enum-pf-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IEnumPfStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPFSTATUS_LIST,
    payload: axios.get<IEnumPfStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumPfStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPFSTATUS,
    payload: axios.get<IEnumPfStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumPfStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMPFSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumPfStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMPFSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumPfStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMPFSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
