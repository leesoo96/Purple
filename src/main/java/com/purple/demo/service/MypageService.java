package com.purple.demo.service;

import java.util.Map;

import com.purple.demo.mapper.MypageMapper;
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
    private MypageMapper mapper;

    @Autowired
	private PurpleFileUtils fUtils;

    public int modUserInfo(UserEntity userEntity) {
        return mapper.modUserInfo(userEntity);
    }

    public int checkUserpw(Map<String, Object> map) {
        String db_pw = mapper.checkUserpw(Integer.parseInt((String) map.get("user_pk")));
        if (encoder.matches((String) map.get("user_pw"), db_pw)) {
            return 1;
        }
        return 0;
    }

    public int modUserPw(UserEntity userEntity) {
        userEntity.setUser_pw(encoder.encode(userEntity.getUser_pw()));
        return mapper.modUserPw(userEntity);
    }

    //프로필 이미지 등록
    public String profile_img(MultipartFile img) {
		//유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		//업로드 할 파일 경로
		String folder = "/images/Mypage/profileimg/"+user_pk;
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
		String folder = "/images/Mypage/backgrounimg/"+user_pk;
		try {
            String delFolder = fUtils.getRealPath(folder);
            fUtils.delFolder(delFolder);
			String fileNm = fUtils.transferTo(img, folder);
			return folder + "/" + fileNm;
		} catch(Exception e) {
			return null;
		}
	}
}
