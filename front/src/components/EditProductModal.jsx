import React, { Component } from 'react';
import { Modal, Button, Form, Container } from 'react-bootstrap';
import axios from 'axios';

export default class EditProductModal extends Component {
  constructor(props) {
    super(props);

    this.state = {
      product_name: this.props.product.product_name,
      product_price: this.props.product.product_price,
      measurement_unit: this.props.product.measurement_unit,
      errorMessage: '',
    };
  }

  editProduct = (id) => {
    axios
      .patch(
        'http://localhost:8080/products/' + id,
        {
          product_name: this.state.product_name,
          product_price: this.state.product_price,
          measurement_unit: this.state.measurement_unit,
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
              {this.state.product_name}
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Container>
              <Form>
                <Form.Group>
                  <Form.Label>Naziv proizvoda</Form.Label>
                  <Form.Control
                    type="text"
                    value={this.state.product_name}
                    onChange={(e) =>
                      this.setState({ product_name: e.target.value })
                    }
                  />
                </Form.Group>

                <Form.Group>
                  <Form.Label>Cena</Form.Label>
                  <Form.Control
                    type="text"
                    value={this.state.product_price}
                    onChange={(e) =>
                      this.setState({ product_price: e.target.value })
                    }
                  />
                </Form.Group>
                <Form.Group>
                  <Form.Label>Jedinica mere</Form.Label>
                  <Form.Control
                    type="text"
                    value={this.state.measurement_unit}
                    onChange={(e) =>
                      this.setState({ measurement_unit: e.target.value })
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
              onClick={() => this.editProduct(this.props.product.productId)}
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
