<%@ page import="entities.Usuario"%>
<%@ page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.css" rel="stylesheet">

<script src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>
<style>
.select, #locale {
	width: 100%;
}

.like {
	margin-right: 10px;
}
</style>

<title>Listado de Usuarios</title>

</head>
<body>
	<jsp:include page="/Navbar.jsp" />

	<div class="select">
  <select class="form-control" id="locale">
    <option value="af-ZA">af-ZA</option>
    <option value="ar-SA">ar-SA</option>
    <option value="ca-ES">ca-ES</option>
    <option value="cs-CZ">cs-CZ</option>
    <option value="da-DK">da-DK</option>
    <option value="de-DE">de-DE</option>
    <option value="el-GR">el-GR</option>
    <option value="en-US" selected>en-US</option>
    <option value="es-AR">es-AR</option>
    <option value="es-CL">es-CL</option>
    <option value="es-CR">es-CR</option>
    <option value="es-ES">es-ES</option>
    <option value="es-MX">es-MX</option>
    <option value="es-NI">es-NI</option>
    <option value="es-SP">es-SP</option>
    <option value="et-EE">et-EE</option>
    <option value="eu-EU">eu-EU</option>
    <option value="fa-IR">fa-IR</option>
    <option value="fi-FI">fi-FI</option>
    <option value="fr-BE">fr-BE</option>
    <option value="fr-FR">fr-FR</option>
    <option value="he-IL">he-IL</option>
    <option value="hr-HR">hr-HR</option>
    <option value="hu-HU">hu-HU</option>
    <option value="id-ID">id-ID</option>
    <option value="it-IT">it-IT</option>
    <option value="ja-JP">ja-JP</option>
    <option value="ka-GE">ka-GE</option>
    <option value="ko-KR">ko-KR</option>
    <option value="ms-MY">ms-MY</option>
    <option value="nb-NO">nb-NO</option>
    <option value="nl-NL">nl-NL</option>
    <option value="pl-PL">pl-PL</option>
    <option value="pt-BR">pt-BR</option>
    <option value="pt-PT">pt-PT</option>
    <option value="ro-RO">ro-RO</option>
    <option value="ru-RU">ru-RU</option>
    <option value="sk-SK">sk-SK</option>
    <option value="sv-SE">sv-SE</option>
    <option value="th-TH">th-TH</option>
    <option value="tr-TR">tr-TR</option>
    <option value="uk-UA">uk-UA</option>
    <option value="ur-PK">ur-PK</option>
    <option value="uz-Latn-UZ">uz-Latn-UZ</option>
    <option value="vi-VN">vi-VN</option>
    <option value="zh-CN">zh-CN</option>
    <option value="zh-TW">zh-TW</option>
  </select>
</div>

<div id="toolbar">
  <button id="remove" class="btn btn-danger" disabled>
    <i class="fa fa-trash"></i> Delete
  </button>
</div>
<table
  data-toggle="table"
  data-search="true"
  data-show-columns="true">
  <thead>
    <tr class="tr-class-1">
      <th data-field="name" rowspan="2" data-valign="middle">Name</th>
      <th colspan="3">Detail</th>
    </tr>
    <tr class="tr-class-2">
      <th data-field="star">Stars</th>
      <th data-field="forks">Forks</th>
      <th data-field="description">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr id="tr-id-1" class="tr-class-1" data-title="bootstrap table" data-object='{"key": "value"}'>
      <td id="td-id-1" class="td-class-1" data-title="bootstrap table">
        <a href="https://github.com/wenzhixin/bootstrap-table" target="_blank">bootstrap-table</a>
      </td>
      <td data-value="526">8827</td>
      <td data-text="122">3603</td>
      <td data-i18n="Description">An extended Bootstrap table with radio, checkbox, sort, pagination, and other added features. (supports twitter bootstrap v2 and v3)
      </td>
    </tr>
    <tr id="tr-id-2" class="tr-class-2">
      <td id="td-id-2" class="td-class-2">
        <a href="https://github.com/wenzhixin/multiple-select" target="_blank">multiple-select</a>
      </td>
      <td>1615</td>
      <td>623</td>
      <td>A jQuery plugin to select multiple elements with checkboxes :)
      </td>
    </tr>
    <tr id="tr-id-3" class="tr-class-3">
      <td id="td-id-3" class="td-class-3">
        <a href="https://github.com/wenzhixin/bootstrap-show-password" target="_blank">bootstrap-show-password</a>
      </td>
      <td>220</td>
      <td>85</td>
      <td>Show/hide password plugin for twitter bootstrap.
      </td>
    </tr>
    <tr id="tr-id-4" class="tr-class-4">
      <td id="td-id-4" class="td-class-4">
        <a href="https://github.com/wenzhixin/bootstrap-table-examples" target="_blank">bootstrap-table-examples</a>
      </td>
      <td>1734</td>
      <td>1532</td>
      <td>Bootstrap Table Examples</td>
    </tr>
    <tr id="tr-id-5" class="tr-class-5">
      <td id="td-id-5" class="td-class-5">
        <a href="https://github.com/wenzhixin/scutech-redmine" target="_blank">scutech-redmine</a>
      <td>24</td>
      <td>18</td>
      <td>Redmine notification tools for chrome extension.</td>
    </tr>
  </tbody>
</table>


	</main>
	<jsp:include page="/Footer.jsp" />
</body>
</html>