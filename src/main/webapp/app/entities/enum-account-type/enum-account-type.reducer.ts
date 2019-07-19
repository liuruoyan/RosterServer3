import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumAccountType, defaultValue } from 'app/shared/model/enum-account-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMACCOUNTTYPE_LIST: 'enumAccountType/FETCH_ENUMACCOUNTTYPE_LIST',
  FETCH_ENUMACCOUNTTYPE: 'enumAccountType/FETCH_ENUMACCOUNTTYPE',
  CREATE_ENUMACCOUNTTYPE: 'enumAccountType/CREATE_ENUMACCOUNTTYPE',
  UPDATE_ENUMACCOUNTTYPE: 'enumAccountType/UPDATE_ENUMACCOUNTTYPE',
  DELETE_ENUMACCOUNTTYPE: 'enumAccountType/DELETE_ENUMACCOUNTTYPE',
  RESET: 'enumAccountType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumAccountType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumAccountTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumAccountTypeState = initialState, action): EnumAccountTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMACCOUNTTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMACCOUNTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMACCOUNTTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMACCOUNTTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMACCOUNTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMACCOUNTTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMACCOUNTTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMACCOUNTTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMACCOUNTTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMACCOUNTTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMACCOUNTTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMACCOUNTTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMACCOUNTTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMACCOUNTTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMACCOUNTTYPE):
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

const apiUrl = 'api/enum-account-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumAccountType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMACCOUNTTYPE_LIST,
    payload: axios.get<IEnumAccountType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumAccountType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMACCOUNTTYPE,
    payload: axios.get<IEnumAccountType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumAccountType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMACCOUNTTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumAccountType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMACCOUNTTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumAccountType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMACCOUNTTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
