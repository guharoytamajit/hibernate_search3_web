<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.mylibrary.action.db.entitys.Author"%>
<%@page import="com.mylibrary.action.db.entitys.Book"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
.resultTable {
	border: 1px gray solid;
	width: 700px;
	border-spacing: 0px;
	border-collapse: collapse;
	margin-bottom: 50px;
	margin-left: 100px;
}

.oddRow {
	border-right: 1px gray solid;
	background-color: #F3F3F3;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IcodingClubSearch</title>
</head>
<body>
<%
	String searchKey = (String) request.getAttribute("searchKey");
%>
<img style="margin-left: 100px" alt="icodingclub_banner" src="ICCS.png">
<form action="SearchController"><input
	style="margin-left: 100px; margin-top: 5px" name="searchKey"
	type="text" size="50" value="<%=searchKey%>"> <br>
<input style="margin-left: 200px; margin-top: 5px" type="submit"
	value="Search"> <br>

<%
	String[] suggestions = (String[]) request
	.getAttribute("serachSuggestions");

	if (!(suggestions == null || suggestions.length == 0)) {
out.print("<label style='margin-left: 100px;margin-top: 5px;margin-bottom: 5px;color: maroon;'>Did you mean ? : </label>");
for (String suggestion : suggestions) {
	out.print("<a href='SearchController?searchKey="
	+ suggestion + "'>" + suggestion + "</a>, ");
}
	}
%> <br>

<h2 style="color: maroon; margin-left: 100px">Search Results</h2>
<%
	Set books = (Set) request.getAttribute("result");
	for (Object obj : books) {
Book book = (Book) obj;
out.print("<table class='resultTable'>");
out.print("<tr>");
out.print("<td>Title</td>");
out.print("<td>");
if (book.getTitle().toLowerCase()
.indexOf(searchKey.toLowerCase()) > -1) {
	out.print(book.getTitle().replaceAll("(?i)" + searchKey,
	"<b>" + searchKey + "</b>"));
} else {
	out.print(book.getTitle());
}

out.print("</td>");
out.print("</tr>");
out.print("<tr class='oddRow'>");
out.print("<td>Author</td>");
out.print("<td>");
for (Author author : book.getAuthors()) {
	if (author.getName().toLowerCase()
	.indexOf(searchKey.toLowerCase()) > -1) {
out.print(author.getName().replaceAll(
"(?i)" + searchKey, "<b>" + searchKey + "</b>"));
	} else {
out.print(author.getName());
	}
}

out.print("</td>");
out.print("</tr>");

out.print("<tr>");
out.print("<td>ISBN</td>");
out.print("<td>");
if (book.getIsbn().toLowerCase()
.indexOf(searchKey.toLowerCase()) > -1) {
	out.print(book.getIsbn().replaceAll("(?i)" + searchKey,
	"<b>" + searchKey + "</b>"));
} else {
	out.print(book.getIsbn());
}
out.print("</td>");
out.print("</tr>");

out.print("<tr class='oddRow'>");
out.print("<td >Price</td>");
out.print("<td >");
String price = "" + book.getPrice();
if (price.toLowerCase().indexOf(searchKey.toLowerCase()) > -1) {
	out.print(price.replaceAll("(?i)" + searchKey, "<b>"
	+ searchKey + "</b>"));
} else {
	out.print(price);
}
out.print("</td>");
out.print("</tr>");

out.print("<tr>");
out.print("<td >Publisher</td>");
out.print("<td >");
if (book.getPublisher().toLowerCase()
.indexOf(searchKey.toLowerCase()) > -1) {
	out.print(book.getPublisher().replaceAll(
	"(?i)" + searchKey, "<b>" + searchKey + "</b>"));
} else {
	out.print(book.getPublisher());
}
out.print("</td>");
out.print("</tr>");

out.print("<tr class='oddRow'>");
out.print("<td >Year Of Publication</td>");
out.print("<td >");
Calendar cal = Calendar.getInstance();
cal.setTime(book.getDateOfPublication());
String year = "" + cal.get(Calendar.YEAR);
if (year.toLowerCase().indexOf(searchKey.toLowerCase()) > -1) {
	out.print(year.replaceAll("(?i)" + searchKey, "<b>"
	+ searchKey + "</b>"));
} else {
	out.print(year);
}
out.print("</td>");
out.print("</tr>");

out.print("<tr>");
out.print("<td >Discription</td>");
out.print("<td >");
if (book.getDesc().toLowerCase()
.indexOf(searchKey.toLowerCase()) > -1) {
	out.print(book.getDesc().replaceAll("(?i)" + searchKey,
	"<b>" + searchKey + "</b>"));
} else {
	out.print(book.getDesc());
}
out.print("</td>");
out.print("</tr>");
out.print("</table>");
out.print("</br>");
out.print("</br>");
	}
%>

</body>
</html>