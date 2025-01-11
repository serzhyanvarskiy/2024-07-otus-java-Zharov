package ru.ATM.Cash;


import ru.ATM.Interfaces.IATMItem;
import java.util.Set;
import java.util.TreeMap;

public final class CashSections implements ru.ATM.Interfaces.IATMSections<Integer>{
    private final TreeMap<IATMItem<Integer>, Integer> basket= new TreeMap <>(new IATMItemComparator());

    public CashSections(Banknote[] banknotes) {
        if(banknotes!=null)
            this.addSections(banknotes);
    }
    public void addSections(Banknote[] banknotes) {
        for(Banknote b: banknotes)
            addNewSection(b);
    }

    @Override
    public int addNewSection(IATMItem<Integer> section) {
        if(section!=null && !basket.containsKey(section)) {
            basket.put(section, 0);
            return 0;
        }
        return 1;
    }

    @Override
    public int removeSection(IATMItem<Integer> section) {
        return 0;
    }

    @Override
    public int putInSection(IATMItem<Integer> section, int count) {
        boolean isBanknote=basket.containsKey(section);
        if(isBanknote && count>0) {
            basket.put(section, basket.get(section)+count);
            return 0;
        }
        return 1;
    }

    @Override
    public int getInSection(IATMItem<Integer> section, int count) {
        if(count <0 || !basket.containsKey(section) || count > basket.get(section) )
            return 1;
        basket.put(section, basket.get(section)-count);
        return 0;
    }

    @Override
    public Integer getCountInSection(IATMItem<Integer> section) {
        if(section !=null && basket.containsKey(section))
            return basket.get(section);
        return -1;
    }

    @Override
    public Integer getBalanceInSection(IATMItem<Integer> section) {
        if(section !=null && basket.containsKey(section))
            return basket.get(section)*section.getSize();
        return -1;
    }

    @Override
    public Integer getBalance() {
        var key= basket.keySet();
        int sum=0;
        for(var section : key)
            sum+=getBalanceInSection(section);

        return sum;
    }
    @Override
    public Set<IATMItem<Integer>> getAllSections() {
        return basket.keySet();
    }

    @Override
    public IATMItem<Integer> getMinSection() {
        return basket.lastKey();
    }
}
