package com.synacy.poker.hand.processors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ProcessorChainFactory {
	
	private static ProcessorChainFactory instance = null;
	private static HandValueProcessor processor = null;
	
	public ProcessorChainFactory() {
		//Make sure this will be initialized only once since these are only processors
		if (processor == null) {
			processor = initHandValueChainProcessor();
		}
	}

	/**
	 * Intiializes the chain
	 * 
	 * @return {@link HandValueProcessor} - the very top in the chain
	 */
	private HandValueProcessor initHandValueChainProcessor() {
				
		//This is the very tip of the chain (bottom)
		//terminate the chain with null
		HandValueProcessor highCard = new HighCardProcessor(null);
		
		//This is the middle processors
		HandValueProcessor onePair = new OnePairProcessor(highCard);
		HandValueProcessor twoPairs = new TwoPairsProcessor(onePair);
		HandValueProcessor threeOfAKind = new ThreeOfAKindProcessor(twoPairs);
		HandValueProcessor straight = new StraightProcessor(threeOfAKind);
		HandValueProcessor flush = new FlushProcessor(straight);
		HandValueProcessor fullHouse = new FullHouseProcessor(flush);
		HandValueProcessor fourOfAKind = new FourOfAKindProcessor(fullHouse);
		
		//This is the very top of the chain (top)
		//set the next processor in the chain
		HandValueProcessor straightFlush = new StraightFlushProcessor(fourOfAKind);
		
		return straightFlush;
		
	}
	
	/**
	 * Processor will find the best combination of 5 cards 
	 * which in turn return the best player's Hand combination
	 * 
	 * @return {@link HandValueProcessor}
	 */
	public HandValueProcessor getProcessor() {
		return processor;
	}
}
