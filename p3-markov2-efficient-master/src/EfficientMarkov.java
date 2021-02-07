import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();

	public EfficientMarkov(int order) {
		super(order);
		myRandom = new Random(RANDOM_SEED);

	}

	public EfficientMarkov() {
		this(3);
	}

	/**
	 *
	 * @param text the trainer text; method gives back the the text trained
	 */
	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		this.myMap.clear();

		for (int k = 0; k <= text.length() - this.myOrder; k++) {

			String key = this.myText.substring(k, k + this.myOrder);

			if(!this.myMap.containsKey(key)){
				this.myMap.put(key, new ArrayList<String>());
			}

			if( k + this.myOrder >= this.myText.length()){
				(this.myMap.get(key)).add(PSEUDO_EOS);
			}
			else{
				(this.myMap.get(key)).add(this.myText.substring(k + this.myOrder, k + this.myOrder + 1));
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
	public ArrayList<String> getFollows(String key) {
		if (!this.myMap.containsKey(key)) {
			//return null;
			throw new NoSuchElementException(key+" not in map");
		}
		else {
			return myMap.get(key);
		}
	}

}