package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		String action = req.getParameter("action");
		if ("add".equals(action)) {
			addItemToCart(req, cart);
		} else if ("update".equals(action)) {
			updateCartItem(req, cart);
		} else if ("remove".equals(action)) {
			removeItemFromCart(req, cart);
		} else if ("clear".equals(action)) {
			clearCart(cart);
		}

		session.setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");

	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("menuid"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		MenuDAO menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenu(itemId);

//		HttpSession session = req.getSession();
//		session.setAttribute("restaurantid", menuItem.getRestaurantid()); //it is already added to the session if we add again there is no differece it just overrides the value

		if (menuItem != null) {
			CartItem item = new CartItem(menuItem.getMenuid(), menuItem.getRestaurantid(), menuItem.getItemName(),
					menuItem.getPrice(), quantity, quantity * menuItem.getPrice()); // it is sutotal

			cart.addItem(item);

		}
	}

	private void updateCartItem(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(itemId, quantity);

	}

	private void removeItemFromCart(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId);
	}

	private void clearCart(Cart cart) {
		cart.clearItems(); // Assuming your Cart class has a method to clear all items
	}

}
