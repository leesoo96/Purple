<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/resources/css/search/search.css" />
<script defer src="/resources/js/search/search.js"></script>
<link rel="stylesheet" href="/resources/css/feed/feed.css">
<link rel="stylesheet" href="/resources/css/feed/feedDetail.css">

<svg xmlns="http://www.w3.org/2000/svg" width="0" height="0" display="none">
  <symbol id="user" viewBox="0 0 32 32">
    <path
      d="M 16 4 C 12.145852 4 9 7.1458513 9 11 C 9 13.393064 10.220383 15.517805 12.0625 16.78125 C 8.485554 18.302923 6 21.859881 6 26 L 8 26 C 8 21.533333 11.533333 18 16 18 C 20.466667 18 24 21.533333 24 26 L 26 26 C 26 21.859881 23.514446 18.302923 19.9375 16.78125 C 21.779617 15.517805 23 13.393064 23 11 C 23 7.1458513 19.854148 4 16 4 z M 16 6 C 18.773268 6 21 8.2267317 21 11 C 21 13.773268 18.773268 16 16 16 C 13.226732 16 11 13.773268 11 11 C 11 8.2267317 13.226732 6 16 6 z"
    />
  </symbol>
  <symbol id="post" viewbox="0 0 32 32">
    <path
      d="M 3 5 L 3 6 L 3 23 C 3 25.209804 4.7901961 27 7 27 L 25 27 C 27.209804 27 29 25.209804 29 23 L 29 13 L 29 12 L 28 12 L 23 12 L 23 6 L 23 5 L 22 5 L 4 5 L 3 5 z M 5 7 L 21 7 L 21 12 L 21 13 L 21 23 C 21 23.73015 21.221057 24.41091 21.5625 25 L 7 25 C 5.8098039 25 5 24.190196 5 23 L 5 7 z M 7 9 L 7 10 L 7 13 L 7 14 L 8 14 L 18 14 L 19 14 L 19 13 L 19 10 L 19 9 L 18 9 L 8 9 L 7 9 z M 9 11 L 17 11 L 17 12 L 9 12 L 9 11 z M 23 14 L 27 14 L 27 23 C 27 24.190196 26.190196 25 25 25 C 23.809804 25 23 24.190196 23 23 L 23 14 z M 7 15 L 7 17 L 12 17 L 12 15 L 7 15 z M 14 15 L 14 17 L 19 17 L 19 15 L 14 15 z M 7 18 L 7 20 L 12 20 L 12 18 L 7 18 z M 14 18 L 14 20 L 19 20 L 19 18 L 14 18 z M 7 21 L 7 23 L 12 23 L 12 21 L 7 21 z M 14 21 L 14 23 L 19 23 L 19 21 L 14 21 z"
    />
  </symbol>
  <symbol id="special" viewbox="0 0 32 32">
    <path
      d="M 4 4 L 4 5 L 4 27 L 4 28 L 5 28 L 27 28 L 28 28 L 28 27 L 28 5 L 28 4 L 27 4 L 5 4 L 4 4 z M 6 6 L 26 6 L 26 26 L 6 26 L 6 6 z M 16 8.40625 L 13.6875 13.59375 L 8 14.1875 L 12.3125 18 L 11.09375 23.59375 L 16 20.6875 L 20.90625 23.59375 L 19.6875 18 L 24 14.1875 L 18.3125 13.59375 L 16 8.40625 z M 16 13.3125 L 16.5 14.40625 L 17 15.5 L 18.1875 15.59375 L 19.40625 15.6875 L 18.5 16.5 L 17.59375 17.3125 L 17.8125 18.40625 L 18.09375 19.59375 L 17 19 L 16 18.40625 L 15 19 L 14 19.59375 L 14.3125 18.40625 L 14.5 17.3125 L 13.59375 16.5 L 12.6875 15.6875 L 13.90625 15.59375 L 15.09375 15.5 L 15.59375 14.40625 L 16 13.3125 z"
    />
  </symbol>
</svg>

<form class="search-form" onsubmit="return false" >
  <input type="text" name="search_input" onkeyup="enterkey(this)" placeholder="Search" class="search-input" value="${data}" />
  <div class="search-option">
    <div>
      <input name="type" type="radio" value="0" onclick="userSearchClick()" id="type-users" class="search_chack" <c:if test="${data  == null}">checked</c:if>/>
      <label for="type-users">
        <svg class="edit-pen-title">
          <use
            xmlns:xlink="http://www.w3.org/1999/xlink"
            xlink:href="#user"
          ></use>
        </svg>
        <span>Users</span>
      </label>
    </div>

    <div>
      <input name="type" type="radio" value="1" onclick="feedSearchClick()" id="type-posts" class="search_chack" />
      <label for="type-posts">
        <svg class="edit-pen-title">
          <use
            xmlns:xlink="http://www.w3.org/1999/xlink"
            xlink:href="#post"
          ></use>
        </svg>
        <span>Feed</span>
      </label>
    </div>
    <div>
      <input name="type" type="radio" value="2" onclick="hashtagSearchClick()" id="type-special"  class="search_chack" 
      <c:if test="${data != null}">checked</c:if> 
      />
      <label for="type-special">
        <svg class="edit-pen-title">
          <use
            xmlns:xlink="http://www.w3.org/1999/xlink"
            xlink:href="#special"
          ></use>
        </svg>
        <span>Hashtag</span>
      </label>
    </div>
  </div>
</form>



<div class="search_content" name="search_content">

</div>

<div class="feed_overlay">
  <div class="feedDetail">
           
  </div> 
</div>

