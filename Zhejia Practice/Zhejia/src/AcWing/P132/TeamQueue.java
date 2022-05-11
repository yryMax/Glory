package AcWing.P132;

import java.util.*;

public class TeamQueue {
    static List<Set<Integer>> memberInfo = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (Integer.parseInt(line) == 0) break;
            int numberTeams = Integer.parseInt(line);

            int index = 0;
            while (numberTeams-- > 0) {
                memberInfo.add(new HashSet<>());
                int numberMember = scanner.nextInt();
                for (int i = 0; i < numberMember; i++)
                    memberInfo.get(index).add(scanner.nextInt());
                index++;
            }

            scanner.nextLine();
            processTeams(testCase, scanner);
            testCase++;
        }

    }

    private static void processTeams(int testCase, Scanner scanner) {
        System.out.println("Scenario #" + testCase);
        Queue<Integer> teamIndex = new LinkedList<>();
        List<Queue<Integer>> teamQueue = new LinkedList<>();
        for (int i = 0; i < memberInfo.size(); i++) teamQueue.add(new LinkedList<>());
        boolean[] alreadyPresent = new boolean[memberInfo.size()];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("STOP")) {
                System.out.println();
                return;
            }
            if (line.startsWith("ENQUEUE")) {
                int arrived = Integer.parseInt(line.split(" ")[1]);
                int teamOfTheMember = findTeam(arrived);
                if (!alreadyPresent[teamOfTheMember]) {
                    teamIndex.add(teamOfTheMember);
                    alreadyPresent[teamOfTheMember] = true;
                }
                teamQueue.get(teamOfTheMember).add(arrived);
            } else {
                assert teamIndex.peek() != null;
                int lastTeam = teamIndex.peek();
                System.out.println(teamQueue.get(lastTeam).poll());
                if (teamQueue.get(lastTeam).isEmpty()) {
                    teamIndex.poll();
                    alreadyPresent[lastTeam] = false;
                }
            }
        }
    }

    private static int findTeam(int memberIndex) {
        for (int i = 0; i < memberInfo.size(); i++)
            if (memberInfo.get(i).contains(memberIndex)) return i;
        throw new NoSuchElementException(String.valueOf(memberIndex));
    }
}
