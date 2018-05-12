package com.foriba.jws1.web.page;

import java.util.ArrayList;
import java.util.List;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.web.service.ServiceLocator;

/**
 * 
 * @author 
 * 
 */

public class OrderManagementPage extends AbstractPage {

  private static final long serialVersionUID = 1L;

  private List<Jws1Order> jws = new ArrayList<Jws1Order>();

  private Jws1Order currentParameter;

  public OrderManagementPage() throws Exception {
    onLoad();
  }

  public String onLoad()throws Exception {
    
      return "orderManagement";
   
  }

  public void refresh() {
    try {
      

    } catch (Exception e) {
      error(e);
    }
  }

  public void updateAll() throws Exception {
	
    try {
      refresh();
      
      
    } catch (Exception e) {
      error(e);
    }
  }

  public List<Jws1Order> getJWS1Order() {
    return jws;
  }

  public void setGeneralParams(List<Jws1Order> jws1) {
    this.jws = jws1;
  }

  public void setCurrentParameter(Jws1Order currentParameter) {
    this.currentParameter = currentParameter;
  }

  public Jws1Order getCurrentParameter() {
    return currentParameter;
  }

}
