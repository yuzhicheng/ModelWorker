package org.xadzkd.tool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ClickColumn extends TagSupport{
	private String label;
	private String value;
	private String onclick;
	public String getLabel() {
		return label;
	}
	
	@Override
	public int doStartTag() throws JspException {
		if(!(this.getParent() instanceof Table)){
			throw new JspException("ClickColumn must be inside Table");
		}
		Map<String,String> column = new HashMap<String,String>();
		
		column.put("label", label);
		column.put("value", value);
		column.put("onclick", onclick);
		
		Table table = (Table) this.getParent();
		table.getClickColumns().add(column);
		return super.doStartTag();
	}
	
	
	
	
	
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	

}
