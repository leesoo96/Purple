package com.purple.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.mapper.MypageMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.utils.PurpleFileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MypageService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MypageMapper mypageMapper;

    @Autowired
	private PurpleFileUtils fUtils;

    @Autowired
    private FeedMapper feedMapper;

    public int modUserInfo(UserEntity entity) {
        return mypageMapper.modUserInfo(entity);
    }

    public int checkUserpw(Map<String, Object> map) {
        String db_pw = mypageMapper.checkUserpw(Integer.parseInt((String) map.get("user_pk")));
        if (encoder.matches((String) map.get("user_pw"), db_pw)) {
            return 1;
        }
        return 0;
    }

    public int modUserPw(UserEntity entity) {
        entity.setUser_pw(encoder.encode(entity.getUser_pw()));
        return mypageMapper.modUserPw(entity);
    }

    //프로필 이미지 등록
    public String profile_img(MultipartFile img) {
		//유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		
        //업로드 할 파일 경로
		String folder = "/images/user/" + user_pk + "/profileimg";
		
        try {
            String delFolder = fUtils.getRealPath(folder);
            fUtils.delFolder(delFolder);
			
            String fileNm = fUtils.transferTo(img, folder);
			
            return folder + "/" + fileNm;
		} catch(Exception e) {
			return null;
		}
	}

    //배경 이미지 등록
    public String background_img(MultipartFile img) {
		//유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		
        //업로드 할 파일 경로
		String folder = "/images/user/" + user_pk +"/backgroundimg";
		
        try {
            String delFolder = fUtils.getRealPath(folder);
            fUtils.delFolder(delFolder);
			
            String fileNm = fUtils.transferTo(img, folder);
			
            return folder + "/" + fileNm;
		} catch(Exception e) {
			return null;
		}
	}

    public List<FeedListDTO> selMypageFeedList(FeedListDTO dto) {
        List<FeedListDTO> feed_list = new ArrayList<FeedListDTO>();
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setFeed_state(1);
        dto.setUser_pk(principal.getUser_pk());
        
        int first_page = (5 * dto.getPage_count());
        int end_page = first_page + 5;
        dto.setFirst_page(first_page);
        dto.setEnd_page(end_page);
        
        feed_list = mypageMapper.selMypageFeedList(dto);
        
        for (int i = 0; i < feed_list.size(); i++ ) {
            feed_list.get(i).setUser_pk(principal.getUser_pk());
            
            List<HashtagEntity> hashtag_list = new ArrayList<HashtagEntity>();
            
            hashtag_list = feedMapper.selHashtagList(feed_list.get(i));
            
            List<MediaEntity> media_list = new ArrayList<MediaEntity>();
            media_list = feedMapper.selMediaList(feed_list.get(i));
            feed_list.get(i).setMedia_url(media_list);
            feed_list.get(i).setHashtag_ctnt(hashtag_list);
            feed_list.get(i).setFavorite_state(feedMapper.isFavorite(feed_list.get(i).getFeed_pk(), principal.getUser_pk()));
            feed_list.get(i).setBookmark_state(feedMapper.isBookmark(feed_list.get(i)));
        }
        return feed_list;
    }
}
