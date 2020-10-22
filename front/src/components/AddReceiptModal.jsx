import React, { Component } from 'react';
import { Modal, Button, Form, Container } from 'react-bootstrap';
import axios from 'axios';
import * as moment from 'moment';

export default class AddReceiptModal extends Component {
  state = {
    errorMessage: '',
    date: moment(new Date()).format('YYYY-MM-DD'),
    amount: 0,
    signed_by: localStorage.getItem('id'),
    suppliers: [],
    items: [],
    products: [],
    idForEdit: 0,
    supplierId: 0,
    supplier: {},
  };

  componentDidMount() {
    axios
      .get('http://localhost:8080/products', {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Access-Control-Allow-Origin': '*',
        },
      })
      .then((response) => {
        this.setState({ products: response.data });
      })
      .catch((error) => {
        console.log(error);
      });

    axios
      .get('http://localhost:8080/suppliers', {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Access-Control-Allow-Origin': '*',
        },
      })
      .then((response) => {
        this.setState({ suppliers: response.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  saveReceipt = () => {
    const newItems = [];
    this.state.items.forEach((item) => {
      const { created_by, measurement_unit, product_name, ...rest } = item;
      const {
        productId: article_id,
        count: quantity,
        product_price: price,
      } = rest;
      newItems.push({ article_id, quantity, price });
    });

    axios
      .post(
        'http://localhost:8080/receipts',
        {
          receipt: {
            receipt_date: this.state.date,
            amount: this.state.amount,
            supplied_by: this.state.supplierId,
            signed_by: this.state.signed_by,
          },
          receiptItems: newItems,
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
        this.setState({ errorMessage: 'Neuspešno kreiranje prijemnice!' });
      });
  };

  handleChange = (id) => {
    let product = this.state.products[id];

    const products = this.state.items.slice();
    let inCart = false;
    products.forEach((item) => {
      if (item.productId === product.productId) {
        item.count++;
        inCart = true;
      }
    });
    if (!inCart) {
      products.push({ ...product, count: 1 });
    }
    this.setState({
      amount: this.state.amount + this.state.products[id].product_price,
    });
    this.setState({ items: products });
  };

  setId = (id) => {
    this.setState({ idForEdit: id });
  };

  updateCount = (id) => {
    let items = this.state.items.slice();
    items.forEach((item, i) => {
      if (i === id && item.count > 1) {
        item.count--;
      } else if (i === id && item.count === 1) {
        items.splice(id, 1);
      }
      this.setState({
        amount: this.state.amount - this.state.items[id].product_price,
      });
    });
    this.setState({ items: items });
  };

  setSupplier = (id) => {
    this.state.suppliers.forEach((supplier) => {
      if (supplier.supplierId === id) {
        this.setState({ supplier: supplier });
      }
    });
  };

  render() {
    return (
      <div>
        <Modal
          {...this.props}
          size="lg"
          aria-labelledby="contained-modal-title-vcenter"
          centered
        >
          <Modal.Header closeButton>
            <Modal.Title id="contained-modal-title-vcenter">
              Kreiranje prijemnice
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Container>
              <Form>
                <select
                  onChange={(e) =>
                    this.setState({ supplierId: +e.target.value })
                  }
                >
                  <option>Odaberite dobavljača...</option>
                  {this.state.suppliers.map((supplier, i) => (
                    <option
                      key={supplier.supplierId}
                      value={supplier.supplierId}
                    >
                      {supplier.supplier_name}
                    </option>
                  ))}
                </select>{' '}
                <Button
                  onClick={() => this.setSupplier(this.state.supplierId)}
                  variant="secondary"
                  style={{ marginLeft: '30px', marginRight: '100px' }}
                >
                  Odaberi
                </Button>{' '}
                <select onChange={(e) => this.setId(e.target.value)}>
                  <option>Odaberite proizvod...</option>
                  {this.state.products.map((product, i) => (
                    <option key={product.productId} value={i}>
                      {product.product_name}
                    </option>
                  ))}
                </select>{' '}
                <Button
                  onClick={() => this.handleChange(this.state.idForEdit)}
                  variant="secondary"
                  style={{ marginLeft: '30px' }}
                >
                  Dodaj
                </Button>
                <ul style={{ marginTop: '15px' }}>
                  {this.state.items.size !== 0 &&
                    this.state.items.map((item, i) => (
                      <li key={'item' + i} style={{ marginTop: '15px' }}>
                        {item.product_name} x {item.count}
                        <Button
                          id={item.productId}
                          variant="secondary"
                          style={{ marginLeft: '20px', padding: '0px 10px' }}
                          size="small"
                          onClick={() => this.updateCount(i)}
                        >
                          -
                        </Button>
                      </li>
                    ))}
                </ul>
                <h5>
                  Ukupno košta:{' '}
                  {this.state.items.length === 0
                    ? '0.00'
                    : this.state.amount.toFixed(2)}{' '}
                  RSD
                </h5>
                <h5>Dobavljač: {this.state.supplier['supplier_name']}</h5>
                <p>{this.state.errorMessage}</p>
              </Form>
            </Container>
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="secondary"
              type="submit"
              onClick={() => this.saveReceipt()}
            >
              Kreiraj prijemnicu
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
