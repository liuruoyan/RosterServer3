import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPayCard, defaultValue } from 'app/shared/model/pay-card.model';

export const ACTION_TYPES = {
  FETCH_PAYCARD_LIST: 'payCard/FETCH_PAYCARD_LIST',
  FETCH_PAYCARD: 'payCard/FETCH_PAYCARD',
  CREATE_PAYCARD: 'payCard/CREATE_PAYCARD',
  UPDATE_PAYCARD: 'payCard/UPDATE_PAYCARD',
  DELETE_PAYCARD: 'payCard/DELETE_PAYCARD',
  RESET: 'payCard/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPayCard>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PayCardState = Readonly<typeof initialState>;

// Reducer

export default (state: PayCardState = initialState, action): PayCardState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PAYCARD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PAYCARD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PAYCARD):
    case REQUEST(ACTION_TYPES.UPDATE_PAYCARD):
    case REQUEST(ACTION_TYPES.DELETE_PAYCARD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PAYCARD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PAYCARD):
    case FAILURE(ACTION_TYPES.CREATE_PAYCARD):
    case FAILURE(ACTION_TYPES.UPDATE_PAYCARD):
    case FAILURE(ACTION_TYPES.DELETE_PAYCARD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAYCARD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAYCARD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PAYCARD):
    case SUCCESS(ACTION_TYPES.UPDATE_PAYCARD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PAYCARD):
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

const apiUrl = 'api/pay-cards';

// Actions

export const getEntities: ICrudGetAllAction<IPayCard> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PAYCARD_LIST,
    payload: axios.get<IPayCard>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPayCard> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PAYCARD,
    payload: axios.get<IPayCard>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPayCard> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PAYCARD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPayCard> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PAYCARD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPayCard> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PAYCARD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
