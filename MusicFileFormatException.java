package musicProject;

public class MusicFileFormatException extends Exception
{
    @Override
	public String toString()
	{
		return "file extention is incorrect";
	}
}

class NoRecommendationException extends Exception
{
    @Override
	public String toString()
	{
		return "Oops !!! No recomendation found...";
	}
}
