package com.cy.tablayoutniubility;

import android.graphics.pdf.PdfDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/11/19 16:09
 * @UpdateUser:
 * @UpdateDate: 2020/11/19 16:09
 * @UpdateRemark:
 * @Version:
 */
public class PageContainerChildManager {
    private List<PageContainer> list;
    private PageContainer pageContainerChildResumedLast;

    public PageContainerChildManager() {
        list = new ArrayList<>();
    }

    public void addPageContainer(PageContainer pageContainer) {
        list.add(pageContainer);
    }

    public void removePageContainer(int index) {
        list.remove(index);
    }

    public void removePageContainer(PageContainer pageContainer) {
        list.remove(pageContainer);
    }

    public List<PageContainer> getPageContainers() {
        return list;
    }

    public PageContainer getPageContainerChildResumedLast() {
        return pageContainerChildResumedLast;
    }

    public void setPageContainerChildResumedLast(PageContainer pageContainerChildResumedLast) {
        this.pageContainerChildResumedLast = pageContainerChildResumedLast;
    }
    public void clear(){
        list.clear();
        pageContainerChildResumedLast=null;
    }
}
