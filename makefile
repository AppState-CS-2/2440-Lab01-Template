JUNIT5_JAR = lib/junit-platform-console-standalone-1.8.2.jar 
JUNIT5_RUNNER = org.junit.platform.console.ConsoleLauncher
CKSTYLE_COMMAND =  -jar lib/checkstyle-10.5.0-all.jar 
JAVAC_FLAGS = -d bin -cp src/main:$(JUNIT5_JAR)
JUNIT_FLAGS = -jar $(JUNIT5_JAR) -cp bin --select-class 

default: 
	@echo "usage: make target"
	@echo "____________________ rank - compile and run Rank tests"
	@echo "____________________ suit - compile and run Suit tests"
	@echo "____________________ playingCard - compile and run PlayingCard tests"
	@echo "____________________ deck - compile and run Deck tests"
	@echo "____________________ test - compile and run all tests"
	@echo "____________________ demo - compile and run the demo"
	@echo "____________________ check - runs checkstyle"
	@echo "____________________ compile - compiles all classes"
	@echo "____________________ clean - removes all class files"

rank:
	javac $(JAVAC_FLAGS) src/main/deck/Rank.java
	javac $(JAVAC_FLAGS) src/tests/deck/RankTest.java
	java $(JUNIT_FLAGS) deck.RankTest

suit:
	javac $(JAVAC_FLAGS) src/main/deck/Suit.java
	javac $(JAVAC_FLAGS) src/tests/deck/SuitTest.java
	java $(JUNIT_FLAGS) deck.SuitTest

playingCard:
	javac $(JAVAC_FLAGS) src/main/deck/PlayingCard.java
	javac $(JAVAC_FLAGS) src/tests/deck/PlayingCardTest.java
	java $(JUNIT_FLAGS) deck.PlayingCardTest

deck:
	javac $(JAVAC_FLAGS) src/main/deck/Deck.java
	javac $(JAVAC_FLAGS) src/tests/deck/DeckTest.java
	java $(JUNIT_FLAGS) deck.PlayingCardTest

compile:
	javac $(JAVAC_FLAGS) src/**/**/*.java

test:
	make compile
	java -cp bin:$(JUNIT5_JAR) $(JUNIT5_RUNNER) --scan-class-path
	
demo:
	make compile
	java -cp .:bin client.Demo

check:
	java11 $(CKSTYLE_COMMAND) -c resources/styles.xml src/main/**/*.java

clean:
	rm -f ./bin/**/*.class
