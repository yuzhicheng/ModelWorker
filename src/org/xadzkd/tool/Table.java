package org.xadzkd.tool;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class Table extends BodyTagSupport{
	private String sort;//排序方式  desc/asc
	private String sortName ;//排序名称 属性名
	 //
	 // <taglib:column property="id" label="编号" />
	 //
	private String uri;
	private int page;
	private List<Map<String,Object>> columns = new ArrayList<Map<String,Object>>();//保存列表信息
	private List<Map<String,String>> clickColumns = new ArrayList<Map<String,String>>();//保存点击列表的信息
	private String flush;//在触发事件之后是否刷新


	/** 存储数据，可能为集合也可能是数组*/
	private Object items;
	private int display_num;
	


	@Override
	public int doStartTag() throws JspException {
		columns.clear();
		clickColumns.clear();
		return super.doStartTag();
	}


	@Override
	public int doAfterBody() throws JspException {
		try{
			BodyContent bc = getBodyContent();
			JspWriter out = bc.getEnclosingWriter();
			
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			
			//处理排序
			sort = (String)request.getParameter("sort");//获取当前排序方式
			sort = "desc".equals(sort) ? "desc" : "asc";//默认为正序
			
			String tmp = (String) request.getParameter("page");
			tmp = tmp == null ? "1" : tmp;
			page = Integer.parseInt(tmp);//获取当前页数
			
			sortName = (String) request.getParameter("sortName");
			
			ArrayList<Object> display_items = getPageItem();
			
			flush = "true".equals(flush) ? "true" : "false";
			int pageNum = getPageNum();
			//排序操作
			//...
			
			
			
			//写入js
			out.println("<script type='text/javascript'>");
			out.println(
					"var this_page = " + page + " + 2;\n"
					+ "var pageNum = " + pageNum + ";\n"
					+"$(document).ready(function(){\n"
					+ "computeWidth();\n"
					+ "var each_height = $('.myThead').height() + 2;\n"
					+ "$('#nagv').css({top:(each_height *"+ (display_num + 1) + " + 10)});\n"
					+ "$('#nagv').width($('.myThead').width());\n"
					+ "pageCompute();\n"
					+ "});\n"
					+ "function computeWidth(){\n"
					+ "var width = 0;\n"
					+ "$('.myThead').find('div').not('.hidden').each(function(){\n"
					+ "width = $(this).width() + 2 + width;\n"
					+ "});\n"
					+ "width = width +1;\n"
					+ "$('.myThead').width(width);\n"
					+ "$('.myTBody').width(width);\n"
					+ "}\n"
					+ "\n"
					+ "function pageCompute(){//计算页数\n"
					+ "var true_page = this_page -2;//\n"
					+ "var pageNo = $('#nagv').children(':not(span) ');\n"
					+ "if(pageNum <= 10){\n"
					+ "$('#middle').hide();\n"
					+ "var i;\n"
					+ "for(i = (pageNum + 1) ; i <= 10; i++){\n"
					+ "$(pageNo[i + 1]).hide();\n"
					+ "}\n"
					+ "$(pageNo[true_page + 1]).css({color: 'red',cursor:'auto'});\n"
					+ "return;\n"
					+ "}\n"
					+ "//改变最后几页\n"
					+ "{\n"
					+ "var i;\n"
					+ "for(i=6; i <= 10; i++){\n"
					+ "$(pageNo[i+1]).text(pageNum - (10 - i));\n"
					+ "}\n"
					+ "}\n"
					+ "if(true_page >= 1 && true_page <= 3 ){\n"
					+ "$($('#nagv').children(':not(span) ')[this_page -1]).css({color: 'red',cursor:'auto'});\n"
					+ "return;\n"
					+ "}\n"
					+ "if(pageNum - true_page < 10){\n"
					+ "var j;\n"
					+ "for(j=1; j < 10; j++){\n"
					+ "$(pageNo[10 - j + 1]).text(pageNum - j);\n"
					+ "console.info(pageNum - j);"
					+ "}\n"
					+ "if(pageNum != true_page){"
					+ "$(pageNo[true_page % 10 + 1]).css({color: 'red',cursor:'auto'});"
					+ "}else{\n"
					+ "$(pageNo[10 + 1]).css({color: 'red',cursor:'auto'});\n"
					+ "}"
					+ "$('#middle').hide();\n"
					+ "return;\n"
					+ "}\n"
					+ "var gap = pageNum - 5;//差距值\n"
					+ "if(true_page> 3 && true_page <= gap){//在3到page-5之间\n"
					+ "$(pageNo[2]).text(true_page - 2);\n"
					+ "$(pageNo[3]).text(true_page - 1);\n"
					+ "$(pageNo[4]).text(true_page); $(pageNo[4]).css({color: 'red',cursor:'auto'});\n"
					+ "$(pageNo[5]).text(true_page +1);\n"
					+ "$(pageNo[6]).text(true_page + 2);\n"
					+ "if(true_page == gap){\n"
					+ "$('#middle').hide();\n"
					+ "}\n"
					+ "return;\n}\n"
					+ "{"
					+ "var No = pageNum - true_page;\n"
					+ "$(pageNo[11 - No]).css({color: 'red',cursor:'auto'});\n"
					+ "}\n"
					+ "}\n"
					+ "var begin_x;\n"
					+ "var move = false;\n"
					+ "var mydiv;\n"
					+ "var others;\n"
					+ "var target_item;\n"
					+ "function movedown(target){\n"
					+ "target_item = target;\n"
					+ "move = true;\n"
					+ "begin_x = event.x;\n"
					+ "$('.myThead').css({cursor:'ew-resize'});\n"
					+ "mydiv = $(target).parent();\n"
					+ "others = $('.myThead').children().not('.hidden').length - 1;\n"
					+ "}\n"
					+ "function moveover(target){\n"
					+ "if(move == true){\n"
					+ "var orgin_width = $('.myThead').width();\n"
					+ "var move_width = event.x - begin_x;\n"
					+ "var now_width = mydiv.width() + move_width;\n"
					+ "$($(target_item).parent().attr('target')).width(now_width);\n"
					+ "var other_off = move_width / others;\n"
					+ "$(target_item).parent().siblings().each(function(){\n"
					+ "$($(this).attr('target')).width($(this).width() - other_off);\n"
					+ "});\n"
					+ "\n"
					+ "var orginal_width = $('.myThead').width();\n"
					+ "computeWidth();\n"
					+ "begin_x = event.x;\n"
					+ "var now_width = $('.myThead').width();\n"
					+ "if(orgin_width != now_width){//因舍入导致的宽度不齐\n"
					+ "var last_width = $($('.myThead').children(':last').attr('target')).width();\n"
					+ "$($('.myThead').children(':last').attr('target')).width(last_width + orgin_width - now_width);\n"
					+ "computeWidth();\n"
					+ "}\n"
					+ "}\n"
					+ "}\n"
					+ "function moveup(){\n"
					+ "move = false;\n"
					+ "$('.myThead').css({cursor:'auto'});\n"
					+ "}\n"//变色
					+ "var color ;\n"
					+ "function onMouseOver(target){\n"
					+ "color = $(target).css('background-color');\n"
					+ "$(target).css({background:'#808080'});\n"
					+ "}\n"
					+ "function onMouseOut(target){\n"
					+ "$(target).css({background:color});\n"
					+ "}\n"
					+ "function itemMoveOut(target){\n"
					+ "$(target).parent().parent().animate({\n"
					+ "left:'-100%',\n"
					+ "height:'0'\n"
					+ "},300,function(){\n"
					+ "$(target).parent().parent().hide();\n"
					+ "});\n"
					+ "}\n"
					+ "function clickFirst(){\n"
					+ "if((this_page - 2) != 1){\n"
					+ "location.href= '" + uri + "?page=1'\n"
					+ "}\n"
					+ "}\n"
					+ "function clickFinal(){\n"
					+ "if((this_page -2 ) < pageNum){\n"
					+ "location.href= '"+ uri + "?page='+pageNum;"
					+ "}\n"
					+ "}\n"
					+ "function clickNext(){\n"
					+ "if((this_page -2) < pageNum){\n"
					+ "location.href = '" + uri + "?page=' + (this_page -2 +1);\n"
					+ "}\n"
					+ "}\n"
					+ "function clickPre(){\n"
					+ "if((this_page -2) != 1){\n"
					+ "location.href = '" + uri + "?page=' + (this_page -2 - 1);\n"
					+ "}\n"
					+ "}\n"
					+ "function clickPage(target){\n"
					+ "var toPage = parseFloat($(target).text(),10);\n"
					+ "if((toPage - 2) != this_page){\n"
					+ "location.href = '" + uri + "?page=' + toPage;\n"
					+ "}\n"
					+ "}\n"
					+ "function flush(){\n"
					+ "setTimeout(function(){\n"
					+ "location.reload();\n"
					+ "},500);\n"
					+ "}\n"
					+ "</script>");
			
			
			//硬写入样式
			out.println("<style type='text/css'>\n"
					+ ".myTable{\n"
					+ "position:relative;\n"
					+ "}\n"
					+ ".myThead{\n"
					+ "height:37px;\n"
					+ "}\n"
					+ ".myThead  div{\n"
					+ "width: 170px;\n"
					+ "background-color: #fe8833;\n"
					+ "text-align: center;\n"
					+ "line-height: 35px;\n"
					+ "height: 35px;\n"
					+ "border:1px solid #D2D2D2;\n"
					+ "float: left;\n"
					+ "-moz-user-select:none;/*火狐*/\n"
					+ "-webkit-user-select:none;/*webkit浏览器*/\n"
					+ "-ms-user-select:none;/*IE10*/\n"
					+ "-khtml-user-select:none;/*早期浏览器*/\n"
					+ "user-select:none;\n"
					+ "}\n"
					+ "\n"
					+ ".myTBody  div{\n"
					+ "width: 170px;\n"
					+ "text-align: center;\n"
					+ "line-height: 35px;\n"
					+ "height: 35px;\n"
					+ "border:1px solid #D2D2D2;\n"
					+ "float: left;\n"
					+ "}\n"
					+ ".myTBody{\n"
					+ "height:37px;\n"
					+ "position: relative;\n"
					+ "}\n"
					+ ".moveDiv{\n"
					+ "width: 10px;\n"
					+ "height: 35px;\n"
					+ "float: right;\n"
					+ "background-color: #D2D2D2;\n"
					+ "cursor:ew-resize;\n"
					+ "margin-right: -3px;\n"
					+ "opacity:0;\n"
					+ "}\n"
					+ ".pageDiv{\n"
					+ "width:25px;\n"
					+ "height:29px;\n"
					+ "line-height: 29px;\n"
					+ "float: left;\n"
					+ "margin-left: 20px;\n"
					+ "cursor:pointer;\n"
					+ "}\n"
					+ "#prePage,#nextPage,#middle,#firstPage,#finalPage{\n"
					+ "height:29px;\n"
					+ "line-height: 29px;\n"
					+ "float: left;\n"
					+ "margin-left: 20px;\n"
					+ "cursor:pointer;\n"
					+ "}\n"
					+ ".hidden{\n"
					+ "display:none;\n"
					+ "}"
					+ "</style>\n"
					+ "");
			
			
			
			
			out.println("<div class='myTable' >");
			out.println("<div class='myThead'  onmouseup='moveup();' onmousemove='moveover(this);'  >");
			//写入表头 .myThead
			int begin = 0;
			for(int i=0; i < columns.size(); i++){
				Map<String,Object> column = columns.get(i);
				
				/** 列头名称 */
				String label = (String)column.get("label");
				/** Java Bean对应属性名 */
				String property = (String)column.get("property");
				
				String hidden = (String)column.get("hidden");
				
				label = label == null ? property: label;
					out.print("<div class='item" + i + ("true".equals(hidden) ? " hidden" : "") + " ' target='.item" + i + "'>");
					out.println(label + "<span class='moveDiv' onmousedown='movedown(this);'>1</span></div>");
					begin = i;
				
			}
			begin ++;
			for(int i=0; i < clickColumns.size();i++){
				Map<String,String> clickColumn = clickColumns.get(i);
				String label = clickColumn.get("label");
				out.println("<div class='item" + begin + "' target='.item" + begin + "'>");
				out.println(label + "<span class='moveDiv' onmousedown='movedown(this);'>1</span></div>");
				begin ++;
				break;
			}
			out.println("</div>");
			
			if(items != null){
				int index = 0;
				begin = 0;
				for(Object obj :  display_items){
					out.println("<tr style='background-color:" + (index % 2 == 0 ? "#e2e2e2;'" : "#f5f5f5;'") + "onMouseOver='onMouseOver(this);' onMouseOut='onMouseOut(this);'>");// 间隔颜色不同
					out.println("<div class='myTBody' style='background-color:" + (index++ % 2 == 0 ? "#e2e2e2;'" : "#f5f5f5;'") + " onMouseOver='onMouseOver(this);' onMouseOut='onMouseOut(this);'>");
					for(int i=0; i < columns.size(); i++){
						// java 反射
						Map<String,Object> column = columns.get(i);
						
						String property = (String)column.get("property");
						String hidden = (String)column.get("hidden");
						/*String getterStyle = toGetterStyle(property);
						
						try{
							String getter = "get" + getterStyle;
							String is = "is" + getterStyle;
							
							Method method = null;
							try{
								method = obj.getClass().getMethod(getter);
							}catch(Exception e){}
							
							if(method == null){
								
								method = obj.getClass().getMethod(is);
							}
							
							method.setAccessible(true);
							
							Object value = method.invoke(obj);
							*/
							Map<String,Object> map = (Map<String,Object>) obj;
							System.out.println(property);
							String value = ""+map.get(property);
							ArrayList<String> dictionary = (ArrayList<String>)column.get("dictionary");
							if(dictionary != null){
								System.out.println(value);
								int value_tmp = Integer.parseInt(value);
								out.println("<div class='item" + i + ("true".equals(hidden) ? " hidden" : "") + "'>" + dictionary.get(value_tmp) + "</div>");
							}else{
								out.println("<div class='item" + i + ("true".equals(hidden) ? " hidden" : "") + "'>" + value + "</div>");
							}
						
						begin = i;
					}
					begin ++;
					//加入相应事件
					out.print("<div class='item" + begin + "'>");
					for(int i=0; i < clickColumns.size();i++){
						
						Map<String,String> clickColumn = clickColumns.get(i);
						String value = clickColumn.get("value");
						String onclick = clickColumn.get("onclick");
						String clickTmp =("true".equals(flush) ? "flush();" : "");
						if(onclick == null){
							out.print("<font onclick='itemMoveOut(this);+" + clickTmp+" '");
						}else{
							out.print("<font onclick='itemMoveOut(this);" + onclick  + clickTmp + "'");
						}
						out.print(" style='cursor:pointer'> " + value + "</font>");
					}
					out.println("</div>\n</div>");
				}
				index ++;
			}
			out.println("<div style='width: 100%; height: 30px;position:absolute;background-color: #b7e0f3' id='nagv'>");
			out.println("<div id='firstPage' onclick='clickFirst();'>首页</div>");
			out.println("<div id='prePage' onclick='clickPre()'>上一页</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>1</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>2</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>3</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>4</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>5</div>");
			out.println("<span id='middle' style='cursor:auto;'>...</span>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>6</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>7</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>8</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>9</div>");
			out.println("<div class='pageDiv' onclick='clickPage(this)'>10</div>");
			out.println("<div id='nextPage' onclick='clickNext()'>下一页</div>");
			out.println("<div id='finalPage' onclick='clickFinal();'>尾页</div>");
			
			out.println("</div>");
			out.println("</div>");
		}catch(IOException ioe){
			throw new JspException("Error:" + ioe.getMessage());
		}
		return SKIP_BODY;
	}
	/**
	 * 选出需要显示的页面
	 * @return
	 */
	private ArrayList<Object> getPageItem(){
		ArrayList<Object> array = new ArrayList<Object>();
		int index = 0;
		int begin = (page - 1) * display_num;
		int end = (page) * display_num;
		for(Object obj : (Iterable) items){
			if(index == end){//到达end
				break;
			}
			if(index >= begin){
				array.add(obj);
			}
			index ++;
		}
		return array;
	}
	
	private int getPageNum(){
		int num =0;
		for(Object obj : (Iterable) items){
			num ++;
		}
		
		int pageNum = num / display_num;
		pageNum = num % display_num == 0 ? pageNum : pageNum + 1;
		
		return pageNum;
	}
	public List<Map<String, Object>> getColumns() {
		return columns;
	}


	public void setColumns(List<Map<String,Object>> columns) {
		this.columns = columns;
	}


	public Object getItems() {
		return items;
	}


	public void setItems(Object items) {
		this.items = items;
	}


	/**
     * 首字母大写
     * @param column
     * @return
     */
    private String toGetterStyle(String column)
    {
        if (column.length() == 1)
            return column.toUpperCase();

        char ch = column.charAt(0);

        return Character.toUpperCase(ch) + column.substring(1);

    }
	
	
	
    public List<Map<String, String>> getClickColumns() {
    	return clickColumns;
    }
    
    
    public void setClickColumns(List<Map<String, String>> clickColumns) {
    	this.clickColumns = clickColumns;
    }


	public int getDisplay_num() {
		return display_num;
	}


	public void setDisplay_num(int display_num) {
		this.display_num = display_num;
	}


	public String getFlush() {
		return flush;
	}


	public void setFlush(String flush) {
		this.flush = flush;
	}


	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}
	
   
}
