package textgen;

import java.util.*;


/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;

	private HashSet<String> wordSet = new HashSet<>();
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		String[] result = sourceText.split("\\s+");
		if(result.length == 0) return;
		//init
//		this.wordList = new LinkedList<>();
//		this.wordSet = new HashSet<>();
		//start to parse
		this.starter = result[0];
		String prev = this.starter;
		this.wordList.add(new ListNode(starter));
		this.wordSet.add(starter);
		for(int i = 1; i < result.length; i++){
			String w = result[i];
			checkAndAdd(prev,  w);
			prev = w;
		}
		//add start to be the next word of the last word
		checkAndAdd(prev,  this.starter);
	}


	public void checkAndAdd(String prev, String w){
		if(!this.wordSet.contains(prev)){
			this.wordSet.add(prev);
			ListNode wordNode = new ListNode(prev);
			wordNode.addNextWord(w);
			this.wordList.add(wordNode);
		}else{
			for(ListNode node : this.wordList){
				if(node.getWord().equals(prev)){
					node.addNextWord(w);
				}
			}
		}
	}
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if(numWords == 0) return "";
	    String curWord = this.wordList.size()>0 ?
				this.wordList.get(0).getWord() : "";
	    String outputText = curWord;
	    for(int i = 1; i < numWords; i++){
	    	for(ListNode node : this.wordList){
	    		if(node.getWord().equals(curWord)){
	    			curWord = node.getRandomNextWord(new Random());
	    			break;
				}
			}
	    	outputText += " " + curWord;
		}

		return outputText;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		String[] result = sourceText.split("\\s+");
		if(result.length == 0) return;

//		//init
		this.wordList = new LinkedList<>();
		this.wordSet = new HashSet<>();

		this.starter = result[0];
		String prev = this.starter;
		this.wordList.add(new ListNode(starter));
		this.wordSet.add(starter);
		for(int i = 1; i < result.length; i++){
			String w = result[i];
			checkAndAdd(prev,  w);
			prev = w;
		}
		//add start to be the next word of the last word
		checkAndAdd(prev,  this.starter);

	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString1 = "hi there hi Leo";
		gen.train(textString1);
		System.out.println(gen);
		System.out.println(gen.generateText(4));


		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));

		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int index = generator.nextInt(nextWords.size());
		return this.nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


