
    import java.util.*;
import java.io.*;

public class musicAddExport {    
    static HashMap<String, Boolean> isSinger = new HashMap<>();
    static HashMap<String, HashMap<String, Integer>> SingerMusic = new HashMap<>();
    static HashMap<String, ArrayList<CountMusic>> HighestMusicPlayBySinger = new HashMap<>();
    static ArrayList<CountMusic> HighestSongPLayed = new ArrayList<>();
    static HashMap<String, String> MusicSinger = new HashMap<>();
    static HashMap<String, ArrayList<String>> userPlaylists = new HashMap<>(); // Stores user playlists

    public static void initialize() {
        isSinger.put("!", false);
        HashMap<String, Integer> Initialize = new HashMap<>();
        SingerMusic.put("!", Initialize);
        ArrayList<CountMusic> Initialize2 = new ArrayList<>();
        HighestMusicPlayBySinger.put("!", Initialize2);
        MusicSinger.put("!", "!");
    }

    public static void addMusicNameAndSinger(String music, String Singer) {
        if (!isSinger.containsKey(Singer)) {
            isSinger.put(Singer, true);
        }
        HashMap<String, Integer> checkTheMusic = SingerMusic.get(Singer);
        try {
            if (checkTheMusic == null) {
                checkTheMusic = new HashMap<>();
                checkTheMusic.put(music, 0);
                SingerMusic.put(Singer, checkTheMusic);
                MusicSinger.put(music, Singer);
            } else if (!checkTheMusic.containsKey(music)) {
                checkTheMusic.put(music, 0);
                SingerMusic.put(Singer, checkTheMusic);
                MusicSinger.put(music, Singer);
            } else {
                throw new Exception("Music already added.");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void playMusic(String singer, String music) {
        if (singer.length() != 0 && music.length() != 0) {
            try {
                if (isSinger.containsKey(singer)) {
                    HashMap<String, Integer> checkTheMusic = SingerMusic.get(singer);
                    try {
                        if (checkTheMusic == null) {
                            throw new Exception("No music for this singer.");
                        } else if (checkTheMusic.containsKey(music)) {
                            checkTheMusic.put(music, checkTheMusic.get(music) + 1);
                            SingerMusic.put(singer, checkTheMusic);

                            ArrayList<CountMusic> AddingMusicCount = HighestMusicPlayBySinger.get(singer);
                            if (AddingMusicCount == null) {
                                AddingMusicCount = new ArrayList<>();
                            }

                            CountMusic temp0 = new CountMusic(music);
                            temp0.count = checkTheMusic.get(music);
                            AddingMusicCount.add(temp0);
                            HighestMusicPlayBySinger.put(singer, AddingMusicCount);
                        }
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                } else {
                    throw new Exception("Singer not found.");
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }
    public static void createPlaylist(String username, ArrayList<String> songs) {
        if (!userPlaylists.containsKey(username)) {
            userPlaylists.put(username, new ArrayList<>());
        }
        userPlaylists.get(username).addAll(songs);
        System.out.println("Playlist created for user: " + username);
    }
    public static void exportPlaylistToCSV(String username) {
        if (userPlaylists.containsKey(username)) {
            ArrayList<String> playlist = userPlaylists.get(username);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(username + "_playlist.csv"));
                writer.write("Song Name, Play Count\n");

                for (String song : playlist) {

                    String singer = MusicSinger.get(song);
                    if (singer != null && SingerMusic.containsKey(singer) && SingerMusic.get(singer).containsKey(song)) {
                        int count = SingerMusic.get(singer).get(song);
                        writer.write(song + ", " + count + "\n");
                    }
                }
                writer.close();
                System.out.println("Playlist exported to " + username + "_playlist.csv");
            } catch (IOException e) {
                System.out.println("An error occurred while exporting the playlist.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No playlist found for the user: " + username);
        }
    }
    public static void main(String[] args) {
        Scanner temp2 = new Scanner(System.in);
        initialize();
        ArrayList<String> songs = new ArrayList<>();
        songs.add("Song1");
        songs.add("Song2");
        createPlaylist("User1", songs);
        addMusicNameAndSinger("Song1", "Artist1");
        addMusicNameAndSinger("Song2", "Artist2");
        playMusic("Artist1", "Song1");
        playMusic("Artist2", "Song2");
        exportPlaylistToCSV("User1");
        temp2.close();
    }
}

class CountMusic {
    String musicname;
    int count;

    public CountMusic(String music) {
        this.musicname = music;
        this.count = 0;
    }
    
}
