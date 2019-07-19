import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumEmpTaxerType, defaultValue } from 'app/shared/model/enum-emp-taxer-type.model';

export const ACTION_TYPES = {
  FETCH_ENUMEMPTAXERTYPE_LIST: 'enumEmpTaxerType/FETCH_ENUMEMPTAXERTYPE_LIST',
  FETCH_ENUMEMPTAXERTYPE: 'enumEmpTaxerType/FETCH_ENUMEMPTAXERTYPE',
  CREATE_ENUMEMPTAXERTYPE: 'enumEmpTaxerType/CREATE_ENUMEMPTAXERTYPE',
  UPDATE_ENUMEMPTAXERTYPE: 'enumEmpTaxerType/UPDATE_ENUMEMPTAXERTYPE',
  DELETE_ENUMEMPTAXERTYPE: 'enumEmpTaxerType/DELETE_ENUMEMPTAXERTYPE',
  RESET: 'enumEmpTaxerType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumEmpTaxerType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumEmpTaxerTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumEmpTaxerTypeState = initialState, action): EnumEmpTaxerTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMEMPTAXERTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMEMPTAXERTYPE):
    case REQUEST(ACTION_TYPES.DELETE_ENUMEMPTAXERTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE):
    case FAILURE(ACTION_TYPES.CREATE_ENUMEMPTAXERTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMEMPTAXERTYPE):
    case FAILURE(ACTION_TYPES.DELETE_ENUMEMPTAXERTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMEMPTAXERTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMEMPTAXERTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMEMPTAXERTYPE):
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

const apiUrl = 'api/enum-emp-taxer-types';

// Actions

export const getEntities: ICrudGetAllAction<IEnumEmpTaxerType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE_LIST,
    payload: axios.get<IEnumEmpTaxerType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumEmpTaxerType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPTAXERTYPE,
    payload: axios.get<IEnumEmpTaxerType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumEmpTaxerType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMEMPTAXERTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumEmpTaxerType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMEMPTAXERTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumEmpTaxerType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMEMPTAXERTYPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
