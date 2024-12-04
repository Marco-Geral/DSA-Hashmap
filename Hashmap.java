import java.lang.Math;;

public class Hashmap {
    public KeyValuePair[] array;
    public PrimeNumberGenerator prime = new PrimeNumberGenerator();

    @Override
    public String toString() {
        String res = String.valueOf(prime.currentPrime()) + "\n";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += "\n";
            }
            res += i + "\t";
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res;
    }

    public String toStringOneLine() {
        String res = String.valueOf(prime.currentPrime()) + "[";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += ",";
            }
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res + "]";
    }

    public Hashmap() {
        array = new KeyValuePair[1];
    }

    public Hashmap(String inp) {

        String split[] = inp.split("\\[");
        int val = Integer.parseInt(split[0]);

        while(prime.currentPrime() < val){
            prime.nextPrime();
        }

        String splitTwo[] = split[1].split(",");
        if(splitTwo.length < 1) {
            array = new KeyValuePair[1];
            array[0] = null;
            return;
        } else {
            array = new KeyValuePair[splitTwo.length];
        }

        for(int i = 0; i < splitTwo.length; i++) {
            if(splitTwo[i].contains("u") == true) {
                int p = Integer.parseInt(splitTwo[i].substring(splitTwo[i].indexOf("u") + 1, splitTwo[i].indexOf(":")));
                int q = Integer.parseInt(splitTwo[i].substring(splitTwo[i].indexOf(":") + 1, splitTwo[i].indexOf("%")));
                array[i] = new KeyValuePair(p, q);
            } else {
                array[i] = null;
            }
        }


    }

    public int hash(int studentNumber) {

        int s = array.length;
        int hashValue = 0;
        String stuNum = studentNumber +"";
        for(int i = 0; i < stuNum.length(); i++) {
            int k = Character.getNumericValue(stuNum.charAt(i));
            hashValue = (hashValue * prime.currentPrime()) + k;
        }
        hashValue = hashValue % s;
        if(hashValue < 0) {
            hashValue = Math.abs(hashValue);
        }
        return hashValue;
    }

    public KeyValuePair search(int studentNumber) {
        int position = hash(studentNumber);
        if(array[position] != null && array[position].studentNumber == studentNumber)
        {
            return array[position];
        }

        int hash = position;
        int idx = probeSearch(hash);
        if(idx != -1 && array[idx] != null && array[idx].studentNumber == studentNumber)
        {
            return array[idx];
        }
        return null;
    }

    private int probeSearch(int hash) {
        int idx = Math.abs(hash + (1*prime.currentPrime())) % array.length;
        if(array[idx] != null)
            return idx;

        idx = Math.abs(hash - (1*prime.currentPrime())) % array.length;
        if(array[idx] != null)
            return idx;

        idx = Math.abs(hash + (4*prime.currentPrime())) % array.length;
        if(array[idx] != null)
            return idx;

        idx = Math.abs(hash - (4*prime.currentPrime())) % array.length;
        if(array[idx] != null)
            return idx;
        
        idx = Math.abs(hash + (9*prime.currentPrime())) % array.length;
        if(array[idx] != null)
            return idx;
        
        idx = Math.abs(hash - (9*prime.currentPrime())) % array.length;
        if(array[idx] != null)
            return idx;

        return -1;
    }

    public void insert(int studentNumber, int mark) {
        if(array == null) {
            return;
        }
        KeyValuePair e = search(studentNumber);
            if((e != null) && (e.studentNumber == studentNumber)) {
                e.mark = mark;
                return;
            }
        
        KeyValuePair keyVal = new KeyValuePair(studentNumber, mark);
        insertHelperMethod(keyVal, studentNumber, mark);
    }

    private void insertHelperMethod(KeyValuePair keyVal, int studentNumber, int mark) {
        int position = hash(studentNumber);
        if(array[position] == null) {
            array[position] = keyVal;
            return;
        }

        int hash = position;

        boolean arrayIsFull = true;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                arrayIsFull = false;
            }
        }

        if(!arrayIsFull) {
           int index = probing(hash);
           if(index != -1) {
                array[index] = keyVal;
                return;
           }      
        }
       
        KeyValuePair[] temp = copyArray(array);
        int old = temp.length;
        int newValue = array.length*2;
        array = new KeyValuePair[newValue];

        prime.nextPrime();
        for(int i = 0; i < old; i++) {
            if(temp[i] != null)
                insert(temp[i].studentNumber, temp[i].mark);
        }
        insert(studentNumber, mark);
    }

    private int probing(int hash) {
        int idx = Math.abs(hash + 1 * prime.currentPrime()) % array.length;
        if(array[idx] == null)
            return idx;

        idx = Math.abs(hash - 1 * prime.currentPrime()) % array.length;
        if(array[idx] == null)
            return idx;

        idx = Math.abs(hash + 4 * prime.currentPrime()) % array.length;
        if(array[idx] == null)
            return idx;

        idx = Math.abs(hash - 4 * prime.currentPrime()) % array.length;
        if(array[idx] == null)
            return idx;
        
        idx = Math.abs(hash + 9 * prime.currentPrime()) % array.length;
        if(array[idx] == null)
            return idx;
        
        idx = Math.abs(hash - 9 * prime.currentPrime()) % array.length;
        if(array[idx] == null)
            return idx;

        return -1;
    }

    public KeyValuePair[] copyArray(KeyValuePair[] current) {
        KeyValuePair[] tmp = new KeyValuePair[current.length];
        for(int i = 0; i < tmp.length; i++) {
            tmp[i] = current[i];
        }
        return tmp;
    }

    public void remove(int studentNumber) {
        KeyValuePair t = search(studentNumber);
        if(t != null) {
            int idx = searchRemove(studentNumber);
            if(idx >= 0 && array[idx].studentNumber == studentNumber)
                array[idx] = null;
        }
    }

    private int searchRemove(int studentNumber) {
        int position = hash(studentNumber);
        if(array[position] != null && array[position].studentNumber == studentNumber) {
            return position;
        }
        int cur = prime.currentPrime();
        int hash = position;

        for(int i = 1; i < ((array.length-1) / 2); i++) {
            hash = (int) Math.abs(hash + Math.pow((-1),(i-1)) * cur) % array.length;
            if(array[hash] != null && array[hash].studentNumber == studentNumber) {
                return hash;
            }
        }
        
        return -1;
    }
}
