package org.xadzkd.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class Column extends TagSupport{
	private String  property;
	private String 	label;
	private String 	type;
	private String  hidden;
	private ArrayList<String> dictionary;
	private String width;

	@Override
	public int doStartTag() throws JspException {
		
		if(!(this.getParent() instanceof Table)){
			throw new JspException("Column must be inside Table.");
		}
		
		Map<String,Object> column = new HashMap<String,Object>();
		column.put("label", label);
		column.put("property", property);
		column.put("type", type);
		hidden = "true".equals(hidden) ? "true" : "false";
		column.put("hidden", hidden);
		column.put("dictionary", dictionary);
		column.put("width", width);
		
		Table table = (Table) this.getParent();
		table.getColumns().add(column);
		return super.doStartTag();
	}
	
	public int doEndTag()
            throws JspException
    {
        return EVAL_PAGE;
    }

	public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public ArrayList<String> getDictionary() {
		return dictionary;
	}

	public void setDictionary(ArrayList<String> dictionary) {
		this.dictionary = dictionary;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
   
}
