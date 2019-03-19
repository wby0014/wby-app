package number2;

/**
 * p155 定义一个可用于比较的数据类型
 *
 * @author wubinyu
 * @date 2019/1/18 9:33.
 */
public class ComparableDate implements Comparable<ComparableDate> {

    private final int day;
    private final int month;
    private final int year;

    public ComparableDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(ComparableDate that) {
        if (this.year > that.year) {
            return +1;
        }
        if (this.year < that.year) {
            return -1;
        }
        if (this.month > that.month) {
            return +1;
        }
        if (this.month < that.month) {
            return -1;
        }
        if (this.year > that.year) {
            return +1;
        }
        if (this.year < that.year) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ComparableDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
