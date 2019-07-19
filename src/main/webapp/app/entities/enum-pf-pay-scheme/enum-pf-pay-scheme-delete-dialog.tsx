import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IEnumPfPayScheme } from 'app/shared/model/enum-pf-pay-scheme.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './enum-pf-pay-scheme.reducer';

export interface IEnumPfPaySchemeDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumPfPaySchemeDeleteDialog extends React.Component<IEnumPfPaySchemeDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.enumPfPaySchemeEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { enumPfPaySchemeEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="rosterServer3App.enumPfPayScheme.delete.question">
          <Translate contentKey="rosterServer3App.enumPfPayScheme.delete.question" interpolate={{ id: enumPfPaySchemeEntity.id }}>
            Are you sure you want to delete this EnumPfPayScheme?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-enumPfPayScheme" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ enumPfPayScheme }: IRootState) => ({
  enumPfPaySchemeEntity: enumPfPayScheme.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumPfPaySchemeDeleteDialog);
