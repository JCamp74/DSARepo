//Implement: Collision count.
//Collision is when two elements have the same hash index.
//Collisions are ok, in general, but do slow down your program if there are too many.

//Write a new private method: resizeInternalData
//This will double the size of the data[] you're programming to.
//But.... what does that mean in Hashing?
//Take all data - rehash, rebin everything.

//Write a toggle to switch between using .hashcode() and your dummy hash.

//You can reuse as much hash code as makes sense.
//Instead have this Map, alphabetized.
//So printouts will be in order.
//Adds will add things in order.
//???

//Write a tester
//Test both final outputs
//Ticket out of today is the completion of today's project.

import java.util.LinkedList;
public class HashPractice {
    LinkedList<String> data[] = new LinkedList[5];

    public HashPractice() {
        for(int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<>();
        }
    }

    public int dumbHash(String id) {
        
        return id.length();
    }



    public void add(String name) {
        data[dumbHash(name) - 1].add(name);
    }
    
    public void remove(String name) {
        LinkedList<String> temp = new LinkedList<>();
        for(int i = 0; i < data.length; i++) {
            if(dumbHash(name) - 1 == i) {
                temp = data[i];
            }
        }
        for(String string : temp) {
            if(string.equals(name)) {
                temp.set(temp.indexOf(string), "");
                break;
            }
        }
        data[dumbHash(name) - 1] = temp;
    }

    private int collisionCount() {
        int collisions = 0;
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].size() - 1; j++) {
                if(dumbHash(data[i].get(j)) == dumbHash(data[i].get(j+1))) {
                    collisions++;
                }
            }
        }
        return collisions;
    }

    public void print() {
         for(int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        System.out.println("Collision Count: " + collisionCount());
    }
}