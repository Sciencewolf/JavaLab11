import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static Loading loading = new Loading();
    public static void main(String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        Process process = new Process();
        //while(true){
        System.out.println("Input way of payment");
        System.out.print("[VISA{1} MASTERCARD{2} NFC{3} CRYPTO{4} CASH{5} EXIT{6}]\n>>> ");
        int form_of_payment = sc.nextInt();
        switch(form_of_payment){
            case 1 -> process.VISA();
            case 2 -> process.MASTERCARD();
            case 3 -> process.NFC();
            case 4 -> process.CRYPTO();
            case 5 -> process.CASH();
            case 6 -> System.exit(0);
        }
        //}
    }

    public static void do_stuff(int times) throws InterruptedException{
        for(int i=0;i<times;i++){
            loading.animate();
            Thread.sleep(400);
        }
        System.out.println();
    }
}


interface ATM{
    void VISA();
    void MASTERCARD();
    void NFC() throws InterruptedException;
    void CRYPTO() throws InterruptedException;
    void CASH();
}

class Process implements ATM {
    final String CYAN = "\033[0;36m";
    final String RESET = "\033[0m";
    Random rand = new Random();
    public Scanner sc = new Scanner(System.in);

    @Override
    public void VISA() {
        System.out.println("Welcome to VISA system");
        int len, input, correct_pin = 4666, inputted_pin, chance = 2;
        int balance = rand.nextInt(1000), amount_to_pay = rand.nextInt(400);
        String card_number;
        char startWith;
        while(true) {
            System.out.print("Input your credit card: [1] - true [0] - exit\n>>> ");
            input = sc.nextInt();
            if (input == 0) System.exit(0);
            System.out.print("\nEnter your card number\n>>> ");
            card_number = sc.next(); // correct 4263982640269299  incorrect 5263982640269299  pin_c 4666
            len = card_number.length();
            startWith = card_number.charAt(0);

            if(startWith == '4') {
                if (len == 16) {
                    while(true) {
                        System.out.print("Enter your PIN code\n>>> ");
                        inputted_pin = sc.nextInt();
                        if (inputted_pin == correct_pin) {System.out.println("PIN code is correct");break;}
                        else {System.out.println(CYAN + "Incorrect PIN code" + RESET);
                            chance--;}
                        if(chance<0){
                            System.out.println(CYAN + "Sorry. Try again" + RESET);
                            System.exit(0);
                        }
                    }
                    if(balance > amount_to_pay){
                        System.out.println("Your balance: " + balance + '$');
                        System.out.println("To pay: " + amount_to_pay + '$');
                        balance -= amount_to_pay;
                        System.out.println("\nYour balance: " + balance + '$');
                        System.out.println("Collect your purchases ");
                    }  else{
                        System.out.println("Your balance: " + balance + '$');
                        System.out.println("To pay: " + amount_to_pay + '$');
                        System.out.println(CYAN + "Transaction failed. Try again" + RESET);
                    }
                }
            } else{
                System.out.println(CYAN + "Incorrect card number. Try again" + RESET);
            }
        }
    }

    @Override
    public void MASTERCARD() {
        System.out.println("Welcome to MASTERCARD system");
        int len, input, correct_pin = 2155, inputted_pin, chance = 2;
        int balance = rand.nextInt(1000), amount_to_pay = rand.nextInt(1100);
        String card_number;
        char startWith;
        while(true) {
            System.out.print("Input your credit card: [1] - true [0] - exit\n>>> ");
            input = sc.nextInt();
            if (input == 0) System.exit(0);
            System.out.print("\nEnter your card number\n>>> ");
            card_number = sc.next(); // correct 5425233430109903  incorrect 4263982640269299 pin_c 2155
            len = card_number.length();
            startWith = card_number.charAt(0);

            if(startWith == '5') {
                if (len == 16) {
                    while(true) {
                        System.out.print("Enter your PIN code\n>>> ");
                        inputted_pin = sc.nextInt();
                        if (inputted_pin == correct_pin) {System.out.println("PIN code is correct");break;}
                        else {System.out.println(CYAN + "Incorrect CVV code" + RESET);
                            chance--;}
                        if(chance<0){
                            System.out.println(CYAN + "Sorry. Try again" + RESET);
                            System.exit(0);
                        }
                    }
                    if(balance > amount_to_pay){
                        System.out.println("Your balance: " + balance + '$');
                        System.out.println("To pay: " + amount_to_pay + '$');
                        balance -= amount_to_pay;
                        System.out.println("\nYour balance: " + balance + '$');
                        System.out.println("Collect your purchases "); // заберіть свої покупки
                    }else{
                        System.out.println("Your balance: " + balance + '$');
                        System.out.println("To pay: " + amount_to_pay + '$');
                        System.out.println(CYAN + "Transaction failed. You don't have enough money.\nYou must need" + amount_to_pay + RESET);
                    }
                }
            } else{
                System.out.println(CYAN + "Incorrect card number. Try again" + RESET);
            }
            // break
        }
    }

    @Override
    public void NFC() throws InterruptedException{
        System.out.println("Welcome to NFC system");
        int times = 2;
        while(true){
            int amount_to_pay = rand.nextInt(300), balance = rand.nextInt(700);
            System.out.println("Put the phone near reader");
            Main.do_stuff(8);
            System.out.println("Connecting to your bank");
            Main.do_stuff(4);
            System.out.println("In your balance: " + balance + '$');
            System.out.println("To pay: " + amount_to_pay + '$');
            if(balance > amount_to_pay){
                System.out.println("Transaction completed. Collect your purchases");
                break;
            }else {System.out.println(CYAN + "Transaction failed.You don't have enough money.\nYou must need " + amount_to_pay + RESET);
                Thread.sleep(4000);
                times--;
            }
            if(times < 0)System.exit(0);
        }
    }
    @Override
    public void CRYPTO() throws InterruptedException{
        DecimalFormat dec = new DecimalFormat("#0.00000");
        System.out.println("Welcome to CRYPTO system");
        double amount_to_pay = rand.nextInt(300);
        double convertToCrypto;
        int btc_price = 40788, eth_price = 2805, bnb_price = 388;
        Object btc_address = "bc1q0ptklcqgpjcjgtrrnm29f37rnl7sxx06tv88xt";
        Object eth_address = "0x0F9fE1Fa3F62706757c32303632bF2007A3167E4";
        Object bnb_address = "bnb1j530gdmnf6j7paafpj2k3j6yf8fjze4vr74vp3";
        Object hash_of_transaction = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
        System.out.println("Choose one cryptocurrency to pay\n[1] - Bitcoin [2] - Ethereum [3] - BNB");
        int choose = sc.nextInt();
        switch (choose){
            case 1 -> {
                System.out.println("You choose Bitcoin.\nSend only BTC(Bitcoin) to this address => " + CYAN + btc_address + RESET);
                System.out.println("To pay: " + amount_to_pay + '$');
                System.out.println("Current BTC price " + btc_price + '$');
                convertToCrypto = amount_to_pay / btc_price;
                System.out.println("Send " + dec.format(convertToCrypto) + " BTC");
                Main.do_stuff(20);
                System.out.println("Transaction completed.\nYour hash " + CYAN + hash_of_transaction + RESET);
            }
            case 2 -> {
                System.out.println("You choose Ethereum.\nSend only ETH(Ethereum) to this address => " + CYAN + eth_address + RESET);
                System.out.println("To pay: " + amount_to_pay + '$');
                System.out.println("Current ETH price " + eth_price + '$');
                convertToCrypto = amount_to_pay / eth_price;
                System.out.println("Send " + dec.format(convertToCrypto) + " ETH");
                Main.do_stuff(20);
                System.out.println("Transaction completed.\nYour hash " + CYAN + hash_of_transaction + RESET);
            }
            case 3 -> {
                System.out.println("You choose BNB.\nSend only BNB(BNB) to this address => " + CYAN + bnb_address + RESET);
                System.out.println("To pay: " + amount_to_pay + '$');
                System.out.println("Current BNB price " + bnb_price + '$');
                convertToCrypto = amount_to_pay / bnb_price;
                System.out.println("Send " + dec.format(convertToCrypto) + " BNB");
                Main.do_stuff(20);
                System.out.println("Transaction completed.\nYour hash " + CYAN + hash_of_transaction + RESET);
            }
        }
    }

    @Override
    public void CASH() {
        int amount_to_pay = rand.nextInt(300);
        System.out.println("You pay with cash");
        System.out.println("To pay: " + amount_to_pay + '$');
        while(true){
            int moneyFromClient = sc.nextInt();
            if(amount_to_pay > moneyFromClient){
                System.out.println(CYAN + "Please sir give me " + amount_to_pay + " $" + RESET);
            }else{
                System.out.println("Your pay back(charge): " + (moneyFromClient - amount_to_pay) + '$');
                break;
            }
        }
    }
}


class Loading{
    private String lastLine = "";

    public void print(String line) {
        //clear the last line if longer
        if (lastLine.length() > line.length()) {
            String temp = "";
            for (int i = 0; i < lastLine.length(); i++) {
                temp += " ";
            }
            if (temp.length() > 1)
                System.out.print("\r" + temp);
        }
        System.out.print("\r" + line);
        lastLine = line;
    }

    private byte anim;

    public void animate() {
        switch (anim) {
            case 1 -> print("\\");
            case 2 -> print("|");
            case 3 -> print("/");
            default -> {
                anim = 0;
                print("-");
            }
        }
        anim++;
    }
}
