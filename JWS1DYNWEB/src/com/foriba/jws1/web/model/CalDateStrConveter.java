package com.foriba.jws1.web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class CalDateStrConveter implements Converter {

	 private String pattern = "dd/MM/yyyy HH:mm";
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if(value!= null && value.length() > 0) {
            try {
                Date date = sdf.parse(value);
                result = sdf.format(date);
            } catch (Exception e) {
            }
        }

        return result;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String result = "";
        String valueStr = (String) value;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (valueStr!= null && valueStr.length() > 0) {
            try {
                Date date = sdf.parse(valueStr);
                result = sdf.format(date);
            } catch (Exception e) {
              
            }
        }
        return result;
	}

}
