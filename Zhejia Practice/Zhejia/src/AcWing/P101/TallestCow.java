package AcWing.P101;

import java.util.*;

public class TallestCow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cowNumbers = scanner.nextInt();
        int indexTallestCow = scanner.nextInt() - 1;
        int tallestHeight = scanner.nextInt();
        int numberRelations = scanner.nextInt();
        Set<Relation> relations = new HashSet<>();
        while (numberRelations-- > 0) {
            int index1 = scanner.nextInt() - 1;
            int index2 = scanner.nextInt() - 1;
            if (index2 < index1) {
                int temp = index1;
                index1 = index2;
                index2 = temp;
            }
            Relation relation = new Relation(index1, index2);
            relations.add(relation);
        }
        getMaximumHeight(relations, cowNumbers, indexTallestCow, tallestHeight);
    }

    private static void getMaximumHeight(Set<Relation> relations, int cowNumbers, int indexTallestCow, int tallestHeight) {
        int[] cowHeights = new int[cowNumbers];
        int[] heightDifference = new int[cowNumbers + 1];
        for (Relation relation: relations) {
            heightDifference[relation.indexCow1 + 1] -= 1;
            heightDifference[relation.indexCow2] += 1;
        }
        for (int cow = 1; cow < cowNumbers; cow++)
            cowHeights[cow] = cowHeights[cow - 1] + heightDifference[cow];

        for (int cow: cowHeights)
            System.out.println(tallestHeight + cow);
    }

}

class Relation {
    int indexCow1;
    int indexCow2;

    public Relation(int indexCow1, int indexCow2) {
        this.indexCow1 = indexCow1;
        this.indexCow2 = indexCow2;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Relation otherRelation)
            return indexCow1 == otherRelation.indexCow1 && indexCow2 == otherRelation.indexCow2;
        else return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(indexCow1, indexCow2);
    }
}
