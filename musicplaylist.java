import java.util.*;
public class musicplaylist{
    //in hashmap we will do access for the if we have any singer name or not
    //after we find that if we do not have a singer then we will create a entry for it in the hashmap and corresponding
    static HashMap<String,Boolean> isSinger=new HashMap<String,Boolean>();
    static HashMap<String,HashMap<String,Integer>> SingerMusic=new HashMap<String,HashMap<String,Integer>> ();
    static HashMap<String,ArrayList<CountMusic>> HighestMusicPlayBySinger=new HashMap<String,ArrayList<CountMusic>>();
    static ArrayList<CountMusic> HighestSongPLayed=new ArrayList<CountMusic>();
    static HashMap<String,String> MusicSinger=new HashMap<String,String>();
    public static void Iniatializer(){
        isSinger.put("!",false);
        HashMap<String,Integer> Initialize=new HashMap<String,Integer>();
        SingerMusic.put("!",Initialize);
        ArrayList<CountMusic> Initialize2=new ArrayList<CountMusic>();
        HighestMusicPlayBySinger.put("!",Initialize2);
        MusicSinger.put("!","!");
    }
    public static void AddMusicNameAndSinger(String music,String Singer){
        if(!isSinger.containsKey(Singer)){
            isSinger.put(Singer,true);    
        }
        HashMap<String,Integer> checkTheMusic=SingerMusic.get(Singer);
        //checkTheMusic.put("!",0);
        try{
        if(checkTheMusic==null){
            checkTheMusic=new HashMap<>();
            checkTheMusic.put(music,0);
            SingerMusic.put(Singer,checkTheMusic);
            System.out.println(checkTheMusic.get("tera zikr"));
        }
        else if(!checkTheMusic.containsKey(music)){
            checkTheMusic.put(music,0);
            SingerMusic.put(Singer,checkTheMusic);
            MusicSinger.put(music,Singer);
        }
        else{
            throw new Exception("music is alreay add");
        }
    }
    catch(Exception e){
        System.out.print(e);
    }
    }
    public static void PlayMusic(String singer,String music){
        if(singer.length()!=0 && music.length()!=0){
            try{
                if(isSinger.containsKey(singer)){
                      HashMap<String,Integer> checkTheMusic=SingerMusic.get(music);
                      //checkTheMusic.put("!",0);
                      try{
                        if(checkTheMusic==null){
                            throw new Exception("no music");
                        }
                        else if(checkTheMusic.containsKey(music)){
                            checkTheMusic.put(music,checkTheMusic.get(music)+1);
                            SingerMusic.put(singer,checkTheMusic);
                            System.out.print("playing the music by" + singer + music);
                            ArrayList<CountMusic> AddingMusicCount=HighestMusicPlayBySinger.get(singer);
                            int temp2=-1;
                            int presentAlready=0;
                            int presentAlreadyIndex=-1;
                            if(AddingMusicCount.size()==0){
                                CountMusic temp0=new CountMusic(music);
                                temp0.count=temp0.count+1;
                                AddingMusicCount.add(temp0);
                                HighestMusicPlayBySinger.put(singer,AddingMusicCount);
                            }
                            else if(AddingMusicCount.size()!=0){
                            for(int e=0;e<AddingMusicCount.size();e++){
                                CountMusic temp0=AddingMusicCount.get(e);
                                if(temp2==-1 && temp0.count<checkTheMusic.get(music)){
                                    if(temp0.musicname.equals(music)){
                                        presentAlready=1;
                                        presentAlreadyIndex=e;
                                    }
                                    temp2=e;
                                }
                                else if(temp2!=-1 && temp0.count>AddingMusicCount.get(temp2).count){
                                    if(temp0.musicname.equals(music)){
                                        presentAlready=1;
                                        presentAlreadyIndex=e;
                                    }
                                    temp2=e;
                                }
                            }
                            if(temp2!=-1 && presentAlready==0 && AddingMusicCount.size()==10){
                               CountMusic temp0=new CountMusic(music);
                               temp0.count=checkTheMusic.get(music);
                               temp0.musicname=music;
                               temp0.count=checkTheMusic.get(music);
                               AddingMusicCount.set(temp2,temp0);
                               int checkForAll=-1;
                               int presentAlreadyAll=0;
                               int presentAlreadyAllIndex=-1;
                               for(int e=0;e<HighestSongPLayed.size();e++){
                                if(HighestSongPLayed.get(e).musicname.equals(music)){
                                    presentAlreadyAll=1;
                                    presentAlreadyAllIndex=e;
                                }
                                else if(checkForAll==-1 && HighestSongPLayed.get(e).count<checkTheMusic.get(music)){
                                    checkForAll=e;
                                }
                                else if(checkForAll!=-1 && HighestSongPLayed.get(e).count<HighestSongPLayed.get(checkForAll).count){
                                    checkForAll=e;
                                }
                               }
                               if(checkForAll!=-1 && presentAlreadyAll==0){
                                if(HighestSongPLayed.size()<10){
                                     HighestSongPLayed.add(temp0);
                                }
                                else if(HighestSongPLayed.size()==10){
                                     HighestSongPLayed.add(checkForAll,temp0);
                                }
                               }
                               else if(presentAlreadyAllIndex!=-1){
                                HighestSongPLayed.add(presentAlreadyAllIndex,temp0);
                               }
                            }
                            else if(presentAlready==0 && AddingMusicCount.size()<10){
                                CountMusic temp0=new CountMusic(music);
                                temp0.count=checkTheMusic.get(music);
                                temp0.musicname=music;
                                AddingMusicCount.add(temp0);
                                int checkForAll=-1;
                               int presentAlreadyAll=0;
                               int presentAlreadyAllIndex=-1;
                               for(int e=0;e<HighestSongPLayed.size();e++){
                                if(HighestSongPLayed.get(e).musicname.equals(music)){
                                    presentAlreadyAll=1;
                                    presentAlreadyAllIndex=e;
                                }
                                else if(checkForAll==-1 && HighestSongPLayed.get(e).count<checkTheMusic.get(music)){
                                    checkForAll=e;
                                }
                                else if(checkForAll!=-1 && HighestSongPLayed.get(e).count<HighestSongPLayed.get(checkForAll).count){
                                    checkForAll=e;
                                }
                               }
                               if(checkForAll!=-1 && presentAlreadyAll==0){
                                if(HighestSongPLayed.size()<10){
                                     HighestSongPLayed.add(temp0);
                                }
                                else if(HighestSongPLayed.size()==10){
                                     HighestSongPLayed.add(checkForAll,temp0);
                                }
                               }
                            }
                            else if(presentAlready==1){
                                CountMusic temp0=new CountMusic(music);
                               temp0.count=checkTheMusic.get(music);
                               temp0.musicname=music;
                               temp0.count=checkTheMusic.get(music);
                                AddingMusicCount.add(presentAlreadyIndex,temp0);
                            }
                            HighestMusicPlayBySinger.put(singer,AddingMusicCount);
                        }
                        }
                        else{
                            throw new Exception("no music");
                        }
                      }
                      catch(Exception e){
                        System.out.print(e);
                      }
                }
                else{
                    throw new Exception("no singer");
                }
            }
            catch(Exception e){
                System.out.print(e);
            }
        }
        else if(singer.length()!=0 && music.length()==0){
            ArrayList<CountMusic> getmusicforsinger=HighestMusicPlayBySinger.get(singer);
            if(getmusicforsinger.size()==0){
                System.out.print("no song");
                return;
            }
            System.out.print("music" + getmusicforsinger.get(0).musicname + getmusicforsinger.get(0).count);
            getmusicforsinger.get(0).count=getmusicforsinger.get(0).count+1;
            int checkForAll=-1;
            int presentAlreadyAll=0;
            int presentAlreadyAllIndex=-1;
            CountMusic temp0=new CountMusic(music);
            temp0.count=getmusicforsinger.get(0).count;
                               for(int e=0;e<HighestSongPLayed.size();e++){
                                if(HighestSongPLayed.get(e).musicname.equals(music)){
                                    presentAlreadyAll=1;
                                    presentAlreadyAllIndex=e;
                                }
                                else if(checkForAll==-1 && HighestSongPLayed.get(e).count<getmusicforsinger.get(0).count){
                                    checkForAll=e;
                                }
                                else if(checkForAll!=-1 && HighestSongPLayed.get(e).count<HighestSongPLayed.get(checkForAll).count){
                                    checkForAll=e;
                                }
                               }
                               if(checkForAll!=-1 && presentAlreadyAll==0){
                                if(HighestSongPLayed.size()<10){
                                     HighestSongPLayed.add(temp0);
                                }
                                else if(HighestSongPLayed.size()==10){
                                     HighestSongPLayed.add(checkForAll,temp0);
                                }
                               }
                               else if(presentAlreadyAllIndex!=-1){
                                HighestSongPLayed.add(presentAlreadyAllIndex,temp0);
                               }
                               HighestMusicPlayBySinger.put(music,getmusicforsinger);
        }
        else if(singer.length()==0  && music.length()!=0){
            if(!MusicSinger.containsKey(music)){
             System.out.print("no song");
             return;
            }
            String singer2=MusicSinger.get(music);
            PlayMusic(singer2,music);
        }
    }
    public static void printHighestMusicSinger(String singer){
        if(singer.length()!=0){
             try{
                  if(!isSinger.get(singer)){
                    throw new Exception("no singer");
                  }
             }
             catch(Exception e){
                  System.out.print(e);
             }
             if(!HighestMusicPlayBySinger.containsKey(singer)){
                  System.out.println("no song");
                  return;
             }
             ArrayList<CountMusic> temp0=HighestMusicPlayBySinger.get(singer);
             for(int e=0;e<HighestMusicPlayBySinger.size();e++){
                System.out.println("music by" + singer + temp0.get(e).musicname + temp0.get(e).count);
             }
        }
        else{
            System.out.print("please enter singer");
        }
    }
    public static void printHighestMusic(){
        if(HighestSongPLayed.size()==0){
            System.out.print("no song");
            return;
        }
        for(int e=0;e<HighestSongPLayed.size();e++){
            System.out.println("music" + HighestSongPLayed.get(e).musicname + HighestSongPLayed.get(e).count);
        }
    }
    public static void main(String [] args){
        Scanner temp2=new Scanner(System.in);
        Iniatializer();
        for(int temp0=0;temp0<10;temp0++){
                 String music=temp2.nextLine();
                 String singer=temp2.nextLine();
                 AddMusicNameAndSinger(music,singer);
                 //PlayMusic("darshan","");
        }
        PlayMusic("darshan","tera zikr");
    }
}
