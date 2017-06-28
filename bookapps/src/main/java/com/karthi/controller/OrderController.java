package com.karthi.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karthi.model.Order;
import com.karthi.model.User;
import com.karthi.service.OrderService;
import com.karthi.service.UserService;
import com.karthi.util.EmailUtil;

@Controller
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailUtil emailUtil;

	@GetMapping("/myorders")
	public String list(ModelMap modelMap, HttpSession session) {
		User user = userService.findByEmail((String) session.getAttribute("logid"));
		Long id = user.getId();
		List<Order> list = orderService.findBYUserId(id);
		System.out.println("orders:" + list.size());
		for (Order order : list) {
			System.out.println(order);
		}
		modelMap.addAttribute("ORDERS_LIST", list);
		return "order/list";
	}

	@GetMapping
	public String lists(ModelMap modelMap, HttpSession session) {

		User user = userService.findByEmail((String) session.getAttribute("logid"));
		Long id = user.getId();
		List<Order> list = orderService.findAll();
		System.out.println("orders:" + list.size());
		for (Order order : list) {
			System.out.println(order);
		}
		modelMap.addAttribute("ORDERS_LIST", list);
		return "order/list";
	}

	@GetMapping("/allOrders")
	public String listsAll(ModelMap modelMap, HttpSession session) {

		User user = userService.findByEmail((String) session.getAttribute("logid"));
		Long id = user.getId();
		List<Order> list = orderService.findAll();
		System.out.println("orders:" + list.size());
		for (Order order : list) {
			System.out.println(order);
		}
		modelMap.addAttribute("ORDERS_LIST", list);
		return "order/list";
	}

	@GetMapping("/cart")
	public String showData() {
		return "order/cart";
	}

	@PostMapping("/save")
	public String save(@RequestParam("total_amount") String totalAmount, String paymentmode, HttpSession session) throws Exception {

		Order orderInCart = (Order) session.getAttribute("MY_CART_ITEMS");
		if (orderInCart != null && orderInCart.getOrderItems().size() > 0) {

			orderInCart.setUser(userService.findByEmail((String) session.getAttribute("logid")));
			orderInCart.setTotalPrice(totalAmount);
			orderInCart.setPaymentmode(paymentmode);
			orderService.save(orderInCart);
			session.removeAttribute("MY_CART_ITEMS");
		}

		return "redirect:../orders/myorders";

	}

	@GetMapping("/updateStatus")
	public String updateStatus(@RequestParam("id") String orderId, @RequestParam("status") String status,
			HttpSession session) throws Exception {
		Order order = orderService.findOne(Integer.valueOf(orderId));
		User user = (User) session.getAttribute("LOGGED_IN_USER");
		if (user.getRole().getId() == 1) {
			if ("CANCELLED".equals(status)) {
				order.setCancelledDate(LocalDate.now());
			} else if ("DELIVERED".equals(status)) {
				order.setDeliveredDate(LocalDateTime.now());
			}

			order.setStatus(status);
			orderService.save(order);
			return "redirect:../orders/allOrders";
		} else {
			if ("CANCELLED".equals(status)) {
				order.setCancelledDate(LocalDate.now());
			} else if ("DELIVERED".equals(status)) {
				order.setDeliveredDate(LocalDateTime.now());
			}

			order.setStatus(status);
			orderService.save(order);
			return "redirect:../orders/myorders";
		}
	}

}
