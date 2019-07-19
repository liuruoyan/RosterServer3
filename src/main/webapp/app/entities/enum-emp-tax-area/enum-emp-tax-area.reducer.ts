import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumEmpTaxArea, defaultValue } from 'app/shared/model/enum-emp-tax-area.model';

export const ACTION_TYPES = {
  FETCH_ENUMEMPTAXAREA_LIST: 'enumEmpTaxArea/FETCH_ENUMEMPTAXAREA_LIST',
  FETCH_ENUMEMPTAXAREA: 'enumEmpTaxArea/FETCH_ENUMEMPTAXAREA',
  CREATE_ENUMEMPTAXAREA: 'enumEmpTaxArea/CREATE_ENUMEMPTAXAREA',
  UPDATE_ENUMEMPTAXAREA: 'enumEmpTaxArea/UPDATE_ENUMEMPTAXAREA',
  DELETE_ENUMEMPTAXAREA: 'enumEmpTaxArea/DELETE_ENUMEMPTAXAREA',
  RESET: 'enumEmpTaxArea/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumEmpTaxArea>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumEmpTaxAreaState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumEmpTaxAreaState = initialState, action): EnumEmpTaxAreaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPTAXAREA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMEMPTAXAREA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMEMPTAXAREA):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMEMPTAXAREA):
    case REQUEST(ACTION_TYPES.DELETE_ENUMEMPTAXAREA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPTAXAREA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMEMPTAXAREA):
    case FAILURE(ACTION_TYPES.CREATE_ENUMEMPTAXAREA):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMEMPTAXAREA):
    case FAILURE(ACTION_TYPES.DELETE_ENUMEMPTAXAREA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPTAXAREA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMEMPTAXAREA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMEMPTAXAREA):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMEMPTAXAREA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMEMPTAXAREA):
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

const apiUrl = 'api/enum-emp-tax-areas';

// Actions

export const getEntities: ICrudGetAllAction<IEnumEmpTaxArea> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPTAXAREA_LIST,
    payload: axios.get<IEnumEmpTaxArea>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumEmpTaxArea> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMEMPTAXAREA,
    payload: axios.get<IEnumEmpTaxArea>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumEmpTaxArea> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMEMPTAXAREA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumEmpTaxArea> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMEMPTAXAREA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumEmpTaxArea> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMEMPTAXAREA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
