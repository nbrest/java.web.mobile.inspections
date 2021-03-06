<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width">
<meta name="author" content="nbrest">
<meta name="mobile-web-app-capable" content="yes">

<title>KameHouse - Test Module - JSP</title>
<link rel="icon" type="img/ico" href="${pageContext.request.contextPath}/img/favicon.ico" />
<script src="/kame-house/lib/js/jquery-2.0.3.min.js"></script>
<script src="/kame-house/js/global.js"></script>
<script src="/kame-house/js/test-module/jsp/index.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
</head>
<body>
  <div class="main-body">
  <div class="banner-wrapper">
  <div id="banner" class="fade-in-out-15s banner-ancient-era-warriors">
    <div class="default-layout banner-text">
      <h1>JSP</h1>
      <p>JSP test application in the Test Module</p>
    </div>
  </div>
  </div>
  <div class="default-layout landing-p-links">
    <br>
    <input type="button" value="DragonBall Users" class="btn btn-block btn-outline-secondary"
      onclick="window.location.href='dragonball/users/users-list'">
    <br>
    <input type="button" value="DragonBall Model And View" class="btn btn-block btn-outline-secondary"
      onclick="window.location.href='dragonball/model-and-view'">  
  </div>
  </div>
</body>
</html>
