import React from 'react';
import './App.css';
import Products from './components/Products';
import Bills from './components/Bills';
import Receipts from './components/Receipts';
import { BrowserRouter, Route, Redirect } from 'react-router-dom';
import Suppliers from './components/Suppliers';
import Login from './components/Login';
import Register from './components/Register';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Route exact path="/" render={() => <Redirect to="/login" />} />
        <Route path="/login" component={Login} exact={true} />
        <Route path="/products" component={() => <Products />} />
        <Route path="/bills" component={() => <Bills />} />
        <Route path="/receipts" component={() => <Receipts />} />
        <Route path="/suppliers" component={() => <Suppliers />} />
        <Route path="/register" component={() => <Register />} />
      </div>
    </BrowserRouter>
  );
}

export default App;
