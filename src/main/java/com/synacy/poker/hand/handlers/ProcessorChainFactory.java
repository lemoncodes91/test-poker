package com.synacy.poker.hand.handlers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ProcessorChainFactory {
	
	private static ProcessorChainFactory instance = null;
	private static AbstractHandler processor = null;
	
	public ProcessorChainFactory() {
		//Make sure this will be initialized only once since these are only processors
		if (processor == null) {
			processor = initHandValueChainProcessor();
		}
	}

	/**
	 * Intiializes the chain
	 * 
	 * @return {@link AbstractHandler} - the very top in the chain
	 */
	private AbstractHandler initHandValueChainProcessor() {
				
		//This is the very tip of the chain (bottom)
		//terminate the chain with null
		AbstractHandler highCard = new HighCardHandler(null);
		
		//This is the middle processors
		AbstractHandler onePair = new OnePairCardHandHandler(highCard);
		AbstractHandler twoPairs = new TwoPairsCardHandHandler(onePair);
		AbstractHandler threeOfAKind = new ThreeOfAKindCardHandHandler(twoPairs);
		AbstractHandler straight = new StraightCardHandHandler(threeOfAKind);
		AbstractHandler flush = new FlushCardHandler(straight);
		AbstractHandler fullHouse = new FullHouseCardHandler(flush);
		AbstractHandler fourOfAKind = new FourOfAKindHandler(fullHouse);
		
		//This is the very top of the chain (top)
		//set the next processor in the chain
		AbstractHandler straightFlush = new StraightFlushCardHandHandler(fourOfAKind);
		
		return straightFlush;
		
	}
	
	/**
	 * Processor will find the best combination of 5 cards 
	 * which in turn return the best player's Hand combination
	 * 
	 * @return {@link AbstractHandler}
	 */
	public AbstractHandler getProcessor() {
		return processor;
	}
}
