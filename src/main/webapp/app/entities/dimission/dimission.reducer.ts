import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDimission, defaultValue } from 'app/shared/model/dimission.model';

export const ACTION_TYPES = {
  FETCH_DIMISSION_LIST: 'dimission/FETCH_DIMISSION_LIST',
  FETCH_DIMISSION: 'dimission/FETCH_DIMISSION',
  CREATE_DIMISSION: 'dimission/CREATE_DIMISSION',
  UPDATE_DIMISSION: 'dimission/UPDATE_DIMISSION',
  DELETE_DIMISSION: 'dimission/DELETE_DIMISSION',
  RESET: 'dimission/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDimission>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DimissionState = Readonly<typeof initialState>;

// Reducer

export default (state: DimissionState = initialState, action): DimissionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DIMISSION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DIMISSION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DIMISSION):
    case REQUEST(ACTION_TYPES.UPDATE_DIMISSION):
    case REQUEST(ACTION_TYPES.DELETE_DIMISSION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DIMISSION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DIMISSION):
    case FAILURE(ACTION_TYPES.CREATE_DIMISSION):
    case FAILURE(ACTION_TYPES.UPDATE_DIMISSION):
    case FAILURE(ACTION_TYPES.DELETE_DIMISSION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIMISSION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIMISSION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DIMISSION):
    case SUCCESS(ACTION_TYPES.UPDATE_DIMISSION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DIMISSION):
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

const apiUrl = 'api/dimissions';

// Actions

export const getEntities: ICrudGetAllAction<IDimission> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DIMISSION_LIST,
    payload: axios.get<IDimission>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDimission> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DIMISSION,
    payload: axios.get<IDimission>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDimission> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DIMISSION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDimission> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DIMISSION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDimission> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DIMISSION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
