import React, { Component } from 'react';
import axios from 'axios';
import { Accordion, Card, Button, Container } from 'react-bootstrap';
import Navbar from './CustomNavbar';
import AddReceiptModal from './AddReceiptModal';
import { Redirect } from 'react-router';

export default class Receipts extends Component {
  state = {
    receipts: [],
    showAddModal: false,
  };

  componentDidMount() {
    axios
      .get('http://localhost:8080/receipts', {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Access-Control-Allow-Origin': '*',
        },
      })
      .then((response) => {
        this.setState({ receipts: response.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    let closeAddModal = () => this.setState({ showAddModal: false });

    return (
      <div>
        <Navbar />
        <Container>
          <h1 className="mt-3">Prijemnice</h1>
          <Accordion id="accordion">
            {this.state.receipts.map((receipt, i) => (
              <Card key={receipt.receiptId}>
                <Card.Header style={{ backgroundColor: '#343a40' }}>
                  <Accordion.Toggle
                    as={Button}
                    variant="link"
                    eventKey={i.toString()}
                  >
                    <h5 style={{ color: 'white' }}>
                      Id: {receipt.receiptId} ; Datum:{' '}
                      {receipt.receipt_date.replace('T', ' ').substring(0, 19)}{' '}
                      - Iznos: {receipt.amount} RSD
                    </h5>
                  </Accordion.Toggle>
                </Card.Header>
                <Accordion.Collapse
                  eventKey={i.toString()}
                  id="accordion-collapse"
                >
                  <Card.Body>
                    {receipt.receiptItems.map((item) => (
                      <h5 key={receipt.receiptId + item.item_number}>
                        {item.item_number}. {item.quantity} x{' '}
                        {item.article_name} = {item.quantity} x {item.price} RSD
                      </h5>
                    ))}
                  </Card.Body>
                </Accordion.Collapse>
              </Card>
            ))}
          </Accordion>
          <Button
            variant="secondary"
            style={{ marginBottom: '30px' }}
            onClick={() => this.setState({ showAddModal: true })}
          >
            Kreiraj novu prijemnicu
          </Button>

          {this.state.showAddModal && (
            <AddReceiptModal
              show={this.state.showAddModal}
              onHide={closeAddModal}
            />
          )}
          {localStorage.getItem('token') === null && <Redirect to="login" />}
        </Container>
      </div>
    );
  }
}
