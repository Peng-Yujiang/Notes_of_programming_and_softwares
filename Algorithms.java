/* A note of the book Algrithems, fourth edition */
/* Author: Yujiang Peng */
/* Date: March 1, 2019 */
 
 
 //Format conventions for printf()
 int     d    512        "%14d"         "           512"
                                                                           "%-14d"       "512           "
 double  f    1595.1680010754388       "%14.2f"      "      1595.17"
                    e                                                     "%.7f"           "1595.1680011"
                                                                           "%14.4e"      "    1.5952e+03"
 String         s       "Hello, World"                    "%14s"          "  Hello, World"
                                                                           "%-14s"         "Hello, World  "
                                                                           "%-14.5s"      "Hello         "

//public class StdIn
static boolean isEmpty()                               //true if no more values, false otherwise
static     int readInt()                        //read a value of type int
static  double readDouble()                        //read a value of type double
static   float readFloat()                      //read a value of type float
static    long readLong()                       //read a value of type long
static boolean readBoolean()                      //read a value of type boolean
static    char readChar()                      //read a value of type char
static    byte readByte()                      //read a value of type byte
static  String readString()                    //read a value of type String
static boolean hasNextLine()                    //is there another line in the input stream?
static  String readLine()                   //read the rest of the line
static  String readAll()                   //read the rest of hte input stream

//Redirection and piping
//Redirection and piping from the command line
//redirecting standard output to a file
% java RandomSeq 1000 100.0 200.0 > data.txt
//redirecting from a file to standard input
% java Average < data.txt
//piping the output of one program to the input of another
% java RandomSeq 1000 100.0 200.0 | java Average

//Input and output from a file
//public class In
static         int[] readInts(String name)            //read int values
static double[] readDoubles(String name)    //read double values
static   String[] readStrings(String name)       //read String values
//public class Out
static void write(int[] a, String name)             //write int values
static void write(double[] a, String name)      //write double values
static void write(String[] a, String name)        //write String values
//Note 1: Other primitive types are supported.
//Note 2: StdIn and StdOut are supported (omit name argument).

//Standard drawing (basic methods)
//public class StdDraw
static void line(double x0, double y0, double x1, double y1)
static void point(double x, double y)
static void text(double x, double y, String s)
static void circle(double x, double y, double r)
static void filledCircle(double x, double y, double r)
static void ellipse(double x, double y, double rw, double rh)
static void filledEllipse(double x, double y, double rw, double rh)
static void Square(double x, double y, double r)
static void filledSquare(double x, double y, double r)
static void rectangle(double x, double y, double rw, double rh)
static void filledRectangle(double x, double y, double rw, double rh)
static void polygon(double[] x, double[] y)
static void filledPolygon(double[] x, double[] y)
//StdDraw examples
StdDraw.point(x0, y0);
StdDraw.line(x0, y0, x1, y1);
StdDraw.circle(x, y, r);
StdDraw.square(x, y, r);
double[] x = {x0, x1, x2, x3};
double[] y = {y0, y1,y2, y3};
StdDraw.polygon(x, y);

//Standard drawing (control methods)
//public class StdDraw
static void setXscale(double x0, double x1)    //reset x range to (x0, x1)
static void setYscale(double y0, double y1)    //reset y range to (y0, y1)
static void setPenRadius(double r)                   //set pen radius to r
static void setPenColor(Color c)                        //set pen color to c
static void SetFont(Font f)                                  //set text font to f
static void setCanvasSize(int w, int h)              //set canvas to w-by-h window
static void clear(Color c)                                     //clear the canvas; color it c
static void show(int dt)                                       //show all; pause dt milliseconds
//colors BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY, MAGENTA, 
//ORANGE, PINK, RED, BOOK_RED, WHITE, YELLOW
  
 //Binary search
 import java.util. Arrays;
 public class BinarySearch
 {
     public static int rank(int key, int[] a)
     {  //Array must be sorted.
         int lo = 0;
         int hi = a.length - 1;
         while (lo <= hi)
         {     //Key is in a[lo, hi] or not present.
             int mid = lo  + (hi - lo) / 2;
             if         (key < a[mid]) hi = mid - 1;
             else if (key > a[mid]) lo = mid + 1;
             else                              return mid;
         }
         return -1;
     }
     public static void main(String[] args)
     {
         int[] whitelist = In.readInts(args[0]);
         Arrays.sort(whitelist);
         while (!StdIn.isEmpty())
         {     //Read key, print if not in whitelist.
             int key = StdIn.readInt();
             if (rank(key, whitelist) < 0)
                    StdOut.println(key);
         }
     }
 }
 % java BinarySearch tinyW.txt < tinyT.txt



//DATA ABSTRACTION
public class Counter
Counter(String id)                                          //create a coujnter named id
void increment()                                            //increment the counter by one
int tally()                                                          //number of increments since creation
String toString()                                             //string representation
//Creating an object
Counter heads = new Counter("heads");
Counter tails = new Counter("tails");
//Invoking instance methods
Counter heads;
heads = new Counter("heads");
heads.increment();
heads.tally() - tails.tally()
StdOut.println(heads);
//Instance methods versus static methods
//                            instance method              static method
//sample call         head.increment()           Math.sqrt(2.0)
//invoked with         object name                   class name
//parameters         reference to object          argument(s)
//                                and argument(s)
//primary              examine or change         compute return
//purpose                   object value                     value

//Counter client taht simulates T coin flips
public class Flips
{
    public static void main(String[] args)
    {
        int T = Integer.parseInt(args[0]);
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for (int t = 0; t < T; t++)
            if (StdRandom.bernoulli(0.5))
                    heads.increment();
             else tails.increment();
             StdOut.println(heads);
             StdOut.println(tails);
             int d = heads.tally() - tails.tally();
             StdOut.println("delta:" + Math.abs(d));
    }
}
% java Flips 10
5 heads
5 tails
delta: 9
% java Flips 10
8 heads
2 tails
delta: 6
% java Flips 1000000
499710 heads
500290 tails
delta: 580

//Example of a static method with object argument and return values
public class FlipsAMax
{
    public static Counter max(Counter x, Counter y)
    {
        if (x.tally() > y.tally())    return x;
        else                                 return y;
    }
    public static void main(String[] args)
    {
        int T = Integer.parseInt(args[0]);
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for (int t = 0; t < T; t++)
            if (StdRandom.bernoulli(0.5))
                         heads.increment();
            else tails.increment();
         if (heads.tally() == tails.tally())
                    StdOut.println("Tie");
          else StdOut.println(max(heads, tails) + "wins");
    }
}
% java FlipMax 1000000
500281 tails wins

//Counter client that simulates T rolls of a die
public class Rolls
{
    public static void main(String[] args)
    {
        int T = Integer.parseInt(args[0]);
        int SIDES = 6;
        Counter[] rolls = new Counter[SIDES+1];
        for (int i = 1; i <= SIDES; i++)
            rolls[i] = new Counter(i + "'s");
        for (int t = 0; t < T; t++)
        {
            int result = StdRandom.uniform(1,  SIDES+1);
            rolls[result].increment();
        }
        for (int i = 1; i <=  SIDES; i++)
            StdOut.println(rolls[i]);
    }
}
% java Rolls 1000000
167308 1's
166540 2's
166087 3's
167051 4's
166422 5's
166592 6's

//Selected ADTs used in this book
//standard Java system types in java.lang
Integer  //int wrapper
Double  //double wrapper
String   //indexed chars
StringBuilder //builder for strings
//other Java types
java.awt.Color    //colors
java.awt.Font     //fonts
java.net.URL      //URLs
java.io.File     //files
//out standard I/O types
In   //input stream
Out     //output stream
Draw    //drawing
//data-oriented types for client examples
Point2D  //point in the plane
Interval1D    //1D interval
Interval2D    //2D interval
Date    //date
Transaction   //transacion
//types for the analysis of algorithms
Counter  //counter
Accumulator  //accumulator
VisualAccumulator     //visual version
Stopwatch     //stopwatch
//collection types
Stack    //pushdown stack
Queue   //FIFO queue
Bag     //bag
MinPQ MaxPQ    //priority queue
IndexMaxPQ IndexMinPQ   //priority queue(indexed)
ST   //symbol table
SET      //set 
StringST      //symbol table(string keys)
//data-oriented graph types
Graph   //graph
Digraph      //directed graph
Edge      //edge(weighted)
EdgeWeightedGraph   //graph(weighted)
DirectedEdge     //edge(directed, weighted)
EdgeWeightedDigraph      //graph(directed, weighted)
//operations-oriented graph types
UF   //dynamic connectivity
DepthFirstPaths   //DFS path searcher
CC   //connected components
BreadthFirstPaths      //BFS path search
DirectedDFS  //DFS digraph path search
DirectedBFS   //BFS digraph path search
TransitiveClosure   //all paths
Topological    //topological order
DepthFirstOrder    //DFS order
DirectedCycle       //cycle search
SCC       //strong components
MST       //minimum spanning tree
SP   //shortest paths

//Interval2D test client
public static void mian(String[] args)
{
    double xlo = double.parseDouble(arg[0]);
    double xhi = double.parseDouble(arg[1]);
    double ylo = double.parseDouble(arg[2]);
    double yhi = double.parseDouble(arg[3]);
    int T = Integer.parseInt(arg[4]);
    
    Interval1D x = new Interval1D(xlo, xhi);
    Interval1D y = new Interval1D(ylo, yhi);
    Interval2D box = new Interval2D(x, y);
    box.draw();
    
    Counter c = new Conter("hits");
    for (int t = 0; t < T; t++)
    {
        double x = Math.random();
        double y = Math.random();
        Point p = new Point(x, y);
        if (box.contains(p)) c.increment();
        else              p.draw();
    }
    StdOut.println(c);
    StdOut.println(box.area());
}
% java Interval2D .2 .5 .5 .6 10000
297 hits
.03

//An API for points in the plane
//public class Point2D
Point2D(double x, double y)       //create a point
double x()       //x coordinate
double y()      //y coordinate
double r()       //radius(polar coordinates)
double theta()      //angle (polar coordinates)
double distTo(Point2D that)       //Euclidean distance from this point to that
     void draw()     //draw the point on StdDraw
//An API for intervals on the line
//public class Interval1D
Interval1D(double lo, double hi)      //create an interval
  double lenght()      //lenght of hte interval
boolean contains(double x)         //does the interval contain x?
boolean intersects(Interval1D that)        //does the interval intersect taht?
       void draw()         //draw the interval on StdDraw
//An API for two dimensional intervals in the plane
//public class Interval2D
Interval2D(Interval1D x, Interval1D y)       //create a 2D interval
  double area()        //area of the 2D interval
boolean contains(Point P)        //does the 2D interval contain p?
boolean intersects(Interval2D that)        //does the 2D interval intersect that?
       void draw()         //draw the 2D interval on StdDraw

//Sample APIs for commercial applications (dates and transactions)
//public class Date impliments Comparable<Date>
            Date(int month, int day, int year)    //create a date
            Date(String date)      //create a date (parse constructor)
      int month()      //month
      int day()    //day
      int year()      //year
 String toString()    //string representation
 boolean equals(object that) //is this hte same date as that?
     int comparable(Date that)  //compare this date to that
     int hashCode() //hash code
//public class Transaction implements Comparable<Transaction>
              Transaction(String who, Date when, double amount)
              Transaction(String transaction)      //create a transaction (parse constructor)
     String who()       //customer name
       Date when()       //date
   double amount   //amount
     String toString()      //string representation
 boolean equals(Object taht)    //is this the same transaction as that?
           int compareTo(Transaction that)    //compare this transaction to that
           int hashCode()    //hash code

//Strings
//Java String API
//public class String
            String()     //create an empty string
    int length()     //length of the string
    int charAt(int i)      //ith character
    int indexOf(String p)      //first occurrence of p (-1 if none)
    int indexOf(String p, int i)     //first occurrence of p after i (-1 if none)
String concat(String t)       //this string with t appended
String substring(int i, int j)       //substring of this string ( ith to j-1st chars)
String[] split(String delim)        //strins between occurrences of delim
    int comareTo(String t)           //string comparision
boolean equals(String t)           //is this string's value the same as t's?
    int hashCode()        //hash code
    
//Typical stirng-processing code
//is the string a palinderome?
public static boolean isPalindrome(String s)
{
    int N = s.length();
    for (int i = 0; i < N/2; i++)
      if (s.charAt(i) != s.charAt(N-1-i))
            return false;
    return true;
}
//extract file name and extension from a command-line argument
String s = args[0];
int dot = s.rank(".");
String base          = s.substring(0, dot);
String extension = s.substring(dot + 1, s.length());
//print all lines in standard input that cantain a string specified on the command line
String query = args[0];
while (!StdIn.isEmpty())
{
    String s = StdIn.readLine();
    if (s.contains(query)) StdOut.println(s);
}
//create an array of hte strings an StdIn delimited by whiterpace
String input = StdIn.readAll();
String[] words = input.split("\\s+");
//check whether an array of strings is in alphabetical order
public boolean isSorted(String[] a)
{
    for (int i = 1; i < a.length; i++)
    {
        if (a[i-1].compareTo(a[i]) > 0)
              return false;
    }
    return true;
}

//Input and output revisited
//A sample In and Out client
public calss Cat
{
    public static void main(String[] args)
    {  //Copy input files to out (last argument).
    Out out = new Out(args[args.length-1]);
    for (int i = 0; i < args.length - 1; i++)
    {  //Copy input file named on ith arg to out.
    In in = new In (args[i]);
    String s= in.readAll();
    out.println(s);
    in.close();
    }
    out.close();
    }
}
% more in1.txt
This is 
% more in2.txt
a tiny
test.
% java Cat in1.txt in2.txt out.txt
% more out.txt
This is 
a tiny
test.

//API for our data type for input streams
//public class In
            In()                                                       //create an input stream from standard input
            In(String name)                                  //create an input stream from a file or website
boolean isEmpty()                                         //true if no more input, false otehrwise
       int readInt()                                             //read a value of type int
double readDouble()                                    //read a value of type double
      ...
 void close()                                                    //closse the input stream
 //Note: all operations supported by StdIn are also supported In objects
 //API for out data type for output streams
 //public class Out
         Out()                                                        //create an output stream to standard output
         Out(String name)                                   //create an output stream to a file
void print(String s)                                         //append s to the output stream
void println(String s)                                      //append s and a newline to the output stream
void println()                                                  //append a newline to the output stream
void printf(String f, ...)                                  //formatted print to the output stream
void close()                                                     //close the output stream
//Note: all operations suppported by StdOut are also supported for Out objects.
//API for our data type for drawings
//public class Draw
        Draw()
void line(double x0, double y0, double x1, double y1)
void point(double x, double y)
//Note: all operations supported by StdDraw are also supported for Draw objects

//Implementing an abstract data type
//Instance variables in ADTs are private
public class Counter
{
    private final String name;
    private int count;
    ...
}
//Anatomy of a class that defines a data type
public class Counter   //Counter is the class name
{
    private final String name;   //instance variables
    private int count;    //instance 
    
    //constructor
    public Counter(String id)
    { name = id;    }
    
    //instance methods
    public void increment()
    {  count++; }
    public int tally()
    {  return count; }
    public String toString()
    {  return count+ " " + name;   } //name is the instance variable name
    
    //test client
    public static void main(String[] args)
    {
        //create and initialize objects
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");   //Counter("tails") is invoke constructor
        
        heads.increment();
        heads.increment();
        tails.increment();
        
        //automatically invoke toString()
        StdOut.println(heads + " " + tails);
        StdOut.println(heads.tally() + tails.tally() ); //tails.tally() invoke method
    }
}
//Scope of instance and local variables in an instance method
public class Example
{
    private int var;  //instance variable
    ...
    private void methods1()
    {
        int var;   //local variable
        ...   var     ...   //refers to local variable, NOT instance variable
        ...   this.var   ...    //refers to instance variable
    }
    private void method2()
    {
        ...   var   ...    //refers to instance variable
    }
    ...
}

//API, clients, and implementations.
//An abstract data type for a simple counter
//API
public class Counter
         Counter(String id)                                 //create a counter named id
void increment()                                            //increment the counter
  int tally()                                                        //number of increments since creation
String toString()                                             //string representaiton
//typical client
public class Flips
{
    public static void main (String[] args)
    {
        int T = Integer.parseInt(args[0]);
        
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        
        for (int t = 0; t < T; t++)
            if (StdRandom.bernoulli(0.5))
                heads.increment();
            else tails.increment();
            
         StdOut.println(heads);
         StdOut.println(tails);
         int d = heads.tally() - tails.tally();
         StdOut.println("delta: " + Math.abs(d));
    }
}
//implementation
public class Counter
{
    private final String name;
    private int count;
    
    public Counter(String id)
    {  name = id;    }
    
    public void increment()
    {  count++;      }
    
    public int tally()
    {  return count;  }
    
    public String toString()
    {  return count + " " + name;  }
}
//application
% java Flips 1000000
500172 heads
499828 tails
delta: 344

//An abstract data type to encapsulate dates, with two implementations
//API
//public class Date
            Date(int month, int day, int year)    //create a date
      int month()                                                //month
      int day()                                                      //day
      int year()                                                   //year
String toString()                                             //string representation
//test client
public static void main(String[] args)
{
    int m = Integer.parseInt(args[0]);
    int d = Integer.parseInt(args[1]);
    int y = Integer.parseInt(args[2]);
    Date date =  new Date(m, d, y);
    StdOut.println(date);
}
//application
% java Date 12 31 1999
12/31/1999
//implementation
public class Date
{
    private final int month;
    private final int day;
    private final int year;
    
    public Date(int m, int d, int y)
    {  month = m, day = d, year = y;    }
    
    public int month()
    {  return month;    }
    
    public int day()
    {  return day;     }
    
    public int year()
    {  return year;    }
    
    public String toString()
    {  return month() + "/" + day() + "/" + year();  }
}
//alternate implementation
public class Date
{
    private final int value;
    
    public Date(int m, int d, int y)
    {  value = y*512 + m*32 +d;    }
    
    public int month()
    {  return (value / 32) % 16;    }
    
    public int day()
    {  return value % 32;  }
    
    public int year()
    {  return value / 512;     }
    
    public String toString()
    {  return month() + "/" + day() + "/" + year(); }
}

//An abstract data type for accumulating data values
//API
//public class Accumulator
             Accumulator()                                   //create an accumulator
    void addDataValue(double val)               //add a new data value
double mean()                                               //mean of all data values
  String toString()                                           //string representaiton
//typical client
public class TestAccumulator
{
    public static void main(String[] args)
    {
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.random());
            StdOut.println(a);
    }
}
//application
% java TestAccumulator 1000
Mean (1000 values): 0.51829
% java TestAccumulator 1000000
Mean (1000000 values): 0.49948
% java TestAccumulator 1000000
Mean (1000000 values): 0.50014
//implementation
public class Accumulator
{
    private double total;
    private int N;
    
    public void addDataValue(double val)
    {
        N++;
        total += val;
    }
    
    public double mean()
    {  return total/N;      }
    
    public String toString()
    {  return "Mean (" + N + " values): "
                                     + String.format("%7.5f", mean*())}
}

//An abstract data type for accumulating data values (visual version)
//API
//public class VisualAccumulator
              VisualAccumulator(int trials, double max)
     void addDataValue(double val)              //add a new data value
double avg()                                                   //average of all data values
String toString()                                             //string representation
//typical client
public class TestVisualAccumulator
{
    public static void main(String[] args)
    {
        int T = Integer.parseInt(args[0]);
        VisualAccumulator a = new VisualAccumulator(T, 1.0);
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.random());
            StdOut.println(a);
    }
}
//implementation
public class VisualAccumulator
{
    private double total;
    private int N;
    
    public VisualAccumulator(int trials, double max)
    {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(0.005);
    }
    
    public void addDataValue(double val)
    {
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK-GARY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total/N);
    }
    
    public double mean()
    public String toString()
    //Same as Accumulator.
}
% java TestVisualAccumulator 2000
Mean (2000 values): 0.509789

//Binary search react as an object-oriented program (an ADT for search in a set of integers)
//API
//public class StaticSETofInts
StaticSETofInts(int [] a)                                 //create a set form the values in a[]
boolean contains(int key)                             //is key in the set?
//typical client
public class Whitelist
{
    public static void main (String[] args)
    {
        int [] w = In.readInts(args[0]);
        StaticSETofInts set = new StaticSETofInts(w);
        while (!StdIn,isEmpty())
        {    //Read key, print if not in whitelist.
            int key = StdIn.readInt();
            if (set.rank(key) == -1)
               StdOut.println(key);
        }
    }
}
//implementation
import java.util.Arrays;
public class StaticSETofInts
{
    private int [] a;
    public StaticSETofInt(int [] keys)
    {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i];    //defensive copy
        Arrays.sort(a);
    }
    public boolean contains(int key)
    {  return rank(key) != -1;   }
    private int rank (int key)
    {   //Binary search.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi)
        {    //key is in a[lo.. hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if         (key < a[mid]) hi = mid -1;
            else if(key > a[mid]) lo = mid +1;
            else                             return mid;
        }
        return -1;
    }
}

//Java interfaces used in this book
                        interface                           methods           section
comparison   jave.lang.Comparable    compareTo()      2.1
                        jave.util.Comparetor      compare()          2.5
 iteration        jave.lang.Iterable            iterator()            1.3
                                                                   hasNext()
                        jave.util.Iterator              next()                  1.3
                                                                   remove()

//Inherited methods from Object used in this book
Class      getClass()                                         //what class is this object?
String     toString()                                          //string representation of this object
boolean equals(Object that)                       //is this object equal to that?
int           hashCode()                                      //hash code for this object

//Overriding toString() and equal(0 in a data-type definition
public class Date
{
    private final int month;
    private final int day;
    private final int year;
    
    public Date (int m, int d, int y)
    {  month = m; day = d; year = y;    }
    public int month()
    {  return month;    }
    public int day()
    {  return day;     }
    public int year()
    {  return year;    }
    
    public String toString()
    {  return month() + "/" + day() + "/" + year();  }
    public boolean equal(Object x)
    {
        if (this == x) return true;
        if (x == null) return false;
        if ( this.getClass() != x.getClass()) return false;
        Date that = (Date) x;
        if (this.day != that.day)      return false;
        if (this.month != that.month)    return false;
        if (this.year != that.year)             return false;
        return true;
    }
}

//1.3 Bags, Queues, and Stacks
//APIs for fundamental generic iterable collections
//Bag
//public class Bag<Item>  //implements Iterable <Item>
               Bag()                                                 //create an empty bag
       void add(Item item)                                 //add an item
boolean isEmpty()                                          //is the bag empty?
          int size()                                                  //number of items in the bag
//FIFO queue
//public class Queue<Item> //implements Iterable<Item>
                Queue()                                            //create an empty queue
        void enqueueu(Item item)                    //add an item
        Item dequeue()                                       //remove the least recently added item
boolean isEmpty()                                           //is the queue empty?
          int size()                                                  //number of items in the queue
//Pushdown (LIFO) stack
//public class Stack<Item>  //implements Iterable<Item>
                  Stack()                                           //create an empty stack
          void push(Item item)                          //add and item
          Item pop()                                             //remove the most recently added item
 boolean isEmpty()                                        //is the stack empty?
            int size()                                               //number of items in the stack

//typical Bag client
public class Stats
{
    public static void mian (String[] args)
    {
        Bag<Double> numbers = new Bag<Double>();
        while (!StdIn.isEmpty())
            numbers.add(StdIn.readDouble());
         int N = numbers.size();
         double sum = 0.0;
         for (double x : numbers)
             sum += x;
         double mean = sum/N;
         sum = 0.0;
         for (double x : numbers)
             sum += (x - mean)*(x - mean);
         double std = Math.sqrt(sum/(N - 1));
         StdOut.printf("Mean : %.2f\n", mean);
         StdOut.printf("Std dev : %.2f\n", std);
    }
}
//application
% java Stats
100
99
101
120
98
107
109
81
101
90
Mean: 100.60
Std dev: 10.51

//FIFO queues
//Sample Queue client
public static int[] readInts(String name)
{
    In in = new In(name);
    Queue<Integer> q = new Queue<Integer>();
    while (!in.isEmpty())
       q.enqueue(in.readInt());
       
     int N = q.size();
     int[] a = new int[N];
     for (int i = 0; i < N; i++)
        a[i] = q.dequeue();
    return a;
}

//Pushdown stacks
//LIFO
//Sample Stack client
public class Reverse
{
    public static void main (String [] args)
    {
        Stack<Integer> stack;
        stack  = new Stack<Integer>();
        while (!StdIn.isEmpty())
              stack.push(StdIn.readInt());
        for (int i : stack)
              StdOut.println(i);
    }
}

//Dijkstra's Two-Stack Algorithm for Expression Evaluation
public class Evaluation
{
    public static void main(String[] args)
    {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty())
        {      //Read token, push if operator.
            String s = StdIn.readString();
            if         (s.equals("("));
            else if (s.equals("+"))       ops.push(s);
            else if (s.equals("-"))        ops.push(s);
            else if (s.equals("*"))       ops.push(s);
            else if (s.equals("/"))        ops.push(s);
            else if (s.equals("sqrt"))   ops.push(s);
            else if (s.equals(")"))
            {   //Pop, evaluate, and push result if token is ")".
                String op = ops.pop();
                double v = vals.pop();
                if         (op.equals("+"))       v = vals.pop() + v;
                else if (op.equals("-"))        v = vals.pop() - v;
                else if (op.equals("*"))       v = vals.pop() * v;
                else if (op.equals("/"))       v = vals.pop() / v;
                else if (op.equals("squrt"))  v = Math.sqrt(v);
                vals.push(v);
            }   // Token not operator or paren: push double value.
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
% java Evaluate
( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
101.0
% java Evaluate
( ( 1 + sqrt ( 5.0 ) ) / 2.0 )
1.618033988749895

//Fixed-capacity stack
//An abstract data type for a fixed-capacity stack of strings
//API
//public class FixedCapacityStackOfStrings
              FixedCapacityStackOfString(int cap)     //create an empty stack of capacity cap
     void push(String item)                                      //add a string
  String pop()                                                            //remove the most recently added string
boolean isEmpty()                                                   //is the stack empty?
        int size()                                                            //number of strings on the stack
//test client
public static void main(String[] args)
{
    FixedCapacityStackOfStrings s;
    s = new FixedCapacityStackOfStrings(100);
    while (!StdIn.isEmpty())
    {
        String item = StdIn.readString();
        if (!item.equals("-"))
              s.push(item);
        else if (!s.isEmpty())    StdOut.print(s.pop() + " ");
    }
    StdOut.println("(" + s.size() + " left on stack)");
}
//application
% more tobe.txt
to be or not to - be - - that - - - is
% java FixedCapacityStackOfStrings < tobe.txt
to be not that or be (2 left on stack)
//implementation
public class FixedCapacityStackOfStrings
{
    private String[] a;   //stack entries
    private int N;     //size
    public FixedCapacityStackOfStrings(int cap)
    {  a = new String[cap];    }
    public boolean isEmpty()      {   return N ==0;     }
    public int size()                       {   return N;       }
    public void push(String item)
    {  a[N++] = item;      }
    public String pop()
    {  return a[--N];      }
}

//An abstract data type for a fixed-capacity generic stack
//API
//public class FixedCapacityStack<Item>
              FixedCapacityStack(int cap)     //create an empty stack of capacity cap
     void push(Item item)                         //add a string
      Item pop()                                           //remove the most recently added item
boolean isEmpty()                                   //is the stack empty?
        int size()                                            //number of items on the stack
//test client
public static void main(String[] args)
{
    FixedCapacityStack<String> s;
    s = new FixedCapacityStack<String>(100);
    while (!StdIn.isEmpty())
    {
        String item = StdIn.readString();
        if (!item.equals("-")
               s.push(item);
        else if (!s.isEmpty())    StdOut.print(s.pop() + " ");
    }
    StdOut.println("(" + s.size() + " left on stack)");
}
//application
% more tobe.txt
to be or not to - be - - that - - - is
% java FixedCapacityStack < tobe.txt
to be not that or be (2 left on stack)
//implementation
public class FixedCapacityStack<Item>
{
    private Item[] a;   //stack entries
    private int N;     //size
    public FixedCapacityStack(int cap)
    {  a =  (Item[]) new Object[cap];    }
    public boolean isEmpty()      {   return N ==0;     }
    public int size()                       {   return N;       }
    public void push(Item item)
    {  a[N++] = item;      }
    public Item pop()
    {  return a[--N];      }
}

//Array resizing
//moves a stack into an array of a different size
private void resize(int max)
{     //Move stack of size N <= max to a new array of size max.
    Item[] temp = (Item[]) new Object[max];
    for (int i = 0; i < N; i++)
       temp[i] = a[i];
    a = temp;
}
//double the size of the array, tehn insert the new item
public void push(String item)
{     //Add item to top of stack
    if ( N == a.length)      resize(2*a.length);
    a[N++] = item;
}
//the stack never overflows and never becomes less than one quarter full
public String pop()
{     //Remove item from top of stack.
    String item = a[--N];
    a[N] = null;     //Avoid loitering.
    if (N > 2 && N == a.length/4)   resize(a.length/2);
    return item;
}

//Loitering

//Iteration
//print all of the items in a collection of strings, one per line
Stack<String> collection = new Stack<String>();
...
for (String s: collection)
      StdOut.println(s);
...
//equivalent to the while statement
Iterator<String> i = collection.iterator();
while (i.hasNext())
{
    String s = i.next();
    StdOut.println(s);
}
/*To make a class iteravle, the first step is to add the phrase implements
Iterable<Item> to its declaration, matching the interface, and to add a
method iterator() to the class that returns an Iterable<Item>. */
public interface Iterable<Item>
{
    Iterable<Item> iterator();
}   //in java.lang.Iterable
//we need to iterate through an array in reverse order
public Iterator<Item> iterator()
{     return new ReverseArrayIterator();      }
//What is an iterator?
public interface Iterator<Item>
{
    boolean hasNext();
    Item next();
    void remove();
}
//For ReverseArrayIterator, implemented in a nested class
private class ReverseArrayIterator implements Iterator<Item>
{
    private int i = N;
    
    public boolean hasNext() {      return i > 0;     }
    public Item next()              {      return a[--i];    }
    public void remove()         {                                }
}
//One crucial detail remains: we have to include
import java.util.Iterator;      //at the beginning of the program

import javax.lang.model.util.ElementScanner6;

//Algorithm 1.1 Puchdown (LIFO) stack (resizing array implementation)
import java.util.Iterator;
public class ResizingArrayStack<Item> implements Iterable<Item>
{
    private Item[] a= (Item[]) new Object[1];    //stack items
    private int N = 0;       //number of items
    
    public boolean isEmpty()  {      return N == 0;       }
    public int size()                    {      reuturn N;             }
    
    private void resize(int max)
    {   //Move stack to a new array of size man/
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
              temp[i] = a[i];
         a = temp;
    }
    
    public void push(Item item)
    {   //Add item to top of stack.
        if (N == a.length)  resize(2*a.length);
        a[N++] = item;
    }
    
    public Item pop()
    {   //Remove item from top of stack.
        Item item = a[--N];
        a[N] = null;     //Avoid loitering.
        if (N > 0 && N == a.length/4)    resize(a.length/2);
        return item;
    }
    
    public Iterator<Item> iterator()
    {  return new ReverseArrayIterator();     }
    
    private class ReverseArrayIterator implements Iterator<Item>
    {  //Support LIFO iteration.
        private int i = N;
        public boolean hasNext()   {  return i > 0;   }
        public       Item next()          {  return a[--i];  }
        public       void remove()     {                          }
    }
}

//Linked lists
/* Definition: A linked list is a recursive data structure that is either empty (null)
or a reference that a node having a generic item and a reference to a linked list. */
//Node record
//a nested class that defines that node abstraction
private class Node
{
    Item item;      //a parameterized type
    Node next;
}

//Building a linked list
//a linked list that contains the items to, be, and or
Node first = new Node();
Node second = new Node();
Node third = new Node();
//set the item field in each of the nodes to the desired value
first.item = "to";
second.item = "be";
third.item = "or";
//set the next fields to build the linked list
first.next = second;
second.next = third;
//using an array
String[] s = {  "to", "be", "or" };

//Insert at the beginning
//Insert a new node at the beginning of a linked list
//save a link to the first
Node oldfirst = first;
//create a new node for hte beginning
first =  new Node();
//set the instance variables in the new node
first.item = "not";
first.next = oldfirst;

//Remove from the beginning
//Removing the first node in a linked list
first = first.next;

//Insert at the end
//Inserting a new node at the end of a linked list
//save a link to the last node
Node oldlast = last;
//create a new node for the end
Node last = new Node();
last.item = "not";
//link the new node the the end of the list
oldlast.next = last;

//insert/remove at other positions
//to use a doubly-linked list

//Traversal
for (Node x = first; x != null; x = x.next)
{
    //Process x.item.
}

//Stack implementation
//Test client for Stack
public static void main(String[] args)
{      //Create a stack and push/pop strings as directed on StdIn.
    Stack<String> s = new Stack<String>();
    while (!StdIn.isEmpty())
    {
        String item = StdIn.readString();
        if (!item.equals("-"))
              s.push(item);
        else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
    }
    StdOut.println("(" + s.size() + " left on stack)");
}

//Algorithm 1.2 Pushdown stack (linked-list implementation)
public class Stack<Item> implements Iterable<Item>
{
    private Node first;    //top of stack (most recently added node)
    private int N;      //number of items
    
    private class Node
    {   //nested class to define nodes
        Item item;
        Node next;
    }
    
    public boolean isEmpty()  {      return first == null;  }  //or: N == 0
    public int size()                   {      reutrn N;  }
    
    public void push(Item item)
    {   //Add item to top of stack
        Node oldfirst = first;
        first  = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    public Item pop()
    {   //Remove item from top of stack
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
}
% more tobe.txt
to be or not to - be - - that - - - is
% java Stack < tobe.txt
to be not that or be (2 left on stack)

//Queue implementation
//Test client for Queue
public static void main (String[] args)
{      //Create a queue and enqueue/dequeue strings.
    Queue<String> q = new Queue<String>();
    while (!StdIn.isEmpty())
    {
        String item = StdIn.readString();
        if (!item.equals("-"))
              q.enqueue(item);
        else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
    }
    StdOut.println("(" + q.size() + " left on queue)");
}
% more tobe.txt
to be or not to - be - - that - - - is
% java Queue < tobe.txt
to be not that or be (2 left on queue)

//Algorithm 1.3 FIFO queue
public class Queue<Item>  implements Iterable<Item>
{
    private Node first;     //link to least recently added node
    private Node last;      //link to most recently added node
    private int N;              //number of items on the queue
    
    private class Node
    {   //nested class to define nodes
        Item item;
        Node next;
    }
    
    public boolean isEmpty()  {      return first == null;     }   //or: N == 0.
    public int size()                   {      return N;  }
    
    public void enqueue(Item item)
    {   //Add item to the end of hte list
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else                 oldlast.next = last;
        N++;
    }
    
    public Item dequeue()
    {   //Remove item from the beginning of the list.
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
}

//Bag implementation
//the first step is to include
import java.util.Iterator;
//the second step is to add
implements Iterable<Item>
//then
public Iterator<Item> iterator()
{     return new ListIterator();      }

//Algorithm 1.4 Bag
import java.util.Iterator;
public class Bag<Item> implements Iterable<Item>
{
    private Node first;     //first node in list
    
    private class Node
    {
        Item item;
        Node next;
    }
    
    public void add(Item item)
    {   //same as push() in Stack
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public Iterator<Item> iterator()
    {  return new ListIterator();      }
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext()
        {      return current != null;   }
        public void remove()    {}
        public Item next()
        {
            Item item = current.item;
            current =  current.next;
            return item;
        }
    }

//1.4 Analysis of algorithms
//Given N, how long will this program take?
public class ThreeSum
{
    public static int count(int[] a)
    {   //Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                         cnt++;
        return cnt;
    }
    public static void main(String[] args)
    {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}
% java ThreeSum 1000 1Kints.txt
% java Threesum 2000 2Kints.txt
% java ThreeSum 4000 4Kints.txt
% more 1Mints.txt

//Stopwatch
//An abstract data type for a stopwatch
//API
//public class Stopwatch
               Stopwatch()                                     //create a stopwatch
 double elapsedTime()                                   //return elapsed time since creation
 //typical client
 public static void main(String[] args)
 {
     int N = Integer.parseInt(arg[0]);
     int[] a = new int[N];
     for (int i = 0; i < N; i++)
        a[i] = StdRandom.uniform(-1000000, 1000000);
     Stopwatch timer =  new Stopwatch();
     int cnt = ThreeSum.count(a);
     double time = timer.elapsedTime();
     StdOut.println(cnt + " triples " + time);
 }
 //application
 % java Stopwatch 1000
 51 triples 0.488 seconds
 % java Stopwatch 2000
 516 triples 3.855 seconds
 //implementation
 public class Stopwatch
 {
     private final long start;
     public Stopwatch()
     {      start = System.currentTimeMillis();     }
     public double elapsedTime()
     {
         long now = System.currentTimeMillis();
         return (now - start) / 1000.0;
     }
 }
 //Analysis of experimential data
 //the running time of ThreeSum.count()
 //program to perform experiments
 public class DoublingTest
 {
     public static double timeTrial(int N)
     {  //Time ThreeSum.count() for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int [N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAx);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        reutrn timer.elapsedTime;
     }
     public static void main(String[] args)
     {      //Print table of running times.
         for (int N =250; trur; N += N)
         {  //Print time for problem size N.
             double time = timeTrial(N);
             StdOut.printf("%7d %5.1f\n", N, time);
         }
     }
 }
 //results experiments
 % java DoublingTest
  250 0.0
  500 0.0
 1000 0.1
 2000 0.8
 4000 6.4
 8000 51.1
 ...

//Design faster algorithms
//Linearithmic solution to the 2-sum problem
import java.util.Arrays;
public class TwoSumFast
{   //Count pairs that sum to 0.
    public static int count(int[] a)
    {
        Array.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            if (BinarySearch.rank(-a[i], a) >i)
                cnt++;
        return cnt;
    }
    public staitc void main (String[] args)
    {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}

//Fast algorithm for 3-sum
//N^2 lg N solution to the 3-sum problem
import java.util.Arrays;
public class ThreeSumFast
{
    public static int count(int[] a)
    {   //Count triples that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                if (BinarySearch.rank(-a[i]-a[j], a) > j)
                    cnt++;
        return cnt++;
    }
    public staitc void main(String[] args)
    {
        int[] a = In.readInts(args[0]);
        StdInt.println(count(a));
    }
}

//Doubling ratio experiments
//program to perform experiments
public class DoublingRatio
{
    public static double timeTrial(int N)
    //same as for DoublingTest
    public static void main(String[] args)
    {
        double prev = timeTrial(125);
        for (int N = 250; true; N += N)
        {
            double time = timeTrial(N);
            StdOut.printf("%6d %7.1f", N, time);
            StdOut.printf("%5.1f\n", time/prev);
            prev = time;
        }
    }
}
//result of experiments
% java DoublingRatio
 250 0.0 2.7
 500 0.0 4.8
1000 0.1 6.9
2000 0.8 7.7
4000 6.4 8.0
8000 51.1 8.0
//predictiosn
16000   408.8 8.0
32000  3270.4 8.0
64000 26163.2 8.0

//1.5 CASE study: Uniom-find
//Union-find API
//public class UF
        UF(int N)                   //initialize N sites with interger names (0 to N-1)
   void union(int p, int q)         //add connection between p and q
    int find(int p)                 //component identifier for p (0 to N-1)
boolean connected(int p, int q)     //return true if p and q are in the same component
    int count()                     //number of components

//Algorithm 1.5 Union-find implementation
public class UF
{
    private int[] id;   //access to component id (site indexed)
    private int count;  //number of components
    public UF(int N)
    {   //Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    public int count()
    {   return count;   }
    public boolean connected (int p, int q)
    {   return find(p) == find(q);  }
    public int find(int p)
    public void union(int p, int q)
    public static void main (String[] args)
    {   //Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();    //Read number of sites.
        UF uf = new UF(N);  //Initialize N components.
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();    //Read pair to connect.
            if (uf.connected(p, q)) continue;   //Ignore if connected.
            uf.union(p, q); //Combine components
            StdOut.println(p + " " + q);    //and print connection.
        }
        StdOut.println(uf.count() + " components");
    }   
}
% java UF < tinyUF.txt
4 3
3 8
6 5
9 4
2 1
5 0
7 2
6 1
2 components

//Implementations
//Quick-find
public int find(int p)
{   reutrn id[p];   }
public void union(int p, int q)
{   //Put p and q into the same component.
    int pID = find(P);
    int qID = find(q);
    //Nothing to do if p and q are already in the same component.
    if (pID == qID) return;
    //Rename p's component to q's name.
    for (int i = 0; i < id.length; i++)
        if (id[i] == pID)   id[i] = qID;
    count--;
}
//Quick-union
private int find(int p)
{   //Find component name.
    while (p != id[p])  p = id[p];
    return p;
}
public void union(int p, int q)
{   //Give p and q the same root.
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) return;
    id[pRoot] = qRoot;
    count--;
}
//Weighted quick-union
//Algorithm 1.5 (continued) Union-find implementation (weighted quick-union)
public class WeightedQuickUnionUF
{
    private int[] id;   //parent link (site indexed)
    private int[] sz;   //size of component for roots(site indexed)
    private int count;  //number of components
    public WeightedQuickUnionUF(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }
    public int count()
    {   return coujnt;  }
    public boolean connected(int p, int q)
    {   return find(p) == find(q);  }
    private int find(int p)
    {   //Follow links to find a root.
        whiel (p != id[p])  p = id[p];
        return p;
    }
    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        //Make smaller root point to larger one.
        if (sz[i] M < sz[j])    {   id[i] = j; sz[j] += sz[i];  }
        else                    {   id[j] = i; sz[i] += sz[j];  }
        count--;
    }
}


//TWO Sorting
//Template for sort classes
public class Example
{
    public static void sort(Comparable[] a)
    {   /* See Algorithms 2.1, 2.2, 2.3, 2.4, 2.5, or 2.7. */   }
    private static boolean less(Comparable v, comparable w)
    {   return v.compareTo(w) <0;   } 
    private static void exch(Comparable[] a, int i, int j)
    {   Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
    private static void show(Comparable[] a)
    {   //Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a)
    {   //Test whether the array entries are in order.
        for (int i = 0; i < a.length; i++)
            if (less(a[i], a[i+1])) return false;
        return true;
    }
    public static void main(String[] args)
    {   //Read strings from standard input, sort them, and print.
        String[] a = In.readString();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
5 more tiny.txt
S O R T E X A M P L E
% java Example < tiny.txt
A E E L M O P R S T X
% more word3.txt
bed bug dad yes zoo ... all bad yet
% java Example < words.txt
all bad bed bug dad ... yes yet zoo

//Sorting an array of random values
Double a[] = new Double[N];
for (int i = 0; i < N; i++)
    a[i] = StdRandom.uniform();
Quick.sort(a);

//Defining a comp;arable type
public class Date implements Comparable<Date>
{
    private final int day;
    private final int month;
    private final int year;
    public Date(int d, int m, int y)
    {   day = d; month = m; year = y;   }
    public int day()    {   return day; }
    public int month()  {   return month;   }
    public int year()   {   return year;    }
    public int compareTo(Date that)
    {
        if (this.year > that.year)  return +1;
        if (this.year < that.year)  return -1;
        if (this.month>that.month)  return +1;
        if (this.month<that.month)  return -1;
        if (this.day  > that.day)   return +1;
        if (this.day  < that.day)   return -1;
        return 0;
    }
    public String toString()
    {   return month + "/" + day + "/" + year; }
}

//Algorithm 2.1 Selection sort
public class Selection
{
    public static void sort(Comparable[] a)
    {   //Sort a[] into increasing order.
        int N = a.length;   //array length
        for (int i = 0; i < N; i++)
        {   //Exchange a[i] with smallest entry in a[i+1...N]
            int min = i;   //index of minimal entr.
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
    }
}

//Algorithm 2.2 Insertion sort
public class Insertion
{
    public static void sort(Cpmparable[] a)
    {   //Sort a[] into incresing order.
        int N = a.length;
        for (int i =1; i < N; i++)
        {
            for (int j = i; j > 0 && less (a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
}

//Visualizing sorting algorithms

//Comparing two sorting algoeithms
//Timing one of the sort algorithms in this chapter on a given input
public static double time(String alg, Cpmparable[] a)
{
    Stopwatch timer = new Stopwatch();
    if (alg.equals("Insertion"))    Insertion.sort(a);
    if (alg.equals("Selection"))    Selection.sort(a);
    if (alg.equals("Shell"))        Shell.sort(a);
    if (alg.euqlas("Merge"))        Merge.sort(a);
    if (alg.equals("Quick"))        Quick.sort(a);
    if (alg.equals("Heap"))         Heap.sort(a);
    return timer.elapsedTime();
}

//Comparing two sorting algorithms
public class SortCompare
{
    public static double time(String alg, Double[] a)
    {   /*see text.*/   }
    public static double timeRandomInput(String alg, int N, int T)
    {   //Use alg to sort T random arrays of length N.
        double total = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; t < T; t++)
        {   //Perform one experiment (generate and sort an array)
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
                total += time(alg, a);
        }
        return total;
    }
    public static void main(String[] args)
    {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);    //total for alg1
        double t2 = timeRandomInput(alg2, N, T);    //total for alg2
        StdOut.printf("For %d random Doubles\n %s is ", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}
% java SortCompare Insertion Selection 1000 100
For 1000 andom Doubles 
    Insertion is 1.7 times faster than Selection

//Algorithm 2.3 Shellsort
public
{
    public static void sort(Comparable[] a)
    {   //Sort a[] into increasing order.
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1;    //1,4,13,40,121,364,1093...
        while(h >= 1)
        {   //h-sort the array.
            for (int i = h; i < N; i++)
            {   //Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]...
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3;
        }
    }
}
% java SortCompare Shell Insertion 100000 100
For 100000 random Doubles
    Shell is 600 times faster than Insertion.

//Mergesort
//Abstract-place merge
public static void merge(Comparable[] a, int lo, int mid, int hi)
{   //Merge a[lo..mid] with a a[mid..hi].
    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; k++)  //Copy a[lo..hi] to aux[lo..hi].
        auc[k] = a[k];
    for (int k = lo; h <= hi; k++)  //Merge back to a[lo..hi].
        if      (i > mid)               a[k] = aux[j++];
        else if (j > hi)                a[k] = aux[i++];
        else if (less(aux[j], aux[i]))  a[k] = aux[j++];
        else                            a[k] = aux[i++];
}

//Algorithm 2.4 Top-down mergesort
public class Merge
{
    private static Comparable[] aux;    //auxiliary array for merges
    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length]; //Allocate space just once.
        sort(a, o, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {   //Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);   //Sort left half.
        sort(a, mid + 1; hi);   //Sort right half.
        merge(a, lo, mid, hi);  //Merge results.
    }
}

//Algorithm 2.5 Bottom-up mergesort
public class MergeBU
{
    private staitc Comparable[] aux;    //auxiliary array for merges
    //See the merge() code
    public static void sort(Comparable[] a)
    {   //Do lg N passes of pairwise merges.
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz +sz)   //sz: subarray size
            for (int lo = 0; lo < N - sz; lo += sz+sz)  //lo: subarray index
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }
}

//Algorithm 2.5 Quicksort
public class Quick
{
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);   //Eliminate dependence on input
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)   return;
        int j = partition(a, lo, hi);   //Partition.
        sort(a, lo, j-1);   //Sort left part a[lo..j-1].
        sort(a, j+1, hi);   //Sort right part a[j+1..hi].
    }
}
//Quicksort partitioning
//璁惧畾涓�涓獀鍊硷紝鍏堜粠宸﹁竟杩涜鎵弿锛屽鏋滄瘮v澶у垯鍋滄鎵弿锛涘啀浠庡彸杈硅繘琛屾壂鎻�
//濡傛灉姣攙灏忓垯鍋滄鎵弿锛涗氦鎹㈠仠姝㈡壂鎻忕殑鍊硷紱鍙嶅杩涜姝ゆ楠わ紝鐭ラ亾鎵弿鍒板悓涓�涓�兼椂鍋滄锛�
//鏈�鍚庡皢v鍊间笌鏈�鍚庢壂鎻忓埌鐨勫�间氦鎹�
//灏嗘瘮v灏忕殑鏀惧埌宸﹁竟锛屾瘮v澶х殑鏀惧埌鍙宠竟
private static int partition(Comparable[] a, int lo, int hi)
{   //Partition into a[lo..i-1], a[i], a[i+1..hi].
    int i = lo, j = hi+1;   //left and right scan indices
    Comparable v = a[lo];   //partitioning item
    while (true)
    {   //Scan right, scna left, check for scan complete, and exchange.
        while (less(a[++i], v)) if (i == hi) break;
        while (less(v, a[--j])) if (j == lo) break;
        if (i >= j) break;
        exch(a, i, j);
    }
    exch(a, lo, j); //Put v = a[j] into position.
    return j;   //with a[lo..j-1] <= a[j] <= a[j+1..hi].
}
//Quicksort with 3-way partitioning
public class Quick3way
{
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        in lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt)
        {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0)   exch(a, lt++, i++);
            else if (cmp > 0)   exch(a, i, gt--);
            else                i++;
        }   //New a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}

//2.4 Priority queues
//API for a generic priority queue
public class MaxPQ<Key extends Comparable<Key>>
        MaxPQ() //create a priority queue
        MaxPQ(int max)  //create a priority queue of initial capacity max
        MaxPQ(Key[] a)  //create a priority queue from the keys in a[]
   void insert(Key v)   //insert a key into the priority queue
    Key max()   //return the largest key
    Key delMax()    //return and remove the largest key
boolean isEmpty()   //is the priority queue empty?
    int size()  //number of keys in hte priority queue

//A priority-queue client
public class TopM
{
    public static void main(String[] args)
    {   //Print the top M lines in the input stream.
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
        while (StdIn.hasNextLine())
        {   //Create an entry from the next line and put on the PQ.
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M)
                pq.delMin();    //Remove minimum if M+1 entries on the PQ.
        }   //Top M entries are on the PQ.
        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t : stack) StdOut.println(t);
    }
}
% Java topM 5 < tinyBatch.txt
Thonpson    2/27/2000   4747.08
vonNeumann  2/12/1994   4732.35
vonNeumann  1/11/1999   4409.74
Hoare       8/18/1992   4381.21
vonNeumann  3/26/2002   4121.85

//Heap definitions
//Algorithms on heaps
//Compare and exchange methods for heap implementations
private boolean less(int i, int j)
{   return pq[i].compareTo(pq[j] < 0;   )}
private void exch(int i, int j)
{   Key t = pq[i]; pq[i] = pq[j]; pq[j] =t; }

Bottom-up reheapify (swim)
private void swim(int k)
{
    while (k > 1 && less(k/2, k))
    {
        exch(k/2, k);
        k = k/2;
    }
}
Top-down reheapify (sink)
private void sink(int k)
{
    while (2*k <= N) //N, the total number of the tree
    {
        int j = 2*k;
        if (j < N && less(j, j+1)) j++;
        if (!less(k, j)) break;
        exch(k, j);
        k = j;
    }
}

Algoroth 2.6 Heap priority queue
public class MaxPQ<Key extends Comparable<Key>>
{
    private Key[] pq;   //heap-ordered complete binary tree
    private int N = 0;  //in pq[1..N] with pq[0] unused
    public MaxPQ(int maxN)
    {   pq = (Key[]) new Comparable[maxN+1]}
    public boolean isEmpty()
    {   reutrn N == 0;  }
    public int size()
    {   return N;   }
    public void insert(Key v) //insert a new element
    {
        pq[++N] = v;
        swim(N);
    }
    public Key delMax()
    {
        Key max = pq[1];    //retrieve max key form top.
        exch(1, N--);   //exchange with last item.
        //how to remove the node from heap, previously we have inserted a new element
        pq[N+1] = null; //avoid loitering.
        sink(1);    //restore heap property.
        return max;
    }
    private boolean less(int i, int j)
    private void exch(int i, int j)
    private void swim(int k)
    private void sink(int k)
}

API for a generic priority queue with associated indices
public class IndexMinPQ<Item extends Comparable<Item>>
        IndexMinPQ(int maxN)    create a priority queue of capacity maxN
                                with possible indices between 1 and maxN-1
   void insert(int k, Item item)    insert item; associate it with k
   void change(int k, Item item)    change the item associated with k to item
boolean contains(int k)             is k associated with some item?
   void delete(int k)               remove k and its associated item
   Item min()                       return a minimal item
    int minIndex()                  return a minimal item's index
    int delMin()                    remove a minimal item and return its index
boolean isEmpty()                   is the priority queue empty?
    int size()                      number of items in the priority queue

Index priority-queue
Multi merge priority-queue client
public class Multiway
{
    public static void merge(In[] streams)
    {
        int N = stream.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
        for (int i = 0; i < N; i++)
            if (!stream[i].isEmpty())
                pq.insert(i, streams[i].readString());
        while (!pq.isEmpty())
        {
            StdOut.println(pq.min());
            int i = pq.delMin();
            if (!streams[i].isEmpty())
                pq.insert(i, stream[i].readString());
        }
    }
    public static void main(String[] args)
    {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++)
            stream[i] = new In(args[i]);
        merge(streams);
    }
}

Algorithm 2.7 Heapsort
public static void sort(Comparable[] a)
{
    int N = a.length;
    for (int k = N/2; k >= 1; k--)
        sink(a, k, N);
    while (N > 1)
    {
        exch(a, 1, N--);
        sink(a, 1, N);
    }
}

2.5 applications
Alternate compareTo() implementation for sorting transactions by date
public int compareTo(Transaction tha)
{   return this.when.compareTo(that.when);  }

Insertion sorting with a Comparator
public static void sort(Object[] a, Comparator c)
{
    int N = a.length;
    for (int i = 1; i < N; i++)
        for (int j = i; j > 0; && less(c, a[j], a[j-1]); j--)
            exch(a, j, j-1);
}
private static boolean less(Comparator c, Object v, Object w)
{   return c.compare(v, w) < 0; }
private static void exch(Object[] a, int i, int j)
{   Object t = a[i]; a[i] = a[j]; a[j] = t; }

Insertion sorting with a Comparator
import java.util.Comparator;
public class Transaction
{
    ...
    private final String who;
    private final Date when;
    private final double amount;
    ...
    public static class WhoOrder implements Comparator<Transaction>
    {
        public int compare(Transaction v, Transaction w)
        {   return v.who.compareTo(w.when);     }
    }
    public static class WhenOrder implements Comparator<Transaction>
    {
        public int compare(Transaction v, Transaction w)
        {   return v.when.compareTo(w.when);    }
    }
    public static class HowMuchOrder implements Comparator<Transaction>
    {
        public int compare(Transaction v, Transaction w)
        {
            if (v.amount < w.amount) return -1;
            if (v.amount > w.amount) return +1;
            return 0;
        }
    }
}

Selecting the k smallest elements in a[]
public static Comparable
select(Comparable[] a, int k)
{
    StdRandom.shuffle(a);
    int lo = 0, hi = a.length - 1;
    while (hi > lo)
    {
        int j = partition(a, lo, hi);
        if      (j == k)    return a[k];
        else if (j > k)     hi = j - 1;
        else if (j < k)     lo = j + 1;
    }
    return a[k];
}

3 Searching
3.1 Symbol Tables
//API for a generic basic symbol table
public class ST<Key, Value>
             ST()               //create a symbol table
        void put(Key key, Value val) //put key-value pair into the table
                                    //(remove key from table if value is null)
       Value get(Key key)       //value paired with key(null if key is absent)
        void delete(Key key)    //remove key (and its value) from table
     boolean contains(Key key)  //is there a value paired with key?
     boolean isEmpty()          //is the table empty?
         int size()             //number of key-value pairs in the table
Iterable<Key> keys()            //all the keys in the table

//default implementations
        method      default implementation
    void delete(Key key)    put(key, null);
boolean contains(key)       return get(key) != null;
boolean isEmpty()           return size() == 0;

//API for a  generic ordered symbol table
public class ST<Key extends Comparable<Key>, Value>
              ST()               //create a symbol table
         void put(Key key, Value val) //put key-value pair into the table
                                        //(remove key from table if value is null)
        Value get(Key key)       //value paired with key(null if key is absent)
         void delete(Key key)    //remove key (and its value) from table
      boolean contains(Key key)  //is there a value paired with key?
      boolean isEmpty()          //is the table empty?
          int size()             //number of key-value pairs in the table
          Key min()              //smallest key
          Key max()              //largest key
          Key floor(Key key)     //largest key less than or equal to key
          Key ceiling (Key key)  //smallest key greater than or eueal to key
          int rank(Key key)      //number of keys less than key
          Key select(int k)      //key of rank k
         void deleteMin()        //delete smallest key
         void deleteMax()        //delete largest key
          int size(Key lo, Key hi) //number of keys in [lo..hi]
Iterable<Key> keys(Key lo, Key hi) //heys in [lo..hi], in sorted order
Iterable<Key> keys()             //all the keys in the table

//Default implementations of redundant order-based symbol-table mehtods
    methods                             default implementation
         void deleteMin()           delete(min());
         void deleteMax()           delete(mac());
          int size(Key lo, Key hi)  if (hi.comparaTo(lo) < 0)
                                        return 0;
                                    else if (contains(hi))
                                        return rank(hi) - rank(lo) + 1;
                                    else
                                        return rang(hi) - rank(lo);
Iterable<Key> keys()                return keys(min(), max());

//Basic symbol-table test client
public static void main(String[] args)
{
    ST<String, Integer> st;
    st = new St<String, Integer>();
    for (int i = 0; !StdIn.isEmpty(); i++)
    {
        String key = StdIn.readString();
        st.put(key, i);
    }
    for (String s: st.keys())
        StdOut.println(s + " " + st.get(s));
}                              

//A symbol-table client
public class FrequencyCounter
{
    public static void main(String[] args)
    {
        int minlen = Integer.parseInt(arg[0]);  //key-length cutoff
        ST<String, Integer> st = new ST<String, Integer>();
        while (!StdIn.isEmpty())
        {   //Build symbol table and count frequencies.
            String word = StdIn.readString();
            if (word.length() < minlen) continue;   //Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
                                    st.put(word, st.get(word) + 1);
        }
        //Find a key with the highest frequency count.
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;
        StdOut.println(max + " " + st.get(max));
    }
}

//Algotithm 3.1 Sequential search (in an unordered linked list)
public class SequentialSearchST<Key, Value>
{
    private Node first;     //first node in the linked list
    private class Node
    {   //linked-list node
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    public Value get(Key key)
    {   //Search for key, return associated value.
        for (Node x = first; x != null; x = x.next)
            if (key.euqals(x.key))
                return x.val;   //search hit
        return null;    //search miss
    }
    public void put(Key key, Value val)
    {
        for (Node x = first; x != null; x = x.next)
            if (key.euqals(x.key))
            {   x.val = val; return;    }   //Search hit: update val.
        first = new Node(key, val, first);  //Search miss: add new node.
    }
}

//Algorithm 3.2 Binary search (in an ordered array)
//濡傛灉鐩稿悓鍙渶瑕佽鐩杤alue鐨勫�硷紝涓嶉渶瑕佹敼key
//濡傛灉涓嶅悓鍒欏皢鍚庨潰鐨剉alue鍜宬ey閮藉悜鍚庣Щ涓�浣嶏紝鍐嶅皢鏂皏alue鍜宬ey鎻掑叆
public class BinarySearchST<Key extends Comparable<Key>, value>
{
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity)
    {   //see algorithm 1.1 for standard array-resizing code.
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public int size()
    {   return N;   }
    public Value get(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if ( i < N && keys[i].compareTo(key) == 0)  return vals[i];
        else                                        return null;
    }
    public int rank(Key key)
    //see page 381
    public void put(Key key, Value val)
    {   //Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        {   vals[i] = val; return;  }
        for (int j = N; j > i; j--)
        {   keys[j] = keys[j-1]; vals[j] = vals[j-1];   }
        keys[i] = key; vals[i] = val;
        N++;
    }
    public void delete(Key key)
    //see exercise 3.1.16 for this method
}
//Binary search in an ordered array(iterative杩唬)
public  int rank(Key key)
{
    int lo = 0, hi = N-1;
    while (lo <= hi)
    {
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if      (cmp < 0)   hi = mid -1;
        else if (cmp > 0)   lo = mid +1;
        else return mid;
    }
    return lo;
}
//Ordered symbol-table operations for binary search
public Key min()
{   return keys[0]; }
public Key max()
{   return keys[N-1];   }
public Key select(int k)
{   return keys[k]; }
public Key ceiling(Key key)
{   int i = rank(key);
    return keys[i];
}
public Key floor(Key key)
//see exercise 3.1.17
public Key delete(Key key)
//see exercise 3.1.16
public Iterable<Key> keys(Key lo, Key hi)
{
    Queue<Key> q = new Queue<Key>();
    for (int i = rank(lo); i < rank(hi); i++)
    q.enqueue(keys[i]);
    if (contains(hi))
        q.enqueue(keys[rank(hi)]);
    return q;
}

//Binary search
//Recursive binary search閫掑綊
public int rank(Key key, int lo, int hi)
{
    if (hi < lo) return lo;
    int mid = lo + (hi - lo)/2;
    int cmp = key.compareTo(keys[mid]);
    if      (cmp < 0)
        return rank(key, lo, mid - 1);
    else if (cmp > 0)
        return rank(key, mid + 1, hi);
    else return mid;
}

3.2 Binary Search Trees
//Algorithm 3.3 Binary search tree symbol table
public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;      //root of BST
    private class Node;
    {
        private Key key;    //key
        private Value val;  //associated value
        private Node left, right;   //links to subtrees
        private int N;      //# nodes in subtree rooted here
        public Node(Key key, Value val, int N)
        {   this.key = key; this.val = val; this.N = N; }
    }
    public int size()
    {   return size(root);  }
    private int size(Node x)
    {
        if (x == null)  return 0;
        else            return x.N;
    }
    public Value get(Key key)
    //see page 399
    public void put(Key key, Value val)
    //see page 399
}
//search and insert for BSTs
public Value get(Key key)
{   return get(root, key)}
private Value get(Node x, Key key)
{   //Return value associated with key in the subtree rooted at x;
    //return null if key not present in subtree rooted at x.
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if      (cmp < 0) return get(x.left, key);
    else if (cmp > 0) return get(x.right, key);
    else return x.val;
}
public void put(Key key, Value val)
{   //Search for key. Update value if found; grow table if new.
    root = put(root, key, val);
}
private Node put(Node x, Key key, Value val)
{   //Change key's value to val if key in subtree rooted at x.
    //Otherwise, add new node to subtree associating key with val.
    if (x == null) return new Node(key, val,1);
    int cmp = key.compareTo(x.key);
    if      (cmp < 0) x.left  = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.val = val;
    x.N = size(x.left) + size(x.right) + 1;
    return x;
}
//Min, max, floor, and ceiling in BSTs
public Key min()
{
    return min(root).key;
}
private Node min(Node x)
{
    if (x.left == null) return x;
    return min(x.left);
}
public Key floor(Key key)
{
    Node x = floor(root, key);
    if (x == null) return null;
    return x.key;
}
private Node floor(Node x, Key key)
{
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp < 0)  return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else           return x;
}
//Selection and rank in BSTs
public Key select(int k)
{
    return select(root, k).key;
}
private Node select(Node x, int k)
{   //Return Node containing key of rank k.
    if (x == null) return null;
    int t = size(x.left);
    if      (t > k) return select(x.left k);
    else if (t < k) return select(x.right, k-t-1);
    else            return x;
}
public int rank(Key key)
{   return rank(key, root); }
private int rank(Key key, Node x)
{   //Return number of keys less than x.key in the subtree rooted at x.
    if (x == null)  return 0;
    int cmp = key.compareTo(x.key);
    if      (cmp < 0) return rank(key, x.left);
    else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
    else              return size(x.left);
}
//Deletion in BSTs
public void deleteMin()
{
    root = deleteMin(root);
}
private Node deleteMin(Node x)
{   //涓�鐩村悜宸︽悳绱紝鐩村埌x.left涓虹┖
    //鐒跺悗杩斿洖x.right锛屽嵆鏈�鍚庝竴娆￠�掑綊寰楀埌鐨刣eleteMin(x.left)鐨勫��
    //骞跺皢璇ュ�艰祴鍊间綔涓簒.left,鍙栦唬鍘熸潵鐨剎.Min
    if (x.left == null) return x.right;
    x.left = deleteMin(x.left); 
    x.N = size(x.left) + size(x.right) + 1;
    return x;
}
public void delete(Key key)
{   root = delete(root, key);   }
private Node delete(Node x, Key key)
{
    if (x == null) return null; //鎼滅储鍒版渶鍚庢病鏈夋壘鍒帮紝鍒欒繑鍥瀗ull锛岃〃鏄庢病鏈夎鍊�
    int cmp = key.compareTo(x.key);
    //浠庝袱涓柟鍚戝key鍊艰繘琛屾悳绱�
    if      (cmp < 0) x.left  = delete(x.left, key);    //寰�宸︽悳绱ey鍊�
    else if (cmp > 0) x.right = delete(x.right, key);   //寰�鍙虫悳绱ey鍊�
    else    //姝ゆ椂x.key = key锛屾壘鍒拌鍊�
    {   //鍗曚晶涓虹┖鏃讹紝鐩存帴杩斿洖鍙︿竴渚х殑鑺傜偣浣滀负x??? 鏄惁鏄繖鏍�
        if (x.right = null) return x.left;
        if (x.left == null) return x.right;
        Node t = x;
        x = min(t.right);   //灏唗.right鐨勬渶灏忓�艰祴缁檟
        x.right = deleteMin(t.right);   //鍒犻櫎t.right鐨勬渶灏忓�硷紝鍗崇幇鍦ㄧ殑x
        x.left = t.left;
    }
    x.N = size(x.left) + size(x.right) + 1;
    return x;
}
//Range searching in BSTs
public Iterable<Key> keys()
{   return keys(min(min(), max());  }
public Iterable<Key> keys(Key lo, Key hi)
{
    Queue<Key> queue = new Queue<Key>();
    keya(root, queue, lo, hi);
    return queue;
}
private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
{
    if (x == null) return;
    int cmplo = lo.compareTo(x.key);
    int cmphi = hi.compareTo(x.key);
    if (cmplo < 0) keys(x.left, queue, lo, hi);
    if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
    if (cmphi > 0) keys(x.right, queue, lo, hi);
}

//Range queries
//Printing the keys in a BST in order
private void print(Node x)
{
    if (x == null) return;
    print (x.left);
    StdOut.println(x.key);
    print(x.right);
}


3.3 Balanced search trees
2-3search trees
Red-black BSTs
//Node representation for red-black BSTs
private static final boolean RED   =True;
private static final boolean BLACK = false;
private class Node
{
    Key key;    //key
    Value val;  //associated data
    Node left, right;   //subtrees
    int N;      //# nodes in this subtree
    boolean color;  //color of link from parent to this node
    Node(Key key, Value val, int N, boolean color)
    {
        this.key   = key;
        this.val   = val;
        this.N     = N;
        this.color = color;
    }
}
private boolean isRed(Node x)
{
    if (x == null) return false;
    return x.color == RED;
}
//Left rotate(right link of h)
Node rotateLeft(Node h)
{
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = h.color;
    h.color = RED;
    x.N = h.N;
    h.N = 1 + size(h.left) + size(h.right);
    return x;
}
//Right rotate(left link of h)
Node rotateRight(Node h)
{
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = h.color;
    h.color = RED;
    x.N = h.N;
    h.N = 1 + size(h.left) + size(h.right);
    return x;
}
//Flipping colors to split a 4-node
void flipColors(Node h)
{
    h.color = RED;
    h.left.color = BLACK;
    h.right.color = BLACK;
}

//Algorithm 3.4 Insert for red-black BSTs
public class RedBlackBST<Key extends Comparable<Key>, Value>
{
    private Node root;
    private class Node //BST node with color bit
    private boolean isRes(Node h)
    private Node rotateLeft(Node h)
    private Node rotateRight(Node h)
    private void flipColors(Node h)
    private int size()
    public void put(Key key, Value val)
    {   //Search for key. Update value if found; grow table if new.
        root = put(root ,key, val);
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val)
    {
        if (h == null)  //Do standard insert, with red link to parent.
            return new Node(key, val, a, RED);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColor(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
}

//3.4 Hash tables
//Hashing a string key
int hash = 0;
for (int i = 0; i < s.length(); i++)
    hash = (R * hash + s.charAt(i)) % M;
//Implementing hashCode() in a user-defined type
public class Transaciton
{
    ...
    private final String who;
    private final Date when;
    private final double amount;
    public int hashCode()
    {
        int hash = 17;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }
    ...
}

//Algorithm 3.5 Hashing with separate chaining
public class SeparateChainingHashST<Key, value>
{
    private int N;  //number of key-value pairs
    private int M;  //hash table size 
    private SequentialSearchST<Key, Value>[] st;    //array of ST objects
    public SeparateChainingHashST()
    {   this(997);  }
    public SeparateChainingHashST(int M)
    {   //Create M linked lists.
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }
    private int hash(Key key)
    {   return (key.hashCode() & 0x7fffffff) % M;   }
    public Value get(Key key)
    {   return (Value) st[hash(key)].get(key);  }
    public void put(Key key, Value val)
    {   st[hash(key)].put(key, val);    }
    public Iterable<Key> keys()
}

//Algorithm 3.6 Hashing with linear probing
public class LinearProbingHashST<Key, Value>
{
    private int N;  //number of key-value pairs in the table
    private int M = 16; //size of linear-probing table
    private Key[] keys; //the keys
    private Value[] vals;   //the values
    public LinearProbingHashST()
    {
        keys = (Key[])   new Object[M];
        vals = (Value[]) new Object[M];
    }
    private int hash(Key key)
    {   return (key.hashCode() & 0x7fffffff) % M;   }
    private void resize();
    public void put(Key key, Value val)
    {
        if (N >= M/2) resize(2*M);  //double M
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))    {   vals[i] = val; return;  }
        keys[i] = key;
        vlas[i] = val;
    }
    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }
}

//Deletion for linear probing
public void delte(Key key)
{
    if (!contains(key)) return;
    int i = hash(key);
    while (!key.equals(keys[i]))
        i = (i + 1) % M;
    keys[i] = null;
    vals[i] = null;
    i = (i + 1) % M;
    while (keys[i] != null)
    {
        Key keyToRedo = keys[i];
        Value valToRedo = vals[i];
        keys[i] = null;
        vals[i] = null;
        N--;
        put(keyToRedo, valToRedo);
        i = (i + 1) % M;
    }
    N--;
    if (N > 0 && N == M/8) resize(M/2);
}

//Resizing a linear-probing hash table
private void resize(int cap)
{
    LinearProbingHashST<Key, Value> t;
    t = new LinearProbingHashST<Key, Value>(cap);
    for (int i = 0; i < M; i++)
        if (keys[i] != null)
            t.put(keys[i], vals[i]);
    keys = t.keys;
    vals =t.vals;
    M    = t.M;
}

3.5 applications
//API for a basic set data type
public class SET<Key>
        SET()              create an empty set
   void add(Key key)       add key into the set
   void delete(Key key)    remove key from the set
boolean contains(Key key)  is key in the set?
boolean isEmpty()          is the set empty?
    int size()             number of keys in the set
 String toString()         string representation of the set

 //Dedup filter
 public class Dedup
 {
     public stataic void main(String[] args)
     {
         HashSET<String> set;
         set = new HashSET<String>();
         while (!StdIn.isEmpty())
         {
             String key = StdIn.readString();
             if (!set.contains(key))
             {
                 set.add(key);
                 StdOut.println(key);
             }
         }
     }
 }
 % java Dedup <tinyTale.txt
 it was the best of times worst
 age wisdom foolishness
 epoch belief incredulity
 season light daukness
 spring hope winter despair

 //Whitelist filter
 public class WhiteFilter
 {
     public static void main(String[] args)
     {
         HashSET<String> set;
         set = new HashSET<String>();
         In in = new In(args[0]);
         while (!in.isEmpty())
            set.add(in.readString());
        while (!StdIn.isEmpty())
        {
            String word = StdIn.readString();
            if (set.contains(word))
                StdOut.println(word);
        }
     }
 }
 % more list.txt
 was it the of
 % java WhiteFilter list.txt < tinyTale.txt
 it was the of it was the of
 it was the of it was the of
 it was the of it was the of
 it was the of it was the of
 it was the of it was the of
 % java BlackFilter list.txt < tinyTale.txt
 best times worst times
 age wisdom age foolishness
 epoch belief epoch incredulity
 season light season darkness
 spring hope winter despair

 //Dictionary lookup
 public class LookupCSV
 {
     public static void main(String[] args)
     {
         In in = new In(args[0]);
         int keyField = Integer.parseInt(arg[1]);
         int valField = Integer.parseInt(arg[2]);
         ST<String, String> st = new ST<String, String>();
         while (in.hasNextLing())
         {
             String line = in.readLine();
             String[] tokens = line.split(",");
             String key = tokens[keyField];
             String val = tokens[valField];
             st.put(key, val);
         }
         while (!StdIn.isEmpty())
         {
             String query = StdIn.readString();
             if (st.contains(query));
                StdOut.println(st.get(query));
         }
     }
 }
 % java LoojupCSV ip.csv 1 0

 //Index (and inverted index) lookup
 public class LookupIndex
 {
     public static void main(String[] args)
     {
         In in = new In(args[0]);   //index database
         String sp = args[1];   //separator
         ST<String, Queue<String>> st = new ST<String, Queue<String>>();
         ST<String, Queue<String>> ts = new ST<String, Queue<String>>();
         while (in.hashNextLine())
         {
             String[] a = in.readLine().split(sp);
             String key = a[0];
             for (int i = 1; i < a.length; i++)
             {
                 String val = a[i];
                 if (!st.contains(key)) st.put(key, new Queue<String>());
                 if (!ts.contains(val)) ts.put(val, new Queue<String>());
                 st.get(key).enqueue(val);
                 ts.get(val).enqueue(key);
             }
         }
         while (!StdIn.isEmpty())
         {
             String query = StdIn.readLine();
             if (st.contains(query))
                for (String s : st.get(query))
                    StdOut.println(" " + s);
            if (ts.contains(query))
                for (String s : ts.get(query))
                    StdOut.println(" " + s);
         }
     }
 }
 