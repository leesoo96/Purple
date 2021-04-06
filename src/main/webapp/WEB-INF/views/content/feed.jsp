<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/resources/css/feed/feed.css">
<link rel="stylesheet" href="/resources/css/feed/feedDetail.css">
<script defer src="/resources/js/feed/feed.js"></script>

<div id="selectBox">
  <select name="feed">
    <option value="1" selected>최신순</option>
    <option value="2">인기순</option>
  </select>
</div>
<div id="feed">
</div>   <!-- feed -->

<div class="feed_overlay">
   <div class="feedDetail">
   </div> 
</div>