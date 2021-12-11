package org.example.hellomaven;

import ModelClass.Episode;
import ModelClass.Song;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Play {
    public void PlaySongPlaylist(String songname, List<Song> songslist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        try{
            String path="src/main/resources/"+songname+".wav";
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            while(!response.equals("Q")) {
                System.out.println("P = play, T= Pause, S=Stop, L=Loop, F=Forward, B=Reverse, R = Reset, Q= Next");    /*N=Next Song,*/
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch(response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        System.out.println("Songs in queue: ");
                        SongsLeft(songname,songslist);
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip stopped at: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case("S"):{
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case("L"):{
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case("F"):{
                        long duration=clip.getFrameLength();
                        //clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if((duration-clip_position)>10000000){
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition()+10000000);
                            clip.start();
                        }
                        long milliseconds=clip.getMicrosecondPosition()/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        /*long l=10000000;
                        byte b=(byte) l;
                        long l2 = b & 0xFF;
                        clip.skip(l2);*/
                        break;
                    }
                    case("B"):{
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if(clip_position<10000000){
                            clip.setMicrosecondPosition(0);
                        }
                        else{
                            clip.setMicrosecondPosition(clip_position-10000000);
                        }
                        clip.start();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("R"): clip.setMicrosecondPosition(0);
                        break;
                    case ("N"): {
                        clip.close();
                        break;
                    }
                    default: System.out.println("Not a valid response");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    public void PlayPodPlaylist(String epiname, List<Episode> epislist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        try{
            String path="src/main/resources/"+epiname+".wav";
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            while(!response.equals("Q")) {
                System.out.println("P = play, T= Resume, S=Stop, L=Loop, F=Forward, B=Reverse, R = Reset, Q=Next");
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch(response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        EpisodesLeft(epiname,epislist);
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip stopped at: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case("S"):{
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case("L"):{
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case("F"):{
                        long duration=clip.getFrameLength();
                        long clip_position = clip.getMicrosecondPosition();
                        if((duration-clip_position)>10000000){
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition()+10000000);
                            clip.start();
                        }
                        long milliseconds=clip.getMicrosecondPosition()/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case("B"):{
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if(clip_position<10000000){
                            clip.setMicrosecondPosition(0);
                        }
                        else{
                            clip.setMicrosecondPosition(clip_position-10000000);
                        }
                        clip.start();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("R"): clip.setMicrosecondPosition(0);
                        break;
                    case ("Q"): {
                        clip.close();
                        break;
                    }

                    default: System.out.println("Not a valid response");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public void PlaySong(String songname, List<Song> songslist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        try{
            String path="src/main/resources/"+songname+".wav";
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            while(!response.equals("Q")) {
                System.out.println("P = play, T= Pause, S=Stop, L=Loop, F=Forward, B=Reverse, R = Reset, N=Next Song Q = Quit");    /*N=Next Song,*/
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch(response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        System.out.println("Songs in queue: ");
                        SongsLeft(songname,songslist);
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip stopped at: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case("S"):{
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case("L"):{
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case("F"):{
                        long duration=clip.getFrameLength();

                        long clip_position = clip.getMicrosecondPosition();
                        if((duration-clip_position)>10000000){
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition()+10000000);
                            clip.start();

                        }
                        long milliseconds=clip.getMicrosecondPosition()/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case("B"):{
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if(clip_position<10000000){
                            clip.setMicrosecondPosition(0);
                        }
                        else{
                            clip.setMicrosecondPosition(clip_position-10000000);
                        }
                        clip.start();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("R"): clip.setMicrosecondPosition(0);
                        break;
                    case ("N"):{
                        SongsLeft(songname,songslist);
                        System.out.println("Choose the song to be played next: ");
                        scanner.nextLine();
                        String song=scanner.nextLine();
                        PlaySong(song,songslist);
                        break;
                    }
                    case ("Q"): clip.close();
                        break;
                    default: System.out.println("Not a valid response");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public void PlayPod(String epiname, List<Episode> epislist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        try{
            String path="src/main/resources/"+epiname+".wav";
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            while(!response.equals("Q")) {
                System.out.println("P = play, T= Resume, S=Stop, L=Loop, F=Forward, B=Reverse, R = Reset, N=Next Episode Q = Quit");
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch(response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        EpisodesLeft(epiname,epislist);
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip stopped at: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case("S"):{
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case("L"):{
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case("F"):{
                        long duration=clip.getFrameLength();
                        long clip_position = clip.getMicrosecondPosition();
                        if((duration-clip_position)>10000000){
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition()+10000000);
                            clip.start();
                        }
                        long milliseconds=clip.getMicrosecondPosition()/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case("B"):{
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if(clip_position<10000000){
                            clip.setMicrosecondPosition(0);
                        }
                        else{
                            clip.setMicrosecondPosition(clip_position-10000000);
                        }
                        clip.start();
                        long milliseconds=clip_position/1000;
                        System.out.println("Clip played from: "+milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("R"): clip.setMicrosecondPosition(0);
                        break;
                    case ("N"): {

                        EpisodesLeft(epiname,epislist);
                        System.out.println("\nChoose the episode to be played next: ");
                        scanner.nextLine();
                        String episode=scanner.nextLine();
                        PlayPod(episode,epislist);
                        break;
                    }
                    case ("Q"): clip.close();
                        break;
                    default: System.out.println("Not a valid response");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hours > 0) {
            finalTimerString = hours + ":";
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        }   else {
            secondsString = "" + seconds;
        }
        finalTimerString = finalTimerString + minutes + ":" + secondsString;
        return finalTimerString;
    }



    public static void SongsLeft(String song_name, List<Song> songslist){
        songslist.stream().filter(a->!a.getSong_name().equals(song_name)).forEach(b-> System.out.println(b));
    }


    public static void EpisodesLeft(String episode_name, List<Episode> epislist){
        epislist.stream().filter(a->!a.getE_name().equals(episode_name)).forEach(b-> System.out.println(b));
    }


    /*public static List<Song> SongsLeftList(String song_name, List<Song> songslist){
        List<Song> leftSongs=new ArrayList<>();
        songslist.stream().filter(a->!a.getSong_name().equals(song_name)).forEach(b-> leftSongs.add(b));
        System.out.println("Songs in queue: ");
        for(Song s: leftSongs){
            System.out.println(s);
        }
        return leftSongs;
    }


    public static List<Episode> EpisodesLeftlist(String episode_name,List<Episode>epislist){
        List<Episode> leftEpisodes=new ArrayList<>();
        epislist.stream().filter(a->!a.getE_name().equals(episode_name)).forEach(b-> leftEpisodes.add(b));
        System.out.println("Episodes in queue: ");
        for(Episode e: leftEpisodes){
            System.out.println(e);
        }
        return leftEpisodes;

    }*/

}
