package com.parser.netDataParser.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class UtilTest {

    @Parameter
    public String actualValue;

    @Parameter(1)
    public Boolean expectedValue;

    @Parameters
    public static List<Object> data() {
        return Arrays.asList(
                new Object[][]{
                        {".csv", true}, {".xls", true},
                        {"ccv", false},{".",false},
                        {"",false}

                }
        );

    }

    @Test
    public void test_isValidFileFormat(){
        Assert.assertEquals(expectedValue,Util.isValidFileFormat(actualValue));
    }

}





