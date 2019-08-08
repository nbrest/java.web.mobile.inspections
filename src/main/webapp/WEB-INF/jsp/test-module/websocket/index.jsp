<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<meta name="author" content="nbrest">
<meta name="description" content="kame-house application">
<meta name="keywords" content="kame-house nicobrest nbrest">

<title>KameHouse - Test Module - WebSocket</title>
<link rel="icon" type="img/ico" href="/kame-house/img/favicon.ico" />
<link rel="stylesheet" href="/kame-house/lib/css/bootstrap.min.css" />
<link rel="stylesheet" href="/kame-house/css/global.css" />
<link rel="stylesheet" href="/kame-house/test-module/websocket/css/websocket.css" />
</head>
<body>
  <div id="headerContainer"></div>
  <div class="main-body">
  <div class="default-layout">
   
  <div id="connect-group" class="pd-15-d-kh">
    <form>
      <div class="form-group">
        <label for="connect">WebSocket: </label>
        <button id="connect" class="btn btn-outline-success" type="submit">Connect</button>
        <button id="disconnect" class="btn btn-outline-danger" type="submit" disabled="disabled">Disconnect
        </button>
      </div>
    </form>
  </div>
        
  <div id="input-group">
    <form class="form-inline"> 
      <label for="firstName">Tell us who you are: </label>
      <input type="text" id="firstName" class="form-control mar-5-d-kh mar-5-m-kh" placeholder="First name...">
      <input type="text" id="lastName" class="form-control mar-5-d-kh mar-5-m-kh" placeholder="Last name...">
      <button id="send" class="btn btn-outline-primary" type="submit" disabled="disabled">Send</button> 
    </form>
  </div> 
     
  <table id="websocket-responses-wrapper" class="table">
    <thead><tr><th>WebSocket Responses</th> </tr></thead>
    <tbody id="websocket-responses"></tbody>
  </table> 
  </div>
  </div> 
  <div id="footerContainer"></div>
  <script src="/kame-house/lib/js/jquery-2.0.3.min.js"></script>
  <script src="/kame-house/lib/js/sockjs.min.js"></script>
  <script src="/kame-house/lib/js/stomp.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/header-footer/headerFooter.js"></script>
  <script src="/kame-house/js/global.js"></script>
  <script src="/kame-house/test-module/websocket/js/websocket.js"></script>  
</body>
</html>
