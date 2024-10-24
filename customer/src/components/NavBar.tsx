import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { ShoppingCart, User, Menu, X } from 'lucide-react';
import { useCart } from '../context/CartContext';

function NavBar() {
  const [isMenuVisible, setMenuVisible] = useState(false);
  const { cart } = useCart();

  const toggleMenu = () => setMenuVisible(!isMenuVisible);

  return (
    <nav className="bg-black text-white p-4">
      <div className="container mx-auto flex justify-between items-center">
        <Link to="/" className="text-2xl font-bold">JSA Services</Link>
        
        <div className="hidden md:flex space-x-4">
          <Link to="/services" className="hover:text-gray-300">Services</Link>
          <Link to="/companies" className="hover:text-gray-300">Companies</Link>
          <Link to="/about" className="hover:text-gray-300">About Us</Link>
        </div>
        
        <div className="flex items-center space-x-4">
          <Link to="/cart" className="relative">
            <ShoppingCart className="h-6 w-6" />
            {cart.length > 0 && (
              <span className="absolute -top-2 -right-2 bg-red-500 text-white rounded-full w-5 h-5 flex items-center justify-center text-xs">
                {cart.reduce((total, item) => total + item.quantity, 0)}
              </span>
            )}
          </Link>
          <Link to="/login">
            <User className="h-6 w-6" />
          </Link>
          <button onClick={toggleMenu} className="md:hidden">
            {isMenuVisible ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
          </button>
        </div>
      </div>
      
      {isMenuVisible && (
        <div className="md:hidden mt-4">
          <Link to="/services" className="block py-2 hover:bg-gray-700" onClick={toggleMenu}>Services</Link>
          <Link to="/companies" className="block py-2 hover:bg-gray-700" onClick={toggleMenu}>Companies</Link>
          <Link to="/about" className="block py-2 hover:bg-gray-700" onClick={toggleMenu}>About Us</Link>
        </div>
      )}
    </nav>
  );
}

export default NavBar;