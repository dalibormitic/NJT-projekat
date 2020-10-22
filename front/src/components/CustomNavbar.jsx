import React, { Component } from 'react';
import { Navbar, Nav, Button } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';

class CustomNavbar extends Component {
  handleClick = () => {
    localStorage.clear();
    this.props.history.push('/');
  };

  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Nav className="mr-auto">
          <Nav.Link href="/products">Proizvodi</Nav.Link>
          <Nav.Link href="/suppliers">Dobavljači</Nav.Link>
          <Nav.Link href="/bills">Računi</Nav.Link>
          <Nav.Link href="/receipts">Prijemnice</Nav.Link>
          <Nav.Link href="http://localhost:8080/pdfreport" target="_blank">
            Izveštaj
          </Nav.Link>
          <Button
            variant="secondary"
            style={{ marginLeft: '800px' }}
            onClick={() => this.handleClick()}
          >
            Izloguj se
          </Button>
        </Nav>
      </Navbar>
    );
  }
}

export default withRouter(CustomNavbar);
