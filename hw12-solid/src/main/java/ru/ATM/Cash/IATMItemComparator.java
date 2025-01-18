package ru.ATM.Cash;

import ru.ATM.Interfaces.IATMItem;
import java.util.Comparator;

public class IATMItemComparator implements Comparator<IATMItem<Integer>> {
    @Override
    public int compare(IATMItem<Integer> o1, IATMItem<Integer> o2) {
        int sigNum=-1;  //  reverse
        if( o1==null && o2==null )
            return 0;
        if(o2 == null)
            return sigNum;
        if(o1 == null)
            return -sigNum;

        int s1=o1.getSize();
        int s2=o2.getSize();

        if(s1 == s2)
            return 0;

        if(s1 > s2)
            return sigNum;

        return -sigNum;
    }
}
