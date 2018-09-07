package TestScript.ElementManage.BalancePage;

import org.openqa.selenium.By;

public class AccountPage extends AddAccountPage{

    //资产金额
    public static final By account=By.id("com.seele.tigerwallet:id/total_tv");

    //隐藏图标
    public static final By eyeimg=By.id("com.seele.tigerwallet:id/switch_img");

    //添加资产
    public static final By addaccount=By.id("com.seele.tigerwallet:id/floatingActionButton");

    //扫描
    public static final By scanpic=By.id("com.seele.tigerwallet:id/action_scanning");

    //ETH钱包
    public static final By ETHPath=By.xpath("//android.widget.TextView[@text='ETH']");

    public static final String ETHPath1 ="new UiSelector().text('ETH')";

}


class AddAccountPage{//添加资产界面元素

    //页面标题
    public static final By addtitle=By.id("com.seele.tigerwallet:id/middle_title");

    //返回按钮
    public static final By addback=By.id("com.seele.tigerwallet:id/btn_back");

    //搜索图标
    public static final By search=By.id("com.seele.tigerwallet:id/right_img");

    //添加按钮
    //android.widget.TextView[@text='MTH']/../../android.widget.LinearLayout[1]

    //搜索输入框
    public static final By searchtext=By.xpath("//android.widget.EditText[@text='请输入Token名称或合约地址']");

    //取消搜索
    public static final By canclesearch=By.id("com.seele.tigerwallet:id/right_text");

    //搜素结果为空
    public static final By nullresult=By.id("//android.widget.TextView[@text='未找到匹配结果']");

}

