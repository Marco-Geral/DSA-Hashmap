public class Main {
    
    
    public static void main(String[] args) {
        System.out.println("--------------Testing PrimeNumberGenerator--------------\n");

        PrimeNumberGenerator p = new PrimeNumberGenerator();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println("\n");
        System.out.println("current prime is: ");
        System.out.println(p.currentPrime());
        System.out.println("\n");
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        p.nextPrime();
        System.out.println(p.toString());
        System.out.println("\n---This is shap---\n");


        System.out.println("--------------Testing Hashmap--------------\n");
        System.out.println("--------------Testing Hashmap Constructor--------------\n");
        Hashmap h0 = new Hashmap("2[-]");
        System.out.println(h0.toString() + "\n");
        Hashmap h1 = new Hashmap("5[u9:8%,u5:11%,u1:2%,-]");
        System.out.println(h1.toString() + "\n");
        Hashmap h2 = new Hashmap("7[u1:2%,u9:8%,-,-,-,u5:11%,u12:13%,u22:20%]");
        System.out.println(h2.toString());

        System.out.println("--------------Testing Hashmap insert--------------\n");
        System.out.println(h0.toString() + "\n");
        h0.insert(5,10);
        System.out.println(h0.toString() + "\n");
        h0.insert(10,20);
        System.out.println(h0.toString() + "\n");

        System.out.println("--------------Testing Hashmap search--------------\n");
        System.out.println(h2.search(22));

        System.out.println("--------------Testing Hashmap remove--------------\n");
        System.out.println(h2.toString() + "\n");
        h2.remove(12);
        System.out.println(h2.toString() + "\n");
        h2.remove(22);
        System.out.println(h2.toString() + "\n");
        h2.remove(5);
        System.out.println(h2.toString() + "\n");
        h2.remove(9);
        System.out.println(h2.toString() + "\n");
        h2.remove(1);
        System.out.println(h2.toString() + "\n");
        
    }
}