import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdditionalPost, defaultValue } from 'app/shared/model/additional-post.model';

export const ACTION_TYPES = {
  FETCH_ADDITIONALPOST_LIST: 'additionalPost/FETCH_ADDITIONALPOST_LIST',
  FETCH_ADDITIONALPOST: 'additionalPost/FETCH_ADDITIONALPOST',
  CREATE_ADDITIONALPOST: 'additionalPost/CREATE_ADDITIONALPOST',
  UPDATE_ADDITIONALPOST: 'additionalPost/UPDATE_ADDITIONALPOST',
  DELETE_ADDITIONALPOST: 'additionalPost/DELETE_ADDITIONALPOST',
  RESET: 'additionalPost/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdditionalPost>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type AdditionalPostState = Readonly<typeof initialState>;

// Reducer

export default (state: AdditionalPostState = initialState, action): AdditionalPostState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADDITIONALPOST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADDITIONALPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ADDITIONALPOST):
    case REQUEST(ACTION_TYPES.UPDATE_ADDITIONALPOST):
    case REQUEST(ACTION_TYPES.DELETE_ADDITIONALPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ADDITIONALPOST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADDITIONALPOST):
    case FAILURE(ACTION_TYPES.CREATE_ADDITIONALPOST):
    case FAILURE(ACTION_TYPES.UPDATE_ADDITIONALPOST):
    case FAILURE(ACTION_TYPES.DELETE_ADDITIONALPOST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADDITIONALPOST_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADDITIONALPOST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADDITIONALPOST):
    case SUCCESS(ACTION_TYPES.UPDATE_ADDITIONALPOST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADDITIONALPOST):
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

const apiUrl = 'api/additional-posts';

// Actions

export const getEntities: ICrudGetAllAction<IAdditionalPost> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ADDITIONALPOST_LIST,
    payload: axios.get<IAdditionalPost>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IAdditionalPost> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADDITIONALPOST,
    payload: axios.get<IAdditionalPost>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAdditionalPost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADDITIONALPOST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdditionalPost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADDITIONALPOST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdditionalPost> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADDITIONALPOST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
