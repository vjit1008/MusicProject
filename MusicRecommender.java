package musicProject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;




public class MusicRecommender {

	static private List<Music> list1;
    public MusicRecommender(String s) throws MusicFileFormatException, FileNotFoundException
    {
	 File file = new File(s);  
	 if (!file.exists()) 
	 {
	     System.out.println(file.getAbsolutePath());
	     throw new FileNotFoundException();
	 }
	 String fileName = file.toString();
	 String extension="";
	 int index = fileName.lastIndexOf('.');
	 if(index > 0)
	 {
	     extension = fileName.substring(index + 1);
	 }
	 if(!extension.equals("txt"))
	 {
	     throw new MusicFileFormatException();
	 }
	 Music m;
	 File f = new File("C:\\Users\\Akshay\\eclipse-workspace\\neebalgurukul\\src\\com\\gurukul\\day13\\project\\Music_list.txt");
	 list1 = new ArrayList<>();
	 Scanner sc = new Scanner(f);
		
		     while (sc.hasNext()) 
		     {
			 String s1 = sc.nextLine();
			 String x[]=s1.split(" ");
			
			    m = new Music(x[0],x[1],x[2],Integer.parseInt(x[3]),Integer.parseInt(x[4]));
			    list1.add(m);
		     }
    }
    
    
    public static ArrayList<Music> searchArtists(MusicProfile p) throws NoRecommendationException
    {
	Music m;
	     ArrayList <Music> list2 = new ArrayList<>();
		for(int i=0;i<list1.size();i++)
		{
		    m= list1.get(i);
			if(m.getArtist().equalsIgnoreCase(p.getPreferredArtist()))
			{
			    m.setPopularity(m.getPopularity()+1);
			    list2.add(m);
			}
		 }
	    
	return list2;
    }
    
    
    public static ArrayList<Music> genreBasedRecommendation(MusicProfile p) throws NoRecommendationException
    {
	Music m = null;
	
	ArrayList <Music> list2 = new ArrayList<>();
	for(int i=0;i<list1.size();i++)
	{
	    m= list1.get(i);
		if(m.getGenre().equalsIgnoreCase(p.getPreferredGenre()))
		{
		    m.setPopularity(m.getPopularity()+1);
		    list2.add(m);
		}
	 }
		    
	return list2;
}
    
    static ArrayList<Music> BPMBasedRecommendation(MusicProfile p) throws NoRecommendationException
    {
	Music m = null;
	
	ArrayList <Music> list2 = new ArrayList<>();
	for(int i=0;i<list1.size();i++)
	{
	    m= list1.get(i);
		if(m.getBPM()-5 < p.getPreferredBPM() && m.getBPM()+5>p.getPreferredBPM())
		{
		    m.setPopularity(m.getPopularity()+1);
		    list2.add(m);
		}
	 }
		    
	return list2;
	
    }
    
    static Music getMostPopularMusic()
    {
	Music m = null;
	
	ArrayList <Music> list2 = new ArrayList<>();
	Music max=list1.get(0);
	
	for(int i=1;i<list1.size();i++)
	{
	    m= list1.get(i);
	    
		if(m.getPopularity()>max.getPopularity())
		{
		    max = m;  
		    
		}
	 }
	max.setPopularity(max.getPopularity()+1);    
	return max;	
	
    }
    
    static void writeToStream(OutputStream outputStream,List<Music> s)
    {
	try
	{
	    
	    for (int i = 0; i < s.size(); i++) {
		byte[] data = s.get(i).toString().getBytes();
		outputStream.write(data);
	    }
	    
	    outputStream.flush();
	    
	    
	}
	catch(IOException e)
	{
	    throw new RuntimeException(e);
	}
	finally
	{
	    try
		{
		    outputStream.close();
		}
		catch(IOException e)
		{
		    throw new RuntimeException(e);
		}
	}
    }
    
    static void saveMusicList()
    {
	
	try (OutputStream outputStream = new FileOutputStream("C:\\Users\\Akshay\\eclipse-workspace\\neebalgurukul\\src\\com\\gurukul\\day13\\project\\Music_list.txt")) {
	    writeToStream(outputStream, list1);
	} catch(IOException e) {
	    throw new RuntimeException();
	}
    }
}
