import React, { Component } from 'react';
import { Modal, Button, Form, Container } from 'react-bootstrap';
import axios from 'axios';

export default class EditSupplierModal extends Component {
  constructor(props) {
    super(props);

    this.state = {
      supplier_name: this.props.supplier.supplier_name,
      supplier_address: this.props.supplier.supplier_address,
      errorMessage: '',
    };
  }

  editSupplier = (id) => {
    console.log(id);
    axios
      .patch(
        'http://localhost:8080/suppliers/' + id,
        {
          supplier_name: this.state.supplier_name,
          supplier_address: this.state.supplier_address,
        },
        {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token'),
            'Access-Control-Allow-Origin': '*',
          },
        }
      )
      .then((response) => {
        this.props.onHide();
      })
      .catch((error) => {
        this.setState({ errorMessage: 'Neuspešna izmena!' });
      });
  };

  render() {
    return (
      <div>
        <Modal
          {...this.props}
          size="md"
          aria-labelledby="contained-modal-title-vcenter"
          centered
        >
          <Modal.Header closeButton>
            <Modal.Title id="contained-modal-title-vcenter">
              {this.state.supplier_name}
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Container>
              <Form>
                <Form.Group>
                  <Form.Label>Naziv dobavljača</Form.Label>
                  <Form.Control
                    type="text"
                    value={this.state.supplier_name}
                    onChange={(e) =>
                      this.setState({ supplier_name: e.target.value })
                    }
                  />
                </Form.Group>

                <Form.Group>
                  <Form.Label>Adresa dobavljača</Form.Label>
                  <Form.Control
                    type="text"
                    value={this.state.supplier_address}
                    onChange={(e) =>
                      this.setState({ supplier_address: e.target.value })
                    }
                  />
                </Form.Group>
                <p>{this.state.errorMessage}</p>
              </Form>
            </Container>
          </Modal.Body>
          <Modal.Footer>
            <Button
              type="submit"
              onClick={() => this.editSupplier(this.props.supplier.supplierId)}
              variant="secondary"
            >
              Izmeni
            </Button>
            <Button onClick={this.props.onHide} variant="secondary">
              Zatvori modal
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
}
