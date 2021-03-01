<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>purple</title>
  <link rel="stylesheet" href="resources/css/feed/feed.css">
  <script defer src="resources/js/feed/feed.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
    integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
</head>

<body>
  <div id="container">
    <div id="layers">
      <div id="nav1">
        <div class="menus">
          <div class="logo">
            <a href="/home">
              <img src="resources/img/home/logo1.png" alt="로고">
            </a>
          </div>
          <div class="home">
            <a href="/home">
              <i class="fas fa-home menu_icons"></i>
              <span>홈</span>
            </a>
          </div>
          <div class="search">
            <a href="search.html">
              <i class="fas fa-search menu_icons"></i>
              <span>검색하기</span>
            </a>
          </div>
          <div class="news">
            <a href="">
              <i class="far fa-bell menu_icons"></i>
              <span>알림</span>
            </a>
            <div class="new_msg">
               <div>17</div> 
            </div>
          </div>
          <div class="bookmark">
            <a href="">
              <i class="far fa-bookmark menu_icons"></i>
              <span>북마크</span>
            </a>
          </div>
          <div class="cs">
              <i class="fas fa-cogs menu_icons"></i>
              <span>고객센터</span>
              <div class="cs_menus">
                <a href="">공지사항</a>
                <a href="">문의사항</a>
              </div>
          </div>
          <button class="userWrite_btn">글쓰기</button>

          <div class="temp_user">
            <img src="resources/img/feed/profile.png" alt="기본프로필사진">
            
           <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
  				<sec:authentication property="principal" var="userInfo"/>
  				<p>${userInfo.user_name }</p>
         	</sec:authorize> 
            	
            <i class="fas fa-grip-lines"></i>
          </div>
          <div class="only_modalClose"></div> <!-- user_modal창을 닫기위한 도구적 클래스 -->
          <div class="temp_user_modal">
            <img src="resources/img/feed/profile.png" alt="기본프로필사진">
         
          <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
  			<sec:authentication property="principal" var="userInfo"/>
  			<p>${userInfo.user_id }</p>
         </sec:authorize> 
             
            <a href="mypage.html">마이페이지</a>
            <a href="/logout">로그아웃</a>
          </div>
          
        </div> <!-- menus -->
      </div> <!-- nav2 -->

      <div id="main">
        <!-- 인페이지 -->
        <div id="selectBox">
          <select name="feed">
            <option value="" selected>최신순</option>
            <option value="">인기순</option>
          </select>
        </div>
        <div id="content_detail">
          <div class="upload_user">
            <img src="resources/img/feed/profile.png" alt="기본프로필사진">
            <span>friends_threeeeeeeee</span>
            <div class="upload_content">
              <span>테스트용 텍스트입니다.</span>
            </div>
          </div>

          <div class="upload_user">
            <img src="resources/img/feed/profile.png" alt="기본프로필사진">
            <span>friends_one</span>
            <div class="upload_content">
              <img src="resources/img/feed/demo1.jpg" alt="">
              <img src="resources/img/feed/demo4.png" alt="">
            </div>
          </div>

          <div class="upload_user">
            <img src="resources/img/feed/profile.png" alt="기본프로필사진">
            <span>friends_two</span>
            <div class="upload_content">
              <span>테스트용 텍스트입니다.</span>
            </div>
          </div>

          <div class="upload_user">
            <img src="resources/img/home/profile.png" alt="기본프로필사진">
            <span>Unknown friend</span>
            <div class="upload_content">
              <img src="resources/img/home/demo2.png" alt="">
            </div>
          </div>

          <div class="upload_user">
            <img src="resources/img/feed/profile.png" alt="기본프로필사진">
            <span>friends_threeeeeeeee</span>
            <div class="upload_content">
              <img src="resources/img/feed/demo3.png" alt="">
            </div>
          </div>
        </div>
      </div>

      <div id="nav2">
        <div class="followers">
          <h3>Followers</h3>
          <table>
            <tr>
              <td>
                <img src="resources/img/feed/profile.png" alt="기본프로필사진">
                <span>friends_one</span>
              </td>

            </tr>
            <tr>
              <td>
                <img src="resources/img/feed/profile.png" alt="기본프로필사진">
                <span>friends_two</span>
              </td>
            </tr>
            <tr>
              <td>
                <img src="resources/img/feed/profile.png" alt="기본프로필사진">
                <span>friends_threeeeeeeee</span>
              </td>
            </tr>
          </table>

          <div class="side_etc">
            <a href="">
              <img src="resources/img/feed/github.PNG" alt="깃허브">
            </a>
          </div> <!-- side_etc -->
        </div> <!-- followers -->

        <div class="dm_view">
          <!-- 대화창 표시 -->
          <p>쪽지</p>
          <div class="new_dm">
            <div>31</div> 
         </div>
          <i class="fas fa-angle-double-up dm_open"></i>

          <div class="chat_view">
            <div class="dm_close">
              <i class="fas fa-angle-double-down"></i>
            </div> <!-- dm_close -->
            <div class="friend_list">
              <span>친구목록</span>
              <button class="chat_btn">채팅</button>
              <table>
                <tr>
                  <td><img src="resources/img/feed/profile.png" alt="프로필사진"></td>
                  <td>friendName</td>
                  <td><button class="add_chat">+</button></td>
                </tr>
                <tr>
                  <td><img src="resources/img/feed/profile.png" alt="프로필사진"></td>
                  <td>friendName2</td>
                  <td><button class="add_chat">+</button></td>
                </tr>
                <tr>
                  <td><img src="resources/img/feed/profile.png" alt="프로필사진"></td>
                  <td>friendNameeee3</td>
                  <td><button class="add_chat">+</button></td>
                </tr>
                <tr>
                  <td><img src="resources/img/feed/profile.png" alt="프로필사진"></td>
                  <td>friendNameeeee4</td>
                  <td><button class="add_chat">+</button></td>
                </tr>
                <tr>
                  <td><img src="resources/img/feed/profile.png" alt="프로필사진"></td>
                  <td>friendNameeeee5</td>
                  <td><button class="add_chat">+</button></td>
                </tr>
              </table>
            </div> <!-- friend_lsit -->

            <div class="chat_list">
              <span>대화목록</span>
              <button class="friend_btn">친구</button>
              <div>
                <span>friendName</span>
                <p>안녕안녕</p>
                <small>오전 00:00</small>
              </div>

              <div>
                <span>friendName2</span>
                <p>ㅇㅇ</p>
                <small>오전 00:00</small>
              </div>

              <div>
                <span>friendNameeee3</span>
                <p>ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ</p>
                <small>오전 00:00</small>
              </div>
            </div> <!-- chat_list -->

            <div class="chat_msg">
              <img src="resources/img/feed/back.png" alt="뒤로가기">
              <p>friendName</p>

              <div class="chat">
                <span>- 2021년 00월 00일 -</span>
                <div class="friendMsgContainer">
                  <p class="friendMsg">야</p>
                  <small class="friendMsg_time">오후 00:00</small>
                </div> <!-- friendMsgContainer -->

                <div class="friendMsgContainer">
                  <p class="friendMsg">지금 뭐해?</p>
                  <small class="friendMsg_time">오후 00:00</small>
                </div> <!-- friendMsgContainer -->
                <!--채팅 메시지 입력 시 계속 append -->
              </div> <!-- chat -->

              <div class="msg_container">
                <textarea name="msg_input" placeholder="메시지를 입력해주세요."></textarea>
                <div class="msg_btn">
                  <button>전송</button>
                </div>
              </div> <!-- msg_container -->
            </div>
            <!--chat msg-->
          </div> <!-- chat_view -->
        </div> <!-- dm_view -->
      </div><!-- nav1 -->

    </div> <!-- layers -->
  </div> <!-- container-->
</body>

</html>