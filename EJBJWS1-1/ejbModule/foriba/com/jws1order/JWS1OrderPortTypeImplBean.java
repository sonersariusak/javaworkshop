package foriba.com.jws1order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.foriba.jws1.bean.OrderServiceBean;
import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;

@WebService(portName = "JWS1OrderPort", serviceName = "JWS1OrderService", endpointInterface = "foriba.com.jws1order.JWS1OrderPortType", targetNamespace = "http://com.foriba/JWS1Order", wsdlLocation = "META-INF/wsdl/foriba/com/jws1order/JWS1Order/JWS1Order.wsdl")
@Stateless
public class JWS1OrderPortTypeImplBean {

	@EJB
	private OrderService OrderService;
	@SuppressWarnings("unused")
	private JWS1OrderService jws1OrderService;
	public  foriba.com.jws1order.GetOrderListByOrderProductNameResponse getOrderListByOrderProductName(foriba.com.jws1order.GetOrderListByOrderProductNameRequest parameter) throws Exception {
		OrderServiceBean response = new OrderServiceBean();
		response.getOrderListByOrderProductName(parameter.orderProductName);
		return null;
	 }

	public  AddOrderResponse addOrder(AddOrderRequest parameter) throws Exception {
		AddOrderResponse aor=new AddOrderResponse();
		Jws1Order jws=new Jws1Order();
		jws.setOrderedProductName(parameter.orderedProductName);
		//jws.setOrderAmount(parameter.orderAmount);
		jws.setOrderDate(null);
		OrderService.addOrder(jws);
		aor.result="The order was saved successfully.";
		return aor;
	    
	 }

	public  GetOrderByIDResponse getOrderByID(GetOrderByIDRequest parameter) throws Exception {
		GetOrderByIDResponse rs=new GetOrderByIDResponse();
		//rs.result.add((Jws1Order) OrderService.getOrderByID(parameter.id));
		return rs;
	 }

	public  UpdateOrderResponse updateOrder(UpdateOrderRequest parameter) {
	  return null;
	 }

	public  UpdateAmountByOrderedProductNameResponse updateAmountByOrderedProductName(UpdateAmountByOrderedProductNameRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderArrivalDateResponse getOrderListByOrderArrivalDate(GetOrderListByOrderArrivalDateRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderDateResponse getOrderListByOrderDate(GetOrderListByOrderDateRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderedProductNameByAmountResponse getOrderListByOrderedProductNameByAmount(GetOrderListByOrderedProductNameByAmountRequest parameter) {
	  return null;
	 }

	public  GetOrderListByAmountByOrderArrivalDateResponse getOrderListByAmountByOrderArrivalDate(GetOrderListByAmountByOrderArrivalDateRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderedProductNameResponse getOrderListByOrderedProductName(GetOrderListByOrderedProductNameRequest parameter) {
	  return null;
	 }
}