<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<div id="followers">
  <h3>알 수도 있는 사람</h3>
  <div id="recommand_div">
    <table name="recommand_friend">
    </table>
  </div> <!-- fetch 이용 -->

  <div id="footer">
    <div id="footer_content">
      <a href="">
        <img src="/resources/img/common/github.png" alt="깃허브">
      </a>
      <p>개발자 : 최홍석, 이수진<br>
        김가영, 이동건<br>
        안상후
      </p>
    </div>
  </div> <!-- side_etc -->
</div> <!-- followers -->

<div id="dm_view"> <!-- 대화창 표시 -->
  <p>Purple Talk</p>

  <i class="fas fa-angle-double-up dm_open"></i>

  <div class="chat_view">
    <div class="chat_menus">
      <i id="friend_btn" class="fas fa-user-alt"></i>
      <i id="chat_btn" class="far fa-comments"></i>
    </div> <!-- chat_menus -->

    <div class="dm_close">
      <i class="fas fa-angle-double-down"></i>
    </div> <!-- dm_close -->

    <div id="friend_list">
      <span>친구목록</span>
      <table>
      </table>
    </div> <!-- fetch 이용 -->

    <div class="chat_list">
      <span>대화목록</span>
    </div> <!-- fetch 이용 -->

    <!-- 채팅방 --> 
    <div class="chat_msg">
      <p id="chat_friendName">
      </p>

      <div class="chat">
        </div> <!-- friendMsgContainer -->
          <!-- 채팅 메시지 입력 시 계속 append  -->
          <div class="msg_container">
            <input name="msg_input" onclick="clickRead(this)" placeholder="메시지를 입력해주세요." onkeydown="return enterKey(event)">
            <div class="msg_btn" >
              <button name="send_btn">전송</button>
            </div>
          </div> <!-- msg_container -->
      </div> <!-- chat --> 
    </div><!--chat msg-->
  </div> <!-- chat_view -->
</div> <!-- dm_view -->