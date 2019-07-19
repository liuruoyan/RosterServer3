import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IEnumEmpTaxArea } from 'app/shared/model/enum-emp-tax-area.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './enum-emp-tax-area.reducer';

export interface IEnumEmpTaxAreaDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumEmpTaxAreaDeleteDialog extends React.Component<IEnumEmpTaxAreaDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.enumEmpTaxAreaEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { enumEmpTaxAreaEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="rosterServer3App.enumEmpTaxArea.delete.question">
          <Translate contentKey="rosterServer3App.enumEmpTaxArea.delete.question" interpolate={{ id: enumEmpTaxAreaEntity.id }}>
            Are you sure you want to delete this EnumEmpTaxArea?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-enumEmpTaxArea" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ enumEmpTaxArea }: IRootState) => ({
  enumEmpTaxAreaEntity: enumEmpTaxArea.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumEmpTaxAreaDeleteDialog);
