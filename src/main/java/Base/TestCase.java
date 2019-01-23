package Base;
import lombok.Data;
import java.util.List;

//测试的case集合
@Data
public class TestCase extends TestBase{
    private List<TestStep> steps;//测试步骤集合

    public List<TestStep> getSteps() {
        return steps;
    }

    public void setSteps(List<TestStep> steps) {
        this.steps = steps;
    }

    public String getCaseName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "TestCase "+super.getName()+" [steps=" + steps + "]";
    }


}
