import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEnumDepositBank, defaultValue } from 'app/shared/model/enum-deposit-bank.model';

export const ACTION_TYPES = {
  FETCH_ENUMDEPOSITBANK_LIST: 'enumDepositBank/FETCH_ENUMDEPOSITBANK_LIST',
  FETCH_ENUMDEPOSITBANK: 'enumDepositBank/FETCH_ENUMDEPOSITBANK',
  CREATE_ENUMDEPOSITBANK: 'enumDepositBank/CREATE_ENUMDEPOSITBANK',
  UPDATE_ENUMDEPOSITBANK: 'enumDepositBank/UPDATE_ENUMDEPOSITBANK',
  DELETE_ENUMDEPOSITBANK: 'enumDepositBank/DELETE_ENUMDEPOSITBANK',
  RESET: 'enumDepositBank/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEnumDepositBank>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EnumDepositBankState = Readonly<typeof initialState>;

// Reducer

export default (state: EnumDepositBankState = initialState, action): EnumDepositBankState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENUMDEPOSITBANK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENUMDEPOSITBANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ENUMDEPOSITBANK):
    case REQUEST(ACTION_TYPES.UPDATE_ENUMDEPOSITBANK):
    case REQUEST(ACTION_TYPES.DELETE_ENUMDEPOSITBANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ENUMDEPOSITBANK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENUMDEPOSITBANK):
    case FAILURE(ACTION_TYPES.CREATE_ENUMDEPOSITBANK):
    case FAILURE(ACTION_TYPES.UPDATE_ENUMDEPOSITBANK):
    case FAILURE(ACTION_TYPES.DELETE_ENUMDEPOSITBANK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMDEPOSITBANK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENUMDEPOSITBANK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENUMDEPOSITBANK):
    case SUCCESS(ACTION_TYPES.UPDATE_ENUMDEPOSITBANK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENUMDEPOSITBANK):
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

const apiUrl = 'api/enum-deposit-banks';

// Actions

export const getEntities: ICrudGetAllAction<IEnumDepositBank> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMDEPOSITBANK_LIST,
    payload: axios.get<IEnumDepositBank>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEnumDepositBank> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENUMDEPOSITBANK,
    payload: axios.get<IEnumDepositBank>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEnumDepositBank> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENUMDEPOSITBANK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEnumDepositBank> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENUMDEPOSITBANK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEnumDepositBank> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENUMDEPOSITBANK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
