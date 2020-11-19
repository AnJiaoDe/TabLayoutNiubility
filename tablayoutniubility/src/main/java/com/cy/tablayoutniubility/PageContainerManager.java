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
public class PageContainerManager {
    private List<PageContainer> list;
    public PageContainerManager() {
        list=new ArrayList<>();
    }
    public void addPageContainer(PageContainer pageContainer){
        list.add(pageContainer);
    }
    public void removePageContainer(int index){
        list.remove(index);
    }
    public void removePageContainer(PageContainer pageContainer){
        list.remove(pageContainer);
    }
    public List<PageContainer> getPageContainers() {
        return list;
    }

}
