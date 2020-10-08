package com.cy.tablayoutniubility;

import android.graphics.pdf.PdfDocument;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:27
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class ContainerPageAdapterVp<T> extends BaseContainerPageAdapterVp<T, TabViewHolder>
        implements ITabPageAdapterVp<T>{
    @Override
    public <W extends PagerAdapter> W getPageAdapter() {
        return (W) this;
    }
}
