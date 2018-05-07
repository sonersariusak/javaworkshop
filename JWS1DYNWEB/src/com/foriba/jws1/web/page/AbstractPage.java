package com.foriba.jws1.web.page;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author murat.demir
 *
 * 
 * Apr 16, 2015 - 5:36:05 AM
 *
 *
 */

public abstract class AbstractPage implements Serializable {

	private static final long serialVersionUID = 1L;

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected Application getApplication() {
		return FacesContext.getCurrentInstance().getApplication();
	}

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected ELContext getElContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}
	protected UIViewRoot getViewRoot() {
		return FacesContext.getCurrentInstance().getViewRoot();
	}

	protected Map<String, Object> getApplicationMap() {
		return getExternalContext().getApplicationMap();
	}

	protected Map<String, Object> getRequestMap() {
		return getExternalContext().getRequestMap();
	}

	protected Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	protected Object getBean(Class<?> beanClass, String beanName) {
		return getApplicationMap().get(beanName);
	}

	protected Object getBean(String objectName) {
		ELContext elContext = getElContext();
		return elContext.getELResolver().getValue(elContext, null, objectName);
	}

	protected void storeOnSession(String key, Object object) {
		getSessionMap().put(key, object);
	}

	protected void storeOnRequest(String key, Object object) {
		getRequestMap().put(key, object);
	}

	protected Map<String, String> getRequestParameterMap() {
		return getExternalContext().getRequestParameterMap();
	}

	protected ValueExpression getValueExpression(String expression) {
		ELContext elContext = getElContext();
		return getApplication().getExpressionFactory().createValueExpression(elContext, expression, null);
	}

	protected Object getValue(String expression) {
		ELContext elContext = getElContext();
		return getValueExpression(expression).getValue(elContext);
	}

	protected void setValue(String expression, Object value) {
		ELContext elContext = getElContext();
		getValueExpression(expression).setValue(elContext, value);
	}

	protected boolean isPostBack() {
		FacesContext context = getFacesContext();
		return getFacesContext().getRenderKit().getResponseStateManager().isPostback(context);
	}

	protected Lifecycle getLifecycle() {
		String lifecycleId = getExternalContext().getInitParameter("javax.faces.LIFECYCLE_ID");
		if (lifecycleId == null || lifecycleId.length() == 0) {
			lifecycleId = LifecycleFactory.DEFAULT_LIFECYCLE;
		}
		LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
		return lifecycleFactory.getLifecycle(lifecycleId);
	}

	protected void navigate(String outcome) {
		FacesContext context = getFacesContext();
		context.getApplication().getNavigationHandler().handleNavigation(context, null, outcome);
	}

	protected void erase() {
		erase(getFacesContext().getViewRoot());
	}

	private void erase(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			((EditableValueHolder) component).setSubmittedValue(null);
		}
		Iterator<UIComponent> kids = component.getFacetsAndChildren();
		while (kids.hasNext()) {
			erase(kids.next());
		}
	}

	protected void info(String summary) {
		addMessage(null, FacesMessage.SEVERITY_INFO, summary);
	}

	protected void info(UIComponent component, String summary) {
		addMessage(component.getClientId(getFacesContext()), FacesMessage.SEVERITY_INFO, summary);
	}

	protected void warn(String summary) {
		addMessage(null, FacesMessage.SEVERITY_WARN, summary);
	}

	protected void warn(UIComponent component, String summary) {
		addMessage(component.getClientId(getFacesContext()), FacesMessage.SEVERITY_WARN, summary);
	}

	protected void error(String summary) {
		addMessage(null, FacesMessage.SEVERITY_ERROR, summary);
	}

	protected void error(Throwable e) {
		String message = (null != e.getMessage() ? e.getMessage() : e.toString());
		addMessage(null, FacesMessage.SEVERITY_ERROR, message);
	}

	protected void error(UIComponent component, String summary) {
		addMessage(component.getClientId(getFacesContext()), FacesMessage.SEVERITY_ERROR, summary);
	}

	protected void fatal(String summary) {
		addMessage(null, FacesMessage.SEVERITY_FATAL, summary);
	}

	protected void fatal(UIComponent component, String summary) {
		addMessage(component.getClientId(getFacesContext()), FacesMessage.SEVERITY_FATAL, summary);
	}

	protected void addMessage(String cientId, Severity severity, String message) {
		getFacesContext().addMessage(cientId, new FacesMessage(severity, message, null));
		storeOnRequest("showMessages", true);
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	protected String getResource(String key) {
		FacesContext context = getFacesContext();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "resource");
		return bundle.getString(key);
	}
	
}
