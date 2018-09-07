package TestScript.ElementManage.CreatOrImportPage;
import org.openqa.selenium.By;
public class CreatWordsPage {
    //刷新助记词
    public static final By  refreshWord=By.id("com.seele.tigerwallet:id/layout_mnemonic_refresh");

    //什么是助记词
    public static final By  whatWord=By.id("com.seele.tigerwallet:id/tv_mnemonic_exp");

    //我记下来了按钮
    public static final By  remember=By.id("com.seele.tigerwallet:id/btn_record");

    //助记词，按索引排序
    public static final By word1=By.xpath("//android.support.v7.widget.RecyclerView/android.widget.TextView[1]");
}
