package ru.ATM.Cash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ATM.Interfaces.IATMSections;

public class CashMachine implements  ru.ATM.Interfaces.IATM<Integer>{
    private static final Logger log = LoggerFactory.getLogger(CashMachine.class);
    private int balance=0;
    private final CashSections basket = new CashSections(null);
    public CashMachine(Banknote[] banknotes) {
        if(banknotes == null) return;
        basket.addSections(banknotes);
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public Integer put(IATMSections<Integer> packet) {
        int res=0;
        if(packet==null)
            return -1;
        for(var sections : packet.getAllSections())
            res+=basket.putInSection(sections, packet.getCountInSection(sections));
        balance= basket.getBalance();
        return res;
    }

    @Override
    public IATMSections<Integer> get(Integer sum) {
        if( sum > balance) {
            log.info("The operation of receiving funds is not possible. Insufficient funds in the account...");
            return null;
        }

        return findSumInBasket(sum);
    }

    private CashSections findSumInBasket(Integer sum) {
        var iBanknotsBox = basket.getAllSections().iterator();
        var min = basket.getMinSection().getSize();
        if(sum % min != 0)
        {
            log.info("Set a multiple of the amount {}.", min);
            return null;
        }
        int actualSum=sum;
        CashSections pack = new CashSections(null);
        while (iBanknotsBox.hasNext()){
            var section=iBanknotsBox.next();
            int count=basket.getCountInSection(section);
            int max = actualSum / section.getSize();
            if(max == 0 || count ==0)
                continue;
            pack.addNewSection(section);
            int tail = actualSum % section.getSize();

            if(count >= max) {
                basket.getInSection(section, max);
                balance = basket.getBalance();
                pack.putInSection(section, max);
                if(tail == 0) return pack;
                actualSum-=max * section.getSize();
            }
        }
        log.info("The amount cannot be issued in existing banknotes. The request for {} will be granted", pack.getBalance());
        return null;
    }

}
