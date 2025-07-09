To try out the scenario described in section 13.1 on Evolving APIs you need to do the following:

compile individual files as follows from the directory src/main/java:

javac com/manje/modernJavaInAction/chap13/compatability/Resizable.java
javac com/manje/modernJavaInAction/chap13/compatability/Ellipse.java
javac com/manje/modernJavaInAction/chap13/compatability/Utils.java
javac com/manje/modernJavaInAction/chap13/compatability/Game.java

You can run the application and everything will work:

java com/manje/modernJavaInAction/chap13/compatability/Game

You can now modify the interface Resizable and add the method "setRelativeSize".
Compile and run, no problem:

javac com/manje/modernJavaInAction/chap13/compatability/Resizable.java

Now modify Utils to use the new setRelativeSize method available on all kinds of Resizable.
Just uncomment the appropriate the line in Utils, compile, run, and you'll have a surprise!

Exception in thread "main" java.lang.AbstractMethodError: java89inaction.chap13.Square.setRelativeSize(II)V

Note also that recompiling the whole application will fail because Ellipse doesn't implement
the new method setRelativeSize:

javac com/manje/modernJavaInAction/chap13/compatability/Ellipse.java
java89inaction/chap13/Ellipse.java:6: error: Ellipse is not abstract and does not override abstract method setRelativeSize(int,int) in Resizable
public class Ellipse implements Resizable {
^
1 error

The problem can be fixed by ensuring that setRelativeSize is a default method:

default void setRelativeSize(int widthFactor, int heightFactor) {
// a default implementation
}
