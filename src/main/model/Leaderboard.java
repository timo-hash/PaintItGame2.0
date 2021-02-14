package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard {

    private ArrayList<Player> players;
    private Grid grid;

    public Leaderboard() {
        players = new ArrayList<Player>();
    }

    // MODIFIES: this
    // EFFECTS: add a Player to the arraylist
    public void addPlayers(Player p) {
        players.add(p);
    }


    // EFFECTS: Returns true if Player p is in the players and false otherwise
    public boolean containPlayers(Player p) {
        return players.contains(p);
    }

    // EFFECTS: return number of elements in the players arrayList
    public int numOfPlayers() {
        return players.size();
    }

    // EFFECTS: get the ith element from the players arrayList
    public Player getIthPlayer(int i) {
        return players.get(i);
    }

    // MODIFIES: this
    // EFFECTS: sort the players with scores in descending order
    public void sortPlayers() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });
    }

      // EFFECTS: print leaderboard out on the console
//    public void printLeaderboard() {
//        grid = new Grid();
//        System.out.println("\nLeaderboard");
//        for (int i = 0; i < players.size(); i++) {
//            System.out.println(players.get(i).getName() + " scored " + players.get(i).getScore()
//                    + " points out of " + grid.gridArea());
//        }
//    }


}
