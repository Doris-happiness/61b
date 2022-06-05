Lab5:  Getting Started on Project 2



### 5.1 Part I: Meet the Tile Rendering Engine

#### 5.1.1 Boring World

（记得每次打开相应的lab和project时，都要导入library，不然相应的packagex找不到，编译和执行时会报错。）



​		Open up the `BoringWorldDemo` file. Try running it and you should see a window appear that looks like the following:

<img src="https://sp18.datastructur.es/materials/lab/lab5/img/boringWorld.png" alt="boring world" style="zoom: 50%;" />

​		This world consists of empty space, except for the rectangular block near the bottom middle. The code to generate this world consists of three main parts:

- Initializing the tile rendering engine.
- Generating a two dimensional `TETile[][]` array.
- Using the tile rendering engine to display the `TETile[][]` array.



（1）The API for the tile rendering engine is simple.  After creating a `TERenderer` object, you simply need to call the `initialize` method, specifying the width and height of your world, where the width and height is given in terms of the number of tiles. Each tile is 16 pixels by 16 pixels, so for example, if we called `ter.initialize(10, 20)`, we’d end up with a world that is 10 tiles and 20 tiles tall, or equivalently 160 pixels wide and 320 pixels tall. 

（创建`TERenderer` object—> call `initialize`method 来初始化height和width）

即`ter.initialize(10, 20)`



（2）`TETile` objects are also quite simple.You can either build them from scratch using the `TETile`constructor (see `TETile.java`), or you can choose from a palette of pregenerated tiles in the file `Tileset.java`. For example, the code from BoringWorldDemo.java below generates a 2D array of tiles and fills them with the pregenerated tile given by `Tileset.NOTHING`.

（可以用`TETile`的constructor或者直接从`Tileset.java`这个文件里已有的tiles选一个生成）

```java
TETile[][] world = new TETile[WIDTH][HEIGHT];
for (int x = 0; x < WIDTH; x += 1) {
  for (int y = 0; y < HEIGHT; y += 1) {
    world[x][y] = Tileset.NOTHING;
  }
}
```



​	Of course, we can overwrite existing tiles. For example, the code below from BoringWorld.java creates a 14 x 4 tile region made up of the pregenerated tile `Tileset.WALL` and writes it over some of the `NOTHING` tiles created by the loop code shown immediately above.

```java
for (int x = 20; x < 35; x += 1) {
  for (int y = 5; y < 10; y += 1) {
    world[x][y] = Tileset.WALL;
  }
}
```



（3）The last step in rendering is to simply call `ter.renderFrame(world)`. Changes made to the tiles array will not appear on the screen until you call the `renderFrame` method.



#### 5.1.2 Random World

Now open up `RandomWorldDemo.java`. Try running it and you should see something like this:

<img src="/Users/apple/Desktop/屏幕快照 2022-05-15 10.29.52.png" alt="屏幕快照 2022-05-15 10.29.52" style="zoom:30%;" />

在这个`RandomWorldDemo.java` file里，做了如下几个事：

（1）create and use  an object of type `Random` that is a “[pseudorandom number generator](https://en.wikipedia.org/wiki/Pseudorandom_number_generator)”（伪随机数生成器）

（2）use a new type of conditional called a switch statement.（switch语句）

（3）delegated work to functions instead of doing everything in main. 

​		❁ The final and most important thing is that rather than doing everything in `main`, **our code delegates work to functions with clearly defined behavior**. This is critically important for your project 2 experience! You’re going to want to constantly identify small subtasks that can be solved with clearly defined methods. Furthermore, your methods should form a hierarchy of abstractions! 

● 关于`random number generator`

​		A random number generator does exactly what its name suggests, it produces an infinite stream of numbers that appear to be randomly ordered. The `Random` class provides the ability to produce *pseudorandom* numbers for us in Java. For example, the following code generates and prints 3 random integers:

```java
Random r = new Random(1000);
System.out.println(r.nextInt());
System.out.println(r.nextInt());
System.out.println(r.nextInt());
```

​		We call `Random` a *pseudorandom* number generator because it isn’t truly random. Underneath the hood, it uses cool math to take the previously generated number and calculate the next number. 

（用前一个产生的数，利用数学方法来计算下一个数）

Importantly, the sequence generated is deterministic, and the way we get different sequences is by choosing what is called a “seed”. If you start up a pseudorandom generator with a particular seed, you are guaranteed to get the exact sequence of random values.

（利用seed来控制产生的数）

In the above code snippet, the seed is the input to the `Random` constructor, so 1000 in this case. Having control over the seed is pretty useful since it allows us to indirectly control the output of the random number generator. If we provide the same seed to the constructor, we will get the same sequence values. For example, the code below prints 4 random numbers, then prints the SAME 4 random numbers again. 

（如果产生的seed一样，那么生成的随机数也会一样）

```java
Random r = new Random(82731);
System.out.println(r.nextInt());
System.out.println(r.nextInt());
System.out.println(r.nextInt());
System.out.println(r.nextInt());
r = new Random(82731);
System.out.println(r.nextInt());
System.out.println(r.nextInt());
System.out.println(r.nextInt());
System.out.println(r.nextInt());
```

In the case a seed is not provided by the user/programmer, i.e. `Random r = new Random()`, random number generators select a seed using some value that changes frequently and produces a lot of unique values, such as the current time and date.

（For now, `RandomWorldDemo` uses a hard coded seed, namely 2873123, so it will always generate the exact same random world. ）



### 5.2 Part II:  Use the Tile Rendering Engine

#### 5.2.1 Hex World Intro

​	Above, we’ve seen how we can draw a world and generate randomness. In the remainder of this lab, we’ll work our way towards strategically leveraging randomness and hierarchical abstraction to generate an interesting world: The ultimate goal is to create a random terrain generator that will build hexagon based worlds (similar to the board game [Settlers of Catan](https://www.google.com/search?q=settlers+of+catan&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiquNjwy5rZAhUFYawKHRnjBM8Q_AUICygC&biw=1700&bih=640), if you’ve heard of it). As an example, the world below contains fields of grass, fields of flowers, deserts, forests, and mountains.

<img src="/Users/apple/Library/Application Support/typora-user-images/image-20220515105059460.png" alt="image-20220515105059460" style="zoom:33%;" />

In the actual project 2, you’ll be generating random worlds as well, although in the project, they will be indoor spaces instead of open landscapes.

 Your world should be able to support differently sized hexagons. The picture above is of size-3 hexagons, and below we see a world consisting of size-2 and size-4 hexagons, respectively.

​                              <img src="/Users/apple/Library/Application Support/typora-user-images/image-20220515105254160.png" alt="image-20220515105254160" style="zoom:38%;" /><img src="/Users/apple/Desktop/屏幕快照 2022-05-15 10.53.05.png" alt="屏幕快照 2022-05-15 10.53.05" style="zoom:20%;" />



#### 5.2.2 Drawing A Single Hexagon

1、Start by trying to create a method `addHexagon` that adds a hexagon of side length‘s to a given position in the world.

​	**the most important thing is to break the task down into smaller pieces.**This will probably involve creating helper methods. Example hexagons of size 2, 3, 4, and 5 are shown below. Note that your hexagon should always have a “middle” that is two rows thick, no matter how large the size (ie. the widest part consists of two rows of equal length). This is important so that the hexagons tesselate nicely.

<img src="/Users/apple/Library/Application Support/typora-user-images/image-20220515105637554.png" alt="image-20220515105637554" style="zoom:50%;" />

These helper methods might involve drawing tasks, or they might involve calculating useful quantities.



#### 5.2.3 Drawing A Tesselation of Hexagons

​		Once you have code that can draw a single hexagon, you can try to tesselate them to form a world. Specifically, you should try to arrange them in the pattern shown below, consisting of 19 total hexagons.

<img src="/Users/apple/Library/Application Support/typora-user-images/image-20220515125455006.png" alt="image-20220515125455006" style="zoom:33%;" />

As with drawing a single hexagon, there are a huge number of ways to do this. The most important part is to identify helper methods that will be useful for the task!

First: I wrote a couple of methods that compute the bottom-left Position of a current hexagon’s neighbors. For example, I wrote topRightNeighbor, which computed the right thing to pass to addHexagon so that I could get my topRight neighbor. I did not write JUnit tests for this because I knew it’d be easy to visually test, though JUnit tests would have been fine. I then wrote bottomRight, and ended up with 3 whole hexagons out of the 19 I wanted.

Second: I tried to figure out how I could use bottomRight and topRight in some clever way to get the whole grid, but was a bit stymied. I considered trying to use recursion in some way, but it didn’t feel like the right solution for symmetry reasons (too much weird backtracking). I then thought about whether I’d be stuck having to write six different neighbor methods, and decided that seemed excessive. All of this was done without any coding. If I’d have started coding all this, it would have been a huge waste of time.”

Third: Stepping back, I decided to try to find the “nicest” way to try to lay out my hexagons and was ready to throw away my bottomRight and topRight methods entirely. Then the key AHA moment occurred when I noticed that the world consists of 5 columns of 3, 4, 5, 4, and 3 hexagons, respectively.

Fourth: I wrote a method called drawRandomVerticalHexes that draws a column of N hexes, each one with a random biome.

Fifth: I wrote a main method that is a little ugly, but basically calls drawRandomVerticalHexes five times, one for each of the five columns of the world, consisting of 3, 4, 5, 4, and 3 hexagons. To figure out the starting position for the “top” hex of each column, I used the topRightNeighbor or bottomRightNeighbor on the old top, as appropriate.

Sixth: I added some code to allow for interactivity so you can press a single key to get a new random world and enjoyed playing around with it. But interactivity will have to wait until next week’s lab for you guys.
