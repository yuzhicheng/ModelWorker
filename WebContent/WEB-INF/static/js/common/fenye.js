 window.onload = function(){
        page = new Page(10,'senfe','group_one'); 
        };
    
        function Page(iAbsolute,sTableId,sTBodyId,page)
        {
          this.absolute = iAbsolute; //每页最大记录数
          this.tableId = sTableId;
          this.tBodyId = sTBodyId;
          this.rowCount = 0;//记录数
          this.pageCount = 0;//页数
          this.pageIndex = 0;//页索引
          this.oTable = null;//表格引用
          this.oTBody = null;//要分页内容
          this.dataRows = 0;//记录行引用
          this.oldTBody = null;
          this.init(); //初始化;
        };
        Page.prototype.init = function(){
          this.oTable = document.getElementById(this.tableId);//获取table引用
          this.oTBody = this.oTable.tBodies[this.tBodyId];//获取tBody引用
          this.dataRows = this.oTBody.rows;
          this.rowCount = this.dataRows.length;
          try{
          this.absolute = (this.absolute <= 0) || (this.absolute>this.rowCount) ? this.rowCount : this.absolute;
          this.pageCount = parseInt(this.rowCount%this.absolute == 0
          ? this.rowCount/this.absolute : this.rowCount/this.absolute+1);
          }catch(exception){}
          this.updateTableRows();
        };
        Page.prototype.GetBar=function(obj)
        {
          var bar= document.getElementById(obj.id);
          bar.innerHTML= "每页"+this.absolute+"条/共"+this.rowCount+"条";// 第2页/共6页 首页 上一页 1 2 3 4 5 6 下一页 末页
        }

        Page.prototype.nextPage = function(){
          if(this.pageIndex + 1 < this.pageCount){
          this.pageIndex += 1;
          this.updateTableRows();}
        };

        Page.prototype.prePage = function(){
          if(this.pageIndex >= 1){
          this.pageIndex -= 1;
          this.updateTableRows();}
        };

        Page.prototype.firstPage = function(){
          if(this.pageIndex != 0){
          this.pageIndex = 0;
          this.updateTableRows();}
        };

       Page.prototype.lastPage = function(){
          if(this.pageIndex+1 != this.pageCount){
          this.pageIndex = this.pageCount - 1;
          this.updateTableRows();}
       };

       Page.prototype.aimPage = function(){
         var abc = document.getElementById("pageno");
         var iPageIndex = abc.value;
         var iPageIndex = iPageIndex*1;
         if(iPageIndex > this.pageCount-1){
         this.pageIndex = this.pageCount -1;}
         else if(iPageIndex < 0){
         this.pageIndex = 0;}
         else{
         this.pageIndex = iPageIndex-1;}
         this.updateTableRows();
       };

       Page.prototype.updateTableRows = function(){
         var iCurrentRowCount = this.absolute * this.pageIndex;
         var iMoreRow = this.absolute+iCurrentRowCount > this.rowCount ? this.absolute+iCurrentRowCount - this.rowCount : 0;
         var tempRows = this.cloneRows();
         var removedTBody = this.oTable.removeChild(this.oTBody);
         var newTBody = document.createElement("TBODY");
         newTBody.setAttribute("id", this.tBodyId);
         for(var i=iCurrentRowCount; i < this.absolute+iCurrentRowCount-iMoreRow; i++){
         newTBody.appendChild(tempRows[i]);
       }
         this.oTable.appendChild(newTBody);
         this.dataRows = tempRows;
         this.oTBody = newTBody;
         var divFood = document.getElementById("divFood");//分页工具栏
         divFood.innerHTML="";
         var rightBar = document.createElement("divFood");
         rightBar.setAttribute("display","");
         rightBar.setAttribute("float","left");
         rightBar.innerHTML="第"+(this.pageIndex+1)+"页/共"+this.pageCount+"页";
         var isOK="Y";
         var cssColor="";
         divFood.appendChild(rightBar);////页脚显示分页结
         };
         Page.prototype.cloneRows = function(){
         var tempRows = [];
         for(var i=0; i<this.dataRows.length; i++){
         tempRows[i] = this.dataRows[i].cloneNode(1);
         }
         return tempRows;
        };