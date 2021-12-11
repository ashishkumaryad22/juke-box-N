package org.example.hellomaven;

import DaoImpl.*;
import DaoInterface.*;
import ModelClass.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App
{
    static int playlist_counter=406;
    static UserDao ud=new UserDaoImpl();
    static SongDao sd=new SongDaoImpl();
    static PodcastDao pd=new PodcastDaoImpl();
    static EpisodeDao ed=new EpisodeDaoImpl();
    static PlaylistDao pld=new PlaylistDaoImpl();
    static PlaylistDetailsDao plds=new PlaylistdetailsDaoImpl();
    static JoinDao jd=new JoinDaoImpl();
    static String name;
    static int id,u_id;
    static Scanner sc=new Scanner(System.in);
    public static void main( String[] args ) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int current_user_id=0;

        int user_counter=5;
        System.out.println( "*************Welcome to JUKEBOX**************" );
        System.out.println("1. Login \n2. Create an account");
        int answer=sc.nextInt();
        int flag=0;

        switch(answer){
            case 1: {
                while(flag==0) {
                    System.out.println("Kindly enter your name: ");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.println("Kindly enter user id: ");
//                    sc.nextLine();
                    id = sc.nextInt();
                    boolean checkvalue = ud.checkUser(name, id);
                    if (checkvalue == true) {
                        System.out.println("Logged in successfully");
                        flag++;
                    }
                    else {
                        System.out.println("Please enter the right credentials");
                    }
                    current_user_id=id;
                }
                break;

            }
            case 2:{

                u_id=ud.getuserId();
                System.out.println("Enter your name: ");
                sc.nextLine();
                String nameOFUser=sc.nextLine();
                User user=new User(u_id+1,nameOFUser);
                ud.addUser(user);
                current_user_id=u_id+1;
                System.out.println("Your user id is: "+current_user_id);
                break;
            }

        }
        boolean y=true;
        while(y){
        System.out.println("\nEnter the operation you want to perform: \n1. Play Songs \n2. Play Podcasts \n3. View Playlist \n4. Create Playlist \n5. Delete Playlist");
        int choice=sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Select the operation \n1. Sort Songs based on artist \n2. Sort songs based on genre \n3. Sort songs based on album name");
                    List<Song> songList= sd.getAllSongs();
                    System.out.println("======================Song List======================");
                    for (Song s : songList) {
                        System.out.println(s);
                    }

                    System.out.println("\n******Select the operation****** \n1. Sort Songs based on artist \n2. Sort songs based on genre \n3. Sort songs based on album name \n4. All Songs");
                    int userchoice= sc.nextInt();
                    switch(userchoice) {
                        case 1: {
                            System.out.println("Enter the name of artist:");
                            sc.nextLine();
                            String artist_name = sc.nextLine();
                            List<Song> sortedlist = new ArrayList<>();
                            songList.stream().filter(a -> a.getArtist().equals(artist_name)).forEach(b -> sortedlist.add(b));
                            for(Song s: sortedlist){
                                System.out.println(s);
                            }

                            System.out.println("Enter song to be played");
                            String songname = sc.nextLine();
                            Play p = new Play();
                            p.PlaySong(songname, sortedlist);
                            break;
                        }
                        case 2: {
                            System.out.println("Enter the genre:");
                            String genre = sc.nextLine();
                            List<Song> sortedlist = new ArrayList<>();
                            songList.stream().filter(a -> a.getGenre().equals(genre)).forEach(b -> sortedlist.add(b));

                            System.out.println("Enter song to be played");
                            sc.nextLine();
                            String songname = sc.nextLine();
                            Play p = new Play();
                            p.PlaySong(songname, sortedlist);
                            break;
                        }
                        case 3: {
                            System.out.println("Enter the album name:");
                            String album_name = sc.nextLine();
                            List<Song> sortedlist = new ArrayList<>();
                            songList.stream().filter(a -> a.getAlbum().equals(album_name)).forEach(b -> sortedlist.add(b));

                            System.out.println("Enter song to be played");
                            sc.nextLine();
                            String songname = sc.nextLine();
                            Play p = new Play();
                            p.PlaySong(songname, sortedlist);
                            break;
                        }
                        case 4:{
                            System.out.println("Enter song name");
                            sc.nextLine();
                            String songname=sc.nextLine();
                            Play p=new Play();
                            p.PlaySong(songname,songList);
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    List<Podcast> podlist=pd.getAllPodcasts();
                    System.out.println("=======================Podcasts list=======================");
                    for (Podcast p : podlist) {
                        System.out.println(p);
                    }
                    sc.nextLine();
                    System.out.println("Enter the podcast name: ");
                    String podcast=sc.nextLine();
                    System.out.println("\nChoose: \n1. All Episodes \n2. Sort Episodes by date");
                    int option_chosen= sc.nextInt();
                    switch(option_chosen){
                        case 1:{
                            List<Episode> listbyname=ed.getAllEpisodesByName(podcast);
                            System.out.println("=======================Episodes list=======================");
                            for(Episode e: listbyname){
                                System.out.println(e);
                            }
                            System.out.println("Enter episode name");
                            sc.nextLine();
                            String episode_name=sc.nextLine();
                            Play p=new Play();
                            p.PlayPod(episode_name,listbyname);
                            break;
                        }
                        case 2:{
                            List<Episode> byDateSorted=ed.getAllEpisodesByDate(podcast);
                            System.out.println("\n=======================Episode List sorted on date==================");
                            for(Episode e: byDateSorted){
                                System.out.println(e);
                            }
                            System.out.println("\nEnter episode name");
                            sc.nextLine();
                            String episode_name=sc.nextLine();
                            Play p=new Play();
                            p.PlayPod(episode_name,byDateSorted);
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    List<Playlist> dets =pld.getAllPlaylistsByUserId(current_user_id);
                    System.out.println("=======================Your Playlists=======================");
                    for (Playlist pl : dets) {
                        System.out.println(pl);
                    }
                    System.out.println("Enter name of the playlist");
                    sc.nextLine();
                    String choice1 = sc.nextLine();
                    //Displays song of the playlist whose name has been provided by user
                    int idd = pld.getPlaylistIDByName(choice1);

                    boolean loop=true;
                    while(loop){
                        System.out.println("1. Listen \n2. Add Songs/Podcasts to your playlist \n3. Exit");
                        int entered_choice=sc.nextInt();
                        switch(entered_choice){
                            case 1: {
                                List<Song> songDetails = jd.getSongListByPlaylistID(idd);
                                System.out.println("=======================Songs in playlist=======================");
                                for (Song s : songDetails) {
                                    System.out.println(s);
                                }
                                List<Podcast> pods = jd.getPodsListByPlaylistID(idd);
                                System.out.println("=======================Podcasts in playlist=======================");
                                for (Podcast podcast : pods) {
                                    System.out.println(podcast);
                                }
                                System.out.println("Options: \n1. Choose from playlist  \n2. Shuffle order ");
                                int option = sc.nextInt();
                                switch (option) {
                                    case 1: {
                                        System.out.println("Select song/Podcast id to be played: ");
                                        int song_pod_play_id = sc.nextInt();
                                        boolean song_or_pod = sd.CheckIfSongIdPresent(song_pod_play_id);
                                        Play p = new Play();
                                        if (song_or_pod == true) {
                                            String song_name = sd.getSongNAmeById(song_pod_play_id);
                                            List<Song> songList = sd.getAllSongs();
                                            p.PlaySong(song_name, songDetails);
                                        }
                                        else {
                                            List<Episode> epi_names = ed.getAllEpisodesbypID(song_pod_play_id);
                                            System.out.println("=======================Episodes in Playlist=======================");
                                            for (Episode e : epi_names) {
                                                System.out.println(e);
                                            }
                                            System.out.println("Enter episode name to be played: ");
                                            sc.nextLine();
                                            String epi_name = sc.nextLine();
                                            p.PlayPod(epi_name, epi_names);
                                        }
                                        break;
                                    }
                                    case 2:{
                                        List<PlaylistDetails> allitems=plds.displayDetailsOfPlaylistbyId(idd);
                                        Collections.shuffle(allitems);
                                        for(PlaylistDetails pd: allitems){
                                            boolean song_or_pod = sd.CheckIfSongIdPresent(pd.getItem_id());
                                            Play p = new Play();
                                            if (song_or_pod == true) {
                                                String song_name = sd.getSongNAmeById(pd.getItem_id());
                                                p.PlaySongPlaylist(song_name, songDetails);
                                            }
                                            else
                                            {
                                                List<Episode> epi_names = ed.getAllEpisodesbypID(pd.getItem_id());
                                                for(Episode e: epi_names){
                                                    p.PlayPodPlaylist(e.getE_name(), epi_names);
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case 3:{
                                        loop=false;
                                    }
                                }
                                break;
                            }
                            case 2:{
                                System.out.println("Add songs/podcasts");
                                System.out.println("=======================All Items=======================");
                                System.out.println("Songs: ");
                                for(Song s: sd.getAllSongs()){
                                    System.out.println(s);
                                }
                                System.out.println("Podcasts: ");
                                for(Podcast p: pd.getAllPodcasts()){
                                    System.out.println(p);
                                }
                                System.out.println("\nSelect the song/podcast id to insert into playlist");
                                sc.nextLine();
                                int userchoice=sc.nextInt();
                                boolean check=plds.checkIfExists(userchoice,idd);
                                if(check==true){
                                    System.out.println("The item already exists in the playlist");
                                }
                                else{
                                    int detail_id=plds.getLastplaydetails_id();
                                    List<PlaylistDetails> details=plds.displayDetailsOfPlaylistbyId(idd);
                                    plds.addItemToPlaylist(userchoice,idd,detail_id);
                                }
                                break;
                            }
                            case 3:{
                                loop=false;
                            }
                        }
                    }

                }
                case 4: {
                    int play_id = pld.getPlaylistID();
                    sc.nextLine();
                    System.out.println("Enter the name of playlist");
                    String namechoice = sc.nextLine();
                    Playlist p = new Playlist(play_id + 1, namechoice, current_user_id);
                    PlaylistDao pld1=new PlaylistDaoImpl();
                    pld1.addPlaylist(p);
                    List<Playlist>updatedplaylists= pld.getAllPlaylistsByUserId(current_user_id);
                    System.out.println("======================Updated Playlist======================");
                    for(Playlist pl: updatedplaylists){
                        System.out.println(pl);
                    }
                    System.out.println("=======================All Items=======================");
                    System.out.println("Songs: ");
                    for(Song s: sd.getAllSongs()){
                        System.out.println(s);
                    }
                    System.out.println("Podcasts: ");
                    for(Podcast pod: pd.getAllPodcasts()){
                        System.out.println(pod);
                    }
                    int flag1=1;
                    while(flag1!=0){
                        System.out.println("\nSelect the song/podcast id to insert into playlist");
                        int userchoice=sc.nextInt();
                        int playlist_id=pld.getPlaylistIDByName(namechoice);
                        boolean check=plds.checkIfExists(userchoice,playlist_id);
                        if(check==true){
                            System.out.println("The item already exists in the playlist");
                            continue;
                        }
                        else {
                            int detail_id = plds.getLastplaydetails_id();

                            List<PlaylistDetails> details = plds.displayDetailsOfPlaylistbyId(playlist_id);
                            plds.addItemToPlaylist(userchoice, playlist_id, detail_id);
                            System.out.println("Do you want to add more items?  (1-YES   2-NO)");
                            int user_answer = sc.nextInt();
                            if (user_answer == 1) {
                                continue;
                            } else {
                                flag1 = 0;
                            }
                        }

                    }
                    break;
                }
                case 5: {
                    System.out.println("======================My Playlists======================");
                    for (Playlist pl1 : pld.getAllPlaylists()) {
                        System.out.println(pl1);
                    }
                    sc.nextLine();
                    System.out.println("Enter name of playlist to be deleted: ");
                    String playlist_name = sc.nextLine();
                    pld.deletebyName(playlist_name);
                    System.out.println("======================Updated Playlist======================");
                    List<Playlist> playlists=pld.getAllPlaylists();
                    for(Playlist play:playlists){
                        System.out.println(play);
                    }
                    break;
                }
                case 6: {
                    y = false;
                    break;
                }
                default: {
                    System.out.println("Please enter a valid choice");
                    break;
                }
            }
        }
    }


}



