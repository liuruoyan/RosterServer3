import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumHighestEducation, defaultValue } from 'app/shared/model/enum-highest-education.model';

export const ACTION_TYPES = {
  FETCH_ENUMHIGHESTEDUCATION_LIST: 'enumHighestEducation/FETCH_ENUMHIGHESTEDUCATION_LIST',
  FETCH_ENUMHIGHESTEDUCATION: 'enumHighestEducation/FETCH_ENUMHIGHESTEDUCATION',
  CREATE_ENUMHIGHESTEDUCATION: 'enumHighestEducation/CREATE_ENUMHIGHESTEDUCATION',
  UPDATE_ENUMHIGHESTEDUCATION: 'enumHighestEducation/UPDATE_ENUMHIGHESTEDUCATION',
  DELETE_ENUMHIGHESTEDUCATION: 'enumHighestEducation/DELETE_ENUMHIGHESTEDUCATION',
  RESET: 'enumHighestEducation/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumHighestEducation>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumHighestEducationState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumHighestEducationState = initialState, action): EnumHighestEducationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMHIGHESTEDUCATION):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMHIGHESTEDUCATION):
    case REQUEST(ACTION_TYPES.DELETE_ENUMHIGHESTEDUCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION):
    case FAILURE(ACTION_TYPES.CREATE_ENUMHIGHESTEDUCATION):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMHIGHESTEDUCATION):
    case FAILURE(ACTION_TYPES.DELETE_ENUMHIGHESTEDUCATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMHIGHESTEDUCATION):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMHIGHESTEDUCATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMHIGHESTEDUCATION):
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

const apiUrl = 'api/enum-highest-educations';

// Actions

export const getEntities: ICrudGetAllAction<IEnumHighestEducation> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION_LIST,
    payload: axios.get<IEnumHighestEducation>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumHighestEducation> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMHIGHESTEDUCATION,
    payload: axios.get<IEnumHighestEducation>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumHighestEducation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMHIGHESTEDUCATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumHighestEducation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMHIGHESTEDUCATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumHighestEducation> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMHIGHESTEDUCATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
