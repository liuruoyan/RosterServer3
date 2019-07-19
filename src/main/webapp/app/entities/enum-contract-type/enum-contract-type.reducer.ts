import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumContractType, defaultValue } from 'app/shared/model/enum-contract-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMCONTRACTTYPE_LIST: 'enumContractType/FETCH_ENUMCONTRACTTYPE_LIST',
  FETCH_ENUMCONTRACTTYPE: 'enumContractType/FETCH_ENUMCONTRACTTYPE',
  CREATE_ENUMCONTRACTTYPE: 'enumContractType/CREATE_ENUMCONTRACTTYPE',
  UPDATE_ENUMCONTRACTTYPE: 'enumContractType/UPDATE_ENUMCONTRACTTYPE',
  DELETE_ENUMCONTRACTTYPE: 'enumContractType/DELETE_ENUMCONTRACTTYPE',
  RESET: 'enumContractType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumContractType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumContractTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumContractTypeState = initialState, action): EnumContractTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMCONTRACTTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMCONTRACTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMCONTRACTTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMCONTRACTTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMCONTRACTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMCONTRACTTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMCONTRACTTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMCONTRACTTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMCONTRACTTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMCONTRACTTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMCONTRACTTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMCONTRACTTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMCONTRACTTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMCONTRACTTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMCONTRACTTYPE):
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

const apiUrl = 'api/enum-contract-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumContractType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMCONTRACTTYPE_LIST,
    payload: axios.get<IEnumContractType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumContractType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMCONTRACTTYPE,
    payload: axios.get<IEnumContractType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumContractType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMCONTRACTTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumContractType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMCONTRACTTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumContractType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMCONTRACTTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
