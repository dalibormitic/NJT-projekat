import React, { Component } from 'react';
import axios from 'axios';
import { Accordion, Card, Button, Container } from 'react-bootstrap';
import Navbar from './CustomNavbar';
import AddBillModal from './AddBillModal';
import { Redirect } from 'react-router';

export default class Bills extends Component {
  state = {
    bills: [],
    showAddModal: false,
  };

  componentDidMount() {
    axios
      .get('http://localhost:8080/bills', {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Access-Control-Allow-Origin': '*',
        },
      })
      .then((response) => {
        this.setState({ bills: response.data });
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
          <h1 className="mt-3">Računi</h1>
          <Accordion id="accordion">
            {this.state.bills.map((bill, i) => (
              <Card key={bill.bill_id}>
                <Card.Header style={{ backgroundColor: '#343a40' }}>
                  <Accordion.Toggle
                    as={Button}
                    variant="link"
                    eventKey={i.toString()}
                  >
                    <h5 style={{ color: 'white' }}>
                      Id: {bill.bill_id} ; Datum:{' '}
                      {bill.bill_date.replace('T', ' ').substring(0, 19)} -
                      Iznos: {bill.bill_amount} RSD
                    </h5>
                  </Accordion.Toggle>
                </Card.Header>
                <Accordion.Collapse
                  eventKey={i.toString()}
                  id="accordion-collapse"
                >
                  <Card.Body>
                    {bill.billItems.map((item) => (
                      <h5 key={bill.bill_id + item.item_number}>
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
            Kreiraj novi račun
          </Button>

          {this.state.showAddModal && (
            <AddBillModal
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
