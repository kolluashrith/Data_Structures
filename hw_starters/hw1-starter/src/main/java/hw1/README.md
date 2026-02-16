# Homework 1 Discussion

## Part I: Object-Oriented Programming Concepts

1. **Class**: A collection of related fields and methods. Block (defined in Block.java) is a class.

2. **Object**: An instance of a class, meaning an item constructed from a class definition. The 
flappyBox declared in Game.java is an object of the FlappyBox class.

3. **Encapsulation**: Refers to the consolidation of fields and methods into a unit like a class and 
their modification using visibility modifiers to restrict access and protect information. The use of
private fields to protect them and public getter and setter methods to view and access fields when 
necessary in the Block class is an example of this. 

4. **Abstraction**: Refers to the simplification of complex features by hiding unnecessary details 
about their inner workings, allowing focus to shift to how those features can be used. The Pipe class
is an example, since the class contains the implementation for the random generation of pipes on the
canvas. However, this is hidden away into the class, so simply creating an instance of the class with the 
appropriate parameters in Game.java is sufficient to randomly generate pipes in the game window.

5. **Data type**: An attribute of data containing information about how to interpret it and its memory
size. The score in Game.java is of type int, which is a type corresponding to integers.

6. **Composite data type**: These are just data types that are made up of other data types. The array of 
Boxes used as a field in the Pipe class in Pipe.java is an example of this since it is a list of Box data
types.

7. **Method**: A method is a block of code with a name that contains information about what tasks to do when
the method is called in a program using its name. The setUpCanvas method in Game.java is a block of code that 
contains information about how to set up the screen when that method is called in the game program.

8. **Constructor**: A constructor is a specific type of method that is called upon initialization of an object,
or instance, of a class. It initializes the object by declaring and assigning fields and by setting up the
object's initial state. The Game() method in Game.java is the constructor for the Game class. It sets up the
game canvas, game objects, initial score, and game state boolean.

9. **Instance variable**: These are variables that are declared in a class and exist as fields of that class.
Every object of a class will have their own individual instance variables, though the names are the same.
The integer score is an example of an instance variable of the Game class in Game.java since it is a field of
the class and is a variable in every Game object.

10. **Local variable**: These are variables that are declared in methods, constructors, or other code blocks 
instead of globally. They only exist for the lifetime of the function or code block call and are destroyed once
the execution of that function or block is complete. The collided boolean in Game.handleCollisions is a local
variable that only exists for the duration of the handleCollisions function, and is destroyed once the function
returns back to where it was called.

11. **Parameter**: These are arguments that need to be passed into a method when called. These are variables
containing the type of the data to be passed in for each field when the function is called. The names of
those variables can then be used by the function as needed to manipulate the passed-in data. In Block.java, the
Block.setY is a function that has a double parameter that is needed so the method knows what to set Y to when
it is called.

12. **Return type**: This is the data type of the data that is returned by a function. A function can return any
valid data type, or even none at all. Game.handleCollisions has a boolean return type, meaning at the end of the
function call, a boolean value is returned back to where the call originated.

13. **Inheritance**: A mechanism allowing classes to be derived from other classes with the former being a derived
class and the latter being the parent class. Child classes also obtain the attributes (fields and methods) from their
parent class while defining more of their own. The Sprite class is an example of inheritance since it inherits from 
the block class, getting its coordinate and block dimension attributes while adding its own velocity and acceleration
ones.

14. **Type Hierarchy**: Refers to the structure with which data types are related based on the types they inherit from.
The most broad type is at the top while the types that extend that type come below. Here, FlappyBox is a type that
extends the FallingBox type, which extends the Box type, and so on until the base Block type is reached. That means in
the type hierarchy, Block is at the top while FlappyBox is at the bottom, with each lower type extending the type above.

15. **Apparent type**: A variable's apparent type is the type of object that it was declared as. It is essentially the type
of the reference to that data instead of the data itself. In Game.handleCollisions, when flappyBox is passed into the
intersects() function, the function sees the data as a Box type instead of the FlappyBox type since the parameter was 
set with the Box type. Thus, to the function, the apparent type of the argument is Box.

16. **Actual type**: A variable's actual type is the type of object that it was created as at runtime. It is the type
of the data itself, not its reference. In Game.handleCollisions, when flappyBox is passed into the intersects() 
function, the function sees the data as a Box type instead of the FlappyBox type since the parameter was set with the 
Box type. The actual type of the variable when in the function is FlappyBox, even though it is seen as a Box object.

17. **Is-a relationship**: This represents inheritance, demonstrating type hierarchy by allowing one derived class to 
represent a specific type of a parent class. When we defined Box.java, a Box is-a Sprite since it extends that class
and adds on to it.

18. **Has-a relationship**: This represents aggregation, where one class contains a field with a type of another class.
Our Game class has a FlappyBox object as a field, indicating that there is a has-a relationship here where every 
instance of Game *has* a FlappyBox object.

19. **Method overloading**: Refers to when two methods have the same name but different parameter numbers and/or types.
In StdDraw.java, there are two methods with the name show, but one takes a time parameter while the other has no 
parameters. Since these methods have the same name but different parameters, this is an example of overloading.

20. **Method overriding**: Refers to when a sub-class defines a method with the same exact signature, meaning it has the
same return type, name, and parameters, as a method in a parent class. This results in the overriding of the parent 
method, meaning objects of the sub-class type will run the method defined in the sub-class when called instead of the
method that is defined in the parent class. In Pipe.java, the intersects function overrides the definition provided in
its parent class Box, meaning Pipe objects will have a different behavior than Box objects when intersects is called.

21. **Static polymorphism**: Refers to how the compiler can make substitutions at compile-time. A sub-class object
fitting into a parent class parameter, variable declaration, or list is an example of static polymorphism. Method
overloading is also form of static polymorphism. Game.handleCollisions demonstrates an example of static polymorphism
because it passes a FlappyBox object into an intersects() function, which has a Box parameter type. This substitution
from the more specific FlappyBox type to the base Box type is characteristic of static polymorphism.

22. **Dynamic polymorphism**: Refers to how the JVM can choose which version of a method to dispatch at runtime
based on the type of an object. A sub-class can override a method from a parent class, redefining it for objects of that
sub-class. It is not until runtime that the JVM checks the type of the calling object to decide which method it will
use. For example, the Pipe class is derived from the Box class, and both define an intersects() method with the one in
Pipe.java overriding the one in Box.java. Thus, in Game.handleCollisions(), it is not until runtime that the JVM sees
that pipe, the calling object, is a Pipe object and dispatches the overridden method.

## Part II: Data Structures
This game uses both built-in data structures and user-defined ones. Lists and arrays are used in order to store multiple
related items together. In Pipe.java, all the boxes that make up a pipe are stored together in an array to allow us to
easily implement a way to move the whole pipe together instead of moving each box individually. A list of Pipes in 
Game.java then allows for the handling of multiple Pipes across the screen to make the game work properly. Most of the
user-defined classes that are used to implement the game are also data structures. Block is a structure that creates
an object with a specific height, width, color, and coordinate location, consolidating all the information needed to
construct a standalone block. Sprite then adds movement components like velocity and acceleration implementations to it.
With more class extensions, more functionalities and/or fields containing more information are added. All of these
classes are data structures because of the way they allow for the organization and manipulation of data stored. By
storing all the associated data in distinct units as identified by unique instances of the class, we are able to
manipulate game objects more simply and make the algorithms that do so much more readable.

## Part III: Algorithm Example
One algorithm in the game is the Game.runGameLoop function that continuously clears the screen, moves the objects in
game by calling the move method on all components, draws the new positions of all the components, change and display
score, remove unnecessary pipes, show all components on the screen, and check to make sure game end conditions are not
yet met. This repeated sequence of computation until the game ends contains a sequence of defined steps, has some input
and output (keystroke inputs and updated screen display output), and has a clear stopping condition, meaning it has the
characteristics typical of algorithms. This algorithm ensures that the game continuously updates using user input until
some stopping condition is met. It forms the backbone of the game by defining a sort of feedback loop that keeps the
game going. 