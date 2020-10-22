import React, { Component } from 'react';
import { Button, Form, Container, Row, Col } from 'react-bootstrap';
import { Redirect, withRouter, Link } from 'react-router-dom';
import axios from 'axios';

class Login extends Component {
  state = {
    username: '',
    password: '',
    token: '',
    errorMessage: '',
  };

  login = () => {
    axios
      .post('http://localhost:8080/authenticate', {
        username: this.state.username,
        password: this.state.password,
      })
      .then((response) => {
        this.setState({ token: response.data.token });
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('id', response.data.id);

        this.props.history.push('/products');
      })
      .catch((error) => {
        this.setState({ errorMessage: 'Proverite podatke!' });
        setTimeout(() => {
          this.setState({ errorMessage: '' });
        }, 1500);
      });
  };

  render() {
    return (
      <Container>
        <h2 className="mt-5">Prijavite se za nastavak</h2>
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
                  type="password"
                  value={this.state.password}
                  onChange={(e) => this.setState({ password: e.target.value })}
                />
              </Form.Group>
            </Col>
          </Row>

          <Form.Group>
            <Button variant="secondary" onClick={() => this.login()}>
              Prijavi se
            </Button>
          </Form.Group>
          <p>{this.state.errorMessage}</p>
        </Form>
        Ukoliko nemate nalog, <Link to="/register">registrujte se ovde</Link>
        {localStorage.getItem('token') && <Redirect to="/products" />}
      </Container>
    );
  }
}

export default withRouter(Login);
