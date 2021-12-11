package org.example.hellomaven;

import DaoImpl.*;
import DaoInterface.*;
import ModelClass.Playlist;
import ModelClass.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.*/


public class AppTest 
{
    @Test
    public void addUserCheckSize()
    {
        UserDao ud=new UserDaoImpl();
        ud.addUser(new User(7,"Nishu"));
        assertFalse(ud.getAllUsers().isEmpty());
        UserDao ud1=new UserDaoImpl();
        assertEquals(7,ud1.getAllUsers().size());
    }
    @Test
    public void CheckUser(){
        UserDao ud=new UserDaoImpl();
        assertTrue("true",ud.checkUser("Rishu", 6));
    }

    @Test
    public void DeleteUser()
    {
        UserDao ud=new UserDaoImpl();
        assertTrue("true",ud.delete(7));
    }

    //Test cases for Song
    @Test
    public void CheckIfSongIsPresent(){
        SongDao sd=new SongDaoImpl();
        assertTrue("true", sd.CheckIfSongIdPresent(104));
        assertFalse("false", sd.CheckIfSongIdPresent(111));
    }

    //Test cases for Podcasts
    public void CheckIfPodIdPresent(){
        PodcastDao pd=new PodcastDaoImpl();
        assertTrue("true", pd.CheckIfPodIdPresent(203));
        assertFalse("false", pd.CheckIfPodIdPresent(205));
    }


    //Test cases for Playlist
    @Test
    public void addPlaylist(){
        PlaylistDao pd=new PlaylistDaoImpl();
        Playlist pl=new Playlist(411,"check",1);
        pd.addPlaylist(pl);
        assertFalse(pd.getAllPlaylists().isEmpty());
        PlaylistDao pd1=new PlaylistDaoImpl();
        assertEquals(11,pd.getAllPlaylists().size());
    }
    @Test
    public void DeletePlaylist()
    {
        PlaylistDao pd=new PlaylistDaoImpl();
        assertTrue("true",pd.deletebyID(411));
        assertEquals(10,pd.getAllPlaylists().size());
    }


    //Test case for PlayListDetails

    public void addDetails(){
        PlaylistDetailsDao pld=new PlaylistdetailsDaoImpl();
        pld.addItemToPlaylist(101, 403, 529);
        assertEquals(18,pld.displayDetailsOfPlaylistbyId(403).size());
    }


}
