import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram,ArrayList<String>> myMap;
    public EfficientWordMarkov() {
        this(2);
    }

    public EfficientWordMarkov(int order){
        super(order);
        this.myMap = new HashMap();
        this.myRandom = new Random(RANDOM_SEED);
    }

    /**
     *
     * @param text to get the strings
     *        this is the "trainer" method
     */
    @Override
    public void setTraining(String text) {
        super.setTraining(text);
        this.myMap.clear();
        myWords = text.split("\\s+");

        for (int k = 0; k <= myWords.length - myOrder; k ++) {

            WordGram key = new WordGram(myWords,k, myOrder);

            if(!myMap.containsKey(key)){
                myMap.put(key, new ArrayList<String>());
            }

            if((k+myOrder) >= myWords.length){
                myMap.get(key).add(PSEUDO_EOS);
            }
            else{
                myMap.get(key).add(myWords[k+myOrder]);
            }

        }
    }

    /**
     *
     * @param key the arraylist that contains all the values that with the same
     *            starting point
     * @return arraylist of the different "follow-ups"
     */
    @Override
    public ArrayList<String> getFollows(WordGram key){
        if(!myMap.containsKey(key)){
            throw new NoSuchElementException(key+" not in map");
        }
        else{
            return myMap.get(key);
        }
    }
}