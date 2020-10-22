import React, { Component } from 'react';
import { Button, Form, Container, Row, Col } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default class Register extends Component {
  state = {
    username: '',
    password: '',
    name: '',
    message: '',
    linkVisible: false,
  };

  register = () => {
    axios
      .post('http://localhost:8080/users', {
        username: this.state.username,
        password: this.state.password,
        name: this.state.username,
      })
      .then((response) => {
        this.setState({ message: 'Uspešna registracija' });
        this.setState({ linkVisible: true });
      })
      .catch((error) => {
        this.setState({ message: 'Neuspešna registracija!' });
      });
  };

  render() {
    return (
      <Container>
        <h2 className="mt-5">Registrujte se</h2>
        <Form className="mt-5">
          <Row>
            <Col xs={6} style={{ margin: '0 auto' }}>
              <Form.Group style={{ textAlign: 'left' }}>
                <Form.Label>Korisničko ime</Form.Label>
                <Form.Control
                  type="text"
                  value={this.state.username}
                  onChange={(e) => this.setState({ username: e.target.value })}
                />
              </Form.Group>
            </Col>
          </Row>
          <Row>
            <Col xs={6} style={{ margin: '0 auto' }}>
              <Form.Group style={{ textAlign: 'left' }}>
                <Form.Label>Lozinka</Form.Label>
                <Form.Control
                  type="text"
                  value={this.state.password}
                  onChange={(e) => this.setState({ password: e.target.value })}
                />
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col xs={6} style={{ margin: '0 auto' }}>
              <Form.Group style={{ textAlign: 'left' }}>
                <Form.Label>Ime</Form.Label>
                <Form.Control
                  type="text"
                  value={this.state.name}
                  onChange={(e) => this.setState({ name: e.target.value })}
                />
              </Form.Group>
            </Col>
          </Row>

          <Form.Group>
            <Button variant="secondary" onClick={() => this.register()}>
              Registrujte se
            </Button>
          </Form.Group>
          <p>{this.state.message}</p>
        </Form>
        {this.state.linkVisible && (
          <p>
            Možete se prijaviti <Link to="/login">na sledećem linku</Link>
          </p>
        )}
      </Container>
    );
  }
}
