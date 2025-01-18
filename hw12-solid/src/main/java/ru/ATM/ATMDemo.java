package ru.ATM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ATM.Cash.Banknote;
import ru.ATM.Cash.CashMachine;
import ru.ATM.Cash.CashSections;
import ru.ATM.Interfaces.IATMSections;



public class ATMDemo {
    private static final Logger log =  LoggerFactory.getLogger(ATMDemo.class);

    public static void main(String[] args) {
        var banknotes = new Banknote[]{
                new Banknote(100, "One hundred rubles"),
                new Banknote(500, "Five hundred rubles"),
                new Banknote(1000, "One thousand rubles"),
                new Banknote(5000, "Five thousand rubles")
        };
        CashMachine ATM = new CashMachine(banknotes);

        IATMSections<Integer> packCash = new CashSections(banknotes);
        int i = packCash.putInSection(banknotes[3], 3);
        i = packCash.putInSection(banknotes[2], 1);
        i = packCash.putInSection(banknotes[1], 1);
        ATM.put(packCash);

        log.info("Amount credited, updated balance: {} ", ATM.getBalance());

        packCash = ATM.get(5500);

        log.info("Requested amount {} issued, updated balance: {} ",  packCash.getBalance(), ATM.getBalance());
    }
}
