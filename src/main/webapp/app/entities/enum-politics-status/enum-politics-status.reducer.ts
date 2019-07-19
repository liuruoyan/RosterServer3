import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumPoliticsStatus, defaultValue } from 'app/shared/model/enum-politics-status.model';

export const ACTION_TYPES = {
  FETCH_ENUMPOLITICSSTATUS_LIST: 'enumPoliticsStatus/FETCH_ENUMPOLITICSSTATUS_LIST',
  FETCH_ENUMPOLITICSSTATUS: 'enumPoliticsStatus/FETCH_ENUMPOLITICSSTATUS',
  CREATE_ENUMPOLITICSSTATUS: 'enumPoliticsStatus/CREATE_ENUMPOLITICSSTATUS',
  UPDATE_ENUMPOLITICSSTATUS: 'enumPoliticsStatus/UPDATE_ENUMPOLITICSSTATUS',
  DELETE_ENUMPOLITICSSTATUS: 'enumPoliticsStatus/DELETE_ENUMPOLITICSSTATUS',
  RESET: 'enumPoliticsStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumPoliticsStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumPoliticsStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumPoliticsStatusState = initialState, action): EnumPoliticsStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMPOLITICSSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMPOLITICSSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_ENUMPOLITICSSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_ENUMPOLITICSSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMPOLITICSSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_ENUMPOLITICSSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMPOLITICSSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMPOLITICSSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMPOLITICSSTATUS):
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

const apiUrl = 'api/enum-politics-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IEnumPoliticsStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS_LIST,
    payload: axios.get<IEnumPoliticsStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumPoliticsStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMPOLITICSSTATUS,
    payload: axios.get<IEnumPoliticsStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumPoliticsStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMPOLITICSSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumPoliticsStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMPOLITICSSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumPoliticsStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMPOLITICSSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
