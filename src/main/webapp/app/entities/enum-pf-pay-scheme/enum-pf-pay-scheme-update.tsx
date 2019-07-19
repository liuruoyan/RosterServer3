import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumPfPaySchemes } from 'app/entities/enum-pf-pay-scheme/enum-pf-pay-scheme.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-pf-pay-scheme.reducer';
import { IEnumPfPayScheme } from 'app/shared/model/enum-pf-pay-scheme.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumPfPaySchemeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumPfPaySchemeUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumPfPaySchemeUpdate extends React.Component<IEnumPfPaySchemeUpdateProps, IEnumPfPaySchemeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      parentId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEnumPfPaySchemes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumPfPaySchemeEntity } = this.props;
      const entity = {
        ...enumPfPaySchemeEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/enum-pf-pay-scheme');
  };

  render() {
    const { enumPfPaySchemeEntity, enumPfPaySchemes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumPfPayScheme.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumPfPayScheme.home.createOrEditLabel">Create or edit a EnumPfPayScheme</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumPfPaySchemeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-pf-pay-scheme-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-pf-pay-scheme-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-pf-pay-scheme-valuez">
                    <Translate contentKey="rosterServer3App.enumPfPayScheme.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-pf-pay-scheme-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-pf-pay-scheme-orderz">
                    <Translate contentKey="rosterServer3App.enumPfPayScheme.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-pf-pay-scheme-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-pf-pay-scheme-tenentCode">
                    <Translate contentKey="rosterServer3App.enumPfPayScheme.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-pf-pay-scheme-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-pf-pay-scheme-parent">
                    <Translate contentKey="rosterServer3App.enumPfPayScheme.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-pf-pay-scheme-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumPfPaySchemes
                      ? enumPfPaySchemes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-pf-pay-scheme" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  enumPfPaySchemes: storeState.enumPfPayScheme.entities,
  enumPfPaySchemeEntity: storeState.enumPfPayScheme.entity,
  loading: storeState.enumPfPayScheme.loading,
  updating: storeState.enumPfPayScheme.updating,
  updateSuccess: storeState.enumPfPayScheme.updateSuccess
});

const mapDispatchToProps = {
  getEnumPfPaySchemes,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumPfPaySchemeUpdate);
