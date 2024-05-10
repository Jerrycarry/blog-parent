package com.Jerry.blog.admin.Service;

import com.Jerry.blog.admin.Pojo.Admin;
import com.Jerry.blog.admin.Pojo.Permission;
import com.Jerry.blog.admin.mapper.PermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;
import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/8/8 10:27
 */

@Service
@Slf4j
public class AuthService {

    @Autowired
    private AdminService adminService;

    public boolean auth(HttpServletRequest request, Authentication authentication){
        String url = request.getRequestURI();
        log.info("request url:{}",url);
        //true代表放行，false代表拦截
        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)){
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Admin admin = adminService.findAdminByUserName(username);

        if (admin == null){
            return false;
        }
        if (admin.getId() == 1){
            return true;
        }
        List<Permission> permissions = adminService.findPermissionsByAdminId(admin.getId());
        url = StringUtils.split(url,'?')[0];
        for (Permission permission:permissions){
            if (url.equals(permission.getPath())){
                log.info("权限通过");
                return true;
            }
        }
        return false;
    }
}
