<%@ taglib uri="/birt.tld" prefix="birt" %>
<form name="dateForm" action="historicalData" method="get">
    From:
    <input type="date" name="fromdate" value="${from}" id="fromdatePicker"/>
    To:
    <input type="date" name="todate" value="${to}" id="todatePicker"/>
    <button id="sort" class="btn btn-primary" type="submit">Go</button>
</form><br/><br/>
<birt:viewer id="birtViewer" reportDesign="DataWiseParameterizeReport.rptdesign" pattern="frameset" height="900" width="1600" format="html">
    <birt:param name="endDateParameter" value="${to}"></birt:param>
    <birt:param name="dateParamete" value="${from}"></birt:param>
</birt:viewer>
<script>
    var defaultDate = new Date();
    defaultDate.setDate(defaultDate.getDate()-1);
    var date = defaultDate.getDate();
    var month = defaultDate.getMonth()+1;
    var year = defaultDate.getFullYear();
    if (date < 10){
        date='0' + date;
    }
    if (month < 10){
        month = '0' + month;
    }
    defaultDate = year + '-' + month + '-' + date;

    var todateEntered = '<c:out value="${todate}"></c:out>';
    var fromdateEntered = '<c:out value="${fromdate}"></c:out>';
    if (todateEntered == '')
        todateEntered = defaultDate;
    if (fromdateEntered == '')
        fromdateEntered = defaultDate;

    $("#todatePicker").datepicker({
        maxDate: "0",
        dateFormat: "yy-mm-dd"
    });

    $("#fromdatePicker").datepicker({
        maxDate: "0",
        dateFormat: "yy-mm-dd"
    });

</script>