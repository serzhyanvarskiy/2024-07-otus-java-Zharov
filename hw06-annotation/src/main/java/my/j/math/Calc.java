package my.j.math;

public interface Calc {
    public static int sum(Integer s[]) {
        int sum_res = 0;
        for (int i : s) {
            sum_res += i;
        }
        return sum_res;
    }

    public static int multiplication(Integer s[]) {
        int res = 1;
        for (int i : s) {
            res *= i;
        }
        return res;
    }
}
