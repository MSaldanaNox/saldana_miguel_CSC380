package fds.server;

import java.io.Serializable;
import java.util.Random;

public class WordsLogic implements Serializable, ReflectionParent {

	private String[] words = {"Hello","MIA","word","SOA"};
	private static WordsLogic INSTANCE = null;
	public WordsLogic() {
		
	}
	
	public String getRandomWord(String blah){
		return "Your word: " + blah;
	}

}
