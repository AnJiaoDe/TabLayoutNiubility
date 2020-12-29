文章目录

GitHub:https://github.com/AnJiaoDe/TabLayoutNiubility

该轮子特异功能如下：

使用方法

注意：该轮子适用于androidx中的ViewPager2和ViewPager

注意：如果轮子死活下载不下来，说明maven地址有毛病，你需要找到jitpack的官网首页，查看最新的官网地址

注意：记得去gayhub查看最新版本，最新版本最niubility

详细使用如下

Tab均分不滑动(ViewPager、ViewPager2均支持)

Tab滑动、 indicator蠕动、多布局(ViewPager、ViewPager2均支持)

根据item个数动态设置Tab均分还是滑动

Tab文字颜色渐变(ViewPager、ViewPager2均支持)

自定义Indicator如三角形(ViewPager、ViewPager2均支持)

ViewPager双层嵌套(建议不要使用ViewPager2进行双层嵌套，ViewPager2嵌套滑动冲突几乎无法处理，贼鸡儿坑)

仿微信主页Tab

千古BUG：Activity销毁重启，Fragment恢复问题

AndroidX ViewPager中的FragmentStatePagerAdapter存在的问题

AndroidX ViewPager2中的FragmentStateAdapter存在的问题

自定义fragment

自定义Fragment PageContainer 双层嵌套（ViewPager和ViewPager2均适用）

相关API

TabMediator

FragmentPageAdapter

TabAdapter

TabLayoutScroll、TabLayoutNoScroll、TabLayoutMulti、IndicatorLineView 、 IndicatorTriangleView

TabLayoutScroll和 indicator style设置

自定义indicator

实现原理剖析

说真的，这自定义控件还真不简单

涉及到的难点场景

搞清楚ViewPager监听的onPageSelected、onPageScrolled和onPageScrollStateChanged回调执行特点

自定义HorizontalRecyclerView实现TabLayout

源码如下

TabLayout的item宽度均分

RecyclerView的item刷新如何做到不闪烁

UML类图如下

面向接口编程（面向多态编程）的思想

欢迎联系、指正、批评

## [GitHub:https://github.com/AnJiaoDe/TabLayoutNiubility](https://github.com/AnJiaoDe/TabLayoutNiubility)

## 该轮子特异功能如下：

Tab均分不滑动(ViewPager、ViewPager2均支持)

Tab滑动、 indicator蠕动、多布局(ViewPager、ViewPager2均支持)

根据item个数动态设置Tab均分还是滑动

Tab文字颜色渐变(ViewPager、ViewPager2均支持)

自定义Indicator如三角形(ViewPager、ViewPager2均支持)

ViewPager双层嵌套(建议不要使用ViewPager2进行双层嵌套，ViewPager2嵌套滑动冲突几乎无法处理，贼鸡儿坑)

仿微信主页Tab

自定义fragment
## 使用方法

## 注意：该轮子适用于androidx中的ViewPager2和ViewPager

1.工程目录下的`build.gradle`中添加代码：

## 注意：如果轮子死活下载不下来，说明maven地址有毛病，你需要找到jitpack的官网首页，查看最新的官网地址

```java
allprojects {
		repositories {
			        maven { url 'https://www.jitpack.io' }
		}
	}
```
2.直接在需要使用的模块的`build.gradle`中添加代码：

```java
dependencies {
api 'com.github.AnJiaoDe:TabLayoutNiubility:V1.2.7'
api 'androidx.recyclerview:recyclerview:1.1.0'//版本必须>=1.1.0
}
```

## 注意：记得去gayhub查看最新版本，最新版本最niubility

3.如果你想使用`ViewPager2`,那么添加代码：

```java
api 'androidx.viewpager2:viewpager2:1.0.0'//版本必须>=1.0.0
```
4.混淆已配置到库内部，无需做混淆配置

## 详细使用如下

## Tab均分不滑动(ViewPager、ViewPager2均支持)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807172358952.gif)
activity布局：



```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.cy.tablayoutniubility.TabLayoutNoScroll
        android:id="@+id/tablayout"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        app:space_horizontal="0dp"
        android:background="#fff">

        <com.cy.tablayoutniubility.IndicatorLineView
            android:layout_width="match_parent"
            app:width_indicator_max="80dp"
            app:width_indicator_selected="30dp"
            android:layout_height="wrap_content" />
    </com.cy.tablayoutniubility.TabLayoutNoScroll>

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never" />
</LinearLayout>
```
tab_item布局：

```xml
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/tv"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:gravity="center"
    android:textColor="#444444"
    android:textSize="16sp" >
</TextView>
```
JAVA代码：

```java
    ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        TabLayoutNoScroll tabLayoutLine = findViewById(R.id.tablayout);
//        tabLayoutLine.setSpace_horizontal(0).setSpace_vertical(0);
        FragPageAdapterVp2NoScroll<String> fragmentPageAdapter = new FragPageAdapterVp2NoScroll<String>(this) {
            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabNoScrollViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab_center;
            }
        };

        TabAdapterNoScroll<String> tabAdapter = new TabMediatorVp2NoScroll<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("上课");
        list.add("抗疫");
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
```

## Tab滑动、 indicator蠕动、多布局(ViewPager、ViewPager2均支持)
多布局：

```java
            @Override
            public int getTabLayoutID(int position, String bean) {
                if (position == 0) {
                    return R.layout.item_tab_msg;
                }
                return R.layout.item_tab;
            }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807172711239.gif)
activity布局：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.cy.tablayoutniubility.TabLayoutScroll
        android:id="@+id/tablayout"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:background="#fff">

        <com.cy.tablayoutniubility.IndicatorLineView
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.cy.tablayoutniubility.TabLayoutScroll>

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never" />
</LinearLayout>
```
tab item布局：

```xml
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:gravity="center"
    android:textSize="16sp"
    android:textColor="#444444"
    android:id="@+id/tv">

</TextView>
```

JAVA代码：

```java
   ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine = findViewById(R.id.tablayout);
//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        FragPageAdapterVp2<String> fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {

            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                if (position == 0) {
                    return R.layout.item_tab_msg;
                }
                return R.layout.item_tab;
            }
        };
        TabAdapter<String> tabAdapter = new TabMediatorVp2<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("视频");
        list.add("抗疫");
        list.add("酷玩");
        list.add("彩票");
        list.add("漫画");
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
```

## 根据item个数动态设置Tab均分还是滑动
使用`TabLayoutMulti`
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.cy.tablayoutniubility.TabLayoutMulti
        android:id="@+id/tablayout"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        app:space_horizontal="0dp"
        android:background="#fff">

        <com.cy.tablayoutniubility.IndicatorLineView
            android:layout_width="match_parent"
            app:width_indicator_max="80dp"
            app:width_indicator_selected="30dp"
            android:layout_height="wrap_content" />
    </com.cy.tablayoutniubility.TabLayoutMulti>

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never" />
</LinearLayout>


```
JAVA代码：

```java
ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        TabLayoutMulti tabLayoutMulti = findViewById(R.id.tablayout);
//        tabLayoutLine.setSpace_horizontal(0).setSpace_vertical(0);
        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("上课");
        list.add("抗疫");
        list.add("文化");
//        list.add("经济");
//        list.add("幸福里");

        //根据item个数设置是否需要滚动
        if (list.size() > 6) tabLayoutMulti.setScrollable(true);

        BaseFragPageAdapterVp2 fragmentPageAdapter;

        if (tabLayoutMulti.isScrollable()) {
            fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {
                @Override
                public Fragment createFragment(String bean, int position) {
                    return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
                }

                @Override
                public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                    TextView textView = holder.getView(R.id.tv);
                    if (isSelected) {
                        textView.setTextColor(0xffe45540);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        textView.setTextColor(0xff444444);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                    textView.setText(bean);
                }

                @Override
                public int getTabLayoutID(int position, String bean) {
                    return R.layout.item_tab_center;
                }
            };
        }else {
            fragmentPageAdapter = new FragPageAdapterVp2NoScroll<String>(this) {
                @Override
                public Fragment createFragment(String bean, int position) {
                    return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
                }

                @Override
                public void bindDataToTab(TabNoScrollViewHolder holder, int position, String bean, boolean isSelected) {
                    LogUtils.log("bindDataToTab");
                    TextView textView = holder.getView(R.id.tv);
                    if (isSelected) {
                        textView.setTextColor(0xffe45540);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        textView.setTextColor(0xff444444);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                    textView.setText(bean);
                }

                @Override
                public int getTabLayoutID(int position, String bean) {
                    return R.layout.item_tab_center;
                }
            };
        }


        ITabAdapter tabAdapter = new TabMediatorMulti<String>(tabLayoutMulti).setAdapter(viewPager2, fragmentPageAdapter);
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
        //或者
//        if(tabLayoutMulti.isScrollable()){
//            TabAdapter tabAdapt= (TabAdapter) tabAdapter.getAdapter();
//            tabAdapt.add(list);
//        }else {
//            TabAdapterNoScroll tabAdapt= (TabAdapterNoScroll) tabAdapter.getAdapter();
//            tabAdapt.add(list);
//        }
```

## Tab文字颜色渐变(ViewPager、ViewPager2均支持)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807172901680.gif)
activity代码：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:background="#fff"
    android:layout_height="match_parent" >
    <com.cy.tablayoutniubility.TabLayoutScroll
        android:layout_width="match_parent"
        android:background="#fff"
        android:layout_height="40dp"
        android:id="@+id/tablayout">
        <com.cy.tablayoutniubility.IndicatorLineView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    </com.cy.tablayoutniubility.TabLayoutScroll>
    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="#eee"/>
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:overScrollMode="never"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</LinearLayout>
```
tab item布局：

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.cy.tablayoutniubility.TabGradientTextView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/tv"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:gravity="center"
    android:textSize="16sp"
    app:textColorNormal="#ff444444"
    app:textColorSelected="#ffe45540">

</com.cy.tablayoutniubility.TabGradientTextView>
```
JAVA代码：

```java
     ViewPager2 viewPager2= findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine= findViewById(R.id.tablayout);

//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        FragPageAdapterVp2<String> fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {

            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TabGradientTextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    //因为            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //positionOffset没有为1的时候
                    //必须
                    textView.setProgress(1);
                } else {
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    //因为快速滑动时，            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //positionOffset不会出现0
                    //必须
                    textView.setProgress(0);
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab_gradient;
            }

            @Override
            public void onTabScrolled(TabViewHolder holderCurrent, int positionCurrent, boolean fromLeft2RightCurrent, float positionOffsetCurrent, TabViewHolder holder2, int position2, boolean fromLeft2Right2, float positionOffset2) {
                super.onTabScrolled(holderCurrent, positionCurrent, fromLeft2RightCurrent, positionOffsetCurrent, holder2, position2, fromLeft2Right2, positionOffset2);
                TabGradientTextView textViewCurrent = holderCurrent.getView(R.id.tv);
                TabGradientTextView textView2= holder2.getView(R.id.tv);
                LogUtils.log("onTabScrolled");
                textViewCurrent.setDirection(fromLeft2RightCurrent?TabGradientTextView.DIRECTION_FROM_LEFT:TabGradientTextView.DIRECTION_FROM_RIGHT)
                        .setProgress(positionOffsetCurrent);
                textView2.setDirection(fromLeft2Right2?TabGradientTextView.DIRECTION_FROM_LEFT:TabGradientTextView.DIRECTION_FROM_RIGHT)
                        .setProgress(positionOffset2);


            }
        };

        TabAdapter<String> tabAdapter = new TabMediatorVp2<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("视频");
        list.add("抗疫");
        list.add("彩票");
        list.add("漫画");
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
```

## 自定义Indicator如三角形(ViewPager、ViewPager2均支持)
可以在布局或者代码中设置三角形的选中宽度和最大宽度，使三角形宽度不改变

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807173012659.gif)
activity代码：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:background="#fff"
    android:layout_height="match_parent" >
    <com.cy.tablayoutniubility.TabLayoutScroll
        android:layout_width="match_parent"
        android:background="#fff"
        android:layout_height="40dp"
        android:id="@+id/tablayout">
        <com.cy.tablayoutniubility.IndicatorTriangleView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    </com.cy.tablayoutniubility.TabLayoutScroll>
    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="#eee"/>
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:overScrollMode="never"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</LinearLayout>

```
tab item布局：

```xml
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:gravity="center"
    android:textSize="16sp"
    android:textColor="#444444"
    android:id="@+id/tv">

</TextView>
```
JAVA代码：

```java
   ViewPager2 viewPager2= findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutNiubility= findViewById(R.id.tablayout);

//        tabLayoutTriangle.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        FragPageAdapterVp2<String> fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {

            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab;
            }
        };

        TabAdapter<String> tabAdapter = new TabMediatorVp2<String>(tabLayoutNiubility, viewPager2).setAdapter(fragmentPageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("彩票");
        list.add("漫画");
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
```

## ViewPager双层嵌套(建议不要使用ViewPager2进行双层嵌套，ViewPager2嵌套滑动冲突几乎无法处理，贼鸡儿坑)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807173114131.gif)
activity布局：
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.cy.tablayoutniubility.TabLayoutScroll
        android:id="@+id/tablayout"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:background="#fff">

        <com.cy.tablayoutniubility.IndicatorLineView
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.cy.tablayoutniubility.TabLayoutScroll>

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never" />
</LinearLayout>


```
activity代码：

```java
  ViewPager viewPager= findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine= findViewById(R.id.tablayout);

//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        FragPageAdapterVp<String> fragmentPageAdapter = new FragPageAdapterVp<String>(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab1.newInstance(FragmentTab1.TAB_NAME1, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                if (position == 0) {
                    return R.layout.item_tab_msg;
                }
                return R.layout.item_tab;
            }
        };

        TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPager).setAdapter(fragmentPageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("酷玩");
        list.add("彩票");
        list.add("漫画");
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
```
Fragment代码：
```java
     viewPager = view.findViewById(R.id.view_pager);
        tabLayoutLine = view.findViewById(R.id.tablayout);
        FragPageAdapterVp<String> fragmentPageAdapter = new FragPageAdapterVp<String>(getChildFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    LogUtils.log("bindDataToTabisSelected",position);
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    LogUtils.log("bindDataToTab",position);
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab;
            }
        };
        TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPager).setAdapter(fragmentPageAdapter);
        if (getArguments() != null) {
            String tab_name1 = getArguments().getString(TAB_NAME1);
            List<String> list = new ArrayList<>();
            list.add(tab_name1 + "0");
            list.add(tab_name1 + "1");
            list.add(tab_name1 + "2");
            list.add(tab_name1 + "3");
            list.add(tab_name1 + "4");
            list.add(tab_name1 + "5");
            list.add(tab_name1 + "6");
            list.add(tab_name1 + "7");
            list.add(tab_name1 + "8");
            list.add(tab_name1 + "9");
            list.add(tab_name1 + "10");
            list.add(tab_name1 + "11");
            list.add(tab_name1 + "12");
            list.add(tab_name1 + "13");
            fragmentPageAdapter.add(list);
            tabAdapter.add(list);
        }
```

## 仿微信主页Tab
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200808223718351.gif)

activity布局：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:overScrollMode="never" />

    <com.cy.tablayoutniubility.TabLayoutNoScroll
        android:id="@+id/tablayout"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:background="#fff">
        <com.cy.tablayoutniubility.IndicatorNullView
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.cy.tablayoutniubility.TabLayoutNoScroll>


</LinearLayout>


```
item布局：

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/iv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:scaleType="centerInside" />

    <TextView
        android:id="@+id/tv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:gravity="center"
        android:textSize="12sp"></TextView>

</LinearLayout>

```
JAVA代码：

```java
 ViewPager2 viewPager2= findViewById(R.id.view_pager);
        TabLayoutNoScroll tabLayoutNoScroll= findViewById(R.id.tablayout);
        FragPageAdapterVp2NoScroll<TabBean> fragmentPageAdapter = new FragPageAdapterVp2NoScroll<TabBean>(this) {

            @Override
            public Fragment createFragment(TabBean bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position).getText());
            }

            @Override
            public void bindDataToTab(TabNoScrollViewHolder holder, int position, TabBean bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xff00ff00);
                    holder.setImageResource(R.id.iv,bean.getResID_selected());
                } else {
                    textView.setTextColor(0xff444444);
                    holder.setImageResource(R.id.iv,bean.getResID_normal());
                }
                textView.setText(bean.getText());
            }

            @Override
            public int getTabLayoutID(int position, TabBean bean) {
                if (position == 2) {
                    return R.layout.item_tab_main_circle;
                }
                return R.layout.item_tab_main;
            }
        };

        TabAdapterNoScroll<TabBean> tabAdapter = new TabMediatorVp2NoScroll<TabBean>(tabLayoutNoScroll, viewPager2).setAdapter(fragmentPageAdapter);

        List<TabBean> list = new ArrayList<>();
        list.add(new TabBean("消息",R.drawable.msg,R.drawable.msg_selected));
        list.add(new TabBean("通讯录",R.drawable.friends,R.drawable.friends_selected));
        list.add(new TabBean("朋友圈",R.drawable.circle,R.drawable.circle_selected));
        list.add(new TabBean("我",R.drawable.my,R.drawable.my_selected));
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
```



## 千古BUG：Activity销毁重启，Fragment恢复问题
当用户修改了手机字体大小，语言等，Activity会被销毁并重启，其中的Fragment会在Activity的 `super.onCreate(savedInstanceState);`中恢复，这样就出了个严重的BUG：Activity被回收，Activity持有的所有数据和对象均被回收，假如Fragment持有了Activity的某些数据或者对象，在Acitivty尚未来得及初始化这些数据和对象的时候，去恢复Fragment,这时候必然报空指针异常，这种现象尤其是在ViewPager和Fragment双层嵌套使用时尤为造孽。

虽然有很直接的办法：就是判断NULL，但这样写代码未免极其不爽，而且还不保证绝对不出问题。

也有人用这种办法：

在`onSaveInstanceState`中做手脚，然而小编试了，并不灵，而且小编不推荐这样做，因为这样做，代码肯定不健壮。

[FragmentActivity重启时Fragment状态异常的问题解决办法](https://www.jianshu.com/p/517d1c4eff1d)


```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
```

## AndroidX ViewPager中的FragmentStatePagerAdapter存在的问题

```java
  @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // If we already have this item instantiated, there is nothing
        // to do.  This can happen when we are restoring the entire pager
        // from its saved state, where the fragment manager has already
        // taken care of restoring the fragments we previously had instantiated.
        if (mFragments.size() > position) {
            Fragment f = mFragments.get(position);
            if (f != null) {
                return f;
            }
        }

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        Fragment fragment = getItem(position);
        if (DEBUG) Log.v(TAG, "Adding item #" + position + ": f=" + fragment);
        if (mSavedState.size() > position) {
            Fragment.SavedState fss = mSavedState.get(position);
            if (fss != null) {
                fragment.setInitialSavedState(fss);
            }
        }
        while (mFragments.size() <= position) {
            mFragments.add(null);
        }
        fragment.setMenuVisibility(false);
        if (mBehavior == BEHAVIOR_SET_USER_VISIBLE_HINT) {
            fragment.setUserVisibleHint(false);
        }

        mFragments.set(position, fragment);
        mCurTransaction.add(container.getId(), fragment);

        if (mBehavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            mCurTransaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED);
        }

        return fragment;
    }
```
看到一处关键代码：
```java
 if (mFragments.size() > position) {
            Fragment f = mFragments.get(position);
            if (f != null) {
                return f;
            }
        }

```
Fragment被缓存起来了，虽说节约内存，但是也容易出现身体不适，因为可能开发过程中，重新设置了adapter或者多种原因，这时，我们不希望缓存`fragment`了。

所以小编开发的轮子中，把这段使用缓存的代码删除了。


## AndroidX ViewPager2中的FragmentStateAdapter存在的问题
```java
private void ensureFragment(int position) {
        long itemId = getItemId(position);
//        if (!mFragments.containsKey(itemId)) {
            // TODO(133419201): check if a Fragment provided here is a new Fragment
            Fragment newFragment = createFragment(position);
            newFragment.setInitialSavedState(mSavedStates.get(itemId));
            mFragments.put(itemId, newFragment);
//        }
    }
```
可以看到和`Androidx`中的`ViewPager`的`FragmentStatePagerAdapter`一样缓存了`Fragment`,
小编在轮子中也把这段使用缓存的代码删除了。

## 自定义fragment
小编有超强的代码洁癖，所以不喜欢用一堆代码去解决API的某些身体不适，干脆自己自定义`fragment`，

做了基本的生命周期控制，但是生命周期肯定不如Fragment的强大，不过依然能满足基本需求，特别适用于`ViewPgaer`和`Fragmnt`双层嵌套使用的时候。

```java
public abstract class PageContainer {
    protected View view;
    protected Context context;
    private PageContainerChildManager pageContainerChildManager=new PageContainerChildManager();
    private PageContainer pageContainerParent;

    public PageContainer(PageContainer pageContainerParent) {
        this.pageContainerParent = pageContainerParent;
    }

    public Context getContext() {
        return context;
    }

    public View getView() {
        return view;
    }

    public abstract View onCreateView(LayoutInflater layoutInflater, ViewGroup container);

    public  void onResume(boolean isFirstResume){}

    public  void onStop(){}

    public  void onDestroyView(){}

    public final PageContainerChildManager getPageContainerChildManager() {
        return pageContainerChildManager;
    }

    public final PageContainer getPageContainerParent() {
        return pageContainerParent;
    }
}
```

## 自定义Fragment PageContainer 双层嵌套（ViewPager和ViewPager2均适用）

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807173114131.gif)
Activity代码：
```java
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine = findViewById(R.id.tablayout);
//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        ContainerPageAdapterVp<String> containerPageAdapterVp = new ContainerPageAdapterVp<String>(viewPager) {
            @Override
            public PageContainer onCreatePageContainer(ViewGroup container, int position, String bean) {
                LogUtils.log("onCreatePageContainer", position);
                 //该PageContainer属于第一层ViewPager,它的父PageContainer传null即可
                return new PageContainerTab1(null, bean);
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                LogUtils.log("createFragmentbindDataToTab", position);
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                if (position == 0) {
                    return R.layout.item_tab_msg;
                }
                return R.layout.item_tab;
            }
        };

        TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPager).setAdapter(containerPageAdapterVp);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("视频");
        list.add("漫画");
        containerPageAdapterVp.add(list);
        tabAdapter.add(list);
```
PageContainerTab1 代码：
```java
public class PageContainerTab1 extends PageContainer {
    private String bean;

    public PageContainerTab1(PageContainer pageContainerParent, String bean) {
        super(pageContainerParent);
        this.bean = bean;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container) {
        view = (ViewGroup) layoutInflater.inflate(R.layout.fragment_tab1, container, false);
        LogUtils.log("onCreateView");
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine = view.findViewById(R.id.tablayout);
        ContainerPageAdapterVp<String> containerPageAdapterVp = new ContainerPageAdapterVp<String>(viewPager) {
            @Override
            public PageContainer onCreatePageContainer(ViewGroup container, int position, String bean) {
                LogUtils.log("onCreatePageContainer", position);
                  //该PageContainer属于第2层ViewPager,它的父PageContainer传PageContainerTab1.this即可
                return new PageContainerTab2(PageContainerTab1.this, bean);
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab;
            }
        };
        TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPager).setAdapter(containerPageAdapterVp);
        List<String> list = new ArrayList<>();
        if (bean.equals("关注")) {
            list.add(bean + "0");
            list.add(bean + "1");
        } else if (bean.equals("推荐")) {
            list.add(bean + "0");
        } else {
            list.add(bean + "0");
            list.add(bean + "1");
            list.add(bean + "2");
            list.add(bean + "3");
            list.add(bean + "4");
            list.add(bean + "5");
            list.add(bean + "6");
            list.add(bean + "7");
            list.add(bean + "8");
            list.add(bean + "9");
            list.add(bean + "10");
            list.add(bean + "11");
            list.add(bean + "12");
            list.add(bean + "13");
        }
        containerPageAdapterVp.add(list);
        tabAdapter.add(list);
        return view;
    }

    @Override
    public void onResume(boolean isFirstResume) {
        super.onResume(isFirstResume);
        LogUtils.log("onResumetab1", bean + isFirstResume);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.log("onStop", bean);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.log("onDestroyView", bean);

    }
}

```
PageContainerTab2 代码：
```java
public class PageContainerTab2 extends PageContainer {
    private View view;
    private String bean;

    public PageContainerTab2(PageContainer pageContainerParent,String bean) {
        super(pageContainerParent);
        this.bean = bean;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container) {
        view=layoutInflater.inflate(R.layout.fragment_tab2, container, false);
        LogUtils.log("onCreateView",bean);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText(bean);
        return view;
    }

    @Override
    public void onResume(boolean isFirstResume) {
        super.onResume(isFirstResume);
        LogUtils.log("onResumetab2",bean+isFirstResume);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.log("onStop",bean);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.log("onDestroyViewPageContainerTab2",bean);

    }
}

```

## 相关API

## TabMediator
TabMediatorVp

TabMediatorVp2

TabMediatorVp2NoScroll(不可滚动)

TabMediatorVpNoScroll(不可滚动)

TabMediatorMulti(可用于ViewPager和ViewPager2,可根据item个数动态设置是否滚动)
## FragmentPageAdapter
拥有一系列`add`、`remove`函数

FragmentPageAdapterVp2（Tab可滑动，ViewPager2使用）

FragmentPageAdapterVp（Tab可滑动，ViewPager使用）

FragmentPageAdapterVp2NoScroll（Tab不可滑动，ViewPager2使用）

FragmentPageAdapterVpNoScroll（Tab不可滑动，ViewPager使用）

## TabAdapter

TabAdapter(Tab的Adapter，继承自RecyclerView的Adapter)
拥有一系列`add`、`remove`函数

TabAdapterNoScroll(Tab的Adapter，不能滚动)
## TabLayoutScroll、TabLayoutNoScroll、TabLayoutMulti、IndicatorLineView 、 IndicatorTriangleView
`TabLayoutScroll`是可滚动tab,`TabLayoutNoScroll`是不可滚动tab,里面需要嵌套`indicatorview`,可以选择`IndicatorLineView`线条indicator、`IndicatorTriangleView`三角形indicator,
`TabLayoutMulti`用于需要根据item个数动态设置是否滚动
```xml
<com.cy.tablayoutniubility.TabLayoutNiubility
        android:id="@+id/tablayout"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:background="#fff">

        <com.cy.tablayoutniubility.IndicatorLineView
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.cy.tablayoutniubility.TabLayoutNiubility>
```

## TabLayoutScroll和 indicator style设置
`TabLayoutNiubility`可设置`space`

`indicator`可设置颜色、选中长度、最大长度、高度、radius等

可在布局中使用
比如：

```xml
<com.cy.tablayoutniubility.IndicatorLineView
            android:layout_width="match_parent"
            app:width_indicator_max="80dp"
            app:width_indicator_selected="30dp"
            android:layout_height="wrap_content" />
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <attr name="height_indicator" format="dimension|reference" />
    <attr name="width_indicator_selected" format="dimension|reference"/>
    <attr name="width_indicator_max" format="dimension|reference"/>
    <attr name="color_indicator" format="color|reference"/>

    <declare-styleable name="TabGradientTextView">
        <attr name="textColorNormal" format="color|reference" />
        <attr name="textColorSelected" format="color|reference"/>
    </declare-styleable>
    <declare-styleable name="TabLayoutNiubility">
        <attr name="space_vertical" format="dimension|reference"/>
        <attr name="space_horizontal" format="dimension|reference"/>
    </declare-styleable>
    <declare-styleable name="IndicatorLineView">
        <attr name="height_indicator" />
        <attr name="width_indicator_selected"/>
        <attr name="width_indicator_max"/>
        <attr name="radius_indicator" format="dimension|reference"/>
        <attr name="color_indicator" />
    </declare-styleable>
    <declare-styleable name="IndicatorTriangleView">
        <attr name="height_indicator" />
        <attr name="width_indicator_selected"/>
        <attr name="width_indicator_max"/>
        <attr name="color_indicator" />
    </declare-styleable>
</resources>
```
可在代码中使用
比如：
```java
tabLayoutScroll.getIndicatorView().getIndicator().setColor_indicator();
```

```java
/**
     * 设置indicator进度
     * @param progress
     * @return
     */
    public Indicator setProgress(int progress) {
        this.progress = progress;
        viewIndicator.invalidate();
        return  this;
    }

    public int getProgress() {
        return progress;
    }

    public int getWidth_indicator_max() {
        return width_indicator_max;
    }

    /**
     * 设置indicator最大长度
     * @param width_indicator_max
     * @return
     */
    public Indicator setWidth_indicator_max(int width_indicator_max) {
        this.width_indicator_max = width_indicator_max;
        return  this;
    }

    /**
     * 设置indicator颜色
     * @param color_indicator
     * @return
     */
    public Indicator setColor_indicator(int color_indicator) {
        paint_indicator.setColor(color_indicator);
        return  this;
    }

    /**
     * 设置indicator高度
     * @param height_indicator
     * @return
     */
    public Indicator setHeight_indicator(int height_indicator) {
        this.height_indicator = height_indicator;
        return  this;
    }

    public int getHeight_indicator() {
        return height_indicator;
    }

    public Paint getPaint_indicator() {
        return paint_indicator;
    }

    /**
     * 设置indicator选中时的长度
     * @param width_indicator_selected
     * @return
     */
    public Indicator setWidth_indicator_selected(int width_indicator_selected) {
        this.width_indicator_selected = width_indicator_selected;
        return  this;
    }

    public int getWidth_indicator_selected() {
        return width_indicator_selected;
    }

    public int getWidth_indicator() {
        return width_indicator;
    }

    /**
     * 设置indicator当前长度
     * @param width_indicator
     * @return
     */
    public Indicator setWidth_indicator(int width_indicator) {
        this.width_indicator = Math.min(width_indicator_max, width_indicator);
        return  this;
    }
```

## 自定义indicator
1.实现`IIndicatorView` 接口
2.创建`Indicator` 对象、设置基本默认参数
3.实现`draw`方法，根据`progress`和`width_indicator`绘制自己想要的样式

比如库里提供的IndicatorTriangleView 
```java
public class IndicatorTriangleView extends View implements IIndicatorView {
    private Path path;
    private Indicator indicator;
    private int height;
    public IndicatorTriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        indicator=new Indicator(this);
        //实例化路径
        path = new Path();

        indicator=new Indicator(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorTriangleView);

        indicator.setWidth_indicator_selected(typedArray.getDimensionPixelSize(R.styleable.IndicatorTriangleView_width_indicator_selected, ScreenUtils.dpAdapt(context,12)));
        indicator.setWidth_indicator_max(typedArray.getDimensionPixelSize(R.styleable.IndicatorTriangleView_width_indicator_max, ScreenUtils.dpAdapt(context,48)));
        indicator.setHeight_indicator(typedArray.getDimensionPixelSize(R.styleable.IndicatorTriangleView_height_indicator, ScreenUtils.dpAdapt(context,6)));
        indicator.setColor_indicator(typedArray.getColor(R.styleable.IndicatorTriangleView_color_indicator, 0xffe45540));

        indicator.setWidth_indicator(ScreenUtils.dpAdapt(context,30));
        typedArray.recycle();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        height=getHeight();
    }

    @Override
    public <T extends View> T getView() {
        return (T) this;
    }

    @Override
    public Indicator getIndicator() {
        return indicator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        path.moveTo(indicator.getProgress(), height);// 此点为多边形的起点
        path.lineTo(indicator.getProgress() + indicator.getWidth_indicator() * 1f / 2, height - indicator.getHeight_indicator());
        path.lineTo(indicator.getProgress() + indicator.getWidth_indicator(), height);
        path.close(); // 使这些点构成封闭的多边形
        indicator.getPaint_indicator().setStyle(Paint.Style.FILL);
        canvas.drawPath(path, indicator.getPaint_indicator());
    }
}

```

## 实现原理剖析

## 说真的，这自定义控件还真不简单
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807184654203.png)

## 涉及到的难点场景

## 搞清楚ViewPager监听的onPageSelected、onPageScrolled和onPageScrollStateChanged回调执行特点

```java
   /**
         * This method will be invoked when the current page is scrolled, either as part
         * of a programmatically initiated smooth scroll or a user initiated touch scroll.
         *
         * @param position Position index of the first page currently being displayed.
         *                 Page position+1 will be visible if positionOffset is nonzero.
         * @param positionOffset Value from [0, 1) indicating the offset from the page at position.
         * @param positionOffsetPixels Value in pixels indicating the offset from position.
         */
        public void onPageScrolled(int position, float positionOffset,
                @Px int positionOffsetPixels) {
        }

        /**
         * This method will be invoked when a new page becomes selected. Animation is not
         * necessarily complete.
         *
         * @param position Position index of the new selected page.
         */
        public void onPageSelected(int position) {
        }

        /**
         * Called when the scroll state changes. Useful for discovering when the user begins
         * dragging, when a fake drag is started, when the pager is automatically settling to the
         * current page, or when it is fully stopped/idle. {@code state} can be one of {@link
         * #SCROLL_STATE_IDLE}, {@link #SCROLL_STATE_DRAGGING} or {@link #SCROLL_STATE_SETTLING}.
         */
        public void onPageScrollStateChanged(@ScrollState int state) {
        }
```

**首次进入viewPager,回调如下：**

```cpp
onPageSelected: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
```
**手指向左拖动，viewapger从index切换到第index+1,回调如下：**

可以发现当手指松开，ViewPager从`SCROLL_STATE_DRAGGING`到达`SCROLL_STATE_SETTLING`（自动滚动状态），`onPageSelected`先执行，`onPageScrolled` position从`index` 慢慢到`index+1`
```cpp
LOG_E: ----------------------------------->>>>onPageScrollStateChangedSCROLL_STATE_DRAGGING
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
LOG_E: ----------------------------------->>>>onPageScrollStateChangedSCROLL_STATE_SETTLING
onPageSelected: ----------------------------------->>>>1
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>1
LOG_E: ----------------------------------->>>>onPageScrollStateChangedSCROLL_STATE_IDLE

```

**手指向右拖动，viewapger从index切换到第index-1,回调如下：**

```cpp
onPageScrolled: ----------------------------------->>>>1
LOG_E: ----------------------------------->>>>onPageScrollStateChangedSCROLL_STATE_IDLE
LOG_E: ----------------------------------->>>>onPageScrollStateChangedSCROLL_STATE_DRAGGING
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
LOG_E: ----------------------------------->>>>onPageScrollStateChangedSCROLL_STATE_SETTLING
onPageSelected: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
onPageScrolled: ----------------------------------->>>>0
LOG_E: ----------------------------------->>>>onPageScrollStateChangedSCROLL_STATE_IDLE

```

可以发现当手指开始拖动，`onPageScrolled` position从`index` 直接变成`index-1`,
当手指松开，ViewPager从`SCROLL_STATE_DRAGGING`到达`SCROLL_STATE_SETTLING`（自动滚动状态），`onPageSelected`执行，`onPageScrolled` position不再改变，直到SCROLL_STATE_IDLE（自动滚动停止）

**总结**：onPageSelected先执行（粗略来说），手指向左拖动,onPageScrolled position是当前item的`position+1`,手指向右拖动,onPageScrolled position是当前item的`position-1`,搞懂这点是关键。

## 自定义HorizontalRecyclerView实现TabLayout
之所以选择RecyclerView做tabLayout，是因为`RecyclerView`最适用于多item的布局，不仅因为它有缓存的功能、还因为使用起来极其方便简单，个人觉得，android里`recyclerView`的设计是超级奶思的。

因为TabLayout需要根据ViewPager的滑动来滑动，但RecyclerView的`scrollTo`函数是空的，没有任何作用，这样滑动控制就会变得困难。不过我们可以override，自己实现它，通过`scrollBy`函数滑动，设置滑动监听事件，记录偏移量`offsetX`，这样，我们就可以做到scrollTo是有作用的。
```java
public class HorizontalRecyclerView extends RecyclerView {
    private LinearItemDecoration linearItemDecoration;
    //永远<=0
    private int offsetX = 0;
    public HorizontalRecyclerView(Context context) {
        this(context, null);
    }

    public HorizontalRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                offsetX -= dx;
            }

        });
        SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) getItemAnimator();
        if (simpleItemAnimator != null) simpleItemAnimator.setSupportsChangeAnimations(false);

    }

    /**
     * x为正，表示手指往左滑,x为负，表示手指往右滑
     *
     * @param x
     * @param y
     */
    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    /**
     * x<=0
     * 比如 x=0,表示滑动到RecyclerView最左边，完全显示第一个item,
     * 比如 x=-100,表示RecyclerView左边100像素的界面被隐藏
     *
     * @param x
     * @param y
     */
    @Override
    public void scrollTo(int x, int y) {
        scrollBy(offsetX - x, y);
    }

    public int getOffsetX() {
        return offsetX;
    }


    @Override
    public void setAdapter(Adapter adapter) {
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        super.setAdapter(adapter);
    }
}

```

手指滑动ViewPager，保证选中的TabLayout的item在正中间

手指滑动ViewPager,TabLayout跟着滑动

手指点击TabLayout,ViewPager跟着滑动

手指滑动TabLayout,再点击TabLayout

 手指滑动TabLayout,再触摸ViewPager
 
手指滑动TabLayout,再滑动ViewPager

 今日头条存在一个体验不好的场景：快速滑动TabLayout,ViewPager在TabLayout停止滑动之前就停止了滑动，这时，将看不到indicator，然而小编的TabLayoutNiubility解决了这个问题

## 源码如下

 

```java
package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/7/29 18:42
 * @UpdateUser:
 * @UpdateDate: 2020/7/29 18:42
 * @UpdateRemark:
 * @Version:
 */
public class TabLayoutMediatorVp2<T> {
    private TabLayoutNiubility tabLayout;
    private ViewPager2 viewPager2;
    private TabAdapter<T> tabAdapter;
    private int position_scroll_last = 0;
    private int diff = 0;
    private int diff_click = 0;
    private int toScroll = 0;
    private int offsetX_last = 0;
    private int offsetX_last_click = 0;
    private int offsetX_touch = 0;
    private boolean rvScrolledByVp = false;
    private boolean rvScrolledByTouch = false;
    private boolean scrolledByClick = false;
    private int position_selected_last = 0;
    private boolean op_click_last = false;
    private int click_position_last = -1;

    public TabLayoutMediatorVp2(TabLayoutNiubility tabLayout, final ViewPager2 viewPager2) {
        this.tabLayout = tabLayout;
        this.viewPager2 = viewPager2;
    }

    public TabAdapter<T> setAdapter(final FragmentPageAdapterVp2<T> fragmentPageAdapter) {
        tabAdapter = new TabAdapter<T>() {
            @Override
            public void bindDataToView(TabViewHolder holder, int position, T bean, boolean isSelected) {
                fragmentPageAdapter.bindDataToTab( holder, position, bean, isSelected);
            }

            @Override
            public int getItemLayoutID(int position, T bean) {
                return fragmentPageAdapter.getTabLayoutID(position, bean);
            }

            @Override
            public void onItemClick(TabViewHolder holder, int position, T bean) {
                //点击tabLayout的item,会先回调onPageSelected,然后回调onPageScrolled
                //标志复位
                rvScrolledByTouch = false;
                offsetX_touch = 0;
                //标志：tablayout的滑动是由点击item触发的
                scrolledByClick = true;
                position_selected_last = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(position);
                //让indicator立马指向currentItem
                RecyclerView.ViewHolder viewHolder = tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(viewPager2.getCurrentItem());
                if (viewHolder != null) {

                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected())
                            .setProgress((int) (viewHolder.itemView.getLeft()
                                    + viewHolder.itemView.getWidth() * 1f / 2
                                    - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2));

                } else {
                    //不可见，width_indicator为0
                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                }
                fragmentPageAdapter.onTabClick( holder, position, bean);
            }
        };


        tabLayout.getHorizontalRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果是手指滑动tabLayout，需要记录滑动的距离
                if (!rvScrolledByVp) {
                    rvScrolledByTouch = true;
                    offsetX_touch -= dx;
                }
                //indicator需要跟着滑动
                RecyclerView.ViewHolder viewHolder = tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(viewPager2.getCurrentItem());
                if (viewHolder != null) {
                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected())
                            .setProgress((int) (viewHolder.itemView.getLeft()
                                    + viewHolder.itemView.getWidth() * 1f / 2
                                    - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2));
                } else {
                    //不可见，width_indicator为0
                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                }

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //通知tabAdapter更新选中项
                tabAdapter.setPositionSelected(viewPager2.getCurrentItem());
            }

            /**注意：滑动很快的时候，即使到了另外的page,positionOffsetPixels不一定会出现0
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position,positionOffset,positionOffsetPixels);
                int centerX = (int) (tabLayout.getWidth() * 1f / 2);
                //说明上次手指滑动了tabLayout，现在手指滑动viewpager,需要将tablayout复位
                if (rvScrolledByTouch && offsetX_touch != 0) {
                    tabLayout.getHorizontalRecyclerView().stopScroll();
                    //标志不是由手指滑动tablayout
                    rvScrolledByVp = true;
                    tabLayout.getHorizontalRecyclerView().scrollBy(offsetX_touch, 0);
                    rvScrolledByVp = false;
                    //立刻复位
                    rvScrolledByTouch = false;
                    offsetX_touch = 0;
                    //这里不能修改position_scroll_last，因为只要上次手指滑动了tablayout,然后手指滑动viewapger，onPageScrolled会被回调多次
                    //在后面去修改position_scroll_last即可
//                    position_scroll_last = position;
                    return;
                }
                //点击item后，onPageSelected先回调，然后还会继续回调onPageScrolled，直到onPageScrolled=position_selected，从page index 滑动到 page index+1，
                //position == viewPager2.getCurrentItem() - 1说明点击的item在当前position之后
                //position == viewPager2.getCurrentItem()说明点击的item在当前position之前
                //viewpager滑动中，才处理，
                if (scrolledByClick) {
                    if ((position == viewPager2.getCurrentItem() - 1 || position == viewPager2.getCurrentItem())) {
                        RecyclerView.ViewHolder viewHolder = tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(viewPager2.getCurrentItem());
                        if (viewHolder != null) {
                            //indicator想要指向正中间，计算TabLayout需要滑动的距离
                            if (diff_click == 0)
                                diff_click = (int) (viewHolder.itemView.getLeft() + viewHolder.itemView.getWidth() * 1f / 2 - centerX);
                            //获取tablayout的偏移量，永远<=0
                            if (offsetX_last_click == 0)
                                offsetX_last_click = tabLayout.getHorizontalRecyclerView().getOffsetX();
                            if (positionOffset != 0) {
                                //scrollBy调用一次，onScrolled回调一次
                                //标志不是由手指滑动tablayout
                                rvScrolledByVp = true;
                                //往右滑
                                if (position_selected_last < viewPager2.getCurrentItem()) {
                                    tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last_click - (diff_click * positionOffset)), 0);
                                } else {
                                    //往左滑
                                    tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last_click - (diff_click * (1 - positionOffset))), 0);
                                }
                                rvScrolledByVp = false;
                            }

                        } else {
                            //不可见，width_indicator为0
                            tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                        }

                    }
                    position_scroll_last = position;
                    return;
                }
                /**
                 * 手指左右滑动Viewpager，触发下面所有代码
                 */
                TabViewHolder viewHolder = (TabViewHolder) tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(position);
                if (viewHolder != null) {
                    int width_half = (int) (viewHolder.itemView.getWidth() * 1f / 2);
                    int left = viewHolder.itemView.getLeft();
                    int space = tabLayout.getHorizontalRecyclerView().getItemDecoration().getSpace_horizontal();
                    TabViewHolder viewHolder_behind = (TabViewHolder) tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(position + 1);
                    if (position == 0) {
                        //TabLayout刚显示，indicator会指向第0个item
                        diff = 0;
                        offsetX_last = 0;
                        if (viewHolder_behind != null)
                            //计算indicator指向下一个item需要滑动的距离
                            toScroll = (int) (width_half
                                    + tabLayout.getHorizontalRecyclerView().getItemDecoration().getSpace_horizontal()
                                    + viewHolder_behind.itemView.getWidth() * 1f / 2);
                    } else if (position_scroll_last < position) {
                        //说明从page index 滑动到了page index+1,
                        if (viewHolder_behind != null) {
                            //indicator想要指向正中间，计算TabLayout需要滑动的距离
                            diff = (int) (viewHolder_behind.itemView.getLeft() + viewHolder_behind.itemView.getWidth() * 1f / 2 - centerX);
                            //下一个item都在正中间的前面，无需滑动，而且可以避免出现负数导致recyclerView抖动
                            if (diff < 0) diff = 0;
                            //获取上次tablayout的偏移量，永远<=0
                            offsetX_last = tabLayout.getHorizontalRecyclerView().getOffsetX();
                            //计算indicator指向下一个item需要滑动的距离
                            toScroll = (int) (width_half
                                    + tabLayout.getHorizontalRecyclerView().getItemDecoration().getSpace_horizontal()
                                    + viewHolder_behind.itemView.getWidth() * 1f / 2);
                        }

                    } else if (position_scroll_last > position) {
                        //说明从page index 滑动到了page index-1
                        //indicator想要指向正中间，计算TabLayout需要滑动的距离
                        diff = (int) (left + width_half - centerX);
                        //position的item在正中间的后面，无需滑动，而且可以避免出现正数导致recyclerView抖动
                        if (diff > 0) diff = 0;
                        //获取上次tablayout的偏移量，永远<=0
                        offsetX_last = tabLayout.getHorizontalRecyclerView().getOffsetX();
                        if (viewHolder_behind != null)
                            //计算indicator指向position的item需要滑动的距离
                            toScroll = (int) (width_half
                                    + tabLayout.getHorizontalRecyclerView().getItemDecoration().getSpace_horizontal()
                                    + viewHolder_behind.itemView.getWidth() * 1f / 2);
                    } else if (op_click_last) {
                        //如果position_scroll_last==position,并且上次操作是点击item,
                        if (position == click_position_last) {
                            //说明现在是正要从page index 滑动到page index+1
                            if (viewHolder_behind != null) {
                                //indicator想要指向正中间，计算TabLayout需要滑动的距离
                                diff = (int) (viewHolder_behind.itemView.getLeft() + viewHolder_behind.itemView.getWidth() * 1f / 2 - centerX);
                                //获取上次tablayout的偏移量，永远<=0
                                offsetX_last = tabLayout.getHorizontalRecyclerView().getOffsetX();
                                //计算indicator指向position的item需要滑动的距离
                                toScroll = (int) (width_half
                                        + tabLayout.getHorizontalRecyclerView().getItemDecoration().getSpace_horizontal()
                                        + viewHolder_behind.itemView.getWidth() * 1f / 2);
                            }
                        }
                        op_click_last = false;
                    }
                    //diff==0,无需滑动，positionOffset==0,无需滑动，当前position和上次滑动的position相等，才执行滑动操作
                    if (diff != 0 && positionOffset != 0 && position_scroll_last == position) {
                        //标志，tabLayout滑动，不是因为手指滑动tablayout导致的
                        rvScrolledByVp = true;
                        if (diff > 0) {
                            //scrollBy调用一次，onScrolled回调一次
                            //手指往左滑动，positionOffset由小变大
                            tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last - (diff * positionOffset)), 0);
                        } else {
                            //手指往右滑动，positionOffset由大变小
                            tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last - (diff * (1 - positionOffset))), 0);
                        }
                        //标志复位
                        rvScrolledByVp = false;
                    }
                    //计算Width_indicator,Width_indicator由小变大再变小，2个item中间时最大
                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(Math.max(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected(),
                            (int) (tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected() +
                                    (positionOffset == 0 ? 0 : tabLayout.getIndicatorView().getIndicator().getWidth_indicator_max() * (0.5 - Math.abs(0.5 - positionOffset))))))
                            .setProgress((int) (left
                                    + width_half
                                    - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2
                                    + (toScroll * positionOffset)));

                    if (toScroll != 0)
                        //手指往左滑动，positionOffset由小变大
                        //手指往右滑动，positionOffset由大变小
                        if (viewHolder_behind != null)
                            fragmentPageAdapter.onTabScrolled(viewHolder, position, false, 1 - positionOffset,
                                    viewHolder_behind, position + 1, true, positionOffset);

                } else {
                    //viewpager嵌套viewpager的时候，内层viewpager向右滑动了以后又向左滑动，会导致tablayout
                    //position对应的item不可见，所以要滑动到对应的position
                    tabLayout.getHorizontalRecyclerView().scrollToPosition(position);
                    viewHolder = (TabViewHolder) tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(position);
                    //scrollToPosition一调用，不会立马滑动完毕，所以还会有存在null的时候，
                    if(viewHolder!=null){
                        int width_half = (int) (viewHolder.itemView.getWidth() * 1f / 2);
                        int left = viewHolder.itemView.getLeft();
                        tabLayout.getIndicatorView().getIndicator().setWidth_indicator(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected())
                                .setProgress((int) (left
                                        + width_half
                                        - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2));
                    }else {
                        tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                    }

                }

                position_scroll_last = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case SCROLL_STATE_IDLE:
                        //记录上次操作的是点击item
                        if (scrolledByClick) {
                            click_position_last = viewPager2.getCurrentItem();
                            op_click_last = true;
                        }
                        //标志复位
                        scrolledByClick = false;
                        diff_click = 0;
                        offsetX_last_click = 0;
                        break;
                }
            }
        });

        tabLayout.setAdapter(tabAdapter);

        viewPager2.setAdapter(fragmentPageAdapter);

        return tabAdapter;
    }

}

```
处理过程超鸡儿复杂，千万别想着能看懂，要是能看懂，只能说明你是万中无一的绝世高手，能知道大概干了些什么就可以了。

## TabLayout的item宽度均分
用LinearLayout做tablayout，每个item的`weight`设置为1
```java
        removeAllViews();
        for (int i = 0; i < tabNoScrollAdapter.getItemCount(); i++) {
            TabNoScrollViewHolder tabNoScrollViewHolder = tabNoScrollAdapter.onCreateViewHolder(i, tabNoScrollAdapter.getList_bean().get(i), this);
            tabNoScrollViewHolder.setPositionAdapter(i);
            sparseArrayViewHolder.put(i, tabNoScrollViewHolder);
            addView(tabNoScrollViewHolder.itemView, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            ItemChanged(i);
        }
```
## RecyclerView的item刷新如何做到不闪烁
禁用默认的刷新动画
```java
 SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) getItemAnimator();
 if (simpleItemAnimator != null) simpleItemAnimator.setSupportsChangeAnimations(false);
```

## UML类图如下

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200808231445477.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NvbmZ1c2luZ19hd2FrZW5pbmc=,size_16,color_FFFFFF,t_70)
为了更清晰易懂，小编画得不正规，比较随意,该UML是老的，不是最新版本，最新版本，名字 有改动。

## 面向接口编程（面向多态编程）的思想
小编特别喜欢JAVA这门语言，小编个人认为JAVA将面向对象编程的思想展现的淋漓尽致。

整个轮子用得最多的编程思想就是多态，`多态`是设计模式和框架的基础。

`接口`和`泛型`是实现多态的2把利器。

编程思想暂且稍微透露，后面小编会专门出一个SDK开发入门教程，详细讲述设计模式和多态，敬请关注。
## 欢迎联系、指正、批评



Github:[https://github.com/AnJiaoDe](https://github.com/AnJiaoDe)

简书：[https://www.jianshu.com/u/b8159d455c69](https://www.jianshu.com/u/b8159d455c69)

CSDN：[https://blog.csdn.net/confusing_awakening](https://blog.csdn.net/confusing_awakening)

ffmpeg入门教程:[https://www.jianshu.com/p/042c7847bd8a](https://www.jianshu.com/p/042c7847bd8a)

 微信公众号
 ![这里写图片描述](https://imgconvert.csdnimg.cn/aHR0cDovL3VwbG9hZC1pbWFnZXMuamlhbnNodS5pby91cGxvYWRfaW1hZ2VzLzExODY2MDc4LWZjZmJiNDUxNzVmOTlkZTA)

QQ群

![这里写图片描述](https://imgconvert.csdnimg.cn/aHR0cDovL3VwbG9hZC1pbWFnZXMuamlhbnNodS5pby91cGxvYWRfaW1hZ2VzLzExODY2MDc4LWEzMWZmNDBhYzY4NTBhNmQ)

