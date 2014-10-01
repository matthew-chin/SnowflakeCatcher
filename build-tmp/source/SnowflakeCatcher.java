import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SnowflakeCatcher extends PApplet {

int icelen = 100;
boolean fast = false;
boolean g = false;
boolean r = false;

SnowFlake[] ice = new SnowFlake[icelen];

public void setup()
{
  rectMode(CENTER);
  size(300, 300);
  background(0);
  frameRate(100);
  for(int i = 0; i < icelen; i++)
  {
    ice[i] = new SnowFlake();
  }
}
public void draw()
{
  if(g)
  {
    background(50,205,50);
  }
  for (int i = 0; i < icelen; i++)
  {
    ice[i].lookDown();
    ice[i].move();
    ice[i].wrap();
    ice[i].erase();
    ice[i].show();
  }
}

public void keyPressed()
{
  if(key == '0')
  {
    fast = !fast;
  }
  if(key == 'g')
  {
    g = !g;
    if(!g)
    {
     background(0); 
   }
 }
 if(key == 'r')
 {
  if(!g)
  {
    r = !r;
    if(r)
    {
      background(255, 0, 0);
      for (int i = 0; i < icelen; i++)
      {
        ice[i].moving = false;
      }
    }
    else 
    {
      background(0);
      for (int i = 0; i < icelen; i++)
      {
        ice[i].moving = true;
      }
    }
  }
}
if(key == 'b')
{
  background(0);
}
}

public void mouseDragged()
{
  if(mouseButton == LEFT)
  {
    if(fast)
    {
      fill(50,205,50);
    }
    else
    {
      fill(255,0,0);  
    }
    noStroke();
    rect(mouseX, mouseY, 15, 15);
    fill(255,255,255);
    stroke(0, 0, 0);
  }
  else if(mouseButton == RIGHT)
  {
    fill(0,0,0); 
    rect(mouseX, mouseY, 15, 15);
  }
}

class SnowFlake
{
  int x, y;
  boolean moving;
  SnowFlake()
  {
    x = (int)(Math.random()*294)+4;
    y = (int)(Math.random()*600)-600;
    moving = true;
  }
  public void show()
  {
    fill(255,255,255);
    ellipse(x, y, 10, 10);
  }
  public void lookDown()
  {

    if(y + 6 < 300 && y + 6 > 0 && x > 6 && x < 294)
    {
      if(get(x, y + 6) == color(255,0,0) || get(x, y + 6) == color(255,255,255))
      {
        moving = false;
      }
      else
      {
        moving = true;
      }
    }
  }

  public void erase()
  {
    if(moving)
    {
      fill(0,0,0);
      if(get(x, y + 6) == color(50,205,50))
      {
        ellipse(x, y-5, 10,10);
      }
      else
      {
        ellipse(x, y-3, 10,10);
      }
    }
  }
  public void move()
  {
    if(moving)
    {
      if(get(x, y + 6) == color(50,205,50))
      {
        y += 3;
      }
      else
      {
        y += 1;
      }
    }
  }
  public void wrap()
  {
    if(y > 305)
    {
      fill(0,0,0);
      if(get(x, y + 6) == color(50,205,50))
      {
        ellipse(x, y-5, 10,10);
      }
      else
      {
        ellipse(x, y-3, 10,10);
      }
      y = (int)(Math.random()*300)-300;
      x = (int)(Math.random()*294)+4;
    }
  }
}


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SnowflakeCatcher" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
