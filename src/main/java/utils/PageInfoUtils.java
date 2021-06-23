package utils;


import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.stream.Collectors;

public class PageInfoUtils<T> {
    /**
     * 手工分页
     * @param allList
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static <T> PageInfo<T> getPage(List<T> allList, Integer pageNo, Integer pageSize) {
        int total = allList.size();
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        List<T> list = allList.stream().skip((pageNo - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);
        pageInfo.setPages(pages);
        pageInfo.setList(list);
        return pageInfo;
    }
}
