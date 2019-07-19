import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISocialSecurityBenefits, defaultValue } from 'app/shared/model/social-security-benefits.model';

export const ACTION_TYPES = {
  FETCH_SOCIALSECURITYBENEFITS_LIST: 'socialSecurityBenefits/FETCH_SOCIALSECURITYBENEFITS_LIST',
  FETCH_SOCIALSECURITYBENEFITS: 'socialSecurityBenefits/FETCH_SOCIALSECURITYBENEFITS',
  CREATE_SOCIALSECURITYBENEFITS: 'socialSecurityBenefits/CREATE_SOCIALSECURITYBENEFITS',
  UPDATE_SOCIALSECURITYBENEFITS: 'socialSecurityBenefits/UPDATE_SOCIALSECURITYBENEFITS',
  DELETE_SOCIALSECURITYBENEFITS: 'socialSecurityBenefits/DELETE_SOCIALSECURITYBENEFITS',
  RESET: 'socialSecurityBenefits/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISocialSecurityBenefits>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SocialSecurityBenefitsState = Readonly<typeof initialState>;

// Reducer

export default (state: SocialSecurityBenefitsState = initialState, action): SocialSecurityBenefitsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SOCIALSECURITYBENEFITS):
    case REQUEST(ACTION_TYPES.UPDATE_SOCIALSECURITYBENEFITS):
    case REQUEST(ACTION_TYPES.DELETE_SOCIALSECURITYBENEFITS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS):
    case FAILURE(ACTION_TYPES.CREATE_SOCIALSECURITYBENEFITS):
    case FAILURE(ACTION_TYPES.UPDATE_SOCIALSECURITYBENEFITS):
    case FAILURE(ACTION_TYPES.DELETE_SOCIALSECURITYBENEFITS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SOCIALSECURITYBENEFITS):
    case SUCCESS(ACTION_TYPES.UPDATE_SOCIALSECURITYBENEFITS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SOCIALSECURITYBENEFITS):
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

const apiUrl = 'api/social-security-benefits';

// Actions

export const getEntities: ICrudGetAllAction<ISocialSecurityBenefits> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS_LIST,
    payload: axios.get<ISocialSecurityBenefits>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISocialSecurityBenefits> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SOCIALSECURITYBENEFITS,
    payload: axios.get<ISocialSecurityBenefits>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISocialSecurityBenefits> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SOCIALSECURITYBENEFITS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISocialSecurityBenefits> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SOCIALSECURITYBENEFITS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISocialSecurityBenefits> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SOCIALSECURITYBENEFITS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
