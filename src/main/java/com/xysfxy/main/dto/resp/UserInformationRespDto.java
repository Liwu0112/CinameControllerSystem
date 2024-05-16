package com.xysfxy.main.dto.resp;

import com.xysfxy.main.utils.DesensitizationUtil;
import lombok.Data;

@Data
public class UserInformationRespDto {

        private Integer userId;

        private String userName;

        private String userPhone;

        private Integer userBalance;

        public String getUserPhone() {
                return DesensitizationUtil.mobileEncrypt(userPhone);
        }
}
