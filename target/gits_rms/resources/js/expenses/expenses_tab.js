var cnt = parseFloat("0");
var divCnt = parseInt("0");
var prevAmount = parseFloat("0");
var divId = parseInt("0");
var tableNode = "";
var tbody = "";
var tableRow = "";
var tableData = "";
var tableRow1 = "";
var tableData1 = "";
var sub = "true";
var editModeUpdate = false;
var count = 0;
var delArray = new Array();
var arrIndex = 0;
var dbArray = new Array();


function disableit()
{
   var autoCompleter = document.getElementById("submitAction");
   autoCompleter.disable();
}

function validateForm()
{
   var eDtValue = document.getElementById('empExpenses.expensesDate').value;
   var eExpValue = document.getElementById("expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId").value;
   var eProValue = document.getElementById('empExpenses.hcmoProjectId.projectId').value;
   var eExpAmtValue = document.getElementById('expenseDetails.amount').value;

   if (eDtValue == null || eDtValue == "" || eDtValue.length == 0)
   {
      alert('Expense Date is required');
      sub = "false";
      return false;
   }
   if (eExpValue == null || eExpValue == "" || eExpValue.length == 0)
   {
      alert('Expense Type is required');
      sub = "false";
      return false;
   }
   if (eExpAmtValue == null || eExpAmtValue == "" || eExpAmtValue.length == 0)
   {
      alert('Amount is required');
      sub = "false";
      return false;
   }
   if (eProValue == null || eProValue == "" || eProValue.length == 0)
   {
      alert('Project Name is required');
      sub = "false";
      return false;
   }
   if (eProValue.length != 0 && eExpAmtValue.length != 0 && eExpValue.length != 0 && eDtValue.length != 0)
   {
      sub = "true";
      document.getElementById('submitAction').disabled = "false";
   }
   return true;
}

function formDetails()
{
}

function doInsert()
{
   var pageCount = document.getElementById('pageCount').value;
   prevAmount = 0;

   pageCount++;
   validateForm();
   if (sub != "false")
   {
      if (editModeUpdate == false)
      {
         var doc = document;
         var eDtValue = document.getElementById('empExpenses.expensesDate').value;
         var eExpValue = document.getElementById('expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId').value;
         var eProValue = document.getElementById('empExpenses.hcmoProjectId.projectId').value;
         var eExpAmtValue = doc.getElementById('expenseDetails.amount').value;
         var eExpNoteValue = doc.getElementById('expenseDetails.note').value;
         var eExpDescValue = doc.getElementById('expenseDetails.description').value;
         var totalAmt = doc.getElementById('empExpenses.totalAmount').value;


         var pName = document.getElementById('empExpenses.hcmoProjectId.projectId');
         var eType = document.getElementById('expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId');

         var pIndex = pName.selectedIndex;
         var pselval = pName.options[pIndex].value;
         var pseltext = pName.options[pIndex].text;

         var eIndex = eType.selectedIndex;
         var eselval = eType.options[eIndex].value;
         var eseltext = eType.options[eIndex].text;

         if (totalAmt == 0.00)
         {
            divCnt = 0;
            cnt = 0;
            arrIndex = 0;
         }
         prevAmount = eExpAmtValue;
         var f = doc.getElementById('test');
         divId = 'div' + divCnt;


         var newDiv = doc.createElement('div');
         newDiv.setAttribute('id', divId);
         var eDtLbl = doc.createTextNode("ExpenseDate:");
         var eDt = doc.createElement("input");
         eDt.name = "expensesDate";
         eDt.readOnly = true;
         var nweDateStr = eDtValue;
         eDt.value = nweDateStr;
         eDt.size = '13';
         tableNode = doc.createElement("table");
         tbody = doc.createElement("TBODY");
         tableRow = doc.createElement("tr");
         tableData = doc.createElement("td");

         //var eExpTypeLbl = doc.createTextNode("ExpenseType:");
         var eExpword = doc.createElement("input");
         eExpword.type = "hidden";
         eExpword.name = "expid";
         eExpword.id = "expid";
         eExpword.readOnly = true;
         eExpword.value = eselval;
         eExpword.size = '13';

         var eExpName = doc.createElement("input");
         eExpName.name = "hcmoExpensesTypeId";
         eExpName.readOnly = true;
         eExpName.value = eseltext;
         eExpName.size = '13';

         //var eExpAmtLbl = doc.createTextNode("Amount:");
         var eExpAmtName = doc.createElement("input");
         eExpAmtName.name = "amount";
         eExpAmtName.readOnly = true;
         eExpAmtName.value = eExpAmtValue;
         eExpAmtName.size = '13';

         //var eExpNoteLbl = doc.createTextNode(" Note  :");
         var eExpNoteName = doc.createElement("input");
         eExpNoteName.name = "note";
         eExpNoteName.readOnly = true;
         //eExpNoteName.setAttribute("cols","10");
         //eExpNoteName.setAttribute("rows","3");
         eExpNoteName.value = eExpNoteValue;
         //eExpNoteName.setAttribute("maxLength","eExpDescValue");
         eExpNoteName.size = '14';

         //var eExpDescLbl = doc.createTextNode(" Description  :");
         var eExpDescName = doc.createElement("input");
         eExpDescName.name = "description";
         eExpDescName.readOnly = true;
         //eExpNoteName.setAttribute("cols","10");
         //eExpNoteName.setAttribute("rows","3");
         eExpDescName.value = eExpDescValue;
         //eExpNoteName.setAttribute("maxLength","eExpDescValue");
         eExpDescName.size = '14';

         var eProeName = doc.createElement("input");
         eProeName.name = "projectId";
         eProeName.readOnly = true;
         eProeName.value = pseltext;
         eProeName.size = '12';

         var eProWord = doc.createElement("input");
         eProWord.type = "hidden";
         eProWord.id = "proId";
         eProWord.name = "proId";
         eProWord.readOnly = true;
         eProWord.value = pselval;
         eProWord.size = '12';

         var eBr = doc.createElement("br");

         var eDeleteButton = doc.createElement("input");
         eDeleteButton.setAttribute("type", "button");
         eDeleteButton.setAttribute("value", "   Delete   ");
         eDeleteButton.setAttribute("name", "Delete");
         eDeleteButton.setAttribute("size", "12");
         eDeleteButton.setAttribute('onClick', "deleteRow('" + divId + "')");


         var eEditButton = doc.createElement("input");
         eEditButton.setAttribute("type", "button");
         eEditButton.setAttribute("value", "       Edit       ");
         eEditButton.setAttribute("name", "Edit");
         eEditButton.setAttribute("size", "12");
         eEditButton.setAttribute('onClick', "editRow('" + divId + "')");

         if (parseInt(divCnt) == 0)
         {

            tableRow1 = doc.createElement("tr");
            tableRow1.colSpan = "8";
            tableRow1.rowSpan = "8";
            tableRow1.width = "80%";

            tableData1 = doc.createElement("td");
            tableData2 = doc.createElement("td");
            tableData3 = doc.createElement("td");
            tableData4 = doc.createElement("td");
            tableData5 = doc.createElement("td");
            tableData6 = doc.createElement("td");

            var table_headers = new Array();
            table_headers[0] = "Expense Date";
            table_headers[1] = "Project Name";
            table_headers[2] = "Expense Type";
            table_headers[3] = "Amount";
            table_headers[4] = "Note";
            table_headers[5] = "Description";
            table_headers[6] = "Edit";
            table_headers[7] = "Delete";

            var table = document.createElement('TABLE');
            table.setAttribute('id', 'dataTable');
            table.border = '0';
            var tbdy = document.createElement('TBODY');
            table.appendChild(tbdy);
            for (var zxc1 = 0; zxc1 < 1; zxc1++)
            {
               var tr = document.createElement('TR');
               tr.style.backgroundColor = 'black';
               tr.style.color = 'white';

               tbdy.appendChild(tr);
               for (var zxc2 = 0; zxc2 < 8; zxc2++)
               {
                  var td = document.createElement('TD');
                  td.width = '90';
                  td.appendChild(document.createTextNode(table_headers[zxc2]));
                  tr.appendChild(td);
               }
            }
            tableData1.appendChild(table);
            newDiv.appendChild(eDt);
            newDiv.appendChild(eProWord);
            newDiv.appendChild(eExpword);
            newDiv.appendChild(eProeName);
            newDiv.appendChild(eExpName);
            newDiv.appendChild(eExpAmtName);
            newDiv.appendChild(eExpNoteName);
            newDiv.appendChild(eExpDescName);
            newDiv.appendChild(eEditButton);
            newDiv.appendChild(eDeleteButton);
            newDiv.appendChild(eBr);
            tableRow1.appendChild(tableData1);
            tbody.appendChild(tableRow1);
            tableNode.appendChild(tbody);
            tableData.appendChild(newDiv);
            tableData.colspan = "20";
            tableData.rowspan = "20";
            tableRow.appendChild(tableData);
            tableRow.width = "80%";
            tbody.appendChild(tableRow);
            tableNode.width = "100%";
            tableNode.border = "0";
            tableNode.appendChild(tableRow);
            f.appendChild(tableNode);
            divCnt++;
         }
         else
         {
            newDiv.appendChild(eDt);
            newDiv.appendChild(eProWord);
            newDiv.appendChild(eExpword);
            newDiv.appendChild(eProeName);
            newDiv.appendChild(eExpName);
            newDiv.appendChild(eExpAmtName);
            newDiv.appendChild(eExpNoteName);
            newDiv.appendChild(eExpDescName);
            newDiv.appendChild(eEditButton);
            newDiv.appendChild(eDeleteButton);
            tableData.appendChild(newDiv);
            tableData.colspan = "20";
            tableData.rowspan = "20";
            tableRow.appendChild(tableData);
            tableRow.width = "80%";
            tbody.appendChild(tableRow);
            tableNode.width = "100%";
            tableNode.border = "0";
            tableNode.appendChild(tableRow);
            f.appendChild(tableNode);
            divCnt++;
         }

         totalAmountSum(divCnt);
         document.getElementById('empExpenses.expensesDate').value = "";
         document.getElementById('empExpenses.hcmoProjectId.projectId').value = "";
         document.getElementById('expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId').value = "";
         doc.getElementById('expenseDetails.amount').value = "";
         doc.getElementById('expenseDetails.note').value = "";
         doc.getElementById('expenseDetails.description').value = "";
         doc.getElementById('submitAction').disabled = false;
         return true;
      }
   }

} /********************Deleting the row from the Grid*********************/

function deleteRow(divId)
{
   var d1 = document.getElementById('test');
   document.getElementById('delete').value = 1;
   var editHid = document.getElementById('edit').value;

   var top_level_div = document.getElementById('test');
   var count = top_level_div.getElementsByTagName('div').length;
   if (count == 1)
   {
      document.getElementById('dataTable').style.display = 'none';
   }
   document.getElementById('empExpenses.expensesDate').value = "";
   if (editHid == "1")
   {
      var d2 = document.getElementById(divId);
      var p = d2.parentNode;
      var nodes = d2.childNodes;
      for (var i = 0; i < nodes.length; ++i)
      {
         var nameCheck = nodes[i].name;
         if (nameCheck != "" && nameCheck != null)
         {
            if (nameCheck == "amount")
            {
               var amountValue = nodes[i].value;
               var formatAmt = document.getElementById('empExpenses.totalAmount').value - amountValue;
               document.getElementById('empExpenses.totalAmount').value = formatAmt.toFixed(2);
            }
         }

      }
      p.removeChild(d2);
      document.getElementById('expenseDetails.amount').value = "";
      document.getElementById('expenseDetails.note').value = "";
      document.getElementById('expenseDetails.description').value = "";
   }
   else
   {
      var divValue = document.getElementById(divId);
      var nodes = divValue.childNodes;
      var p = divValue.parentNode;
      for (var i = 0; i < nodes.length; i++)
      {
         var nameCheck = nodes[i].name;
         if (nameCheck != "" && nameCheck != null)
         {
            if (nameCheck == "amount")
            {
               var amountValue = nodes[i].value;
               var formatAmt = document.getElementById('empExpenses.totalAmount').value - amountValue;
               document.getElementById('empExpenses.totalAmount').value = formatAmt.toFixed(2);
            }
         }
      }
      var parent = divValue.parentNode;
      parent.removeChild(divValue);
      delArray[arrIndex] = divId;
      arrIndex++;
   }
   if (divCnt == 1)
   {
      var p2 = tableData1.parentNode;
      p2.removeChild(tableData1);
      p2.removeChild(tableData2);
      p2.removeChild(tableData3);
      p2.removeChild(tableData4);
      p2.removeChild(tableData5);
      var f = document.getElementById('test');
   }
}

function formValidation()
{
   if (myTrim(document.getElementById('empExpenses.expensesDate').value) == '')
   {
      document.getElementById('empExpenses.expensesDate').focus;
      return false;
   }
}


function editRow(divId)
{

   var top_level_div = document.getElementById('test');
   var count = top_level_div.getElementsByTagName('div').length;
   if (count == 1)
   {
      document.getElementById('dataTable').style.display = 'none';
   }

   var eHiddenValue = document.getElementById('edit');
   eHiddenValue.value = '1';
   var divValue = document.getElementById(divId);
   var nodes = divValue.childNodes;
   document.getElementById('divEdit').value = divId.id;

   for (var i = 0; i < nodes.length; ++i)
   {
      var nameCheck = nodes[i].name;
      if (nameCheck != "" && nameCheck != null)
      {
         if (nameCheck == "amount")
         {
            var amountValue = nodes[i].value;
            document.getElementById('expenseDetails.amount').value = amountValue;
            var formatAmt = document.getElementById('empExpenses.totalAmount').value - amountValue;
            document.getElementById('empExpenses.totalAmount').value = formatAmt.toFixed(2);
         }
         if (nameCheck == "expensesDate")
         {
            var expensesDateValue = nodes[i].value;
            document.getElementById('empExpenses.expensesDate').value = expensesDateValue;
         }
         if (nameCheck == "description")
         {
            var descriptionValue = nodes[i].value;
            document.getElementById('expenseDetails.description').value = descriptionValue;
         }
         if (nameCheck == "note")
         {
            var noteValue = nodes[i].value;
            document.getElementById('expenseDetails.note').value = noteValue;
         }
         if (nameCheck == "expid")
         {
            var expTypeValue = nodes[i].value;
            document.getElementById('expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId').value = expTypeValue;
         }
         if (nameCheck == "proId")
         {
            var proValue = nodes[i].value;
            document.getElementById('empExpenses.hcmoProjectId.projectId').value = proValue;
         }
      }
   }
   var parent = divValue.parentNode;
   parent.removeChild(divValue);
   delArray[arrIndex] = divId;
   arrIndex++;
   if (divCnt == 1)
   {
      var p2 = tableData1.parentNode;
      p2.removeChild(tableData1);
      p2.removeChild(tableData2);
      p2.removeChild(tableData3);
      p2.removeChild(tableData4);
      p2.removeChild(tableData5);
      var f = document.getElementById('test');
   }
}

function totalAmountSum(divCnt)
{
   if (document.getElementById('edit').value == 1)
   {
      var amount = prevAmount;
      var formatAmt = parseFloat(document.getElementById('empExpenses.totalAmount').value) + parseFloat(document.getElementById('expenseDetails.amount').value);
      document.getElementById('empExpenses.totalAmount').value = formatAmt.toFixed(2);
      document.getElementById('edit').value = 0;
   }
   else
   {
      var doc = document;
      var amount = doc.getElementById('expenseDetails.amount').value;
      if (document.getElementById('delete').value == 1)
      {
         var formatAmt = parseFloat(doc.getElementById('empExpenses.totalAmount').value) + parseFloat(amount);
         document.getElementById('empExpenses.totalAmount').value = formatAmt.toFixed(2);
      }
      else
      {
         var testamt = parseFloat(document.getElementById('empExpenses.totalAmount').value) + parseFloat(document.getElementById('expenseDetails.amount').value);
         var formatAmt = parseFloat(document.getElementById('empExpenses.totalAmount').value) + parseFloat(document.getElementById('expenseDetails.amount').value);
         document.getElementById('empExpenses.totalAmount').value = formatAmt.toFixed(2);
      }
      doc.getElementById('expenseDetails.amount').value = "";
      doc.getElementById('expenseDetails.note').value = "";
      doc.getElementById('expenseDetails.description').value = "";
      doc.getElementById('submitAction').disabled = false;
      return;
   }
}


function futureDateValidation()
{
   var right_now = new Date();
}

function myTrim(text)
{
   var newStr = "";
   for (var i = 0; i < text.length; i++)
   {
      if (text.charCodeAt(i) != 32)
      {
         newStr += text.charAt(i);
      }
   }
   return newStr;
}

function amountValidation()
{
   var doc = document;
   var tempAmount = doc.getElementById('expenseDetails.amount').value;
   var amountFlag;

   for (var i = 0; i < tempAmount.length; i++)
   {
      if (!(tempAmount.charAt(i) >= 0 || tempAmount.charAt(i) <= 9 || tempAmount.charAt(i) == "."))
      {
         amountFlag = "true";
      }
      else
      {
         if (tempAmount.indexOf(".") == -1)
         {
            tempAmount = tempAmount + ".00";
            doc.getElementById('expenseDetails.amount').value = tempAmount;
         }

      }
   }
   if (amountFlag)
   {
      doc.getElementById('expenseDetails.amount').value = "";
   }
}

function clearForm()
{
   for (var i = 0; i < divCnt; i++)
   {
      var divValue = document.getElementById('div' + i);
      var nodes = divValue.childNodes;
      var p = divValue.parentNode;
      p.removeChild(divValue);
   }
   if (expensedateArray == null && expensedateArray.length == 0)
   {
      document.getElementById('submitAction').disabled = true;
   }
   var tabValue = document.getElementsByTagName("dataTable")[0];
   var p1 = tabValue.parentNode;
   p1.removeChild(tabValue);
   return true;
}

function amountValForEdit()
{
   var doc = document;
   var amt = document.getElementById("empExpenses.totalAmount").value;
   var amount = document.getElementById("expenseDetails.amount").value;
   var tempAmount = doc.getElementById('expenseDetails.amount').value;
   var amountFlag;
   for (var i = 0; i < tempAmount.length; i++)
   {
      if (!(tempAmount.charAt(i) >= 0 || tempAmount.charAt(i) <= 9 || tempAmount.charAt(i) == "."))
      {
         amountFlag = "true";
      }
      else
      {
         if (tempAmount.indexOf(".") == -1)
         {
            tempAmount = tempAmount + ".00";
            doc.getElementById('expenseDetails.amount').value = tempAmount;
         }

      }
   }
   if (amountFlag)
   {
      doc.getElementById('expenseDetails.amount').value = "";
   }
}

function expSubmitAction()
{
   var desArray = "";
   var amountArray = "";
   var noteArray = "";
   var expensedateArray = "";
   var expenseTypeArray = "";
   var projectIdArray = "";

   var doc = document;
   var eExpDescValue = doc.getElementById('expenseDetails.description').value;
   var tempAMount = doc.getElementById('empExpenses.totalAmount').value;
   doc.getElementById('totalAmt').value = tempAMount;
   var f = doc.getElementById('test');
   for (var k = 0; k < divCnt; k++)
   {
      if (document.getElementById('div' + k) != null)
      {
      }
   }

   if (delArray.length > 0)
   {
      for (var j = 0; j < divCnt; j++)
      {
         var divEl = 'div' + j;
         if (document.getElementById('div' + j) != null)
         {
            var d2 = document.getElementById('div' + j);
            var nodes = d2.childNodes;
            if (d2.hasChildNodes() == true)
            {
            }
            else
            {
            }
            for (var i = 0; i < nodes.length - 1; i++)
            {
            }
            for (var i = 0; i < 8; i++)
            {
               var nameCheck = nodes[i].name;
               if (nameCheck == "expensesDate")
               {
                  expensedateArray += nodes[i].value + ',';
               }
               if (nameCheck == "projectId")
               {
                  projectIdArray += nodes[i].value + ',';
               }
               if (nameCheck == "hcmoExpensesTypeId")
               {
                  expenseTypeArray += nodes[i].value + ',';
               }
               if (nameCheck == "amount")
               {
                  amountArray += nodes[i].value + ',';
               }
               if (nameCheck == "note")
               {
                   if(nodes[i].value==""||nodes[i].value==null){
                   nodes[i].value=' ';
                   }
                   noteArray += nodes[i].value + ',';
               }
               if (nameCheck == "description")
               {
                   if(nodes[i].value==""||nodes[i].value==null){
                   nodes[i].value=' ';
                   }
                   desArray += nodes[i].value + ',';
               }

            }
         }

      }

   }
   else
   {
      for (var j = 0; j < divCnt; j++)
      {
         var divEl = 'div' + j;
         var d2 = document.getElementById(divEl);
         var nodes = d2.childNodes;
         if (d2.hasChildNodes() == true)
         {
         }
         else
         {
         }
         for (var i = 0; i < nodes.length - 1; i++)
         {
         }
         for (var i = 0; i < 8; i++)
         {
            var nameCheck = nodes[i].name;
            if (nameCheck == "expensesDate")
            {
               expensedateArray += nodes[i].value + ',';
            }
            if (nameCheck == "projectId")
            {
               projectIdArray += nodes[i].value + ',';
            }
            if (nameCheck == "hcmoExpensesTypeId")
            {
               expenseTypeArray += nodes[i].value + ',';
            }
            if (nameCheck == "amount")
            {
               amountArray += nodes[i].value + ',';
            }
            if (nameCheck == "note")
            {
            if(nodes[i].value==""||nodes[i].value==null){
         	nodes[i].value=' ';
            }
            noteArray += nodes[i].value + ',';
            }
            if (nameCheck == "description")
            {
	        if(nodes[i].value==""||nodes[i].value==null){
	       	nodes[i].value=' ';
	        }
	        desArray += nodes[i].value + ',';
            }
         }

      }
   }
   doc.getElementById('desArrayHide').value = desArray;
   doc.getElementById('noteArrayHide').value = noteArray;
   doc.getElementById('amountArrayHide').value = amountArray;
   doc.getElementById('expDateArrayHide').value = expensedateArray;
   doc.getElementById('expTypeArrayHide').value = expenseTypeArray;
   doc.getElementById('proNameArrayHide').value = projectIdArray;

   divCnt = parseInt("0");
   divId = 'div' + divCnt;
   return true;

}



