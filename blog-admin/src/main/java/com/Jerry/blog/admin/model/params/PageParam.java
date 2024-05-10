package com.Jerry.blog.admin.model.params;

import lombok.Data;
import sun.rmi.server.InactiveGroupException;

/**
 * @author CaiBowen
 * @date 2023/8/7 9:20
 */

@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;


}
