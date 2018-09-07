package TestScript.ElementManage.UserPage;

import org.openqa.selenium.By;

public class LinkManPage {

    //返回按钮
    public static final By linkback=By.id("com.seele.tigerwallet:id/btn_back");

    //添加联系人
    public static final By addlinkman=By.id("com.seele.tigerwallet:id/right_img");

    //联系人姓名
    public static final By linkname=By.id("com.seele.tigerwallet:id/name_edt");

    //联系人备注
    public static final By linknote=By.xpath("//android.widget.LinearLayout[@resource-id='remarks']/android.widget.FrameLayout[1]/android.widget.EditText[1]");

    //联系人钱包地址
    public static final By linkadress=By.id("com.seele.tigerwallet:id/address_edt");

    //保存按钮
    public static final By linksave=By.id("com.seele.tigerwallet:id/right_text");


}
