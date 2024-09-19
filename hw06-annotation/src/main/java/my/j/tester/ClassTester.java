package my.j.tester;

import java.util.function.Function;
import my.j.annotations.After;
import my.j.annotations.Before;
import my.j.annotations.Test;
import my.j.math.Calc;

public class ClassTester {

    int num_test = 0;
    // enum func_type = {sum' 'mult'};
    @Before
    String start_test(int num_test) {
        this.num_test = num_test;

        String str_start = this.getClass().getName() + ": test #" + Integer.toString(num_test) + " is started";
        System.out.println(str_start);
        return str_start;
    }

    boolean call_test_func(Integer[] args, Function<Integer[], Integer> f, int test_res, String name_test) {
        System.out.println(String.format("Start test %s...", name_test));
        String info = String.format("Test %s is", name_test);
        int res = f.apply(args);
        boolean success = res == test_res;
        if (success) {
            info += " successful.";
        } else {
            info += " failed...";
        }
        System.out.println(info);
        return success;
    }

    @Test
    boolean call_test_func_sum() {
        return call_test_func(new Integer[] {2, 2}, Calc::sum, 4, "summator");
    }

    @Test
    boolean call_test_func_mult() {
        return call_test_func(new Integer[] {5, 5}, Calc::multiplication, 25, "multiplication");
    }

    @After
    String final_test_func() {
        String str_final = this.getClass().getName() + ": test #" + Integer.toString(num_test) + " is completed";
        System.out.println(str_final);
        return str_final;
    }
}
