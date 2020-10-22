import React, { Component } from 'react';
import { Modal, Button, Form, Container } from 'react-bootstrap';
import axios from 'axios';

export default class AddSupplierModal extends Component {
  constructor(props) {
    super(props);

    this.state = {
      supplier_name: '',
      supplier_address: '',
      errorMessage: '',
    };
  }

  saveSupplier = () => {
    axios
      .post(
        'http://localhost:8080/suppliers/',
        {
          supplier_name: this.state.supplier_name,
          supplier_address: this.state.supplier_address,
          created_by: localStorage.getItem('id'),
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
        this.setState({
          errorMessage: 'Dobavljač sa tim nazivom već postoji!',
        });
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
              Dodajte novi proizvod
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
              variant="secondary"
              type="submit"
              onClick={() => this.saveSupplier()}
            >
              Dodaj dobavljača
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
