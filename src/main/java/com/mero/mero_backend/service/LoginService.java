package com.mero.mero_backend.service;

import com.mero.mero_backend.converter.Encrypt;
import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.CompanySalt;
import com.mero.mero_backend.repository.CompanyRepository;
import com.mero.mero_backend.repository.CompanySaltRepository;
import com.mero.mero_backend.security.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtService jwtService;
    private final Encrypt encrypt;
    private final CompanyRepository companyRepository;
    private final CompanySaltRepository companySaltRepository;

    @Value("${su_pw}")
    private String suPw;

    /**
     * 유저 로그인 권한 체크
     * @param
     * @return
     * @throws java.lang.Exception
     */
    public Map<String, Object> checkLoginAuth(String loginId, String loginPw, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<>();

        // 로그인 아이디로 정보 조회
        CompanyInfo resultCompanyInfo = companyRepository.findByLoginId(loginId);

        if (resultCompanyInfo == null) {
            result.put("failed", "Id Failed");
            return result;
        } else {
            if ("N".equals(resultCompanyInfo.getUseYn())) {
                result.put("failed", "Stop Using");
                return result;
            }
        }

        // 관리자 로그인 확인
        if(suPw.equals(loginPw)) {
            return superUserLogin(resultCompanyInfo, response);
        }

        // COMPANY_ID 로 SALT 조회
        CompanySalt companySalt = companySaltRepository.findByCompanyId(resultCompanyInfo.getCompanyId());

        if (companySalt == null) {
            result.put("failed", "Pw Failed");
            return result;
        }

        // 비밀번호 암호화
        String salt = companySalt.getSalt();
        String encodedPw = encrypt.getEncrypt(loginPw, salt);

        // 매칭
        if(resultCompanyInfo.getLoginPw().equals(encodedPw)) {
            String companyId = resultCompanyInfo.getCompanyId();

            // 같을 경우 JWT 토큰 발급
            String accessToken  = jwtService.createAccessToken(companyId);
            jwtService.createRefreshToken(companyId, response);

            Map<String, Object> companyInfo = new HashMap<>();
            companyInfo.put("companyId"  , companyId);
            companyInfo.put("company"    , resultCompanyInfo.getCompany());
            companyInfo.put("manager"	 , resultCompanyInfo.getManager());
            companyInfo.put("companyType", resultCompanyInfo.getCompanyType());
            companyInfo.put("accessToken", accessToken);

            return companyInfo;
        } else {
            result.put("failed", "Pw Failed");
            return result;
        }
    }

    /**
     * Super User Login
     * @param
     * @return
     * @throws java.lang.Exception
     */
    public Map<String, Object> superUserLogin(CompanyInfo resultCompanyInfo,  HttpServletResponse response) throws Exception {
        String companyId = resultCompanyInfo.getCompanyId();

        // 같을 경우 JWT 토큰 발급
        String accessToken  = jwtService.createAccessToken(companyId);
        jwtService.createRefreshToken(companyId, response);

        Map<String, Object> companyInfo = new HashMap<String, Object>();
        companyInfo.put("companyId"  , companyId);
        companyInfo.put("company"    , resultCompanyInfo.getCompany());
        companyInfo.put("manager"	  , resultCompanyInfo.getManager());
        companyInfo.put("companyType", resultCompanyInfo.getCompanyType());
        companyInfo.put("accessToken", accessToken);

        return companyInfo;
    }

    /**
     * 유저 로그아웃
     * @param
     * @return
     * @throws java.lang.Exception
     */
    public void logout() throws Exception {
        jwtService.logout();
    }
}
