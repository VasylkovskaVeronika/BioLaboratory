package Classes;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CustomSerializeDeserialize implements Serializable {
    static PlayerStat currentP;
    CustomSerializeDeserialize(PlayerStat cp) {
        currentP=cp;
    }
//    public static void main(String[] args) {
//        PlayerStat[] newplayers = new PlayerStat[3];
//        newplayers[0] = new PlayerStat("hh", 1, 2, "888");
//        newplayers[1]=new PlayerStat("ffff", 3, 4, "098");
//        newplayers[2]=new PlayerStat();
//        // Let's serialize an Object
//        try {
//            FileOutputStream fileOut = new FileOutputStream("highScores.txt");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.reset();
//            out.writeObject(newplayers);
//            out.close();
//            fileOut.close();
//            System.out.println("\nSerialization Successful... Checkout your specified output file..\n");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        PlayerStat[] imReading=deserialize();
//        for (var r:imReading) {
//            System.out.println(r);
//        }
//        reloadFile(new PlayerStat("xxx", 8, 9, "076"));
//        PlayerStat[] imReading2=deserialize();
//        for (var r:imReading2) {
//            System.out.println(r);
//        }
//        EndGameDialog e=new EndGameDialog(7,5,
//                "10:9:0");
//        e.setVisible(true);
//    }
    public static PlayerStat[] deserialize() {
        // Let's deserialize an Object
        var players = new PlayerStat[0];
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("highScores.txt"))) {
            var coolplayers = (PlayerStat[]) in.readObject();
            if (coolplayers != null) {
                for (PlayerStat p : players) {
                    System.out.println(p.name + "\n");
                }
                in.close();
                return coolplayers;
            }
        }
             catch(FileNotFoundException | ClassNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        return players;
    }
public static void reloadFile(PlayerStat cp) {
        PlayerStat[] players = deserialize();
        PlayerStat[] newplayers = new PlayerStat[players.length + 1];
        for(int i=0; i< players.length; i++)
            newplayers[i]=players[i];
        newplayers[players.length] = cp;
        // Let's serialize an Object
        try {
            FileOutputStream fileOut = new FileOutputStream("highScores.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.reset();
            out.writeObject(newplayers);
            out.close();
            fileOut.close();
            System.out.println("\nSerialization Successful... Checkout your specified output file..\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //check
    PlayerStat[] imReading2=deserialize();
    for (var r:imReading2) {
        System.out.println(r);
    }
}


    public static class PlayerStat implements Serializable{
        String name;
        int savedLives;
        int donates;
        String time;

        public PlayerStat() {
            name="";
            savedLives=0;
            donates=0;
            time="";
        }

        public PlayerStat(String n, int l, int d, String t) {
            name=n;
            savedLives=l;
            donates=d;
            time=t;
        }
        private void writeObject(ObjectOutputStream out) throws IOException
        {
            out.defaultWriteObject();
            out.writeUTF(name);
            out.writeInt(donates);
            out.writeInt(savedLives);
            out.writeUTF(time);
        }
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
        {
            in.defaultReadObject();
            name=in.readUTF();
            donates=in.readInt();
            savedLives=in.readInt();
            time=in.readUTF();
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public int getSavedLives() {
            return savedLives;
        }

        public int getDonates() {
            return donates;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setDonates(int donates) {
            this.donates = donates;
        }

        public void setSavedLives(int savedLives) {
            this.savedLives = savedLives;
        }

        @Override
        public String toString() {
            return "-> " + name + " famous for " + savedLives + " saved lives with" +donates +
                    " donates left in " + time + " timeframe";
        }
    }
}
