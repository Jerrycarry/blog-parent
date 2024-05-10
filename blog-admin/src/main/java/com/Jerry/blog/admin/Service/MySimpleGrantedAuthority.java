package com.Jerry.blog.admin.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

/**
 * @author CaiBowen
 * @date 2023/8/8 10:37
 */

public class MySimpleGrantedAuthority implements GrantedAuthority {

    private String authority;

    private String path;


    public MySimpleGrantedAuthority(){

    }
    public MySimpleGrantedAuthority(String authority){
        this.authority = authority;
    }
    public MySimpleGrantedAuthority(String authority,String path){
        this.authority = authority;
        this.path = path;
    }



    @Override
    public String getAuthority() {
        return authority;
    }

    public String getPath(){
        return path;
    }
}
