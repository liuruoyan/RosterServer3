import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPersonal, defaultValue } from 'app/shared/model/personal.model';

export const ACTION_TYPES = {
  FETCH_PERSONAL_LIST: 'personal/FETCH_PERSONAL_LIST',
  FETCH_PERSONAL: 'personal/FETCH_PERSONAL',
  CREATE_PERSONAL: 'personal/CREATE_PERSONAL',
  UPDATE_PERSONAL: 'personal/UPDATE_PERSONAL',
  DELETE_PERSONAL: 'personal/DELETE_PERSONAL',
  RESET: 'personal/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPersonal>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PersonalState = Readonly<typeof initialState>;

// Reducer

export default (state: PersonalState = initialState, action): PersonalState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PERSONAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PERSONAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PERSONAL):
    case REQUEST(ACTION_TYPES.UPDATE_PERSONAL):
    case REQUEST(ACTION_TYPES.DELETE_PERSONAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PERSONAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PERSONAL):
    case FAILURE(ACTION_TYPES.CREATE_PERSONAL):
    case FAILURE(ACTION_TYPES.UPDATE_PERSONAL):
    case FAILURE(ACTION_TYPES.DELETE_PERSONAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERSONAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERSONAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PERSONAL):
    case SUCCESS(ACTION_TYPES.UPDATE_PERSONAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PERSONAL):
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

const apiUrl = 'api/personals';

// Actions

export const getEntities: ICrudGetAllAction<IPersonal> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PERSONAL_LIST,
    payload: axios.get<IPersonal>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPersonal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PERSONAL,
    payload: axios.get<IPersonal>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPersonal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PERSONAL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPersonal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PERSONAL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPersonal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PERSONAL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
