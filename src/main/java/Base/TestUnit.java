package Base;

import java.util.LinkedHashMap;

//测试单元的集合
public class TestUnit extends TestBase{

    private LinkedHashMap<String,TestCase> caseMap;//case集合

    public LinkedHashMap<String,TestCase> getCaseMap() {
        return caseMap;
    }

    public void setCaseMap(LinkedHashMap<String,TestCase> caseMap) {
        this.caseMap = caseMap;
    }

    @Override
    public String toString() {
        return "TestUnit [caseMap=" + caseMap + "]";
    }

}
