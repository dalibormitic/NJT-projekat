import React, { Component } from 'react';
import axios from 'axios';
import { Table, Container, Button, Toast, Form, Row } from 'react-bootstrap';
import EditModal from './EditProductModal';
import Navbar from './CustomNavbar';
import AddProductModal from './AddProductModal';
import { Redirect } from 'react-router';

export default class Products extends Component {
  state = {
    products: [],
    showModal: false,
    productForEdit: {},
    showToast: false,
    toastMessage: '',
    showAddModal: false,
    search: '',
    filteredProducts: [],
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
        this.setState({ filteredProducts: response.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  deleteProduct = (id) => {
    const currentProducts = this.state.products;

    this.setState({
      products: currentProducts.filter((product) => product.productId !== id),
    });

    axios
      .delete('http://localhost:8080/products/' + id, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Access-Control-Allow-Origin': '*',
        },
      })
      .then((response) => {
        this.setState({ toastMessage: 'Uspešno obrisano!' });
        this.setState({ showToast: true });
      })
      .catch((error) => {
        this.setState({
          products: currentProducts,
        });
        this.setState({
          toastMessage:
            'Neuspešno brisanje! Proizvod se nalazi na nekom računu ili prijemnici!',
        });
        this.setState({ showToast: true });
      });
  };

  filterProducts = () => {
    const original = this.state.products.slice();
    if (this.state.search === '') {
      this.setState({ filteredProducts: original });
    } else {
      let filtered = [];
      filtered = this.state.products.filter((product) => {
        return product.product_name
          .toLowerCase()
          .includes(this.state.search.toLowerCase());
      });
      this.setState({ filteredProducts: filtered });
    }
  };

  render() {
    let closeModal = () => this.setState({ showModal: false });
    let closeAddModal = () => this.setState({ showAddModal: false });
    let toggleShowToast = () => this.setState({ showToast: false });
    return (
      <>
        <Navbar />
        <Container>
          <h1 className="mt-3">Proizvodi</h1>
          <Button
            className="float-right"
            style={{ margin: '20px' }}
            onClick={() => this.setState({ showAddModal: true })}
            variant="secondary"
          >
            Dodaj novi proizvod
          </Button>
          <Form>
            <Row>
              <Form.Group>
                <Form.Control
                  type="text"
                  value={this.state.search}
                  onChange={(e) => this.setState({ search: e.target.value })}
                  style={{ margin: '20px' }}
                  placeholder="Pretražite"
                />
              </Form.Group>
              <Form.Group>
                <Button
                  variant="secondary"
                  style={{ margin: '20px', marginLeft: '50px' }}
                  onClick={() => this.filterProducts()}
                >
                  Pretraži
                </Button>
              </Form.Group>
            </Row>
          </Form>
          <Table striped bordered hover variant="dark" id="productTable">
            <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Ime proizvoda</th>
                <th scope="col">Cena</th>
                <th scope="col">Jedinica mere</th>
                <th scope="col">Izmeni</th>
                <th scope="col">Obriši</th>
              </tr>
            </thead>
            <tbody>
              {this.state.filteredProducts.map((product, i) => (
                <tr key={product.productId}>
                  <td>{product.productId}</td>
                  <td>{product.product_name}</td>
                  <td>{product.product_price}</td>
                  <td>{product.measurement_unit}</td>
                  <td>
                    <Button
                      id={product.productId}
                      onClick={() =>
                        this.setState({
                          showModal: true,
                          productForEdit: product,
                        })
                      }
                      variant="secondary"
                    >
                      Izmeni
                    </Button>
                  </td>
                  <td>
                    <Button
                      id={product.productId}
                      onClick={() => this.deleteProduct(product.productId)}
                      variant="secondary"
                    >
                      Obriši
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>

          {this.state.showToast && (
            <Toast
              show={this.state.showToast}
              onClose={toggleShowToast}
              style={{
                position: 'absolute',
                bottom: 20,
                right: 30,
                backgroundColor: 'white',
              }}
              delay={2500}
              autohide
            >
              <Toast.Header>
                <strong className="mr-auto">Brisanje</strong>
              </Toast.Header>
              <Toast.Body>{this.state.toastMessage}</Toast.Body>
            </Toast>
          )}
          {this.state.showModal && (
            <EditModal
              show={this.state.showModal}
              onHide={closeModal}
              product={this.state.productForEdit}
            />
          )}

          {this.state.showAddModal && (
            <AddProductModal
              show={this.state.showAddModal}
              onHide={closeAddModal}
            />
          )}

          {localStorage.getItem('token') === null && <Redirect to="login" />}
        </Container>
      </>
    );
  }
}
