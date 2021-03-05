<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<div id="followers">
        <h3>Followers</h3>
        <table>
          <tr>
            <td>
              <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
              <span>friends_one</span>
            </td>
          </tr>
          <tr>
            <td>
              <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
              <span>friends_two</span>
            </td>
          </tr>
          <tr>
            <td>
              <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
              <span>friends_threeeeeeeee</span>
            </td>
          </tr>
          <tr>
            <td>
              <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
              <span>friends_threeeeeeeee</span>
            </td>
          </tr>
          <tr>
            <td>
              <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
              <span>friends_threeeeeeeee</span>
            </td>
          </tr>
        </table>

        <div id="footer">
          <div id="footer_content">
          <a href="">
            <img src="resources/img/common/github.png" alt="깃허브">
          </a>
          <p>개발자 : 최홍석, 이수진<br>
                      김가영, 이동건<br>
                      안상후
          </p>
          </div>
        </div> <!-- side_etc -->
      </div> <!-- followers -->

      <div id="dm_view">
        <!-- 대화창 표시 -->
        <p>Purple Talk</p>
        <div id="new_dm">
          <div>31</div> 
        </div>
        <i class="fas fa-angle-double-up dm_open"></i>

        <div class="chat_view">
          <div class="chat_menus">
            <i id="friend_btn" class="fas fa-user-alt"></i>
            <i id="chat_btn" class="far fa-comments"></i>
          </div> <!-- chat_menus -->
          <div class="dm_close">
            <i class="fas fa-angle-double-down"></i>
          </div> <!-- dm_close -->
          <div class="friend_list">
            <span>친구목록</span>
            <table>
              <tr>
                <td><img src="resources/img/common/basic_profile.png" alt="프로필사진"></td>
                <td id="friend_info">
                  <span>friendName</span>
                  <small>상태메시지</small>
                </td>
                <td id="friend_block">
                  <span>차단하기</span>
                </td>
                <td id="friend_chat_func">
                  <span>대화</span>
                </td>
              </tr>
              <tr>
                <td><img src="resources/img/common/basic_profile.png" alt="프로필사진"></td>
                <td id="friend_info">
                  <span>friendName2</span>
                  <small>상태메시지</small>
                </td>
                <td id="friend_block">
                  <span>차단하기</span>
                </td>
                <td id="friend_chat_func">
                  <span>대화</span>
                </td>
              </tr>
              <tr>
                <td><img src="resources/img/common/basic_profile.png" alt="프로필사진"></td>
                <td id="friend_info">
                  <span>friendNameeee3</span>
                  <small>상태메시지</small>
                </td>
                <td id="friend_block">
                  <span>차단하기</span>
                </td>
                <td id="friend_chat_func">
                    <span>대화</span>
                  </div>
                </td>
              </tr>
              <tr>
                <td><img src="resources/img/common/basic_profile.png" alt="프로필사진"></td>
                <td id="friend_info">
                  <span>friendNameeeee4</span>
                  <small>상태메시지</small>
                </td>
                <td id="friend_block">
                  <span>차단하기</span>
                </td>
                <td id="friend_chat_func">
                  <span>대화</span>
                </td>
              </tr>
              <tr>
                <td><img src="resources/img/common/basic_profile.png" alt="프로필사진"></td>
                <td id="friend_info">
                  <span>friendNameeeee5</span>
                  <small>상태메시지</small>
                </td>
                <td id="friend_block">
                  <span>차단하기</span>
                </td>
                <td id="friend_chat_func">
                  <span>대화</span>
                </td>
              </tr>
            </table>
          </div> <!-- friend_lsit -->

          <div class="chat_list">
            <span>대화목록</span>
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
            <img src="img/temp/back.png" alt="뒤로가기">
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