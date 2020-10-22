import React, { Component } from 'react';
import { Table, Container, Button, Toast, Form, Row } from 'react-bootstrap';
import Navbar from './CustomNavbar';
import axios from 'axios';
import EditModal from './EditSupplierModal';
import AddSupplierModal from './AddSupplierModal';
import { Redirect } from 'react-router';

export default class Suppliers extends Component {
  state = {
    suppliers: [],
    showModal: false,
    supplierForEdit: {},
    showToast: false,
    toastMessage: '',
    showAddModal: false,
    filteredSuppliers: [],
    search: '',
  };

  componentDidMount() {
    axios
      .get('http://localhost:8080/suppliers', {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Access-Control-Allow-Origin': '*',
        },
      })
      .then((response) => {
        this.setState({ suppliers: response.data });
        this.setState({ filteredSuppliers: response.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  deleteSupplier = (id) => {
    const currentSuppliers = this.state.suppliers;

    this.setState({
      suppliers: currentSuppliers.filter(
        (supplier) => supplier.supplierId !== id
      ),
    });

    axios
      .delete('http://localhost:8080/suppliers/' + id, {
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
          suppliers: currentSuppliers,
        });
        this.setState({
          toastMessage: 'Neuspešno brisanje!',
        });
        this.setState({ showToast: true });
      });
  };

  filterSuppliers = () => {
    const original = this.state.suppliers.slice();
    if (this.state.search === '') {
      this.setState({ filteredSuppliers: original });
    } else {
      let filtered = [];
      filtered = this.state.suppliers.filter((supplier) => {
        return supplier.supplier_name
          .toLowerCase()
          .includes(this.state.search.toLowerCase());
      });
      this.setState({ filteredSuppliers: filtered });
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
          <h1 className="mt-3">Dobavljači</h1>
          <Button
            className="float-right"
            style={{ margin: '20px' }}
            onClick={() => this.setState({ showAddModal: true })}
            variant="secondary"
          >
            Dodaj novog dobavljača
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
                  onClick={() => this.filterSuppliers()}
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
                <th scope="col">Naziv dobavljača</th>
                <th scope="col">Adresa</th>
                <th scope="col">Izmeni</th>
                <th scope="col">Obriši</th>
              </tr>
            </thead>
            <tbody>
              {this.state.filteredSuppliers.map((supplier, i) => (
                <tr key={supplier.supplierId}>
                  <td>{supplier.supplierId}</td>
                  <td>{supplier.supplier_name}</td>
                  <td>{supplier.supplier_address}</td>
                  <td>
                    <Button
                      id={supplier.supplierId}
                      onClick={() =>
                        this.setState({
                          showModal: true,
                          supplierForEdit: supplier,
                        })
                      }
                      variant="secondary"
                    >
                      Izmeni
                    </Button>
                  </td>
                  <td>
                    <Button
                      id={supplier.supplier_id}
                      onClick={() => this.deleteSupplier(supplier.supplierId)}
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
              supplier={this.state.supplierForEdit}
            />
          )}

          {this.state.showAddModal && (
            <AddSupplierModal
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
