### VARIABLES ###

JC = javac
JCFLAGS = -implicit:none

JVM = java

### REGLES ESSENTIELLES ###

Main.class : Main.java Menu.class
	${JC} ${JCFLAGS} Main.java

Menu.class : Menu.java ListenerMenu.class MenuView.class
	${JC} ${JCFLAGS} Menu.java

ListenerMenu.class : ListenerMenu.java Board.class
	${JC} ${JCFLAGS} ListenerMenu.java

Board.class : Board.java FileBoard.class RandomBoard.class BoardView.class BoardListener.class
	${JC} ${JCFLAGS} Board.java

BoardListener.class : BoardListener.java BoardView.class FileBoard.class RandomBoard.class
	${JC} ${JCFLAGS} BoardListener.java

BoardModel.class : BoardModel.java Liste.class
	${JC} ${JCFLAGS} BoardModel.java

FileBoard.class : FileBoard.java BoardModel.class
	${JC} ${JCFLAGS} FileBoard.java

RandomBoard.class : RandomBoard.java BoardModel.class
	${JC} ${JCFLAGS} RandomBoard.java

BoardView.class : BoardView.java BoardModel.class
	${JC} ${JCFLAGS} BoardView.java

Liste.class : Liste.java
	${JC} ${JCFLAGS} Liste.java

MenuView.class : MenuView.java
	${JC} ${JCFLAGS} MenuView.java

### REGLES OPTIONELLES ###

run : Main.class
	${JVM} Main

clean : 
	rm -rf *.class doc

doc : 
	javadoc -d doc *.java

readDoc : ./doc
	xdg-open ./doc/allclasses-index.html

### BUTS FACTICES ###

.PHONY : run clean doc readDoc

### FIN ###