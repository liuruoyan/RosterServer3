import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumEmpLaborType, defaultValue } from 'app/shared/model/enum-emp-labor-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMEMPLABORTYPE_LIST: 'enumEmpLaborType/FETCH_ENUMEMPLABORTYPE_LIST',
  FETCH_ENUMEMPLABORTYPE: 'enumEmpLaborType/FETCH_ENUMEMPLABORTYPE',
  CREATE_ENUMEMPLABORTYPE: 'enumEmpLaborType/CREATE_ENUMEMPLABORTYPE',
  UPDATE_ENUMEMPLABORTYPE: 'enumEmpLaborType/UPDATE_ENUMEMPLABORTYPE',
  DELETE_ENUMEMPLABORTYPE: 'enumEmpLaborType/DELETE_ENUMEMPLABORTYPE',
  RESET: 'enumEmpLaborType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumEmpLaborType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumEmpLaborTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumEmpLaborTypeState = initialState, action): EnumEmpLaborTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPLABORTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPLABORTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMEMPLABORTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMEMPLABORTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMEMPLABORTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPLABORTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPLABORTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMEMPLABORTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMEMPLABORTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMEMPLABORTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPLABORTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPLABORTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMEMPLABORTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMEMPLABORTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMEMPLABORTYPE):
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

const apiUrl = 'api/enum-emp-labor-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumEmpLaborType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPLABORTYPE_LIST,
    payload: axios.get<IEnumEmpLaborType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumEmpLaborType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPLABORTYPE,
    payload: axios.get<IEnumEmpLaborType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumEmpLaborType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMEMPLABORTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumEmpLaborType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMEMPLABORTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumEmpLaborType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMEMPLABORTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
