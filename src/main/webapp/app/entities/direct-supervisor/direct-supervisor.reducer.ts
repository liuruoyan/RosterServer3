import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDirectSupervisor, defaultValue } from 'app/shared/model/direct-supervisor.model';

export const ACTION_TYPES = {
  FETCH_DIRECTSUPERVISOR_LIST: 'directSupervisor/FETCH_DIRECTSUPERVISOR_LIST',
  FETCH_DIRECTSUPERVISOR: 'directSupervisor/FETCH_DIRECTSUPERVISOR',
  CREATE_DIRECTSUPERVISOR: 'directSupervisor/CREATE_DIRECTSUPERVISOR',
  UPDATE_DIRECTSUPERVISOR: 'directSupervisor/UPDATE_DIRECTSUPERVISOR',
  DELETE_DIRECTSUPERVISOR: 'directSupervisor/DELETE_DIRECTSUPERVISOR',
  RESET: 'directSupervisor/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDirectSupervisor>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DirectSupervisorState = Readonly<typeof initialState>;

// Reducer

export default (state: DirectSupervisorState = initialState, action): DirectSupervisorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DIRECTSUPERVISOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DIRECTSUPERVISOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DIRECTSUPERVISOR):
    case REQUEST(ACTION_TYPES.UPDATE_DIRECTSUPERVISOR):
    case REQUEST(ACTION_TYPES.DELETE_DIRECTSUPERVISOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DIRECTSUPERVISOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DIRECTSUPERVISOR):
    case FAILURE(ACTION_TYPES.CREATE_DIRECTSUPERVISOR):
    case FAILURE(ACTION_TYPES.UPDATE_DIRECTSUPERVISOR):
    case FAILURE(ACTION_TYPES.DELETE_DIRECTSUPERVISOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIRECTSUPERVISOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIRECTSUPERVISOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DIRECTSUPERVISOR):
    case SUCCESS(ACTION_TYPES.UPDATE_DIRECTSUPERVISOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DIRECTSUPERVISOR):
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

const apiUrl = 'api/direct-supervisors';

// Actions

export const getEntities: ICrudGetAllAction<IDirectSupervisor> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DIRECTSUPERVISOR_LIST,
    payload: axios.get<IDirectSupervisor>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDirectSupervisor> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DIRECTSUPERVISOR,
    payload: axios.get<IDirectSupervisor>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDirectSupervisor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DIRECTSUPERVISOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDirectSupervisor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DIRECTSUPERVISOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDirectSupervisor> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DIRECTSUPERVISOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
