-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
    #这行不能加，加了就会失败
#    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class androidx.** {*;}