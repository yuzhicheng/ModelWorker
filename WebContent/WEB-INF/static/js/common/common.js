$(document).ready(function(){
	//下拉和上升
	$("ul.leftmenu a.dropmenu").click(function(){
		var ulNode = $(this).next("ul");
		ulNode.slideToggle("fast");
	});
	
	
	//�б�ҳ��hover��ɫ
	$(".list_table tr").hover(function(){
		$(this).addClass("rowhover");
	},function(){
		$(this).removeClass("rowhover");
	});
	
});
/**
 * �޸����˵���ָʾͼ��
 */
 /*function changeIcon(chgNode){
	 if(chgNode){
		 if(chgNode.css("background-image").indexOf("collapsed.gif")>=0){
			 chgNode.css("background-image","url('static/image/common/expanded.gif')");
		 }else{
			 chgNode.css("background-image","url('static/image/common/collapsed.gif')");
		 }
	 }
 }*/
 
 /** ȫѡ */
 function doCheckedAll(elem){
	 $(elem).offsetParent("tabel").find("input:checkbox").attr("checked",elem.checked);
 }
 

 /**
  * ���һ�и߼���ѯ����
  */
  function addRowCondition(){
	 $("#advsearchtab tr:last").after("<tr>"+$("#condtemplet tr:last").html()+"</tr>");
  }

 /**
  * ɾ��һ�и߼���ѯ����
  */
  function removeRowCondition(){
	 $("#advsearchtab tr:not(:first):last").remove();
  }

 /**
  * �߼���ѯ��ʾ
  */
  function doAdvancedSearch(){
	  $("#advancedsearchcon").slideToggle()
  }

 /**
  * �߼���ѯ��ʾ
  */
  function resetAdvancedSearch(){
	  $("#advsearchtab tr:not(:first)").remove();
	  $("#groupbrowse .kind").removeClass("kindhover").find("li.kindlihover").removeClass("kindlihover");
	  $("#groupbrowse div.subkind:visible").fadeOut("20");
	  $("#groupbrowse .subkind ul li.sel").removeClass("sel");
  }

/**
  * �߼�����-�����������
  */
  $(function(){
	  //�򿪷������
	  $("#groupbrowse .kind li").bind("click",function(){
		  $(this).addClass("kindlihover").parent(".kind").addClass("kindhover").end().siblings("li").removeClass("kindlihover");
		  var index = $(this).prevAll().length;
		  $("#groupbrowse div.subkind").eq(index).fadeIn("100").siblings("div.subkind").hide();
	  });
	  //�رշ������
	  $("#groupbrowse .subkind span.close").bind("click",function(){
		  $("#groupbrowse .kind").removeClass("kindhover").find("li.kindlihover").removeClass("kindlihover");
		  $("#groupbrowse div.subkind:visible").fadeOut("20");
	  });
	  //���÷������ѡ��
	  $("#groupbrowse .subkind ul li").bind("click",function(){
		  $(this).toggleClass("sel");
	  });
	  //�󶨡��߼������������click
	  $("#advancedsearchcon .advsearchtitle span").bind("click",function(){
		  $("#advancedsearchcon").slideToggle();
	  });
  });